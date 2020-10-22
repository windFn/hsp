package com.gf.common.vo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RestCallItem {
	private List<String> productServices;
	
	public List<String> getProductServices() {
		return productServices;
	}

	public void setProductServices(List<String> productServices) {
		this.productServices = productServices;
	}
}
