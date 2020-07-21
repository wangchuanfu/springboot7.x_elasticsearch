package com.j1.action;

import com.j1.pojo.EsAttribute;
import com.j1.pojo.Product;
import com.j1.pojo.WebProductVo;
import com.j1.service.ProductIndexService;
import com.j1.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@RestController
@Slf4j
public class ProductIndexController {
    @Resource
    ProductIndexService productIndexService;
    @Resource
    EsUtils esUtils;
    @Resource
    ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    EsAttribute esAttribute;

    //这个方法可以写成定时任务去跑,暂时先这样,便于测试开发
    @RequestMapping(value = {"/getProductInfo"})
    @ResponseBody
    public String getProductInfo() {
        log.error("【" + DateUtils.formatDate(new Date()) + "】" + "WebProduct推送数据至ES 任务更新开始=...");
        String msg = "";
        try {
            long start =System.currentTimeMillis();
            //通过注解创建index,mapping
            if (!esUtils.existsIndex(esAttribute.getIndexName())) {
                elasticsearchTemplate.createIndex(WebProductVo.class);
                elasticsearchTemplate.putMapping(WebProductVo.class);
            }
            List<Map<String, Object>> allProduct = productIndexService.getAllProduct();
            if (allProduct.size() > 0) {
                esUtils.insertIntoEsByBulk(esAttribute.getIndexName(), esAttribute.getIndexType(), allProduct, "goodsId");

            }
            long end = System.currentTimeMillis();
            msg = "WebProduct 推送数据至ES 任务更新结束,成功更新数据量-->> " + "商品总数量:" + allProduct.size() + ",共用时间 -->> " + (end - start) + " 毫秒";

        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex(esAttribute.getIndexName());
            e.printStackTrace();
            return "false";
        }
        log.error("【" + DateUtils.formatDate(new Date()) + "】" + msg);
        return "ok";
    }

}
