<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 9:55
  发布作业界面.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>发布作业界面</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
		<tr>
			<th>班级编号</th>
			<th>班级名称</th>
			<th>作业内容</th>
			<th>布置时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<tr id="content">
			<input id="fileUrl" type="hidden">
			<td>1</td>
			<td>小(1)班</td>
			<td></td>
			<td>${requestScope.date}</td>
			<td><button class="layui-btn" id="postHomework">发布作业</button></td>
		</tr>
		</tbody>
	</table>
	<div class="layui-upload">
		<button type="button" name="file" class="layui-btn layui-btn-normal" id="test8">选择作业</button>
		<button type="button" class="layui-btn" id="test9">开始上传</button>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script>
	layui.use(['upload','layer'], function(){
		var upload = layui.upload;
		var layer = layui.layer;
		var $ = layui.$;
		var $tds = $("#content").find("td");
		var fileUrl;
		//执行上传
		upload.render({
			elem: '#test8'
			,url: 'upload'
			,data: {'flag':'teacher'}
			,auto: false
			//,multiple: true
			,accept:'file'
			,bindAction: '#test9'
			,done: function(json){
				if(json.code === 0){
					return layer.msg('上传失败-------');
				}
				//上传成功
				if(json.code > 0){
					$tds.eq(2).text(json.filename);
					layer.msg(json.url);
					fileUrl =json.url;
					return layer.msg('上传成功--------');
				}

			}
		});
		
		$("#postHomework").on('click',function () {
			var filename = $tds.eq(2).text();
			if ($.trim(filename)!==""){
				$.ajax({
					type:'post',
					url:'/ChildSchool/HomeworkController/postHomework',
					data:{'filename':filename,'fileUrl':fileUrl},
					dataType:'json',
					async:true,
					success:function (json) {
						if ($.trim(json) ==="1"){
							layer.msg("作业发布成功");
						} else {
							layer.msg("作业发布失败");
						}
						$tds.eq(2).text("");
					},
					error:function () {
						layer.msg("数据传输失败");
					}
				});
			} else {
				layer.msg("请先上传作业");
			}

		});
	});
</script>
</html>
