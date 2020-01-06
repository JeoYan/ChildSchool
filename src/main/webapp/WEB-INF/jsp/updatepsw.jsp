<%--
------------------------------------------------
               修改密码界面
               by 陈超
------------------------------------------------
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
</head>
<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
	<div class="layadmin-user-login-main">
		<div class="layadmin-user-login-box layadmin-user-login-header">
			<h2>智慧幼儿园</h2>
			<p>修改密码</p>
		</div>

		<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
			<div class="layui-form-item">
				<label class="layadmin-user-login-icon layui-icon layui-icon-password"
				       for="LAY-user-login-password"></label>
				<input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
				       placeholder="旧密码" class="layui-input" >
			</div>

			<div class="layui-form-item">
				<label class="layadmin-user-login-icon layui-icon layui-icon-password"
				       for="LAY-user-login-password"></label>
				<input type="password" name="password1" id="LAY-user-login-password1" lay-verify="required"
				       placeholder="新密码" class="layui-input" >
			</div>

			<div class="layui-form-item">
				<label class="layadmin-user-login-icon layui-icon layui-icon-password"
				       for="LAY-user-login-password"></label>
				<input type="password" name="password2" id="LAY-user-login-password2" lay-verify="required"
				       placeholder="确认密码" class="layui-input" >
			</div>

			<div class="layui-form-item" style="margin-bottom: 20px;">
				<%--				<input type="checkbox" name="remember" lay-skin="primary" title="记住密码">--%>
				<a class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;"
				   href=<%=path + "/parentLogin/parentForgetPage.action"%>>忘记密码？</a>
			</div>

			<div class="layui-form-item">
				<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">确定修改</button>
			</div>

<%--			<div class="layui-trans layui-form-item layadmin-user-login-other">--%>
<%--				&lt;%&ndash;				<label>社交账号登入</label>&ndash;%&gt;--%>
<%--				&lt;%&ndash;				<a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>&ndash;%&gt;--%>
<%--				&lt;%&ndash;				<a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>&ndash;%&gt;--%>
<%--				&lt;%&ndash;				<a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>&ndash;%&gt;--%>
<%--				<a href="reg.html" class="layadmin-user-jump-change layadmin-link">注册帐号</a>--%>
<%--			</div>--%>
		</div>
	</div>

	<div class="layui-trans layadmin-user-login-footer">

		<p>© 2019 Design by Jeo</p>
		<%--		<p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>--%>
		<%--		<p>--%>
		<%--			<span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>--%>
		<%--			<span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>--%>
		<%--			<span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>--%>
		<%--		</p>--%>
	</div>

<input type="hidden" id="wid" value="${sessionScope.wid}">

</div>

<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
<script>

	//点击验证码图片获得获得验证码
	<%--$("#imgVerify").on('click', function () {--%>
	<%--	$("#imgVerify").attr("src", "${pageContext.request.contextPath}/workerLogin/getVerCode.action?"--%>
	<%--		+ Math.random());--%>
	<%--});--%>

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
			var flag=false;
			// var field =obj.field;
			var wid=$("#wid").val();
			var oldpsw = $("#LAY-user-login-password").val();
			var newpsw= $("#LAY-user-login-password1").val();
			var newpsw1 = $("#LAY-user-login-password2").val();
			alert(wid);



			<%--if(field.newpsw !==field.newpsw1){--%>
			<%--	return layer.msg('两次密码输入不一致');--%>
			<%--}--%>

			<%--//请求接口--%>
			<%--admin.req({--%>
			<%--	url: layui.setter.base + 'json/user/resetpass.js' //实际使用请改成服务端真实接口--%>
			<%--	,data: field--%>
			<%--	,done: function(res){--%>
			<%--		layer.msg('密码已成功重置', {--%>
			<%--			offset: '15px'--%>
			<%--			,icon: 1--%>
			<%--			,time: 1000--%>
			<%--		}, function(){--%>
			<%--			location.href = '${pageContext.request.contextPath}/workerLogin/workerLoginPage.action'; //跳转到登入页--%>
			<%--		});--%>
			<%--	}--%>
			<%--});--%>

			<%--return false;--%>

			$.ajax({
				async: false,//异步操作
				type: "POST",
				url: "${pageContext.request.contextPath}/workerLogin/pswCheck.action",//注意路径
				data: {wid : wid,oldpsw: oldpsw, newpsw: newpsw,newpsw1: newpsw1},
				dataType: "text",
				success: function (data) {
					// alert(data);
					if (data ==="NotNull") {
						layer.msg("密码不能为空！！！");
					}else if(data==="newpswError"){
						layer.msg("新密码两次输入不一致！！！");
					} else if(data==="NotExist"){
						layer.msg("原密码不存在！！！");
					}else{
						//登入成功的提示与跳转
						layer.msg('修改成功', {
							offset: '15px'
							, icon: 1
							, time: 1000
						}, function () {
							location.href = '${pageContext.request.contextPath}/workerLogin/workerLoginPage.action'; //后台主页
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
