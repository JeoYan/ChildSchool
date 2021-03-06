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
	<title>关于我们</title>
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

						<li >
							<a href=<%=path+"/home.action"%>>网站首页</a>
						</li>
						<li>
							<a href=<%=path+"/about.action"%>>走进智慧</a>
						</li>
						<li class="active">
							<a href=<%=path+"/teach.action"%>>教育天地</a>
						</li>
						<li>
							<a href=<%=path+"/event.action"%>>幼儿乐园</a>
						</li>
<%--						<li>--%>
<%--							<a href=<%=path+"/form.action"%>>校园公告</a>--%>
<%--						</li>--%>
						<li>
							<a href=<%=path+"/contact.action"%>>联系我们</a>
						</li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</header>
<div class="tePg bgImg">
	<div class="banner">
		<img src=<%=path+"/images/banner/ban2.jpg"%> />
	</div>
	<div class="lesIntr container padT80 padD80">
		<div class="row">
			<div class="every padD80">
				<h2>引进一流的教育管理模式</h2>
				<p>
					专注于激发孩子们学习兴趣、食品认知能力、动手能力、我们坚信，
					友家的孩子会拥有这样的核心价值——多元文化的情商和胸怀、优良的品格和超凡的创造力！
				</p>
			</div>
			<div class="col-sm-6 col-xs-12 padD30">
				<img src=<%=path+"/images/lesson/c1.png"%>/>
				<div class="lesCon">
					<h3>国际班级</h3>
					<p>
						拥有国际一流的教育专家团队和具有国际资格认证的中外教师队伍，
						每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，
						目前友家已经发展成为引领中国幼教国际化发展的旗舰。
					</p>
				</div>
			</div>
			<div class="col-sm-6 col-xs-12 padD30">
				<img src=<%=path+"/images/lesson/c2.png"%>/>
				<div class="lesCon">
					<h3>科普班级</h3>
					<p>
						拥有国际一流的教育专家团队和具有国际资格认证的中外教师队伍，
						每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，
						目前友家已经发展成为引领中国幼教国际化发展的旗舰。
					</p>
				</div>
			</div>
			<div class="col-sm-6 col-xs-12 padD30">
				<img src=<%=path+"/images/lesson/c3.png"%>/>
				<div class="lesCon">
					<h3>手绘班级</h3>
					<p>
						拥有国际一流的教育专家团队和具有国际资格认证的中外教师队伍，
						每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，
						目前友家已经发展成为引领中国幼教国际化发展的旗舰。
					</p>
				</div>
			</div>
			<div class="col-sm-6 col-xs-12 padD30">
				<img src=<%=path+"/images/lesson/c4.png"%>/>
				<div class="lesCon">
					<h3>品德班级</h3>
					<p>
						拥有国际一流的教育专家团队和具有国际资格认证的中外教师队伍，
						每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，
						目前友家已经发展成为引领中国幼教国际化发展的旗舰。
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="lesIntr container padT80 padD80">
		<div class="row">
			<div class="every padD80">
				<h2>一切为了孩子卓越的未来</h2>
				<p>
					课程会让孩子们在每月的不同主题中得到非凡的学习体验。课程将孩子日常生活中的方方面面进行合理整合，
					让孩子们学会对身边的事物、动物、理念等进行区别和分类。为他们发展更好的逻辑思维能力打下基础。
					专业的老师和教育专家们将富有创造力的教学主体和教学环境建立在丰富的蒙台梭利材料之上，每个月变换
					不 同的主题，全年不间断。
				</p>
			</div>
		</div>
		<div class="col-sm-6 col-xs-12">
			<h3>特色主题教学： </h3>
			<p>
				为他们发展更好的逻辑思维能力打下基础。专业的老师和教育专家们将富有创造力的教学主体和教学环
				境建立在丰富的蒙台梭利材料之上，每个月变换不同的主题，全年不间断。为他们发展更好的逻辑思维能力打下基础。
				专业的老师和教育专家们将富有创造力的教学主体和教学环境建立在丰富的蒙台梭利材料之上，专业的老
				师和教育专家们将富有创造力的教学主体和教学环境建立在丰富的蒙台梭利材料之上，每个月变换不同的主题，全年不间断
			</p>
		</div>
		<div class="col-sm-6 col-xs-12">
			<img src=<%=path+"/images/tec.png"%> class="width80"/>
		</div>
	</div>
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
						<li><span class="glyphicon glyphicon-map-marker"></span>公司地址：软件园东二门观日路56号</li>
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
