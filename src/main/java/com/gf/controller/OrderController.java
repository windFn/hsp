package com.gf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gf.common.util.RandomChoseUtil;
import com.gf.common.vo.RestCallItem;
import com.gf.common.vo.rest.Product;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	RestCallItem items;
	@RequestMapping("/getOrder")
	public Product getOrder(){
		List<String> productServices = items.getProductServices();
		String url = RandomChoseUtil.choseRandom(productServices);
		Product postForObject = restTemplate.postForObject("http://"+url+"/hsp/product/getProduct", null, Product.class);
		return postForObject;
	}
}
