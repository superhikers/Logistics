package com.logistics.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.logistics.pojo.Order;

public interface OrderService {
	int add(Order order);
	List<Order> findUserOrder(String username);
	List<Order> getUserOrderByPhone(String phone);
	Order getUserOrderByNumber(String phone);
	int findPageCount(String username);
	List<Order> findOrderByPage(String username,int start,int pageSize);
	int orderFinish(String o_number,String o_endDate);
	List<Order> getAll(String adminName);
	List<Order> getPageAll(int start,int pageSize);
	int deleteByOrderNum(String o_number);
	int getCount();
	int getOkCount();
	List<Order> getPageAllOk(int start,int pageSize);
	int staffGetOrder(String o_number);
}	
