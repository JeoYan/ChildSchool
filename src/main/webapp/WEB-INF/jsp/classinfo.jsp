<%--
  Created by IntelliJ IDEA.
  User: 陈美杰
  Date: 2019/12/17
  Time: 14:48
  用来显示班级信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>班级信息</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-row" id="lookBabyInfo" style="display:none;">
	<div>
		<h1>宝宝信息</h1>
		<div class="layui-form-item">
			<label class="layui-form-label">宝宝姓名：</label>
			<label class="layui-form-label" id="babyName"></label>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出生年月：</label>
			<label class="layui-form-label" id="babyBirth"></label>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">性别：</label>
			<label class="layui-form-label" id="babySex"></label>
		</div>

		<h1>家长信息</h1>
		<div class="layui-form-item">
			<label class="layui-form-label">家长姓名：</label>
			<label class="layui-form-label" id="parentName"></label>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">亲子关系：</label>
			<label class="layui-form-label" id="relation"></label>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话号码：</label>
			<label class="layui-form-label" id="phone"></label>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">职业：</label>
			<label class="layui-form-label" id="job"></label>
		</div>
	</div>

</div>

<div class="layui-container" id="box">
	<h1>班级信息</h1>
	<div class="demoTable">
		<div>班级名称：英才小（1）班</div>
		查询条件：
		<div class="layui-inline">
			<input type="date" class="layui-input" name="beginDate" id="beginDate" autocomplete="off">
			<input type="date" class="layui-input" name="endDate" id="endDate" autocomplete="off">
		</div>
		<button class="layui-btn" id="query" data-type="reload">搜索</button>
	</div>
	<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>
</div>
</body>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="babyInfo">宝宝信息</button>
	</div>
</script>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script>
	layui.use(['layer','table'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var data;
		var $ = layui.$;
		var object;
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,filter:'test'
			,url: '/ChildSchool/ClassInfoController/queryClassInfo'
			,cols: [[
				{field: 'bid', title: '宝宝编号', width:100}
				,{field: 'bName', title: '宝宝名称', width:140}
				,{field: 'pName', title: '家长名称', width:140, sort: true}
				,{field: 'bDate', title: '入学时间', width: 140, sort: true}
				,{field: 'bSex', title: '性别', width: 80}
				,{title:'操作', toolbar: '#toolbarDemo', width:150}
			]]
			,cellMinWidth: 80
			,limits:[5,10,20,40,100]
			,limit:5
			,id: 'testReload'
			,page: true
			,height: 350
		});

		//监听事件
		table.on('tool(test)', function(obj){
			object = obj;
			data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if(layEvent==='babyInfo'){
				var bid = data.bid;
				$.ajax({
					type:'post',
					url:'/ChildSchool/ClassInfoController/queryBabyInfo',
					data:{'bid':bid},
					dataType:'json',
					async:true,
					success:function (babyInfo) {
						$("#babyName").text(babyInfo[0].bName);
						$("#babySex").text(babyInfo[0].bSex);
						$("#babyBirth").text(babyInfo[0].bBirth);
						$("#parentName").text(babyInfo[1].pname);
						$("#relation").text(babyInfo[1].prelation);
						$("#phone").text(babyInfo[1].pphone);
						$("#job").text(babyInfo[1].pjob);
					},
					error:function () {
						layer.msg("数据传输失败");
					}
				});

				layer.open({
					type:1,
					title:"宝宝家庭信息",
					area: ['20%','70%'],
					content: $("#lookBabyInfo")
				});
			}
		});


		var active = {
			reload: function(){
				var beginDate = $('#beginDate');
				var endDate = $('#endDate');
				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {
						'beginDate':beginDate.val(),
						'endDate':endDate.val()
					}
				}, 'data');
			}
		};

		$('#query').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});


	});

</script>
</html>
