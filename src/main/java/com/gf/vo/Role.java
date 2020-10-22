package com.gf.vo;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{
	private static final long serialVersionUID = -8259694063824012984L;
	private Integer id;
	private String roleName;
	private List<User> users;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
