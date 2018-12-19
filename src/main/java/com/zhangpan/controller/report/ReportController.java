package com.zhangpan.controller.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.controller.BaseController;
import com.zhangpan.service.sys.user.SysUserService;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/chart/list")
    public String chartlist() {
        return "report/chart/chartlist";
    }
	
}
