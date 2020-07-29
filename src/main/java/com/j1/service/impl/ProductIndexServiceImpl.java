package com.j1.service.impl;

import com.j1.dao.ProductIndexMapper;
import com.j1.pojo.Product;
import com.j1.service.ProductIndexService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@Service
public class ProductIndexServiceImpl implements ProductIndexService {
    @Resource
    ProductIndexMapper productIndexMapper;
    @Override
    @ResponseBody
    public List<Product>  getAllProduct() {
        List<Product>allProduct = productIndexMapper.getAllProduct();

        return allProduct;
    }
}
