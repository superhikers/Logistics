package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.logistics.pojo.User;

public interface UserMapper {
	public int add(User user);
	
	public int delete(String u_username);
	
	public User get(int id);
	
	public User getUser(String u_username);
	
	public User loginUser(String u_username);
	
	public int update(User user);
	
	public int updateToDel(String u_username);
	
	public int updateToStart(String u_username);
	
	public List<User> list(@Param("start")int start,@Param("limit")int limit);
	
	public List<User> listAboutDel(@Param("start")int start,@Param("limit")int limit);
	
	public int getCount();
	
	public int getDelCount();
	
	public int changeUserInfo(User user);
	
}
