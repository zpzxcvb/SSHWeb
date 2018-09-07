package com.zhangpan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.user.SysUserService;

@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map login(){
	    System.out.println(this.paramMap);
	    String userName = paramMap.get("userName");
	    String password = paramMap.get("password");
	    SysUser user = userService.userAuth(paramMap);
	    if(user != null) {
	        session.setAttribute("user", user);
//	        request.setAttribute("mainPage", "/sys/main.html");
	        result.put("code","1");
	    }else {
	        result.put("code","0");
	    }
		return result;
	}
	
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}
