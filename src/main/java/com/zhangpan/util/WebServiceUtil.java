package com.zhangpan.util;

//import org.apache.cxf.endpoint.Client;
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @描述：接口工具类
 * @author Administrator
 * 
 */
public class WebServiceUtil {
    /**
     * 访问远程接口
     * @url 接口地址(url?wsdl格式)
     * @methodName 接口方法
     * @params new Object[]{"x","x"}
     * @return result
     */
    public static String invoke(String url, String methodName, Object... params) {
		String result = "";
		try {
//			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//			
//	        Client client = dcf.createClient(url);
//	        
//	        Object[] objects = client.invoke(methodName, params);
//	         
//            result = objects[0].toString();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
	    String url="http://www.webxml.com.cn/webservices/ChinaTVprogramWebService.asmx?wsdl";
	    String methodName="getAreaDataSet";
	    String xmlParam="<qqCode>3412003909</qqCode>";
	    String result = invoke(url, methodName, xmlParam);
	    System.out.println(result);
    }

}
