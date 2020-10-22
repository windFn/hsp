package com.gf.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gf.vo.User;

@Controller
@SessionAttributes({ "user" })
public class TestController {
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("user", "ganf");
		return "testSuccess";
	}

	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", "ganf");
		modelAndView.setViewName("testSuccess");
		return modelAndView;
	}

	@RequestMapping("/testPathVariable/{name}/{age}")
	public ModelAndView testPathVariable(@PathVariable("name") String name1,
			@PathVariable("age") Integer age) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("testSuccess");
		System.out.println(name1);
		System.out.println(age);  
		return modelAndView;
	}
	
	@RequestMapping("/testPojo")
	public ModelAndView testPojo(User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("testSuccess");
		System.out.println(user);
		return modelAndView;
	} 
}
