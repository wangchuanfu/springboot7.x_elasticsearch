package com.j1.action;

import com.j1.service.impl.HashFunctionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangchuanfu on 20/8/12.
 */
@RestController
public class HashOrderItemIndexController {
    @Autowired
    HashFunctionUtils hashFunctionUtils;

    //根据会员id,取模,查询出对应的订单商品数据,取模后动态的插入索引中
    public  void  InitHashOrderItemIndex(String memberId){
        //hash 算法取模
        Integer hash = hashFunctionUtils.hash(memberId);

        //创建index
        //创建mapping


    }
    }
