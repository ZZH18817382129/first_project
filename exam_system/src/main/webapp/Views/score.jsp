<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link type="text/css" rel="stylesheet"
	href="/exam01/layui/css/layui.css">
<link type="text/css" rel="stylesheet" href="/exam01/css/score.css">
<link type="text/css" rel="stylesheet" href="/exam01/css/cjoform.css">
<title>成绩管理</title>
<style type="text/css">
.test {
	display: inline-block;
}
</style>
</head>
<body>
	<!-- 成绩的条件查询的设计 -->
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="demoTable">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生姓名：
		<div class="test">
			<input name="sname" class="layui-input" id="sname" autocomplete="off"
				style="width: 80px">
		</div>
		&nbsp;&nbsp;&nbsp;学生学号：
		<div class="test">
			<input name="sno" class="layui-input" id="sno" autocomplete="off"
				style="width: 80px">
		</div>
		&nbsp;&nbsp;&nbsp;<span class="span-interval"></span>班级名称：
		<div class="test">
			<input name="cname" class="layui-input" id="cname" autocomplete="off"
				style="width: 80px">
		</div>
		&nbsp;&nbsp;&nbsp;<span class="span-interval"></span>考试名称：
		<div class="test">
			<input name="tpname" class="layui-input" id="tpname"
				autocomplete="off" style="width: 80px">
		</div>
		&nbsp;&nbsp;&nbsp;<span class="span-interval"></span>成绩：
		<div class="test">
			<input name="esgrade" class="layui-input" id="esgrade"
				autocomplete="off" style="width: 80px">
		</div>
		<div class="test" id="chaxun1" style="margin-left: 50px;">
			<a class="layui-btn" lay-event="chaxun1" style="margin-bottom: 2px;">查询</a>
		</div>
	</div>
	<div class="test" id="xs">
		<a class="layui-btn" lay-event="xs">学生成绩详情</a>
	</div>
	<div class="test" id="jl">
		<a class="layui-btn" lay-event="jl">成绩记录</a>
	</div>
	</script>
	<script type="text/html" id="bar">
<div class="layui-btn-container">
<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</div>
</script>
	<div>
		<table id="score" lay-filter="score"></table>
	</div>
	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script type="text/javascript">
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.0'
		}).use('score_test');
	</script>
	<div class="layui-footer">
		<p class="copyright">&copy; 2019 版权声明</p>
	</div>
</body>
</html>