package com.zhangpan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhangpan.model.Student;
import com.zhangpan.service.StudentService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/login")
	public String login(String username,String password,Model model){
		if("admin".equals(username)&&"123".equals(password)){
			Student stu=studentService.findStudentById(1);
			model.addAttribute("username", username);
			return "index";
		}else{
			return "login";
		}
	}
}
