package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.mapper.StaffMapper;
import com.logistics.mapper.UserMapper;
import com.logistics.pojo.Order;
import com.logistics.pojo.OrderConnStaff;
import com.logistics.pojo.Staff;
import com.logistics.pojo.User;
import com.logistics.service.StaffService;
import com.logistics.service.UserService;

//注解为一个service
@Service
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	StaffMapper staffMapper;

	@Override
	public int getStaffCount() {
		return staffMapper.getStaffCount();
	}

	@Override
	public List<Staff> getAllStaff(int start, int limit) {
		return staffMapper.getAllStaff(start, limit);
	}

	@Override
	public int addStaff(Staff staff) {
		return staffMapper.addStaff(staff);
	}

	@Override
	public int deleteSufferById(int id) {
		return staffMapper.deleteSufferById(id);
	}

	@Override
	public Staff getStaff(String s_userid) {
		return staffMapper.getStaff(s_userid);
	}

	@Override
	public int getOrder(OrderConnStaff orderConnStaff) {
		return staffMapper.getOrder(orderConnStaff);
	}

	@Override
	public int getMyCount(String s_userid) {
		return staffMapper.getMyCount(s_userid);
	}

	@Override
	public List<Order> getMyOrder(String s_userid,int start, int limit) {
		return staffMapper.getMyOrder(s_userid,start, limit);
	}

	@Override
	public int setDeliver(String o_number) {
		return staffMapper.setDeliver(o_number);
	}

	@Override
	public int orderSend(String o_number) {
		return staffMapper.orderSend(o_number);
	}

	@Override
	public int changeLocation(String o_number, String o_location) {
		return staffMapper.changeLocation(o_number, o_location);
	}

	@Override
	public int getFinishCount(String s_userid) {
		return staffMapper.getFinishCount(s_userid);
	}

	@Override
	public List<Order> getFinishOrder(String s_userid, int start, int limit) {
		return staffMapper.getFinishOrder(s_userid, start, limit);
	}
	
	


}
