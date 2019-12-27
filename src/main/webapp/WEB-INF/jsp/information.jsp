
<%--/**--%>
<%--* 个人信息-%>
<%--* by 陈超--%>
<%--*/--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath();
	String uicssPath = request.getContextPath()+"/layuiadmin/";
	String JsPath = application.getContextPath()+"/js/";
	String cssPath = request.getContextPath()+"/css/";
	String uiPath = request.getContextPath()+"/layuiadmin/";

%>
<html>
<head>
	<meta charset="utf-8">
	<title>个人信息</title>

	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%>  media="all">
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body style="padding: 10px 250px 0 300px;background-image: url(<%=path+"/image/back.jpg"%>);background-repeat: no-repeat;background-size: 100% 100%;" >

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	<legend>职工信息</legend>
</fieldset>
<div class="layui-form" action="">

	<div class="layui-form-item">

		<input type="hidden" id="wid" value="${sessionScope.wid}">

		<label class="layui-form-label">姓名</label>
		<div class="layui-input-inline">
			<input type="text" name="wname" id="wname" readonly style="background-color:transparent;border: 0px" lay-verify="required" lay-reqtext="宝宝名称是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.wname}" >
		</div>



		<label class="layui-form-label">性别</label>
		<div class="layui-input-inline">
			<input type="text" name="wsex" id="wsex" readonly style="background-color:transparent;border: 0px" lay-verify="required" lay-reqtext="宝宝名称是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.wsex}" >

		</div>

	</div>


	<div class="layui-form-item" >
		<label class="layui-form-label">角色</label>
		<div class="layui-input-inline">
			<input type="text" name="rname" id="rname" readonly style="background-color:transparent;border: 0px" lay-verify="required" lay-reqtext="宝宝名称是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input" value="${sessionScope.rname}">
		</div>

		<div class="layui-inline">
		<label class="layui-form-label">出生年月</label>
		<div class="layui-inline">
			<input class="layui-input" type="date" name="wbirth"  readonly style="background-color:transparent;border: 0px" id="wbirth" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" value="${sessionScope.wbrith}">
		</div>
		</div>

</div>
	<div class="layui-form-item">

		<div class="layui-inline">
			<label class="layui-form-label">入园日期</label>
			<div class="layui-inline">
				<input class="layui-input" type="date" name="wdate" readonly style="background-color:transparent;border:0px" id="wdate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" value="${sessionScope.wdate}">
			</div>
		</div>

		<label class="layui-form-label">状态</label>
		<div class="layui-input-inline">
			<input type="text" width="500px" name="sname" id="sname" readonly style="background-color:transparent;border: 0px" lay-verify="required" lay-reqtext="宝宝地址是必填项，岂能为空？" placeholder="请输入宝宝地址" autocomplete="off" class="layui-input" value="${sessionScope.sname}">
		</div>
	</div>
	<div class="layui-form-item" style="text-align: center">
	<li class="layui-nav-item">
		<a href="/ChildSchool/workerLogin/login.action" >
		<button style="width: 100px; height: 30px;background-color: deepskyblue;">返回</button>
		</a>
	</li>
	</div>
</div>





<script src="<%=uiPath+"layui/layui.js"%>"></script>
<script>
	layui.use('form', function(){
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

		//……

		//但是，如果你的HTML是动态生成的，自动渲染就会失效
		//因此你需要在相应的地方，执行下述方法来进行渲染
		form.render();
	});
</script>

</body>
</html>
