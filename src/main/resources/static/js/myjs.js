/**
 * 初始化tree
 * check 是否带复选框(true/false)
 * async 是否异步模式(true/false)
 * */
function initTree(param){
	var treeObj = $("#" + param.id);
	//是否自带搜索框
	if(param.search){
		treeObj.before("<input id=\"treeSearch\" type=\"text\" placeholder=\"请输入搜索内容\" onkeypress=\"treeSearch('"+param.id+"',this,event)\"/>");
	}
	//是否异步请求
	var async = false;
	if(param.async){
		async={
                enable: true,
                url: param.url,
                autoParam: ["id", "name", "path"]
            };
	}
	//是否带复选框
	var check = false;
	if(param.check){
		check = {
			enable: true
		}
	}
	//ztree非异步设置
	var setting = {
		async: async,
		check: check,
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid"
			}
		},
        callback:{
        	onClick: (typeof zTreeClick == 'function') ? zTreeClick : ''
        }
	};
	if(async){
		$.fn.zTree.init(treeObj, setting, null);
	}else{
		$.ajax({
			url: param.url,
			success: function(data) {
				$.fn.zTree.init(treeObj, setting, data);
			}
		})
	}
}

/**
 * 根据treeName进行搜索
 */
var allTreeNodes,nodeList,flag=true;
function treeSearch(id,search,event){
	if(event.which==13){
		var param=$(search).val();
		if(flag){
			allTreeNodes=$.fn.zTree.getZTreeObj(id).getNodes();
			flag=false;
		}
		if(param==""){
			nodeList=allTreeNodes;
		}else{
			nodeList = zTree.getNodesByParamFuzzy("name", param);
		}
		initTree({
			id:id,
			async:false,
			zTreeNodes:nodeList,
			search:false
		});
	}
}

//获取下拉框数据,并根据传值进行默认选中
function queryOptions(id,url,value){
	var dom = $("#"+id);
	$.ajax({ 
		url: url,
		success: function(data){
			$.each(data, function (i, e) {
				dom.append("<option value='"+e.id+"'>"+e.name+"</option>")
            });
			dom.find("option[value='"+value+"']").attr("selected","selected");
		}
	});
}

//layui分页表格构造
function table_layui(table, params){
	return table.render({
		elem: params.id,
		url: params.url,
		width: params.width,
		toolbar: params.toolbar,
		method: 'post',
		where: params.param,
		cellMinWidth: 80,
		even: false,//条纹
	    page: true,//分页
	    cols: [params.columns],
	    request: {
	    	pageName: 'pageNum', //页码的参数名称，默认：page
	    	limitName: 'pageSize' //每页数据量的参数名，默认：limit
	    },
		response: {
    		statusName: 'code', //数据状态的字段名称，默认：code
    		statusCode: 0, //成功的状态码，默认：0
    		msgName: 'msg', //状态信息的字段名称，默认：msg
    		countName: 'count', //数据总数的字段名称，默认：count
    		dataName: 'data' //数据列表的字段名称，默认：data
    	}
	});
}
//layer 弹出层
function openDialog(table, tableId, url){
	layer.open({
		type: 2,
	  	area: ['400px', '400px'], //宽高
	  	content: url,
	  	end: function(){
	  		if(tableIns){
	  			tableIns.reload();
	  		}
	  	}
	});
}
//关闭子页面
function closeDialog(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
//layer 弹出编辑层
function editDialog(table, tableId, url, id){
	var checkStatus = table.checkStatus(tableId);
	if(checkStatus.data.length == 1){
		var id = checkStatus.data[0][id];
		url = url + "?id="+id
		openDialog(table, tableId, url);
	}else{
		layer.msg("请选择一行记录",{icon:0});
	}
}

/**
 * 删除询问框
 * */
function confirm_Delete(table, tableId, url, id){
	var checkStatus = table.checkStatus(tableId);
	if(checkStatus.data.length > 0){
		var ids=[];
		$.each(checkStatus.data, function(index, item){
			ids.push(item[id]);
		});
		layer.confirm('您确定要删除操作吗?', {icon: 3}, function(index){
			$.ajax({
				url: url,
				type: "post",
				data: {ids:ids},
				success: function(data){
					if(data.code == "ok"){
						layer.msg(data.msg,{icon:1});
						layer.close(index);
						tableIns.reload();
					}else{
						layer.msg(data.msg,{icon:0});
					}
				}
			});
		});
	}else{
		layer.msg("请至少选择一行记录",{icon:0});
	}
}
/**
 * 通用表单提交
 * */
function formSubmit(url){
	$("#postBtn").click(function(){
		var param = $("form").serialize();
		$.ajax({
			url: url,
			type: "post",
			data: param,
			success: function(data){
				if(data.code == "ok"){
					parent.layer.msg(data.msg,{icon:1});
				}else{
					parent.layer.msg(data.msg,{icon:0});
				}
				//关闭页面
				closeDialog();
			}
		})
	})
}