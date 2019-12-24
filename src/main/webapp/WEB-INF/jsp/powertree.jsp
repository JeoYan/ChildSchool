<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>教职工权限管理</title>

	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

</head>
<body>


<div id="LAY-user-login" style="display: none;">
	<div class="layadmin-user-login-box layadmin-user-login-header">
		<%--		<h2>教职工权限管理</h2>--%>
	</div>
	<div style="text-align: center">
		<%--		<div class="layui-inline" style="width:1000px;text-align: right">--%>
		<%--			<a href="userlogin.jsp">登录</a>&nbsp&nbsp&nbsp&nbsp--%>
		<%--			<a href="userlogin.jsp">注册</a>--%>
		<%--		</div>--%>
		<div class="layui-inline" style="width:500px;">
			<div class="layui-inline">
				<label class="layui-form-label" id="wName"></label>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" id="rName"></label>
			</div>
			<hr>
		</div>
	</div>
	<div class="layui-card-body">
		<%--表主体--%>
		<div style="text-align: center">
			<div class="layui-inline" style="width:96%">
				<input type="hidden" id="hiddenWid" value="<%=request.getParameter("wid")%>">
				<div id="test12" class="demo-tree-more"></div>
				<div class="layui-btn-container">
					<button type="button" class="layui-btn layui-btn-sm" lay-demo="getChecked">保存</button>
				</div>
			</div>
		</div>
		<div class="layui-trans layadmin-user-login-footer">
		</div>
	</div>
</div>
<script>
	layui.use(['tree', 'util'], function () {
		var tree = layui.tree
			, layer = layui.layer
			, util = layui.util
			, $ = layui.$;
		var url = "${pageContext.request.contextPath}/power/getMenu.action?wid=" + $("#hiddenWid").val();
		$.post(url, function (data) {
			console.log(data);
			//基本演示
			tree.render({
				elem: '#test12'
				, data: data
				, showCheckbox: true  //是否显示复选框
				, id: 'demoId1'
				, isJump: true //是否允许点击节点时弹出新窗口跳转
				, click: function (obj) {
					var data = obj.data;  //获取当前点击的节点数据
					// layer.msg('状态：' + obj.state + '<br>节点数据：' + JSON.stringify(data));
				}
			});
		});
		//按钮事件
		util.event('lay-demo', {
			getChecked: function (othis) {
				var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据
				var ob = JSON.stringify(checkedData);

				var flag=confirm("确定修改？");
				if(flag){
					$.ajax({
						type: "POST",//提交的方式
						url: "${pageContext.request.contextPath}/power/updateTree.action",//提交的地址
						data: {wid: $("#hiddenWid").val(), data: ob},
						dataType: "text",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							if (msg === "NotOK") {
								alert("修改不成功！");
								// tree.reload('demoId1', {});
							} else {
								alert("修改成功！");
							}
						},
						error: function () {//错误的方法
							alert("服务器正忙")
						}
					});
				}
				console.log(checkedData);
			}
			, reload: function () {
				//重载实例
				tree.reload('demoId1', {});
			}
		});

	});


</script>


</body>
</html>

