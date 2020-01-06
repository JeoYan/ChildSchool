<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30 0030
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>详情</title>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link href=<%=path+"/css/style.css"%> rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href=<%=path + "/css/bootstrap.min.css"%>/>
	<link rel="stylesheet" type="text/css" href=<%=path + "/css/main.css"%>/>
	<link rel="stylesheet" href=<%=path + "/css/detailstyle.css"%>/>
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"/js/nav.js"%> type="text/javascript"></script>
</head>
<body>
<div class="topIntr container">
	<a class="navbar-brand" href="#"><h1><span>智慧</span>幼儿园 <i>孩子们健康成长热爱学习的地方</i></h1></a>
	<div class="pull-right">
		<div style="font-size: 15px">

			<a href=<%=path+"/parentLogin/parentLoginPage.action"%>>登入</a>|
			<a href="">注册</a>

		</div>
		<%--		<span class=" glyphicon-phone-alt"></span>--%>
		<%--		<div>--%>
		<%--			<p>400-12345678</p>--%>
		<%--		</div>--%>
	</div>
</div>
<header class="mnav">
	<nav class="navbar navbar-default">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">

					<li class="active">
						<a href=<%=path+"/home.action"%>>网站首页</a>
					</li>
					<li>
						<a href=<%=path+"/about.action"%>>走进智慧</a>
					</li>
					<li>
						<a href=<%=path+"/teach.action"%>>教育天地</a>
					</li>
					<li>
						<a href=<%=path+"/event.action"%>>幼儿乐园</a>
					</li>
					<%--						<li>--%>
					<%--							<a href=<%=path+"/form.action"%>>校园公告</a>--%>
					<%--						</li>--%>
					<li >
						<a href=<%=path+"/contact.action"%>>联系我们</a>
					</li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</header>
<!--banner-->


<div class="rigcon">
	<div class="newsdeldiv">
		<div class="newsdel_tit">${requestScope.notice.notitle}</div>
		<div class="explain">发布时间：${requestScope.notice.ndate}&nbsp;&nbsp;来源：${requestScope.notice.wName}&nbsp;&nbsp</div>
		<div class="newsdel_content">  ${requestScope.notice.nconntext}</div>
	</div>
</div>

</body>
<script src=<%=path+"/js/jquery.min.js"%> type="text/javascript" charset="utf-8"></script>
<script src=<%=path+"/js/bootstrap.min.js"%> type="text/javascript" charset="utf-8"></script>
<script src=<%=path+"/js/main.js"%> type="text/javascript" charset="utf-8"></script>
</html>
