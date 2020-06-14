<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>考试首页</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css" media="all">
</head>
<body>
<form action="/exam01/start_exam" name="eno" method="post">
	<input type="hidden" value="" name="eno" id="eno">
</form>
	<div style="margin-left: 10px; margin-top: 10px;">
	<input type="hidden" value="${user.uno}" id="sno">
	<input type="hidden" value="${user.uname}" id="sname">
	<div style="font-size: 20px;" id="msg">
	</div>
		<table id="exam_msg" lay-filter="exam_msg"></table>
	</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use(['table','jquery'], function() {
			var table = layui.table;
			var $ = layui.$;
			
			var sno = $('#sno').val();
			var sname=$('#sname').val();
			var msg=sname+'同学,系统已为你规划了以下考试.请选择要进行的考试,点击"进入考试"开始答题.';
			$('#msg').html(msg);
			table.render({
				elem : '#exam_msg',
				url : '/exam01/exam_lists?sno='+sno,
				page : true,
				limit:10,
				limits:[5,10,15],
				cols : [ [ {
					field : 'eno',
					title : '考试编号',
					sort : true,
					width : '15%',
					align : 'center'
				}, {
					field : 'ename',
					title : '考试名称',
					width : '15%',
					align : 'center'
				}, {
					field : 'etype',
					title : '考试类型',
					width : '13%',
					align : 'center'
				}, {
					field : 'estarttime',
					title : '考试开始时间',
					width : '15%',
					align : 'center'
				}, {
					field : 'eendtime',
					title : '考试结束时间',
					width : '15%',
					align : 'center'
				}, {
					field : 'estatus',
					title : '考试状态',
					width : '12%',
					align : 'center'
				},{
					field : 'estatus',
					title : '操作',
					width : '15%',
					minWidth:150,
					align : 'center',
					templet: function (d) {
				    if(d.estatus == '已完成'){
						return '<a class="layui-btn layui-btn-sm" style="background-color:#B2B2B2;">已经完成</a>';
					}else{
						return '<a class="layui-btn layui-btn-sm" lay-event="start_exam">进入考试</a>';
					}
						}
				} ] ]
			});
			
			/*进入考试按钮*/
			table.on('tool(exam_msg)', function(obj) {
				if (obj.event == 'start_exam') {
					var eno = obj.data.eno;
					$('#eno').val(eno);
					//把要进入的考试状态设为已考并设置当前时间为考生开始考试时间
					var date=new Date();//获取当前时间
		        	var year=date.getFullYear();
		        	var month=date.getMonth()+1;
		        	var day=date.getDate();
		            var hour=date.getHours();
		            var minute=date.getMinutes();
		            var second=date.getSeconds();
		            //在1~9的前补零;
		            if (hour<10) {
		            	hour='0'+hour;
		            }
		            if (minute<10) {
		            	minute='0'+minute;
		            }
		            if (second<10) {
		            	second='0'+second;
		            }
		            var now_time=year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
		            var end_time = year+'-'+month+'-'+day+' '+(parseInt(hour)+1)+':'+minute+':'+second;
					var sno = $('#sno').val();
					$.get('/exam01/set_exam_start',{
						eno:eno,
						sno:sno,
						now_time:now_time,
						end_time:end_time
					});
					$("form[name='eno']").submit();
				}
			});
			
		});
	</script>
</body>
</html>