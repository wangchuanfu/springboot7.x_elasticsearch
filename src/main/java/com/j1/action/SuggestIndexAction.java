package com.j1.action;

import com.j1.pojo.EsAttribute;
import com.j1.pojo.SuggestPrompt;
import com.j1.pojo.vo.SuggestKeyWordVo;
import com.j1.service.SuggestIndexService;
import com.j1.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/3.
 */

@RestController
@Slf4j
//创建SuggestIndex
public class SuggestIndexAction {
    @Resource
    ElasticsearchRestTemplate elasticsearchTemplate;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsUtils esUtils;
    @Autowired
    EsAttribute esAttribute;

    @Autowired
    SuggestIndexService suggestIndexService;

    //基于注解创建index,mapping
    @RequestMapping("/suggestVo")
    public String produceVo(@RequestParam(value = "userName") String userName) {
        try {
            //创建之前先判断索引是否存在,
            if(esUtils.existsIndex(esAttribute.getSuggestIndexName())){
                elasticsearchTemplate.deleteIndex(esAttribute.getSuggestIndexName());
            }
            if(!esUtils.existsIndex(esAttribute.getSuggestIndexName())) {
                elasticsearchTemplate.createIndex(SuggestKeyWordVo.class);
                elasticsearchTemplate.putMapping(SuggestKeyWordVo.class);

            }
            List<SuggestPrompt> allSuggestProduct = suggestIndexService.getAllSuggestProduct();
            //同步数据至es指定索引中
            if (allSuggestProduct.size() > 0) {
                esUtils.insertIntoSuggestByBulk(esAttribute.getSuggestIndexName(), esAttribute.getIndexType(), allSuggestProduct, "goodsId");

            }

        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex("suggest_keyword");
            e.printStackTrace();
            log.error("创建索引失败", e.getMessage());
        }
        return userName;
    }
}
