<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/21
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path=request.getContextPath();

%>
<html>
<head>
	<meta charset="utf-8">
	<title>新增新闻</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

</head>
<body>

<form action="" class="layui-form">


	<div  lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

		<div class="layui-form-item">
			<label class="layui-form-label">发布人</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="wName" name="wName"  style=" border: none;"disabled="disabled">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">标题</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" name="ntitle" id="ntitle"placeholder="请输入标题" style="width: 300px">

			</div>

		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">内容</label>
			<div class="layui-input-block">
				<textarea id="nconntext"  type="text/plain" style="width:300px;height:200px;resize: none;"></textarea>
			</div>
		</div>


<%--		<div class="layui-form-item layui-hide">--%>
<%--			<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">--%>
<%--		</div>--%>

	</div>



	<script>





		layui.use(['form'], function(){
			var form = layui.form;

			layui.form.on('select(class)',function (date) {
				var classname=date.value;
				// alert(classname);

				var ob = {
					cId: classname
				};

				form.render();














			});




		});




	</script>
</form>
</body>
</html>
