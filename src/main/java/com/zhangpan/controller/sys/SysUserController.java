package com.zhangpan.controller.sys;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.user.SysUserService;

@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
    
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
	
	@Autowired
	private SysUserService userService;
	
	@ResponseBody
	@RequestMapping("/save")
	public Object save(SysUser user){
		int count = userService.save(user);
		
		return this.getResponseState(count);
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
		Object user = userService.findById(userId);
		map.put("user", user);
		map.put("sex", 1);
		return "sys/sysUser";
	}
	
}
