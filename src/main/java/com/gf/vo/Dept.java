package com.gf.vo;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable{
	private static final long serialVersionUID = 3949840592069992018L;
	private Integer id;
	private String deptName;
	private List<User> users;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
