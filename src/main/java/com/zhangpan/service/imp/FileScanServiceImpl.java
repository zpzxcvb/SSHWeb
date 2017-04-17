package com.zhangpan.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zhangpan.service.FileScanService;
import com.zhangpan.util.FileScanUtil;
import com.zhangpan.util.StringUtil;
import com.zhangpan.util.TreeNode;
import com.zhangpan.util.TreeUtil;

@Service
public class FileScanServiceImpl implements FileScanService {

	@Override
	public List<TreeNode> scanProjects(String path) {
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		if(StringUtil.isEmpty(path)){
			path="E:\\Workspaces";
			treeNodes=FileScanUtil.scanFiles(path);
			TreeNode treeNode=TreeUtil.buildRootNode("项目列表", path);
			treeNodes.add(treeNode);
		}else{
			treeNodes=FileScanUtil.scanFiles(path);
		}
		return treeNodes;
	}

}
