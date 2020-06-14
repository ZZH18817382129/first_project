
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>课程管理</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	
<div  style="padding-top: 30px" >
	<div class="demoTable" style="padding-left: 22px">
				课程名称：  
				<div class="layui-inline">
					<input type="text" class="layui-input" name="mname" id="mname"
						>
				</div>&nbsp&nbsp&nbsp&nbsp&nbsp
				课程模块：
				<div class="layui-inline">
					<input type="text" class="layui-input" name="module" id="module"
						>
				</div>&nbsp&nbsp&nbsp&nbsp&nbsp
				课程级别：
				<div class="layui-inline">
					<input type="text" class="layui-input" name="level" id="level"
						>
				</div>
				课程阶段：
				<div class="layui-inline">
					<input type="text" class="layui-input" name="stem" id="stem"
						>
				</div>
				<button type="button" class="layui-btn "  id="sell" name="sell">搜索</button>
			</div>
		<script type="text/html" id="toolbar">
        
			<a class="layui-btn layui-btn-sm" lay-event="add">新建课程</a>
			<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delmany">删除课程</a>
		
        </script>
	<div >
		<table id="tt"  lay-filter="tt"></table>
	</div>
		<script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="know">知识点 </a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑 </a>
        <a class="layui-btn layui-btn-xs  layui-btn-danger" lay-event="del">删除 </a>
       
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
				url  :'/exam01/ListLesson',
				page : true
				,limit:10
				,title:'课程列表'
				,toolbar: '#toolbar'
				,defaultToolbar: ['filter', 'exports', 'print']
				,cols : [[ 
				   {
					  field : 'id',
					   type:'checkbox'
				   },{
					field : 'mno',
					align : 'center',
					title : '课程编号',
					width : '17%'
				}, {
					field : 'mname',
					align : 'center',
					title : '课程名称',
					width : '30%'
				}, {
					field : 'mlevel',
					align : 'center',
					title : '级别' ,
					width : '10%'
				},{
					field : 'mstem',
					align : 'center',
					title : '阶段' ,
					width : '10%'
				},{
					field : 'mmodule',
					align : 'center',
					title : '模块' ,
					width : '10%'
				},{
					fixed: 'right',
					
					title:'操作', 
					toolbar: '#barDemo', 
					width: '20%'
				}

				]]
			});
			table.on('toolbar(tt)',function(btn){
				var evt = btn.event;
				switch(evt){
				case 'add': 
					
					layer.open({
						type : 2,
						title: '新增课程',
						closeBtn : 1,
						offset:'80px',
						area :['650px', '560px'],
						maxmin: true, 
						btn: false,
						content : ['/exam01/BeforLesson', 'yes' ], 
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
						var mno = '';
						layui.each(checkStatus.data, function(index,
								row) { 
							mno += "'"+row.mno+"'," ;
						});

						mno = mno.substring(0, mno.length - 1);

						$.post('/exam01/DeletesLesson', {
							mno : mno
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
			$('#sell').on('click',function(){
				var mname = $('#mname').val();
				var mno = $('#mno').val();
				var module = $('#module').val();
				var level = $('#level').val();
				var stem = $('#stem').val();
				$.ajax('/exam01/FindLess',{
					type:'get',
					asyn:false,
					data:{
						mname:mname,
						mno:mno,
						mmodule:module,
						mlevel:level,
						mstem:stem
					},
					success:function(res){
						console.log(res.data);
						table.render({
							elem : '#tt',
							data:res.data,
							page : true
							,title:'课程列表'
							,toolbar: '#toolbar'
							,defaultToolbar: ['filter', 'exports', 'print']
						,cols : [[ 
							   {
								  field : 'id',
								   type:'checkbox'
							   },{
								field : 'mno',
								align : 'center',
								title : '课程编号',
								width : '17%'
							}, {
								field : 'mname',
								align : 'center',
								title : '课程名称',
								width : '30%'
							}, {
								field : 'mlevel',
								align : 'center',
								title : '级别' ,
								width : '10%'
							},{
								field : 'mstem',
								align : 'center',
								title : '阶段' ,
								width : '10%'
							},{
								field : 'mmodule',
								align : 'center',
								title : '模块' ,
								width : '10%'
							},{
								fixed: 'right',
								title:'操作', 
								toolbar: '#barDemo', 
								width: '20%'
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

				case 'know':
					layer.open({
						type : 2,
						title: '管理知识点 ' ,
						closeBtn : 1,  
						offset:'80px',
						area : [ '780px', '650px' ],
						maxmin: true, 
						btn: false,
						content : ['/exam01/knowLesson?mno=' + btn.data.mno, 'yes' ], 
						end: function(){
							table.reload('tt');
						}
					});
					break;
					
				case 'edit':
					layer.open({
						type : 2,
						title: '修改课程信息' ,
						closeBtn : 1, 
						offset:'80px', 
						area : [ '600px', '555px' ],
						maxmin: true, 
						btn: false,
						content : ['/exam01/ModifyLesson?id=' + btn.data.id, 'yes' ], 
						end: function(){
							table.reload('tt');
						}
					});
					break;

				case 'del':
					layer.confirm('确定删除当前行数据？', {
						icon : 0,
						title : '询问'
					}, function(win) {
						layer.close(win);
						var mno = btn.data.mno;
						$.get('/exam01/delLesson?mno=' + mno, function(res) {
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