<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>更改考试</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
</head>
<body>
     <div style="margin-top: 35px">
		<form class="layui-form" id="form1" lay-filter="form1">
			<input type="hidden" name="id" value="${student.id}">
			<div class="layui-form-item">
				<label class="layui-form-label">联系电话:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="sphone" id="sphone"  style="width:170px"
						value="${student.sphone}" lay-verify="required|phone">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学生班级:</label>
				<div class="layui-input-block" style="width: 150px">
					<select name="sclass" id="sclass" lay-search>
						<c:forEach items="${classes}" var="cla">
						 <option value="${cla.cname}">${cla.cname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">毕业院校:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="sschool" id="sschool" style="width:170px" 
						value="${student.sschool}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">大学专业:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="smajor" id="smajor" style="width:170px" 
						value="${student.smajor}" lay-verify="required">
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
		layui.use([ 'element', 'form', 'jquery', 'layer' ,'laydate'], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;

			form.on('submit(form1)', function(data) {
				$.ajax('/exam01/stu_upd',{
					type:'post',
					asyn:false,
					data:{
						sphone:form.val('form1').sphone,
						sclass:$('#sclass').val(),
						sschool:form.val('form1').sschool,
						smajor:form.val('form1').smajor,
						id:form.val('form1').id
					},
					success:function(res){
						layer.alert(res.msg);
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},
					error: function(res) {
						layer.alert(res.msg);
					}
				})
				return false;
			});

		});
	</script>
</body>
</html>
