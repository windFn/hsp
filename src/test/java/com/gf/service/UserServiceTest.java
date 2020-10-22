package com.gf.service;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gf.vo.User;

import common.BaseTest;

public class UserServiceTest extends BaseTest{
	Logger log = LoggerFactory.getLogger(UserServiceTest.class);
	@Autowired
	private UserService userService;
	@Test
	public void testFindUser() {
		User user = userService.findUser(1, "gbq");
		user.getDept();
	}
	
	@Test
	public void testSaveUser(){
		User user = new User();
		user.setAge(10000);
		user.setBirthday(new Date());
		user.setSex("1");
		int num = userService.saveUser(user);
		System.out.println(num);
	}
}
