package com.j1.xiaoxiang.service;

import com.j1.xiaoxiang.entity.dto.PageResultDto;
import com.j1.xiaoxiang.entity.dto.ShoesTradeResultDto;
import com.j1.xiaoxiang.query.ShoesTradeQuery;

import java.util.List;
import java.util.Set;

public interface ShoesTradeMallService {
    PageResultDto<ShoesTradeResultDto> search(ShoesTradeQuery shoesTradeQuery);

    void deleteGoodsNotInIds(Set<Integer> allIdSet);

    List<Integer> batchImportShoesTradeGoodsData(List<Integer> allId);
}
