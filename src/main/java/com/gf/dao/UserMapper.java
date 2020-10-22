package com.gf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gf.vo.User;
@Repository
public interface UserMapper {
	public User selectByIdForResultType(int id);
	
	public User selectByIdForResultMap(int id);
	
	public int insertUser(User user);
	
	public int deleteUserByName(String name);
	
	public int updateUser(User user);
	
	public int insertUserBatch(List<User> users);
	
	public User findOneOne(@Param("id")int id, @Param("name")String name);
	
	public List<User> findUserByDeptId(int deptId);
	
	public User disCardFind(int id);
}
