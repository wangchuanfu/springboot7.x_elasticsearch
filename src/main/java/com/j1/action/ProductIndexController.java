package com.j1.action;

import com.j1.pojo.EsAttribute;
import com.j1.pojo.Product;
import com.j1.service.ProductIndexService;
import com.j1.utils.EsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@RestController
public class ProductIndexController {
    @Resource
    ProductIndexService productIndexService;
    @Resource
    EsUtils esUtils;

    @Autowired
    EsAttribute esAttribute;
    //查询这是mysql
    @RequestMapping(value = {"/getProductInfo"})
    @ResponseBody
    public  String getProductInfo(){
        try {
        List<Map<String, Object>> allProduct = productIndexService.getAllProduct();
       if(allProduct.size()>0){
           esUtils.insertIntoEsByBulk(esAttribute.getIndexName(),esAttribute.getIndexType(),allProduct,"goodsId");

       }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
