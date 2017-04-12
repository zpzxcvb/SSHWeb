package com.zhangpan.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangpan.dao.SysDictDao;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.SysDictService;
import com.zhangpan.util.TreeNode;

@Service
public class SysDictServiceImp extends BaseServiceImp<SysDictType,Integer> implements SysDictService {
	
	@Resource
	private SysDictDao sysDictDao;

	@Override
	public TreeNode showSysDictType() {
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		List<SysDictType> list=sysDictDao.findAll();
		TreeNode root=new TreeNode();
		root.setId("");
		root.setName("类别管理");
		root.setOpen(true);
		root.setChildren(treeNodes);
		for(SysDictType dicType : list){
			TreeNode tree=new TreeNode();
			tree.setId(dicType.getDictValue());
			tree.setName(dicType.getDictName());
			treeNodes.add(tree);
		}
		return root;
	}

}
