<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<link type="text/css" rel="stylesheet" href="/exam01/layui/css/layui.css">
</head>
<body >
<div style="">
	<form action="/exam01/update_priv" class="layui-form layui-form-pane" style="width: 500px;" lay-filter="loginform" method="post" >
		<div class="layui-input-block" id="identity">
			<select name="identity" id="role"  required="required" lay-filter="role">
				
			</select>
		</div>
		<div id="privs" style="margin-left: 20px;"></div>
	</form>
</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use(['layer', 'form','jquery','transfer','table'], function(){
		  	var layer = layui.layer
		 	var form = layui.form;
		 	var $ = layui.$;
		 	var transfer = layui.transfer;
			//更改角色拥有的权限
			form.on('select(role)', function(data){
				var rid = data.value;
			 	$.get('/exam01/get_roles?id'+rid,function(res){
					transfer.render({
						elem: '#privs',
						data: res.data,
			 	      	//values:res.have_roles,
				 	    id: 'privs'
					});
				 });
			 });
			 //表单提交时得到穿梭框右边的权限赋给角色
		});
	</script>
</body>
</html>