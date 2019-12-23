<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/18
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>家长修改密码</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
	<style>
		#div1 {
			background-color: rgba(161, 204, 112, 0.5);
			width: 400px;
			height: 300px;
			margin: auto; /*块左右居中*/
			margin-top: 100px; /*块距离顶部距离*/
			text-align: center; /*内容居中*/
			padding-top: 50px; /*块里面内容距离顶部距离*/
			box-sizing: border-box; /*防止被扩撑*/
			border-radius: 10px; /*圆角*/
		}
	</style>
</head>
<body>
<div id="div1">
	<div class="layui-form" action="">

		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 120px">请输入旧密码:</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="oldPassword" required lay-verify="required"
				       placeholder="请输入旧密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 120px">请输入新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="newPassword" required lay-verify="required"
				       placeholder="请输入新密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 120px">请再次输入新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="reNewPassword" required lay-verify="required"
				       placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			</div>
		</div>
	</div>
</div>
<script>
	//Demo
	layui.use('form', function () {
		var form = layui.form;
		var $ = layui.$;
		//监听提交
		form.on('submit(formDemo)', function (data) {
			var oldPassword = $('#oldPassword').val();
			var newPassword = $('#newPassword').val();
			var reNewPassword = $('#reNewPassword').val();
			if (newPassword !== reNewPassword) {
				layer.msg("两次密码不一致", {icon: 5});
			} else {
				var ob = {"oldPassword": oldPassword, "newPassword": newPassword, "reNewPassword": reNewPassword};
				$.ajax({
					type: "POST",//提交的方式
					url: "/ChildSchool/BackAction/parentChangePassword.action",
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据
						if (msg.msg === '0') {
							layer.msg("两次密码不一致", {icon: 5});
						} else if (msg.msg === '1') {
							layer.msg("旧密码错误", {icon: 5});
						} else if (msg.msg === '2') {
							layer.msg("修改成功,新密码为:" + newPassword, {icon: 6});
						} else if (msg.msg === '3') {
							layer.msg("修改失败", {icon: 5});
						}
						$('#oldPassword').val('');
						$('#newPassword').val('');
						$('#reNewPassword').val('');

					},
					error: function () {//错误的方法
						layer.msg("服务器正忙", {icon: 5});
						$('#oldPassword').val('');
						$('#newPassword').val('');
						$('#reNewPassword').val('');
					}
				});
			}

		});
	});


</script>
</body>
</html>
