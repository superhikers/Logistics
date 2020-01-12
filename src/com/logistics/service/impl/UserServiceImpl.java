package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.mapper.UserMapper;
import com.logistics.pojo.User;
import com.logistics.service.UserService;

//注解为一个service
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;
	
	public List<User> list(int start,int limit) {
		return userMapper.list(start,limit);
	}

	@Override
	public User getUser(String name) {
		return userMapper.getUser(name);
	}

	@Override
	public int add(User user) {
		return userMapper.add(user);
	}

	@Override
	public int delete(String username) {
		return userMapper.delete(username);
	}

	@Override
	public int updateToDel(String username) {
		return userMapper.updateToDel(username);
		
	}

	@Override
	public List<User> listAboutDel(int start,int limit) {
		return userMapper.listAboutDel(start,limit);
	}

	@Override
	public int updateToStart(String username) {
		return userMapper.updateToStart(username);
	}

	@Override
	public int getCount() {
		return userMapper.getCount();
	}

	@Override
	public int getDelCount() {
		return userMapper.getDelCount();
	}

	@Override
	public int changeUserInfo(User user) {
		return userMapper.changeUserInfo(user);
	}


}
