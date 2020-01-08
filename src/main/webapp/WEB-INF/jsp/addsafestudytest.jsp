<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>配置试题</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="container" style="width: 90%;margin:0 auto">
	<div style="margin-top: 30px;">
		<div>
			<h1 style="text-align: center">
				配置试题
			</h1>
		</div>
		<div>
			<h2 style="text-align: left">
				判断题：每小题1分，共${requestScope.questionNum}分
			</h2>
		</div>
		<div style="text-align: right">
			<button type="button"  style="text-align: right" 	class="layui-btn"
			        onclick="addQuestion( )">新增
			</button>
		</div>
		<div class="layui-form">
			<table class="layui-table">
				<colgroup>
					<col width="6%">
					<col width="35%">
					<col width="35%">
					<col width="12%">
					<col width="12%">

				</colgroup>
				<c:if test="${requestScope.tableBody!=null}">
					<c:forEach items="${requestScope.tableBody}" var="i" begin="0" step="1" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td colspan="4">${i.question}</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="radio" name="sex" value=${i.optionA} title=${i.optionA}></td>
							<td><input type="radio" name="sex" value=${i.optionB} title=${i.optionB}></td>
							<td>
								<button type="button" class="btn btn-schedule layui-btn layui-btn-primary"
								        optionB="${i.optionB}"   optiona="${i.optionA}"    question="${i.question}"
								        questionId="${i.questionId}"  answer="${i.answer}"
								        onclick="updateQuestion(this)">修改
								</button>
							</td>
							<td>
								<button type="button" class="layui-btn layui-btn-danger"
								        questionId="${i.questionId}"     onclick="deleteQuestion(this)">删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
</div>
<script>
	var layer;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		layer = layui.layer;
		$ = layui.$;
	});

	// 新增试题
	function addQuestion() {
		layer.open({
			type: 2
			, title: '新增试题'
			, offset: 'auto'
			, content: '/ChildSchool/web/addsafestudyquestion.action'
			, area: ['600px', '450px']
			, btn: ['确定', '取消']
			, shade: 0
			, yes: function (index, layero) {
				var question = $(layero).find('iframe')[0].contentWindow.question.value;
				var optiona = $(layero).find('iframe')[0].contentWindow.optiona.value;
				var optionb = $(layero).find('iframe')[0].contentWindow.optionb.value;
				var answer = $(layero).find('iframe')[0].contentWindow.answer.value;
				var ob = {"question": question, "optiona": optiona, "optionb": optionb, "answer": answer, "safeId": ${requestScope.safeId}};
				$.ajax({
					type: "POST",//提交的方式
					url: "/ChildSchool/BackAction/addSafeStudyTest.action",
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据
						if (msg.msg === "1") {
							layer.msg('新增成功', {
								time: 1000,
								icon: 1,
								offset: '50px'
							}, function () {
								location.reload()
							});
						} else if (msg.msg === "0") {
							layer.msg("新增失败", {icon: 5});
						}


					},
					error: function () {//错误的方法
						layer.msg("服务器正忙", {icon: 5});
					}
				});
			}
		});
	}
	// 修改试题
	function updateQuestion(node) {
		layer.open({
			type: 2
			, title: '修改试题'
			, offset: 'auto'
			, content: '/ChildSchool/web/updatequestionview.action'
			, area: ['600px', '450px']
			, btn: ['确定','取消']
			, shade: 0
			, success: function (layero, index) {
				var body = layer.getChildFrame('body', index);
				body.find("#question").val($(node).attr("question"));
				body.find("#optiona").val($(node).attr("optiona"));
				body.find("#optionb").val($(node).attr("optionb"));
				body.find("#answer").val($(node).attr("answer"));
			}
			, yes: function (index, layero) {
				var question = $(layero).find('iframe')[0].contentWindow.question.value;
				var optiona = $(layero).find('iframe')[0].contentWindow.optiona.value;
				var optionb = $(layero).find('iframe')[0].contentWindow.optionb.value;
				var answer = $(layero).find('iframe')[0].contentWindow.answer.value;
				var questionId = $(node).attr("questionId");
				var ob = {"question": question, "optiona": optiona, "optionb": optionb, "answer": answer,"questionId":questionId};
				$.ajax({
					type: "POST",//提交的方式
					url: "/ChildSchool/BackAction/updateQuestion.action",
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据
						if (msg.msg === "1") {
							layer.msg('修改成功', {
								time: 1000,
								icon: 1,
								offset: '50px'
							}, function () {
								location.reload()
							});
						} else if (msg.msg === "0") {
							layer.msg("修改失败", {icon: 5});
						}
					},
					error: function () {//错误的方法
						layer.msg("服务器正忙", {icon: 5});
					}
				});
			}
		});

	}
	// 删除试题
	function deleteQuestion(node) {
		layer.confirm('真的删除行么', function (index) {
			var questionId = $(node).attr("questionId");
			var ob = {"questionId": questionId};
			$.ajax({
				url: '/ChildSchool/BackAction/deleteQuestion.action',
				type: "POST",
				data: ob,
				dataType: "json",
				success: function (msg) {
					if (msg.msg === "ok") {
						layer.msg("删除成功", {icon: 6});
						location.reload();
						layer.close(index);
					} else if (msg.msg === "error") {
						layer.msg("删除失败", {icon: 5});
					}

				},
				error: function () {
					layer.msg("服务器正忙", {icon: 5});
				}
			});
		});
	}

</script>
</body>
</html>