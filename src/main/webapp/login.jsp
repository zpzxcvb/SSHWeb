<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录</title>
    <%@ include file="/common/common.jsp"%>
  </head>
  
  <body>
<!--   	<%@ include file="/common/head.jsp"%> -->

	<form class="layui-form" action="/user/login" method="post">
	<textarea class="layui-textarea" id="demo" >  
  把编辑器的初始内容放在这textarea即可
</textarea>
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户名：</label>
	  	<div class="layui-input-block">
		    <input class="layui-input" type="text"  id="username" name="username" placeholder="请输入名称">
	  	</div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">密码：</label>
	    <div class="layui-input-block">
	    	<input class="layui-input" type="password"  id="password" name="password" placeholder="请输入密码">
	    </div>
	  </div>
	  <div class="layui-input-block">
	  	<button class="layui-btn" type="submit">登录</button>
	  	<button class="layui-btn" type="button" id="test">test</button>
      </div>
<!-- 		<a href="/regedit/regedit.jsp" class="layui-btn">点击注册</a>	   -->
	</form>
	<!-- <footer>
		<p>@2016 由 张攀 提供  京ICP证10000号</p>
		<p>京公安备 11111111111111111111号</p>
	</footer> -->
  </body>
  <script type="text/javascript">
  $(function(){
	  layui.use('layedit', function(){
		  var layedit = layui.layedit;
		  var index=layedit.build('demo'); //建立编辑器
		  var active = {
		    content: function(){
		      alert(layedit.getContent(index)); //获取编辑器内容
		    }
		  };
		});
	  $("#test").click(function(){
		  alert(layedit.getContent(index));
	  })
  })
  </script>
</html>
