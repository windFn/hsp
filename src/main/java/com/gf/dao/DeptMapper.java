package com.gf.dao;

import org.springframework.stereotype.Repository;

import com.gf.vo.Dept;

@Repository
public interface DeptMapper {
	public Dept selectByIdForResultMap(int id);
	
	public Dept findoneMany(int id);
	
	public Dept findoneManyResult(int id);
}
