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
    <script src="/resources/jsLib/handlebars-v3.0.1.js"></script>
  </head>
  
  <body>
  <table id="tablelist">
</table>
  	<script id="tpl" type="text/x-handlebars-template">
{{#each list}}
<tr>
<td>{{name}}</td>
<td>
{{transformat sex}}
</td>
<td>{{age}}</td>
<td>
{{#with favorite}}
	{{#each this}}
		<p>{{name}}</p>
	{{/each}}
{{/with}}
</td>
<td>{{{home}}}</td>
</tr>
{{/each}}
	</script>
	<div id="bsp"></div>
  	<div class="container">
	  	<table id="users" class="table table-bordered table-striped table-hover ">
		</table>
	</div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		 var table=$("#users").DataTable({
  	        ajax: {
  	        	deferRender:true,//延迟渲染
  	        	type:"post",
  	        	error:function(){alert("服务器未正常响应，请重试");},
  	        	url:"user/queryUsers"
  	        },
  	        columns:[
  		           {data:null,title:"序号"},
  		         {data:"realname",title:"姓名"},
  		           {data:"userName",title:"登录名"},
  		           {data:"email",title:"邮箱"}
  	        ],
  	        columnDefs:[
  		             {
  		             	targets:1,
  		             	render:function(data,type,row,meta){
  		             		return "<a href=''>"+data+"</a>";
  		             	}
  		             }
  	        ]
  		});
  		//添加序号
  	     //不管是排序，还是分页，还是搜索最后都会重画，这里监听draw事件即可
  	     table.on("draw.dt",function() {
  	                 table.column(0, {
  	                     search: "applied",
  	                     order: "applied"
  	                 }).nodes().each(function(cell, i) {
  	                     //i 从0开始，所以这里先加1
  	                     i = i+1;
  	                     //服务器模式下获取分页信息
  	                     var page = table.page.info();
  	                     //当前第几页，从0开始
  	                     var pageno = page.page;
  	                     //每页数据
  	                     var length = page.length;
  	                     //行号等于 页数*每页数据长度+行号
  	                     var columnIndex = (i+pageno*length);
  	                     cell.innerHTML = columnIndex;
  	                 });
  	             });
  	})
  	function bulidTPL(){
	  var tpl   =  $("#tpl").html();
		//预编译模板
	    var template = Handlebars.compile(tpl);
	  	var context = { title: "zhaoshuai", content: "learn Handlebars",list:[{name:"a1",sex:"1",age:18,favorite:[{name:"唱歌"},{name:"跑步"}],home:"<a href='javascript:void(0);'>李四的个人主页</a>"},{name:"a2",sex:"0",age:22,favorite:[{name:"看书"},{name:"音乐"}],home:"<a href='javascript:void(0);'>张三的个人主页</a>"}]};
	  	Handlebars.registerHelper("transformat",function(value){
	  		if(value=="0"){
	  			return "男0";
	  		}else{
	  			return "女1";
	  		}
	  	});
	  	var html = template(context);
	  	$("#tablelist").html(html);
  }
  </script>
</html>
