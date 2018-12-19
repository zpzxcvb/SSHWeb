package com.zhangpan.controller.sys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysInterface;
import com.zhangpan.service.sys.interfaceManager.SysInterfaceService;
import com.zhangpan.util.DateUtil;

@Controller
@RequestMapping("/sys/interface")
public class SysInterfaceController extends BaseController {
    
	@Autowired
	private SysInterfaceService interfaceService;
	
	@RequestMapping("/list")
    public String list(){
        return "/sys/interface/list";
    }
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Object pageList(){
		Page<Object> page = interfaceService.findPage(paramMap);
        return pageData(page);
	}
	
	@RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Integer id, Model model){
	    if(id != null) {
	        SysInterface obj = interfaceService.findById(id);
            model.addAttribute("obj", obj);
        }
        return "/sys/interface/edit";
    }
	
	@RequestMapping("/findById")
	@ResponseBody
    public Object findById(Integer id){
	    SysInterface obj = interfaceService.findById(id);
        return obj;
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public Object save(SysInterface model){
        model.setCreateTime(new Date());
        int count = interfaceService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public Object update(SysInterface model){
        int count = interfaceService.update(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = interfaceService.deleteByIds(ids);
        return getResponseState(count);
    }
    
}
