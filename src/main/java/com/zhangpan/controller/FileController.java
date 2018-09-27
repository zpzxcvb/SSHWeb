package com.zhangpan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhangpan.service.FileScanService;
import com.zhangpan.util.FileUtil;
import com.zhangpan.util.TreeNode;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    
    private static final Logger log = Logger.getLogger(FileController.class);
	
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
		List list=FileUtil.readFile(path);
		return list;
	}
	
	@RequestMapping("/upLoad")
	@ResponseBody
    public Object upLoad(MultipartFile file) throws IllegalStateException, IOException {
	    if(file.isEmpty()){
            return false;
        }
        String path = ResourceUtils.getURL("classpath:").getPath();
        File dest = new File(path, "static/upload/" + file.getOriginalFilename());
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        file.transferTo(dest);
        map.put("src", "/upload/"+file.getOriginalFilename());
        return getResults("0", "", map);
    }
	
	@RequestMapping("/downLoad")
    public String downLoad() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath()+"upload/image";
        FileUtil.downLoad(this.response, path, "me.jpg");
        return null;
    }
}
