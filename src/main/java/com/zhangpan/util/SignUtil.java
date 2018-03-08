package com.zhangpan.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

public class SignUtil {
	
	private static final String appKey = "jcxxfb";//第三方标识
	private static final String appSecret = "123";//密钥
	private static final long time = 5;//有效期(秒)
	
	public static Map<String, Object> verify(Map<String, Object> param) {
		String status="";
		String code="";
		String msg="";
		String req_appKey=param.get("appKey").toString();
		String req_sign=param.get("sign").toString();
		
		long req_time = Long.parseLong(param.get("time").toString());
		
		long currentTime = System.currentTimeMillis();
		
		if(req_appKey.equals(appKey)) {
			if((currentTime-req_time)/1000 < time) {
				//根据请求参数生成签名
				String sign = createSign(param);
				//和请求参数的签名做对比
				if(req_sign.equals(sign)) {
					status="success";
					code="0";
					msg="验签成功";
					
				}else {
					code="205";
					msg="验签失败";
				}
			}else {
				code="204";
				msg="超过有效期";
			}
			
		}else {
			code="203";
			msg="系统标识不匹配";
		}
		
		if(!code.equals("0")) {
			status="error";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	
	public static String createSign(Map<String, Object> param) {
		String sign="";
		//对请求参数升序排序
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>(param);
		StringBuffer sb = new StringBuffer();
        //把map中的集合拼接成字符串
        for(Map.Entry<String, Object> entry:treeMap.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if("sign".equals(key) || value == null || "".equals(value)) {
            	continue;
            }
            sb.append(key+value);
        }
        System.out.println("排序后的参数："+sb.toString());
        sb.insert(0, param.get("appSecret"));
        System.out.println("拼接密钥后的字符："+sb.toString());
        try {
        	//进行MD5加密，然后转换大写
			sign = DigestUtils.md5Hex(sb.toString().getBytes("utf-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        System.out.println("加密后的签名："+sign);
		return sign;
	}
	
	public static void main(String[] args) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("username", "test");
			param.put("clientKey", "tht");
			param.put("time", System.currentTimeMillis());
			param.put("appKey", "jcxxfb");
//			param.put("appSecret", "123");
			String sign = createSign(param);
			
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, Object> entry:param.entrySet()){
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            sb.append(key+"="+value+"&");
	        }
			sb.append("sign="+sign);
			System.out.println(sb.toString());
			param.put("sign", sign);
			Map<String,Object> m = verify(param);
			System.out.println(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
