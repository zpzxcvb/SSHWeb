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
  	<div class="container">
	  	<table id="users" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th></th>
					<th>姓名</th>
					<th>邮箱</th>
				</tr>
			</thead>
		</table>
	</div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		 var table=$("#users").DataTable({
  			/* ajax: function (data, callback, settings) {
  	            //封装请求参数
  	            var param = {};
  	            param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
  	            param.start = data.start;//开始的记录序号
  	            param.page = (data.start / data.length)+1;//当前页码
  	            //console.log(param);
  	            //ajax请求数据
  	            $.ajax({
  	                type: "GET",
  	                url: "/user/queryUsers",
  	                cache: false,  //禁用缓存
  	                data: param,  //传入组装的参数
  	                dataType: "json",
  	                success: function (result) {
  	                    setTimeout(function () {
  	                        //封装返回数据
  	                        var returnData = {};
  	                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
  	                        returnData.recordsTotal = result.recordsTotal;//返回数据全部记录
  	                        returnData.recordsFiltered = result.recordsFiltered;//后台不实现过滤功能，每次查询均视作全部结果
  	                        returnData.data = result.data;//返回的数据列表
  	                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
  	                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
  	                        callback(returnData);
  	                    }, 200);
  	                }
  	            });
  	        }, */
  	        ajax: {
  	        	deferRender:true,

  	        	url:"/resources/test.txt"//"/user/queryUsers",
  	        },
  	        columns:[
  		           {data:null},
  		           {data:"name"},
  		           {data:"office"}
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
  	     table.on('draw.dt',function() {
  	                 table.column(0, {
  	                     search: 'applied',
  	                     order: 'applied'
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
  	             }).draw();
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
