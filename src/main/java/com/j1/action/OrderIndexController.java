package com.j1.action;

import com.j1.pojo.EsAttribute;
import com.j1.pojo.OrderItem;
import com.j1.pojo.vo.OrderItemVo;
import com.j1.service.OrderItemService;
import com.j1.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangchuanfu on 20/8/12.
 */
@RestController
@Slf4j
public class OrderIndexController {
    @Resource
    ElasticsearchRestTemplate elasticsearchTemplate;
    @Resource
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;
    @Resource
    EsUtils esUtils;
    @Autowired
    EsAttribute esAttribute;
    @Autowired
    OrderItemService  orderItemService;
    /**
     * 健一网 订单搜索索引创建
     */

    private static  final  String orderIndex="order_info";
    //创建订单详情index,mapping
    //基于注解创建index,mapping
    @RequestMapping("/orderInfoIndex")
    public String produceVo(@RequestParam(value = "userName") String userName) {
        try {
            /**
             *  */

            //创建之前先判断索引是否存在,
            if(esUtils.existsIndex(orderIndex)){
                elasticsearchTemplate.deleteIndex(orderIndex);
            }
            if(!esUtils.existsIndex(orderIndex) ){
                elasticsearchTemplate.createIndex(OrderItemVo.class);
                elasticsearchTemplate.putMapping(OrderItemVo.class);

            }

            List<OrderItem> orderItemList = orderItemService.getOrderItemById();
            //同步数据至es指定索引中
            if (orderItemList.size() > 0) {
               // esUtils.insertIntoOrderByBulk(orderIndex, esAttribute.getIndexType(), orderItemList, "orderId");

            }

        } catch (Exception e) {
            //失败的话删除索引
            elasticsearchTemplate.deleteIndex(orderIndex);
            e.printStackTrace();
            log.error("创建索引失败", e.getMessage());
        }
        return userName;
    }
}
