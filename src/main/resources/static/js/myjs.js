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
				if(data[0]!=null){
					$.fn.zTree.init(treeObj, setting, data);
				}
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
				dom.append("<option value='"+e.value+"'>"+e.name+"</option>")
            });
			if(value){
				dom.find("option[value='"+value+"']").attr("selected","selected");
			}
		}
	});
}

//layui分页表格构造
function table_layui(table, params){
	return table.render({
		elem: params.id,
		url: params.url,
		width: params.width,
		method: 'post',
		where: params.param,
		cellMinWidth: 120,
		even: false,//条纹
	    page: {//分页
	    	layout:['prev', 'page', 'next', 'limit', 'skip', 'count', 'refresh']
	    },
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
function openDialog(url, width, height){
	if(!width){
		width="500px";
		height="400px";
	}
	layer.open({
		type: 2,
	  	area: [width, height], //宽高
	  	content: url,
	  	btn: ['保存', '取消'],
//	  	btnAlign: 'c',
	  	yes:function(index, layero){
//	  		var iframeWin = window[layero.find('iframe')[0]['name']];
//	  		iframeWin.mm();//调用子页面方法
	  		var body = layer.getChildFrame('body', index);
	  		body.find('form').find('[lay-submit]').click();
//	  		layer.close(index);
	  	}
	});
}
//关闭子页面
function closeDialog(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

/**
 * 批量删除询问框
 * id 表示数据行字段id名
 * */
function confirm_Delete(url, id){
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
function formSubmit(form,url,submit_lay_filter, layedit){
	form.on('submit('+submit_lay_filter+')', function(data){
		$.ajax({
			url: url,
			type: "post",
			data: data.field,
			success: function(data){
				if(data.code == "ok"){
					parent.layer.msg(data.msg,{icon:1});
					if(parent.tableIns){
						parent.tableIns.reload();
					}
				}else{
					parent.layer.msg(data.msg,{icon:0});
				}
				//关闭页面
				closeDialog();
			},
			error: function(){
				parent.layer.msg('服务器异常',{icon:5});
				//关闭页面
				closeDialog();
			}
		})
		return false;
	});
}

/**
 * 表单赋值
 * */
function loadFormValue(form, formFilter, url, param){
	$.ajax({
		url: url,
		type: "post",
		data: param,
		success: function(data){
			form.val(formFilter, data);
		}
	})
}

/**
 * 开关状态修改
 * */
function switchStatusForUpdate(form, switchFilter, url, id) {
	form.on('switch('+switchFilter+')', function(data) {
		var status = this.checked ? 1 : 0;
		var param = {
			[id] : this.value,
			status : status
		};
		layer.confirm('您确定要操作吗?', {
			icon : 3
		}, function(index) {
			$.ajax({
				url : url,
				type : "post",
				data : param,
				success : function(data) {
					if (data.code == "ok") {
						layer.msg(data.msg, {
							icon : 1
						});
						layer.close(index);
					} else {
						layer.msg(data.msg, {
							icon : 0
						});
					}
				}
			});
		}, function() {
			tableIns.reload();
		});
	});
}

/**
 * 文件上传
 * 
 * domId 上传文件的元素id
 * fileListId 文件列表的tbody的id
 * fileType 允许上传文件的类型
 * */
function fileUpload(upload, domId, fileListId, fileType){
	var fileList = $("#"+fileListId);//文件列表table对象
	var files;
	var loadIndex;
	var uploadInst = upload.render({
	    elem: '#'+domId, //绑定元素
	    url: '/file/upLoad', //上传接口
	    accept: 'file',
	    multiple: true,
	    auto: false,
	    bindAction: '#uploadBtn',
//	    acceptMime:'image/*',//过滤文件类型
	    exts: fileType,//文件类型  txt|jpg|pdf
	    choose: function(obj){   
	        files = obj.pushFile(); //将每次选择的文件追加到文件队列
	        //读取本地文件
	        obj.preview(function(index, file, result){
//        	console.log(index);
//        	console.log(file);
//        	console.log(result);
	          var tr = $([
	        	  '<tr id="upload-'+ index +'">',
		            '<td>'+ file.name +'</td>',
		            '<td>'+ (file.size/1024).toFixed() +'kb</td>',
		            '<td>等待上传</td>',
		            '<td>',
		              '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
		              '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
		            '</td>',
		          '</tr>'].join(''));
	          
	          //单个重传
	          tr.find('.demo-reload').on('click', function(){
	            obj.upload(index, file);
	          });
	          
	          //删除
	          tr.find('.demo-delete').on('click', function(){
	            delete files[index]; //删除对应的文件
	            tr.remove();
	            uploadInst.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	          });
	          
	          fileList.append(tr);
	        });
	    },
	    before: function(obj){
	    	console.log(files);
	        console.log('文件上传中');
	        loadIndex = layer.load(1);
	    },
	    done: function(res, index, upload){
	    	layer.close(loadIndex);
		    if(res.code == '0'){
		    	layer.msg(res.msg,{icon:1});
		    	var tr = fileList.find('tr#upload-'+ index);
		        var tds = tr.children();
		        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		        tds.eq(3).html(''); //清空操作
		        return delete files[index]; //删除文件队列已经上传成功的文件
		    }else{
		    	layer.msg(res.msg,{icon:0});
		    	var tr = fileList.find('tr#upload-'+ index);
		        var tds = tr.children();
		        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
		        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
	    },
	    error: function(index, upload){
	    	layer.close(loadIndex);
	    	layer.msg('服务器异常',{icon:5});
	    	var tr = fileList.find('tr#upload-'+ index);
	        var tds = tr.children();
	        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	 });
}
/**
 * 初始化按钮调用事件
 * */
function initFunction(){
	//监听普通按钮事件
    $('.layui-btn').click(function(){
  	  	var method = this.id;
  	  	active[method] ? active[method].call(this) : '';
    });
  	//监听行工具按钮事件
    table.on('tool(table_event)', function(obj){
    	var method = obj.event;
  	  	active[method] ? active[method].call(this, obj.data) : '';
    });
}