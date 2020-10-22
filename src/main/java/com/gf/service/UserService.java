package com.gf.service;

import com.gf.vo.User;
 
public interface UserService {
	public User findUser(int id, String name);
	
	public int saveUser(User user);
}
