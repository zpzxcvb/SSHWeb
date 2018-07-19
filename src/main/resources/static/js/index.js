layui.define(['layer'],function(exports){  //引用layer模块
	var layer = layui.layer;
	exports('index',function(){  //注意，这里是模块输出的核心，模块名必须和use时的模块名一致，这里的index就是在index.html use的模块
		layer.msg('hello world');
	})
})
layui.use(['index'],function(){
	layui.index()  //调用index这个自定义模块
})