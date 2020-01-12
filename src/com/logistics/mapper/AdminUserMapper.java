package com.logistics.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.logistics.pojo.AdminUser;

public interface AdminUserMapper {
	public AdminUser getUser(String username);
	public List<AdminUser> getAllAdmin(@Param("start")int start,@Param("limit")int limit);
	public List<AdminUser> getAllDelAdmin(@Param("start")int start,@Param("limit")int limit);
	public int add(AdminUser adminUser);
	public int updateToDel(String a_username);
	public int updateToBack(String a_username);
	public int getDelCount();
	public int getCount();
}
