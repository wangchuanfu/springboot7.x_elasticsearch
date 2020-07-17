package com.j1.common.base;

import com.j1.common.config.EsBasicConfig;
import com.j1.common.utils.ExceptionUtils;

/**
 * Created by wangchuanfu on 20/7/16.
 */
 //默认配置信息
public abstract class DefaultParams extends EsBasicConfig {
    /**
     * 无结果
     */
    public final static String NO_RESULT = "0";

    /**
     * 整词查询
     */
    public final static String ACCURATE = "1";

    /**
     * 分词A_AND查询
     */
    public final static String SEGMENT_A_AND = "2";

    /**
     * 分词B_AND查询
     */
    public final static String SEGMENT_B_AND = "3";

    /**
     * 分词OR查询
     */
    public final static String SEGMENT_AB_OR = "4";

    //---------------------------------搜索改版 2016-08-02 by tianqi----------START-----------------------

    /**
     * 分词C_AND查询
     */
    public final static String SEGMENT_C_AND = "10";

    /**
     * 分词C_OR查询
     */
    public final static String SEGMENT_C_OR = "11";

    //---------------------------------搜索改版 2016-08-02 by tianqi-----------END----------------------


    /**
     * String类型为空时
     */
    public final static String STRING_NULL_VALUE = null;

    /**
     * 售价数字为空时=-1
     */
    public static int INTEGER_EMPTY_VALUE = -1;

    /**
     * 默认排序字段
     */
    public static String DEFAULT_ORDER_BY = "_score";

    /**
     * 默认id名字
     */
    public static String DEFAULT_ID_FIELD_NAME = "_id";

    /**
     * 搜索列表页，默认目录匹配度达到该值时，以目录为筛选条件
     */
    public static Float DEFAULT_CATALOG_MATCH_DEGREE = 0.3f;

    static
    {
        try
        {
            INTEGER_EMPTY_VALUE = setObjectNotNull(INTEGER_EMPTY_VALUE, getIntProp("esclient.default.integer_empty_value"));
            DEFAULT_ORDER_BY = setObjectNotNull(DEFAULT_ORDER_BY, getStrProp("esclient.default.default_order_by"));
            DEFAULT_CATALOG_MATCH_DEGREE = getFloatProp("esclient.default.catalog_match_degree");
        }
        catch (Exception e)
        {
            CONFIG_LOG.error(ExceptionUtils.configErrorFormat1(DefaultParams.class), e);
        }
    }
}
