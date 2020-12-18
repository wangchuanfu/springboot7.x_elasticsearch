package com.j1.dao;

import com.j1.pojo.ShoesTradeExpand;

import java.util.List;

public interface TGmGoodsMapper {
    List<String> selectBrandName();

    List<String> selectCategaryName();

    List<Integer> selectAllId();

    List<ShoesTradeExpand> selectShoesTradeMallGoodsList(List<Integer> goodsSubIds);
}
