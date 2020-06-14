<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改课程</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	<div class="layui-container" style="margin-top: 50px">
		<form action="" name="form1" method="post" class="layui-form" lay-filter="form1" id="from1">
       <div style="padding-left: 80px">
			<div class="layui-form-item">
					<label class="layui-form-label">课程名称：</label>
					<div class="layui-input-inline" >
						<input type="text" name="mname" id="mname" value="${major.mname}"  class="layui-input" lay-verify="required"  placeholder="请输入">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">课程级别：</label>
				<div class="layui-input-inline">
					<select name="mlevel" id="mlevel" lay-verify="required">
						<option value="${major.mlevel}">${major.mlevel}</option>
						<option value="初级">初级</option>
						<option value="中级">中级</option>
						<option value="高级">高级</option>

					</select>
				</div>
              </div>

				<div class="layui-form-item">
					<label class="layui-form-label">课程阶段：</label>
					<div class="layui-input-inline">
						<select name="mstem" id="mstem" lay-verify="required">
							<option value="${major.mstem}">${major.mstem}</option>
							<option value="第一阶段">第一阶段</option>
							<option value="第二阶段">第二阶段</option>
							<option value="第三阶段">第三阶段</option>
							<option value="第四阶段">第四阶段</option>
							<option value="第五阶段">第五阶段</option>

						</select>
					</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">课程模块：</label>
						<div class="layui-input-inline">
							<select name="mmodule" id="mmodule" lay-verify="required">
								<option value="${major.mmodule}">${major.mmodule}</option>
								<option value="java基础">java基础</option>
								<option value="web前端">web前端</option>
								<option value="javaweb">javaweb</option>
								<option value="大数据">大数据</option>

							</select>
						</div>

                      </div>

			</div>
       
	<div class="layui-form-item" style="padding-left:120px; margin-top: 100px">
		<div class="layui-input-block">
		
			<button type="submit" class="layui-btn" lay-submit="" lay-filter="form1">立即提交</button>
		</div>
	</div>

	</form>
</div>

	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table' ],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;

					
					form.on('submit(form1)', function(data) {
						$.post('/exam01/UpdateLesson', {
							mno : $('#mno').val(),
							mname : $('#mname').val(),
							mlevel : $('#mlevel').val(),
                            mstem : $('#mstem').val() ,
                            mmodule : $('#mmodule').val(),
                            id : ${major.id}
						}, function(res) {
							layer.alert(res.msg);
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);

					});

				});
	</script>
</body>
</html>