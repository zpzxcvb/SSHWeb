package com.zhangpan.controller.sys;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.user.SysUserService;
import com.zhangpan.util.DateUtil;
import com.zhangpan.util.StringUtil;

@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
    
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/list")
    public String list(){
        return "/sys/user/list";
    }
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Object pageList(){
		Page<SysUser> page = userService.findPage(paramMap);
        return pageData(page);
	}
	
	@RequestMapping("/find/{userId}")
	public String findById(@PathVariable("userId")Integer userId,ModelMap map){
		System.out.println("userId---->"+userId);
		Object user = userService.findById(userId);
		map.put("user", user);
		map.put("sex", 1);
		return "sys/user/sysUser";
	}
	
	@RequestMapping("/saveOrUpdate")
    public String model(Integer id, Model model){
	    String view = "";
        if(id != null) {
            SysUser user = userService.findById(id);
            model.addAttribute("user", user);
            view = "/sys/user/edit";
        }else {
            view = "/sys/user/add";
        }
        return view;
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public Object save(SysUser model){
        model.setCreateTime(DateUtil.currentDateTime());
        String password = model.getPassword();
        password = DigestUtils.md5Hex(password);
        model.setPassword(password);
        int count = userService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public Object update(SysUser model){
        model.setUpdateTime(DateUtil.currentDateTime());
        int count = userService.update(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = userService.deleteByIds(ids);
        return getResponseState(count);
    }
	
}
