package com.gf.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 3431708893352526373L;
	
	private Integer id;
	private Integer age;
	private String name;
	private Date birthday;
	private String sex;
	private Dept dept;
	private List<Role> roles;
	public User(){}
	public User(Integer id, Integer age, String name, Date birthday) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.birthday = birthday;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
