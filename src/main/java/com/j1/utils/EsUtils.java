package com.j1.utils;

import com.alibaba.fastjson.JSON;
import com.j1.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
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
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean insertEsByBulk(String indexName, String type, List <?> dataList, String idFieldName) throws Exception {
        try {
            //先判断索引是否存在
            if(!existsIndex(indexName)){
                creatIndex(indexName);
            }
            BulkRequest bulkRequest = new BulkRequest();
            dataList.forEach((data) -> {
                UpdateRequest request = this.getUpdateRequest(indexName, type);
                Object id = null;
                try {
                    id = FieldUtils.readField(data, idFieldName, true);
                } catch (Exception e) {
                    e.printStackTrace ();
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
    public boolean insertIntoEsByBulk(String indexName, String type, List <Map<String, Object>> dataList, String idFieldName) throws Exception {
        String goodsId=null;
        XContentBuilder sources = null;
        try {
            //先判断索引是否存在

            BulkRequest bulkRequest = new BulkRequest();
            if (null != dataList && dataList.size() > 0) {

                for (int i = 0; i < dataList.size(); i++) {
                    IndexRequest indexRequest = this.getIndexRequest(indexName, type);
                   // UpdateRequest request = this.getUpdateRequest(indexName, type);
                    try {
                         goodsId = dataList.get(i).get("goodsId").toString();
                    }catch (Exception e){
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

                   // sources.field(DefaultIndexField.MODIFIED, new Date());
                    indexRequest.source ( JSON.toJSONString ( dataList.get(i) ), XContentType.JSON );
                    IndexResponse indexResponse = client.index ( indexRequest, RequestOptions.DEFAULT );
                    bulkRequest.add(indexRequest);
                }
            }


            BulkResponse bulk = this.client.bulk(bulkRequest, RequestOptions.DEFAULT);

        } catch (Exception e) {
            log.error("{} 批量插入失败",goodsId, e);
        }
        return true;
    }
    //更新
    private UpdateRequest getUpdateRequest(String indexName, String type) {
        UpdateRequest request = new UpdateRequest();
        request.index (indexName);
        if (StringUtils.isNotBlank(type)) {
            //7.x之后type 可以不指定,默认为_doc
          request.type(type);
        }

        return request;

    }

    //判断index是否存在
   public boolean existsIndex(String indexName) throws  Exception{
       GetIndexRequest request = new GetIndexRequest( indexName );
       boolean exists = client.indices ().exists ( request, RequestOptions.DEFAULT );
        return exists;
   }

    //创建index
    public  boolean creatIndex(String indexName) throws Exception {
        //这里只是创建索引,并没有创建mapping,
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest( indexName );
        //执行请求
       client.indices ().create ( request, RequestOptions.DEFAULT );
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

}
