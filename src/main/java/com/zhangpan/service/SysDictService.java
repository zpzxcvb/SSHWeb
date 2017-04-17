package com.zhangpan.service;

import java.util.List;

import com.zhangpan.model.SysDictType;
import com.zhangpan.util.TreeNode;

public interface SysDictService extends BaseService<SysDictType,Integer>{

	/**
	 * 显示字典类型
	 * @return
	 */
	public List<TreeNode> showSysDictType();
	
}
