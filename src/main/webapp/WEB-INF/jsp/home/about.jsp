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

						<li>
							<a href=<%=path+"/home.action"%>>网站首页</a>
						</li>
						<li class="active">
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
<div class="abPg bgImg">
	<div class="banner">
		<img src=<%=path+"/images/banner/ban1.jpg"%> />
	</div>
	<div class="about container">
		<div class="page-header">
			<h2>关于智慧<small>  About</small></h2>
		</div>
		<div class="row padT80">
			<div class="col-sm-6 col-xs-12">
				<img src=<%=path+"/images/comp.jpg"%>/>
			</div>
			<div class="col-sm-6 col-xs-12">
				<p>
					&emsp;&emsp;<span>智慧</span>幼儿园成立于1950年，
					是市教委批准开办的我市第一所民办幼儿园，开全市社会力量办学幼儿教育之先河。受到市委、
					市政府和各级主管部门的大力支持。办园60年来，幼儿园始终遵循“<span>一切为了孩子，为了孩子将来</span>
					”的办园宗旨，自强不息、开拓进取取得了良好的社会效益。为发展我市民办教育和幼教事
					业做出了自有的贡献，业已成为目前我市规模大、实力强、设施全、信誉好的一所省内外著名的
					示范性民办幼儿园。
				</p>
				<p>
					&emsp;&emsp;友家贯彻国家的教育体系，给学前儿童提供了一个安全，舒适，高质量的学前
					教育。自创办以来，友家幼儿园的教育体系一直在优化和完善，在跟随时代
					的进步的同时，不断为广大父母和社会培养了一代又一代的有位青年。
					友家跟随国家一直发展到现在，拥有员工超过13亿人口，占据中国总人口的99%,在不断发展
					的同时也为社会提供了很过有保障的岗位。友家以宽敬待人的方针一直发展到现在。
				</p>
			</div>
		</div>
	</div>
	<div class="advantage container">
		<div class="page-header">
			<h2>智慧的优势<small>  Advantage</small></h2>
		</div>
		<div class="row padT80 adlist padD80">
			<div class="col-sm-3 col-xs-12">
				<div class="bgBlue">
					<div>
						<h3>环境舒适</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-xs-12">
				<div class="bgYellow">
					<div>
						<h3>设备齐全</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-xs-12">
				<div class="bgRed">
					<div>
						<h3>师资强大</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-xs-12">
				<div class="bgOrg">
					<div>
						<h3>课程合理</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row padD80 padT80">
			<div class="every">
				<h2>一切为了孩子卓越的未来</h2>
				<p>
					课程会让孩子们在每月的不同主题中得到非凡的学习体验。课程将孩子日常生活中的方方面面进行合理整合，
					让孩子们学会对身边的事物、动物、理念等进行区别和分类。为他们发展更好的逻辑思维能力打下基础。
					专业的老师和教育专家们将富有创造力的教学主体和教学环境建立在丰富的蒙台梭利材料之上，每个月变换不
					同的主题，全年不间断。
				</p>
				<div class="col-sm-6 col-xs-12">
					<p>
						我们将为孩子提供： <br />
						1. 非凡的学习体验，独立的空间，老师的关怀与支持，让孩子们能够在不同的领域出类拔萃;<br />
						2. 温馨且适宜学习的环境，满足孩子们在社交、文化以及语言等各方面的发展需求; <br />
						3.积极且不被打扰的氛围，让每一个孩子拥有机会去释放他们与生俱来的求知欲望，去发现和探索我们的世界; <br />
						4.丰富多彩的课程，培养孩子成为严谨的思考者，他们尊重自己的同胞以及生存的环境，他们一生都对学习充满热爱;<br />
					</p>
				</div>
				<div class="col-sm-6 col-xs-12">
					<img src=<%=path+"/images/lesson/le1.jpg"%>/>
				</div>
			</div>

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
