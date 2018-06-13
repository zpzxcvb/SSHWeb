$(function(){
	currentTime();
	$("#color").change(function(){
		$("#choose_color").val($("#color").val());
	})
})
/**
 * 当前时间
 */
function currentTime(){
	$("#currentTime").text(new Date().toLocaleString()+" 星期"+"日一二三四五六".charAt(new Date().getDay()));
	setTimeout("currentTime()",1000);
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