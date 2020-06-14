<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>考试首页</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css" media="all">
<style type="text/css">
	body{
		font-size: 16px;
	}
	.imp{
		font-size: 24px;
		color: #CA0C61;
	}
</style>
</head>
<body>
<input type="hidden" id="tpno" value="${exam.tpno}">
<input type="hidden" id="eno" value="${exam.eno}">
<input type="hidden" id="grades" value="${grades}">
	<div style="margin-left: 10px; margin-top: 10px;">
		<span class="imp">${user.uname}</span>同学,<!-- 后续改为登录时传递姓名 -->本次考试为:<span class="imp">${exam.ename}</span>.以下是本次考试要求。若开始答题，则
表示你承诺遵守本次考试规则。
<br><br><br><br><br><br>考试要求：<br>
1，考试期间不使用任何外部软件，工具等测试考试结果。<br>2，考试期间不与他人讨论考试内容及答案。<br><br><br><br><br><br>
		<div class="layui-form-item" style="text-align: left;">
			 	 	<button id="start_exam" class="layui-btn layui-btn-lg layui-btn-normal" style="border-radius: 5px;width: 200px;">
			 	 		开始答题
			 	 	</button>
		</div>
	</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use(['jquery'], function() {
			$ = layui.$;
			$("#start_exam").click(function(res){
				var tpno = $('#tpno').val();
				var eno = $('#eno').val();
				var grades = $('#grades').val();
				$.get('/exam01/get_items?tpno='+tpno,function(res){
					window.location.href='/exam01/Views/exam_start_old.jsp';
				});	   			 
		  	});
		});
	</script>
</body>
</html>