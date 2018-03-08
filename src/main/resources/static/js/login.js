var login = function(){
	$("#btn-login").click(function(){
		$.ajax({
			url:"/user/login",
			type: "post",
			data:$("#form-login").serialize(),
			beforeSend:function(){},
			success:function(data){
				alert(data);
			}
		});
	});
}

$(function(){
	login();
})
