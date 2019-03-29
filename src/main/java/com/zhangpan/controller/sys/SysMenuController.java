package com.zhangpan.controller.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysMenu;
import com.zhangpan.service.sys.menu.SysMenuService;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
	
    @Autowired
    private SysMenuService sysMenuService;
	
    @RequestMapping("/list")
    public String list(){
        return "/sys/menu/list";
    }
    
    @RequestMapping("/pageList")
    @ResponseBody
    public Object pageList(){
        Page<Object> page = sysMenuService.findPage(paramMap);
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
    public String saveOrUpdate(Integer id, Model model){
	    if(id != null) {
	        SysMenu menu = sysMenuService.findById(id);
	        model.addAttribute("menu", menu);
	    }
        return "/sys/menu/edit";
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
		boolean bool = true;
		for(int id : ids) {
			int hasChild = sysMenuService.hasChild(id);
			if(hasChild > 0) {
				bool = false;
				break;
			}
		}
		if(bool) {
			int count = sysMenuService.deleteByIds(ids);
			return getResponseState(count);
		} else {
			return getResults("-1", "删除失败，请检查所选项是否包含子目录。");
		}
    }
	
	/**
	 * 查询所有节点
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/menuAll")
    @ResponseBody
	public Object listAllMenu() throws Exception {
	    //根节点id=1
	    List<SysMenu> listAllMenu = sysMenuService.listAllMenu(1);
        return listAllMenu;
    }
}
