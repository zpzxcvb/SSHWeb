package com.zhangpan.ssh.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.qtong.base.util.DES;

public class TestUnity {

	public static void Encode() throws Exception {
		//jar包中加密、解密工具类
		DES db = new DES();
		//加解密公钥
		String key = db.getKey();
		String url = "";
			StringBuffer s = new StringBuffer();
			s.append("sysid=ZXGS&sysname=报账平台&syspwd=password");
			s.append("&documentnum=").append("103016122308150242937");
			s.append("&dtype=").append("交通费报账单");
			s.append("&userid=").append("30169");
			s.append("&username=").append("lixiang@cmos.cmcc");
			s.append("&ouname=").append("在线公司总部");
			s.append("&ouid=").append("1130");
			s.append("&linkname=").append("起草");
			s.append("&token=").append(System.currentTimeMillis());
			String urlPatternSrc = URLEncoder.encode(s.toString(),"UTF-8");
			//Encode方法用于加密  
			String urlPatternDes = db.Encode(urlPatternSrc);
			url = url+"value="+urlPatternDes+"&key="+key;
			System.out.println("http://10.1.55.248:9086/ElectronicCertificate/imagesys/scanImageOperUrl.action?"+url);
			//加密后将密文和key都传送给电子影像系统。
			decode(urlPatternDes, key);
	}
	
	//解码
	public static void decode(String value , String key) throws Exception {
		
		//jar包中加密、解密工具类
		DES db = new DES();
		//decode方法用于加密  
		String clearStr = db.Decode(value, key);
		clearStr=URLDecoder.decode(clearStr, "UTF-8");
		System.out.println(clearStr.split("documentnum=")[1].split("&")[0]);
			
	}
	
	public static void main(String[] args) {
		
		try {
			Encode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
