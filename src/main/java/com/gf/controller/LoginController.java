package com.gf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sys")
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public String login(@RequestParam("userName")String userName, 
			@RequestParam("password")String password){
		log.info("userName:"+userName);
		log.info("password:"+password);
		return "loginSuccess";
	}
}
