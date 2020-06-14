<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<link rel="stylesheet" href="/exam01/css/updcj.css">
<title>修改成绩弹窗</title>
</head>
<body>
	<!-- 从数据库获取数据 -->
	<div class="layui-container wrapper">
		<form class="layui-form" id="form4" lay-filter="form4">
			<input type="hidden" name="id">

			<!--  <div class="layui-form-item">
				<label class="layui-form-label">学生姓名</label>
				<div class="layui-input-block">
					<input class="layui-input" name="sname" id="sname"
						value="${Score.sname }">
				</div>
			</div>-->
			<div class="layui-form-item">
				<label class="layui-form-label">学生学号</label>
				<div class="layui-input-block">
					<input class="layui-input" disabled="disabled" name="sno" id="sno"
						value="${Score.sno }">
				</div>
			</div>
			<!-- <div class="layui-form-item">
				<label class="layui-form-label">班级名称</label>
				<div class="layui-input-block">
					<input class="layui-input" name="cname" id="cname"
						value="${Score.cname }">
				</div>
			</div> -->
			<div class="layui-form-item">
				<label class="layui-form-label">试卷名称</label>
				<div class="layui-input-block">
					<input class="layui-input" disabled="disabled" name="tpname"
						id="tpname" value="${Score.tpname }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">成绩</label>
				<div class="layui-input-block">
					<input class="layui-input" name="esgrade" id="esgrade"
						value="${Score.esgrade }">
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
		}).use('upd_oform');
	</script>
</body>
</html>