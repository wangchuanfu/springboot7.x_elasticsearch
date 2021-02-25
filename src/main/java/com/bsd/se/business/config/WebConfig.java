package com.bsd.se.business.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sohu.tv.builder.ClientBuilder;
import lombok.extern.slf4j.Slf4j;

import org.nlpcn.commons.lang.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Harry.wu
 * @Description: 添加服务配置支持
 * @date 2015年11月6日 下午2:55:19
 */
@Slf4j
@Configuration
@ImportResource("classpath:context/context-main.xml")
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${se.elasticsearch.servers}")
    private String seServers;
    @Value("${se.elasticsearch.clusterName}")
    private String cluster;


    /**
     * 是否使用阿里云redis
     */
    @Value("${base.xxyp.aliyun.redis.open}")
    private boolean useAliyunRedis;

    @Value("${base.xxyp.aliyun.redis.info}")
    private String aliyunRedisInfo;

    @Value("${redis.cluster.appId}")
    private String appId = "10008";
    private final Integer CONNECTION_TIMEOUT = 2000;
    private final Integer READ_TIMEOUT = 1000;
    private final Integer REDIRECT = 5;

    /**
     * 已接入cachecloud,使用其带数据上报功能的客户端
     *
     * @return JedisCluster
     */


    @Bean
    public JedisCluster jedisCluster() {
//      GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(10);
        poolConfig.setMaxTotal(400);
        poolConfig.setMaxIdle(50);
        if (useAliyunRedis) {
            JSONObject jsonObject = JSON.parseObject(aliyunRedisInfo);
            String nodes = jsonObject.getString("nodes");
            String password = jsonObject.getString("password");
            Set<HostAndPort> set = new HashSet<>();
            String[] hostAndPorts = StringUtils.split(nodes + ",", ",");
            List<String> collectRedisHost = Arrays.stream(hostAndPorts).filter(e -> StringUtil.isNotBlank(e)).collect(Collectors.toList());
            for (String hostAndPort : collectRedisHost) {
                String[] node = StringUtils.split(hostAndPort, ":");
                set.add(new HostAndPort(node[0], Integer.parseInt(node[1])));
            }
            return new JedisCluster(set, CONNECTION_TIMEOUT, READ_TIMEOUT, 3, password, poolConfig);
        } else {
            String appId = this.appId;
            log.info("获取配置中心appId={}", appId);
            return ClientBuilder.redisCluster(Long.valueOf(appId))
                    .setJedisPoolConfig(poolConfig)
                    .setConnectionTimeout(CONNECTION_TIMEOUT)
                    .setSoTimeout(READ_TIMEOUT)
                    .setMaxRedirections(REDIRECT)
                    .build();
        }
    }
}
