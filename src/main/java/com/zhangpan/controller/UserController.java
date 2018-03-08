package com.zhangpan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.zhangpan.config.PageInfo;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@ResponseBody
	@RequestMapping("/save")
	public String save(SysUser user){
		int count = userService.save(user);
		
		String code = this.getResponseCode(count);
		
		result.put("code", code);
		
		return "test";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SysUser> findAllUsers(ModelMap map){
		List<SysUser> userList = userService.findAll();
		
		map.put("userList", userList);
		
		return userList;
	}
	
	@RequestMapping("/findPage")
	public String findPage(ModelMap map){
		PageHelper.startPage(2, 2);
		List userList = userService.findPage(null);
		map.put("userList", userList);
		return "user/userInfo";
	}
	
	@RequestMapping("/find/{userId}")
	public String findAllUsers(@PathVariable("userId")Integer userId,ModelMap map){
		System.out.println("userId---->"+userId);
		SysUser user = userService.findById(userId);
		map.put("user", user);
		map.put("sex", 1);
		return "user/userInfo";
	}
	
}
