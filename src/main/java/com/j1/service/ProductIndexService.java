package com.j1.service;

import com.j1.pojo.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/17.
 */
public interface ProductIndexService {
    //查询满足符合条件的商品
    List<Product>  getAllProduct();
}
