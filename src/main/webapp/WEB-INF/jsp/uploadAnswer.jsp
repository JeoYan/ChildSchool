<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>配置试题</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>

<div class="container" style="width: 82%;margin:0 auto ;text-align: center">


	<div style="margin-top: 20px;">

<h2 style="text-align: center ;">
	配置试题
</h2>
<form class="layui-form" action="" lay-filter="example" style="margin-top: 30px;">
	<div class="layui-form-item">
		<label class="layui-form-label">视频编号：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" readonly id="safeId" lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">视频名称：</label>
		<div class="layui-input-inline">
			<input type="text"  id="safeName" readonly lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>
	<div class="demoTable">
		<div style="padding-bottom: 10px;">
			<%--			<button class="layui-btn " data-type="add">增加用户</button>--%>
			<div class="layui-upload">
				<button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
				<button type="button" class="layui-btn" id="test9" style="margin-left: 20px">开始上传</button>
			</div>
		</div>
	</div>

</form>
	</div>
</div>
<script>
	//Demo
	layui.use('form', function () {
		var form = layui.form;

		//监听提交
		form.on('submit(formDemo)', function (data) {
			layer.msg(JSON.stringify(data.field));
			return false;
		});
	});
	layui.use('upload', function () {
		var $ = layui.jquery
			, upload = layui.upload;
		//选完文件后不自动上传
		upload.render({
			elem: '#test8'
			, url: '/ChildSchool/BackAction/uploadAnswer.action'
			, accept: 'file' //普通文件
			,exts: 'doc|docx'
			// ,size: 60 //限制文件大小，单位 KB
			, auto: false

			//,multiple: true
			, bindAction: '#test9'
			,before: function(obj){
				layer.load(1, {
					content: '添加中...',
					success: function (layero) {
						layero.find('.layui-layer-content').css({
							'padding-top': '39px',
							'width': '60px'
						});
					}
				});
				this.data={"safeName": $('#safeName').val(),"safeId": $('#safeId').val()};

			}
			,done: function(res){
				layer.closeAll();
				if(res.msg=="ok"){
					layer.msg('添加成功', {
						time: 1000,
						icon: 1,
						offset: '50px'
					}, function () {
						location.reload()
					});
				}else if(res.msg=="error"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('上传失败');
				}else if(res.msg=="请输入文件名"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('请输入文件名');
				}else if(res.msg=="该文件名已存在"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('该文件名已存在');
				}
			},error: function () {
				layer.closeAll('loading'); //关闭loading
				layer.msg('上传失败');
			}


		});
	});


</script>

</body>
</html>