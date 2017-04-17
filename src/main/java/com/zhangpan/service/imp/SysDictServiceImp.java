package com.zhangpan.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangpan.dao.SysDictDao;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.SysDictService;
import com.zhangpan.util.TreeNode;
import com.zhangpan.util.TreeUtil;

@Service
public class SysDictServiceImp extends BaseServiceImp<SysDictType,Integer> implements SysDictService {
	
	@Resource
	private SysDictDao sysDictDao;

	@Override
	public List<TreeNode> showSysDictType() {
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		TreeNode treeNode=TreeUtil.buildRootNode("字典列表", "0");
		treeNodes.add(treeNode);
		List<SysDictType> list=sysDictDao.findAll();
		for(SysDictType dicType : list){
			TreeNode tree=new TreeNode();
			tree.setId(dicType.getDictValue());
			tree.setName(dicType.getDictName());
			tree.setPid("0");
			treeNodes.add(tree);
		}
		return treeNodes;
	}

}
