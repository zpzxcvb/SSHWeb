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