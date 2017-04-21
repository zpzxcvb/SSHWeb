//配置DataTables默认参数
$.extend(true, $.fn.dataTable.defaults, {
	language:{
	    "processing": "正在处理中...",
	    "lengthMenu": "每页 _MENU_ 条记录",
	    "zeroRecords": "没有匹配结果",
	    "info": "当前显示第 _START_ 至 _END_ 条，共 _TOTAL_ 条记录",//第_PAGE_页，共_PAGES_页，
	    "infoEmpty": "当前显示第 0 至 0 条，共 0 条",
	    "infoFiltered": "(由 _MAX_ 项结果过滤)",
//	    "infoPostFix": "<a class=''><i class='iconfont icon-refresh'></i></a>",
	    "search": "搜索:",
	    "searchPlaceholder": "搜索...",
	    "url": "",
	    "emptyTable": "表中数据为空",
	    "loadingRecords": "正在加载中...",
	    "thousands": ",",
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
	},  //提示信息
	//l-length,f-filter,r-rProcessing,t-table,i-infomation,p-pagination
	dom:"frt<'row'<'dtl'l><'dti'i><'dtp'p><'clear'>>",
	lengthMenu:[10,20,50,100,500,1000],//每页显示多少条
	pagingType:"full_numbers",//包含所有分页按钮
	processing: true,
//	paging:false//禁止分页
	searching:false,
	ordering:false,//禁止排序
//	scrollY:100,//右侧滚动条
	scrollCollapse:true,
	decimal:".",//小数
	stateSave:true,//缓存
	serverSide:true//服务器模式分页
});