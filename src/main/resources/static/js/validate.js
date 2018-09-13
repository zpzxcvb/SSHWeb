$(function() {
	$("#register_form").validate({
		rules : {
			userName : {
				required : true,
				minlength : 2,
				regex : ""
			},
			password : {
				required : true,
				minlength : 5
			},
			pwd_repeat : {
				required : true,
				minlength : 5,
				equalTo : "#password"
			}
		},
		messages : {
			userName : {
				required : "请输入用户名",
				minlength : "用户名必需由两个字母组成"
			},
			password : {
				required : "请输入密码",
				minlength : "密码长度不能小于 6 个字母"
			},
			pwd_repeat : {
				required : "请再次输入密码",
				minlength : "密码长度不能小于 6 个字母",
				equalTo : "两次密码输入不一致"
			}
		}
	});
	
	$.validator.addMethod("regex", function(value, element, param) {
		var regex=/^\d+$/;
	    return regex.test(value);
	  }, '请输入 数字');
});
