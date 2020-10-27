package com.gf.zkUtil.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 远程接口在zk中注册服务的节点</p> 
 * @author ganF
 * @date 2020-10-27
 */
@Component
@PropertySource("classpath:zkConfig.properties")
public class ZkPath {
	@Value("${product_path}")
	private String productPath;

	public String getProductPath() {
		return productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}
	
}
