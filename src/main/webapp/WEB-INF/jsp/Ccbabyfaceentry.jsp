
<%--/**--%>
<%--* 宝宝人脸录入--%>
<%--* by 陈超--%>
<%--*/--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<%
	String path = application.getContextPath();
	String uicssPath = request.getContextPath()+"/layuiadmin/";
	String JsPath = application.getContextPath()+"/js/";
	String cssPath = request.getContextPath()+"/css/";
	String uiPath = request.getContextPath()+"/layuiadmin/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>layuiAdmin 网站用户 iframe 框</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%> media="all">
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
</head>
<body>



<form class="layui-form" action="">
<div class="layui-form" lay-filter="layui-btn-normal" id="layui-btn-normal" style="padding: 20px 0 0 0;">
	<h2 style="text-align: center">人脸识别</h2>
	<dd>
		<input type="button" onclick="query()" value="人脸录入"
		       class="submit_btn" />
	</dd>
<%--	<div class="layui-form-item" style="text-align: center">--%>
<%--		<label class="layui-form-label">职工id</label>--%>
<%--		<div class="layui-input-inline">--%>
<%--			<input type="text"  name="wid" id="wid" lay-verify="required" placeholder="请输入宝宝名称" autocomplete="off" class="layui-input">--%>
<%--		</div>--%>
<%--	</div>--%>
		<div id="media">
			<video id="video" width="530" height="300" autoplay></video>
			<canvas id="canvas" width="400" height="300"></canvas>
		</div>



<%--	</div>--%>

	<div class="layui-form-item layui-hide" style="text-align: center">
		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
	</div>
</div>
</form>

<script src="<%=uiPath+"layui/layui.js"%>"></script>
<script>
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

		//把流媒体数据画到convas画布上去
		context.drawImage(video,0,0,400,400);
		var base = getBase64();
		// var wid=$('#wid').val();

		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/faceentry.action",
			data:{"base":base},
			success:function(data){
				alert(data);

				if(data===1){
					alert("录入成功");
					layer.close(index);
					// parent.location.reload();
				} else {
					alert("录入失败");
				}

			}
		});

	}
	function getBase64() {
		var imgSrc = document.getElementById("canvas").toDataURL(
			"image/png");
		alert(imgSrc);
		return imgSrc.split("base64,")[1];

	}
	layui.use('form', function(){
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

		//……

		//但是，如果你的HTML是动态生成的，自动渲染就会失效
		//因此你需要在相应的地方，执行下述方法来进行渲染
		form.render();
	});
</script>

</body>
</html>
