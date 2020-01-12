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
import com.logistics.pojo.JSONResult;
import com.logistics.pojo.User;
import com.logistics.service.UserService;

/**
 * 
 * user控制器
 * 
 * @author linb
 *
 */
// 告诉SpringMVC这是一个控制器类
@Controller
@RequestMapping("/logistics")
public class UserController {
	@Autowired
	UserService userService;

	// 注册
	@ResponseBody
	@RequestMapping("/checkUser")
	public String checkUser(User user) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		// 判断用户是否已经存在
		if (userService.getUser(user.getU_username()) != null) {
			jsonResult.setCode(500);
			jsonResult.setMsg("用户名已存在");
		} else {
			jsonResult.setCode(200);
			jsonResult.setMsg("注册成功");
			// 如果不存在则存入数据库
			userService.add(user);
			System.out.println(user + "-----注册成功----" + user.toString() + " " + jsonResult.getMsg());
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		return json.toJSONString();
	}

	// 检查用户名是否存在
	@ResponseBody
	@RequestMapping("/checkUsername")
	public String checkUsername(HttpServletRequest request) {
		String name = "";
		name = request.getParameter("name");
		User user = new User();
		String result = "";
		JSONObject json = new JSONObject();
		// 判断用户是否已经存在
		if (userService.getUser(name) != null) {
			result = "用户名已存在";
		} else {
			result = "";
		}
		json.put("result", result);
		return result;
	}

	// 登录验证
	@ResponseBody
	@RequestMapping("/checkLogin")
	public String checkLogin(User clientUser, HttpSession session) {
		User user = new User();
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		user = userService.getUser(clientUser.getU_username());
		// 判断用户是否已经存在
		if (user != null) {
			jsonResult.setCode(1);
			jsonResult.setMsg("密码不正确！");
			if (clientUser.getU_password().equals(user.getU_password())) {
				jsonResult.setCode(200);
				jsonResult.setMsg("登录成功！");
				// 将username存入session
				session.setAttribute("username", clientUser.getU_username());
			}
		} else {
			jsonResult.setCode(0);
			jsonResult.setMsg("用户名不存在！");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----登录----" + user + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 退出登录
	@ResponseBody
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		jsonResult.setCode(200);
		jsonResult.setMsg("退出登录成功！");
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----退出登录-----");
		session.setAttribute("username", null);
		return json.toJSONString();
	}

	// 判断是否已经登录
	@ResponseBody
	@RequestMapping("/checkIsLogin")
	public String checkIsLogin(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		String result = "无";
		if (session.getAttribute("username") == null) {
			jsonResult.setCode(500);
			jsonResult.setMsg("未登录");
		} else {
			jsonResult.setCode(200);
			jsonResult.setMsg("已登录");
			jsonResult.setData((String) session.getAttribute("username"));
		}
		json.put("result", JSONObject.toJSON(jsonResult));

		return json.toJSONString();
	}

	// 获取用户详细信息
	@ResponseBody
	@RequestMapping("/getUserInfo")
	public String getUserInfo(User clientUser, HttpSession session) {
		User user = new User();
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		user = userService.getUser(clientUser.getU_username());
		if (user != null) {
			jsonResult.setCode(200);
			jsonResult.setMsg("获取信息成功");
			jsonResult.setData(user);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("获取信息失败");
		}

		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----获取用户详细信息----" + user + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 用户信息修改
	@ResponseBody
	@RequestMapping("/changeUserInfo")
	public String changeUserInfo(User user) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		int i = userService.changeUserInfo(user);
		if (i == 1) {
			jsonResult.setCode(200);
			jsonResult.setMsg("修改信息成功！");
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("修改信息失败！");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("----用户信息修改---" + jsonResult.getMsg());
		return json.toJSONString();
	}
}
