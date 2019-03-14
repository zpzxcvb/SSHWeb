package com.zhangpan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhangpan.constant.Constant;
import com.zhangpan.service.filescan.FileScanService;
import com.zhangpan.util.FileUtil;
import com.zhangpan.util.TreeNode;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    
    private static final Logger log = Logger.getLogger(FileController.class);
    
    @Value("${upLoadPath}")
    private String upLoadPath;
	
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
	        return getResults("-1", "上传文件为空");
        }
	    String fileType = file.getContentType();   //文件类型
	    long fileSize = file.getSize();   //文件大小
        String fileName = file.getOriginalFilename();  //图片名字
        
	    File dir = new File(upLoadPath);
	    if(!dir.exists()){//判断文件目录是否存在
	        dir.mkdir();
        }
        File dest = new File(upLoadPath, fileName);
        try {
            file.transferTo(dest);
            String downLoadUrl = dest.getAbsolutePath();//返回上传文件路径
            map.put("src", downLoadUrl);
            return getResults("0", "上传成功", map);
        } catch (Exception e) {
            return getResults("-1", "上传失败");
        }
    }
	
	@RequestMapping("/downLoad")
    public String downLoad() throws FileNotFoundException {
        return null;
    }
}
