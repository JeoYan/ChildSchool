<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/20
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String uiPath = request.getContextPath() + "/layui/";
%>
<html>
<head>
	<title>体检管理</title>

	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">

	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

</head>
<body>



	<div class=" layadmin-user-display-show" id="LAY-user-login" style="display: none;">
		<div style="text-align: center;">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2>发布公告</h2>
				<h4 id="wName">发布人：${sessionScope.wName}</h4>
				<input id="wid" value="${sessionScope.wid}" type="hidden">

			</div>
		</div>

		<div class="layui-form-item ">
			<div class="layui-input-block">
			</div>
		</div>
		<div class="layui-form-item" style="font-size: 20px">
			<label class="layui-form-label">标题</label>
			<div class="layui-input-block">
				<input type="text" name="title" id="title"placeholder="请输入标题" style="width:95%; size: 50px" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item" style="font-size: 20px">
			<label class="layui-form-label">公告</label>
			<div class="layui-input-block">
				<textarea id="nconntext" type="text/plain" style="width:95%;height:150px;resize: none;size: 50px"></textarea>
			</div>
		</div>
		<div class="layui-form-item ">
			<div class="layui-input-block">
			</div>
		</div>

		<div class="layui-form-item ">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-fluid" id="send"  style="width:95%;">发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布</button>
			</div>
		</div>




	</div>






<script>


	layui.use('form', function () {
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功


		$('#send').on('click', function (obj) {
			var wid = $("#wid").val();
			var notitle = $("#title").val();
			var nconntext = $("#nconntext").val();

			layer.confirm('确定发送？', function (index) {
				layer.close(index);
				if (notitle.length===0){
					layer.msg("请输入标题！");
				}else if (nconntext.length===0){
					layer.msg("请输入公告内容！");
				} else {

					$.ajax({
						async: false,//异步操作
						type: "POST",
						url: "${pageContext.request.contextPath}/addNotice.action",//注意路径
						data: {wid: wid, notitle: notitle, nconntext: nconntext},
						dataType: "json",
						success: function (num) {
							if (num>0) {
								layer.msg("发送成功！");
								$("#title").val("");
								$("#nconntext").val("");
								form.render();
							}else {
								layer.msg("发送失败！");
							}


						},
						error: function (data) {
							alert("-----失败------" + data);
						}
					});

				}


			});


		});

	});


</script>


</body>
</html>

