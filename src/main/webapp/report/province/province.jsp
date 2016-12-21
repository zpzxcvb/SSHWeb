<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>province</title>
<script type="text/javascript" src="/jsLib/QUI/libs/js/jquery.js"></script>
<script type="text/javascript" src="/jsLib/province/province.js"></script>
<script type="text/javascript" src="/jsLib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/jsLib/ueditor/ueditor.all.js"></script>
</head>
<body onload="setup()">
            <select class="select" id="s4">  
              <option>请选择</option>  
            </select>
			<select class="select" name="province" id="s1">  
              <option></option>  
            </select>  
            <select class="select" name="city" id="s2">  
              <option></option>  
            </select>  
            <select class="select" name="town" id="s3">  
              <option></option>  
            </select> 
            <div id="address"></div>
            <div id="editor" type="text/plain" style="width:1024px;height:500px;"></div>
			<input id="btn" type="button" value="click">
<script>  
var ue;
$(function(){
	document.getElementById("s4").add(new Option("中国",""));
	$("select").change(function(){
		var province = $('#s1').val();  
	    var city = $('#s2').val();  
	    var area = $('#s3').val();  
	    $('#address').text(province +"/"+ city +"/"+ area);  
	});
	ue=UE.getEditor('editor',{
        autoHeightEnabled: true,
        autoFloatEnabled: true,
        initialFrameWidth: 890,
        initialFrameHeight:200

    });
})
$("#btn").click(function(){
		alert(ue.setContent("<p>123</p><p>456</p>"));
		alert(ue.getContent());
		alert(ue.getPlainTxt());
	})
</script>
</body>
</html>