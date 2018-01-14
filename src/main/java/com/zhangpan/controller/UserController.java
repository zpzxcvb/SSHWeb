package com.zhangpan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@RequestMapping("/login/{id}")
	@ResponseBody
	public String login(@PathVariable("id")String id){
		System.out.println("id---->"+id);
		return "test";
	}
	
}
