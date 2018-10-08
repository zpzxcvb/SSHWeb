package com.zhangpan.controller;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.model.SysMenu;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.menu.SysMenuService;
import com.zhangpan.service.sys.user.SysUserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
    private SysMenuService sysMenuService;
	
	@RequestMapping("/")
    public String login() {
        return "user/login";
    }
	
	@RequestMapping("/auth")
	@ResponseBody
	public Object auth(){
	    String password = paramMap.get("password").toString();
	    paramMap.put("password", DigestUtils.md5Hex(password));
	    List<SysUser> users = userService.findList(paramMap);
	    if(users.size() > 0) {
	        SysUser sysUser = users.get(0);
	        if(sysUser.getStatus() == 1) {
	            int hasUser = userService.userAuth(paramMap);
	            if(hasUser == 1) {
	                session.setAttribute("userId", sysUser.getUserId());
	                session.setAttribute("userName", sysUser.getUserName());
	                List<SysMenu> menuList = sysMenuService.listAllMenu(1);
	                session.setAttribute("menuList", menuList);
	                getResults("1", "", "");
	            }else {
	                getResults("0", "帐户名或登录密码不正确，请重新输入", "");
	            }
	        }else {
	            getResults("0", "此帐户已被禁用，请联系管理员", "");
	        }
	    }else {
	        getResults("0", "用户不存在", "");
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
        session.invalidate();
        return "redirect:/login/";
    }
	
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}
