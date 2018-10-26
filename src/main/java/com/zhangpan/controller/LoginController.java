package com.zhangpan.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.model.SysMenu;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.menu.SysMenuService;
import com.zhangpan.service.sys.user.SysUserService;

@Controller
@RequestMapping
public class LoginController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
    private SysMenuService sysMenuService;
	
	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping("/")
    public String login() {
        return "login";
    }
	
	/**
	 * 登陆验证
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object auth(String userName, String password) {
	    try {
    	    Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        
            subject.login(token);
            SysUser user = (SysUser) subject.getPrincipal();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            
            List<SysMenu> menuList = sysMenuService.listAllMenu(1);
            session.setAttribute("menuList", menuList);
            
            getResults("1", "", "");
        } catch (IncorrectCredentialsException e) {
            getResults("0", "登录密码不正确，请重新输入", "");
        } catch (LockedAccountException e) {
            getResults("0", "此帐户已被禁用，请联系管理员", "");
        } catch (UnknownAccountException e) {
            getResults("0", "用户不存在", "");
        } catch (Exception e) {
            e.printStackTrace();
            getResults("0", "未知错误，请联系管理员", "");
        }
	    return result;
	}
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/main")
    public String main() {
        return "main";
    }
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/home")
    public String home() {
        return "home";
    }
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
	    // shiro销毁登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return null;//"redirect:/login";
    }
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	@RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
