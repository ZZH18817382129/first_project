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
<title>知识点页面</title>
<link type="text/css" rel="stylesheet"
	href="/exam01/layui/css/layui.css">
<link type="text/css" rel="stylesheet" href="/exam01/css/zsd.css">
<style type="text/css">
	.test{
		display: inline-block;
	}
</style>
</head>
<body>
	<!-- 知识点编号的查询设计 -->
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="demoTable">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知识点名称：
		<div class="layui-inline">
			<input name="toname" class="layui-input" id="toname"
				style="width: 80px" autocomplete="off">
		</div>
		<input type="hidden" name="tono" id="tono">
		<!--  &nbsp;&nbsp;<span class="span-interval"></span>知识点编号：
		<div class="layui-inline">
			<input name="tono" class="layui-input" id="tono" autocomplete="off"
				style="width: 100px">
		</div>   -->
		&nbsp;&nbsp;<span class="span-interval"></span>级别：
		<div class="test">
			<input name="tolevel" class="layui-input" id="tolevel"
				style="width: 80px" autocomplete="off">
		</div>
		&nbsp;&nbsp;<span class="span-interval"></span>阶段：
		<div class="test">
			<input name="tostage" class="layui-input" id="tostage"
				autocomplete="off" style="width: 80px">
		</div>
		&nbsp;&nbsp;<span class="span-interval"></span>模块：
		<div class="test">
			<input name="tomodule" class="layui-input" id="tomodule"
				autocomplete="off" style="width: 80px">
		</div>
		<div class="test" id="cx" style="margin-left: 50px;">
			<a class="layui-btn" id="chaxun" lay-event="chaxun" style="margin-bottom: 2px;">查询</a>
		</div>
	</div>
	<!-- 新建知识点和批量删除知识点功能 -->
	<script type="text/html" id="toolbar">
<div class="layui-btn-container">
<a class="layui-btn layui-btn-sm" lay-event="addo">新建知识点</a>
<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="deltopic">批量删除</a>
</div>
</script>
	<!-- 对一个知识点的编辑和删除 -->
	<script type="text/html" id="bar">
<div class="layui-btn-container">
<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
</script>
	<div>
		<table id="topic" lay-filter="topic"></table>
		<!-- lay-filter事件过滤器，可以看作ID选择器 -->
	</div>

	<script type="text/javascript" src="/exam01/layui/layui.js"
		charset="utf-8"></script>

	<script type="text/javascript">
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.0'
		}).use('zsd');
	</script>

	<div class="layui-footer">
		<p class="copyright">&copy; 2019 版权声明</p>
	</div>

</body>
</html>