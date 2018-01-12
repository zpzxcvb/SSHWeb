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
<script type="text/javascript" src="/resources/jsLib/jquery-1.9.1.min.js"></script>
<%@ include file="/common/common.jsp"%>

<script type="text/javascript">
	$(function(){
		var options={
				currentPage: 4,
				totalPages: 10,
				numberOfPages:5,
				itemTexts: function(type, page, current) { //修改显示文字
	                switch (type) {
	                case "first":
	                    return "首页";
	                case "prev":
	                    return "上一页";
	                case "next":
	                    return "下一页";
	                case "last":
	                    return "末页";
	                case "page":
	                    return page;
	                }
	            }
			}
		$('#example').bootstrapPaginator("show",3);
			$('#example').bootstrapPaginator(options);
	})
</script>
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
</table>
<div id="example"></div>
<div>
	<ul class="pagination">
	    <li><a href="#">&laquo;</a></li>
	    <li><a href="#">1</a></li>
	    <li><a href="#">2</a></li>
	    <li><a href="#">3</a></li>
	    <li><a href="#">4</a></li>
	    <li><a href="#">5</a></li>
	    <li><a href="#">6</a></li>
	    <li><a href="#">7</a></li>
	    <li><a href="#">8</a></li>
	    <li><a href="#">9</a></li>
	    <li><a href="#">10</a></li>
	    <li><a href="#">11</a></li>
	    <li><a href="#">12</a></li>
	    <li><a href="#">13</a></li>
	    <li><a href="#">14</a></li>
	    <li><a href="#">15</a></li>
	    <li><a href="#">16</a></li>
	    <li><a href="#">17</a></li>
	    <li><a href="#">18</a></li>
	    <li><a href="#">19</a></li>
	    <li><a href="#">20</a></li>
	    <li><a href="#">&raquo;</a></li>
	</ul>
</div>
</body>
</html>