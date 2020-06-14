<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>考试管理</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css" type="text/css">
</head>
<body>
  <div class="layui-form-item" style="margin-top: 5px">
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">考试名称</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="ename" id="ename" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">考试编号</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="eno" id="eno" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">考试类型</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="etype" id="etype" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">时间段</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="times" placeholder=" - ">
			</div>
		</div>
		<div class="layui-inline">
			<button id="select" type="button" class="layui-btn" lay-sbumit>查询</button>
		</div>
	</div>
	
	<script type="text/html" id="toolbar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-sm" data-type="lt" lay-event="add">添加考试</a>
			<a class="layui-btn layui-btn-sm" lay-event="delmany">批量删除</a>
		</div>
	</script>

	<script type="text/html" id="bar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑考试</a>
			<a class="layui-btn layui-btn-xs" lay-event="addstu">添加学生</a>
			<a class="layui-btn layui-btn-xs" lay-event="findstu">编辑考生</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</div>
	</script>
		<table id="order" lay-filter="order"></table>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table','laydate'],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;
					var laydate = layui.laydate;

					 laydate.render({
						    elem: '#times'
						    ,range: true
						    ,theme: 'grid'
						  });

					// 表格渲染
					table.render({
						elem : '#order',
						url : '/exam01/exam_list',
						page : true,
						toolbar : '#toolbar',
						defaultToolbar : [ 'filter', 'print', 'exports' ],
						cols : [ [ {
							   field : 'id'
							   ,type : 'checkbox'
						      },{
						    	field:'eno'
                               ,title:'考试编号'
                            	   ,align: 'center'
                                ,width:'10%'
							   },{
								   field:'ename'
	                               ,title:'考试名称'
	                               ,align: 'center'
	                               ,width:'12%'
								},{
									field:'etype'
		                            ,title:'考试类型'
		                            ,align: 'center'
		                            ,width:'8%'
								},{
									field:'estarttime'
			                        ,title:'开始时间'
			                        ,align: 'center'
			                         ,width:'11%'
								},{
									field:'eendtime'
				                    ,title:'截止时间'
				                    ,align: 'center'
				                     ,width:'11%'
								},{
									field:'etno'
					                    ,title:'监考老师'
					                    ,align: 'center'
					                     ,width:'7%'
									},{
									field:'estatus'
					                 ,title:'状态'
					                ,align: 'center'
					                 , width:'8%'
					                                
								},{
									field:'tpno'
						             ,title:'试卷名称'
						             ,align: 'center'
						             ,width:'11%'
								}, {
									fixed : 'right',
									title : '操作',
									toolbar : '#bar',
									width : 265
								}] ]
					});


					// 工具栏事件处理
					table.on('toolbar(order)', function(btn) { // btn 被操作的工具栏按钮
						var evt = btn.event;
						switch (evt) {
						case 'add':
							layer.open({
								type : 2,
								title: '新增考试',
								closeBtn : 1,
								offset: '50px',
								area :['600px','700px'],
								maxmin: false, 
								btn: false,
								content : ['/exam01/BeforAddExam', 'no' ], 
								end: function(){
									table.reload('order');
								}
							});
							break;

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

								$.post('/exam01/exam_delm', {
									id : id
								}, function(res) {
									layer.alert(res.msg);
								});

								table.reload('order');

							}, function() {
								// '点击确认框取消按钮' 
							});

							break;
						}
						;
					});
					//查询按钮点击事件
					$('#select').on('click',function(){
						var ename = $('#ename').val();
						var eno = $('#eno').val();
						var etype = $('#etype').val();
						var times = $('#times').val();
						var strs = new Array();
						strs = times.split(" - ");
						var estarttime = strs[0];
						var eendtime = strs[1];
						$.ajax('/exam01/exam_find',{
							type:'get',
							asyn:false,
							data:{
								ename:ename,
								eno:eno,
								etype:etype,
								estarttime:estarttime,
								eendtime:eendtime
							},
							success:function(res){
								console.log(res.data);
								table.render({
									elem : '#order',
									data:res.data,
									page : true,
									toolbar : '#toolbar',
									defaultToolbar : [ 'filter', 'print', 'exports' ],
									cols : [ [ {
										   field : 'id'
										   ,type : 'checkbox'
									      },{
									    	field:'eno'
			                               ,title:'考试编号'
			                            	   ,align: 'center'
			                                ,width:'10%'
										   },{
											   field:'ename'
				                               ,title:'考试名称'
				                               ,align: 'center'
				                               ,width:'12%'
											},{
												field:'etype'
					                            ,title:'考试类型'
					                            ,align: 'center'
					                            ,width:'8%'
											},{
												field:'estarttime'
						                        ,title:'开始时间'
						                        ,align: 'center'
						                         ,width:'11%'
											},{
												field:'eendtime'
							                    ,title:'截止时间'
							                    ,align: 'center'
							                     ,width:'11%'
											},{
												field:'etno'
								                    ,title:'监考老师'
								                    ,align: 'center'
								                     ,width:'7%'
												},{
												field:'estatus'
								                 ,title:'状态'
								                ,align: 'center'
								                 , width:'8%'
								                                
											},{
												field:'tpno'
									             ,title:'试卷名称'
									             ,align: 'center'
									             ,width:'11%'
											}, {
												fixed : 'right',
												title : '操作',
												toolbar : '#bar',
												width : 265
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
					table.on('tool(order)', function(btn) {
						var evt = btn.event;
						switch (evt) {
						case 'edit':
							layer.open({
										type : 2,
										title : '编辑考试信息',
										closeBtn : 1,
										area : [ '600px', '350px' ],
										offset: '100px',
										maxmin : false, 
										btn : false,
										content : ['/exam01/exam_upd?id='+ btn.data.id, 'no' ], 
										end : function() {
											table.reload('order');
										}
									});
							break;

						case 'addstu':
							var eno = btn.data.eno;
							layer.open({
								type : 2,
								title: '添加考试学生',
								closeBtn : 1,
								offset: '50px',
								area :['600px','650px'],
								maxmin: false, 
								btn: false,
								content : ['/exam01/forward_to_examAddStu?eno='+eno, 'yes' ],
								end: function(){
									table.reload('order');
								}
							});
							break;

						case 'findstu':
							var eno = btn.data.eno;
							layer.open({
								type : 2,
								title: '编辑考试学生',
								closeBtn : 1,
								offset: '50px',
								area :['600px','650px'],
								maxmin: false, 
								btn: false,
								content : ['/exam01/forward_to_examStu?eno='+eno, 'yes' ],
								end: function(){
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
								$.get('/exam01/exam_delo?id=' + id, function(res) {
									layer.alert(res.msg);
								});
								table.reload('order');
							}, function() {
								// 确认框取消按钮事件处理函数
							});

							break;
						}
					});
				});
	</script>
</body>
</html>
