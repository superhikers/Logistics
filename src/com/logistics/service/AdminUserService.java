package com.logistics.service;

import java.util.List;

import com.logistics.pojo.AdminUser;
import com.logistics.pojo.User;

public interface AdminUserService {
	AdminUser getUser(String name);
	List<AdminUser> getAllAdmin(int start,int limit);
	List<AdminUser> getAllDelAdmin(int start,int limit);
	int add(AdminUser adminUser);
	int updateToDel(String a_username);
	int updateToBack(String a_username);
	int getDelCount();
	int getCount();
}
