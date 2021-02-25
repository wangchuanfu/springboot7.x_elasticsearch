//package com.j1;
//
//import com.bsd.reg.rw.spring.serverimpl.ServerRegZkListener;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.util.StringUtils;
//
//@SpringBootApplication
//@MapperScan("com.j1.dao")
//public class ElasticsearchApplication   {
//	private static Logger logger = LoggerFactory.getLogger(ElasticsearchApplication.class);
//	private   int port=9055;
//	private String contextPath;
//	public static void main(String[] args) {
//		SpringApplication.run(ElasticsearchApplication.class, args);
//	}
//
//
//	//@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		ServerRegZkListener regListener = applicationContext.getBean(ServerRegZkListener.class);
//		port = regListener.getPort();
//		contextPath = regListener.getContextRoot();
//	}
//	/**
//	 * 设置服务端口
//	 */
//	@SuppressWarnings("rawtypes")
//	@Bean
//	public WebServerFactoryCustomizer webServerFactoryCustomizer(){
//		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
//			@Override
//			public void customize(ConfigurableServletWebServerFactory factory) {
//				factory.setPort(port);
//				if(StringUtils.hasText(contextPath)){
//					factory.setContextPath(contextPath);
//				}
//			}
//		};
//	}
//
//}
