package com.logistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.logistics.pojo.AdminUser;
import com.logistics.pojo.JSONResult;
import com.logistics.pojo.Order;
import com.logistics.pojo.PageInfo;
import com.logistics.pojo.User;
import com.logistics.service.AdminUserService;
import com.logistics.service.OrderService;
import com.logistics.service.UserService;

/**
 * 
 * adminOrder控制器
 * 
 * @author linb
 *
 */
// 告诉SpringMVC这是一个控制器类
@Controller
@RequestMapping("/LogisticsAdmin")
public class AdminOrderController {
	@Autowired
	OrderService orderService;
	// 获取所有订单表
		@ResponseBody
		@RequestMapping("/getAll")
		public String getAll(HttpSession session,PageInfo pageInfo) {
			AdminUser adminUser = new AdminUser();
			JSONResult jsonResult = new JSONResult();
			JSONObject json = new JSONObject();
			List<Order> list = null;
			try {
				list = orderService.getPageAll(pageInfo.getStart(),pageInfo.getLimit());
			} catch (Exception e) {
				System.out.println("list获取失败！");
			}
			jsonResult.setCode(200);
			jsonResult.setMsg("获取order数据成功!");
			jsonResult.setData(list);
			json.put("result", JSONObject.toJSON(jsonResult));
			System.out.println("-----getAll--------获取第" +
			pageInfo.getStart()+ "行开始 "+ jsonResult.getMsg());
			return json.toJSONString();
		}
		
		//获取订单条目数
		@ResponseBody
		@RequestMapping("/getOrderCount")
		public String getOrderCount(HttpSession session) {
			PageInfo pageInfo = new PageInfo();
			JSONObject json = new JSONObject();
			int count = orderService.getCount();
			pageInfo.setPageNum(count);
			json.put("result", JSONObject.toJSON(pageInfo));
			System.out.println("-----getOrderCount--------"  + " " + pageInfo.getPageNum());
			return json.toJSONString();
		}
		
		// 通过订单号删除订单
				@ResponseBody
				@RequestMapping("/deleteByOrderNum")
				public String deleteByOrderNum(Order order) {
					AdminUser adminUser = new AdminUser();
					JSONResult jsonResult = new JSONResult();
					JSONObject json = new JSONObject();
					System.out.println(order.getO_number()+"-----O_number()");
					if(order.getO_number()!=null) {
						int resultInt = orderService.deleteByOrderNum(order.getO_number());
						jsonResult.setCode(200);
						jsonResult.setMsg("删除"+order.getO_number()+"数据成功!"); 
						jsonResult.setData(resultInt);
					}else {
						jsonResult.setCode(500);
						jsonResult.setMsg("删除失败!");
					}
					json.put("jsonResult", JSONObject.toJSON(jsonResult));
					System.out.println("-----deleteByOrderNum--------"  + " " + jsonResult.getMsg());
					return json.toJSONString();
				}
}



