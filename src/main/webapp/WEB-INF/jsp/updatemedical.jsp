<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String jspath = application.getContextPath();
	String path=request.getContextPath();

%>
<html>
<head>
	<meta charset="utf-8">
	<title>体检管理修改框</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

</head>
<body>




<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
	<div class="layui-form-item">
		<label class="layui-form-label">班级</label>
		<div class="layui-input-inline">
			<input type="text" disabled="disabled" name="class" id="class" lay-verify="required" placeholder="请输入名字" autocomplete="off" class="layui-input" style=" border: none;">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">宝宝名字</label>
		<div class="layui-input-inline">
			<input type="text" disabled="disabled" name="babyname" id="babyname" lay-verify="required" placeholder="请输入名字" autocomplete="off" class="layui-input" style=" border: none;">
		</div>
	</div>




	<div class="layui-form-item" >
		<label class="layui-form-label">身高</label>
		<div class="layui-input-inline">
			<input type="text" name="high" id="high"   lay-verify="high"  placeholder="请输入身高" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">体重</label>
		<div class="layui-input-inline">
			<input type="text" name="weight" id="weight"placeholder="请输入体重" autocomplete="off" class="layui-input">
		</div>




	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">视力</label>
		<div class="layui-input-inline">
			<input type="text" name="vision" lay-verify="vision" id="vision" placeholder="请输入视力" autocomplete="off" class="layui-input">
		</div>
	</div>


	<div class="layui-form-item">
		<label class="layui-form-label">体温</label>
		<div class="layui-input-inline">
			<input type="text" name="temperature" id="temperature" placeholder="请输入体温" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">健康状况</label>
		<div class="layui-input-inline">
			<input type="text" name="health"  id="health" placeholder="请输入状况" autocomplete="off" class="layui-input">
		</div>
	</div>







	<%--	<div class="layui-form-item">--%>
	<%--		<label class="layui-form-label">头像</label>--%>
	<%--		<div class="layui-input-inline">--%>
	<%--			<input type="text" name="avatar" lay-verify="required" placeholder="请上传图片" autocomplete="off" class="layui-input" >--%>
	<%--		</div>--%>
	<%--		<button style="float: left;" type="button" class="layui-btn" id="layuiadmin-upload-useradmin">上传图片</button>--%>
	<%--	</div>--%>





	<div class="layui-form-item layui-hide">
		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
	</div>
</div>

<script>
	layui.config({
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'form', 'upload'], function(){
		var $ = layui.$
			,form = layui.form
			,upload = layui.upload ;

		// form.verify(
		// 	//数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		// 	high: [
		// 		/^1[34578]\d{9}$/
		// 		,'手机号码1[3-8]xxxxxxxxx'
		// 	]
		// });

		// {
	})





</script>
</body>
</html>
