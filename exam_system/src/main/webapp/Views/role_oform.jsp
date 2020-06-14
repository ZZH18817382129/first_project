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
<title>修改角色信息</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<link rel="stylesheet" href="/exam01/css/role_oform.css">
</head>
<body>
	<div class="layui-container wrapper">
		<form class="layui-form" id="form1" lay-filter="form1">
			<input type="hidden" name="id" value="${Role.id}" id="rid">
			<div class="layui-form-item">
				<label class="layui-form-label">角色名</label>
				<div class="layui-input-block">
					<input class="layui-input" name="ridentity" id="ridentity" 
						value="${Role.ridentity}" lay-verify="required" style="width: 300px;">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit>保存</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
	layui.config({
		base:'/exam01/js/mods/',
		version:'1.0'
	}).use('role_oform');//js页面
			
	</script>
</body>
</html>