<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/10/13
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
	String jsPath = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<style>
		.btn {
			width: 200px;
			margin-left: 41%;
		}

		.btn-schedule {
			width: 100%;
			margin-left: 10%;
			text-align: center; /*内容居中*/
		}

		.btn-default {
			width: 100px;
			margin-left: 25%;
			text-align: center; /*内容居中*/
		}

	</style>
</head>

<body>
<div class="container" style="width: 82%;margin:0 auto">


	<div style="margin-top: 30px;">

		<div>
			<h1 style="text-align: center">
				课程表<input type="hidden" id="cid">
			</h1>
		</div>
		<div>
			<h2 style="text-align: center">
				班级名称：<label  id="className"></label>
			</h2>
		</div>
		<div class="layui-form">
			<table class="layui-table">
				<colgroup>
					<col width="50">
					<col width="200">
					<col width="200">
					<col width="200">
					<col width="200">
					<col width="200">
					<col width="200">
				</colgroup>
				<thead>
				<tr>
					<th>课节</th>
					<input type="hidden" name="now-Date" value="${requestScope.tableHead[0]}">
					<th>${requestScope.tableHead[0]}<br>星期一</th>
					<th>${requestScope.tableHead[1]}<br>星期二</th>
					<th>${requestScope.tableHead[2]}<br>星期三</th>
					<th>${requestScope.tableHead[3]}<br>星期四</th>
					<th>${requestScope.tableHead[4]}<br>星期五</th>
				</tr>
				</thead>
				<tbody id="table-body">

				<c:if test="${requestScope.tableBody!=null}">
					<c:forEach items="${requestScope.tableBody}" var="i" begin="0" step="1">
						<tr>
							<td>${i.key}</td>
							<td>
								<input type="hidden" value="${requestScope.tableHead[0]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="j">
								<c:if test="${j.cDate==requestScope.tableHead[0]}">

									<c:choose>
										<c:when test="${j.subId == 1}">
											<button type="button" class="btn btn-schedule layui-btn layui-btn-primary" value="${j.subId}"
											        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn btn-schedule layui-btn" value="${j.subId}"
											        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
										</c:otherwise>
									</c:choose>



							</c:if>
							</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.tableHead[1]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="j">
									<c:if test="${j.cDate==requestScope.tableHead[1]}">
										<c:choose>
											<c:when test="${j.subId == 1}">
												<button type="button" class="btn btn-schedule layui-btn layui-btn-primary" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-schedule layui-btn" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:otherwise>
										</c:choose>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.tableHead[2]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="j">
									<c:if test="${j.cDate==requestScope.tableHead[2]}">
										<c:choose>
											<c:when test="${j.subId == 1}">
												<button type="button" class="btn btn-schedule layui-btn layui-btn-primary" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-schedule layui-btn" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:otherwise>
										</c:choose>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.tableHead[3]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="j">
									<c:if test="${j.cDate==requestScope.tableHead[3]}">
										<c:choose>
											<c:when test="${j.subId == 1}">
												<button type="button" class="btn btn-schedule layui-btn layui-btn-primary" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-schedule layui-btn" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:otherwise>
										</c:choose>

									</c:if>
								</c:forEach>
							</td>
							<td>
								<input type="hidden" value="${requestScope.tableHead[4]}">
								<c:forEach items="${i.value}" begin="0" step="1" var="j">
									<c:if test="${j.cDate==requestScope.tableHead[4]}">
										<c:choose>
											<c:when test="${j.subId == 1}">
												<button type="button" class="btn btn-schedule layui-btn layui-btn-primary" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-schedule layui-btn" value="${j.subId}"
												        onclick="showRegistered(this)" couId="${j.couId}">${j.subName}</button>
											</c:otherwise>
										</c:choose>

									</c:if>
								</c:forEach>
							</td>
						</tr>

					</c:forEach>
				</c:if>


				</tbody>


			</table>
			<a href="/ChildSchool/BackAction/weekcourseTable.action?now-Date=${requestScope.tableHead[0]}&doWhich=上一周&cid=${requestScope.cid}">
				<input type="button" class="btn btn-default layui-btn" value="上一周" onclick="weeks(this)"/>
			</a>

			<a href="/ChildSchool/BackAction/weekcourseTable.action?now-Date=${requestScope.tableHead[0]}&&doWhich=下一周&cid=${requestScope.cid}">
				<input type="button" class="btn btn-default layui-btn" value="下一周" onclick="weeks(this)"/>
			</a>


		</div>
	</div>
</div>


<script>

	var reloadTable = null;
	var layer;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		layer = layui.layer;

	});

	function showRegistered(node) {
		layer.open({
			type: 2
			, title: '配置课程'
			, offset: 'auto'
			, content: '/ChildSchool/BackAction/findSubject.action'
			, area: ['450px', '350px']
			, btn: ['确定', '取消']
			, shade: 0
			, yes: function (index, layero) {
				var subjects = $(layero).find('iframe')[0].contentWindow.subjects.value;
				var couId = $(node).attr("couId");
				var ob = {"subjects": subjects, "couId": couId};
				$.ajax({
					type: "POST",//提交的方式
					url: "/ChildSchool/BackAction/addSubject.action",
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据
						if (msg.msg === "1") {
							alert("排班成功");
							location.reload();
							layer.close(index); //关闭弹窗
						} else if (msg.msg === "0") {
							alert("排班失败");
						}
					},
					error: function () {//错误的方法
						layer.msg("服务器正忙", {icon: 5});
					}
				});

			}
		});
	}


</script>

</body>
</html>
