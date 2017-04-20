<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common.jsp"%>
</head>

<body>
	<!-- 导航 -->
	<div class="header">
		<div class="top_right">
			<a href="javascript:;"><i class="iconfont icon-USD iconfont-rotate"></i></a>
			欢迎您，${empty username?'访客':username }, 今天是<span id="currentTime""></span>
			<a class="top_mulu" href="javascript:;"><i class="iconfont icon-mulu"></i></a>
			<div class="mulu_menu">
				<a href="">登录</a>
				<a href="">注册</a>
				<a href="">建议留言</a>
			</div>
		</div>
	</div>
	
	<!-- 内容 -->
	<div class="content">
		
	</div>
	

    <!-- 左下角按钮 -->
    <div class="fixed-btn">
		<a class="go-top" href="javascript:window.scroll(0,0);" title="返回顶部"><i class="iconfont icon-top"></i></a>
		<a class="qrcode" href="javascript:;" title="关注我"><i class="iconfont icon-erweima"></i></a>
		<div class="zf-icon">
			<div class="panel-body">
				<img alt="微信支付" src="/resources/img/wxzf.jpg">
				<img alt="支付宝支付" src="/resources/img/zfbzf.jpg">
			</div>
		</div>
	</div>
	<!-- 底部 -->
	<div class="footer">
	    <div class="footer-bar">
	    	Copyright © 2017-2217 <a href="https://localhost:80/" target="_blank">网站</a>由 张攀 提供.All Rights Reserved
	    	<a href="https://www.baidu.com/" target="_blank">百度</a>
	    	<a href="https://github.com/zpzxcvb/SSHWeb" target="_blank">GIT</a>
	    	<a href="mailto:527517062@qq.com">Email</a>
	    </div>
		<span>
			<i class="iconfont icon-xiaolian"></i>备案号：京ICP备10000号-1
			<input id="color" type="color" name="color" > <input id="choose_color" type="text" value="#000000" readonly="readonly" style="border: 0"/>
		</span>
    </div>
</body>
</html>