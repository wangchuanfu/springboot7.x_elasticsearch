package com.j1.xiaoxiang.controller.wireless;

import com.google.common.collect.Lists;
import com.j1.dao.TGmGoodsMapper;
import com.j1.xiaoxiang.entity.dto.PageResultDto;
import com.j1.xiaoxiang.entity.dto.ResultBean;
import com.j1.xiaoxiang.entity.dto.ShoesTradeResultDto;
import com.j1.xiaoxiang.exception.BusinessException;
import com.j1.xiaoxiang.exception.SGErrors;
import com.j1.xiaoxiang.query.ShoesTradeQuery;
import com.j1.xiaoxiang.service.ShoesTradeMallService;
import com.j1.xiaoxiang.utils.RegUtil;
import com.j1.xiaoxiang.utils.SeFeature;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.j1.xiaoxiang.utils.RegUtil.realSkWord;
import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
public class ShoesTradeController implements ShoesTradeControllerApi {

    @Autowired
    private SeFeature seFeature;

    //  @Value("#{'${se.feature.otherBrands}'.split(',')}")
    private List<String> otherBrands;

    @Autowired
    ShoesTradeMallService shoesTradeMallService;

    @Autowired
    private TGmGoodsMapper tGmGoodsMapper;
    /**
     *    @Autowired
     *     private SearchSuggestScheduled searchSuggestScheduled;
     */


    /**
     * 潮鞋搜索查询
     *
     * @param
     * @return
     */
    @Override
    public ResultBean<PageResultDto<ShoesTradeResultDto>> shoesTradeSearch(ShoesTradeQuery shoesTradeQuery) {
        shoesTradeQuery.setOriginalSk(shoesTradeQuery.getSk());

        PageResultDto<ShoesTradeResultDto> searchData;
        /**
         * 校验搜索词是否合法
         */
        if (checkAndProcessSk(shoesTradeQuery)) {
            searchData = shoesTradeMallService.search(shoesTradeQuery);
        } else {
            searchData = new PageResultDto<>(shoesTradeQuery.getPage(), shoesTradeQuery.getPageSize(), 0, Lists.newArrayList());

        }
        //搜索查询


        return new ResultBean<>(searchData);
    }

    /**
     * 检查并优化搜索词,去除表情,非法的字符
     *
     * @param shoesTradeQuery
     * @return
     */
    private boolean checkAndProcessSk(ShoesTradeQuery shoesTradeQuery) {
        shoesTradeQuery.setSk(realSkWord(shoesTradeQuery.getSk()));
        skProcess(shoesTradeQuery);
        if (shoesTradeQuery == null || StringUtils.isEmpty(shoesTradeQuery.getSk())) {
            log.error("shoesTradeQuery 为空:{}", shoesTradeQuery);
            throw new BusinessException(SGErrors.SK_INVALID);
        }
        if (RegUtil.isAllIllegalChar(shoesTradeQuery.getSk())) {
            log.debug("搜索词只包含表情符或非法字符，无结果");
            return false;
        }
        //去除字符串中的非法字符
        if (RegUtil.haveIllegalChar(shoesTradeQuery.getSk())) {
            shoesTradeQuery.setSk(RegUtil.replaceChar(shoesTradeQuery.getSk()));
        }
        //去除表情字符
        String ssk = EmojiParser.removeAllEmojis(shoesTradeQuery.getSk()).trim();
        if (StringUtils.isBlank(ssk)) {
            log.debug("搜索词只包含表情符，无需搜索");
            return false;
        }
        shoesTradeQuery.setSk(ssk.toLowerCase());
        getTrueFenci(shoesTradeQuery);
        return true;

    }

    /**
     * 正确的分词
     *
     * @param shoesTradeQuery
     */

    private void getTrueFenci(ShoesTradeQuery shoesTradeQuery) {
        ConcurrentHashMap<String, List<String>> repalceSk = seFeature.getFilterCategary("personFenCi");
        Result parseResult = DicAnalysis.parse(shoesTradeQuery.getSk());
        List<String> skList = parseResult.getTerms().stream().map(e -> e.getName()).collect(toList());
        repalceSk.entrySet().stream().forEach(e -> {
            if (shoesTradeQuery.getSk().contains(e.getKey()) && skList.contains(e.getKey())) {
                shoesTradeQuery.setSk(shoesTradeQuery.getSk().replace(e.getKey(), e.getValue().get(0)));
            }
        });
    }

    /**
     * 处理搜索词中 汉字加数字,并将其拆分开来,比如,iphone11--> iPhone 11
     *
     * @param shoesTradeQuery
     */
    private void skProcess(ShoesTradeQuery shoesTradeQuery) {
        StringBuilder newStr = new StringBuilder();
        String sk = shoesTradeQuery.getSk().toLowerCase();
        boolean findInt = false;
        //查询搜索品牌
        List<String> brands = seFeature.getBrands(sk);
        if (RegUtil.isAllEnglish(sk)) {
            for (int i = 0; i < sk.length(); i++) {
                if (Character.isDigit(sk.charAt(i))) {
                    String word = "";
                    //获取数字之前的单词
                    for (int j = i; j >= 0; j--) {
                        if (sk.charAt(j) == ' ' || j == 0) {
                            word = sk.substring(j, i);
                            word = word.trim();
                            break;
                        }
                    }
                    if (!findInt && brands.contains(word) && !isContainStr(word)) {//fix bug,针对关键字是eg开头的搜索,比如:eg4235,不要将其拆解为eg 4235
                        newStr.append(" ");
                    }
                    findInt = true;
                } else {
                    findInt = false;
                }
                newStr.append(sk.charAt(i));
            }
        }
    }

    /**
     * 潮鞋批量导入数据
     *
     * @return
     */
    @Override
    public ResultBean<String> fullSync() {
        long startTime = System.currentTimeMillis();
        List<Integer> allId = tGmGoodsMapper.selectAllId();
        Set<Integer> allIdSet;
        if (!CollectionUtils.isEmpty(allId)) {
            allIdSet = new HashSet<>(allId);
        } else {
            allIdSet = new HashSet<>();
        }
        //导入各种联想词,job
        //searchSuggestScheduled.importShoesTradeSuggestToESScheduled("");
        //删除不在上架商品id集合内的商品
        // shoesTradeMallService.deleteGoodsNotInIds(allIdSet);
        //导入应该上架的商品
        if (!CollectionUtils.isEmpty(allId)) {
            shoesTradeMallService.batchImportShoesTradeGoodsData(allId);
        }
        long endTime = System.currentTimeMillis();
        return new ResultBean<>(String.format("全量同步数据完毕,耗时:%s,导入总数：" + allId.size(), endTime - startTime));
    }

    /**
     * 黑名单字段判断
     *
     * @param str
     * @return
     */
    public boolean isContainStr(String str) {
        if (otherBrands.contains(str)) {
            return true;
        }
        return false;
    }
}
