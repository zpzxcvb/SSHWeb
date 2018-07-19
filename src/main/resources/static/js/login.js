require(["jquery","myjs","layui"],function($){
	$(function(){
		login();
	})
	var login = function(){
		$("#btn-login").click(function(){
//			$("#form-login").attr("action","/login")
//			$("#form-login").submit();
			$.ajax({
				url:"/login",
				type: "post",
				data:$("#form-login").serialize(),
				beforeSend:function(){},
				success:function(data){
					window.location.href="/sys/main.html";
				}
			});
		});
	}
});
