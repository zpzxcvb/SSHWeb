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
<style type="text/css">
	ol li{
		list-style:decimal-leading-zero;
		padding: 0 3px 0 10px;
		border-left: 3px solid #6CE26C;
		white-space:nowrap;
		width: 100%;
	}
	.key{
		color: rgb(129,149,149);
	}
</style>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
		        <h3 class="panel-title">
		           	 项目工作区
		        </h3>
		    </div>
			<div class="panel-body">
				<div class="panel panel-default col-md-3" style="float: left;height: 700px;margin-right: 10px;overflow:auto;">
					<div class="panel-body">
						<ul id="orgTree" class="ztree">
						</ul>
					</div>
				</div>
				<div class="panel panel-default col-md-8" style="float: left;height: 700px;overflow:auto;">
					<div class="panel-body">
						<div style="background-color: #eee;width: 100%;">
							<div style="margin-left: 40px;border-left: 3px solid #6CE26C;color: silver;padding: 3px 8px 3px 10px;">
								<b>[java code]</b>
							</div>
							<ol style="background-color: #eee;">
							</ol>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var zTree;
$(function(){
	zTree=initTree({
		id:"orgTree",
		async:true,
		url:"/file/scanProjects"
	});
})
function zTreeClick(event, treeId, treeNode){
		 if(!treeNode.isParent){
			$.ajax({
				url:"/file/readFile",
				data:{path:treeNode.path},
				success:function(result){
					var str="";
					$("ol").empty();
					$.each(result,function(index,value){
						$("ol").append("<li><span>"+value.replace(/ /g,'&nbsp;&nbsp;').replace(/\t/g,'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')+"</span></li>");
					})
					$("ol").find("li:even").css("background-color","#fff");
					$("ol").find("li:odd").css("background-color","#f8f8f8");
				}
			})
		 }
	 };
</script>
</html>