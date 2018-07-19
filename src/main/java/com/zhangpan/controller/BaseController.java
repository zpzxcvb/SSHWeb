package com.zhangpan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangpan.constant.Constant;
import com.zhangpan.constant.ResponseData;

public class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Map<String, String> paramMap;
	protected Map<String, Object> result = new HashMap<String, Object>();

	@RequestMapping("/")
	public String index(){
	    request.setAttribute("mainPage", "/user/login.html");
		return "index";
	}
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.paramMap = getParamMap();
	}

	private Map<String, String> getParamMap(){
		Map<String, String> paramMap = new HashMap<String, String>();
		
		Map<String, String[]> params = request.getParameterMap();
		
		String value = "";
		for(Map.Entry<String, String[]> param : params.entrySet()) {
		    String key = param.getKey();
		    String[] values = param.getValue();
		    for (int i = 0; i < values.length; i++) {
                value += values[i] + ",";
            }
            value = value.substring(0, value.lastIndexOf(","));
            paramMap.put(key, value);
		}
		System.out.println(this.paramMap);
		return paramMap;
	}
	
	public ResponseData setData(String code, String msg, String data) {
        ResponseData result = new ResponseData(code, msg, data);
        return result;
    }
	
	protected String getResponseCode(int count) {
		String code = "";

		if (count > 0) {
			code = Constant.SUCCESS;
		} else {
			code = Constant.ERROR;
		}
		
		return code;
	}
}
