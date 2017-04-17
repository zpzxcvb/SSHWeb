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
</style>
</head>
<body>
	<div style="display: inline-block;height: 500px;width: 100%;">
		<div style="float: left;width: 20%;height: 500px;margin-right: 20px;overflow:auto">
			<ul id="sysDictTree" class="ztree"></ul>
		</div>
		<div style="float: left;width: 70%;">
			<span id="sysDictItem"></span>
		</div>
	</div>
</body>
<script type="text/javascript">
	var zTree;
	$(function(){
		zTree=initTree({
			id:"sysDictTree",
			search:true,
			async:true,
			url:"/dict/showSysDictType"
		});
	});
	function zTreeClick(event, treeId, treeNode){
		alert(treeNode.name+""+treeNode.id);
	}
	function showDictItems(event, treeId, treeNode){
		if(!treeNode.isParent){
			/* $.ajax({
				url:"",
				type:"post",
				data:{path:treeNode.filePath},
				success:function(result){
					$.each(result,function(index,value){
						
					});
				}
			}); */
 		 }
    }
</script>
</html>