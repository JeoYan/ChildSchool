
<%--/**--%>
<%--* 宝宝管理-入园信息--%>
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
	<title>入园信息</title>

	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%>  media="all">
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>




<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	<legend>宝宝入园信息</legend>
</fieldset>
<form class="layui-form" action="" onsubmit="return false">
<%--	<div class="layui-form-item">--%>
<%--		<label class="layui-form-label">单行输入框</label>--%>
<%--		<div class="layui-input-inline">--%>
<%--			<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">--%>
<%--		</div>--%>
<%--	</div>--%>

	<div class="layui-form-item">

		<label class="layui-form-label">宝宝名称</label>
		<div class="layui-input-inline">
			<input type="text" name="bname" id="bname" lay-verify="required" lay-reqtext="宝宝名称是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>

		<label class="layui-form-label">宝宝性别</label>
		<div class="layui-inline">
			<select name="bsex"  id="bsex" lay-filter="LAY-user-adminrole-type">
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
		</div>

	</div>


	<div class="layui-form-item" >

		<div class="layui-inline">
		<label class="layui-form-label">出生年月</label>
		<div class="layui-inline">
			<input class="layui-input" type="date" name="bbirth" id="bbirth" autocomplete="off">
		</div>
		</div>

		<div class="layui-inline">
			<label class="layui-form-label">就读班级</label>
			<div class="layui-input-inline">
				<select name="cname" id="cname">
					<option value="">请选择班级</option>


					<c:forEach items="${requestScope.cname}" begin="0" step="1" var="y">
						<option value="${y.cid}">${y.cname}</option>
					</c:forEach>

				</select>
			</div>
		</div>

	</div>


	<div class="layui-form-item">

		<div class="layui-inline">
			<label class="layui-form-label">入园日期</label>
			<div class="layui-inline">
				<input class="layui-input" type="date" name="bdate" id="bdate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" >
			</div>
		</div>

		<label class="layui-form-label">宝宝地址</label>
		<div class="layui-input-inline">
			<input type="text" width="500px" name="baddress" id="baddress" lay-verify="required" lay-reqtext="宝宝地址是必填项，岂能为空？" placeholder="请输入宝宝地址" autocomplete="off" class="layui-input">
		</div>
	</div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	<legend>宝宝家长信息</legend>
</fieldset>


<%--	<div class="layui-form-item">--%>
<%--		<label class="layui-form-label">单行输入框</label>--%>
<%--		<div class="layui-input-inline">--%>
<%--			<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">--%>
<%--		</div>--%>
<%--	</div>--%>
	<div class="layui-form-item">

		<label class="layui-form-label">家长名称</label>
		<div class="layui-input-inline">
			<input type="text" name="pname" id="pname" lay-verify="required" lay-reqtext="家长名称是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>

		<label class="layui-form-label">亲子关系</label>
		<div class="layui-inline">
			<select name="prelation"  id="prelation" lay-filter="LAY-user-adminrole-type">
				<option value="爸爸">爸爸</option>
				<option value="妈妈">妈妈</option>

			</select>
		</div>


	</div>


	<div class="layui-form-item">

		<div class="layui-inline">
		<label class="layui-form-label">家长职业</label>
		<div class="layui-input-inline">
			<input type="text" width="500px" name="pjob" id="pjob" lay-verify="required" lay-reqtext="家长职业是必填项，岂能为空？" placeholder="请输入家长职业" autocomplete="off" class="layui-input">
		</div>
		</div>

		<div class="layui-inline">
			<label class="layui-form-label">家长手机</label>
			<div class="layui-input-inline">
				<input type="tel" name="pphone" id="pphone" lay-verify="required|phone" autocomplete="off" class="layui-input">
			</div>
		</div>

	</div>

	<div class="layui-form-item">



		<div class="layui-inline">
			<label class="layui-form-label">验证身份证</label>
			<div class="layui-input-inline">
				<input type="text" name="identitycard" id="identitycard" lay-verify="identitycard" placeholder="" autocomplete="off" class="layui-input">
			</div>
		</div>

	</div>
	<div class="layui-inline">
		<button class="layui-btn" data-type="add" id="add">宝宝人脸录入</button>


	</div>

<%--	<div class="layui-form-item">--%>
<%--		<div class="layui-input-block">--%>
<%--			<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">&ndash;%&gt;--%>
<%--&lt;%&ndash;			<button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1" value="确认">立即提交</button>&ndash;%&gt;--%>
<%--		</div>--%>
<%--	</div>--%>

<%--	<div class="layui-form-item layui-hide" style="text-align: center">--%>
<%--		<input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">--%>
<%--	</div>--%>

</form>





<script src="<%=uiPath+"layui/layui.js"%>"></script>
<script>
	layui.use(['layer'], function() {
		var  layer = layui.layer;
		var object;
		var $ = layui.$, active = {

		};
		$('#add').on('click', function () {
			alert("录入");
			layer.open({
				type: 2,
				title: '宝宝人脸录入',
				content: '/ChildSchool/babyfaceentry.action',
				maxmin: true,
				area: ['500px', '400px'],
				btn: ['关闭']

			});
		});

	});

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
