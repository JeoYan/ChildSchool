<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/19
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%--/**--%>
<%--* 班级成员管理-增加界面--%>
<%--* by 陈超--%>
<%--*/--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

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
	<title>layuiAdmin 网站用户 iframe 框</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%> media="all">
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
</head>
<body>



<form class="layui-form" action="">
<div class="layui-form" lay-filter="layui-btn-normal" id="layui-btn-normal" style="padding: 20px 0 0 0;">
	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">班级名称</label>
		<div class="layui-input-inline">
			<input type="text"  name="cname" id="cname" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item" >
		<div class="layui-inline">
			<label class="layui-form-label">班主任</label>
			<div class="layui-input-inline">
				<select name="wname" id="wname">
					<option value="">请选择班主任</option>


					<c:forEach items="${requestScope.work}" begin="0" step="1" var="y">
						<option value="${y.wid}">${y.wname}</option>
					</c:forEach>

				</select>
			</div>
		</div>

	</div>

	<div class="layui-form-item" >
		<div class="layui-inline">
			<label class="layui-form-label">所在教室</label>
			<div class="layui-input-inline">
				<select name="classroom" id="classroom">
					<option value="">请选择教室</option>


					<c:forEach items="${requestScope.class}" begin="0" step="1" var="y">
						<option value="${y.cid}">${y.classroom}</option>
					</c:forEach>

				</select>
			</div>
		</div>

	</div>


	<div class="layui-form-item layui-hide" style="text-align: center">
		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
	</div>
</div>
</form>

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
