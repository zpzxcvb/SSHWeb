require.config({
	baseUrl:'js',
//	urlArgs:'v='+(new Date()).getTime(),//清楚缓存
	paths: {
		"jquery" : "lib/jquery-1.9.1.min",
		"ztree" : "lib/zTree/jquery.ztree.core",
		"bootstrap" : "lib/bootstrap/js/bootstrap",
		"dataTables" : "lib/dataTables/js/dataTables.bootstrap",
		"myjs" : "myjs",
		"a" : "a",
		"b" : "b"
	},
	shim: {
		"myjs":{
			deps : ["jquery","css!../css/style.css","css!lib/iconfonts/iconfont.css"]
		},
		"bootstrap":{
			deps : ["jquery","css!lib/bootstrap/css/bootstrap.min"]
		},
		"ztree":{
			deps : ["jquery","css!lib/zTree/css/zTreeStyle/zTreeStyle"]
		}
	},
	map: {
		"*":{
			"css":"css.min"
		}
	}
})
