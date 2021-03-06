
<%--/**--%>
<%--* 教师管理-修改界面--%>
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




<div class="layui-form" lay-filter="layui-btn-xs" id="layui-btn-xs" style="padding: 20px 0 0 0;">
	<div class="layui-form-item" style="text-align: center">
		<label class="layui-form-label">教师名称</label>
		<div class="layui-input-inline">
			<input type="text"  name="wname" id="wname" lay-verify="required" placeholder="请输入教师名称" autocomplete="off" class="layui-input" value="">
		</div>
	</div>

	<div class="layui-form-item" >
		<div class="layui-inline">
			<label class="layui-form-label">角色选择</label>
			<div class="layui-input-inline">
				<select name="rname" id="rname">
					<option value=""></option>


					<c:forEach items="${requestScope.role}" begin="0" step="1" var="y">
						<option value="${y.rid}">${y.rname}</option>
					</c:forEach>

				</select>
			</div>
		</div>

	</div>


	<div class="layui-form-item layui-hide" style="text-align: center">
		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
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
