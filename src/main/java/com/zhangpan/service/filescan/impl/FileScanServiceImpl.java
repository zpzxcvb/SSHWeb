package com.zhangpan.service.filescan.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zhangpan.service.filescan.FileScanService;
import com.zhangpan.util.FileUtil;
import com.zhangpan.util.StringUtil;
import com.zhangpan.util.TreeNode;

@Service
public class FileScanServiceImpl implements FileScanService {

	@Override
	public List<TreeNode> scanProjects(String path) {
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		if(StringUtil.isEmpty(path)){
			path=System.getProperty("user.dir");
			treeNodes=FileUtil.scanFiles(path);
			TreeNode treeNode=buildRootNode("项目列表", path);
			treeNodes.add(treeNode);
		}else{
			treeNodes=FileUtil.scanFiles(path);
		}
		return treeNodes;
	}
	
	private TreeNode buildRootNode(String rootName,String path){
        TreeNode treeNode=new TreeNode();
        treeNode.setName(rootName);
        treeNode.setPath(path);
        treeNode.setOpen(true);
        treeNode.setParent(true);
        treeNode.setId(path);
        return treeNode;
    }
}
