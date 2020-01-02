<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/26
  Time: 14:26
  教师查看宝宝作业完成情况.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>查看作业</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-row" id="mark" style="display:none;">
	<div style="width: 96%; height: 96%;margin: 0 auto">
		<h1 style="text-align: center">作业打分</h1>
		<div>
			<form class="layui-form" action="">
				<select id="score" lay-verify="">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
					<option value="E">E</option>
				</select>
			</form>
		</div>
		<button class="layui-btn layui-btn-sm" id="save">保存</button>
		<button class="layui-btn layui-btn-sm" id="cancel">取消</button>
	</div>
</div>
	<div class="layui-container" id="box">
		<div style="text-align: center">
			<div class="layui-inline" style="width:100%">
				<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>
			</div>
		</div>
	</div>
</body>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="lookHomework"><a href="#" target="_blank">查看作业</a></button>
		<button class="layui-btn layui-btn-sm" lay-event="markHomework">批改作业</button>
	</div>
</script>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script>

	layui.use(['form','layer','table'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var data;
		var $ = layui.$;
		var object;
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
		form.render();
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,filter:'test'
			,url: '/ChildSchool/HomeworkController/lookHomework'
			,done:function(res,curr,count){
					// 隐藏列
					// $(".layui-table-box").find("[data-field='url']").css("display","none");
					// $(".layui-table-box").find("[data-field='bWid']").css("display","none");
			}
			,cols: [[
				{field: 'bid', title: '宝宝编号',align: 'center'}
				,{field: 'bName', title: '宝宝名称',align: 'center'}
				,{field: 'pName', title: '家长名称', align: 'center', sort: true}
				,{field: 'hName', title: '作业名称',align: 'center'}
				,{field: 'hDate', title: '发布时间', width:180,align: 'center'}
				,{field: 'submitTime', title: '完成时间', width:180,align: 'center'}
				,{title:'操作', toolbar: '#toolbarDemo', width:200,align: 'center'}
				,{field: 'url',align: 'center',hide:true}
				,{field: 'bWid',align: 'center',hide:true}
			]]
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
			if(layEvent==='lookHomework'){
				obj.tr.find('a').attr('href','/ChildSchool/HomeworkController/download?bWid='+data.bWid);
			}else if (layEvent==='markHomework') {
				layer.open({
					type:1,
					title:"批改作业",
					area: ['20%','30%'],
					content: $("#mark")
				});

			}
			$("#cancel").on('click',function () {
				layer.closeAll();
			});
		});



		$("#save").on('click',function () {
			layer.confirm('确定评价?', function(index){
				$.ajax({
					url:'/ChildSchool/HomeworkController/markHomework',
					type:'Post',
					data:{
						bWid:data.bWid,
						score:$("#score").val()
					},
					success:function (msg) {
						if($.trim(msg)==="1"){
							layer.msg("评价成功", {icon: 6});
							setTimeout(function(){
								layer.closeAll();//关闭所有的弹出层
							}, 500);
						}else{
							layer.msg("评价失败", {icon: 5});
						}
					},
					error:function () {
						layer.msg("数据传输失败");
					}
				});
				layer.close(index);
			});
		});

	});

</script>
</html>
