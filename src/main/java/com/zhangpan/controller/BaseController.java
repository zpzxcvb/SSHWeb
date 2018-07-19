package com.zhangpan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
		
        if(paramMap.get("pageNum") != null && paramMap.get("pageSize") != null) {
            int pageNum = Integer.parseInt(paramMap.get("pageNum"));
            int pageSize = Integer.parseInt(paramMap.get("pageSize"));
            
            PageHelper.startPage(pageNum, pageSize);
        }
	}

	private Map<String, String> getParamMap(){
		paramMap = new HashMap<String, String>();
		
		Map<String, String[]> params = request.getParameterMap();
		
		for(Map.Entry<String, String[]> param : params.entrySet()) {
		    String key = param.getKey();
		    String[] values = param.getValue();
            paramMap.put(key, values[0]);
		}
		System.out.println("requestParams=====>【"+this.paramMap+"】");
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
	
	protected Map<String, Object> pageData(Page<?> page) {
        result.put("status", 0);
        result.put("msg", "");
        result.put("total", page.getTotal());
	    result.put("data", page.getResult());
        return result;
    }
}
