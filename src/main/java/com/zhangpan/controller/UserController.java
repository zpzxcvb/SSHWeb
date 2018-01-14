package com.zhangpan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(SysUser user){
		int count = userService.save(user);
		
		String code = this.getResponseCode(count);
		
		result.put("code", code);
		
		return "test";
	}
	
	@RequestMapping("/findAll")
	public String findAllUsers(ModelMap map){
		List<SysUser> userList = userService.findAll();
		
		map.put("userList", userList);
		
		return "test";
	}
	
	@RequestMapping("/find/{userId}")
	public String findAllUsers(@PathVariable("userId")Integer userId,ModelMap map){
		System.out.println("userId---->"+userId);
		
		SysUser user = userService.findById(userId);
		
		map.put("user", user);
		
		return "test";
	}
	
}
