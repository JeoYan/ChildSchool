<%--
  Created by IntelliJ IDEA.
  User: 陈美杰
  Date: 2019/12/24
  Time: 16:57
  显示宝宝考勤信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>班级考勤信息</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-row" id="lookBabySign" style="display:none;">
	<div>
		<h1>宝宝考勤</h1>
		<div class="layui-form-item">
			<label class="layui-form-label">宝宝姓名：</label>
			<label class="layui-form-label" id="showBabyName"></label>
			<label class="layui-form-label">宝宝性别：</label>
			<label class="layui-form-label" id="showBabySex"></label>
		</div>
		<table id="babySignInfo" border="1px">
			<tr id="dateTr">
				<td>日期</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
			</tr>
			<tr id="aTr">
				<td>上午</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
			</tr>
			<tr id="pTr">
				<td>下午</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
			</tr>
		</table>
		<button class="layui-btn" id="pre">上一周</button>
		<button class="layui-btn" id="next">下一周</button>
	</div>
</div>
</body>
</html>
