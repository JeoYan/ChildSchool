<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>卡入库</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>

<div class="container" style="width: 82%;margin:0 auto">


	<div style="margin-top: 20px;">

		<h2 style="text-align: center ;">
			卡入库
		</h2>
		<div class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 130px">开始卡号：</label>
				<div class="layui-input-inline">
					<input type="text" name="username" id="cardstart"  lay-verify="title" autocomplete="off"
					       value=${requestScope.maxCard}  readonly class="layui-input">
				</div>
			</div>


			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 130px">数量：</label>
				<div class="layui-input-inline">
					<input type="text" name="username" id="cardnum"  lay-verify="title" autocomplete="off"
					       maxlength="4"   class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	//Demo
	layui.use('form', function () {
		var form = layui.form;
		var $ = layui.$;
		//监听提交
		form.on('submit(formDemo)', function (data) {
			var cardnum = $('#cardnum').val();
			var cardstart = $('#cardstart').val();
			if (cardnum == null ||cardnum.length<=0) {
				layer.msg("请输入卡数量", {icon: 5});
			} else {
				var ob = {"cardNum": cardnum, "cardStart": cardstart};
				$.ajax({
					type: "POST",//提交的方式
					url: "/ChildSchool/BackAction/addCards.action",
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据
						if (msg.msg == '1') {
							layer.msg("添加成功", {icon: 6});
							parent.location.reload();
						} else if (msg.msg == '0') {
							layer.msg("添加失败", {icon: 5});
						}

					},
					error: function () {//错误的方法
						layer.msg("服务器正忙", {icon: 5});

					}
				});
			}

		});
	});




</script>

</body>
</html>