package com.zhangpan.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		BigInteger b=new BigInteger("4860986780584199407289852147499046803150");
		b=b.setBit(38);
		System.out.println(b.toString());
		System.out.println(b.testBit(38));
		
		/*
		url=URLEncoder.encode(url, "utf-8");
//		url=java.util.Base64.getEncoder().encodeToString(url.getBytes());
		url=Base64.encodeBase64String("it".getBytes());
//		url=Base64.encodeBase64URLSafeString(url.getBytes());
		System.out.println(url);
		url=new String(java.util.Base64.getDecoder().decode(url));
		url=URLDecoder.decode(url, "utf-8");
		System.out.println(url);
		byte[] b ="P".getBytes();
		System.out.println(Integer.toBinaryString(b[0]));*/
	}
}
