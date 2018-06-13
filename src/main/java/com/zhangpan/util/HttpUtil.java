package com.zhangpan.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Http工具类
 */
public class HttpUtil {
    
    private static final int timeOut = 15000;//超时时间
    private static final String charset = "UTF-8";
    
    /**
     * HTTP Get
     * @param url localhost:8080?name=tom&pwd=123
     * @return
     * @throws Exception
     */
    public static String httpGet(String url) {
        return httpGet(url, null);
    }
    
    /**
     * HTTP Get
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpGet(String url, Map<String, String> params) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 构造参数
            URIBuilder builder=new URIBuilder(url);
            if(params != null) {
                for (Map.Entry<String, String> e : params.entrySet()) {
                    builder.setParameter(e.getKey(), e.getValue());
                } 
            }
            System.out.println(builder.build());
            HttpGet httpGet = new HttpGet(builder.build());
            //设置超时时间
            RequestConfig requestConfig = getRequestConfig();
            httpGet.setConfig(requestConfig);
            
            CloseableHttpResponse response = httpClient.execute(httpGet);
            result = getResponseResult(response);
            System.err.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * HTTP POST jsonParams
     * @param url
     * @param jsonParams
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, String jsonParams) {
        return httpPost(url, null, jsonParams);
    }
    
    /**
     * HTTP POST表单
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Map<String, String> params) {
        return httpPost(url, null, params);
    }
    
    /**
     * HTTP POST表单
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Map<String, String> headers, Object params) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            //设置超时时间
            RequestConfig requestConfig = getRequestConfig();
            post.setConfig(requestConfig);
            
            // 构造消息头
            if(headers != null) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    post.setHeader(e.getKey(), e.getValue());
                }
            }
            HttpEntity entity = null;
            // 构造消息体
            if(params instanceof String) {
                String jsonStr = (String) params;
                post.setHeader("Content-Type", "application/json");
                entity = new StringEntity(jsonStr, charset);
            }else if(params instanceof Map) {
                Map<String, String> formParam = (Map<String, String>) params;
                entity = buildFormEntity(formParam);
            }
            if (entity != null) {
                post.setEntity(entity);
            }
            CloseableHttpResponse response = httpClient.execute(post);
            
            result = getResponseResult(response);
            System.err.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                result = "请求连接失败。";
                e.printStackTrace();
            }
        }
        return result;
    }
    
    private static RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeOut)   //设置连接超时时间
                .setConnectionRequestTimeout(timeOut) // 设置请求超时时间
                .setSocketTimeout(timeOut)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        return requestConfig;
    }
    
    /**
     * 构建FormEntity
     * 
     * @param formParam
     * @return
     * @throws UnsupportedEncodingException
     */
    private static UrlEncodedFormEntity buildFormEntity(Map<String, String> formParam) {
        UrlEncodedFormEntity formEntity = null;
        try {
            if (formParam != null) {
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

                for (Map.Entry<String, String> entry : formParam.entrySet()) {
                    nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                formEntity = new UrlEncodedFormEntity(nameValuePairList, charset);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return formEntity;
    }
    
    private static String getResponseResult(HttpResponse response) {
        String result = "";
        if(response != null){
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    try {
                        result = EntityUtils.toString(resEntity, charset);
                    } catch (ParseException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        
    }
}