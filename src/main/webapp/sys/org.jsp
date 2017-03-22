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
	pre{
		padding: 5px;
	    position: relative;
	    white-space: nowrap;
	    height:500px;
	    padding-left: 60px;
	}
	.pre-code{
		
	}
	.pre-rownum{
		position: absolute;
		width: 50px;
		background-color: #eee;
		top:0px;
		left:0px;
		border-right: 1px solid #ddd;
		list-style: none;
		padding:6px 0 40px 0;
		text-align: right;
	}
</style>
</head>
<body>
	<div style="display: inline-block;height: 500px;width: 100%;">
		<div style="float: left;width: 20%;margin-right: 20px;">
			<ul id="orgTree" class="ztree" style="height: 500px;"></ul>
		</div>
		<div style="float: left;width: 70%;">
			<span>code:</span>
			<pre>
				<code class="pre-code">
				</code>
				<ul class="pre-rownum">
				</ul>
			</pre>
		</div>
	</div>
	<button onclick="add()">添加</button>
	<div class="container">
	<table id="stuList" class="table table-bordered table-striped table-hover">
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
var table;
function add(){
// 	alert(table.clear());
}
$(function(){
	 var setting = {
			 data: {  
                 simpleData: {  
                     enable: false  
                 }  
             },
             async: {
                 enable: true,
                 url:"/user/file",
                 autoParam:["id", "name", "filePath=path"],
                 otherParam:{}
             },
             callback:{
            	 onClick:function(event, treeId, treeNode){
            		 if(!treeNode.isParent){
// 	            	 	alert(treeNode.filePath);
						$.ajax({
							url:'/user/readFile',
							data:{path:treeNode.filePath},
							success:function(result){
// 								alert(result);
								var str="";
								$(".pre-rownum").empty();
								$.each(result,function(index,value){
									$(".pre-rownum").append("<li>"+(index+1)+"</li>");
									str+=value+"\n";
									$(".pre-code").html(str);
								})
							}
						})
            		 }
            	 }
             }
	 };
     $.fn.zTree.init($("#orgTree"), setting);  
	 table=$("#stuList").DataTable({
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
                url: "/user/jsonTest",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    //setTimeout仅为测试延迟效果
                    setTimeout(function () {
                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.total;//返回数据全部记录
                        returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data;//返回的数据列表
                        //console.log(returnData);
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }, 200);
                }
            });
        }, */
        ajax: "/user/jsonTest",
        columns:[
	           {data:null},
	           {data:"userName"},
	           {data:"email"}
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

</script>
</html>