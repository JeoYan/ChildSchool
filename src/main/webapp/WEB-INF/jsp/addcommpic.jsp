<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/18
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>新增商品</title>
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


<div class="layadmin-user-login-box layadmin-user-login-header">
	<h2>新增商品</h2>
</div>
<div class="layui-inline" style="width:600px;">
	<hr>
</div>

<div class="layadmin-user-login-box layadmin-user-login-header">
	<%--	<form id="myForm" enctype="multipart/form-data" method="post" class="layui-form" action=<%=path+"/mall/doUpload.action"%> lay-filter="example" >--%>
	<form id="myForm" enctype="multipart/form-data" method="post" class="layui-form" action="" lay-filter="example">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">商品名：</label>
				<div class="layui-input-inline" style="width: 400px;">
					<input type="text" class="layui-input" id="commName" name="commName">
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">类型：</label>
				<div class="layui-input-inline" style="width: 400px;">
					<select name="type" id="type">
						<option value="0"></option>
						<option value="3">文娱</option>
						<option value="4">零食</option>
					</select>
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">库存：</label>
				<div class="layui-input-inline" style="width: 400px;">
					<input type="text" id="commNum" class="layui-input" name="commNum">
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">原价：</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" placeholder="￥" class="layui-input" id="price" name="price">
				</div>
				<div class="layui-form-mid">优惠后的价格：</div>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" placeholder="￥" class="layui-input" id="newPrice" name="newPrice">
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">设置成推荐</label>
			<div class="layui-input-block" id="setHome">
				<input type="radio" name="home" value="是" title="是">
				<input type="radio" name="home" value="否" title="否" checked>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">商品描述：</label>
				<div class="layui-input-inline" style="width: 400px">
					<input type="text" class="layui-input" id="commContent" style="height: 120px" name="commContent">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<div style="padding-bottom: 10px;">
					<div class="layui-upload">
						图片<input type="file" name="file" multiple="multiple"/>
						<%--						<input multiple="multiple" type="file" class="layui-btn layui-btn-normal" id="test8" value="选择文件" name="file">--%>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<div class="demoTable">
					<div style="padding-bottom: 10px;">
						<div>
							<input type="submit" class="layui-btn" value="保存" id="test9" style="width: 150px">
							<%--							<button type="button" class="layui-btn" value="保存" id="test9" style="width: 150px">--%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<script>

	layui.use(['form', 'layedit', 'laydate'], function () {
		var form = layui.form
			, layer = layui.layer
			, layedit = layui.layedit
			, laydate = layui.laydate;

	});


	$('#test9').on('click', function (e) {
		e.preventDefault();//阻止按钮默认提交
		$.ajax({
			url: "/ChildSchool/mall/doUpload.action?setHome=" + $('#setHome input[name="home"]:checked').val(),
			type: 'POST',
			data: new FormData($('#myForm')[0]),//序列化表单，$("form").serialize()只能序列化数据，不能序列化文件
			processData: false,
			contentType: false,
			dataType: "json",
			success: function (res) {
				e.preventDefault();//阻止按钮默认提交

					if (res.msg === "ok") {
						alert("上传成功");
						window.parent.location.reload();//刷新父页面
					} else if (res.msg === "err") {
						alert("上传失败");
					} else {
						alert("请先选择文件");
					}

			},
			error: function () {
				alert("掉线啦")
			}
		});

	});


	//文件上传
	<%--layui.use('upload', function () {--%>
	<%--	var $ = layui.jquery--%>
	<%--		, upload = layui.upload;--%>

	<%--	upload.render({--%>
	<%--		elem: '#test8'--%>
	<%--		, url: "${pageContext.request.contextPath}/mall/doUpload.action"--%>
	<%--		, accept: 'file' //普通文件--%>
	<%--		// ,exts: 'zip|rar|7z' //只允许上传压缩文件--%>
	<%--		// ,size: 60 //限制文件大小，单位 KB--%>
	<%--		, auto: false--%>
	<%--		, multiple: true//多文件上传--%>
	<%--		, bindAction: '#test9'--%>
	<%--		, before: function (obj) {--%>

	<%--			// alert($("#commName").val());--%>
	<%--			// alert($("#type").val());--%>
	<%--			// alert($("#commNum").val());--%>
	<%--			// alert($("#price").val());--%>
	<%--			// alert($("#newPrice").val());--%>
	<%--			// alert($('#setHome input[name="home"]:checked').val());--%>
	<%--			// alert($("#commContent").val());--%>


	<%--			obj.preview(function (index, file, result) {--%>
	<%--				// $('#demo1').attr('src', result); //图片链接（base64）--%>
	<%--			});--%>
	<%--			layer.load(1, {--%>
	<%--				content: '添加中...',--%>
	<%--				success: function (layero) {--%>
	<%--					layero.find('.layui-layer-content').css({--%>
	<%--						'padding-top': '39px',--%>
	<%--						'width': '60px'--%>
	<%--					});--%>
	<%--				}--%>
	<%--			});--%>

	<%--			this.data = {--%>
	<%--				"count":obj.upload.length,--%>
	<%--				"commName": $("#commName").val(),--%>
	<%--				"type": $("#type").val(),--%>
	<%--				"commNum": $("#commNum").val(),--%>
	<%--				"price": $("#price").val(),--%>
	<%--				"newPrice": $("#newPrice").val(),--%>
	<%--				"setHome": $('#setHome input[name="home"]:checked').val(),--%>
	<%--				"commContent": $("#commContent").val()--%>
	<%--			};--%>

	<%--		}--%>
	<%--		, done: function (res) {--%>

	<%--			layer.closeAll();--%>

	// }
	// error: function () {
	// 			layer.closeAll('loading'); //关闭loading
	// 			layer.msg('上传失败');
	// 		}


	<%--	});--%>
	<%--});--%>


</script>


</body>
</html>

