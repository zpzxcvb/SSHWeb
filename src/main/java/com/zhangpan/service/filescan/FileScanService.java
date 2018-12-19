package com.zhangpan.service.filescan;

import java.util.List;

import com.zhangpan.util.TreeNode;

public interface FileScanService{

	/**
	 * 扫描项目列表
	 * @return
	 */
	public List<TreeNode> scanProjects(String path);
	
}
