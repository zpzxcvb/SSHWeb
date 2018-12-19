package com.zhangpan.controller.sys;

import java.util.List;
import java.util.Map;

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
import com.zhangpan.model.SysRolePermission;
import com.zhangpan.service.sys.role.SysRoleService;
import com.zhangpan.service.sys.rolePermission.SysRolePermissionService;
import com.zhangpan.util.DateUtil;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    
	@Autowired
	private SysRoleService roleService;
	
	@Autowired
	private SysRolePermissionService rolePermissionService;
	
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
    
    @RequestMapping("/grantMenu")
    public String grantMenu(Integer id, Model model){
        model.addAttribute("roleId", id);
        return "/sys/role/grantMenu";
    }
    
    @RequestMapping("/saveRolePermission")
    @ResponseBody
    public Object saveRolePermission(Integer roleId, @RequestParam(value = "ids[]")Integer[] ids){
        map.put("roleId", roleId);
        map.put("ids", ids);
        //根据角色查询是否有权限
        List<SysRolePermission> list = rolePermissionService.findList(map);
        int count = 0;
        //角色有权限时清空中间关系表，然后再添加关系
        if(list.size()>0) {
            rolePermissionService.deleteRolePermissionByRoleId(roleId);
        }
        count = rolePermissionService.batchSave(map);
        return getResponseState(count);
    }
    
    /**
     * 根据角色查询用户
     * @param roleId
     * @return
     */
    @RequestMapping("/findUserByRoleId")
    @ResponseBody
    public Object findUserByRoleId(Integer roleId) {
        Page<Object> page = roleService.findUserByRoleId(roleId);
        return pageData(page);
    }
    
    /**
     * 根据角色查询权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findPermissionByRoleId")
    @ResponseBody
    public Object findPermissionByRoleId(Integer roleId) {
        List<Map<String, Object>> permissions = roleService.findPermissionByRoleId(roleId);
        
        for(Map<String, Object> map : permissions) {
            map.put("open", true);
        }
        return permissions;
    }
    
    @RequestMapping("/findCheckedPermissionByRoleId")
    @ResponseBody
    public Object findCheckedPermissionByRoleId(Integer roleId) {
        List<Map<String, Object>> permissions = roleService.findCheckedPermissionByRoleId(roleId);
        
        for(Map<String, Object> map : permissions) {
            Object permission_id = map.get("permission_id");
            if(permission_id != null) {
                map.put("checked", true);
            }
            map.put("open", true);
        }
        return permissions;
    }
}
