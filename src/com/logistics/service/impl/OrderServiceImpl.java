package com.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.mapper.OrderMapper;
import com.logistics.pojo.Order;
import com.logistics.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper;
	@Override
	public int add(Order order) {

		return orderMapper.add(order);
	}
	@Override
	public List<Order> findUserOrder(String username) {
		
		return orderMapper.findUserOrder(username);
	}
	@Override
	public List<Order> getUserOrderByPhone(String phone) {
		
		return orderMapper.getUserOrderByPhone(phone);
	}
	@Override
	public Order getUserOrderByNumber(String number) {
		return orderMapper.getUserOrderByNumber(number);
	}
	@Override
	public int findPageCount(String username) {
		return orderMapper.findPageCount(username);
	}
	@Override
	public List<Order> findOrderByPage(String username, int start, int pageSize) {
		return orderMapper.findOrderByPage(username, start, pageSize);
	}
	@Override
	public int orderFinish(String o_number,String o_endDate) {
		return orderMapper.orderFinish(o_number,o_endDate);
	}
	@Override
	public List<Order> getAll(String adminName) {
		return orderMapper.getAll(adminName);
	}
	@Override
	public int deleteByOrderNum(String o_number) {
		return orderMapper.deleteByOrderNum(o_number);
	}
	@Override
	public int getCount() {
		return orderMapper.getCount();
	}
	@Override
	public List<Order> getPageAll(int start, int pageSize) {
		return orderMapper.getPageAll(start, pageSize);
	}
	@Override
	public int getOkCount() {
		return orderMapper.getOkCount();
	}
	@Override
	public List<Order> getPageAllOk(int start, int pageSize) {
		return orderMapper.getPageAllOk(start, pageSize);
	}
	@Override
	public int staffGetOrder(String o_number) {
		return orderMapper.staffGetOrder(o_number);
	}
	

}
