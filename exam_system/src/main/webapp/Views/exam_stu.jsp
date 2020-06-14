<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查看考生</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
</head>
<body>
	<div class="layui-container wrapper" style="margin-top: 30px">
	<input type="hidden" id="eno" value="${eno}">
          <!--  <div class="layui-form-item">
				<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 12px">班级名称：</label>
			<div class="layui-input-inline" style="width: 120px">
				<input type="text" name="sname" id="sname" autocomplete="off" class="layui-input" placeholder="请输入">
			</div>
		</div>
				<div class="layui-inline">
					<button type="button" class="layui-btn" id="find"
						lay-sbumit>查询</button>
				</div>
			</div> -->
	</div>
	<script type="text/html" id="toolbar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-sm" lay-event="delmany">批量删除</a>
		</div>
	</script>

	<script type="text/html" id="bar">
		<div class="layui-btn-container">
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</div>
	</script>
		<table id="order" lay-filter="order"></table>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer' , 'table'], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var table = layui.table;
			
			eno=$('#eno').val();

			table.render({
				elem : '#order',
				url : '/exam01/show_estu?eno='+eno,
				page : true,
				toolbar : '#toolbar',
				limit:5,
				limits:[5,8,10],
				defaultToolbar : [ 'filter', 'print', 'exports' ],
				cols : [ [ {
					  	field : 'id'
					   ,type : 'checkbox'
				      },{
					    field:'sno'
			            ,title:'学号'
						},{
				    	field:'sname'
                       ,title:'姓名'
					   },{
						field:'sclass'
		                ,title:'学生班级'
						},{
						fixed : 'right',
						title : '操作',
						toolbar : '#bar',
						width : 120
						}] ]
			});
			

			table.on('toolbar(order)', function(btn) { // btn 被操作的工具栏按钮
				var evt = btn.event;
				switch (evt) {
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
						var sno = '';
						layui.each(checkStatus.data, function(index,row) {
							sno += row.sno + ",";
						});

						sno = sno.substring(0, sno.length - 1);

						$.post('/exam01/estu_delm', {
							sno : sno,
							eno : eno
						}, function(res) {
							layer.alert(res.msg);
						});

						table.reload('order');

					}, function() {
						// '点击确认框取消按钮' 
					});

					break;
				};
			});

			table.on('tool(order)', function(btn) {
				var evt = btn.event;
				switch (evt) {
				case 'del':
					layer.confirm('确定删除当前行数据？', {
						icon : 0,
						title : '询问'
					}, function(win) {
						layer.close();
						var sno = btn.data.sno;
						var eno=$('#eno').val();
						$.get('/exam01/estu_delo?sno=' + sno+'&eno='+eno, function(res) {
							layer.alert(res.msg);
						});
						table.reload('order');
					}, function() {
						// 确认框取消按钮事件处理函数
					});

					break;
				}
			});
			
			//查询班级点击事件
			$('#find').on('click',function(){
				var  sname = $('#sname').val();
				$.ajax('/exam01/show_estu?eno='+eno,{
					type:'get',
					asyn:false,
					data:{
						page:1,
						limit:5,
						eno:eno,
						sclass:sname
					},
					success:function(res){
						table.render({
							elem : '#order',
							page : true,
							data:res.data,
							toolbar : '#toolbar',
							limit:5,
							limits:[5,8,10],
							defaultToolbar : [ 'filter', 'print', 'exports' ],
							cols : [ [ {
							  	field : 'id'
							   ,type : 'checkbox'
						      },{
							    field:'sno'
					            ,title:'学号'
								},{
						    	field:'sname'
		                       ,title:'姓名'
							   },{
								field:'sclass'
				                ,title:'学生班级'
								},{
								fixed : 'right',
								title : '操作',
								toolbar : '#bar',
								width : 120
								}] ]
						});
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
