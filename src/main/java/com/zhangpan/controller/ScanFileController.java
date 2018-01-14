package com.zhangpan.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangpan.service.FileScanService;
import com.zhangpan.util.FileScanUtil;
import com.zhangpan.util.TreeNode;

@Controller
@RequestMapping("/file")
public class ScanFileController extends BaseController {
	
	@Autowired
	private FileScanService fileScanService;
	
	@RequestMapping("/scan")
	public String scanProjects() {
		return "scanProjects/scanProjects";
	}
	
	@RequestMapping("/scanProjects")
	@ResponseBody
	public List<TreeNode> scanProjects(String path){
		log.info(path+"------------");
		List<TreeNode> treeNodes=fileScanService.scanProjects(path);
		return treeNodes;
	}
	@RequestMapping("/readFile")
	@ResponseBody
	public  List readFile(String path) throws Exception{
		System.err.println(path);
		List list=FileScanUtil.readFile(path);
		return list;
	}
	
	private static final Logger log = Logger.getLogger(ScanFileController.class);
}
