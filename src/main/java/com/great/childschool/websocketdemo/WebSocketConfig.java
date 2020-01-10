package com.great.childschool.websocketdemo;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.MultipartConfigElement;

/**
 * WebSocket配置
 */
@Configuration
public class WebSocketConfig{


	/**
	 * 用途：扫描并注册所有携带@ServerEndpoint注解的实例。 @ServerEndpoint("/websocket")
	 * PS：如果使用外部容器 则无需提供ServerEndpointExporter。
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	/**
	 * 支持注入其他类
	 */
	@Bean
	public MyEndpointConfigure  newMyEndpointConfigure (){
		return new MyEndpointConfigure ();
	}



	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(1024 * 1024)); // 限制上传文件大小
		return factory.createMultipartConfig();
	}



}