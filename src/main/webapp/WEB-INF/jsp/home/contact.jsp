<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30 0030
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path=request.getContextPath();
%>
<html>
<head>
	<title>联系我们</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/bootstrap.min.css"%>/>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/main.css"%>/>
</head>

<body>
<div class="topIntr container">
	<a class="navbar-brand" href="#"><h1><span>智慧</span>幼儿园 <i>孩子们健康成长热爱学习的地方</i></h1></a>
	<div class="pull-right">
		<div style="font-size: 15px">

			<a href="">登入</a>|
			<a href="">注册</a>

		</div>
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

						<li>
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
						<li class="active">
							<a href=<%=path+"/contact.action"%>>联系我们</a>
						</li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</header>

<div class="conPg">
	<div class="banner">
		<img src=<%=path+"/images/banner/ban4.jpg"%> />
	</div>
	<div class="map container">
		<div class="page-header">
			<h2>联系我们<small>  Contact</small></h2>
		</div>
		<div class="load">
			<iframe src=<%=path+"/map.action"%> width="100%" height="400px"></iframe>
			<div class="loader">
				<img src=<%=path+"/images/loader.gif"%>/>
			</div>
		</div>

	</div>
	<ul class="addre container clearfix container">
		<li class="col-sm-4 col-xs-12">
			<div>
				<div><span class="glyphicon glyphicon-map-marker"></span></div>
				<p>学校地址：软件园东二门观日路56号</p>
			</div>
		</li>
		<li class="col-sm-4 col-xs-12">
			<div>
				<div><span class="glyphicon glyphicon-phone-alt"></span></div>
				<p>联系电话：400-123456</p>
			</div>
		</li>
		<li class="col-sm-4 col-xs-12">
			<div>
				<div><span class="glyphicon glyphicon-envelope"></span></div>
				<p>联系邮箱：1234@123.com</p>
			</div>
		</li>
	</ul>
</div>
<footer>
	<div class="container">
		<div class="row padT80">
			<div class="col-sm-6 col-xs-12 address">
				<p>
					儿童教育，其实不只是大人如何教育孩子，也是孩子如何教育大人，
					让大人成为更加完善的人，更加领悟生活真谛的人。
				</p>
				<div>
					<img src=<%=path+"/images/gw.jpg"%>/>
					<ul>
						<li><span class="glyphicon glyphicon-phone-alt"></span>联系电话：400-123456</li>
						<li><span class="glyphicon glyphicon-envelope"></span>联系邮箱：1234@123.com</li>
						<li><span class="glyphicon glyphicon-file"></span>公司传真：400-123456</li>
						<li><span class="glyphicon glyphicon-map-marker"></span>学校地址：软件园东二门观日路56号</li>
					</ul>
				</div>
			</div>
			<div class="col-sm-6 col-xs-12">
				<form>
					<p>姓名</p>
					<input placeholder=""/>
					<p>描述</p>
					<textarea placeholder=""></textarea>
					<a class="btn btn-primary">提交</a>
				</form>
			</div>
			<div class="col-xs-12 reserve">
				<p>智慧幼儿园 2016-2017 &copy;all rights reserved </p>
			</div>
		</div>
	</div>
</footer>
</body>
<script src=<%=path+"/js/jquery.min.js"%> type="text/javascript" charset="utf-8"></script>
<script src=<%=path+"/js/bootstrap.min.js"%> type="text/javascript" charset="utf-8"></script>
<script src=<%=path+"/js/main.js"%> type="text/javascript" charset="utf-8"></script>
</html>
