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
	<form action="" class="layui-form layui-form-pane" style="margin-top:20px; width: 500px;" lay-filter="user_add" method="post" >
				<div class="layui-form-item" style="text-align: center; margin-left: 80px;margin-right: 80px;">
					<input type="hidden" value="${uno}" id="temp_uno">
					<input type="hidden" value="${rid}" id="rid">
					<div class="layui-form-item">
					    <label class="layui-form-label">用户工号</label>
					    <div class="layui-input-block" >
						   	 <input type="text" value="${uno}" name="uno" id="uno" required  lay-verify="required" placeholder="请输入工/学号" autocomplete="off" class="layui-input">
						</div>
			 		</div>
					<label class="layui-form-label">用户身份</label>
					<div class="layui-input-block" id="identity">
						<select name="identity" lay-verify="" required="required" lay-filter="iden">
						<c:if test="${not empty identity}">
							<option value="">${identity}</option>
						</c:if>
						<!-- 身份列表循环打印 -->
						<c:if test="${empty identity}">
							<option value="">请选择一个身份</option>
						</c:if>
						<c:forEach items="${roles}" var="r">
							<c:if test="${checked_iden eq r.id}">
								<option value="${r.id}" selected="selected">${r.ridentity}</option>
							</c:if>
							<c:if test="${!(checked_iden eq r.id)}">
								<option value="${r.id}">${r.ridentity}</option>
							</c:if>
						</c:forEach>
						</select> 
					</div>
				</div>
				
		 	 <br><br>
		 	 <div class="layui-form-item" style="text-align: center;">
		 	 	<button class="layui-btn layui-btn-lg layui-btn-normal" style="border-radius: 10px;width: 150px;height: 30px;line-height: 30px;">
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
		 	
		 	if($('#temp_uno').val()!=''){
				$('#uno').attr('disabled','disabled');
				form.render();
			}
		 	form.on('select(iden)',function(sel){
		 		$('#rid').val(sel.value);
			 })
		 	form.on('submit(user_add)', function(data) {
			 	var uno=$('#temp_uno').val();
				if(uno!=''){
					var rid = $('#rid').val();
					$.ajax('/exam01/user_upd',{
						type:'get',
						asyn:false,
						data:{
							uno:uno,
							rid:rid
						},
						success:function(res){
							layer.msg(res.msg);
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						},
						error: function(res) {
							layer.msg(res.msg);
						}
					})
				}else{
					var uno = $('#uno').val();
					var rid = $('#rid').val();
					$.ajax('/exam01/user_add',{
						type:'get',
						asyn:false,
						data:{
							uno:uno,
							rid:rid
						},success:function(res){
							layer.msg(res.msg);
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						},error: function(res) {
							layer.msg(res.msg);
						}
					})
		 		}
				return false;
			});
		});
	</script>
</body>
</html>