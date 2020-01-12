package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.logistics.pojo.AdminUser;
import com.logistics.pojo.Order;
import com.logistics.pojo.OrderConnStaff;
import com.logistics.pojo.Staff;
import com.logistics.pojo.User;

public interface StaffMapper {
	
	public int getStaffCount();
	
	public List<Staff> getAllStaff(@Param("start")int start,@Param("limit")int limit);

	public int addStaff(Staff staff);
	
	public int deleteSufferById(int id);
	
	public Staff getStaff(String s_userid);
	
	public int getOrder(OrderConnStaff orderConnStaff);
	
	public int getMyCount(String s_userid);
	
	public List<Order> getMyOrder(@Param("s_userid")String s_userid,@Param("start")int start,@Param("limit")int limit);
	
	public int setDeliver(String o_number);
	
	public int orderSend(String o_number);
	
	public int changeLocation(@Param("o_number")String o_number,@Param("o_location")String o_location);
	
	public int getFinishCount(String s_userid);
	
	public List<Order> getFinishOrder(@Param("s_userid")String s_userid,@Param("start")int start,@Param("limit")int limit);

	
}
