package com.j1.utils;

import com.alibaba.fastjson.JSON;
import com.j1.pojo.Product;
import com.j1.pojo.ProductAttrs;
import com.j1.pojo.SuggestPrompt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangchuanfu on 20/7/10.
 */
@Slf4j
@Component
public class EsUtils {


    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;

    @Resource
    HashMapToBeanUtils hashMapToBeanUtils;

    //批量插入数据到es中
    public boolean insertEsByBulk(String indexName, String type, List<?> dataList, String idFieldName) throws Exception {
        try {
            //先判断索引是否存在
            if (!existsIndex(indexName)) {
                creatIndex(indexName);
            }
            BulkRequest bulkRequest = new BulkRequest();
            dataList.forEach((data) -> {
                UpdateRequest request = this.getUpdateRequest(indexName, type);
                Object id = null;
                try {
                    id = FieldUtils.readField(data, idFieldName, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("{} 获取id失败", id, e);
                }
                request.id(String.valueOf(id));
                request.doc(JSON.toJSONString(data), XContentType.JSON);
                request.upsert(JSON.toJSONString(data), XContentType.JSON);
                bulkRequest.add(request);
            });
            this.client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("{} 批量插入失败", indexName, e);
        }
        return true;
    }

    //批量插入数据到es中
    public boolean insertIntoEsByBulk(String indexName, String type, List<Product> dataList, String idFieldName) throws Exception {
        String goodsId = null;
        XContentBuilder sources = null;
        try {
            //先判断索引是否存在

            BulkRequest bulkRequest = new BulkRequest();
            if (null != dataList && dataList.size() > 0) {

                for (int i = 0; i < dataList.size(); i++) {
                    IndexRequest indexRequest = this.getIndexRequest(indexName, type);
                    // UpdateRequest request = this.getUpdateRequest(indexName, type);
                    try {
                        goodsId = dataList.get(i).getGoodsId().toString();
                    } catch (Exception e) {
                        log.error("{} 获取id失败", goodsId, e);
                    }


                    // request.id(String.valueOf(goodsId));
                    //request.doc(JSON.toJSONString(dataList.get(i)), XContentType.JSON);
                    // request.upsert(JSON.toJSONString(dataList.get(i)), XContentType.JSON);
                    //封装成map
                    // indexRequest.id(String.valueOf(goodsId));
                    //  indexRequest.source((Map)dataList.get(i));

                    //封装成json 数据
                    indexRequest.id(String.valueOf(goodsId));
                    //拼接attrs
                    String attrs = loadAttrs(dataList.get(i));
                    // sources.field(DefaultIndexField.MODIFIED, new Date());
                    indexRequest.source(JSON.toJSONString(dataList.get(i)), XContentType.JSON);
                    IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
                    bulkRequest.add(indexRequest);
                }
            }
            long start = System.currentTimeMillis();

            BulkResponse bulk = this.client.bulk(bulkRequest, RequestOptions.DEFAULT);
            long end = System.currentTimeMillis();
            log.error("bulk共用时间 -->> " + (end - start) + " 毫秒");

        } catch (Exception e) {
            log.error("{} 批量插入失败", goodsId, e);
        }
        return true;
    }

    private String loadAttrs(Product product) {
        List<ProductAttrs> attrs = product.getAttrs();
        StringBuffer sb = new StringBuffer();
        for (ProductAttrs attr : attrs) {
            sb.append(attr.getAttrId().toString() + "_" + attr.getAttrCode().toString() + ",");
        }
        return sb.toString();

    }

    //更新
    private UpdateRequest getUpdateRequest(String indexName, String type) {
        UpdateRequest request = new UpdateRequest();
        request.index(indexName);
        if (StringUtils.isNotBlank(type)) {
            //7.x之后type 可以不指定,默认为_doc
            request.type(type);
        }

        return request;

    }

    //判断index是否存在
    public boolean existsIndex(String indexName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(indexName);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    //创建index
    public boolean creatIndex(String indexName) throws Exception {
        //这里只是创建索引,并没有创建mapping,
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        //执行请求
        client.indices().create(request, RequestOptions.DEFAULT);
        return true;
    }

    //批量插入创建搜索引
    private IndexRequest getIndexRequest(String indexName, String type) {
        IndexRequest indexRequest = new IndexRequest(indexName);
        if (StringUtils.isNotBlank(type)) {
            indexRequest.type(type);
        }
        return indexRequest;
    }

    public void insertIntoSuggestByBulk(String suggestIndexName, String indexType, List<SuggestPrompt> allSuggestProduct, String idFieldName) {

        //同步数据
        String promptId = null;

        try {
            //先判断索引是否存在

            BulkRequest bulkRequest = new BulkRequest();
            if (null != allSuggestProduct && allSuggestProduct.size() > 0) {

                for (int i = 0; i < allSuggestProduct.size(); i++) {
                    IndexRequest indexRequest = this.getIndexRequest(suggestIndexName, indexType);
                    try {
                        promptId = allSuggestProduct.get(i).getPromptId().toString();
                    } catch (Exception e) {
                        log.error("{} 获取id失败", promptId, e);
                    }


                    //封装成json 数据
                    indexRequest.id(String.valueOf(promptId));

                    // sources.field(DefaultIndexField.MODIFIED, new Date());
                    indexRequest.source(JSON.toJSONString(allSuggestProduct.get(i)), XContentType.JSON);
                    IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
                    bulkRequest.add(indexRequest);
                }
            }
            long start = System.currentTimeMillis();

            BulkResponse bulk = this.client.bulk(bulkRequest, RequestOptions.DEFAULT);
            long end = System.currentTimeMillis();
            log.error("bulk共用时间 -->> " + (end - start) + " 毫秒");

        } catch (Exception e) {
            log.error("{} 批量插入失败", promptId, e);
        }
    }
}
