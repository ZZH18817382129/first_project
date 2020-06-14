<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="/exam01/layui/css/layui.css">
</head>
<body>
	<script type="text/html" id="toolbar">
<div class="layui-btn-container">
<a class="layui-btn layui-btn-sm" lay-event="addRole">新建角色</a>
<a class="layui-btn layui-btn-sm" lay-event="give_roles">更改角色权限信息</a>
<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="dellots">批量删除</a>
</div>
</script>
	<script type="text/html" id="bar">
<div class="layui-btn-container">
<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
<a class="layui-btn layui-btn-xs" lay-event="able">激活</a>
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disable">禁用</a>
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
</script>
	<div>
		<table id="order" lay-filter="order"></table>
	</div>
	<script type="text/javascript" src="/exam01/layui/layui.js"
		charset="utf-8"></script>
	<script>
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.1'
		}).use('role_js');//js页面
	</script>
</body>
</html>