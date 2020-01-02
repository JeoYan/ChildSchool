<%--
------------------------------------------------
               家长登入界面
               by 严俊杰
------------------------------------------------
--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17 0017
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="utf-8">
	<title>家长登入</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<link rel="stylesheet" media="screen" href=<%=path+"/css/loginparent.css"%>>
</head>
<body>

<!-- particles.js container -->
<div id="particles-js" style="display: flex;align-items: center;justify-content: center">
</div>
<div class="login-page">
	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2 style="color: white;">智慧幼儿园</h2>
				<p style="color: white;">家长登入</p>
			</div>
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-username"
					       for="LAY-user-login-username"></label>
					<input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="手机号"
					       class="layui-input" value="13860366486">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-password"
					       for="LAY-user-login-password"></label>
					<input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
					       placeholder="密码" class="layui-input" value="123">
				</div>
				<div class="layui-form-item">
					<div class="layui-row">
						<div class="layui-col-xs7">
							<label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
							       for="LAY-user-login-vercode"></label>
							<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required"
							       placeholder="图形验证码" class="layui-input" value="1">
						</div>

						<div class="layui-col-xs5">
							<div style="margin-left: 10px;">
								<img id="imgVerify" src="<%=path+"/parentLogin/getVerCode.action"%>" alt="更换验证码" height="36"
								     width="100%">

							</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" style="margin-bottom: 20px;">
					<%--				<input type="checkbox" name="remember" lay-skin="primary" title="记住密码">--%>
					<a class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;"
					   href=<%=path + "/parentLogin/parentForgetPage.action"%>>忘记密码？</a>
				</div>
				<div class="layui-form-item">
					<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
				</div>
<%--				<div class="layui-trans layui-form-item layadmin-user-login-other">--%>
				<%--					<a href=<%=path + "/parentLogin/parentRegister.action"%> class="layadmin-user-jump-change layadmin-link">注册帐号</a>--%>
				<%--				</div>--%>
			</div>
		</div>
<%--			<div class="layui-trans layadmin-user-login-footer">--%>

<%--				<p style="color: white;">© 2019 Design by Jeo</p>--%>
<%--			</div>--%>
	</div>
</div>









<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
<!-- scripts -->
<script src=<%=path + "/js/particles.js"%>></script>
<script src=<%=path + "/js/app.js"%>></script>
<script>

	//点击验证码图片获得获得验证码
	$("#imgVerify").on('click', function () {
		$("#imgVerify").attr("src", "${pageContext.request.contextPath}/parentLogin/getVerCode.action?"
			+ Math.random());
	});

	layui.config({
		base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'user'], function () {
		var setter = layui.setter
			, admin = layui.admin
			, form = layui.form
			, router = layui.router()
			, search = router.search;
		form.render();
		//提交
		form.on('submit(LAY-user-login-submit)', function (obj) {
			var flag = false;
			var uPhone = $("#LAY-user-login-username").val();
			var passWord = $("#LAY-user-login-password").val();
			var verifyCode = $("#LAY-user-login-vercode").val();
			//请求登入接口
			$.ajax({
				async: false,//异步操作
				type: "POST",
				url: "${pageContext.request.contextPath}/parentLogin/loginCheck.action",//注意路径
				data: {uPhone: uPhone, passWord: passWord, verifyCode: verifyCode},
				dataType: "text",
				success: function (data) {
					// alert(data);
					if (data === "NotOk") {
						layer.msg("账号或密码不存在！！！");
					} else if (data === "vercodeError") {
						layer.msg("验证码错误！！！");
					} else {
						//登入成功的提示与跳转
						layer.msg('登入成功', {
							offset: '15px'
							, icon: 1
							, time: 1000
						}, function () {
							location.href = '${pageContext.request.contextPath}/parentLogin/login.action'; //后台主页
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
