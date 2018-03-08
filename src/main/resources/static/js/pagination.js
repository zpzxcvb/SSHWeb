//配置DataTables默认参数
var language = {
	    "processing": "正在处理中...",
	    "lengthMenu": "显示 _MENU_ 条记录",
	    "zeroRecords": "没有匹配结果",
	    "info": "当前第_PAGE_页，共_PAGES_页，共 _TOTAL_ 条记录",//START END，
	    "infoEmpty": "当前显示第 0 至 0 条，共 0 条",
	    "infoFiltered": "(由 _MAX_ 项结果过滤)",
//	    "infoPostFix": "<a class=''><i class='iconfont icon-refresh'></i></a>",
	    "search": "搜索:",
	    "searchPlaceholder": "请输入关键字",
	    "emptyTable": "表中数据为空",
	    "loadingRecords": "正在加载中...",
	    "thousands": ",",
	    "decimal":".",//小数
	    "paginate": {
	        "first": "首页",
	        "previous": "上一页",
	        "next": "下一页",
	        "last": "末页",
	        "jump": "跳转"
	    },
	    "aria": {
	        "sortAscending": ": 以升序排列此列",
	        "sortDescending": ": 以降序排列此列"
	    }
	};
$.extend(true, $.fn.dataTable.defaults, {
	language:language,  //提示信息
	//l-length,f-filter,r-processing,t-table,i-info,p-paging
	dom:"frt<'bottom'ilp<'clear'>>",
	lengthMenu:[10,20,50,100,500,1000],//每页显示多少条
	pagingType:"full_numbers",//包含所有分页按钮
	processing: true,
//	paging:false//禁止分页
	searching:false,//搜索
	ordering:false,//禁止排序
//	scrollY:100,//右侧滚动条
	scrollCollapse:true
//	stateSave:true//缓存
//	serverSide:true//服务器模式分页
	
});