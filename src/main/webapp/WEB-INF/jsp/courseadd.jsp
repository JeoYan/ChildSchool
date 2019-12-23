<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
	String jsPath = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>排课</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
</head>
<body>


<div class="layui-form" action="" lay-filter="example">

	<div class="layui-form-item">
		<label class="layui-form-label">科目</label>
		<div class="layui-input-block" style="width: 60%">
			<select name="subjects" lay-filter="subjects" id="subjects" >
<%--				<option value="-1"></option>--%>
				<c:forEach items="${requestScope.subjects}" begin="0" step="1" var="i">
					<option value="${i.subId}">${i.subName}</option>
				</c:forEach>
			</select>

		</div>
	</div>


</div>
<script>
	//Demo
	layui.use('form', function () {
		var form = layui.form;
	});

</script>

</body>
</html>