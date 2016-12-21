<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>selected </title>
    
	<script type="text/javascript" src="/jsLib/jquery.js"></script>
	<script type="text/javascript" src="/jsLib/require.js" defer async="true" ></script>
	<script type="text/javascript" src="/jsLib/jquery.validate.js"></script>
	<script type="text/javascript" src="/jsLib/messages_zh.js"></script>
<!-- 	<script type="text/javascript" src="/js/a.js"></script> -->
<script type="text/javascript" src="/jsLib/province/province.js"></script>
  </head>
  
  <body>
  	<form action="">
	  	<div>
	  		姓名：<input id="username" name="username" type="text">
	  	</div>
	  	<div>
	  		密码：<input id="password" name="password" type="password">
	  	</div>
	  	<div>
	  		确认密码：<input id="password2" type="password" equalTo="#password">
	  	</div>
	  	<div>
	  		邮箱：<input id="email" name="email" type="text">
	  	</div>
	  	<div>性别：
	  		<input id="man" name="sex" type="radio" value="1" checked="checked"> <label for="man">男</label>
	  		<input id="woman" name="sex" type="radio" value="0"> <label for="woman">女</label>
	  	</div>
	  	<div>学历：
		  	<select id="job">
		  		<option value="">---请选择---</option>
		  		<option value="1">IT</option>
		  		<option value="2">金融</option>
		  		<option value="3">农业</option>
		  		<option value="4">服务</option>
		  	</select>
	  	</div>
	  	<div>
		  	省：<select id="s1">
		  	</select>
		  	市：<select id="s2">
		  	</select>
		  	区/县：<select id="s3">
		  	</select>
	  	</div>
	  	<div>兴趣爱好：
	  		<input id="allCheckbox" type="checkbox" value="" onchange="checkAll()">全选
	  		<input name="favorite" type="checkbox" value="1">篮球
	  		<input name="favorite" type="checkbox" value="2">足球
	  		<input name="favorite" type="checkbox" value="3">乒乓球
	  		<input name="favorite" type="checkbox" value="4">羽毛球
	  		<input name="favorite" type="checkbox" value="5">爬山
	  		<input name="favorite" type="checkbox" value="6">骑行
	  		<input id="reverse" type="checkbox" value="" onchange="checkreverse()">反选
	  	</div>
	  	<div>
	  		<input id="woman" name="sex" type="checkbox">
	  		<a href="agree.html">我已阅读并同意此条约</a>
	  	</div>
	  	<div>
	  		<input type="submit" value="注册" >
	  		<input id="testBtn" type="button" value="testVal()" onclick="regedit()">
	  	</div>
  	</form>
  </body>
  <script type="text/javascript">
  function checkAll(){
	  if($("#allCheckbox").attr("checked")){
		  $("[name='favorite']").attr("checked",true);
	  }else{
		  $("[name='favorite']").attr("checked",false);
	  }
  }
  function checkreverse(){
	  $("[name='favorite']").each(function(i,ck){
			if(ck.checked){
				ck.checked=false;
			}else{
				ck.checked=true;
			}
		})
  }
  function regedit(){
	var sex=$("[name='sex']:checked").val();
	var favorite="";
	$("[name='favorite']:checked").each(function(i,ck){
		favorite+=ck.value+",";
	})
	var job=$("#job").find("option:selected").val();
	var province = $('#s1').val();  
    var city = $('#s2').val();  
    var area = $('#s3').val();  
    var address=province +"/"+ city +"/"+ area; 
    alert(address)
  }
  $(function(){
	setup();
  })
  </script>
</html>
