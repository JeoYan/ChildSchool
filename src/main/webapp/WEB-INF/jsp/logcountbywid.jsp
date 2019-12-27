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
	<title>按人员统计日志</title>
	<script  src=<%=path+"/js/jquery-3.4.1.js" %>></script>
	<script  src=<%=path+"/js/echarts.js" %>></script>
</head>
<body>
<div id="main" style="width: 95%;height:95%; align-content: center" ></div>

<script type="text/javascript">
	var countArr = [];
	var wNameArr= [];

	$(function() {
		$.ajax({
			method : "POST",
			url : "/ChildSchool/BackAction/logCountByWid.action",
			dataType : "json",
			success : function(logCounts) {
				for (var i = 0; i < logCounts.length; i++) {


					countArr.push(logCounts[i].count);
					wNameArr.push(logCounts[i].wName);


				}

				createEchars();//

			},
			error : function() {
				alert("服务器正忙");
			}
		});
	});



	var myChart = echarts.init(document.getElementById('main'));



	function createEchars(){
		var option = {
			title : {
				text: '按月统计日志'

			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['操作数量']
			},
			toolbox: {
				show : true,
				feature : {
					dataView : {show: true, readOnly: false},
					magicType : {show: true, type: ['line', 'bar']},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			xAxis : [
				{
					name:'人员',
					type : 'category',
					data : wNameArr
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					name:'操作数量',
					type:'bar',
					data:countArr,
					markPoint : {
						data : [
							{type : 'max'},
							{type : 'min'}
						]
					},
					markLine : {
						data : [
							{type : 'average'}
						]
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
