<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>新增安全教育视频</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>

<div class="container" style="width: 82%;margin:0 auto">


	<div style="margin-top: 20px;">

<h2 style="text-align: center ;">
	新增安全教育视频
</h2>
<form class="layui-form" action="" lay-filter="example" style="margin-top: 30px;text-align: center">

	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">视频名称：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" id="safeName"  placeholder="请输入视频名称" lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" style="margin:0 auto;text-align: center">
		<label class="layui-form-label" style="width: 130px">选择开始和结束时间</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" readonly id="rangeDate" placeholder="yyyy-MM-dd">
		</div>
	</div>
	<div class="demoTable">
		<div style="padding-bottom: 10px;margin-top: 10px">
			<div class="layui-upload">
				<div><button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
					<button type="button" class="layui-btn"  id="test9" >开始上传</button>

				<%--					<button type="button" class="layui-btn layui-btn-disabled"  disabled="disabled" id="test9" >开始上传</button>--%>
<%--					<span class="layui-inline layui-upload-choose" id="upload-choose"></span>--%>
				</div>
				<div class=" layui-word-aux" >请上传.mp4格式视频</div>
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
			, url: '/ChildSchool/BackAction/uploadVideo.action'
			, accept: 'video'
			,exts: 'mp4' //只允许上传压缩文件
			// ,size: 102400 //限制文件大小，单位 KB
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
				this.data={"safeName": $('#safeName').val(), "rangeDate":$('#rangeDate').val()};

			}
			,done: function(res){
				layer.closeAll();
				if(res.msg=="ok"){
					layer.msg('添加成功', {
						time: 1000,
						icon: 1,
						offset: '50px'
					}, function () {
						parent.location.reload()
					});
				}else if(res.msg=="error"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('上传失败');
				}else if(res.msg=="请输入文件名"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('请输入文件名');
				}else if(res.msg=="请输入时间范围"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('请输入时间范围');
				}else if(res.msg=="该文件名已存在"){
					layer.closeAll('loading'); //关闭loading
					layer.msg('该文件名已存在');
				}
			},error: function () {
				layer.closeAll('loading'); //关闭loading
				layer.msg("服务器正忙", {icon: 5});
			}



		});
	});
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		laydate.render({
			elem: '#rangeDate'
			,position: 'fixed'
			,min: 0
			,range: true

		});


	});


</script>

</body>
</html>