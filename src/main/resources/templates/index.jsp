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
<div class="">
<div class="top">
	<div class="top_left">
		XXXXXXXXXXX
	</div>
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
</div>
<div class="">
	<div id="grid"></div>
	<div class="fixed-btn">
		<a class="go-top" href="javascript:;" title="返回顶部"><i class="iconfont icon-top"></i></a>
		<a class="qrcode" href="javascript:;" title="关注我"><i class="iconfont icon-erweima"></i></a>
		<div class="zf">
			<img alt="微信支付" src="/resources/img/wxzf.jpg">
			<img alt="支付宝支付" src="/resources/img/zfbzf.jpg">
		</div>
	</div>
</div>



<div class="">
  <div class="">
    <p>
    	@2016 由 张攀 提供  京ICP证10000号
    	<a href="https://www.baidu.com/" target="_blank">百度</a>
    	<a href="https://github.com/zpzxcvb/SSHWeb" target="_blank">Git仓库</a>
    	<a href="mailto:527517062@qq.com">Email：527517062@qq.com</a>
    </p>
	<p>
		<i class="iconfont icon-xiaolian"></i>京公安备 11111111111111111111号
		<input id="color" type="color" name="color" > <input id="choose_color" type="text" value="#000000" readonly="readonly" style="border: 0"/>
	</p>
  </div>
</div>
var add = (function () {
    var counter = 0;
    return function () {return counter += 1;}
})();
  </body>
  <script type="text/javascript">
  $(function(){
  })
  </script>
</html>