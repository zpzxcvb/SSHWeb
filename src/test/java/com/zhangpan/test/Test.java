package com.zhangpan.test;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {
	
	public static void main(String[] args) throws Exception {
	    String str="0";
	    String md5pwd = DigestUtils.md5Hex(str.toString().getBytes("utf-8")).toUpperCase();
	    System.out.println(md5pwd);
	}
	
}
