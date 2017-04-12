<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>test</title>
    <%@ include file="/common/common.jsp"%>
    
    <script type="text/javascript">
    	$(function(){
    		$.post('/user/login/1',function(data){
	   			alert(data);
    		});
    	})
    </script>
  </head>
  
  <body>
  	11
  </body>
</html>
