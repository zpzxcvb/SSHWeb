package com.zhangpan.ssh.test;

import java.util.List;

import com.zhangpan.util.StringUtil;

public class Test {
	private int ab;
	
	public static void main(String[] args) {
		Test t=new Test(); 
		
//		Pattern p=Pattern.compile("href=\"(.+?)\"");
//		Matcher m=p.matcher("＜a href=\"index.html\"＞我的主页＜/a＞");
		String s="<img src=\"1.jpg\" /><img src=\"2.jpg\" />";
		List<String> list=StringUtil.regexStr("\\d+", s);
		System.out.println(list);
	}
}
