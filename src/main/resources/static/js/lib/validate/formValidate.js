/**
 * 
 * required="true"  是否验证，加上默认判空
 * 
 * dataType="url,email,length[1,10],min[10]"  验证属性的类型
 * 
调用示例：
<input id="name" type="text" name="title" required="true" dataType="phone,min[12]"/>

外部自定义验证类型，如下：
test为自定义类型
$.extend($.fn.defaults.rules, {

	test : {
		validator : function(value, param) {
			var regex = /^[A-Z]{3}$/;
    		return regex.test(value);
		},
		message : '必须为三个大写子母'
	}
});

表单提交时统一验证：
function test1() {
	var a = $("#formId").formValidate();
	if(a){
		doSometing....
		submit();
	}
	console.log(a);
}
*/

$(function() {
	$("input[required='true']").each(function(i, dom) {
		$(this).bind("blur", function() {
			validateForm(dom);
		});
	});
});

$.fn.formValidate = function() {
	var flag = true;
	$.each($(this).find("input[required='true']"), function(i, dom) {
		if (dom.required) {
			var validate = validateForm(dom);
			if (!validate) {
				console.log(dom.name+"：validate->"+flag);
				flag = false;
				return flag;
			}
		}
	});
	return flag;
};

window.validateForm = function(dom) {
	var flag = false;
	var obj = $(dom);
	var value = obj.val();
	var msgObj = obj.next();
	var isValidate = obj.attr("required");
	var dataType = obj.attr("dataType");
	var errorMsg = obj.attr("errorMsg");

	var func = dataType;
	var rules = $.fn.defaults.rules;

	if (isValidate) {

		if (msgObj.length == 0) {
			obj.after('<span></span>');
			msgObj = obj.next();
			msgObj.css({
				"color" : "red",
				"padding-left" : "10px"
			});
		}

		if (value == "") {
			if(errorMsg==undefined){
				errorMsg = rules.missMessage;
			}
		} else {

			if (func != undefined) {

				var paramsArray = [];

				if (dataType.indexOf("[") > 0) {

					func = dataType.slice(0, dataType.indexOf("["));

					var params = dataType.slice(dataType.indexOf("[") + 1,
							dataType.lastIndexOf("]"));

					paramsArray = params.split(",");
				}

				var iskey = rules.hasOwnProperty(func);

				if (iskey) {
					var regex_obj = rules[func];

					var f = regex_obj.validator(value, paramsArray);

					if (f) {
						flag = true;
						errorMsg = "";
					} else {
						if (errorMsg == undefined) {

							errorMsg = regex_obj.message;

							errorMsg = errorMsg.format(paramsArray);

						}
					}
				}
			}else{
				flag = true;
				errorMsg = "";
			}
		}
	}

	msgObj.html(errorMsg);
	if (!flag) {
		msgObj.focus();
	}
	return flag;
};

String.prototype.format = function(param) {
	var reg = /{([^{}]+)}/gm;
	return this.replace(reg, function(match, name) {
		return param[name];
	});
};
$.fn.defaults = {
	rules : {
		missMessage : '此项不能为空！'
	}
};
$.extend($.fn.defaults.rules,{
	
	min : {
		validator : function(value, param) {
			var len = value.length;
			return len >= param[0];
		},
		message : '长度最少是{0}位！'
	},
	max : {
		validator : function(value, param) {
			var len = value.length;
			return len <= param[0];
		},
		message : '长度最多是{0}位！'
	},
	length : {
		validator : function(value, param) {
			var len = value.length;
			return len >= param[0] && len <= param[1];
		},
		message : '长度必须在{0}到{1}之间！'
	},
	equalTo : {
		validator : function(value, param) {
			var paramValue = $("#" + param).val();
			return value == paramValue;
		},
		message : '两次输入的值不一致！'
	},
	decimal : {
		validator : function(value, param) {
			var regexStr = "^\\d{0," + param[0] + "}(\\.\\d{0," + param[1]
					+ "})?$";
			var regex = new RegExp(regexStr);
			return regex.test(value);
		},
		message : '请输入{0}位整数，小数点精确到后{1}位！'
	},
	phone : {
		validator : function(value) {
			var regex = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			return regex.test(value) || /^1(3|5|8)\d{9}$/.test(value);
		},
		message : '电话号码或手机号码格式不正确！'
	},
	email : {
		validator : function(value) {
			var regex = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/;
			return regex.test(value);
		},
		message : '邮箱格式不正确！'
	},
	url : {
		validator : function(value) {
			var regex = /^((https|http|ftp|rtsp|mms):\/\/)[^\s]+/;
			return regex.test(value);
		},
		message : 'URL格式不正确！'
	},
	idcard : {
		validator : IdentityCodeValid(value),
		message : '身份证号码格式不正确！'
	},
	zip : {
		validator : function(value) {
			var regex = /^\d{6}$/;
			return regex.test(value);
		},
		message : '邮政编码格式不正确'
	},
	ip : {
		validator : function(value) {
			var regex = /^\d{0,3}\.\d{0,3}\.\d{0,3}\.\d{0,3}$/;
			return regex.test(value);
		},
		message : 'IP地址格式不正确'
	},
	qq : {
		validator : function(value) {
			var regex = /^[1-9]\d{4,9}$/;
			return regex.test(value);
		},
		message : 'QQ号码格式不正确！'
	},
	chinese : {
		validator : function(value) {
			var regex = /^[\u4e00-\u9fa5]+$/;
			return regex.test(value);
		},
		message : '请输入中文字符！'
	}
});
//身份证校验
function IdentityCodeValid(value) {
	var pass= true;
	var regex = /^\d{17}[\d|x]|\d{15}$/;
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    if (!regex.test(value)){
        pass = false;
    } else if (!city[code.substr(0,2)]){
        pass = false;
    } else {
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                pass =false;
            }
        }
    }
    return pass;
}