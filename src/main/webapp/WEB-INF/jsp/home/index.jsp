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
	<title>智慧幼儿园首页</title>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link href=<%=path+"/css/style.css"%> rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href=<%=path + "/css/bootstrap.min.css"%>/>
	<link rel="stylesheet" type="text/css" href=<%=path + "/css/main.css"%>/>

	<script src=<%=path + "/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"/js/nav.js"%> type="text/javascript"></script>
</head>
<body>
<div class="topIntr container">
	<a class="navbar-brand" href="#"><h1><span>智慧</span>幼儿园 <i>孩子们健康成长热爱学习的地方</i></h1></a>
	<div class="pull-right">
		<div style="font-size: 15px">

			<c:if test="${sessionScope.pName==null}">
			<a href=<%=path+"/parentLogin/parentLoginPage.action"%>>登入</a>|
			<a href="">注册</a>
			</c:if>
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
<div class="banner">
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
			<li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<a class="item  active" href="">
				<img src=<%=path+"/images/banner/banner1.jpg"%>>
			</a>
			<a class="item " href="">
				<img src=<%=path+"/images/banner/banner2.jpg"%>>
			</a>
			<a class="item " href="">
				<img src=<%=path+"/images/banner/banner3.jpg"%>>
			</a>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
</div>
<div class="bgImg">
	<!--about-->
	<div class="container about">
		<div class="page-header">
			<h2>关于智慧
				<small> About</small>
				<a class="more" href=<%=path + "about.ation"%>>more+</a></h2>
		</div>
		<div class="row padT80">
			<div class="col-sm-6 col-xs-12">
				<img src=<%=path+"/images/comp.jpg"%>>
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
				<a class="btn btn-primary" href=<%=path + "/about.action"%>>查看更多</a>
			</div>
		</div>
	</div>
	<!--advantage-->
	<div class="advantage container">
		<div class="page-header">
			<h2>智慧的优势
				<small> Advantage</small>
			</h2>
		</div>
		<div class="row padT80 adlist">
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

	</div>
	<!--environment-->
	<div class="envir container">
		<div class="page-header">
			<h2>智慧环境
				<small> Environment</small>
				<a class="more" href=<%=path + "/event.action"%>>more+</a></h2>
		</div>
		<div class="row envirIntr padT80">
			<div class="col-sm-6 col-xs-12">
				<p>
					作为知名的学前教育机构，引进一流的教育管理模式、融合国内外先进的教研成果、
					拥有国际一流的教育专家团队和具有国际资格认证的中外教师队伍。
				</p>
				<p>
					每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，充满着温馨关
					爱的教育氛，打造了最适合孩子健康成长的校园环境。经过十年的积累与沉淀，目
					前友家已经发展成为引领中国幼教国际化发展的旗舰。
				</p>
				<p>
					每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，充满着温馨关
					爱的教育氛，打造了最适合孩子健康成长的校园环境。经过十年的积累与沉淀，目
					前友家已经发展成为引领中国幼教国际化发展的旗舰。
				</p>
				<p><a class="btn btn-primary" href=<%=path + "/event.action"%>>查看更多</a></p>
			</div>
			<div class="col-sm-6 col-xs-12">
				<img src=<%=path + "/images/hj.png"%>/>
			</div>
		</div>
	</div>
</div>
<div class="lesson container">
	<div class="page-header">
		<h2>智慧课程
			<small> Lesson</small>
			<a class="more" href=<%=path + "/teach.action"%>>more+</a></h2>
	</div>
	<div class="row padT80 padD80">
		<div class="col-sm-6 col-xs-12">
			<div class="lessonOne">
				<h3>绘画课程</h3>
				<p>
					为孩子们发展更好的逻辑思维能力打下基础。专业的老师和教育专家们将富有创造力
					的教学主体和教学环境建立在丰富的蒙台梭利材料之上，每个月变换不同的主题
					，全年不间断。
				</p>
			</div>
			<div class="lemgOne">
				<img src=<%=path + "/images/lesson/le2.jpg"%>/>
			</div>
			<img src=<%=path+"/images/lettle2.jpg"%> class="litYello1"/>
		</div>
		<div class="col-sm-6 col-xs-12">
			<div class="lemgTwo">
				<img src=<%=path + "/images/lesson/le1.jpg"%>/>
			</div>
			<img src=<%=path+"/images/lesson/le3.jpg"%> class="lemgThree"/>
			<div class="lessonTwo">
				<h3>品德课程</h3>
				<p>
					拥有国际一流的教育专家团队和具有国际资格认证的中外教师队伍，
					每个校园都是按照国际纯正蒙台梭利校园的特点和标准精心设计的，
					目前友家已经发展成为引领中国幼教国际化发展的旗舰。
				</p>
			</div>
			<img src=<%=path+"/images/lettle1.png"%> class="litYello2"/>
		</div>
	</div>
</div>

<div class="envir container">
	<div class="page-header">
		<h2>消息中心
			<small> news</small>
		</h2>
	</div>

	<div class="col-sm-6 col-xs-12">
		<div class="a02">
			<div class="news2">
				<div class="info">
					<div class="title">校园公告</div>
					<div class="more"><a href=<%=path+"/notice_more.action"%>>MORE+</a></div>
				</div>
				<div class="bt03">
					<ul class="ne1">
						<c:forEach items="${requestScope.noticeList}" var="i" begin="0" step="1" end="9">
							<li><a href=<%=path+"/notice_detail.action?noid="%>${i.noid}><span>${i.ndate}</span>${i.notitle}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="news2" style="margin-left:13px;">
				<div class="info">
					<div class="title">新闻中心</div>
					<div class="more"><a href=<%=path+"/news_more.action"%>>MORE+</a></div>
				</div>
				<div class="bt03">
					<ul class="ne1">
						<c:forEach items="${requestScope.newsList}" var="i" begin="0" step="1" end="9">
							<li><a href=<%=path+"/news_detail.action?nid="%>${i.nid}><span>${i.ndate}</span>${i.ntitle}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>


		<div class="row envirIntr padT80">
		</div>
		<%--		<div class="col-sm-6 col-xs-12">--%>
		<%--			<img src=<%=path+"/images/hj.png"%>/>--%>
		<%--		</div>--%>
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
					<img src=<%=path + "/images/gw.jpg"%>/>
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
