<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/16
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String uiPath = request.getContextPath() + "/layui/";
%>
<html>
<head>
	<title>体检管理</title>


	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">

	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

</head>
<body>


<div  id="LAY-user-login" style="display: none;">
	<div class="layadmin-user-login-box layadmin-user-login-header">
		<h2>体检管理</h2>
	</div>
	<div style="text-align: center" >
		<%--		<div class="layui-inline" style="width:1000px;text-align: right">--%>
		<%--			<a href="userlogin.jsp">登录</a>&nbsp&nbsp&nbsp&nbsp--%>
		<%--			<a href="userlogin.jsp">注册</a>--%>
		<%--		</div>--%>
		<div class="layui-inline" style="width:500px;">
			<hr>
		</div>
	</div>
	<div class="layui-card-body">
		<div style="text-align: center;">

			<div class="layui-inline">
<%--				<label class="layui-form-label">      </label>--%>
			</div>
			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增体检情况</button>

			</div>
		</div>
	</div>
	<div style="text-align: center">
		<div class="layui-inline" style="width:1000px">
			<table id="demo" lay-filter="demo"></table>
		</div>
	</div>
	<div class="layui-trans layadmin-user-login-footer">

	</div>



</div>



<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>

<script>


	layui.use('table', function () {
		var table = layui.table;

		//第一个实例
		table.render({
			elem: '#demo'
			, height: 350
			,url: '/ChildSchool/findMedicalManage.action' //数据接口
			, page: true //开启分页
			,limit:5
			,done: function(){
				$('.layui-laypage-limits').hide();
			}
			, id: 'demo'
			, cols: [[ //表头
				// {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
				// ,
				{field: 'bname', title: '宝宝名字', align:'center'}
				,{field: 'cname', title: '宝宝班级', align:'center'}
				,{field: 'checkDate', title: '体检时间', align:'center'}
				,{field: 'height', title: '身高', align:'center'}
				,{field: 'weight', title: '体重' , align:'center'}
				,{field: 'vision', title: '视力', align:'center'}
				,{field: 'temperature', title: '体温', align:'center'}
				,{field: 'sId', title: '健康状况', align:'center'}
				,{field:'bId', title: 'bId', hide:true}
				,{field:'cid', title: 'cid', hide:true}
				 ,{field: '', title: '操作', align: "center", toolbar: "#barDemo"}

			]]
		});


		var $ = layui.$, active = {
			reload: function () {
				var demoReload = $('#demo');
				//执行重载
				table.reload('demo', {


					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						key: {
							id: demoReload.val()
						}
					}
				}, 'data');
			}

		};


		layui.config({}).extend({}).use(['table'], function () {
			var $ = layui.$
				, form = layui.form
				, table = layui.table;

			//监听搜索
			form.on('submit(LAY-user-front-search)', function (data) {
				var field = data.field;

				//执行重载
				table.reload('LAY-user-manage', {
					where: field
				});
			});

			//修改
			table.on('tool(demo)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				var data = obj.data;//获得当前行数据
				if(obj.event === 'update'){
					layer.open({
						type: 2
						,title: '修改体检表'
						,content: '/ChildSchool/updatemedicalview.action'
						,maxmin: true
						,area: ['500px', '450px']
						,btn: ['确定', '取消']
						, success : function(layero, index) {
							//回显到窗口
							var body = layer.getChildFrame('body', index);
							body.find("#class").val(data.cname);
							body.find("#babyname").val(data.bname);
							body.find("#high").val(data.height);
							body.find("#weight").val(data.weight);
							body.find("#vision").val(data.vision);
							body.find("#temperature").val(data.temperature);
							body.find("#health").val(data.sId);










						}
						,yes: function(index, layero){
							//窗口拿数据

							//宝宝id
							var  bId =data.bId;
							//班级id
							var  cid =data.cid;
							//时间
							var  checkDate =data.checkDate;




							//身高
							var high = $(layero).find('iframe')[0].contentWindow.high.value;


							//体重
							var weight = $(layero).find('iframe')[0].contentWindow.weight.value;
							//视力

							var vision = $(layero).find('iframe')[0].contentWindow.vision.value;

							//体温
							var temperature = $(layero).find('iframe')[0].contentWindow.temperature.value;
							//健康状况
							var health = $(layero).find('iframe')[0].contentWindow.health.value;

							//正则
							//只能输入中文，字母,下划线
							var regname=/^[A-Za-z\u4e00-\u9fa5]+$/;
							//身高  只能整数或小数
							var heightReg = /^[+-]?\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
							//体重
							var weightReg = /^(0(\.\d{1}){0,1}|[1-8]\d{1,3}(\.\d{1}){0,1}|9\d{1,2}(\.\d{1}){0,1}|999(\.0){0,1}|.{0})$/;
							//视力 只能到5.0
							var visionReg = /^(0\.\d{1}|[1-4]{1}(\.\d{1}){0,1}|5(\.0){0,1}|.{0})$/;
							//体温
							var  temperatureReg  =/^(\-?\d{0,2})(\.\d{0,2})?/;



							if (!heightReg.test(high)){
								layer.msg("身高输入格式有误");
							}else if (!weightReg.test(weight)){
								layer.msg("体重输入格式有误");
							}else if (!visionReg.test(vision)){
								layer.msg("视力输入格式有误");
							}else if (!heightReg.test(temperature)){
								layer.msg("体温输入格式有误");
							}else {
								var ob = {
									bId: bId,
									height: high,
									weight: weight,
									vision: vision,
									temperature: temperature,
									sId: health,
									cid:cid,
									checkDate:checkDate

								};

								$.ajax({
									type: "POST",//提奥的方式
									url: "/ChildSchool/updateMedical.action",//提交的地址
									data: ob ,//提交的数据
									dataType: "json",//希望返回的数据类型
									async: true,//异步操作
									success: function (num) {//成功的方法  msg为返回数据
										// alert(num);
										//msg字符串切割得到list和

										//未查询到数据时执行
										if (num >=1) {
											layer.msg("修改成功");

											// //刷新表格
											// table.reload();
											layer.close(index);
											table.reload('demo');

										}
									},
									error: function () {//错误的方法
										alert("服务器未找到")
									}
								});

							}


						},

						// ,value: data.USERNAME
					});
				}
				else if (obj.event==='delete'){
					layer.confirm('确定删除？', function (index) {
						layer.close(index);


						var ob={uname:name,sname:sname};

						$.ajax({
							type: "POST",//提奥的方式
							url: "",//提交的地址
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (msg) {//成功的方法  msg为返回数据

								// alert(msg);
								//msg字符串切割得到list和
								var arr = msg.sendMsg.split("\\");
								// alert(arr[1]);
								//未查询到数据时执行
								if (arr[0] === "OK") {

									alert("修改成功！");
									// //刷新表格
									// table.reload();
									table.reload('demo');
								}
							},
							error: function () {//错误的方法
								alert("服务器未找到")
							}
						});





					});



				}

			});




			//新增
			var active = {
				batchdel: function () {
					var checkStatus = table.checkStatus('LAY-user-manage')
						, checkData = checkStatus.data; //得到选中的数据

					if (checkData.length === 0) {
						return layer.msg('请选择数据');
					}

					layer.prompt({
						formType: 1
						, title: '敏感操作，请验证口令'
					}, function (value, index) {
						layer.close(index);

						layer.confirm('确定删除吗？', function (index) {

							//执行 Ajax 后重载
							/*
							admin.req({
							  url: 'xxx'
							  //,……
							});
							*/
							table.reload('LAY-user-manage');
							layer.msg('已删除');
						});
					});
				}
				, add: function () {
					layer.open({
						type: 2
						, title: '新增体检情况'
						, content: '/ChildSchool/addmedicalview.action'
						, maxmin: true
						, area: ['500px', '450px']
						, btn: ['确定', '取消']
						, yes: function (index, layero) {
							//alert("增加");

							//$(layero).find('iframe')[0].contentWindow.TYPE.value;
							//alert($(layero).find('iframe')[0].contentWindow.username.value);
							// var body = layer.getChildFrame('body', index);
							// body.find("#babyname").val(data.uname);

							//班级
							var classid = $(layero).find('iframe')[0].contentWindow.classid.value;

							//名字
							var babyname = $(layero).find('iframe')[0].contentWindow.babyname.value;
							//身高
							var high = $(layero).find('iframe')[0].contentWindow.high.value;


							//体重
							var weight = $(layero).find('iframe')[0].contentWindow.weight.value;
							//视力

							var vision = $(layero).find('iframe')[0].contentWindow.vision.value;

							//体温

							var temperature = $(layero).find('iframe')[0].contentWindow.temperature.value;
							//健康状况
							var health = $(layero).find('iframe')[0].contentWindow.health.value;

							//正则
							//只能输入中文，字母,下划线
							var regname=/^[A-Za-z\u4e00-\u9fa5]+$/;
							//身高  只能整数或小数
							var heightReg = /^[+-]?\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
							//体重
							var weightReg = /^(0(\.\d{1}){0,1}|[1-8]\d{1,3}(\.\d{1}){0,1}|9\d{1,2}(\.\d{1}){0,1}|999(\.0){0,1}|.{0})$/;
							//视力 只能到5.0
							var visionReg = /^(0\.\d{1}|[1-4]{1}(\.\d{1}){0,1}|5(\.0){0,1}|.{0})$/;
							//体温
							var  temperatureReg  =/^(\-?\d{0,2})(\.\d{0,2})?/;

							if (Number(classid)===0){
								layer.msg("请选择班级");
							}else if (Number(babyname)===0){
								layer.msg("请选择宝宝名字");
							}else if (!heightReg.test(high)){
								layer.msg("身高输入格式有误");
							}else if (!weightReg.test(weight)){
								layer.msg("体重输入格式有误");
							}else if (!visionReg.test(vision)){
								layer.msg("视力输入格式有误");
							}else if (!heightReg.test(temperature)){
								layer.msg("体温输入格式有误");
							}else {
								var ob = {
									bId: babyname,
									height: high,
									weight: weight,
									vision: vision,
									temperature: temperature,
									sId: health
								};

								$.ajax({
									type: "POST",//提奥的方式
									url: "/ChildSchool/addmedical.action",//提交的地址
									data: ob ,//提交的数据
									dataType: "json",//希望返回的数据类型
									async: true,//异步操作
									success: function (num) {//成功的方法  msg为返回数据
										alert(num);
										//msg字符串切割得到list和

										//未查询到数据时执行
										if (num >=1) {
											layer.msg("添加成功");

											// //刷新表格
											// table.reload();
											table.reload('demo');
											layer.close(index);
										}
									},
									error: function () {//错误的方法
										alert("服务器未找到")
									}
								});

							}



							// if(!new RegExp("^1[345789]\d{9}$").test(vision)){
							// 	layer.msg("手机号码格式错误");
							// }











							// var iframeWindow = window['layui-layer-iframe']
							// 	,submitID = 'LAY-user-front-submit'
							// 	,submit = layero.find('iframe').contents().find('#'+ submitID);
							//
							// //监听提交
							// iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
							// 	var field = data.field; //获取提交的字段
							//
							// 	alert(field);
							//
							//
							// 	//提交 Ajax 成功后，静态更新表格中的数据
							// 	//$.ajax({});
							// 	table.reload('LAY-user-front-submit'); //数据刷新
							//关闭弹层
							// });
							//
							// submit.trigger('click');
						}
					});
				}
			};

			$('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});


	});


</script>





</body>
</html>

