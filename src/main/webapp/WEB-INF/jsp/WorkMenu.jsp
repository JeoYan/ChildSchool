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
	<link rel="stylesheet" href=<%=path+"/css/socketChart.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
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
					<dd><a href="/ChildSchool/information.action" >个人信息</a></dd>
					<dd><a href="/ChildSchool/psw.action">修改密码</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item" id="exit">
				<a onclick="exit()" >注销</a>
			</li>
		</ul>
		<ul>
			<li>
				<dl class="layui-nav-child">
					<dd><a href="javascript:void(0)" title="修改密码" onclick="dofix(this)">修改密码</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="/ChildSchool/workerLogin/workerLoginPage.action">退出</a></li>
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
	<%-- -----------------------------------------------------------%>
	<%-- ---------------------在线人数显示弹窗--------------------------------------%>
	<div id="mytalk" style="display: none">
		<div id="hz-group">
			<%--		<input type="hidden" id="talks" value="${sessionScope.pName}">--%>
			<input type="hidden" id="talks" value="${sessionScope.wName}">
			<input type="hidden" id="uid" value="${sessionScope.wid}">
			<input type="hidden" id="rid" value="${sessionScope.rid}">
			<%--		<div style="color: #0C0C0C; font-size:20px">登录用户：--%>
			<%--			<span id="talks">${sessionScope.pName}</span></div>--%>
			<%--		<br/>--%>
			<div style="color: #0C0C0C; font-size:15px">当前在线人数:<span id="onlineCount">0</span></div>
			<!-- 主体 -->
			<div id="hz-group-body">

			</div>
		</div>
	</div>
	<%-- -----------------------------------------------------------%>
	<%-- -----------------------------------------------------------%>

	<%-- -----------------------------------------------------------%>
	<%-- ----------------------聊天窗口-------------------------------------%>
	<div id="hz-message" style="display: none">
		<!-- 头部 -->
		<div style="display: none;">正在与<span id="toUserName"></span>聊天</div>
		<hr style="margin: 0px;"/>
		<!-- 主体 -->
		<div id="hz-message-body">
		</div>
		<!-- 功能条 -->
		<div id="">
			<button>表情</button>
			<button>图片</button>
			<button id="videoBut">视频</button>
			<button onclick="send()" style="float: right;">发送</button>
		</div>
		<!-- 输入框 -->
		<div contenteditable="true" id="hz-message-input">
		</div>
	</div>
	<%-- -----------------------------------------------------------%>
	<%-- -----------------------------------------------------------%>


	<div class="layui-footer">
		<!-- 底部固定区域 -->
	</div>
</div>

<script>
	//JavaScript代码区域
	function exit(){
		if (confirm("是否注销")){
			$.ajax({
				type:"POST",
				url:"/ChildSchool/exit.action",
				sync:true,
				dateType:"text",
				success:function (msg) {
					if (msg=="注销") {
						window.location.href="/ChildSchool/workerLogin/workerLoginPage.action";
					}
				},
				error:function () {

				}
			});
		}
	              }
	layui.use('element', function () {
		var element = layui.element;

	});
	layui.use('layer', function () {
		var layer = layui.layer;
		var loginName = $("#talks").val();
		//当前人员在线显示弹窗
		layer.open({
			title: loginName
			, type: 1
			, content: $('#mytalk')
			, shade: 0
			, maxmin: true
			, closeBtn: 0
			, offset: 'r'
			, min: function (layero) {
				setTimeout(function () {
					layero.css({
						left: 'auto'
						, right: 0
						, bottom: 0
					})
				}, 0);
			}
			, max: function (layerro) {
				$(window).resize(function () {
					$(".layui-layer-maxmin").parents(".layui-layer").width($(window).width()).height($(window).height());
				});
			}
		});
	});

</script>
<script src=<%=path + "/js/socketChart.js"%>></script>
</body>
</html>







