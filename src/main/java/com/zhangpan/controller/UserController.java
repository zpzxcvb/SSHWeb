package com.zhangpan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysDictService;
import com.zhangpan.service.SysUserService;
import com.zhangpan.task.HelloTask;
import com.zhangpan.util.FileScanUtil;
import com.zhangpan.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/login/{id}")
	public String login2(@PathVariable("id")String id){
		System.out.println("id---->"+id);
		return id;
	}
	
	@RequestMapping("/jsonTest")
	public @ResponseBody Map<String,Object> jsonTest(){
		String draw=request.getParameter("draw");
		String start=request.getParameter("start");
		String length=request.getParameter("length");
		System.out.println(start);
		List<SysUser> list = new ArrayList<SysUser>();
		for (int i = 1; i <= 15; i++) {
			SysUser user = new SysUser();
			user.setUserId(i);
			user.setUserName("张三"+i);
			user.setEmail(1000000+i+"@.qqcom");
			list.add(user);
		}
		Map<String,Object> map=new HashMap<String, Object>(); 
		map.put("data", list);
		return map;
	}
	@RequestMapping("/org")
	public @ResponseBody List org(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<SysUser> users=userService.findAll();
		System.out.println(JSON.toJSONString(users));
		for(SysUser user : users){
			Map<String, String> map=new HashMap<String, String>();
			map.put("id", user.getUserId().toString());
			map.put("name", user.getRealname());
			map.put("pId", "0");
			list.add(map);
		}
		return list;
	}
}
