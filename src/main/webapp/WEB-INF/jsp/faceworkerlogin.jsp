<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/24
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String jsPath=request.getContextPath()+"/js/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>人脸登录</title>


	<link rel="stylesheet" type="text/css" href=<%=path+"/css/normalize.css"%> />
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/default.css"%> />

<%--	<script src=<%=path + "/js/prefixfree.min.js"%>></script>--%>

	<link rel="stylesheet" type="text/css" href=<%=path+"/css/faceworkerlogincss.css"%> />

</head>
<body>
<div id="logo">
	<h1 class="hogo"><i> 人脸识别登录</i></h1>
</div>
<section class="stark-login" >
	<form action="" method="">

		<br>
		<video id="video" width="300" height="230" autoplay style=" border: 5px solid #00fffc;"></video>


		<div id="fade-box"  >

		<%--			<input type="text" name="username" id="用户名" placeholder="用户名" required>--%>
<%--			<input type="password" placeholder="密码" required>--%>
	<input type="button" onclick="query()" value="立即登录"
	       class="submit_btn" />
	<a style="margin-left: 240px; font-size: 16px; color: #00a4a2" href=<%=path + "/workerLogin/workerLoginPage.action"%>>账号登录</a>

			<canvas id="canvas" width="400" height="300" hidden></canvas>
		</div>
	</form>
	<div class="hexagons">

		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>

		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<br>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
		<span>&#x2B22;</span>
	</div>
</section>

<div id="circle1">
	<div id="inner-cirlce1">
		<h2> </h2>
	</div>
</div>
<ul>
	<li></li>
	<li></li>
	<li></li>
	<li></li>
	<li></li>
</ul>


<script type="text/javascript" src=<%=jsPath+"jquery-3.4.1.js" %>></script>
<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

<script type="text/javascript">
	//var 是定义变量
	var video = document.getElementById("video"); //获取video标签
	var context = canvas.getContext("2d");
	var con  ={
		audio:false,
		video:{
			width:1980,
			height:1024,
		}
	};

	//导航 获取用户媒体对象
	navigator.mediaDevices.getUserMedia(con)
		.then(function(stream){
			video.srcObject = stream;
			video.onloadmetadate = function(e){
				video.play();
			}
		});


	function query(){
		layui.use('layer', function(){
			var layer = layui.layer;
		//把流媒体数据画到convas画布上去
		context.drawImage(video,0,0,400,300);
		var base = getBase64();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/facelogin.action",
			data:{"base":base},
			success:function(data){
				// layer.msg("账号或密码不存在！！！");

			       if(data==="StatusLock"){
					layer.msg("账户已被禁用，请联系学校管理员！！！");
				}else if(data==="notok"){

				       layer.msg("无此用户！！！");
			       }else{
					//登入成功的提示与跳转
					layer.msg('登入成功', {
						offset: '15px'
						, icon: 1
						, time: 300
					}, function () {
						location.href = '${pageContext.request.contextPath}/workfacelogin.action'; //后台主页
					});
				}



			}
		});
		});

	}




	function getBase64() {
		var imgSrc = document.getElementById("canvas").toDataURL(
			"image/png");
		alert(imgSrc);
		return imgSrc.split("base64,")[1];

	};
</script>

</body>
</html>