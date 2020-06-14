<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新建知识点</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<link rel="stylesheet" href="/exam01/css/addjs.css">
</head>
<body>
	<div class="layui-container wrapper">
		<form class="layui-form" id="form3" lay-filter="form3">
			<input type="hidden" name="id" value="${role.id }">
			<div class="layui-form-item">
				<label class="layui-form-label">角色名</label>
				<div class="layui-input-block">
					<input class="layui-input" name="ridentity" id="ridentity"
						value="${ridentity}">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit>添加</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.0'
		}).use('addjs');
	</script>
</body>
</html>