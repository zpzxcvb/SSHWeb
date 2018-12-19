package com.zhangpan.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.service.sys.log.SysLogService;
import com.zhangpan.util.DateUtil;

@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
    
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("/list")
    public String list(){
        return "/sys/log/list";
    }
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Object pageList(){
	    Object createTime = paramMap.get("createTime");
	    if(createTime != null) {
	        String endTime = DateUtil.sumDay(createTime.toString(), 1);
	        paramMap.put("endTime", endTime);
	    }
		Page<Object> page = sysLogService.findPage(paramMap);
        return pageData(page);
	}
}
