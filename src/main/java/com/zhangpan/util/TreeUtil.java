package com.zhangpan.util;

public class TreeUtil {

	public static TreeNode buildRootNode(String rootName,String id){
		TreeNode treeNode=new TreeNode();
		treeNode.setName(rootName);
		treeNode.setPath(id);
		treeNode.setOpen(true);
		treeNode.setParent(true);
		treeNode.setId(id);
		return treeNode;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
