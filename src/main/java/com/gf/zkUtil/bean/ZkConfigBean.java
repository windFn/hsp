package com.gf.zkUtil.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>Description: zk连接参数实体</p> 
 * @author ganF
 * @date 2020-10-27
 */
@Component
@PropertySource("classpath:zkConfig.properties")
public class ZkConfigBean {
	@Value("${connection_url}")
	private String url;
	@Value("${sessionTimeout}")
	private int sessionTimeout;
	@Value("${connectionTimeout}")
	private int connectionTimeout;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSessionTimeout() {
		return sessionTimeout;
	}
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	
}
