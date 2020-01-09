<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/10/13
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
	String path=request.getContextPath();

%>
<html>
<head>
	<meta charset="utf-8">
	<title>卡充值</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
	<style>
		/*.btn {*/
		/*	width: 200px;*/
		/*	margin-left: 41%;*/
		/*}*/
		/*.form-control {*/
		/*	width: 50%;*/
		/*	display: inline-block;*/
		/*	clear: both;*/
		/*}*/
		/*.ag {*/
		/*	width: 15%;*/
		/*}*/
		/*.name {*/
		/*	padding-left: 20%;*/
		/*}*/
		/*.form-group {*/
		/*	margin-top: 40px;*/
		/*}*/
	</style>
</head>
<body>

<form class="layui-form" target="_blank" method="post" action="<%=path+"/BackAction/myAlipay.action"%>" style="margin-left: 32%;margin-top: 20px ">



	<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">


	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">卡号：</label>
		<div class="layui-input-inline">
			<input type="text" name="cardNum" readonly id="cardNum" placeholder="请输入" lay-verify="title"
			       class="layui-input"     autocomplete="off" value=${requestScope.cardNum}	 >
		</div>
	</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 130px">宝宝姓名：</label>
			<div class="layui-input-inline">
				<input type="text" name="bName" readonly id="bName" placeholder="请输入" lay-verify="title"
				       class="layui-input"       autocomplete="off" value=${requestScope.bName} >

			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 130px">卡余额：</label>
			<div class="layui-input-inline">
				<input type="text" name="oldMoney" readonly id="oldMoney" placeholder="" lay-verify="title"
				       class="layui-input"    autocomplete="off"  value=${requestScope.cardMoney}>
			</div>
			<label class="layui-form-label" style="width: 0px">元</label>
		</div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width: 130px">请输入充值金额：</label>
		<div class="layui-input-inline">
			<input type="text" name="rechargeMoney" id="rechargeMoney" placeholder="支付成功后请刷新页面" lay-verify="title"
				       autocomplete="off"
				       class="layui-input" maxlength="4" onkeyup="value=value.replace(/[^0-9]/g,'')">
		</div>
	</div>

	<div class="layui-form-item">
			<button class="layui-btn layuiadmin-btn-useradmin" data-type="submit" style="display:block;margin:0 auto">付款</button>
	</div>


	</div>
</form>
<script>
	var layer = null;
	var form = null;
	layui.use('table', function () {
		$ = layui.$;
		form = layui.form;
		layer = layui.layer;
	});

</script>
</body>
</html>