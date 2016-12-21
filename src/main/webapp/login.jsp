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
    
    <link rel="stylesheet" type="text/css" href="/jsLib/bootstrap/css/bootstrap.css">
    
	<script type="text/javascript" src="/jsLib/jquery.js"></script>
	<script type="text/javascript" src="/jsLib/bootstrap/js/bootstrap.js"></script>
<!-- 	<script type="text/javascript" src="/js/a.js"></script> -->
	
	<script type="text/javascript">
		
	</script>
  </head>
  
  <body>
  	<header>
  		<a>你</a>|<a>我</a>|<a>他</a>|<a href="/report/student.jsp">学生</a>
  	</header>
  
	<form class="form-horizontal" role="form" action="/login.action" method="post">
	  <div class="form-group">
	    <label>用户名：</label>
	    <input type="text" class="form-control" id="name" placeholder="请输入名称">
	  </div>
	  <div class="form-group">
	    <label>密码：</label>
	    <input type="password" class="form-control" id="password" placeholder="请输入密码">
	  </div>
	  	<button type="submit" class="btn btn-default">登录</button>
		<a href="/report/regedit/regedit.jsp" >点击注册</a>	  
	</form>
	<footer>
		<p>@2016 由 张攀 提供  京ICP证10000号</p>
		<p>京公安备 11111111111111111111号</p>
	</footer>
  </body>
</html>
