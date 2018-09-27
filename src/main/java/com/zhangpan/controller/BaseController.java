package com.zhangpan.controller;

import java.util.HashMap;
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
	protected Map<String, Object> map;
	protected ResponseData result;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.paramMap = getParamMap();
		map = new HashMap<String, Object>();
		
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
		    String value = "";
		    String key = param.getKey();
		    String[] values = param.getValue();
		    value = String.join(",", values);
            paramMap.put(key, value);
		}
		System.err.println("requestParams=====>【"+this.paramMap+"】");
		return paramMap;
	}
	
	public ResponseData getResults(String code, String msg, Object JsonData) {
	    result = new ResponseData(code, msg, JsonData);
        return result;
    }
	
	protected ResponseData getResponseState(int count) {
	    ResponseData resp = null;
		if (count > 0) {
		    resp = getResults(Constant.OK, "操作成功", "");
		} else {
		    resp = getResults(Constant.ERROR, "操作失败", "");
		}
		return resp;
	}
	
	protected Map<String, Object> pageData(Page<?> page) {
	    Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", page.getTotal());
	    result.put("data", page.getResult());
        return result;
    }
}
