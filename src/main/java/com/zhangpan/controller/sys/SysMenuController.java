package com.zhangpan.controller.sys;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysMenu;
import com.zhangpan.service.sys.menu.SysMenuService;
import com.zhangpan.util.FileUtil;
import com.zhangpan.util.TreeNode;

@Controller
@RequestMapping("/menu")
public class SysMenuController extends BaseController {
	
    @Autowired
    private SysMenuService sysMenuService;
	
    @RequestMapping("/list")
    public String list(String pid){
        return "/sys/menu/list";
    }
    
    @RequestMapping("/pageList")
    @ResponseBody
    public Object pageList(){
        Page<SysMenu> page = sysMenuService.findPage(paramMap);
        return pageData(page);
    }
    
	@RequestMapping("/findList")
	@ResponseBody
	public Object findList(){
		List<SysMenu> menus=sysMenuService.findList(paramMap);
		return menus;
	}
	
	@RequestMapping("/menuList")
    @ResponseBody
    public Object menuList(){
        List<Map<String, Object>> menus=sysMenuService.menuList();
        for(Map<String, Object> map : menus) {
            int pid = Integer.valueOf(map.get("pid").toString());
            int id = Integer.valueOf(map.get("id").toString());
            if(pid == 0) {
                map.put("open", true);
            }
        }
        
        return menus;
    }
	
	@RequestMapping("/saveOrUpdate")
    public String model(Integer id, Model model){
	    if(id != null) {
	        Object menu = sysMenuService.findById(id);
	        model.addAttribute("menu", menu);
	    }
        return "/sys/menu/model";
    }
	
	@RequestMapping("/save")
	@ResponseBody
    public Object save(SysMenu model){
	    int count = sysMenuService.save(model);
        return getResponseState(count);
    }
	
	@RequestMapping("/update")
    @ResponseBody
    public Object update(SysMenu model){
        int count = sysMenuService.update(model);
        return getResponseState(count);
    }
	
	@RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = sysMenuService.deleteByIds(ids);
        return getResponseState(count);
    }
	
	@RequestMapping("/downLoad")
	public String downLoad() throws FileNotFoundException {
	    String path = ResourceUtils.getURL("classpath:").getPath()+"upload/image";
	    FileUtil.downLoad(this.response, path, "me.jpg");
	    return null;
	}
}
