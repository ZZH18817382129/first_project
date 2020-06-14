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
	<form action="" class="layui-form layui-form-pane" style="width: 500px;" lay-filter="give_privs" method="post" >
	<div class="layui-form-item">
				<label class="layui-form-label">角色名</label>
				<div class="layui-input-block">
					<select name="identity" id="role"  required="required" lay-filter="role">
						<option value="">请选择一个身份</option>
						<c:forEach items="${roles}" var="r">
							<option value="${r.id}">${r.ridentity}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		<div id="privs" style="margin-left: 10px;"></div>
		<div class="layui-form-item" style="text-align: center;">
		 	 	<button class="layui-btn layui-btn-lg layui-btn-normal" style="border-radius: 20px;width: 150px;height: 40px;line-height: 40px;">
		 	 		保存
		 	 	</button>
		 	 </div>
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
			transfer.render({
					elem: '#privs',
					title: ['未拥有权限', '已拥有权限'],
					data: [],
				 	id: 'privs',
				 	showSearch: true
			});
			form.on('select(role)', function(data){
				//身份的id
				rid = data.value;
			 	$.get('/exam01/get_privs?rid='+rid,function(res){
			 		transfer.reload('privs', {
				 		data:res.data,
			 	        value: res.values
			 	      });
				 });
			 });
			 	form.on('submit(give_privs)', function(data) {
					//表单提交时得到穿梭框右边的权限赋给角色
					var pids = transfer.getData('privs');
					var temp = '';
					for(var i = 0;i<pids.length;i++){
						console.log(pids[i].value);
						temp+=pids[i].value+',';
					}
					temp= temp.substring(0,temp.length-1);
					$.get('/exam01/give_privs?rid='+rid+'&pids='+temp,function(res){
						layer.msg(res.msg);
					});
					return false;
				});
			 
		});
	</script>
</body>
</html>