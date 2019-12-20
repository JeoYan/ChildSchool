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

<form class="layui-form" action="">

	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
		<div style="text-align: center;">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2>发送公告</h2>
				<h4>名字：${requestScope.bybyname} 性别: ${requestScope.bybysex}</h4>

			</div>
		</div>


		<div class="layui-form-item" style="font-size: 20px">
			<label class="layui-form-label">公告</label>
			<div class="layui-input-block">
				<textarea id="vod_content" type="text/plain" style="width:95%;height:150px;resize: none;size: 50px"></textarea>
			</div>
		</div>
		<div class="layui-form-item ">
			<div class="layui-input-block">
			</div>
		</div>

		<div class="layui-form-item ">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit" style="width:95%;">发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布</button>
			</div>
		</div>




	</div>



</form>


<script>

	layui.use('form', function () {
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

		form.render();

		form.on('submit(LAY-user-login-submit)', function (obj) {
			var flag = false;
			var wid = $("#cname").val();
			var passWord = $("#LAY-user-login-password").val();
			var verifyCode = $("#LAY-user-login-vercode").val();
			layer.msg("账号或密码不存在！！！");

			//请求登入接口
			$.ajax({
				async: false,//异步操作
				type: "POST",
				url: "${pageContext.request.contextPath}/workerLogin/loginCheck.action",//注意路径
				data: {wid: wid, passWord: passWord, verifyCode: verifyCode},
				dataType: "text",
				success: function (data) {
					// alert(data);
					if (data === "NotOk") {
						layer.msg("账号或密码不存在！！！");
					} else if (data === "vercodeError") {
						layer.msg("验证码错误！！！");
					} else if (data === "NotExist") {
						layer.msg("账户不存在！！！");
					} else if (data === "StatusLock") {
						layer.msg("账户已被禁用，请联系学校管理员！！！");
					} else {
						//登入成功的提示与跳转
						layer.msg('登入成功', {
							offset: '15px'
							, icon: 1
							, time: 1000
						}, function () {
							location.href = '${pageContext.request.contextPath}/workerLogin/login.action'; //后台主页
						});
					}
				},
				error: function (data) {
					alert("-----失败------" + data);
				}
			});

			return flag;


		});


	});


</script>


</body>
</html>

