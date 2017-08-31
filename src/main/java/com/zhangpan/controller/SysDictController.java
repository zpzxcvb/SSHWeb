package com.zhangpan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhangpan.util.TreeNode;

@Controller
@RequestMapping("/dict")
public class SysDictController extends BaseController {
	
	
	@RequestMapping("/showSysDictType")
	@ResponseBody
	public List<TreeNode> showSysDictType(String dictType){
		System.out.println(this.request.getParameterMap());
		List<TreeNode> nodes=null;//sysDictService.showSysDictType();
		return nodes;
	}
}
