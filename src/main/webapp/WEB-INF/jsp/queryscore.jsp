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
							<td><input  type="radio"   name=${i.questionId} value=${i.optionA} title=${i.optionA} <c:if test="${i.optionA == i.myAnswer}">checked="checked"
							</c:if>></td>
							<td><input type="radio"  name=${i.questionId} value=${i.optionB} title=${i.optionB} <c:if test="${i.optionB == i.myAnswer}">checked="checked"
							</c:if>></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>答案是：${i.answer}</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="layui-form-item">
				<div class="layui-input-block">
					总分：${requestScope.totalScore}
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
	});



</script>
</body>
</html>