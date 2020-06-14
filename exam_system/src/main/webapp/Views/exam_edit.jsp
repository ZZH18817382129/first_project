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
<div class="layui-container wrapper">
		<form class="layui-form" id="form1" lay-filter="form1" style="margin-top: 25px">
			<input type="hidden" name="id" value="${exam.id}">
			<div class="layui-form-item">
				<label class="layui-form-label">考试名称:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="ename" id="ename" style="width: 180px" 
						value="${exam.ename}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">开始时间:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="stime" id="stime" style="width: 180px"
						value="${exam.estarttime}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">结束时间:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="etime" id="etime" style="width: 180px"
						value="${exam.eendtime}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">监考老师:</label>
				<div class="layui-input-block" style="width: 150px">
					<select name="tno" id="tno" lay-search>
						<c:forEach items="${teacher}" var="tea">
						 <option value="${tea.tno}">${tea.tno.concat(":").concat(tea.tname)}</option>
						</c:forEach>
					</select>
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
			var laydate = layui.laydate;

			laydate.render({
			    elem: '#stime'
			    ,theme: 'grid'
			  });

			laydate.render({
			    elem: '#etime'
			    ,theme: 'grid'
			  });
			  

			form.on('submit(form1)', function(data) {
				$.ajax('/exam01/exam_upd',{
					type:'post',
					asyn:false,
					data:{
						ename:form.val('form1').ename,
						estarttime:form.val('form1').stime,
						eendtimes:form.val('form1').etime,
						etno:$('#tno').val,
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
