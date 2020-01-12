package com.logistics.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.logistics.pojo.JSONResult;
import com.logistics.pojo.Order;
import com.logistics.pojo.PageInfo;
import com.logistics.pojo.User;
import com.logistics.service.OrderService;
import com.logistics.service.UserService;
import com.logistics.service.impl.UserServiceImpl;
import com.logistics.util.PageUtil;

/**
 * order控制器
 * 
 * @author linb
 *
 */

// 告诉SpringMVC这是一个控制器类
@Controller
@RequestMapping("/logistics")
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	
	PageUtil pageUtil1 = null;
	PageUtil pageUtil2 = null;

	// 生成订单号
	public static String getTime() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		return sdfTime.format(new Date()).replaceAll("[[\\s-:punct:]]", "");
	}
	// 获取当前时间
		public static String getEndTime() {
			SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdfTime.format(new Date());
		}

	// 获取订单表
	@ResponseBody
	@RequestMapping("/getOrder")
	public String getOrder(Order order, HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		order.setO_number(getTime().toString());
		if (session.getAttribute("username") != null) {
			order.setU_username(session.getAttribute("username").toString());
			int i = orderService.add(order);
			jsonResult.setCode(200);
			jsonResult.setMsg("订单提交成功！");
			jsonResult.setData(i);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("订单提交失败！请先登录");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("--订单表--" + order.toString());
		return json.toJSONString();
	}

	// 通过用户名获取客户订单表
	@ResponseBody
	@RequestMapping("/getUserOrder")
	public String getUserOrder(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		int num = 0;
		String username = "";
		try {
			username = session.getAttribute("username").toString().trim()+"";
		} catch (Exception e) {
			System.out.println("----未登录");
		}
		if (username != "") {
			num = orderService.findPageCount(username);
			pageUtil1 = new PageUtil(num, 5, 1);
			List<Order> list = orderService.findOrderByPage(username, pageUtil1.getStart(), pageUtil1.getCount());
			System.out.println("----页面数---"+pageUtil1.getPageNun());
			jsonResult.setCode(200);
			jsonResult.setMsg("已登录!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("未登录!");
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("---getUserOrder客户订单表----" + jsonResult.getCode());
		return json.toJSONString();
	}
	
	// 通过用户名获取客户订单表上一页与下一页
		@ResponseBody
		@RequestMapping("/getUserOrderByPage")
		public String getUserOrderByPage(PageInfo pageInfo,HttpSession session) {
			JSONResult jsonResult = new JSONResult();
			JSONObject json = new JSONObject();
			int num = 0;
			String username = session.getAttribute("username").toString().trim();
			if (username != null) {
				num = orderService.findPageCount(username);
				pageUtil1 = new PageUtil(num, 5, pageInfo.getNowPage());
				System.out.println("----当前页----=="+pageInfo.getNowPage()+"--start--="+pageUtil1.getStart());
				List<Order> list = orderService.findOrderByPage(username, pageUtil1.getStart(), pageUtil1.getCount());
				jsonResult.setCode(200);
				jsonResult.setMsg("已登录!");
				if(list.size()==0) {
					jsonResult.setData(null);
				}else {
				jsonResult.setData(list);
				}
			} else {
				jsonResult.setCode(500);
				jsonResult.setMsg("未登录!");
			}
			json.put("result", JSONObject.toJSON(jsonResult));
			System.out.println(pageInfo.getNowPage()+"---getUserOrderByPage----" + jsonResult.getCode());
			return json.toJSONString();
		}
	
	
	// 通过用户电话号码获取客户包裹
	@ResponseBody
	@RequestMapping("/getUserOrderByPhone")
	public String getUserOrderByPhone(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		if (session.getAttribute("username") != null) {
			String username = session.getAttribute("username").toString().trim();
			User user = userService.getUser(username);
			List<Order> list = orderService.getUserOrderByPhone(user.getU_phone());
			System.out.println(list + user.getU_phone());
			jsonResult.setCode(200);
			jsonResult.setMsg("已登录!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("未登录!");
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("---getUserOrder客户订单表----" + jsonResult.getCode());
		return json.toJSONString();
	}

	// 通过订单号获取订单信息
	@ResponseBody
	@RequestMapping("/queryOrder")
	public String queryOrder(String orderList) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		Order order = new Order();
		List<Order> list = new ArrayList<Order>();
		System.out.println(orderList);
		//若查询多个表，按照，或,切割 分别查询
		String[] split = orderList.split(",|，");
        if (split != null && split.length != 0) {
            for (int i = 0; i < split.length; i++) {
            	System.out.println(split[i]);
            		order = orderService.getUserOrderByNumber(split[i]);
            		if(order!=null) 
            		{list.add(order);}
                }
            }
		if(list.size()!=0) {
			jsonResult.setCode(200);
			jsonResult.setMsg("查询成功！");
			jsonResult.setData(list);
		}else {
			jsonResult.setCode(500);
			jsonResult.setMsg("查询不到数据！");
			jsonResult.setData(list);
		}
        json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("------queryOrder客户订单表----\n" + jsonResult.getData());
		return json.toJSONString();
	}
	
	// 确认收货
		@ResponseBody
		@RequestMapping("/orderFinish")  
		public String orderFinish(String o_number) {
			JSONResult jsonResult = new JSONResult();
			JSONObject json = new JSONObject();
			int resulr = orderService.orderFinish(o_number,getEndTime());
			if(resulr==1) {
				jsonResult.setCode(200);
				jsonResult.setMsg("确认收货成功！");
			}else {
				jsonResult.setCode(500);
				jsonResult.setMsg("找不到订单");
			}
	        json.put("result", JSONObject.toJSON(jsonResult));
			System.out.println("------orderFinish用户确认收货----");
			return json.toJSONString();
		}

}



