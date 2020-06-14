<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加考试</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
</head>
<body>
	<div class="layui-container wrapper" style="margin-top: 30px">
		<form class="layui-form" id="form1" lay-filter="form1">
			<input type="hidden" id="eno" value="${eno}">
           
           <div class="layui-form-item">
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
			</div>
	<div id ="check">
		<table id="classes" lay-filter="classes"></table>
	</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit id="select">保存</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer' , 'table'], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var table = layui.table;
			
			var eno=$('#eno').val();

			table.render({
				elem : '#classes',
				url : '/exam01/estu_list?eno='+eno,
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
						}] ]
			});
			

             //监听复选框选择
            table.on('checkbox(classes)', function(obj){
            	var eno = $('#eno').val();
               	var sno = obj.data.sno;
               	var LAY_CHECKED = obj.checked;
				var item_table = table.cache['classes'];
                console.log(item_table[0].LAY_CHECKED);
	            if(obj.type=="all"){
					var snonum = '';
					console.log(item_table);
					for (var index in item_table) {
					     snonum += item_table[index].sno+",";
					}
					snonum = snonum.substring(0,snonum.length-1);
					console.log(snonum);
					$.get('/exam01/addExamStus',{//批量添加
						eno:eno,
	                    snos:snonum,
						LAY_CHECKED:item_table[0].LAY_CHECKED
					},function(res){
						layer.msg(res.msg);
					});
				}else{
					$.post('/exam01/addExamStu',{
                  		eno : eno,
                       	sno:obj.data.sno,
                  		LAY_CHECKED : LAY_CHECKED
						},function(res){
						layer.msg(res.msg);
                       });
					}
				var checkStatus = table.checkStatus('classes');
				counter = checkStatus.data.length 
            });
			
			//查询班级点击事件
			$('#find').on('click',function(){
				var  sname = $('#sname').val();
				$.ajax('/exam01/estu_list',{
					type:'get',
					asyn:false,
					data:{
						page:1,
						limit:5,
						eno:eno,
						sclass:sname
					},
					success:function(res){
						console.log(res.data);
						table.render({
							elem : '#classes',
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
								}] ]
						});
					},
					error: function(res) {
						layer.alert(res.msg);
					}
				})
			});	
			
			form.on('submit(form1)', function(res) {
				layer.confirm('添加学生成功', {icon: 1, title:'提示'}, 
						function(index){
					  layer.close(index);
					});
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
						 
			});
		});
	</script>
</body>
</html>
