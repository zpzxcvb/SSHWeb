require.config({
	baseUrl:'/js',
//	urlArgs:'v='+(new Date()).getTime(),//清楚缓存
	paths: {
		"jquery" : "lib/jquery-1.9.1.min",
		"ztree" : "lib/zTree/jquery.ztree.core",
		"layui" : "lib/layui/layui",
		"myjs" : "myjs",
		"index" : "index"
	},
	shim: {
		"myjs":{
			deps : ["jquery","css!../css/style.css"]
		},
		"ztree":{
			deps : ["jquery","css!lib/zTree/css/zTreeStyle/zTreeStyle"]
		},
		"layui":{
			deps : ["css!lib/layui/css/layui"]
		},
		"index":{
			init : function(){
				return {
					hello1: hello1,
					hello2: hello2
				}
			}
		}
	},
	map: {
		"*":{
			"css":"requirejs/css.min"
		}
	}
})
