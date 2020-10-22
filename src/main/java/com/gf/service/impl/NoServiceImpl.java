package com.gf.service.impl;

import org.springframework.stereotype.Service;

import com.gf.annotation.Log;

@Log
@Service
public class NoServiceImpl {
	public void query(){
		System.out.println("query");
	}
}
