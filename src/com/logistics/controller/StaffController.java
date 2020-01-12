package com.logistics.controller;

import java.text.SimpleDateFormat;
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
import com.logistics.pojo.AdminUser;
import com.logistics.pojo.JSONResult;
import com.logistics.pojo.Order;
import com.logistics.pojo.OrderConnStaff;
import com.logistics.pojo.PageInfo;
import com.logistics.pojo.Staff;
import com.logistics.pojo.User;
import com.logistics.service.OrderService;
import com.logistics.service.StaffService;
import com.logistics.service.UserService;

/**
 * 
 * staff员工表控制器
 * 
 * @author linb
 *
 */
// 告诉SpringMVC这是一个控制器类
@Controller
@RequestMapping({ "/LogisticsAdmin", "/LogisticsStaff" })
public class StaffController {
	@Autowired
	StaffService staffService;
	@Autowired
	OrderService orderService;

	public static String getTime() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd mm:SS");
		return sdfTime.format(new Date()).replaceAll("[[\\s-:punct:]]", "");
	}

	// 登录验证
	@ResponseBody
	@RequestMapping("/StaffCheckLogin")
	public String checkLogin(Staff staff, HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		Staff getStaff = staffService.getStaff(staff.getS_userid());
		if (getStaff != null) {
			if ((getStaff.getS_password()).equals(staff.getS_password())) {
				jsonResult.setCode(200);
				jsonResult.setMsg("登录成功");
				jsonResult.setData(getStaff);
				session.setAttribute("StaffName", getStaff.getS_name());
				session.setAttribute("StaffUserid", getStaff.getS_userid());
			} else {
				jsonResult.setCode(100);
				jsonResult.setMsg("密码错误！");
			}
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("用户名不存在");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----登录--------" + getStaff + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 退出登录
	@ResponseBody
	@RequestMapping("/StaffLogOut")
	public String StaffLogOut(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		jsonResult.setCode(200);
		jsonResult.setMsg("退出登录成功！");
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----员工退出登录-----");
		session.setAttribute("StaffName", null);
		session.setAttribute("StaffUserid", null);
		return json.toJSONString();
	}

	// 判断是否已经登录
	@ResponseBody
	@RequestMapping("/StaffCheckIsLogin")
	public String StaffCheckIsLogin(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		String result = "无";
		if (session.getAttribute("StaffName") == null) {
			jsonResult.setCode(500);
			jsonResult.setMsg("未登录");
		} else {
			jsonResult.setCode(200);
			jsonResult.setMsg("已登录");
			jsonResult.setData((String) session.getAttribute("StaffName"));
		}
		json.put("result", JSONObject.toJSON(jsonResult));

		return json.toJSONString();
	}

	// 获取可接单订单条目数
	@ResponseBody
	@RequestMapping("/getOkOrderCount")
	public String getOkOrderCount(HttpSession session) {
		PageInfo pageInfo = new PageInfo();
		JSONObject json = new JSONObject();
		int count = orderService.getOkCount();
		pageInfo.setPageNum(count);
		json.put("result", JSONObject.toJSON(pageInfo));
		System.out.println("-----getOrderCount--------" + " " + pageInfo.getPageNum());
		return json.toJSONString();
	}

	// 获取可接单的订单
	@ResponseBody
	@RequestMapping("/getOkOrder")
	public String getOkOrder(HttpSession session, PageInfo pageInfo) {
		AdminUser adminUser = new AdminUser();
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<Order> list = null;
		try {
			list = orderService.getPageAllOk(pageInfo.getStart(), pageInfo.getLimit());
		} catch (Exception e) {
			System.out.println("list获取失败！");
		}
		jsonResult.setCode(200);
		jsonResult.setMsg("获取order数据成功!");
		jsonResult.setData(list);
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getAll--------获取第" + pageInfo.getStart() + "行开始 " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 接单
	@ResponseBody
	@RequestMapping("/StaffGetOrder")
	public String StaffGetOrder(String o_number, HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		OrderConnStaff orderConnStaff = new OrderConnStaff();
		orderConnStaff.setO_number(o_number);
		orderConnStaff.setS_name((String) session.getAttribute("StaffName"));
		orderConnStaff.setS_userid((String) session.getAttribute("StaffUserid"));

		int resulr = orderService.staffGetOrder(o_number);
		int getResult = staffService.getOrder(orderConnStaff);
		if (resulr == 1 && getResult == 1) {
			jsonResult.setCode(200);
			jsonResult.setMsg("接单成功！");
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("找不到订单");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("------StaffGetOrder员工接单----");
		return json.toJSONString();
	}

	// 获取已接的单订单条目数
	@ResponseBody
	@RequestMapping("/getMyOrderCount")
	public String getMyOrderCount(HttpSession session) {
		PageInfo pageInfo = new PageInfo();
		JSONObject json = new JSONObject();
		int count = staffService.getMyCount((String) session.getAttribute("StaffUserid"));
		pageInfo.setPageNum(count);
		json.put("result", JSONObject.toJSON(pageInfo));
		System.out.println("-----getOrderCount--------" + " " + pageInfo.getPageNum());
		return json.toJSONString();
	}

	// 获取已接单的订单
	@ResponseBody
	@RequestMapping("/getMyOrder")
	public String getMyOrder(HttpSession session, PageInfo pageInfo) {
		AdminUser adminUser = new AdminUser();
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<Order> list = null;
		try {
			list = staffService.getMyOrder((String) session.getAttribute("StaffUserid"), pageInfo.getStart(),
					pageInfo.getLimit());
		} catch (Exception e) {
			System.out.println("list获取失败！");
		}
		jsonResult.setCode(200);
		jsonResult.setMsg("获取order数据成功!");
		jsonResult.setData(list);
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getMyOrder--------获取第" + pageInfo.getStart() + "行开始 " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 发货
	@ResponseBody
	@RequestMapping("/OrderDeliver")
	public String OrderDeliver(String o_number, HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		int deliverNum = staffService.setDeliver(o_number);
		if (deliverNum == 1) {
			jsonResult.setCode(200);
			jsonResult.setMsg("发货成功！");
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("找不到订单");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("------OrderDeliver发货----");
		return json.toJSONString();
	}

	// 发货
	@ResponseBody
	@RequestMapping("/OrderSend")
	public String OrderSend(String o_number, HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		int deliverNum = staffService.orderSend(o_number);
		if (deliverNum == 1) {
			jsonResult.setCode(200);
			jsonResult.setMsg("派件成功！");
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("找不到订单");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("------OrderDeliver发货----");
		return json.toJSONString();
	}
	
	// 修改订单位置信息
		@ResponseBody
		@RequestMapping("/changeLocation")
		public String changeLocation(String o_number,String o_location,HttpSession session) {
			JSONResult jsonResult = new JSONResult();
			JSONObject json = new JSONObject();

			int deliverNum = staffService.changeLocation(o_number,o_location);
			if (deliverNum == 1) {
				jsonResult.setCode(200);
				jsonResult.setMsg("修改订单位置成功！");
			} else {
				jsonResult.setCode(500);
				jsonResult.setMsg("找不到订单");
			}
			json.put("jsonResult", JSONObject.toJSON(jsonResult));
			System.out.println("------修改订单位置----");
			return json.toJSONString();
		}
	
		// 获取已完成的单订单条目数
		@ResponseBody
		@RequestMapping("/getFinishOrderCount")
		public String getFinishOrderCount(HttpSession session) {
			PageInfo pageInfo = new PageInfo();
			JSONObject json = new JSONObject();
			int count = staffService.getFinishCount((String) session.getAttribute("StaffUserid"));
			pageInfo.setPageNum(count);
			json.put("result", JSONObject.toJSON(pageInfo));
			System.out.println("-----getOrderCount--------" + " " + pageInfo.getPageNum());
			return json.toJSONString();
		}

		// 获取已完成的订单
		@ResponseBody
		@RequestMapping("/getFinishOrder")
		public String getFinishOrder(HttpSession session, PageInfo pageInfo) {
			AdminUser adminUser = new AdminUser();
			JSONResult jsonResult = new JSONResult();
			JSONObject json = new JSONObject();
			List<Order> list = null;
			try {
				list = staffService.getFinishOrder((String) session.getAttribute("StaffUserid"), pageInfo.getStart(),
						pageInfo.getLimit());
			} catch (Exception e) {
				System.out.println("list获取失败！");
			}
			System.out.println(list.toString());
			jsonResult.setCode(200);
			jsonResult.setMsg("获取order数据成功!");
			jsonResult.setData(list);
			json.put("result", JSONObject.toJSON(jsonResult));
			System.out.println("-----getMyOrder--------获取第" + pageInfo.getStart() + "行开始 " + jsonResult.getMsg());
			return json.toJSONString();
		}
		
		
		
	

	/**
	 * 以下代码是管理员界面获取员工信息
	 */
	// 获取员工条目数
	@ResponseBody
	@RequestMapping("/getStaffCount")
	public String getStaffCount(HttpSession session) {
		PageInfo pageInfo = new PageInfo();
		JSONObject json = new JSONObject();
		int count = staffService.getStaffCount();
		pageInfo.setPageNum(count);
		json.put("result", JSONObject.toJSON(pageInfo));
		System.out.println("-----getStaffCount--------" + " " + pageInfo.getPageNum());
		return json.toJSONString();
	}

	// 获取员工表
	@ResponseBody
	@RequestMapping("/getAllStaff")
	public String getAllStaff(HttpSession session, PageInfo pageInfo) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<Staff> list = null;
		try {
			list = staffService.getAllStaff(pageInfo.getStart(), pageInfo.getLimit());
		} catch (Exception e) {
			System.out.println("list获取失败");
		}
		if (list != null) {
			jsonResult.setCode(200);
			jsonResult.setMsg("获取StaffList数据成功!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("获取StaffList数据失败!");
			jsonResult.setData(list);
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getAllStaff--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 添加人员
	@ResponseBody
	@RequestMapping("/addStaff")
	public String addStaff(Staff staff) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		if (staff != null) {
			staff.setS_userid(getTime());
			staffService.addStaff(staff);
			jsonResult.setCode(200);
			jsonResult.setMsg("添加人员成功！");
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("添加人员失败！");
		}
		System.out.println(jsonResult.getMsg());
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		return json.toJSONString();
	}

	// 删除人员
	@ResponseBody
	@RequestMapping("/deleteSufferById")
	public String deleteSufferById(Staff staff) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		int id = staff.getS_id();
		if (id != 0) {
			staffService.deleteSufferById(id);
			jsonResult.setCode(200);
			jsonResult.setMsg("删除成功！");
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("删除失败！");
		}
		System.out.println(jsonResult.getMsg());
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		return json.toJSONString();
	}

}
