<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>安全教育试题</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="container" style="width: 90%;margin:0 auto">
	<div style="margin-top: 30px;">
		<div>
			<h1 style="text-align: center" >
				安全教育试题
			</h1>
		</div>
		<div>
			<h2 style="text-align: left">
				判断题：每小题1分，共${requestScope.questionNum}分
			</h2>
		</div>
		<div class="layui-form">
			<table class="layui-table">
				<colgroup>
					<col width="6%">
					<col width="35%">
					<col width="35%">
					<col width="12%">
					<col width="12%">

				</colgroup>
				<c:if test="${requestScope.tableBody!=null}">
					<c:forEach items="${requestScope.tableBody}" var="i" begin="0" step="1" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td colspan="4">${i.question}</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="radio" name=${i.questionId} value=${i.optionA} title=${i.optionA}></td>
							<td><input type="radio" name=${i.questionId} value=${i.optionB} title=${i.optionB}></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				</div>
			</div>

		</div>
	</div>
</div>
<script>
	var layer;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		layer = layui.layer;
		$ = layui.$;

		//监听提交
		form.on('submit(formDemo)', function(data){
			if (${requestScope.type == 0}){
				alert("试题已过期");
				location.reload();
				layer.close(index); //关闭弹窗
			}else if (${requestScope.type == 1}) {
				alert("考试未开始");
				location.reload();
				layer.close(index); //关闭弹窗
			}else {
				var ob = {"answer": JSON.stringify(data.field)};
				$.ajax({
					type: "POST",//提交的方式
					url: '/ChildSchool/BackAction/submitAnswers.action?safeId='+${requestScope.safeId},
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据

						if (msg.msg === "0") {
							alert("提交失败");
							location.reload();
							layer.close(index); //关闭弹窗
						} else {
							alert("您已提交答案，得分是："+msg.msg+"分");
							parent.location.reload();
							layer.close(index); //关闭弹窗
						}


					},
					error: function () {//错误的方法
						alert("服务器正忙")
					}
				});
			}

		});
	});



</script>
</body>
</html>