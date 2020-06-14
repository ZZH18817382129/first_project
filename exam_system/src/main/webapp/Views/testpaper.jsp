
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>试卷管理</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	
<div  style="padding-top: 30px">
	<div class="demoTable" style="padding-left: 16px">
				出题老师：  
				<div class="layui-inline">
					<input type="text" class="layui-input" name="tpteacher" id="tpteacher" value=""
						>
				</div>&nbsp&nbsp&nbsp&nbsp&nbsp
				试卷名称：
				<div class="layui-inline">
					<input type="text" class="layui-input" name="tpname" id="tpname" value=""
						>
				</div>
				<button type="button" class="layui-btn"  id="search" name="search">搜索</button>
			</div>
			
		<script type="text/html" id="toolbar">
        
			<a class="layui-btn layui-btn-sm" lay-event="add">新建试卷</a>
			<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delmany">批量删除</a>
		
        </script>
	<div>
		<table id="tt"  lay-filter="tt"></table>
	</div>
	
		<script type="text/html" id="barDemo">

        <a class="layui-btn layui-btn-xs" lay-event="man">编辑试题</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑基本信息 </a>
        <a class="layui-btn layui-btn-xs" lay-event="check">预览</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="disable">禁用</a>
        <a class="layui-btn layui-btn-xs" lay-event="start">激活</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
        
        </script>
     </div>   


	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'layer', 'form', 'table' ,'jquery'], function() {
			var layer = layui.layer, form = layui.form;
			var table = layui.table;
			var $ = layui.jquery;
			table.render({
				elem : '#tt',
				url  :'/exam01/ListTestPaper',
				page : true
				,limit: 10
				,limits: [10]
				,title:'试卷列表'
				,toolbar: '#toolbar'
			    ,defaultToolbar: ['filter', 'exports', 'print']
			,cols : [[ 
				   {
					  field : 'id',
					   type:'checkbox',
				   },{
					field : 'tpno',
					align : 'center',
					title : '试卷编号',
					width : '15%'
				}, {
					field : 'tpname',
					align : 'center',
					title : '试卷名称',
					width : '39%'
				}, {
					field : 'tpteacher',
					align : 'center',
					title : '出题人' ,
					width : '12%'
				},{
					field : 'disable',
					align : 'center',
					title : '禁用状态' ,
					width : '7%'
				},{
					fixed: 'right',
					title:'操作', 
					toolbar: '#barDemo', 
					width: '24%'
				}

				]]
			
			});

			table.on('toolbar(tt)',function(btn){
				var evt = btn.event;
				switch(evt){
				case 'add': 
					
					layer.open({
						type : 2,
						title: '新增试卷',
						offset:'38px',
						closeBtn : 1,
						area :['1300px', '800px'],
						maxmin: true, 
						btn: false,
						content : ['/exam01/BeforeTestPaper', 'yes' ], 
						end: function(){
							table.reload('tt');
						}
					});
					
					break;
				case 'delmany':
					layer.confirm("确认要删除吗？一旦删除数据不可恢复！", {
						icon : 0,
						 
						title : '询问'
					}, function(win) { 
						var checkStatus = table.checkStatus('tt');
						if (!checkStatus.data.length > 0) {
							layer.alert('请至少选取一行要删除的数据');
							layer.close(win);
							return;
						}
						layer.close(win);
						var tpno = '';
						layui.each(checkStatus.data, function(index,
								row) { 
							tpno += "'"+row.tpno+"',";
						});

						tpno = tpno.substring(0, tpno.length - 1);

						$.post('/exam01/DeletesTestPaper', {
							tpno : tpno
						}, function(res) {
							layer.alert(res.msg);
						});
						
						table.reload('tt');
					}, function() {
						table.reload('tt');
					});

					break;
				};
				
			});

			//查询按钮点击事件
			$('#search').on('click',function(){
				var tpteacher = $('#tpteacher').val();
				var tpname = $('#tpname').val();
				
				$.ajax('/exam01/FindTp',{
					type:'get',
					asyn:false,
					data:{
						tpteacher:tpteacher,
						tpname:tpname
						
					},
					success:function(res){
						console.log(res.data);
						table.render({
							elem : '#tt',
							data:res.data
							,page : true
							,title:'试卷列表'
							,toolbar: '#toolbar'
						    ,defaultToolbar: ['filter', 'exports', 'print']
						,cols : [[ 
							   {
								  field : 'id',
								   type:'checkbox',
							   },{
								field : 'tpno',
								align : 'center',
								title : '试卷编号',
								width : '15%'
							}, {
								field : 'tpname',
								align : 'center',
								title : '试卷名称',
								width : '39%'
							}, {
								field : 'tpteacher',
								align : 'center',
								title : '出题人' ,
								width : '12%'
							},{
								field : 'disable',
								align : 'center',
								title : '禁用状态' ,
								width : '7%'
							},{
								fixed: 'right',
								title:'操作', 
								toolbar: '#barDemo', 
								width: '24%'
							}

							]]
						
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},
					error: function(res) {
						layer.alert(res.msg);
					}
				})
			});	
			table.on('tool(tt)',function(btn){
				var evt = btn.event;
				switch (evt){

				case 'man':
					layer.open({
						type : 2,
						title: '编辑试卷试题' ,
						offset:'38px',
						closeBtn : 1,  
						area : [ '793px', '750px' ],
						maxmin: true, 
						btn: false,
						content : ['/exam01/ManageTestPaper?tpno='+btn.data.tpno ,'yes' ], 
						end: function(){
							table.reload('tt');
						}
					});
					break;

				case 'gen':
					layer.confirm('', {
						icon : 0,
						title : '询问'
					}, function(win) {
						layer.close(win);
						var tpno = btn.data.tpno;
						$.get('' + tpno, function(res) {
							layer.alert(res.msg);
						});
						table.reload('tt');
					}, function() {
						
					});

					break;
				
				case 'edit':
					layer.open({
						type : 2,
						title: '修改试卷基本信息' ,
						closeBtn : 1, 
						offset:'38px',
						area : [ '500px', '360px' ],
						maxmin: true, 
						btn: false,
						content : ['/exam01/ModifyTestPaper?tpno=' + btn.data.tpno, 'yes' ], 
						end: function(){
							table.reload('tt');
						}
					});
					break;
				
				case 'check':
					layer.open({
						type : 2,
						title: '预览试卷' ,
						offset:'38px',
						closeBtn : 1,  
						area : [ '800px', '650px' ],
						maxmin: true, 
						btn: false,
						
						content : ['/exam01/LookTestPaper?tpno=' + btn.data.tpno ,'yes' ], 
						end: function(){
							table.reload('tt');
						}
					});
					break;

				case 'del':
					layer.confirm('确定删除此试卷吗？', {
						icon : 0,
						title : '询问'
					}, function(win) {
						layer.close(win);
						var tpno = btn.data.tpno;
						$.get('/exam01/DelTestPaerOne?tpno=' + tpno, function(res) {
							layer.alert(res.msg);
						});
						table.reload('tt');
					}, function() {
						table.reload('tt');
					});
					
					break;

				case 'disable':
					layer.confirm('确定要禁用此试卷吗？', {
						icon : 0,
						title : '询问'
					}, function(win) {
						layer.close(win);
						var tpno = btn.data.tpno;
						$.post('/exam01/DisableTp', {
							tpno : tpno
						}, function(res) {
							layer.alert(res.msg);
						});
						table.reload('tt');
					}, function() {
						
					});

					break;

				case 'start':
					layer.confirm('确定要激活此试卷吗？', {
						icon : 0,
						title : '询问'
					}, function(win) {
						layer.close(win);
						var tpno = btn.data.tpno;
						$.post('/exam01/StartTp', {
							tpno : tpno
						}, function(res) {
							layer.alert(res.msg);
						});
						table.reload('tt');
					}, function() {
						table.reload('tt');
					});

					break;
				}
			});
			
		});
	</script>
</body>
</html>