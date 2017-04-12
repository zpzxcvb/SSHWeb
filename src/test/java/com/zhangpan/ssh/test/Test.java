package com.zhangpan.ssh.test;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.zhangpan.util.StringUtil;

public class Test {
	 private static final String HEX_NUMS_STR="0123456789ABCDEF";
	 private static final Integer SALT_LENGTH = 12;
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		Test t=new Test(); 
		String str="admin";
		System.out.println("原字符:"+str);
		BASE64Encoder enc=new BASE64Encoder();
		String s=enc.encode(str.getBytes());
		System.out.println("加密:"+s);
		
		BASE64Decoder dec=new BASE64Decoder();
		String ss=new String(dec.decodeBuffer(s),"UTF-8");
		System.out.println("解密:"+ss);
		
		MessageDigest digest = MessageDigest.getInstance("md5");
		digest.update(str.getBytes("UTF-8"));
		byte[] result = digest.digest();
		StringBuffer buffer = new StringBuffer();
		for (byte b : result) {  
            // 与运算  
            int number = b & 0xff;// 加盐  
            String sss = Integer.toHexString(number);  
            if (sss.length() == 1) {  
                buffer.append("0");  
            }  
            buffer.append(sss);  
        }
		System.out.println(buffer.toString());
		
	}
	
}
