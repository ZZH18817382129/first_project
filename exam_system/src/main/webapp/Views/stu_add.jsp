<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加学生</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
</head>
<body>
	<div class="layui-container wrapper" style="margin-top: 30px">
			<form class="layui-form" id="form1" lay-filter="form1">
			<div style="font-size: 20px; padding: 30px; font-family: 宋体,楷体;">
					学生学号:<c:out value="${number}"/><br>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学生姓名:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="sname" id="sname" style="width:150px" 
						value="${student.sname}" lay-verify="required">
			    <div style="color:red">填写后不可更改请注意！！</div>
				</div> 
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系电话:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="sphone" id="sphone" style="width:150px" 
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
				<label class="layui-form-label">性别:</label>
				<div class="layui-input-block" style="width: 150px">
					<select name="interest" id="sgender">
						<option value=""></option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">毕业院校:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="sschool" id="sschool" style="width:150px"
						value="${student.sschool}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">大学专业:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="smajor" id="smajor" style="width:150px" 
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
		layui.use([ 'element', 'form', 'jquery', 'layer' ], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;

			form.on('submit(form1)', function(data) {
				$.ajax('/exam01/stu_add',{
					type:'post',
					asyn:false,
					data:{
						sname:form.val('form1').sname,
						sphone:form.val('form1').sphone,
						sclass:$('#sclass').val(),
						sgender:$('#sgender').val(),
						sno:${number},
						sschool:form.val('form1').sschool,
						smajor:form.val('form1').smajor,
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
