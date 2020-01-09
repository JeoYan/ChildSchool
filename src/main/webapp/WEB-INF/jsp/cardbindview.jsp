<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>修改安全教育视频</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>

<div class="container" style="width: 82%;margin:0 auto">


	<div style="margin-top: 20px;">

<h2 style="text-align: center ;">
	绑定宝宝
</h2>
<form class="layui-form" action="" lay-filter="example" style="margin-top: 30px;text-align: center">

	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">卡片编号：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" id="cardNum" readonly  lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">导入时间：</label>
		<div class="layui-input-inline">
			<input type="text" name="username" id="cardTime" readonly lay-verify="title" autocomplete="off"
			       class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">选择宝宝</label>
		<div class="layui-input-inline" style="width: 300px">
			<select name="bid" lay-filter="bid" id="bid" style="width: 300px">
				<c:forEach items="${requestScope.cards}" begin="0" step="1" var="i">
					<option value="${i.bid}">宝宝编号：${i.bid}，宝宝姓名：${i.bName}</option>
				</c:forEach>
			</select>

		</div>
	</div>

	<div>
		<div style="padding-bottom: 10px;margin-top: 10px">
			<div>
				<div>
					<button type="button" class="layui-btn" onclick="bindCard()">绑定</button>
				</div>
			</div>
		</div>
	</div>

</form>
	</div>
</div>
<script>
	//Demo
	var $;
	layui.use('form', function () {
		var form = layui.form;
		$=layui.$;
	});

	function bindCard() {
		layer.confirm('确定绑定吗', function (index) {
			if ($("#bid").val()!=null && $("#bid").val()!='') {
				var ob = {"cardNum": $("#cardNum").val(),"bid": $("#bid").val()};
				$.ajax({
					url: '/ChildSchool/BackAction/bindCard.action',
					type: "POST",
					data: ob,
					dataType: "json",
					success: function (msg) {
						if (msg.msg === "ok") {
							layer.msg('绑定成功', {
								time: 1000,
								icon: 1,
								offset: '50px'
							}, function () {
								parent.location.reload()
							});
						} else if (msg.msg === "error") {
							layer.msg("绑定失败", {icon: 5});
						}

					},
					error: function () {
						layer.msg("服务器正忙", {icon: 5});
					}
				});
			}else {
				layer.msg("没有需要绑定的宝宝", {icon: 5});
			}

		});
	}


</script>

</body>
</html>