<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/19
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%--/**--%>
<%--* 教师管理-增加界面--%>
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
	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">教师名称</label>
		<div class="layui-input-inline">
			<input type="text"  name="wname" id="wname" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item" >
		<div class="layui-inline">
			<label class="layui-form-label">角色选择</label>
			<div class="layui-input-inline">
				<select name="rname" id="rname">
					<option value="">请选择角色</option>

					<c:forEach items="${requestScope.role}" begin="0" step="1" var="y">
						<option value="${y.rid}">${y.rname}</option>
					</c:forEach>

				</select>
			</div>
		</div>

	</div>

	<div class="layui-form-item" >
		<label class="layui-form-label">人脸录入</label>
		<div  id="media">
			<video id="video"  autoplay style="width: 200px; height: 142px; margin-top: 10px; border-radius: 155%;"></video>
			<canvas id="canvas" width="400" height="300" hidden></canvas>
		</div>
	</div>





	<div class="layui-inline">
		<label class="layui-form-label">性别</label>
		<div class="layui-inline">
			<select name="wsex"  id="wsex">
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
		</div>
	</div>


	<div class="layui-form-item" >
		<label class="layui-form-label">出生年月</label>
		<div class="layui-inline">
			<input class="layui-input" type="date" name="wbrith" id="wbrith" >
		</div>
	</div>

	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">教师名称</label>
		<div class="layui-input-inline">
			<input type="text"  name="wphone" id="wphone" lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
		</div>
	</div>



<%--	<div id="media">--%>
<%--		<video id="video" width="530" height="300" autoplay></video>--%>
<%--		<canvas id="canvas" width="400" height="300"></canvas>--%>
<%--	</div>--%>


<%--	<dd>--%>
<%--		<input class="layui-btn layui-btn-fluid" type="button" onclick="query()" value="立即添加"--%>
<%--		       class="submit_btn" />--%>
<%--	</dd>--%>


	<div class="layui-form-item" style="text-align: center">
		<input class="layui-btn " type="button" onclick="query()" value="立即添加"
		       class="submit_btn" />
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
		context.drawImage(video,0,0,400,300);
		var base = getBase64();
		var wname=$("#wname").val();
		var wsex=$("#wsex").val();
		var wbrith=$("#wbrith").val();
		var rname=$("#rname").val();
		var wphone=$("#wphone").val();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/addteacher.action",
			data:{"wface":base,"wname":wname,"wsex":wsex, "wbrith":wbrith,"rid":rname,"wphone":wphone},
			success:function(i){
				if(i===1){
					alert("添加成功")
					parent.location.reload();
					 // dicTable.reload();
					 layer.close(index); //关闭弹窗
				}else if(i===2) {
					alert("添加失败");
				}else if(i===3) {
					alert("该人脸录入");
				}

			}
		});

	}



	function getBase64() {
		var imgSrc = document.getElementById("canvas").toDataURL(
			"image/png");
		alert(imgSrc);
		return imgSrc.split("base64,")[1];

	};


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
