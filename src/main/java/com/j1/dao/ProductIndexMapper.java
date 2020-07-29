package com.j1.dao;

import com.j1.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/17.
 */
@Mapper
@Repository
public interface ProductIndexMapper {
    List<Map<String, Object>> getAllProduct();

    List getAttrListByProduct(Integer productId);
}
