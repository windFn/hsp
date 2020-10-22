package com.gf.aspect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gf.service.UserService;
import com.gf.service.impl.NoServiceImpl;

import common.BaseTest;

public class LogAspectTest extends BaseTest{
	private final Logger log = LoggerFactory.getLogger(LogAspectTest.class);
	@Autowired
	UserService userService;
	@Autowired
	NoServiceImpl noService;
	@Test
	public void test() {
//		User user = userService.findUser(1, "gbq");
//		System.out.println(user.getName());
		noService.query();
		log.info("中文就开心健康产生的");
	}
}
