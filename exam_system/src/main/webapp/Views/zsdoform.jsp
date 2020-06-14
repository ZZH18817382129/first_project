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
	content="width=device-width,initial-scale=1,maximum-scale=1">
<title>知识点编辑弹窗</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<link rel="stylesheet" href="/exam01/css/zsdoform.css">
</head>
<body>
	<!-- 编辑知识点时的弹窗，显示数据库已有知识点并进行修改，修改后保存 -->
	<div class="layui-container wrapper">
		<form class="layui-form" id="form1" lay-filter="form1">
			<input type="hidden" name="id" value="${Topic.id}">
			<div class="layui-form-item">
				<label class="layui-form-label">知识点名称</label>
				<div class="layui-input-block">
					<input class="layui-input" name="toname" id="toname"
						value="${Topic.toname }"">
				</div>
			</div>
			<!--  <div class="layui-form-item">
				<label class="layui-form-label">知识点编号</label>
				<div class="layui-input-block">
					<input class="layui-input" name="tono" id="tono"
						value="${Topic.tono}"">
				</div>
			</div>  -->
			<div class="layui-form-item">
				<label class="layui-form-label">级别</label>
				<div class="layui-input-block">
					<input class="layui-input" name="tolevel" id="tolevel"
						value="${Topic.tolevel }"">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">阶段</label>
				<div class="layui-input-block">
					<input class="layui-input" name="tostage" id="tostage"
						value="${Topic.tostage}"">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">模块</label>
				<div class="layui-input-block">
					<input class="layui-input" name="tomodule" id="tomodule"
						value="${Topic.tomodule }"">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit>保存</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.0'
		}).use('topic_oform');
	</script>
</body>
</html>