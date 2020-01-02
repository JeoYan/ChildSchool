<%--
------------------------------------------------
               家长登入界面
               by 严俊杰
------------------------------------------------
--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17 0017
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="utf-8">
	<title>工作人员登入</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<link rel="stylesheet" href=<%=path+"/css/login.css"%> media="all"/>
	<style>
		/* 覆盖原框架样式 */
		.layui-elem-quote{background-color: inherit!important;}
		.layui-input, .layui-select, .layui-textarea{background-color: inherit; padding-left: 30px;}
	</style>


</head>
<body>



<!-- Head End -->

<!-- Carousel -->
<div class="layui-row">
	<div class="layui-col-sm12 layui-col-md12">
		<div class="layui-carousel zyl_login_height" id="zyllogin" lay-filter="zyllogin" style="height: 652px !important;">
			<div carousel-item="">
				<div>
					<div class="zyl_login_cont"></div>
				</div>
				<div>
					<img src=<%=path+"/image/01.jpg"%> />
				</div>
				<div>
					<div class="background">
						<span></span><span></span><span></span>
						<span></span><span></span><span></span>
						<span></span><span></span><span></span>
						<span></span><span></span><span></span>
					</div>
				</div>
				<div>
					<img src=<%=path+"/image/03.jpg"%> />
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Carousel End -->

<!-- Footer -->
<%--<div class="layui-row">--%>
<%--	<div class="layui-col-sm12 layui-col-md12 zyl_center zyl_mar_01">--%>
<%--		© 2019 - 简约后台登陆界面 || 简约后台登陆界面版权所有--%>
<%--	</div>--%>
<%--</div>--%>
<!-- Footer End -->



<!-- LoginForm -->
<div class="zyl_lofo_main">
	<div class="layui-row layui-col-space15">
<%--		<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">--%>
			<div class="layadmin-user-login-main">
				<div class="layadmin-user-login-box layadmin-user-login-header">
					<h2>智慧幼儿园</h2>
					<p>工作人员登入</p>
				</div>
				<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-username"
						       for="LAY-user-login-username"></label>
						<input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户账号"
						       class="layui-input" value="41">
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password"
						       for="LAY-user-login-password"></label>
						<input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
						       placeholder="密码" class="layui-input" value="123">
					</div>
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
								       for="LAY-user-login-vercode"></label>
								<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required"
								       placeholder="图形验证码" class="layui-input" value="123">
							</div>

							<div class="layui-col-xs5">
								<div style="margin-left: 10px;">
									<img id="imgVerify" src="<%=path+"/workerLogin/getVerCode.action"%>" alt="更换验证码" height="36"
									     width="100%">
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-bottom: 20px;">
						<%--				<input type="checkbox" name="remember" lay-skin="primary" title="记住密码">--%>
						<a class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;"
						   href=<%=path + "/parentLogin/parentForgetPage.action"%>>忘记密码？</a>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
					</div>
<%--					<div class="layui-trans layui-form-item layadmin-user-login-other">--%>
<%--						<a href="reg.html" class="layadmin-user-jump-change layadmin-link">注册帐号</a>--%>
<%--					</div>--%>
<%--				</div>--%>
			</div>
			<%--	<div class="layui-trans layadmin-user-login-footer">--%>
			<%--		<p>© 2019 Design by Jeo</p>--%>
			<%--	</div>--%>
		</div>
	</div>
</div>
<%--<!-- LoginForm End -->--%>



<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>
<script>

	//点击验证码图片获得获得验证码
	$("#imgVerify").on('click', function () {
		$("#imgVerify").attr("src", "${pageContext.request.contextPath}/workerLogin/getVerCode.action?"
			+ Math.random());
	});

	layui.config({
		base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'user'], function () {
		var setter = layui.setter
			, admin = layui.admin
			, form = layui.form
			, router = layui.router()
			, search = router.search;
		form.render();
		//提交
		form.on('submit(LAY-user-login-submit)', function (obj) {
			var flag=false;
			var wid = $("#LAY-user-login-username").val();
			var passWord = $("#LAY-user-login-password").val();
			var verifyCode = $("#LAY-user-login-vercode").val();
			//请求登入接口
			$.ajax({
				async: false,//异步操作
				type: "POST",
				url: "${pageContext.request.contextPath}/workerLogin/loginCheck.action",//注意路径
				data: {wid: wid, passWord: passWord,verifyCode: verifyCode},
				dataType: "text",
				success: function (data) {
					// alert(data);
					if (data ==="NotOk") {
						layer.msg("账号或密码不存在！！！");
					}else if(data==="vercodeError"){
						layer.msg("验证码错误！！！");
					} else if(data==="NotExist"){
						layer.msg("账户不存在！！！");
					}else if(data==="StatusLock"){
						layer.msg("账户已被禁用，请联系学校管理员！！！");
					}else{
						//登入成功的提示与跳转
						layer.msg('登入成功', {
							offset: '15px'
							, icon: 1
							, time: 1000
						}, function () {
							location.href = '${pageContext.request.contextPath}/workerLogin/login.action'; //后台主页
						});
					}
				},
				error: function (data) {
					alert("-----失败------" + data);
				}
			});

			return flag;


		});

	});
</script>

<!-- Jqarticle Js -->
<script type="text/javascript" src=<%=path + "/assembly/jqarticle/jparticle.min.js"%>></script>
<!-- ZylVerificationCode Js-->
<script type="text/javascript" src=<%=path + "/assembly/zylVerificationCode/zylVerificationCode.js"%>></script>
<script>
	layui.use(['carousel', 'form'], function(){
		var carousel = layui.carousel
			,form = layui.form;


		//设置轮播主体高度
		var zyl_login_height = $(window).height()/1.3;
		var zyl_car_height = $(".zyl_login_height").css("cssText","height:" + zyl_login_height + "px!important");


		//Login轮播主体
		carousel.render({
			elem: '#zyllogin'//指向容器选择器
			,width: '100%' //设置容器宽度
			,height:'zyl_car_height'
			,arrow: 'always' //始终显示箭头
			,anim: 'fade' //切换动画方式
			,autoplay: true //是否自动切换false true
			,arrow: 'hover' //切换箭头默认显示状态||不显示：none||悬停显示：hover||始终显示：always
			,indicator: 'none' //指示器位置||外部：outside||内部：inside||不显示：none
			,interval: '5000' //自动切换时间:单位：ms（毫秒）
		});

		//监听轮播--案例暂未使用
		carousel.on('change(zyllogin)', function(obj){
			var loginCarousel = obj.index;
		});

		//粒子线条
		$(".zyl_login_cont").jParticle({
			background: "rgba(0,0,0,0)",//背景颜色
			color: "#fff",//粒子和连线的颜色
			particlesNumber:100,//粒子数量
			//disableLinks:true,//禁止粒子间连线
			//disableMouse:true,//禁止粒子间连线(鼠标)
			particle: {
				minSize: 1,//最小粒子
				maxSize: 3,//最大粒子
				speed: 30,//粒子的动画速度
			}
		});

		layui.jquery("#zyllogin").css("height", "652px");
	});

</script>



</body>
</html>
