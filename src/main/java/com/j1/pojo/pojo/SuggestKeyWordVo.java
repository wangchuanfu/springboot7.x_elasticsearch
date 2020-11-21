package com.j1.pojo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.completion.Completion;

import java.util.Date;


/**
 * Created by wangchuanfu on 20/8/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "suggest_keyword", type = "_doc", shards = 5, replicas = 1)

public class SuggestKeyWordVo {
    /**
     * 搜索提示词索引
     */



    //搜索提示
    @CompletionField(searchAnalyzer="standard",analyzer = "standard")
    private Completion operationName;

    //时间类型
    @Field(type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 商品id
     */
    private String id;

    /**
     * 关键词，一元查找
     */
    @Field(type = FieldType.Text, index = true, analyzer = "standard", searchAnalyzer = "standard", store = true)
    private String keywordStand;

    /**
     * 关键词，中文前缀查找
     */
    @Field(type =  FieldType.Text, index = true, analyzer = "standard", searchAnalyzer = "standard", store = false)
    private String keywordSuffix;

    /**
     * 关键词，拼音前缀查找
     */
    @Field(type = FieldType.Text,analyzer = "pinyin",searchAnalyzer = "pinyin")
    private String keywordPinyin;

    /**
     * 关键词属于何分类下
     */
    @Field(type =  FieldType.Text, index = true, analyzer = "standard", searchAnalyzer = "standard", store = true)
    private String catalogId;

    /**
     * 关键词相关商品数（含误差）
     */
    @Field(type =  FieldType.Keyword, index = false, store = true)
    private String amount;



    /**
     * 关键词，拼音前缀查找
     */
    @CompletionField(searchAnalyzer="standard",analyzer = "standard")
    private Completion searchName;
    //搜索提示
    @CompletionField(searchAnalyzer="standard",analyzer = "standard")
    private Completion promptName;


}
