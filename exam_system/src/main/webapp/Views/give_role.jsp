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
	<form action="" class="layui-form layui-form-pane" style="margin-top:20px; width: 500px;" lay-filter="give_role" method="post" >
				<div class="layui-form-item" style="text-align: center; margin-left: 80px;margin-right: 80px;">
				<input type="hidden" value="${unos}" id="unos">
				<input type="hidden" value="" id="rid">
					<label class="layui-form-label">用户身份</label>
					<div class="layui-input-block">
						<select name="identity" lay-verify=""  id="iden" required="required" lay-filter="iden">
							<!-- 身份列表循环打印 -->
							<option value="">请选择一个身份</option>
							<c:forEach items="${roles}" var="r">
									<option value="${r.id}">${r.ridentity}</option>
							</c:forEach>
						</select> 
					</div>
				</div>
		 	 <div class="layui-form-item" style="text-align: center;">
		 	 	<button class="layui-btn layui-btn-lg layui-btn-normal" style="border-radius: 20px;width: 150px;height: 40px;line-height: 40px;">
		 	 		确认授予
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

		 	form.on('select(iden)',function(sel){
				console.log(sel.value);
				$('#rid').val(sel.value);
			 });
		 	
		 	form.on('submit(give_role)', function(data) {
			 	var rid = $('#rid').val();
			 	if(rid==''){
					 	layer.msg('请选择身份!');
					 	return;
				}
			 	var unos = $('#unos').val();
			 	console.log(unos);
			 	$.get('/exam01/give_role?unos='+unos+'&rid='+rid,function(res){
						layer.msg(res.msg);
				});
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return false
			});
		});
	</script>
</body>
</html>