<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/19
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%--/**--%>
<%--* 幼儿管理-增加界面--%>
<%--* by 陈超--%>
<%--*/--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<%
	String path = application.getContextPath();
%>
<html>
<head>
	<meta charset="utf-8">
	<title>iframe模板页面</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/css/socketChart.css"%> media="all">
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>

</head>
<body>
<iframe id="aframe" name="aframe" src=<%=path+"/parentLogin/login.action"%> frameborder="0"  marginheight="0" marginwidth="0" width="100%" height="100%" allowfullscreen='true'>

</iframe>

<%-- -----------------------------------------------------------%>
<%-- ---------------------在线人数显示弹窗--------------------------------------%>
<div id="mytalk" style="display: none">
	<div id="hz-group">
		<input type="hidden" id="talks" value="${sessionScope.pName}">
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
	<div style="display: none;">正在与<span id="toUserName" ></span>聊天</div>
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
<script>

	layui.use('layer',function(){
		var layer = layui.layer;
		var loginName=$("#talks").val();
		//当前人员在线显示弹窗
		layer.open({
			title:loginName
			,type: 1
			,content: $('#mytalk')
			,shade: 0
			,maxmin:true
			, closeBtn :0
			,offset: 'r'
			,min: function(layero){
				setTimeout(function(){
					layero.css({
						left:'auto'
						,right: 0
						,bottom: 0
					})},0);
			}
			,max:function (layerro) {
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
