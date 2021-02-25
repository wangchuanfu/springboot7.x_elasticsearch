package com.j1.service.impl;

import com.j1.dao.OrderInfoMapper;
import com.j1.pojo.OrderItem;
import com.j1.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangchuanfu on 20/8/12.
 */

@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderItem> getOrderItemById() {


        List<OrderItem> webOrderInfoDetailList = orderInfoMapper.getWebOrderInfoDetailList();

        return webOrderInfoDetailList;
    }
}
