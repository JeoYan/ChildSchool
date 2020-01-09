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

								<button type="button" class="layui-btn layui-btn-sm" onclick="pay(this)">购买</button>

								<input type="hidden" value="${i.bookid}">
								<input type="hidden" value="${i.bookName}">
								<input type="hidden" value="${i.paytype}">

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

		var bookid = $(node).parent().find("input").eq(0).val();
		var bookName = $(node).parent().find("input").eq(1).val();

		//alert(bookid);



		var ob = {bookid: bookid};


		$.ajax({
			type: "POST",//提奥的方式
			url: "/ChildSchool/findbooktype.action",//提交的地址
			data: ob,//提交的数据
			dataType: "json",//希望返回的数据类型
			async: true,//异步操作
			success: function (data) {//成功的方法  msg为返回数据

				//alert(data);
				layui.use('layer', function () {

					if(data===2){
						var bookid = $(node).parent().find("input").eq(0).val();
						var bookName = $(node).parent().find("input").eq(1).val();
						layer.msg('确定下载?', {
							// area:['200px', '150px'],
							anim: 1,
							btn: ['确定', '取消']
							,yes: function(index, layer){
								//按钮【按钮一】的回调
								layui.layer.close(index);
								var url = "${pageContext.request.contextPath}/readBook/downloadBook.action?bookid="+bookid+"&bookName="+bookName;
								var xmlResquest = new XMLHttpRequest();
								xmlResquest.open("POST", url, true);
								xmlResquest.setRequestHeader("Content-type", "application/json");
								xmlResquest.setRequestHeader("Authorization", "Bearer 6cda86e3-ba1c-4737-972c-f815304932ee");
								xmlResquest.responseType = "blob";
								xmlResquest.onload = function (oEvent) {
									var content = xmlResquest.response;
									var elink = document.createElement('a');
									elink.download = bookName+".zip";
									elink.style.display = 'none';
									var blob = new Blob([content]);
									elink.href = URL.createObjectURL(blob);
									document.body.appendChild(elink);
									elink.click();
									document.body.removeChild(elink);

								};
								xmlResquest.send();

								layer.msg("开始下载！");


							}



						});

					}else {

						layer.msg("您还未购买过此书，请您先购买，才能下载！");

					}

				});

			},
			error: function () {//错误的方法
				alert("服务器未找到")
			}
		});













		// alert("下载");
		// layer.msg("开始下载！");



	}


	function pay(node) {

		var bookid = $(node).parent().find("input").eq(0).val();
		var bookName = $(node).parent().find("input").eq(1).val();
		var paytype = $(node).parent().find("input").eq(2).val();

		var ob = {bookid: bookid};

		//alert(bookid);


		$.ajax({
			// type: "POST",//提奥的方式
			url: "/ChildSchool/findbooktype.action",//提交的地址
			data: ob,//提交的数据
			dataType: "json",//希望返回的数据类型
			async: true,//异步操作
			success: function (data) {//成功的方法  msg为返回数据




				layui.use('layer', function () {

					if(data===1){

						layer.open({
							type: 2
							,title: '购买'
							,content: '${pageContext.request.contextPath}/payView.action'
							,maxmin: true
							,btn: ['关闭']
							,area: ['400px', '300px']
							, success : function(layero, index) {
								// var bookid1 = $(node).parent().find("input").eq(0).val();
								//alert("插入"+bookid);

								var body = layui.layer.getChildFrame('body', index);
								body.find("#bookid").val(bookid);
								//window.location.href="/demo5/PayDemo.jsp";
							}


						});





					}else {

						layer.msg("您已购买过此书，无需再次购买！");

					}



				});



			},
			error: function () {//错误的方法
				alert("服务器未找到")
			}
		});










	}


</script>

</body>
</html>