
<%--/**--%>
<%--* 修改密码界面--%>
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
<body style="background-image: url(<%=path+"/image/back3.jpg"%>);background-repeat: no-repeat;background-size: 100% 100%;">



<form class="layui-form" action="" style="padding: 100px 5px 0 0;" >
	<h1 style="text-align: center">修改密码</h1>
	<div class="layui-form" lay-filter="layui-btn-normal" id="layui-btn-normal" style="padding: 20px  0 0 500px;">
		<div class="layui-form-item" style="text-align: center">
			<label class="layui-form-label">原密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
				       placeholder="旧密码" class="layui-input" >
			</div>
		</div>

		<div class="layui-form-item" style="text-align: center">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password1" id="LAY-user-login-password1" lay-verify="required"
				       placeholder="新密码" class="layui-input" >
			</div>
		</div>

		<div class="layui-form-item" style="text-align: center">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password2" id="LAY-user-login-password2" lay-verify="required"
				       placeholder="确认密码" class="layui-input" >
			</div>
		</div>

		<div class="layui-form-item"  style="padding: 30px  50px 0 101px;">
			<button style="width: 30%" class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">确定修改</button>
			<a href="/ChildSchool/workerLogin/login.action" >返回</a>
		</div>


		<input type="hidden" id="wid" value="${sessionScope.wid}">
	</div>
</form>

<script src="<%=uiPath+"layui/layui.js"%>"></script>
<script>
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

			var wid=$("#wid").val();
			var oldpsw = $("#LAY-user-login-password").val();
			var newpsw= $("#LAY-user-login-password1").val();
			var newpsw1 = $("#LAY-user-login-password2").val();
			alert(wid);

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
