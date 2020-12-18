package com.j1.xiaoxiang.controller.wireless;

import com.j1.xiaoxiang.entity.dto.PageResultDto;
import com.j1.xiaoxiang.entity.dto.ResultBean;
import com.j1.xiaoxiang.entity.dto.ShoesTradeResultDto;
import com.j1.xiaoxiang.query.ShoesTradeQuery;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ShoesTrade/shoes")
@Api(tags = {"潮鞋交易商城搜索"})
public interface ShoesTradeControllerApi {

    /**
     * 潮鞋交易搜索接口
     *
     * @param shoesTradeQuery 请求参数
     * @return 搜索结果
     * @author wangpw
     */
    @ApiOperation(value = "潮鞋交易搜索接口", notes = "潮鞋交易搜索接口")
    @ApiParam(name = "methods", value = "潮鞋交易搜索接口", required = true, type = "String")
    @ApiImplicitParams({@ApiImplicitParam(name = "request", value = "请求参数", dataType = "ShoesTradeQuery")})
    @PostMapping("/search.json")
    ResultBean<PageResultDto<ShoesTradeResultDto>> shoesTradeSearch(ShoesTradeQuery shoesTradeQuery);

    /**
     * 潮鞋交易全量同步接口
     *
     * @return 同步结果
     * @author wangpw
     */
    @ApiOperation(value = "潮鞋交易全量同步接口", notes = "潮鞋交易全量同步接口")
    @ApiParam(name = "methods", value = "潮鞋交易搜索接口", required = true, type = "String")
    @ApiImplicitParams({@ApiImplicitParam(name = "request", value = "请求参数", dataType = "")})
    @PostMapping("/fullSync.json")
    ResultBean<String> fullSync();

}
