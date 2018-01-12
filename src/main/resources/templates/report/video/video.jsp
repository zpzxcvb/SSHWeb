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
<title>Insert title here</title>

<link href="/jsLib/video/css/video-js.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/jsLib/jquery.js"></script>
<script type="text/javascript" src="/jsLib/video/js/video.min.js"></script>

</head>
<body>
<video id="myVideo" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="none" width="340" height="264"
      poster="/img/welcome.jpg"
      data-setup="{}">
    <source src="http://vjs.zencdn.net/v/oceans.mp4" type='video/mp4' />
    <source src="http://vjs.zencdn.net/v/oceans.webm" type='video/webm' />
    <source src="http://vjs.zencdn.net/v/oceans.ogv" type='video/ogg' />
    <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
</video>
<div id="d"></div>
</body>
<script type="text/javascript">
	$(function(){
		var myvideo=videojs("#myVideo");
		
		$("#d").text();
	})
</script>
</html>