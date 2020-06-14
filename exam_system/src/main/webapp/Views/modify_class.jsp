<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>增加班级</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<link rel="stylesheet" href="/exam01/css/oform.css">
</head>
<body>
	<div class="layui-container wrapper">
		<form class="layui-form" id="form1" action="" method="post"
			lay-filter="form1">
		<input type="hidden" name="id" value="${classes.id}">
			<div class="layui-form-item">
				<label class="layui-form-label">班级名称:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="cname" id="
						cname" value="${classes.cname}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label">开班时间:</label>
      <div class="layui-input-block">
        <input type="text" class="layui-input" name="ctime" id="test1" value="${classes.ctime}">
      </div>
           </div>
			<div class="layui-form-item">
				<label class="layui-form-label">人数:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="cnum" id="
						cnum" value="${classes.cnum}" lay-verify="required|number">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">班主任:</label>
				<div class="layui-input-block">
					<select name="cadviser" id="cadviser" lay-verify="" lay-search>
					  <option value="${classes.cadviser}">${classes.cadviser}</option>
					  <c:forEach items="${teachers}" var="tea">
						  <option value="${tea.tname}">${tea.tname}</option>
					  </c:forEach>
					</select> 
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">讲师:</label>
				<div class="layui-input-block">
					<select name="cteacher" id="cteacher" lay-verify="" lay-search>
					  <option value="${classes.cteacher}">${classes.cteacher}</option>
					  <c:forEach items="${teachers}" var="tea">
						  <option value="${tea.tname}">${tea.tname}</option>
					  </c:forEach>
					</select> 
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input class="layui-btn" lay-submit type="submit" value="保存">
				</div>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer','laydate' ], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var laydate = layui.laydate;

			laydate.render({
			    elem: '#test1',
			    trigger: 'click'
			   
			  });
			form.on('submit(form1)', function() {

				var data = form.val('form1');
				$.post('/exam01/Classes_upd', {
					id:data.id,
					cname:data.cname,
					ctime: data.ctime,
					cnum: data.cnum,
					cadviser: data.cadviser,
					cteacher:data.cteacher
					
				}, function(res) {
					layer.alert(res.msg);
				});
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);        
				return false;
			});

		});
	</script>
</body>
</html>
