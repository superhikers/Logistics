package com.logistics.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.logistics.pojo.Order;
import com.logistics.pojo.OrderConnStaff;
import com.logistics.pojo.Staff;
import com.logistics.pojo.User;

public interface StaffService {
	
	int getStaffCount();
	
	List<Staff> getAllStaff(int start ,int limit);
	
	int addStaff(Staff staff);
	
	int deleteSufferById(int id);
	
	Staff getStaff(String s_userid);
	
	int getOrder(OrderConnStaff orderConnStaff);
	
	int getMyCount(String s_userid);
	
	List<Order> getMyOrder(String s_userid,int start,int limit);
	
	int setDeliver(String o_number);
	
	int orderSend(String o_number);
	
	int changeLocation(String o_number,String o_location);
	
	int getFinishCount(String s_userid);
	
	List<Order> getFinishOrder(String s_userid,int start,int limit);

	
	
}
