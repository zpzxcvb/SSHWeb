package com.zhangpan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.sound.sampled.AudioInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zhangpan.constant.Constant;
import com.zhangpan.util.HttpUtil;
import com.zhangpan.util.SignUtil;

/**
 * 播放音频
 * 
 * @author zp
 */
@Controller
@RequestMapping(value = "/audio")
public class AudioController extends BaseController {
    
    @RequestMapping(value = "/video")
    public String video() throws Exception {
        return "video";
    }
	
    @RequestMapping(value = "/audioForm")
    public String audioForm(String content) throws Exception {
        return "audio";
    }
    
    @RequestMapping(value = "/audiomp3")
    public void audiomp3(String content, String voice_name) throws Exception {
        InputStream is = getAudio(content, voice_name);
        FileOutputStream os = new FileOutputStream("temp.wav");
        ServletOutputStream out = this.response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len=is.read(buffer))!=-1) {
            out.write(buffer,0,len);
            os.write(buffer,0,len);
        }
        is.close();
        os.close();
        out.close();
    }
    
	public InputStream getAudio(String content, String voice_name) throws Exception {
        String path="";
	    InputStream is = null;
	    String appId = Constant.X_APPID;
        String apiKey = Constant.X_TTS_APPKEY;
        String url = Constant.X_TTS_URL;
        String curTime = System.currentTimeMillis()/1000+"";
        JSONObject obj = new JSONObject();
        obj.put("auf", "audio/L16;rate=16000");//音频采样率
        obj.put("aue", "raw");//音频编码
        obj.put("voice_name", voice_name);//发音人
        obj.put("speed", "50");//语速
        String xParam = Base64.encodeBase64String(obj.toJSONString().getBytes("UTF-8"));
        
        String checkSum = apiKey + curTime + xParam;
        String md5 = DigestUtils.md5Hex(checkSum.getBytes("UTF-8"));
        
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("X-Appid", appId);
        headers.put("X-CurTime", curTime);
        headers.put("X-Param", xParam);
        headers.put("X-CheckSum", md5);
        
        System.err.println(headers);
        
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("text", content);
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            // 构造消息头
            if(headers != null) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    post.setHeader(e.getKey(), e.getValue());
                }
            }
            // 构造消息体
            UrlEncodedFormEntity formEntity = buildFormEntity(bodys);
            if (formEntity != null) {
                post.setEntity(formEntity);
            }
            HttpResponse response = httpClient.execute(post);
            
            if(response != null){
                // 检验返回码
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK) {
                    HttpEntity resEntity = response.getEntity();
                    if(resEntity != null){
                        is = resEntity.getContent();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            
        }
        return is;
	}
	private static UrlEncodedFormEntity buildFormEntity(Map<String, String> formParam) {
        UrlEncodedFormEntity formEntity = null;
        try {
            if (formParam != null) {
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

                for (Map.Entry<String, String> entry : formParam.entrySet()) {
                    nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                formEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return formEntity;
    }
}