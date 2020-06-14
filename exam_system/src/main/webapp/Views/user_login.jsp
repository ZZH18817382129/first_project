<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<link type="text/css" rel="stylesheet"
	href="../layui/css/layui.css">
<style type="text/css">
body {
	background-image: url("../images/bg.jpg");
	font-size: 18px;
}
</style>
</head>
<body>
	<div class="layui-container" style="margin-top: 100px;">
		<form action="/user_login" class="layui-form layui-form-pane"
			style="text-align: center;" lay-filter="loginform" method="post">
			<input type="hidden" value="${error}" id="error">
				<div class="layui-form-item" >
					<img alt="logo" src="/hist_exam/images/logo.png"
						style="width: 400px; height: 300px;">
				</div>
				<div class="layui-form-item" style="margin-left: 33%">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input name="uno" style="width: 280px;" type="text" required lay-verify="required"
								placeholder="请输入用户名" autocomplete="off" class="layui-input">
						</div>
				</div>
				<div class="layui-form-item" style="margin-left: 33%">
					<label class="layui-form-label">密 码</label>
					<div class="layui-input-block">
						<input name="password" type="password" required
							lay-verify="required" placeholder="请输入密码" autocomplete="off" style="width: 280px;"
							class="layui-input">
					</div>
				</div>
				<br>
				<br>
				<div class="layui-form-item" style="text-align: center;">
					<button class="layui-btn layui-btn-lg layui-btn-normal"
						style="border-radius: 5px; width: 200px;background-color: #257CD6;">
						登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
				</div>
		</form>
	</div>
	<script src="/hist_exam/layui/layui.js"></script>
	<script>
		layui.use([ 'layer', 'form', 'jquery', 'table' ], function() {
			var layer = layui.layer
			var form = layui.form;
			var $ = layui.$;

			var error = $("#error").val();
			if (error != '') {
				layer.msg(error);
			}
		});
	</script>
</body>
</html>