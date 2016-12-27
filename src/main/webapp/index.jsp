<%@page import="java.util.Date"%>
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
	欢迎您，${username }, 今天是<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm:ss E"/>
	<frameset rows="98,*,5" frameborder="no">
   <frame src="top.jsp" name="topFrame" id="topFrame"/>
    <frameset cols="168,*"  frameborder="no">
           <frame src="left.jsp"id="leftFrame" />
           <frame src="main.jsp" id="mainFrame" scrolling="yes"/>
   </frameset>
   <frame src="down.jsp" name="bottomFrame"id="bottomFrame" />
   </frameset>
</body>
</html>