package com.j1.action;

import com.j1.pojo.EsAttribute;
import com.j1.pojo.Product;
import com.j1.pojo.vo.OrderDetailSearchFiled;
import com.j1.service.BucketAndMetricService;
import com.j1.service.ProductIndexService;
import com.j1.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/14.
 */
@RestController
@Slf4j
public class OrderDetailSearchAction {
    @Resource
    ProductIndexService productIndexService;
    @Resource
    EsUtils esUtils;
    @Resource
    ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    EsAttribute esAttribute;
    @Resource
    BucketAndMetricService bucketAndMetricService;

    //这个方法可以写成定时任务去跑,暂时先这样,便于测试开发
    @RequestMapping(value = {"/getOrderInfoM"})
    @ResponseBody
    public String getOrderInfoN() {

        try {
            long start =System.currentTimeMillis();
            if(esUtils.existsIndex("b2b_order_detail")){
                elasticsearchTemplate.deleteIndex("b2b_order_detail");
            }
            //通过注解创建index,mapping
            if (!esUtils.existsIndex("b2b_order_detail")) {
                elasticsearchTemplate.createIndex(OrderDetailSearchFiled.class);
                elasticsearchTemplate.putMapping(OrderDetailSearchFiled.class);
            }
            List<Product> allProduct = productIndexService.getAllProduct();
            if (allProduct.size() > 0) {
                esUtils.insertIntoEsByBulk(esAttribute.getIndexName(), esAttribute.getIndexType(), allProduct, "goodsId");

            }
            long end = System.currentTimeMillis();

        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex(esAttribute.getIndexName());
            e.printStackTrace();
            return "false";
        }
        return "ok";
    }
    @RequestMapping(value = {"/getOrderInfoSearch"})
    @ResponseBody
    public String getOrderInfoSearch() {

        //参数封装测试

       // bucketAndMetricService.aggsQuery();
        return "ok";
    }

}
