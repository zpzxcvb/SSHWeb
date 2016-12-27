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
<%@ include file="/common/common.jsp"%>
</head>
<body>
<input id="beginDate" class="layui-input" placeholder="开始日期" onclick="layui.laydate({elem:this})">
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th>班级</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>xxxx</td>
			<td>11</td>
			<td>男</td>
			<td>1</td>
		</tr>
		<tr>
			<td>2</td>
			<td>xxxx</td>
			<td>12</td>
			<td>女</td>
			<td>1</td>
		</tr>
		<tr>
			<td>3</td>
			<td>xxxx</td>
			<td>13</td>
			<td>男</td>
			<td>2</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th>总计：</th>
			<th colspan="4">xxxx</th>
		</tr>
		<tr>
			<th colspan="5">xxxx</th>
		</tr>
	</tfoot>
</table>
<div>
	<ul class="pagination">
	    <li><a href="#">&laquo;</a></li>
	    <li class="active"><a href="#">1</a></li>
	    <li><a href="#">2</a></li>
	    <li><a href="#">3</a></li>
	    <li><a href="#">4</a></li>
	    <li><a href="#">5</a></li>
	    <li><a href="#">&raquo;</a></li>
	</ul>
</div>
</body>
</html>