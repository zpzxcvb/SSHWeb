package com.zhangpan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhangpan.util.SignUtil;

/**
 * 第三方系统接入
 * 
 * @author zp
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController extends BaseController {
	
	@ResponseBody
	@RequestMapping(value = "/test")
	public Object test() throws Exception {
		boolean bool=true;
		String username=request.getParameter("username");
		String clientId=request.getParameter("clientId");
		String time=request.getParameter("time");
		String sign=request.getParameter("sign");
		String appKey=request.getParameter("appKey");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("clientId", clientId);
		param.put("time", time);
		param.put("sign", sign);
		param.put("appKey", appKey);
		Map<String,Object> m = SignUtil.verify(param);
		String code = m.get("code").toString();
		String msg = m.get("msg").toString();
		if("0".equals(code)) {
			ModelAndView mode = new ModelAndView("/file/scan");
	        mode.addObject("result", result);
	        return mode;
		}else {
			result.put("msg", msg);
			return result;
		}
		
	}
}