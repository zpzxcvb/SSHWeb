package com.zhangpan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
	
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
	
	@RequestMapping("/findPage")
    public String findPage(ModelMap map){
        return "sys/sysUser";
    }
	
	@RequestMapping("/findAll")
	@ResponseBody
	public Object findAllUsers(){
		Page<SysUser> page = userService.findPage(paramMap);
        return pageData(page);
	}
	
	@RequestMapping("/find/{userId}")
	public String findAllUsers(@PathVariable("userId")Integer userId,ModelMap map){
		System.out.println("userId---->"+userId);
		SysUser user = userService.findById(userId);
		map.put("user", user);
		map.put("sex", 1);
		return "sys/sysUser";
	}
	
}
