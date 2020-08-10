package com.j1.action;

import com.j1.pojo.*;
import com.j1.pojo.Product;
import com.j1.pojo.vo.WebProductVo;
import com.j1.utils.EsUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangchuanfu on 20/7/14.
 */
@RestController
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    ElasticsearchRestTemplate elasticsearchTemplate;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsUtils esUtils;
    @Autowired
    EsAttribute esAttribute;


    @RequestMapping("/testitem")
    public String testEsItem(@RequestParam(value = "userName") String userName) {
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
        return userName;
    }

    @RequestMapping("/testgoods")
    public String testEsGoods(@RequestParam(value = "userName") String userName) {
        elasticsearchTemplate.createIndex(Product.class);
        elasticsearchTemplate.putMapping(Product.class);

        return userName;
    }

    //基于注解创建index,mapping
    @RequestMapping("/produceVo")
    public String produceVo(@RequestParam(value = "userName") String userName) {
        try {
            //创建之前先判断索引是否存在,
            if (!esUtils.existsIndex(esAttribute.getIndexName())) {
                elasticsearchTemplate.createIndex(WebProductVo.class);
                elasticsearchTemplate.putMapping(WebProductVo.class);
            }
        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex("hy_web_index");
            e.printStackTrace();
            logger.error("创建索引失败", e.getMessage());
        }
        return userName;
    }

    //批量导入数据
    @RequestMapping("/bulk")
    public String bulk(@RequestParam(value = "userName") String userName) {

        return userName;
    }



}
