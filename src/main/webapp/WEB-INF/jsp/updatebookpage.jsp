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
	<title>新增图片</title>
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


<form class="layui-form" action="" lay-filter="example">
	<div class="layadmin-user-login-box layadmin-user-login-header">
		<h2>新增图片</h2>
	</div>
	<div class="layui-inline" style="width:500px;">
		<hr>
	</div>
	<div class="layadmin-user-login-box layadmin-user-login-header">
		<%--绘本名字--%>
			<div class="layui-form-item">
				<label class="layui-form-label">新增的书名：</label>
				<div class="layui-input-inline">
					<input type="text" name="documentScore" id="hideBookName" lay-verify="title" autocomplete="off"
					       class="layui-input"style="width: 300px" readonly>
				</div>
			</div>

		<div style="padding-bottom: 10px;">
			<%--			<button class="layui-btn " data-type="add">增加用户</button>--%>
			<div class="layui-upload">
				<input type="hidden" id="hidebpid">
				<button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">内容信息：</label>
			<div class="layui-input-inline">
				<input type="text" name="documentScore" id="pageContent" lay-verify="title" autocomplete="off"
				       placeholder="请输入绘本信息"
				       class="layui-input" style="width: 300px;height: 120px">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">新页数：</label>
			<div class="layui-input-inline">
				<input type="text" name="documentScore" id="pageNum" lay-verify="title" autocomplete="off"
				      readonly
				       class="layui-input"style="width: 300px">
			</div>
		</div>

		<div class="demoTable">
			<div style="padding-bottom: 10px;">
				<div class="layui-upload">
					<button type="button" class="layui-btn" id="test9">保存</button>
				</div>
			</div>
		</div>
	</div>
</form>

<script>

	//文件上传
	layui.use('upload', function () {
		var $ = layui.jquery
			, upload = layui.upload;
		//选完文件后不自动上传
		upload.render({
			elem: '#test8'
			, url: "${pageContext.request.contextPath}/readBook/doUpdate.action"
			, accept: 'file' //普通文件
			// ,exts: 'zip|rar|7z' //只允许上传压缩文件
			// ,size: 60 //限制文件大小，单位 KB
			, auto: false
			// ,multiple: true//多文件上传
			, bindAction: '#test9'
			, before: function (obj) {
				var fileName='';
				obj.preview(function (index, file, result) {
					// $('#demo1').attr('src', result); //图片链接（base64）

				});
				layer.load(1, {
					content: '添加中...',
					success: function (layero) {
						layero.find('.layui-layer-content').css({
							'padding-top': '39px',
							'width': '60px'
						});
					}
				});

				this.data = {
					"bpid": $("#hidebpid").val(),
					"bookName": $("#hideBookName").val(),
					"pageContent":$("#pageContent").val(),
					"pageNum":$("#pageNum").val()
				};

			}
			, done: function (res) {
				layer.closeAll();
				if (res.msg === "ok") {
					layer.msg('修改成功', {
						time: 1000,
						icon: 1,
						offset: '50px'
					});
					// window.parent.location.reload();//刷新父页面

				} else if (res.msg === "err") {
					layer.closeAll('loading'); //关闭loading
					layer.msg('上传失败');
				}
			}, error: function () {
				layer.closeAll('loading'); //关闭loading
				layer.msg('上传失败');
			}


		});
	});



















</script>



</body>
</html>

