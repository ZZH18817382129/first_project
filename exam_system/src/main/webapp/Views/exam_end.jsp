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
	<script type="text/htmljavascript" id="bars_one">
		<a class="layui-btn layui-btn-sm" lay-event="start_exam">进入考试</a>
	</script>
	<div style="margin-left: 10px; margin-top: 10px;">
	<input type="hidden" value="${user.uno}" id="sno">
		<table id="exam_msg" lay-filter="exam_msg"></table>
	</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use(['table','jquery'], function() {
			var table = layui.table;
			$ = layui.$;
			
		});
	</script>
</body>
</html>