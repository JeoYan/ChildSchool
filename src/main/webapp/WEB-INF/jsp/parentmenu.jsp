<%--
------------------------------------------------
               家长登入后的菜单主页面
               by 严俊杰
------------------------------------------------
--%>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17 0017
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>家长主页</title>
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
<body class="layui-layout-body">
<div id="LAY_app">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item layadmin-flexible" lay-unselect>
					<a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
						<i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
					</a>
				</li>
				<li class="layui-nav-item" lay-unselect>
					<a href="javascript:;" layadmin-event="refresh" title="刷新">
						<i class="layui-icon layui-icon-refresh-3"></i>
					</a>
				</li>

			</ul>
			<ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

				<li class="layui-nav-item" lay-unselect>
					<a href="javascript:;">
<%--						<cite>${sessionScope.pName}</cite>--%>
						<cite>${requestScope.pName}</cite>
					</a>
				</li>
			</ul>
		</div>

		<!-- 侧边菜单 -->
		<div class="layui-side layui-side-menu">
			<div class="layui-side-scroll">
				<div class="layui-logo" lay-href=<%=path+"/home.action"%>>
					<span>智慧幼儿园</span>
				</div>
				<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
				    lay-filter="layadmin-system-side-menu">
					<li data-name="home" class="layui-nav-item layui-nav-itemed">
						<a href="javascript:;" lay-tips="幼儿中心" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>幼儿中心</cite>
						</a>
						<dl class="layui-nav-child">
<%--							<dd data-name="console" class="layui-this">--%>
<%--								<a lay-href="/ChildSchool/home.action">主页</a>--%>
<%--							</dd>--%>
							<dd data-name="console" >
								<a lay-href="/ChildSchool/medicalcase.action">体检情况</a>
							</dd>
							<dd data-name="console">
								<a lay-href="/ChildSchool/ClassSignController/query/babysign">宝宝考勤</a>
							</dd>
							<dd data-name="console">
								<a lay-href="/ChildSchool/foodCaseView.action">宝宝膳食</a>
							</dd>
							<dd data-name="console">
								<a lay-href="/ChildSchool/BackAction/cardRechargeView.action">宝宝卡充值</a>
							</dd>
						</dl>
					</li>

					<li data-name="component" class="layui-nav-item">
						<a href="javascript:;" lay-tips="直播中心" lay-direction="2">
							<i class="layui-icon layui-icon-component"></i>
							<cite>直播中心</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="button">
								<a lay-href="/ChildSchool/VideoController/query/schoollive">校园直播</a>
							</dd>
						</dl>
					</li>

					<li data-name="template" class="layui-nav-item">
						<a href="javascript:;" lay-tips="作业中心" lay-direction="2">
							<i class="layui-icon layui-icon-template"></i>
							<cite>作业中心</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a lay-href="/ChildSchool/HomeworkController/turnBabyHomework">宝宝作业</a></dd>
							<dd><a lay-href="/ChildSchool/HomeworkController/query/pasthomework">往期作业</a></dd>
							<dd><a lay-href="/ChildSchool/web/parentcoursequery.action">课程表</a></dd>
						</dl>
					</li>

					<li data-name="app" class="layui-nav-item">
						<a href="javascript:;" lay-tips="安全教育" lay-direction="2">
							<i class="layui-icon layui-icon-app"></i>
							<cite>安全教育</cite>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a lay-href="/ChildSchool/web/safestudy.action">试题学习和考试</a>
							</dd>
						</dl>
					</li>
					<li data-name="senior" class="layui-nav-item">
						<a href="javascript:;" lay-tips="亲子阅读" lay-direction="2">
							<i class="layui-icon layui-icon-senior"></i>
							<cite>亲子阅读</cite>
						</a>
						<dl class="layui-nav-child">
							<dd>
							<dd><a lay-href=<%=path + "/parentLogin/readBookPage.action"%>>在线阅读</a></dd>
						</dl>
					</li>
<%--					<li data-name="user" class="layui-nav-item">--%>
<%--						<a href="javascript:;" lay-tips="联系老师" lay-direction="2">--%>
<%--							<i class="layui-icon layui-icon-user"></i>--%>
<%--							<cite>联系老师</cite>--%>
<%--						</a>--%>
<%--						<dl class="layui-nav-child">--%>
<%--							<dd>--%>
<%--								<a lay-href="user/user/list.html">联系老师</a>--%>
<%--							</dd>--%>
<%--						</dl>--%>
<%--					</li>--%>
					<li data-name="set" class="layui-nav-item">
						<a href="javascript:;" lay-tips="个人中心" lay-direction="2">
							<i class="layui-icon layui-icon-set"></i>
							<cite>个人中心</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a lay-href="/ChildSchool/parentinformation.action">宝宝信息</a></dd>
							<dd><a lay-href="/ChildSchool/web/parentchangepassword.action">修改密码</a></dd>
							<a onclick="exit()" >退出</a>
<%--							<a lay-href="javascript:if(confirm('确实要退出？'))location='/ChildSchool/workerLogin/workerLoginPage.action'">退出</a>---%>
						</dl>
					</li>
				</ul>
			</div>
		</div>

		<!-- 页面标签 -->
		<div class="layadmin-pagetabs" id="LAY_app_tabs">
			<div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-down">
				<ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
					<li class="layui-nav-item" lay-unselect>
						<a href="javascript:;"></a>
						<dl class="layui-nav-child layui-anim-fadein">
							<dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
							<dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
							<dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
						</dl>
					</li>
				</ul>
			</div>
			<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
				<ul class="layui-tab-title" id="LAY_app_tabsheader">
					<li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i
							class="layui-icon layui-icon-home"></i></li>
				</ul>
			</div>
		</div>


		<!-- 主体内容 -->
		<div class="layui-body" id="LAY_app_body">
			<div class="layadmin-tabsbody-item layui-show">
				<iframe src=<%=path+"/home.action"%> frameborder="0" class="layadmin-iframe"></iframe>
			</div>
		</div>
		<!-- 辅助元素，一般用于移动设备下遮罩 -->
		<div class="layadmin-body-shade" layadmin-event="shade"></div>
	</div>
</div>


<%-- -----------------------------------------------------------%>
<%-- ---------------------在线人数显示弹窗--------------------------------------%>
<div id="mytalk" style="display: none">
	<div id="hz-group">
<%--		<input type="hidden" id="talks" value="${sessionScope.pName}">--%>
		<input type="hidden" id="talks" value="${requestScope.pName}">
		<input type="hidden" id="uid" value="${requestScope.pid}">
		<input type="hidden" id="rid" value="0">
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

	function exit(){
		if (confirm("是否退出")){
			$.ajax({
				type:"POST",
				url:"/ChildSchool/exit.action",
				sync:true,
				dateType:"text",
				success:function (msg) {
					if (msg=="注销") {
						window.location.href="/ChildSchool/parentLogin/parentLoginPage.action";
					}
				},
				error:function () {

				}
			});
		}
	}

	layui.config({
		base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use('index');

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
