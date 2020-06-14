<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新建知识点</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<link rel="stylesheet" href="/exam01/css/addTopic.css">
</head>
<body>
	<!-- 输入要添加的数据 -->
	<div class="layui-container wrapper">
		<form class="layui-form" id="form2" lay-filter="form2">
			<input type="hidden" name="id" value="${Topic.id }">
			<div class="layui-form-item">
				<label class="layui-form-label">知识点名称</label>
				<div class="layui-input-block">
					<input class="layui-input" name="toname" id="toname"
						value="${toname}">
				</div>
			</div>

			<!-- <div class="layui-form-item">
				<label class="layui-form-label">知识点编号</label>
				<div class="layui-input-block">
				<input type="hidden" name="tono" id="tono" value="null">	
				</div>
			</div>    -->
			<div class="layui-form-item">
				<label class="layui-form-label">级别</label>
				<div class="layui-input-block">
					<select name="tolevel" id="tolevel">
						<option value=""></option>
						<option value="一级">一级</option>
						<option value="二级">二级</option>
						<option value="三级">三级</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">阶段</label>
				<div class="layui-input-block">
					<select name="tostage" id="tostage">
						<option value=""></option>
						<option value="第一阶段">第一阶段</option>
						<option value="第二阶段">第二阶段</option>
						<option value="第三阶段">第三阶段</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">模块</label>
				<div class="layui-input-block">
					<select name="tomodule" id="tomodule" lay-search>
						<option value=""></option>
						<c:forEach items="${tomodule}" var="mo">
							<option value="${mo}">${mo}</option>
						</c:forEach>
					</select>
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
		}).use('addtopic_oform');
	</script>
</body>
</html>