<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>职位人员统计</title>
	<script src=<%=path + "/js/jquery-3.4.1.js" %>></script>
	<script src=<%=path + "/js/echarts.js" %>></script>


</head>
<body>
<div id="main" style="width: 100%;height:100%; align-content: center"></div>


<script type="text/javascript">

	var nameArr = [];
	var obArr = [];

	$(function () {
		$.ajax({
			method: "POST",
			url: "/ChildSchool/postcount.action",
			dataType: "json",
			success: function (postCountList) {


				for (var i = 0; i < postCountList.length; i++) {
					//alert(postCountList[i].name);
					nameArr.push(postCountList[i].name);
					obArr.push({
						value: postCountList[i].rolenum,
						name: postCountList[i].name
					});

				}
				// alert(nameArr);

				rose();// 创建饼图

			},
			error: function () {
				alert("服务器正忙");
			}
		});
	});


	var myChart = echarts.init(document.getElementById('main'));

	function rose() {

		// 基于准备好的dom，初始化echarts实例


		var option = {
			title: {
				text: '职位人员统计',
				x: 'center',
				textStyle: {

					fontSize: 18
				}
			},
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: nameArr,
				label: {
					normal: {
						textStyle: {

							fontSize: 18
						}
					}

				}
			},
			series: [
				{
					name: '人员',
					type: 'pie',
					radius: '55%',
					center: ['50%', '60%'],
					label: {
						normal: {
							textStyle: {

								fontSize: 18
							}
						}

					},
					data:
					obArr
					,
					itemStyle: {
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}
			]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);


	}


</script>


</body>
</html>
