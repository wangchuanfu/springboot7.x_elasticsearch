package com.j1.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public enum  SearchType {

    /**
     * 无结果
     */
    NO_RESULT("0"),
    /**
     * 整词查询
     */
    ACCURATE("1"),
    /**
     * 分词A_AND查询
     */
    SEGMENT_A_AND("2"),
    /**
     * 分词B_AND查询
     */
    SEGMENT_B_AND("3"),
    /**
     * 分词OR查询
     */
    SEGMENT_AB_OR("4"),
    /**
     * 错别字纠正查询
     */
    CORRECTED_TYPOS("5"),
    /**
     * 分词C_AND查询
     */
    SEGMENT_C_AND("10"),
    /**
     * 分词C_OR查询
     */
    SEGMENT_C_OR("11");

    private String i;

    private final static Map<String, SearchType> codeMap = new HashMap<String, SearchType>();

    private final static Map<SearchType, String[]> fieldMap = new HashMap<SearchType, String[]>();

    private final static Map<SearchType, Float[]> fieldBoostMap = new HashMap<SearchType, Float[]>();

    static
    {
        for (SearchType searchType : SearchType.values())
        {
            codeMap.put(searchType.getCode(), searchType);
        }

        // 初始化查询类型对应的查询字段
        String[] accurateFields = {
                NewProductFieldEnum.productCommonName.name(), // 商品通用名
                NewProductFieldEnum.productName1.name(),// 产品名称
                NewProductFieldEnum.productName.name(),// 产品名称
                NewProductFieldEnum.productChnNo.name(),// 拼音码
                NewProductFieldEnum.proCatalogName.name(),// 分类
                NewProductFieldEnum.cataLogListName.name(),// 分类
                NewProductFieldEnum.productBrandName.name(),// 品牌
                NewProductFieldEnum.productBrandName1.name() // 品牌
        };
        String[] segmentAAndFields = {
                NewProductFieldEnum.productCommonName.name(), // 商品通用名
                NewProductFieldEnum.productName1.name(),// 产品名称
                NewProductFieldEnum.productName.name(),// 产品名称
                NewProductFieldEnum.productChnNo.name(),// 拼音码
                NewProductFieldEnum.proCatalogName.name(),// 分类
                NewProductFieldEnum.cataLogListName.name(),// 分类
                NewProductFieldEnum.productBrandName.name(),// 品牌
                NewProductFieldEnum.productBrandName1.name() // 品牌
        };
        String[] segmentBAndFields = {
                NewProductFieldEnum.drugTreatment.name(),// 主治功能
                NewProductFieldEnum.productKeyword.name() // 关键字
        };
        String[] segmentABOrFields = {
                NewProductFieldEnum.productCommonName.name(), // 商品通用名
                NewProductFieldEnum.productName1.name(),// 产品名称
                NewProductFieldEnum.productName.name(),// 产品名称
                NewProductFieldEnum.productChnNo.name(),// 拼音码
                NewProductFieldEnum.proCatalogName.name(),// 分类
                NewProductFieldEnum.cataLogListName.name(),// 分类
                NewProductFieldEnum.productBrandName.name(),// 品牌
                NewProductFieldEnum.productBrandName1.name() // 品牌
        };
        String[] correctedTyposFields = {
                NewProductFieldEnum.productCommonName.name(), // 商品通用名
                NewProductFieldEnum.productName1.name(),// 产品名称
                NewProductFieldEnum.productName.name(),// 产品名称
                NewProductFieldEnum.productChnNo.name(),// 拼音码
                NewProductFieldEnum.proCatalogName.name(),// 分类
                NewProductFieldEnum.cataLogListName.name(),// 分类
                NewProductFieldEnum.productBrandName.name(),// 品牌
                NewProductFieldEnum.productBrandName1.name() // 品牌
        };
        fieldMap.put(SearchType.ACCURATE, accurateFields);
        fieldMap.put(SearchType.SEGMENT_A_AND, segmentAAndFields);
        fieldMap.put(SearchType.SEGMENT_B_AND, segmentBAndFields);
        fieldMap.put(SearchType.SEGMENT_AB_OR, segmentABOrFields);
        fieldMap.put(SearchType.CORRECTED_TYPOS, correctedTyposFields);
        // 初始化查询类型对应的查询字段boost
        Float[] accurateFieldsBoost = { 4.0f,// 商品通用名
                4.0f,// 产品名称
                4.0f,// 产品名称
                1.0f,// 拼音码
                4.0f,// 分类
                4.0f,// 分类名字列表
                4.0f,// 品牌
                4.0f // 品牌
        };
        Float[] segmentAAndFieldsBoost = { 4.0f,// 商品通用名
                4.0f,// 产品名称
                4.0f,// 产品名称
                1.0f,// 拼音码
                4.0f,// 分类
                4.0f,// 分类名字列表
                4.0f,// 品牌
                4.0f // 品牌
        };
        Float[] segmentBAndFieldsBoost = { 1.0f,// 主治功能
                1.0f // 关键字
        };
        Float[] segmentABOrFieldsBoost = { 4.0f,// 商品通用名
                4.0f,// 产品名称
                4.0f,// 产品名称
                1.0f,// 拼音码
                4.0f,// 分类
                4.0f,// 分类名字列表
                4.0f,// 品牌
                4.0f // 品牌
        };
        Float[] correctedTyposFieldsBoost = { 4.0f,// 商品通用名
                4.0f,// 产品名称
                4.0f,// 产品名称
                1.0f,// 拼音码
                4.0f,// 分类
                4.0f,// 分类名字列表
                4.0f,// 品牌
                4.0f // 品牌
        };
        fieldBoostMap.put(SearchType.ACCURATE, accurateFieldsBoost);
        fieldBoostMap.put(SearchType.SEGMENT_A_AND, segmentAAndFieldsBoost);
        fieldBoostMap.put(SearchType.SEGMENT_B_AND, segmentBAndFieldsBoost);
        fieldBoostMap.put(SearchType.SEGMENT_AB_OR, segmentABOrFieldsBoost);
        fieldBoostMap
                .put(SearchType.CORRECTED_TYPOS, correctedTyposFieldsBoost);
    }

    private SearchType(String n)
    {
        this.i = n;
    }

    public String getCode()
    {
        return this.i;
    }

    public static SearchType resolve(String i)
    {
        try
        {
            return SearchType.valueOf(i);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static String getTypeName(String type)
    {
        try
        {
            return SearchType.resolve(type).getCode();
        }
        catch (Exception e)
        {
            return "";
        }
    }

    /**
     * 通过查询编码得到查询枚举
     * @param code
     * @return
     */
    public static SearchType codeOf(String code)
    {
        SearchType searchType = codeMap.get(code);
        if (searchType != null)
            return searchType;
        else
            return SearchType.ACCURATE;
    }

    /**
     * 通过查询枚举获得要查询的字段
     * @param searchType
     * @return
     */
    public static String[] getFields(SearchType searchType)
    {
        return fieldMap.get(searchType);
    }

    /**
     * 通过查询枚举获得要查询的字段boost
     * @param searchType
     * @return
     */
    public static Float[] getFieldsBoost(SearchType searchType)
    {
        return fieldBoostMap.get(searchType);
    }

    public static void main(String[] args)
    {
        System.out.println(SearchType.codeOf("-1"));
    }

}
