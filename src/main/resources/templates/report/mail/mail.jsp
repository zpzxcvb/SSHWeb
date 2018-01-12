<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<script type="text/javascript" src="/jsLib/QUI/libs/js/jquery.js"></script>


</head>
<body>
	<input id="btn" type="button" value="Export">
</body>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			$.ajax({
				url:
			});
		})
	})
</script>
</html>