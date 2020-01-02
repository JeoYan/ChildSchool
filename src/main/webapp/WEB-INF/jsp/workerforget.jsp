<%--
------------------------------------------------
               家长登入忘记密码，密码找回界面
               by 严俊杰
------------------------------------------------
--%>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17 0017
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>

<html>
<head>
	<meta charset="utf-8">
	<title>密码找回</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">

</head>
<body>
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
	<div class="layadmin-user-login-main">
		<div class="layadmin-user-login-box layadmin-user-login-header">
			<h2>密码找回</h2>
			<%--			<p>layui 官方出品的单页面后台管理模板系统</p>--%>
		</div>
		<div class="layadmin-user-login-box layadmin-user-login-body layui-form">


			<div class="layui-form-item">
				<label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"
				       for="LAY-user-login-cellphone"></label>
				<input type="text" name="cellphone" id="LAY-user-login-cellphone" lay-verify="phone"
				       placeholder="请输入注册时的手机号" class="layui-input">
			</div>
			<%--				<div class="layui-form-item">--%>
			<%--					<div class="layui-row">--%>
			<%--						<div class="layui-col-xs7">--%>
			<%--							<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>--%>
			<%--							<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">--%>
			<%--						</div>--%>
			<%--						<div class="layui-col-xs5">--%>
			<%--							<div style="margin-left: 10px;">--%>
			<%--								<img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">--%>
			<%--							</div>--%>
			<%--						</div>--%>
			<%--					</div>--%>
			<%--				</div>--%>

			<div class="layui-form-item">
				<div class="layui-row">
					<div class="layui-col-xs7">
						<label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
						       for="LAY-user-login-smscode"></label>
						<input type="text" name="vercode" id="LAY-user-login-smscode" lay-verify="required"
						       placeholder="短信验证码" class="layui-input">
					</div>
					<div class="layui-col-xs5">
						<div style="margin-left: 10px;">
							<button type="button" class="layui-btn layui-btn-primary layui-btn-fluid"
							        id="LAY-user-getsmscode">获取验证码
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-forget-submit">找回密码</button>
			</div>
			<div class="layui-trans layui-form-item layadmin-user-login-other">
				<a class="layadmin-user-jump-change layadmin-link" href=<%=path + "/workerLogin/workerLoginPage.action"%>>登入</a>
			</div>

		</div>
	</div>

	<div class="layui-trans layadmin-user-login-footer">

		<p>© 2019 Design by Jeo</p>
		<%--		<p>--%>
		<%--			<span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>--%>
		<%--			<span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>--%>
		<%--			<span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>--%>
		<%--		</p>--%>
	</div>

</div>

<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
<script>
	layui.config({
		base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'user'], function () {
		var $ = layui.$
			, setter = layui.setter
			, admin = layui.admin
			, form = layui.form
			, router = layui.router();

		form.render();

		//找回密码下一步
		form.on('submit(LAY-user-forget-submit)', function (obj) {
			var field = obj.field;

			var phone=$("#LAY-user-login-cellphone").val();
			var msgCode=$("#LAY-user-login-smscode").val();
			// alert(phone+msgCode);
            var msg={'phone':phone,'msgCode':msgCode};

			$.ajax({
				async: false,//异步操作
				type: "POST",
				url: "${pageContext.request.contextPath}/workerLogin/getPsw.action",//注意路径
				data: msg,
				dataType: "text",
				success: function (data) {

					if(data==="NotOk"){
						alert("请先获取验证码");
					}else if(data==="msgError"){
						alert("验证码错误");
					}else if(data==="workerNotExist"){
						alert("账户不存在");
					}else{
						alert("你的密码为"+data+",请保管好密码！");
					}


				},
				error: function (data) {
					alert("-----失败------" + data);
				}
			});


			return false;
		});


	});
</script>
</body>
</html>
