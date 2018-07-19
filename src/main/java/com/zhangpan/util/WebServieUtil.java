package com.zhangpan.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @描述：接口工具类
 * @author Administrator
 * 
 */
public class WebServieUtil {
	/**
	 * 访问远程接口返回json字符串
	 * 
	 * @url 接口地址(url?wsdl格式)
	 * @methodName 接口方法
	 * @xmlParam 请求参数(xml格式)
	 *            
	 * @return result 接口返回json字符串
	 */
	public static String getUrlResponse(String url, String methodName, String xmlParam) {
		String result = "";
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			
	        Client client = dcf.createClient(url);
	        
	        Object[] objects = client.invoke(methodName, xmlParam);
	         
            result = objects[0].toString();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
	    String url="https://localhost/gatms_interface/service/demo?wsdl";
	    String methodName="getUsers";
	    String xmlParam="";
	    String result = getUrlResponse(url, methodName, xmlParam);
	    System.out.println(result);
    }

}
