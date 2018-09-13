package com.zhangpan.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.user.SysUserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/")
    public String login() {
        return "user/login";
    }
	
	@RequestMapping("/auth")
	@ResponseBody
	public Object auth(){
	    String password = paramMap.get("password");
//	    paramMap.put("password", DigestUtils.md5Hex(password));
	    SysUser user = userService.userAuth(paramMap);
	    if(user != null) {
	        session.setAttribute("user", user);
	        result.put("code","1");
	    }else {
	        result.put("code","0");
	    }
	    return result;
	}
	
	@RequestMapping("/main")
    public String main() {
        return "main";
    }
	
	@RequestMapping("/home")
    public String home() {
        return "home";
    }
	
	@RequestMapping("/logout")
	public String logout() {
        // 移除session
        session.removeAttribute("user");
        return "redirect:/login/";
    }
	
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}
