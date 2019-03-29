package com.zhangpan.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="用户接口")
@RestController
@RequestMapping("api")
public class DemoController {

	@ApiOperation(value = "test" ,  notes="测试-根据ID查东西")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@RequestMapping(value="/test",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Object test(String id) {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("name", "bill");
		obj.put("age", 20);
		obj.put("sex", "男");
		JSONObject obj1 = new JSONObject();
		obj1.put("语文", 80);
		obj1.put("数据", 90);
		obj.put("subject", obj1);
		return obj.toString();
	}
}
