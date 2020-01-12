package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.logistics.pojo.Order;

public interface OrderMapper {
	public int add(Order order);
	public List<Order> findUserOrder(String username);
	public List<Order> getUserOrderByPhone(String phone);
	public Order getUserOrderByNumber(String number);
	public int findPageCount(String username);
	public List<Order> findOrderByPage(@Param("username")String username,@Param("start")int start,@Param("pageSize")int pageSize);
	public List<Order> getPageAll(@Param("start")int start,@Param("pageSize")int pageSize);
	public int orderFinish(@Param("o_number")String o_number,@Param("o_endDate")String o_endDate);
	public List<Order> getAll(String adminName);
	public int deleteByOrderNum(String o_number);
	public int getCount();
	public int getOkCount();
	public List<Order> getPageAllOk(@Param("start")int start,@Param("pageSize")int pageSize);
	public int staffGetOrder(String o_number);
}
