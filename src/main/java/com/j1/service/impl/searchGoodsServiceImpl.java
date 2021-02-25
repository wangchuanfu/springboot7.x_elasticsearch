package com.j1.service.impl;

import com.j1.pojo.vo.WebProductVo;
import com.j1.service.AbstractEsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by wangchuanfu on 20/7/31.
 */
@Slf4j
@Service
public class searchGoodsServiceImpl extends AbstractEsService<WebProductVo> {

    public void prin() {
        log.error(" =====init AbstractEsService===");
    }

}
