package com.logistics.service;

import java.util.List;

import com.logistics.pojo.User;

public interface UserService {
	List<User> list(int start,int limit);
	
	List<User> listAboutDel(int start,int limit);
	
	User getUser(String name);
	
	int add(User user);
	
	int delete(String username);
	
	int updateToDel(String username);
	
	int updateToStart(String username);
	
	int getCount();
	
	int getDelCount();
	
	int changeUserInfo(User user);
}
