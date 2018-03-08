package com.zhangpan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.service.SysUserService;

@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/login")
	public String login(String userName, String password){
		System.out.println(userName+"--"+password);
		result.put("name", userName);
		return "user/login";
	}
	
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}
