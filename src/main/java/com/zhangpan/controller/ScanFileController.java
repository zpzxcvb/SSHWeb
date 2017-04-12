package com.zhangpan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.SysDictService;
import com.zhangpan.service.SysUserService;
import com.zhangpan.task.HelloTask;
import com.zhangpan.util.FileScanUtil;
import com.zhangpan.util.StringUtil;
import com.zhangpan.util.TreeNode;

@Controller
@RequestMapping("/file")
public class ScanFileController extends BaseController {
	
	@RequestMapping("/scanProjects")
	public @ResponseBody List scanProjects(String path){             
		if(StringUtil.isEmpty(path)){
			path="E:\\Workspaces";
		}
		System.err.println(path);
		List list=FileScanUtil.scanFiles(path);
		return list;
	}
	@RequestMapping("/readFile")
	public @ResponseBody List readFile(String path) throws Exception{
		System.err.println(path);
		List list=FileScanUtil.readFile(path);
		return list;
	}
}
