<%--
  Created by IntelliJ IDEA.
  User: 21
  Date: 2019/12/18
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String jsPath=request.getContextPath()+"/js/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String servletPath=request.getContextPath();
%>
<html>
<head>
	<title>Title</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href=<%=servletPath+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=servletPath+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=servletPath+"/layuiadmin/style/login.css"%> media="all">
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/style.css"%> />

	<script type="text/javascript" src=<%=jsPath+"jquery-1.4.4.min.js" %>></script>
	<style>
		body {
			height: 100%;
			background: #213838;
			overflow: hidden;
		}

		canvas {
			z-index: -1;
			position: absolute;
		}
	</style>

	<script src=<%=jsPath+"jquery.js" %>></script>
<%--	<script src=<%=jsPath+"verificationNumbers.js" %>></script>--%>
	<script src=	<%=jsPath+"Particleground.js" %>></script>
	<script>
		$(document).ready(function() {
			//粒子背景特效
			$('body').particleground({
				dotColor: '#5cbdaa',
				lineColor: '#5cbdaa'
			});
			//验证码
			// createCode();
			//测试提交，对接程序删除即可
			$(".submit_btn").click(function(){
				location.href="index.html";
			});
		});
	</script>


	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}

		body {
			height: 100vh;
			background-position: center;
			overflow: hidden;
		}

		h1 {
			color: #fff;
			text-align: center;
			font-weight: 100;
			margin-top: 40px;
		}



			#media {
			width: 294px;
			height: 282px;
			margin: 5px auto 0;
			overflow: hidden;
			opacity: 50.7;
		}

		#video {
			width: 294px;
			height: 226px;
			margin-top: 3px;
			border-radius: 60%;
		}

		#canvas {
			display: none;
		}

		#btn {
			width: 160px;
			height: 50px;
			background: #03a9f4;
			margin: 70px auto 0;
			text-align: center;
			line-height: 50px;
			color: #fff;
			border-radius: 40px;
		}
	</style>
</head>
<body>
<form action="${pageContext.request.contextPath}/facelogin.action"
      method="get">
	<dl class="admin_login">
		<dt>
			<strong>该功能重要，再次确认身份</strong> <em style="height:
				10px;"></em> <strong>人脸识别</strong>
		</dt>
		<div id="media">
			<video id="video" width="530" height="300" autoplay></video>
			<canvas id="canvas" width="400" height="300"></canvas>
		</div>
	</dl>

</form>

		<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form">

				<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
					<div class="layadmin-user-login-main">
		<dd>

			<input class="layui-btn layui-btn-fluid" type="button" onclick="faceCheck()" value="人脸验证"
			       class="submit_btn" />
		</dd>
<%--		<div class="layui-form-item">--%>
<%--			<label class="layui-form-label">手机号</label>--%>
<%--			<div class="layui-input-inline">--%>
<%--				<input type="text" name="workeraccount" id="phone" lay-verify="required"  autocomplete="off" class="layui-input"  >--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="layui-form-item">--%>
<%--			<label class="layui-form-label">验证码</label>--%>
<%--			<div class="layui-input-inline">--%>
<%--				<input type="password" name="workerpass" id="yzm" lay-verify="required"  autocomplete="off" class="layui-input"  >--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<dd>--%>
<%--			<input class="layui-btn layui-btn-fluid" type="button" onclick="getyzm()" value="获取验证码"--%>
<%--							       class="submit_btn" />--%>
<%--		</dd>--%>

<%--		<dd>--%>
<%--			<input class="layui-btn layui-btn-fluid" type="button" onclick="phoneCheck()" value="手机验证"--%>
<%--			       class="submit_btn" />--%>
<%--		</dd>--%>
					</div>
				</div>
			</div>
		</div>




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


		//人脸验证
		function faceCheck(){

			//把流媒体数据画到convas画布上去
			context.drawImage(video,0,0,400,300);
			var base = getBase64();

			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/BackAction/faceCheck.action",
				data:{"base":base},
				success:function(data){
					if(data.msg=="1"){
						alert("验证成功");
						window.location.href="/ChildSchool/web/coursemanagement.action";
					}else if(data.msg=="0"){
						alert("验证失败");

					}

				}
			});

		}


		<%--//短信验证--%>
		<%--function phoneCheck(){--%>


		<%--	$.ajax({--%>
		<%--		type:"post",--%>
		<%--		url:"${pageContext.request.contextPath}/BackAction/faceCheck.action",--%>
		<%--		data:{"phone":yzm},--%>
		<%--		success:function(data){--%>
		<%--			if(data.msg=="1"){--%>
		<%--				alert("验证成功");--%>
		<%--				window.location.href="/ChildSchool/web/coursemanagement.action";--%>
		<%--			}else if(data.msg=="0"){--%>
		<%--				alert("验证失败");--%>

		<%--			}--%>

		<%--		}--%>
		<%--	});--%>

		<%--}--%>

		<%--function getyzm() {--%>
		<%--	$.ajax({--%>
		<%--		type:"post",--%>
		<%--		url:"${pageContext.request.contextPath}/BackAction/getyzm.action",--%>
		<%--		data:{"phone":yzm},--%>
		<%--		success:function(data){--%>
		<%--			if(data.msg=="1"){--%>
		<%--				alert("验证成功");--%>
		<%--				window.location.href="/ChildSchool/web/coursemanagement.action";--%>
		<%--			}else if(data.msg=="0"){--%>
		<%--				alert("验证失败");--%>

		<%--			}--%>

		<%--		}--%>
		<%--	});--%>
		<%--}--%>

		function getBase64() {
			var imgSrc = document.getElementById("canvas").toDataURL(
				"image/png");
			alert(imgSrc);
			return imgSrc.split("base64,")[1];

		};
	</script>

</body>
</html>
