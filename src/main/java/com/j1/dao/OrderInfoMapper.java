package com.j1.dao;

import com.j1.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchuanfu on 20/8/12.
 */
@Mapper
@Repository
public interface OrderInfoMapper {

    //订单详情
    List<OrderItem> getWebOrderInfoDetailList();
}
