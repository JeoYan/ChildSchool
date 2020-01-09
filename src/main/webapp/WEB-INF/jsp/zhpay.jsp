<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/24
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();
	String uiPath = request.getContextPath() + "/layuiadmin/";



%>
<html>
<head>
	<title>订单详情界面</title>
	<link rel="stylesheet" href=<%=uiPath+"layui/css/layui.css"%>>
	<link rel="stylesheet" href=<%=uiPath+"style/admin.css"%> media="all">
	<script src=<%=uiPath+"layui/layui.js"%>></script>

</head>
<body>


<form class="layui-form" target="_blank" method="post" action="<%=path+"/alipay.action"%>">



<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">


	<div class="layui-form-item">
		<label class="layui-form-label">商品：</label>
		<div class="layui-input-inline">
			<label class="layui-form-label">课外读物</label>
		</div>
	</div>


	<input type="text" id="bookid" name="bookid" value="">

	<div class="layui-form-item">
		<label class="layui-form-label">金额：</label>

		<div class="layui-input-inline">
			<label class="layui-form-label">10元</label>
		</div>
	</div>


	<div class="layui-form-item">


			<button class="layui-btn layuiadmin-btn-useradmin" data-type="submit" style="display:block;margin:0 auto">付款</button>

	</div>


</div>
	</form>


<script>





</script>

</body>
</html>