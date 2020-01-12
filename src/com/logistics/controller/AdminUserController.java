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
import com.logistics.pojo.PageInfo;
import com.logistics.pojo.User;
import com.logistics.service.AdminUserService;
import com.logistics.service.UserService;

/**
 * 
 * adminUser控制器
 * 
 * @author linb
 *
 */
// 告诉SpringMVC这是一个控制器类
@Controller
@RequestMapping("/LogisticsAdmin")
public class AdminUserController {
	@Autowired
	AdminUserService adminUserService;

	@Autowired
	UserService userService;

	// 生成当前时间
	public static String getTime() {
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdfTime.format(new Date());
	}

	// 登录验证  
	@ResponseBody
	@RequestMapping("/AdminCheckLogin")
	public String checkLogin(AdminUser user, HttpSession session) {
		AdminUser adminUser = new AdminUser();
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		AdminUser adminUserBack = adminUserService.getUser(user.getA_username());
		if (adminUserBack != null) {
			if ((adminUserBack.getA_password()).equals(user.getA_password())) {
				jsonResult.setCode(200);
				jsonResult.setMsg("登录成功");
				jsonResult.setData(adminUserBack);
				session.setAttribute("adminUsername", adminUserBack.getA_username());
			} else {
				jsonResult.setCode(100);
				jsonResult.setMsg("密码错误！");
			}
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("用户名不存在");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----登录--------" + user + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 退出登录
	@ResponseBody
	@RequestMapping("/adminLogOut")
	public String logOut(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();

		jsonResult.setCode(200);
		jsonResult.setMsg("退出登录成功！");
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----退出登录-----");
		session.setAttribute("adminUsername", null);
		return json.toJSONString();
	}

	// 判断是否已经登录
	@ResponseBody
	@RequestMapping("/adminCheckIsLogin")
	public String checkIsLogin(HttpSession session) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		String result = "无";
		if (session.getAttribute("adminUsername") == null) {
			jsonResult.setCode(500);
			jsonResult.setMsg("未登录");
		} else {
			jsonResult.setCode(200);
			jsonResult.setMsg("已登录");
			jsonResult.setData((String) session.getAttribute("adminUsername"));
		}
		json.put("result", JSONObject.toJSON(jsonResult));

		return json.toJSONString();
	}

	// 添加管理员
	@ResponseBody
	@RequestMapping("/addAdmin")
	public String addAdmin(AdminUser adminUser) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		// 判断用户是否已经存在
		if (adminUserService.getUser(adminUser.getA_username()) != null) {
			jsonResult.setCode(500);
			jsonResult.setMsg("用户名已存在");
			System.out.println("管理员添加失败，账户已存在");
		} else {
			jsonResult.setCode(200);
			jsonResult.setMsg("添加成功");
			adminUser.setA_date(getTime());
			adminUser.setA_state("已启用");
			// 如果表中不存在则存入数据库
			adminUserService.add(adminUser);
			System.out.println(adminUser + "-----注册成功----" + adminUser.toString() + " " + jsonResult.getMsg());
		}
		System.out.println(jsonResult.getMsg());
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		return json.toJSONString();
	}

	// 获取管理员表
	@ResponseBody
	@RequestMapping("/getAllAdmin")
	public String getAllAdmin(HttpSession session,PageInfo pageInfo) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<AdminUser> list = null;
		try {
			list = adminUserService.getAllAdmin(pageInfo.getStart(),pageInfo.getLimit());
		} catch (Exception e) {
			System.out.println("list获取失败");
		}
		if (list != null) {
			jsonResult.setCode(200);
			jsonResult.setMsg("获取adminList数据成功!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("获取adminList数据失败!");
			jsonResult.setData(list);
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getAllAdmin--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}
	
	//获取管理员用户条目数
		@ResponseBody
		@RequestMapping("/getAdminCount")
		public String getAdminCount(HttpSession session) {
			PageInfo pageInfo = new PageInfo();
			JSONObject json = new JSONObject();
			int count = adminUserService.getCount();
			pageInfo.setPageNum(count);
			json.put("result", JSONObject.toJSON(pageInfo));
			System.out.println("-----getOrderCount--------"  + " " + pageInfo.getPageNum());
			return json.toJSONString();
		}
	
	// 通过用户名修改管理员状态为已删除
	@ResponseBody
	@RequestMapping("/deleteAdminByAdminname")
	public String deleteAdminByAdminname(AdminUser adminUser) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		System.out.println(adminUser.getA_username() + "-----getA_username()");
		if (adminUser.getA_username() != null) {
			int resultInt = adminUserService.updateToDel(adminUser.getA_username());
			jsonResult.setCode(200);
			jsonResult.setMsg("删除" + adminUser.getA_username() + "数据成功!");
			jsonResult.setData(resultInt);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("删除失败!");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----deleteAdminByAdminname--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 获取已删除的管理员表
	@ResponseBody
	@RequestMapping("/getAllDelAdmin")
	public String getAllDelAdmin(HttpSession session,PageInfo pageInfo) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<AdminUser> list = null;
		try {
			list = adminUserService.getAllDelAdmin(pageInfo.getStart(),pageInfo.getLimit());
		} catch (Exception e) {
			System.out.println("list获取失败");
		}
		if (list != null) {
			jsonResult.setCode(200);
			jsonResult.setMsg("获取adminDelList数据成功!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("获取adminDelList数据失败或为空!");
			jsonResult.setData(list);
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getAllDelAdmin--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}
	
	//获取被删除的管理员用户条目数
	@ResponseBody
	@RequestMapping("/getAdminDelCount")
	public String getAdminDelCount(HttpSession session) {
		PageInfo pageInfo = new PageInfo();
		JSONObject json = new JSONObject();
		int count = adminUserService.getDelCount();
		pageInfo.setPageNum(count);
		json.put("result", JSONObject.toJSON(pageInfo));
		System.out.println("-----getOrderCount--------"  + " " + pageInfo.getPageNum());
		return json.toJSONString();
	}
	
	// 通过用户名修改用户状态为已启用
	@ResponseBody
	@RequestMapping("/BackByAdmin")
	public String BackByAdmin(AdminUser adminUser) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		System.out.println(adminUser.getA_username() + "-----getA_username()");
		if (adminUser.getA_username() != null) {
			int resultInt = adminUserService.updateToBack(adminUser.getA_username());
			jsonResult.setCode(200);
			jsonResult.setMsg("恢复" + adminUser.getA_username() + "数据成功!");
			jsonResult.setData(resultInt);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("恢复失败!");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----BackByAdmin--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}
	
	//获取普通用户条目数
			@ResponseBody
			@RequestMapping("/getUserCount")
			public String getUserCount(HttpSession session) {
				PageInfo pageInfo = new PageInfo();
				JSONObject json = new JSONObject();
				int count = userService.getCount();
				pageInfo.setPageNum(count);
				json.put("result", JSONObject.toJSON(pageInfo));
				System.out.println("-----getUserCount--------"  + " " + pageInfo.getPageNum());
				return json.toJSONString();
			}
	
	// 获取用户表
	@ResponseBody
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpSession session,PageInfo pageInfo) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<User> list = null;
		 try {
			 list = userService.list(pageInfo.getStart(),pageInfo.getLimit());
		 } catch (Exception e) {
			 System.out.println("list获取出现问题------+++");
		 }
		if (list != null) {
			jsonResult.setCode(200);
			jsonResult.setMsg("获取UserList数据成功!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("获取UserList数据失败!");
			jsonResult.setData(list);
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getAllUser--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 通过用户名修改用户状态为已删除
	/**
	 * 通过用户名修改用户状态为已删除
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteByUsername")
	public String deleteByUsername(User user) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		System.out.println(user.getU_username() + "-----getU_username()");
		if (user.getU_username() != null) {
			int resultInt = userService.updateToDel(user.getU_username());
			jsonResult.setCode(200);
			jsonResult.setMsg("删除" + user.getU_username() + "数据成功!");
			jsonResult.setData(resultInt);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("删除失败!");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----deleteByUsername--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}

	// 通过用户名修改用户状态为已启用
	@ResponseBody
	@RequestMapping("/BackByUser")
	public String BackByUser(User user) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		System.out.println(user.getU_username() + "-----getU_username()" + user);
		if (user.getU_username() != null) {
			int resultInt = userService.updateToStart(user.getU_username());
			jsonResult.setCode(200);
			jsonResult.setMsg("启用" + user.getU_username() + "数据成功!");
			jsonResult.setData(resultInt);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("启用失败!");
		}
		json.put("jsonResult", JSONObject.toJSON(jsonResult));
		System.out.println("-----BackByUser--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}
	
	//获取普通用户被删除的条目数
	@ResponseBody
	@RequestMapping("/getUserDelCount")
	public String getUserDelCount(HttpSession session) {
		PageInfo pageInfo = new PageInfo();
		JSONObject json = new JSONObject();
		int count = userService.getDelCount();
		pageInfo.setPageNum(count);
		json.put("result", JSONObject.toJSON(pageInfo));
		System.out.println("-----getUserCount--------"  + " " + pageInfo.getPageNum());
		return json.toJSONString();
	}

	
	// 获取已删除的用户表
	@ResponseBody
	@RequestMapping("/getAllDelUser")
	public String getAllDelUser(HttpSession session,PageInfo pageInfo) {
		JSONResult jsonResult = new JSONResult();
		JSONObject json = new JSONObject();
		List<User> list = null;
		try {
			
			list = userService.listAboutDel(pageInfo.getStart(),pageInfo.getLimit());
		} catch (Exception e) {
			System.out.println("list获取失败");
		}
		if (list != null) {
			jsonResult.setCode(200);
			jsonResult.setMsg("获取UserList数据成功!");
			jsonResult.setData(list);
		} else {
			jsonResult.setCode(500);
			jsonResult.setMsg("获取UserList数据失败!");
			jsonResult.setData(list);
		}
		json.put("result", JSONObject.toJSON(jsonResult));
		System.out.println("-----getAllDelUser--------" + " " + jsonResult.getMsg());
		return json.toJSONString();
	}
	
}
