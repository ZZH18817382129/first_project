<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<link type="text/css" rel="stylesheet"
	href="/exam01/layui/css/layui.css">
</head>
<body>
	<div style="">
		<form action="" class="layui-form layui-form-pane"
			style="margin-top: 20px; width: 500px;" lay-filter="user_add"
			method="post">
			<div class="layui-form-item"
				style="text-align: center; margin-left: 80px; margin-right: 80px;">
				<label class="layui-form-label">用户身份</label>
				<div class="layui-input-block">
					<!-- 身份信息 -->
				</div>
			</div>
			<div class="layui-form-item"
				style="text-align: center; margin-left: 80px; margin-right: 80px;">
				<label class="layui-form-label">用户工号</label>
				<div class="layui-input-block">
					<!-- 工号信息 -->
				</div>
			</div>
			<div class="layui-form-item"
				style="text-align: center; margin-left: 80px; margin-right: 80px;">
				<label class="layui-form-label">用户密码</label>
				<div class="layui-input-block">
					<input name="password" type="text" id="password" required
						lay-verify="required" placeholder="请输入密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<br>
			<br>
			<div class="layui-form-item" style="text-align: center;">
				<button class="layui-btn layui-btn-lg layui-btn-normal"
					style="border-radius: 20px; width: 150px; height: 40px; line-height: 40px;">
					确认添加</button>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'layer', 'form', 'jquery', 'transfer', 'table' ],
				function() {
					var layer = layui.layer
					var form = layui.form;
					var $ = layui.$;
					form.on('submit(user_add)', function(data) {
						$.ajax('/exam01/user_upd', {
							type : 'post',
							asyn : false,
							data : {
								identity : $('#identity').val(),
								uno : $('#uno').val(),
								password : $('#password').val()
							},
							success : function(res) {
								layer.msg(res.msg);
								var index = parent.layer
										.getFrameIndex(window.name);
								parent.layer.close(index);
							},
							error : function(res) {
								layer.msg(res.msg);
							}
						})
						return false;
					});
				});
	</script>
</body>
</html>