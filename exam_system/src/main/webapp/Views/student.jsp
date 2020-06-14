<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>学员管理</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css" type="text/css">
</head>
<body>
<div style="margin-top: 30px">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">学员姓名</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="sname"  id="sname" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">学号</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="sno" id="sno" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">毕业院校</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="sschool" id="sschool" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">学生班级</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="sclass" id="sclass" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<button type="button" class="layui-btn" lay-sbumit id="select">查询</button>
		</div>
	</div>
	<script type="text/html" id="toolbar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-sm" lay-event="add">登记学员</a>
			<a class="layui-btn layui-btn-sm" lay-event="delmany">批量删除</a>
		</div>
	</script>

	<script type="text/html" id="bar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</div>
	</script>
		<table id="order" lay-filter="order" style="margin-right: 20px;"></table>
</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table' ],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;

					// 表格渲染
					table.render({
						elem : '#order',
						url : '/exam01/stu_list',
						page : true,
						toolbar : '#toolbar',
						limit : 5,
						limits : ['5','10','20'],
						defaultToolbar : [ 'filter', 'print', 'exports' ],
						cols : [ [ {
							   field : 'id'
							   ,type : 'checkbox'
						      },{
						    	  field:'sname'
                                  ,title:'学生姓名'
                                   ,align: 'center'
                                  ,width:'10%'
							   },{
								   field:'sphone'
	                               ,title:'联系电话'
	                            	   ,align: 'center'
	                               ,width:'15%'
								},{
									field:'sclass'
		                            ,title:'学生班级'
		                            	,align: 'center'
		                            ,width:'13%'
								},{
									field:'sgender'
			                        ,title:'性别'
			                        	,align: 'center'
			                         ,width:'10%'
								},{
									field:'sno'
				                    ,title:'学号'
				                    	,align: 'center'
				                     ,width:'13%'
								},{
									field:'sschool'
					                 ,title:'毕业院校'
					                	 ,align: 'center'
					                 , width:'12%'
					                                
								},{
									field:'smajor'
						             ,title:'大学专业'
						            	 ,align: 'center'
						             ,width:'15%'
								}, {
									fixed : 'right',
									title : '操作',
									toolbar : '#bar',
									width : 150
								}] ]
					});

					// 工具栏事件处理
					table.on('toolbar(order)', function(btn) { // btn 被操作的工具栏按钮
						var evt = btn.event;
						switch (evt) {
						case 'add':
							layer.open({
								type : 2,
								title: '新增学生',
								closeBtn : 1,
								area :['500px', '550px'],
								offset : '80px',
								maxmin: false, 
								btn: false,
								content : ['/exam01/BeforAddStu', 'no' ], 
								end: function(){
									table.reload('order');
								}
							});
							break;

						/* case 'adds':
							layer.open({
								type : 2,
								title: '导入学生Excel',
								closeBtn : 1,
								area :['550px', '650px'],
								maxmin: false, 
								btn: false,
								content : ['/exam01/Views/stuAddExcel.jsp', 'no' ], 
								end: function(){
									table.reload('order');
								}
							});
							break; */
 
							
						case 'delmany':
							layer.confirm("确认要删除吗？一旦删除数据不可恢复！", {
								icon : 0,
								title : '询问'
							}, function(win) { // win 当前的确认框
								var checkStatus = table.checkStatus('order');
								if (!checkStatus.data.length > 0) {
									layer.alert('请至少选取一行要删除的数据。');
									layer.close(win);
									return;
								}
								layer.close(win);
								var id = '';
								layui.each(checkStatus.data, function(index,row) {
									id += row.id + ",";
								});

								id = id.substring(0, id.length - 1);

								$.post('/exam01/stu_delm', {
									id : id
								}, function(res) {
									layer.alert(res.msg);
									table.reload('order');
								});

								

							}, function() {
								// '点击确认框取消按钮' 
							});

							break;
						}
						;
					});

					table.on('tool(order)', function(btn) {
						var evt = btn.event;
						switch (evt) {
						case 'edit':
							layer.open({
										type : 2,
										title : '编辑学生信息',
										closeBtn : 1,
										area : [ '600px', '350px' ],
										offset:'100px',
										maxmin : false, 
										btn : false,
										content : ['/exam01/stu_upd?id='+ btn.data.id, 'no' ], 
										end : function() {
											table.reload('order');
										}
									});
							break;
						case 'del':
							layer.confirm('确定删除当前行数据？', {
								icon : 0,
								title : '询问'
							}, function(win) {
								layer.close();
								var id = btn.data.id;
								$.get('/exam01/stu_delo?id=' + id, function(res) {
									layer.alert(res.msg);
								});
								table.reload('order');
							}, function() {
								// 确认框取消按钮事件处理函数
							});

							break;
						}
					});

					//查询按钮点击事件
					$('#select').on('click',function(){
						var  sname = $('#sname').val();
						var  sno = $('#sno').val();
						var  sclass = $('#sclass').val();
						var  sschool = $('#sschool').val();
						$.ajax('/exam01/stu_find',{
							type:'get',
							asyn:false,
							data:{
								sname:sname,
								sno:sno,
								sclass:sclass,
								sschool:sschool,
							},
							success:function(res){
								console.log(res.data);
								table.render({
									elem : '#order',
									page : true,
									data:res.data,
									toolbar : '#toolbar',
									defaultToolbar : [ 'filter', 'print', 'exports' ],
									cols : [ [ {
										   field : 'id'
										   ,type : 'checkbox'
									      },{
									    	  field:'sname'
			                                  ,title:'学生姓名'
			                                   ,align: 'center'
			                                  ,width:'10%'
										   },{
											   field:'sphone'
				                               ,title:'联系电话'
				                            	   ,align: 'center'
				                               ,width:'15%'
											},{
												field:'sclass'
					                            ,title:'学生班级'
					                            	,align: 'center'
					                            ,width:'13%'
											},{
												field:'sgender'
						                        ,title:'性别'
						                        	,align: 'center'
						                         ,width:'10%'
											},{
												field:'sno'
							                    ,title:'学号'
							                    	,align: 'center'
							                     ,width:'13%'
											},{
												field:'sschool'
								                 ,title:'毕业院校'
								                	 ,align: 'center'
								                 , width:'12%'
								                                
											},{
												field:'smajor'
									             ,title:'大学专业'
									            	 ,align: 'center'
									             ,width:'15%'
											}, {
												fixed : 'right',
												title : '操作',
												toolbar : '#bar',
												width : 150
											}] ]
								});
								var index = parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
							},
							error: function(res) {
								layer.alert(res.msg);
							}
						})
					});	
				});
	</script>
</body>
</html>
