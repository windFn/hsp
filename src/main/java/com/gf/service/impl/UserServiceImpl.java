package com.gf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gf.annotation.Log;
import com.gf.dao.UserMapper;
import com.gf.service.UserService;
import com.gf.vo.User;
@Log
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User findUser(int id, String name) {
		return userMapper.findOneOne(id, name);
	}

	@Override
	public int saveUser(User user) {
		return userMapper.insertUser(user);
	}
}
