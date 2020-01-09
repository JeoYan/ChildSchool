<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>新增试题</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>

<div class="container" style="width: 82%;margin:0 auto">


	<div style="margin-top: 20px;">

<h2 style="text-align: center ;">
	新增试题
</h2>
<form class="layui-form" action="" lay-filter="example" style="margin-top: 30px;text-align: center">

	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">试题：</label>
		<div class="layui-input-inline">
			<textarea name="" required lay-verify="required" id="question" placeholder="请输入" class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">选项A：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" id="optiona"  lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">选项B：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" id="optionb"  lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">答案：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" id="answer"  lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>

</form>
	</div>
</div>
<script>
	//Demo
	layui.use('form', function () {
		var form = layui.form;

	});



</script>

</body>
</html>