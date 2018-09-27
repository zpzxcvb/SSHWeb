package com.zhangpan.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysRole;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.role.SysRoleService;
import com.zhangpan.util.DateUtil;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    
	@Autowired
	private SysRoleService roleService;
	
	@RequestMapping("/list")
    public String list(){
        return "/sys/role/list";
    }
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Object pageList(){
		Page<Object> page = roleService.findPage(paramMap);
        return pageData(page);
	}
	
	@RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Integer id, Model model){
	    if(id != null) {
	        SysRole role = roleService.findById(id);
            model.addAttribute("role", role);
        }
        return "/sys/role/edit";
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public Object save(SysRole model){
        model.setCreateTime(DateUtil.currentDateTime());
        int count = roleService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public Object update(SysRole model){
        int count = roleService.update(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = roleService.deleteByIds(ids);
        return getResponseState(count);
    }
    
    @RequestMapping("/findUserByRoleId/{roleId}")
    @ResponseBody
    public Object findUserByRoleId(@PathVariable("roleId") Integer roleId) {
        Page<Object> page = roleService.findUserByRoleId(roleId);
        return pageData(page);
    }
}
