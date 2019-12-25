<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>亲子读物</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<link rel="stylesheet" type="text/css" href=<%=path+"/res/static/css/main.css"%>>
	<%--	<link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">--%>
	<%--	<script type="text/javascript" src=<%=path+"/res/layui/layui.js"%>></script>--%>
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>


</head>
<body id="list-cont">


<div class="content">

	<div class="product-list-box" id="product-list-box">
		<div class="product-list-cont w1200">
			<h4>亲子读物</h4>
			<div class="product-item-box layui-clear">
				<c:if test="${requestScope.dataList!=null}">
					<c:forEach items="${requestScope.dataList}" var="i" begin="0" step="1">
						<div class="list-item">
							<a href="javascript:;"><img src="/ChildSchool/readBook/showImage.action?url=${i.url}"
							                            style="width: 150px;height:150px "></a>
							<p>
							<div style="text-align: center">${i.bookName}</div>
							<span class="pri">
							<button type="button" class="layui-btn layui-btn-sm" onclick="seeBook(this)">预览</button>
							<input type="hidden" value="${i.bookid}">
							</span>
							<span class="nub">
							<button type="button" class="layui-btn layui-btn-sm" onclick="download(this)">下载</button>
							<input type="hidden" value="${i.bookid}">
							</span>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">

	function seeBook(node) {
		alert("预览");
		var bookid = $(node).parent().find("input").eq(0).val();
		// alert(bookid);
		layui.use('layer', function () {
			var $ = layui.jquery, layer = layui.layer;
			$.getJSON('${pageContext.request.contextPath}/readBook/getBook.action?bookid=' + bookid, function (json) {
				layer.photos({
					photos: json
					, shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
				});
			});
		});
	}

	function download(node) {
		alert("下载");
		var bookid = $(node).parent().find("input").eq(0).val();
		alert(bookid);
	}


</script>
</body>
</html>