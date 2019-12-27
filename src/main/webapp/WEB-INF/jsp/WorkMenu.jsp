<%@ page import="java.util.Map" %>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/11
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
	String path = request.getContextPath();
%>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title> 智慧幼儿园教职工管理</title>
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">智慧幼儿园教职工管理</div>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					${sessionScope.wName}
				</a>
				<dl class="layui-nav-child">
<%--					<dd><a href="" onclick="info()">个人信息</a></dd>--%>
<%--					<dd><a href="">修改密码</a></dd>--%>
					<dd><a href="/ChildSchool/information.action" >个人信息</a></dd>
					<dd><a href="/ChildSchool/psw.action">修改密码</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">注销</a></li>
		</ul>
		<ul>
		<li>
		<dl class="layui-nav-child">
			<dd><a href="javascript:void(0)" title="修改密码" onclick="dofix(this)">修改密码</a></dd>
		</dl>
		</li>
		<li class="layui-nav-item"><a href="">退出</a></li>
		</ul>
	</div>
	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<ul class="layui-nav layui-nav-tree" lay-filter="test">
				<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
				<c:if test="${requestScope.workerMenu!=null}">
					<c:forEach items="${requestScope.workerMenu}" var="i" begin="0" step="1">
						<li class="layui-nav-item">
							<a href="javascript:;">
								<i class="layui-icon layui-icon-component"></i>
									<cite>${i.key}</cite></a>
							<dl class="layui-nav-child">
								<c:forEach items="${i.value}" begin="0" step="1" var="j">
									<dd>
										<a href="${j.url}" target="page-view">${j.childName}</a>
									</dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<iframe src="" frameborder="0" style="width: 100% ;height: 100% " name="page-view" id="myiframe">
			</iframe>
		</div>
	</div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
	</div>
</div>
<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
<script>
	layui.use('element', function () {
		var element = layui.element;
	});
	// function info() {
	// 	layer.open({
	// 		type: 2,
	// 		title: '个人信息',
	// 		content: '/ChildSchool/information.action',
	// 		maxmin: true,
	// 		area: ['500px', '500px'],
	// 		btn: ['确定', '取消'],
	// 		yes: function (index, layero) {
	// 			alert("diankai");
	// 			//教室名称
	// 			var wid = $("#wid").val();
	// 			var oldpsw = $("#LAY-user-login-password").val();
	// 			var newpsw = $("#LAY-user-login-password1").val();
	// 			var newpsw1 = $("#LAY-user-login-password2").val();
	// 			alert(wid);
	// 			var ob = {wid: wid, oldpsw: oldpsw, newpsw: newpsw, newpsw1: newpsw1};
	//
	// 		}
	// 	});
	// }


</script>

</body>
</html>







