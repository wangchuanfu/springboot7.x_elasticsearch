package com.j1.xiaoxiang.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.j1.dao.TGmGoodsMapper;
import com.j1.pojo.ShoesTradeExpand;
import com.j1.pojo.vo.ShoesTrade;
import com.j1.utils.DateUtils;
//import com.j1.utils.HanyuPinyinHelper;
import com.j1.xiaoxiang.constant.ShoesTradeAggs;
import com.j1.xiaoxiang.constant.ShoesTradeConstant;
import com.j1.xiaoxiang.constant.ShoesTradeGoodsPropertyEnum;
import com.j1.xiaoxiang.entity.dto.CustomEsTemplate;
import com.j1.xiaoxiang.entity.dto.PageResultDto;
import com.j1.xiaoxiang.entity.dto.ShoesTradeResultDto;
import com.j1.xiaoxiang.event.SearchEvent;
import com.j1.xiaoxiang.query.ShoesTradeQuery;
import com.j1.xiaoxiang.repository.ShoesTradeRepository;
import com.j1.xiaoxiang.service.ShoesTradeMallService;
import com.j1.xiaoxiang.utils.RegUtil;
import com.j1.xiaoxiang.utils.SeFeature;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.j1.xiaoxiang.constant.ShoesTradeAggs.*;
import static com.j1.xiaoxiang.utils.RegUtil.realSkWord;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@Slf4j
public class ShoesTradeMallServiceImpl implements ShoesTradeMallService {
    @Autowired
    private SeFeature seFeature;

    // @Value("${se.shoes_trade.minScore}")
    private String minScore;

    // @Value("${se.shoes_trade.gotoUrl}")
    private String gotoUrl;
    @Autowired
    private ShoesTradeRepository shoesTradeRepository;
    @Autowired
    private TGmGoodsMapper tGmGoodsMapper;
    @Autowired
    private CustomEsTemplate customEsTemplate;


    @Override
    public PageResultDto<ShoesTradeResultDto> search(ShoesTradeQuery shoesTradeQuery) {
        //初始化优化搜索词
        replaceSkForSearch(shoesTradeQuery);
        /**
         *构造查询条件
         */
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //boolean 查询
        addBoolQueryToNativeQuery(shoesTradeQuery, nativeSearchQueryBuilder);
        //排序
        addSortToNativeQuery(shoesTradeQuery, nativeSearchQueryBuilder);
        //聚合
        addAggregationToNativeQuery(nativeSearchQueryBuilder);
        //分页查询
        addPageParamToNativeQuery(shoesTradeQuery, nativeSearchQueryBuilder);

        //设置minscore
        nativeSearchQueryBuilder.withMinScore(Float.valueOf(minScore));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        log.info("searchQuery dsl:{}", searchQuery.getQuery().toString());
        //执行查询,底层会利用反射
        IndexCoordinates index=null;
        AggregatedPage<ShoesTrade> aggregatedPage = customEsTemplate.queryForPage(searchQuery, ShoesTrade.class,index);

        List<ShoesTradeResultDto> shoesTradeResultDtoList = aggregatedPage.getContent().stream().map(
                m -> {
            ShoesTradeResultDto shoesTradeResultDto = new ShoesTradeResultDto();//返回给前段封装的model实体

            BeanUtils.copyProperties(m, shoesTradeResultDto);

            shoesTradeResultDto.setGotoUrl(gotoUrl + m.getGoodsCode());
            shoesTradeResultDto.setSellPrice(shoesTradeResultDto.getSellPrice() == null ? 0 : shoesTradeResultDto.getSellPrice());
            return shoesTradeResultDto;

        }).collect(Collectors.toList());

        log.info("searchKeyword: {} , shoesTradeResultDtoList size: {} ", shoesTradeQuery.getSk(), shoesTradeResultDtoList.size());
        //聚合
        Set<String> shoesTradesuitCrowdAggSet = getAggregation(aggregatedPage.getAggregations(), shoesTradesuitCrowdAgg.getAggName());
        Set<String> shoesTradesuitCrowd = Sets.newHashSet();
        shoesTradesuitCrowdAggSet.stream().map(e -> e + "/").map(str -> str.split("/"))
                .flatMap(e -> Arrays.stream(e))
                .filter(filterStr -> StringUtils.isNotBlank(filterStr))
                .collect(Collectors.toSet());
        Set<String> shoesTrademeasuresAggSet = getAggregation(aggregatedPage.getAggregations(), shoesTrademeasuresAgg.getAggName());
        //排序
        List<String> shoesTrademeasuresNoHavingCharSortSet = getChiMaList(shoesTrademeasuresAggSet);
        if ((int) aggregatedPage.getTotalElements() > 0) {

            if (shoesTradeQuery.getSearchNum() != null && shoesTradeQuery.getSearchNum() == 1) {

                userSearchWordEventTracking(shoesTradeQuery, 1);

            } else if (shoesTradeQuery.getSearchNum() != null && shoesTradeQuery.getSearchNum() == 2) {

                shoesTradeQuery.setOriginalSk(shoesTradeQuery.getSearchFirstSk());
                userSearchWordEventTracking(shoesTradeQuery, 1);

            } else if (shoesTradeQuery.getSearchNum() != null && shoesTradeQuery.getSearchNum() == 3) {

                shoesTradeQuery.setOriginalSk(shoesTradeQuery.getSearchFirstSk());

                userSearchWordEventTracking(shoesTradeQuery, 1);

            }
        } else if ((int) aggregatedPage.getTotalElements() <= 0) {
            if (shoesTradeQuery.getSearchNum() != null && shoesTradeQuery.getSearchNum() == 1

                    && (!RegUtil.HasDigit(shoesTradeQuery.getOriginalSk()) || RegUtil.isDigit(shoesTradeQuery.getOriginalSk()))) {

                userSearchWordEventTracking(shoesTradeQuery, 2);

            } else if (shoesTradeQuery.getSearchNum() != null && shoesTradeQuery.getSearchNum() == 3

                    && StringUtils.isNotBlank(shoesTradeQuery.getSearchFirstSk()) && (RegUtil.HasDigit(shoesTradeQuery.getSearchFirstSk()) && !RegUtil.isDigit(shoesTradeQuery.getSearchFirstSk()))) {

                shoesTradeQuery.setOriginalSk(shoesTradeQuery.getSearchFirstSk());

                userSearchWordEventTracking(shoesTradeQuery, 4);
            }
        }
        return new PageResultDto<>(shoesTradeQuery.getPage(), shoesTradeQuery.getPageSize(),
                (int) aggregatedPage.getTotalPages(),
                //设置搜索结果
                shoesTradeResultDtoList,
                //设置品牌聚合结果
                getAggregation(aggregatedPage.getAggregations(), shoesTradeBrandAgg.getAggName()),
                //设置类目聚合结果
                getAggregation(aggregatedPage.getAggregations(), shoesTradeCategoryAgg.getAggName()),
                //设置平台聚合结果
                getAggregation(aggregatedPage.getAggregations(), shoesTradePlatformAgg.getAggName()),
                getAggregation(aggregatedPage.getAggregations(), shoesTradeserisNameAgg.getAggName()),
                shoesTradesuitCrowd,
                shoesTrademeasuresNoHavingCharSortSet
        );
    }

    public List<String> getChiMaList(Set<String> shoesTrademeasuresAggSet) {
        List<String> shoesTrademeasuresNoHavingCharSortSet = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(shoesTrademeasuresAggSet)) {
            Set<String> shoesTrademeasures = shoesTrademeasuresAggSet.stream().map(add -> add + "/")
                    .map(str -> str.split("/"))
                    .flatMap(e -> Arrays.stream(e))
                    .filter(filterStr -> StringUtils.isNotBlank(filterStr))
                    .collect(Collectors.toSet());
            if (CollectionUtils.isNotEmpty(shoesTrademeasures)) {
                //数字排序
                List<Double> NoHavingCharData = shoesTrademeasures.stream().filter(e -> !RegUtil.isHavingChar(e)).map(e ->
                        Double.parseDouble(e.replace(ShoesTradeConstant.SAN_FEN_ZHI_YI, ShoesTradeConstant.SAN_FEN_ZHI_YI_SHU_ZI)
                                .replace(ShoesTradeConstant.SAN_FEN_ZHI_ER, ShoesTradeConstant.SAN_FEN_ZHI_ER_SHU_ZI)
                                .replace(ShoesTradeConstant.ER_FEN_ZHI_YI, ShoesTradeConstant.ER_FEN_ZHI_YI_SHU_ZI))
                ).collect(Collectors.toList());
                //C结尾排序
                List<Double> cData = shoesTrademeasures.stream().filter(e -> e.endsWith(ShoesTradeConstant.C_ENDFIX)).map(e -> e.toString().replace(ShoesTradeConstant.C_ENDFIX, ShoesTradeConstant.KG_ENDFIX_REPALCE)).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
                //Y结尾排序
                List<Double> yData = shoesTrademeasures.stream().filter(e -> e.endsWith(ShoesTradeConstant.Y_ENDFIX)).map(e -> e.toString().replace(ShoesTradeConstant.Y_ENDFIX, ShoesTradeConstant.KG_ENDFIX_REPALCE)).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
                shoesTrademeasuresNoHavingCharSortSet.addAll(getSortData(NoHavingCharData, 0));
                shoesTrademeasuresNoHavingCharSortSet.addAll(getSortData(cData, 1));
                shoesTrademeasuresNoHavingCharSortSet.addAll(getSortData(yData, 2));
                //英文排序 XS||S||M||L||XXL
                List<String> noShuZiList = shoesTrademeasures.stream().filter(e -> RegUtil.isNoShuZi(e)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(noShuZiList)) {
                    noShuZiList.sort(Comparator.naturalOrder());
                    shoesTrademeasuresNoHavingCharSortSet.addAll(noShuZiList);
                }
                shoesTrademeasuresNoHavingCharSortSet.remove(null);
            }
        }
        return shoesTrademeasuresNoHavingCharSortSet;
    }

    public List<String> getSortData(List<Double> data, int type) {
        if (CollectionUtils.isEmpty(data)) {
            return Lists.newArrayList();
        }
        data.sort(Comparator.naturalOrder());
        List<String> dataCollect = null;
        if (type == 0) {
            dataCollect = data.stream().map(e -> e.toString()
                    .replace(ShoesTradeConstant.XIAO_SHU_ENDFIX_REPALCE, ShoesTradeConstant.KG_ENDFIX_REPALCE)
                    .replace(ShoesTradeConstant.SAN_FEN_ZHI_YI_SHU_ZI, ShoesTradeConstant.SAN_FEN_ZHI_YI)
                    .replace(ShoesTradeConstant.SAN_FEN_ZHI_ER_SHU_ZI, ShoesTradeConstant.SAN_FEN_ZHI_ER)
                    .replace(ShoesTradeConstant.ER_FEN_ZHI_YI_SHU_ZI, ShoesTradeConstant.ER_FEN_ZHI_YI))
                    .collect(Collectors.toList());
        } else if (type == 1) {
            dataCollect = data.stream().map(e -> e.toString()
                    .replace(ShoesTradeConstant.XIAO_SHU_ENDFIX_REPALCE, ShoesTradeConstant.KG_ENDFIX_REPALCE) + ShoesTradeConstant.C_ENDFIX)
                    .collect(Collectors.toList());
        } else if (type == 2) {
            dataCollect = data.stream().map(e -> e.toString()
                    .replace(ShoesTradeConstant.XIAO_SHU_ENDFIX_REPALCE, ShoesTradeConstant.KG_ENDFIX_REPALCE) + ShoesTradeConstant.Y_ENDFIX)
                    .collect(Collectors.toList());
        }
        return dataCollect;
    }


    public void userSearchWordEventTracking(ShoesTradeQuery shoesTradeQuery, int i) {
        SearchEvent searchEvent = new SearchEvent();
        searchEvent.setType(i);
        BeanUtils.copyProperties(shoesTradeQuery, searchEvent);
        searchEvent.setSk(shoesTradeQuery.getOriginalSk());
        // searchAsyncHandle.asyncInsertSearchWordToDatabaseHandle(searchEvent);
    }

    /**
     * 获取聚合结果的key
     *
     * @param aggregations
     * @param aggName
     */
    private Set<String> getAggregation(Aggregations aggregations, String aggName) {
        StringTerms stringTerms = null;
        Set<String> termSet = null;
        stringTerms = aggregations.get(aggName);
        termSet = stringTerms.getBuckets().stream().map(StringTerms.Bucket::getKeyAsString).collect(Collectors.toSet());
        return termSet;
    }

    @Override
    public void deleteGoodsNotInIds(Set<Integer> allIdSet) {

    }

    @Override
    public List<Integer> batchImportShoesTradeGoodsData(List<Integer> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            log.error("param参数错误：ids={}为空", ids);
            return null;
        }
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "500");
        int pageSize = 100;
        if (CollectionUtils.isNotEmpty(ids)) {
//            int pageSize = 1000;
            int allSize = ids.size() / pageSize + (ids.size() % pageSize == 0 ? 0 : 1);
            log.info("开始全量潮鞋交易数据导入,一共取到:{}页", allSize == 0 ? 1 : allSize);
            IntStream.range(0, allSize).parallel().forEach(i -> {
                int start = i * pageSize;
                int end = start + pageSize;
                if (end > ids.size()) {
                    end = ids.size();
                }
                List<Integer> goodsSubIds = ids.subList(start, end);
                try {
                    List<ShoesTrade> shoesTradeList = getShoesTradeList(goodsSubIds);
                    if (CollectionUtils.isNotEmpty(shoesTradeList)) {
                        shoesTradeRepository.saveAll(shoesTradeList);
                    }
                } catch (Exception e) {
                    log.error("潮鞋商品数据导入异常{},idList:{}", e, ids);
                    e.printStackTrace();
                }
            });
            log.debug("潮鞋交易数据导入完毕");
        }
        return ids;


    }

    /**
     * 查询商品详情
     *
     * @param goodsSubIds 商品表主键id集合
     * @return
     */
    private List<ShoesTrade> getShoesTradeList(List<Integer> goodsSubIds) {
        List<ShoesTradeExpand> shoesTradeGoodsList = tGmGoodsMapper.selectShoesTradeMallGoodsList(goodsSubIds);
        if (CollectionUtils.isEmpty(shoesTradeGoodsList)) {
            return Lists.newArrayList();
        }
        List<ShoesTrade> shoesTradeList = shoesTradeGoodsList.stream().parallel().map(g -> {
            Map<Integer, String> proMap = new HashMap<>();
            g.getTGmGoodsProperties().forEach(p -> {
                        if (p.getPropertyCode() == 0 && p.getPropertyName().equalsIgnoreCase(ShoesTradeGoodsPropertyEnum.CHI_CUN.getPropertyName())) {
                            proMap.put(100, p.getPropertyValue());
                        } else {
                            proMap.put(p.getPropertyCode(), p.getPropertyValue());
                        }
                    }
            );
            String saleTime = proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.FA_SHOU_RI_QI.getPropertyCode(), "").replace(ShoesTradeConstant.DOU_HAO_VERTICAL_LINE, ShoesTradeConstant.SHU_GANG_VERTICAL_LINE);
            String suitCrowd = proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.SHI_YONG_REN_QUN.getPropertyCode(), "").replace(ShoesTradeConstant.SEPARATOR_VERTICAL_LINE, ShoesTradeConstant.SEPARATOR_DIAGONAL);
            String measures = proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.CHI_CUN.getPropertyCode(), "").replace(ShoesTradeConstant.SEPARATOR_VERTICAL_LINE, ShoesTradeConstant.SEPARATOR_DIAGONAL);
            String shoesFashtion = proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.FENG_GE.getPropertyCode(), "").replace(ShoesTradeConstant.SEPARATOR_VERTICAL_LINE, ShoesTradeConstant.SEPARATOR_DIAGONAL);
            ShoesTrade shoesTrade = new ShoesTrade();
            BeanUtils.copyProperties(g, shoesTrade);
            if (shoesTrade.getSellPrice() != null && shoesTrade.getSellPrice() > 0) {
                shoesTrade.setPriceScore(0.05D);
            } else {
                shoesTrade.setSellPrice(0);
                shoesTrade.setPriceScore(0.001D);
            }
            if (StringUtils.isNotBlank(saleTime)) {
                if (saleTime.length() == 7) {
                    shoesTrade.setSaleTime(DateUtils.strToDateYWeek(saleTime));
                } else if (saleTime.length() == 4) {
                    shoesTrade.setSaleTime(DateUtils.strToDateY(saleTime));
                } else if (saleTime.length() == 10) {
                    shoesTrade.setSaleTime(DateUtils.strToDate(saleTime));
                }
            }
            shoesTrade.setSuitCrowd(suitCrowd);
            List<String> suitCrowdList = Arrays.asList(suitCrowd.split(ShoesTradeConstant.SEPARATOR_DIAGONAL));
            if (CollectionUtils.isNotEmpty(suitCrowdList)) {
                suitCrowdList = suitCrowdList.stream().distinct().collect(Collectors.toList());
            }
            shoesTrade.setSuitCrowdAll(suitCrowdList);
           // shoesTrade.setGPyTitle(HanyuPinyinHelper.hanyuIkAnalyzedToPinYin(g.getGoodsName()));
            shoesTrade.setMeasures(measures);
            List<String> measuresList = Arrays.asList(measures.split(ShoesTradeConstant.SEPARATOR_DIAGONAL));
            if (CollectionUtils.isNotEmpty(measuresList)) {
                measuresList = measuresList.stream().distinct().collect(Collectors.toList());
            }
            shoesTrade.setMeasuresAll(measuresList);
            List<String> valueAll = Lists.newArrayList();
            String colorValue = proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.PEI_SE.getPropertyCode(), "");
            if (StringUtils.isNotBlank(colorValue) && colorValue.contains(ShoesTradeConstant.SEPARATOR_DIAGONAL)) {
                List<String> colorList = Arrays.asList(colorValue.split(ShoesTradeConstant.SEPARATOR_DIAGONAL));
                List<String> colorRealSe = colorList.stream().map(e -> e + ShoesTradeConstant.END_PREFIX).collect(Collectors.toList());
                valueAll.addAll(colorList);
                valueAll.addAll(colorRealSe);
            } else {
                valueAll.addAll(Arrays.asList(colorValue));
            }
            shoesTrade.setColourMatchingAll(valueAll);
            shoesTrade.setColourMatching(colorValue);
            shoesTrade.setClosingMode(proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.BI_HE_FANG_SHI.getPropertyCode(), ""));
            shoesTrade.setShoesStyle(proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.XIE_TOU_KUAN_SHI.getPropertyCode(), ""));
            shoesTrade.setShoesHeight(proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.XIE_BANG_GAO_DU.getPropertyCode(), ""));
            shoesTrade.setShoesMaterial(proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.XIE_DI_CAI_LIAO.getPropertyCode(), ""));
            shoesTrade.setShoesType(proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.XIE_GEN_LEI_XING.getPropertyCode(), ""));
            shoesTrade.setUpperMaterial(proMap.getOrDefault(ShoesTradeGoodsPropertyEnum.XIE_MIAN_CAI_ZHI.getPropertyCode(), ""));
            shoesTrade.setShoesFashtion(shoesFashtion);
            shoesTrade.setShoesFashtionAll(Arrays.asList(shoesFashtion.split(ShoesTradeConstant.SEPARATOR_DOUHAO)));
            String sk = mergeField(" ", g.getGoodsName(),
                    g.getProductCategoryName(), g.getSerisName(), g.getBrandName(), shoesTrade.getGoodsNumber(),
                    CollectionUtils.isNotEmpty(valueAll) ? mergeField("", valueAll) : "",
                    shoesTrade.getSuitCrowd().replace(ShoesTradeConstant.SEPARATOR_DIAGONAL, " "),
                    shoesTrade.getClosingMode(),
                    shoesTrade.getShoesStyle(),
                    shoesTrade.getShoesHeight(),
                    shoesTrade.getShoesMaterial(),
                    shoesTrade.getShoesType(),
                    shoesTrade.getUpperMaterial(),
                    shoesTrade.getShoesFashtion().replace(ShoesTradeConstant.SEPARATOR_DIAGONAL, " ")
            );
            shoesTrade.setSk(sk);
           // shoesTrade.setSkPyTitle(HanyuPinyinHelper.hanyuIkAnalyzedToPinYin(shoesTrade.getSk()));
            // searchAsyncHandle.asyncImportSuggestWord(shoesTrade);
            return shoesTrade;
        }).collect(Collectors.toList());

        return shoesTradeList;
    }

    private void replaceSkForSearch(ShoesTradeQuery shoesTradeQuery) {
        //类目过滤
        ConcurrentHashMap<String, List<String>> repalceSk = seFeature.getFilterCategary("repalceSk");
        if (repalceSk.containsKey(realSkWord(shoesTradeQuery.getOriginalSk()))) {
            //\\s
            shoesTradeQuery.setSk(repalceSk.getOrDefault(realSkWord(shoesTradeQuery.getOriginalSk()), Lists.newArrayList()).get(0));
            log.info("sk==>被替换做：{}", shoesTradeQuery.getSk());
        }

    }

    /**
     * 给nativeQueryBuilder 添加分页参数
     *
     * @param shoesTradeQuery          前端请求参数
     * @param nativeSearchQueryBuilder native查询Builder
     */
    private void addPageParamToNativeQuery(ShoesTradeQuery shoesTradeQuery, NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.withPageable(PageRequest.of(shoesTradeQuery.getPage() - 1, shoesTradeQuery.getPageSize()));
    }


    /**
     * 拼接字段
     *
     * @param separator 分隔符
     * @param fields    需要拼接的字段
     * @return 拼接好的字符串
     */
    private String mergeField(String separator, Object... fields) {
        List<Object> list = new ArrayList<>();
        for (Object f : fields) {
            if (f != null && StringUtils.isNotBlank(f.toString())) {
                list.add(f);
            }
        }
        return StringUtils.join(list, separator);

    }

    private void addAggregationToNativeQuery(NativeSearchQueryBuilder nativeSearchQueryBuilder) {

        TermsAggregationBuilder brandsAggregationBuilder = AggregationBuilders.terms(shoesTradeBrandAgg.getAggName()).size(14)
                .field(shoesTradeBrandAgg.getFieldName());

        brandsAggregationBuilder.showTermDocCountError(true);

        TermsAggregationBuilder categoryAggregatonBuilder = AggregationBuilders
                .terms(shoesTradeCategoryAgg.getAggName())
                .field(shoesTradeCategoryAgg.getFieldName())
                .size(14);
        categoryAggregatonBuilder.showTermDocCountError(true);

        TermsAggregationBuilder platfromAggregationBuilder = AggregationBuilders
                .terms(shoesTradePlatformAgg.getAggName())
                .field(shoesTradePlatformAgg.getFieldName())
                .size(14);
        platfromAggregationBuilder.showTermDocCountError(true);

        TermsAggregationBuilder serisNameAggregationBuilder = AggregationBuilders
                .terms(shoesTradeserisNameAgg.getAggName())
                .field(shoesTradeserisNameAgg.getFieldName())
                .size(14);
        serisNameAggregationBuilder.showTermDocCountError(true);

        TermsAggregationBuilder suitCrowdAggregationBuilder = AggregationBuilders
                .terms(shoesTradesuitCrowdAgg.getAggName())
                .field(shoesTradesuitCrowdAgg.getFieldName())
                .size(14);
        suitCrowdAggregationBuilder.showTermDocCountError(true);

        TermsAggregationBuilder measuresAggregationBuilder = AggregationBuilders
                .terms(shoesTrademeasuresAgg.getAggName())
                .field(shoesTrademeasuresAgg.getFieldName())
                .size(14);
        measuresAggregationBuilder.showTermDocCountError(true);

        nativeSearchQueryBuilder.addAggregation(brandsAggregationBuilder)
                .addAggregation(categoryAggregatonBuilder)
                .addAggregation(serisNameAggregationBuilder)
                .addAggregation(suitCrowdAggregationBuilder)
                .addAggregation(measuresAggregationBuilder)
                .addAggregation(platfromAggregationBuilder);

    }


    private void addBoolQueryToNativeQuery(ShoesTradeQuery shoesTradeQuery, NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        //boolquery
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //score query
        addScoreClauseToBoolQuery(shoesTradeQuery, boolQueryBuilder);
        //filter query
        addFilterClauseToBoolQuery(shoesTradeQuery, boolQueryBuilder);
        //对pricescore 二次评分
        FieldValueFactorFunctionBuilder fieldQuery = new FieldValueFactorFunctionBuilder("priceScore");

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder, fieldQuery).boostMode(CombineFunction.SUM);//一种评分机制

        nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);

    }
    //增加过滤条件
    private void addFilterClauseToBoolQuery(ShoesTradeQuery shoesTradeQuery, BoolQueryBuilder boolQueryBuilder) {
        BoolQueryBuilder boolQueryBuilderForBrand = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getBrands())) {
            List<String> boolQueryBuilderForBrands = shoesTradeQuery.getBrands().stream()
                    .map(add -> add + ShoesTradeConstant.SEPARATOR_DIAGONAL)

                    .map(str -> str.split(ShoesTradeConstant.SEPARATOR_DIAGONAL))

                    .flatMap(e -> Arrays.stream(e))

                    .filter(filterStr -> StringUtils.isNotBlank(filterStr))

                    .collect(Collectors.toList());

            boolQueryBuilderForBrands.forEach(b -> boolQueryBuilderForBrand.should(termsQuery("brandName.brandName_analyzed",b, b.toLowerCase(), b.toUpperCase())));

            boolQueryBuilder.filter(boolQueryBuilderForBrand);
        }


        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getBrandIds())) {
            shoesTradeQuery.getBrandIds().forEach(b -> boolQueryBuilderForBrand.must(termsQuery("brandId", b)));
            boolQueryBuilder.filter(boolQueryBuilderForBrand);
        }
        BoolQueryBuilder boolQueryBuilderForCategory = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getCategorys())) {
            //使用不分词的字段,索引中的值没有转小写,所以这里的 c也不转小写 ,保持一致
            shoesTradeQuery.getCategorys().forEach(c -> boolQueryBuilderForCategory.should(QueryBuilders.termsQuery("productCategoryName.productCategoryName_sym", c,c.toLowerCase(),c.toUpperCase())));
            boolQueryBuilder.filter(boolQueryBuilderForCategory);
        }
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getCategoryIds())) {
            shoesTradeQuery.getCategoryIds().forEach(b -> boolQueryBuilderForCategory.must(termsQuery("productCategoryId", b)));
            boolQueryBuilder.filter(boolQueryBuilderForCategory);
        }
        BoolQueryBuilder boolQueryBuilderForSerisName = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getSerisIds())) {
            shoesTradeQuery.getSerisIds().forEach(b -> boolQueryBuilderForSerisName.must(termsQuery("serisId", b)));
            boolQueryBuilder.filter(boolQueryBuilderForSerisName);
        }
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getSerisNames())) {
            //使用不分词的字段,索引中的值没有转小写,所以这里的 c也不转小写 ,保持一致
            shoesTradeQuery.getSerisNames().forEach(c -> boolQueryBuilderForSerisName.should(QueryBuilders.termsQuery("serisName.serisName_sym", c,c.toLowerCase(),c.toUpperCase())));
            boolQueryBuilder.filter(boolQueryBuilderForSerisName);
        }

        BoolQueryBuilder boolQueryBuilderForsuitCrowd = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getSuitCrowd())) {
            //使用不分词的字段,索引中的值没有转小写,所以这里的 c也不转小写 ,保持一致
            shoesTradeQuery.getSuitCrowd().forEach(c -> boolQueryBuilderForsuitCrowd.should(QueryBuilders.termQuery("suitCrowdAll", c)));
            boolQueryBuilder.filter(boolQueryBuilderForsuitCrowd);
        }

        BoolQueryBuilder boolQueryBuilderForMeasures= QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getMeasures())) {
            //使用不分词的字段,索引中的值没有转小写,所以这里的 c也不转小写 ,保持一致
            shoesTradeQuery.getMeasures().forEach(c -> boolQueryBuilderForMeasures.should(QueryBuilders.termQuery("measuresAll", c)));
            boolQueryBuilder.filter(boolQueryBuilderForMeasures);
        }
//        if(StringUtils.isNotBlank(shoesTradeQuery.getPriceSort())||(shoesTradeQuery.getSaleTimeSort()!=null&&shoesTradeQuery.getSaleTimeSort()>0)){
//            boolQueryBuilder.filter(QueryBuilders.rangeQuery("sellPrice").gt(0L));
//        }

        if (shoesTradeQuery.getMinPrice() != null && shoesTradeQuery.getMinPrice() >= 0L) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("sellPrice").gte(shoesTradeQuery.getMinPrice()));
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("sellPrice").gt(0L));
        }
        if (shoesTradeQuery.getMaxPrice() != null && shoesTradeQuery.getMaxPrice() >= 0L) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("sellPrice").lte(shoesTradeQuery.getMaxPrice()));
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("sellPrice").gt(0L));
        }
        BoolQueryBuilder boolQueryBuilderForPlatfrom = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(shoesTradeQuery.getPlatformList())) {
            shoesTradeQuery.getPlatformList().forEach(c -> boolQueryBuilderForPlatfrom.should(QueryBuilders.termQuery("thirdPlatformCode", c)));
            boolQueryBuilder.filter(boolQueryBuilderForPlatfrom);
        }
    }

    /**
     * 在BoolQueryBuilder中添加与score相关的bool子句
     *
     * @param shoesTradeQuery
     * @param boolQueryBuilder
     */

    private void addScoreClauseToBoolQuery(ShoesTradeQuery shoesTradeQuery, BoolQueryBuilder boolQueryBuilder) {
        final String sk = shoesTradeQuery.getSk();
        long start = System.currentTimeMillis();
        //全是中文的判断
        if (RegUtil.isAllChinese(sk)) {

            boolQueryBuilder.should(
                    constantScoreQuery(matchQuery("goodsName", sk).operator(Operator.AND)).
                            boost(0.008f));
        } else if (RegUtil.isAllEnglish(sk)) {
            boolQueryBuilder.should(
                    constantScoreQuery(matchQuery("gPyTitle", sk).operator(Operator.AND)).
                            boost(0.006f));
            boolQueryBuilder.should(
                    constantScoreQuery(
                            matchQuery("skPyTitle", shoesTradeQuery.getSk()).operator(Operator.AND)
                    ).boost(0.3f)
            );
        } else {
            boolQueryBuilder.should(
                    constantScoreQuery(
                            matchQuery("goodsName", sk).operator(Operator.AND))
                            .boost(0.004f));
            boolQueryBuilder.should(
                    constantScoreQuery(
                            matchQuery("sk", sk).operator(Operator.AND)
                    ).boost(0.3f)
            );
        }
        boolQueryBuilder.should(matchPhraseQuery("sk", sk).slop(1)).boost(0.01f);//位移

        //查询品牌
        SeFeature.Feature feature = seFeature.getFeature(realSkWord(shoesTradeQuery.getOriginalSk()));
        if (CollectionUtils.isNotEmpty(feature.getBrandSet())){
          boolQueryBuilder.should(
                  constantScoreQuery(termsQuery("brandName.brandName_analyzed",feature.getBrandSet()).boost(0.005f))
          );
        }  if (CollectionUtils.isNotEmpty(feature.getCategorySet())) {
            boolQueryBuilder.should(constantScoreQuery(termsQuery("productCategoryName.productCategoryName_sym",
                    feature.getCategorySet())).boost(0.005f));
        }

        if (CollectionUtils.isNotEmpty(feature.getSeriesNameSet())) {
            boolQueryBuilder.should(constantScoreQuery(termsQuery("serisName.serisName_sym",
                    feature.getSeriesNameSet())).boost(0.005f));
        }
        //取消顺序
        //boolQueryBuilder.disableCoord(false);

    }

    /**
     *排序
     * @param shoesTradeQuery 前台传递的参数
     * @param nativeSearchQueryBuilder query
     */

    private void addSortToNativeQuery(ShoesTradeQuery shoesTradeQuery, NativeSearchQueryBuilder nativeSearchQueryBuilder) {

        if (StringUtils.isNotBlank(shoesTradeQuery.getPriceSort())) {
            SortBuilder gPriceSortBuilder = new FieldSortBuilder("sellPrice").missing(0);//按照售价排序
            switch (shoesTradeQuery.getPriceSort()){
                case "desc":
                    gPriceSortBuilder.order(SortOrder.DESC);
                    break;
                case "asc":
                    gPriceSortBuilder = gPriceSortBuilder.order(SortOrder.ASC);
                    default:
            }
            nativeSearchQueryBuilder.withSort(gPriceSortBuilder);
        }else if(StringUtils.isNotBlank(shoesTradeQuery.getSalesVolumeSort())){

            FieldSortBuilder saleCountBuileder = new FieldSortBuilder("saleCount").missing(0);//按照销量排序
            switch (shoesTradeQuery.getSalesVolumeSort()){
                case "desc":
                    saleCountBuileder= saleCountBuileder.order(SortOrder.DESC);
                    break;
                case "asc":
                    saleCountBuileder = saleCountBuileder.order(SortOrder.ASC);
                default:
            }
            nativeSearchQueryBuilder.withSort(saleCountBuileder);
        } else if(shoesTradeQuery.getSaleTimeSort()!=null&&shoesTradeQuery.getSaleTimeSort()==1){

            SortBuilder saleTimeSortBuilder = new FieldSortBuilder("saleTime");//销售时间
            saleTimeSortBuilder = saleTimeSortBuilder.order(SortOrder.DESC);
            nativeSearchQueryBuilder.withSort(saleTimeSortBuilder);

        }else{
            SortBuilder salesSortBuilder = new FieldSortBuilder("saleCount").missing(0);
            salesSortBuilder = salesSortBuilder.order(SortOrder.DESC);
            nativeSearchQueryBuilder.withSort(salesSortBuilder);
            SortBuilder putAwayTimeSortBuilder = new FieldSortBuilder("saleTime");
            putAwayTimeSortBuilder = putAwayTimeSortBuilder.order(SortOrder.DESC);
            nativeSearchQueryBuilder.withSort(putAwayTimeSortBuilder);
            ScoreSortBuilder scoreSortBuilder = SortBuilders.scoreSort();
            nativeSearchQueryBuilder.withSort(scoreSortBuilder);//es 默认评分排序
        }
    }

}
