<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>宝宝作业</title>
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
			<th>作业编号</th>
			<th>作业名称</th>
			<th>布置时间</th>
			<th>完成情况</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${not empty requestScope.homework}">
			<c:forEach items="${requestScope.homework}" begin="0" varStatus="status" var="item">
			<tr>
				<td>${item.hid}</td>
				<td>${item.hName}</td>
				<td>${item.hDate}</td>
				<td>未完成</td>
				<td><button class="layui-btn"><a href="/ChildSchool/HomeworkController/downloadHomework?hid=${item.hid}" class="download">下载</a></button><button class="layui-btn postHomework">提交作业</button></td>
			</tr>
		</c:forEach>
		</c:if>
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
			,auto: false
			,data:{'flag':'parent'}
			//,multiple: true
			,accept:'file'
			,bindAction: '#test9'
			,done: function(json){
				if(json.code === 0){
					return layer.msg('上传失败!!');
				}
				//上传成功
				if(json.code > 0){
					$tds.eq(2).text(json.filename);
					layer.msg(json.url);
					fileUrl =json.url;
					return layer.msg('上传成功~~');
				}

			}
		});
		//
		// $(".download").on('click',function () {
		// 	var hid = $(this).parent().parent().find("td:eq(0)").text();
		// 	$.ajax({
		// 		type:'post',
		// 		url:'/ChildSchool/HomeworkController/downloadHomework',
		// 		data:{'hid':hid},
		// 		async:true,
		// 		success:function (json) {
		// 			// if ($.trim(json) ==="1"){
		// 			// 	layer.msg("下载成功");
		// 			// } else {
		// 			// 	layer.msg("下载失败");
		// 			// }
		// 		},
		// 		error:function () {
		// 			layer.msg("数据传输失败");
		// 		}
		// 	});
		// });

		$(".postHomework").on('click',function () {
			var hid = $(this).parent().parent().find("td:eq(0)").text();
			var td3 = $(this).parent().parent().find("td:eq(3)");
			if (fileUrl!==null && fileUrl !==""){
				$.ajax({
					type:'post',
					url:'/ChildSchool/HomeworkController/submitHomework',
					data:{'fileUrl':fileUrl,'hid':hid},
					dataType:'json',
					async:true,
					success:function (json) {
						if ($.trim(json) ==="1"){
							layer.msg("作业提交成功");
							td3.text("已完成");
						} else {
							layer.msg("作业提交失败");
						}
						fileUrl = "";
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
