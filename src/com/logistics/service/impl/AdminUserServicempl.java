package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.mapper.AdminUserMapper;
import com.logistics.pojo.AdminUser;
import com.logistics.service.AdminUserService;

@Service
public class AdminUserServicempl implements AdminUserService {
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	@Override
	public AdminUser getUser(String name) {
		return adminUserMapper.getUser(name);
	}

	@Override
	public List<AdminUser> getAllAdmin(int start,int limit) {
		return adminUserMapper.getAllAdmin(start, limit);
	}

	@Override
	public int add(AdminUser adminUser) {
		return adminUserMapper.add(adminUser);
	}

	@Override
	public int updateToDel(String a_username) {
		return adminUserMapper.updateToDel(a_username);
	}

	@Override
	public List<AdminUser> getAllDelAdmin(int start,int limit) {
		return adminUserMapper.getAllDelAdmin(start,limit);
	}

	@Override
	public int updateToBack(String a_username) {
		return adminUserMapper.updateToBack(a_username);
	}

	@Override
	public int getDelCount() {
		return adminUserMapper.getDelCount();
	}

	@Override
	public int getCount() {
		return adminUserMapper.getCount();
	}


}
