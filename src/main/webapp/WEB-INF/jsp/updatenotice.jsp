<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/21
  Time: 1:28
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
	<title>公告增加框</title>
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
			<input id="wid" value="" type="hidden">
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="wName" name="wName" value="" style=" border: none;"disabled="disabled">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">标题</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" name="notitle" id="notitle"placeholder="请输入标题" >

			</div>

		</div>

		<%--		<div class="layui-form-item" >--%>
		<%--			<label class="layui-form-label">菜名</label>--%>
		<%--			<div class="layui-input-inline">--%>
		<%--				<input type="text" name="high" id="high"   lay-verify="high"   placeholder="请输入身高" autocomplete="off" class="layui-input">--%>
		<%--			</div>--%>
		<%--		</div>--%>

		<div class="layui-form-item">
			<label class="layui-form-label">内容</label>
			<div class="layui-input-block">
				<textarea id="nconntext"  type="text/plain" style="width:190px;height:150px;resize: none;"></textarea>
			</div>
		</div>


		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
		</div>

	</div>



	<script>


		<%--	// $("#class-id").change(function () {--%>
		<%--	// 	alert("2222")--%>
		<%--	// });--%>


		layui.use(['form'], function(){
			var form = layui.form;

			layui.form.on('select(class)',function (date) {
				var classname=date.value;
				// alert(classname);

				var ob = {
					cId: classname
				};
				// $.ajax({
				//
				// 	type: "POST",//提奥的方式
				// 	url: "/ChildSchool/findBaby.action",//提交的地址
				// 	data: ob ,//提交的数据
				// 	dataType: "json",//希望返回的数据类型
				// 	async: true,//异步操作
				// 	success: function (list) {//成功的方法  msg为返回数据
				// 		// alert(list.length);
				// 		$("#babyname").html("");
				// 		var babyName="";
				// 		for (var i = 0; i <list.length ; i++) {
				// 			babyName +='<option value="'+list[i].bId+'">'+list[i].bName+'</option>   '
				// 		}
				// 		$("#babyname").append(babyName);
				form.render();
				// 	},
				// 	error: function () {//错误的方法
				// 		alert("服务器未找到")
				// 	}
				// });

			});




		});




	</script>
</form>
</body>
</html>
