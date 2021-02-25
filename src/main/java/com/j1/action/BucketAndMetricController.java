package com.j1.action;

import com.j1.service.BucketAndMetricService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangchuanfu on 20/7/22.
 */
@RestController
public class BucketAndMetricController {
    @Resource
    BucketAndMetricService bucketAndMetricService;

    @RequestMapping(value = "/dateHistogramAggregation")
    @ResponseBody
    public String dateHistogramAggregation() {
        bucketAndMetricService.dateHistogramAggregation();
        return "ok";
    }

}
