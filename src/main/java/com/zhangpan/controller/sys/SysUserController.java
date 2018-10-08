package com.zhangpan.controller.sys;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysUser;
import com.zhangpan.model.SysUserInfo;
import com.zhangpan.service.sys.user.SysUserService;
import com.zhangpan.service.sys.userInfo.SysUserInfoService;
import com.zhangpan.service.sys.userRole.SysUserRoleService;
import com.zhangpan.util.DateUtil;
import com.zhangpan.util.StringUtil;

@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {
    
	@Autowired
	private SysUserService userService;
	
	@Autowired
    private SysUserInfoService userInfoService;
	
	@Autowired
	private SysUserRoleService userRoleService;
	
	@RequestMapping("/list")
    public String list(){
        return "/sys/user/list";
    }
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Object pageList(){
		Page<Object> page = userService.findPage(paramMap);
        return pageData(page);
	}
	
	@RequestMapping("/find/{userId}")
	public String findById(@PathVariable("userId")Integer userId,Model model){
		System.out.println("userId---->"+userId);
		Object user = userService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("sex", 1);
		return "sys/user/sysUser";
	}
	
	@RequestMapping("/goAdd")
    public String goAdd(){
        return "/sys/user/add";
    }
	
    @RequestMapping("/saveUser")
    @ResponseBody
    public Object saveUser(SysUser model){
        String password = model.getPassword();
        password = DigestUtils.md5Hex(password);
        model.setPassword(password);
        
        model.setStatus(1);//默认可用
        model.setCreateTime(DateUtil.currentDateTime());
        
        int count = userService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/changeUserStatus")
    @ResponseBody
    public Object changeUserStatus(SysUser model){
        model.setUpdateTime(DateUtil.currentDateTime());
        int count = userService.update(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/safeConfig")
    public String safeConfig(Model model){
        return "/sys/user/safeConfig";
    }
    
    @RequestMapping("/password")
    @ResponseBody
    public Object password(SysUser model){
        String oldPwd = paramMap.get("oldPwd").toString();
        oldPwd = DigestUtils.md5Hex(oldPwd);
        
        Integer userId = (Integer) session.getAttribute("userId");
        SysUser user = userService.findById(userId);
        if(user.getPassword().equals(oldPwd)) {
            model.setUserId(userId);
            model.setUpdateTime(DateUtil.currentDateTime());
            String password = model.getPassword();
            if(!StringUtil.isEmpty(password)) {
                password = DigestUtils.md5Hex(password);
                model.setPassword(password);
            }
            int count = userService.update(model);
            return getResponseState(count);
        }else {
            return getResults("0", "用户输入密码错误", "");
        }
    }
    
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = userService.deleteByIds(ids);
        return getResponseState(count);
    }
	
    @RequestMapping("/grantRole")
    public String grantRole(Integer id, Model model){
        model.addAttribute("userId", id);
        return "/sys/user/grantRole";
    }
    
    @RequestMapping("/saveUserRole")
    @ResponseBody
    public Object saveUserRole(Integer userId, @RequestParam(value = "ids[]")Integer[] ids){
        map.put("userId", userId);
        map.put("ids", ids);
        int count = userRoleService.batchSave(map);
        return getResponseState(count);
    }
    
    @RequestMapping("/userInfo")
    public String userInfo(Model model){
        SysUserInfo userInfo = userInfoService.findByParams(paramMap);
        if(userInfo != null) {
            model.addAttribute("userInfo", userInfo);
        }
        return "/sys/userInfo/edit";
    }
    
    /**
     * 更新基本资料
     * @param model
     * @return
     */
    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public Object saveUserInfo(SysUserInfo model){
        int count = 0;
        Integer userId = (Integer) session.getAttribute("userId");
        model.setUserId(userId);
        
        if(model.getId() == null) {
            count = userInfoService.save(model);
        }else {
            count = userInfoService.update(model);
        }
        return getResponseState(count);
    }
}
