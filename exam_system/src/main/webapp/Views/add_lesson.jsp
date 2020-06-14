<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>娣诲姞璇剧▼</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	<div class="layui-container" style="margin-top: 50px">
		<form action="" name="form1" method="post" class="layui-form"
			lay-filter="form1" id="from1">
         <div style="padding-left: 100px">
			<div style="font-size: 20px; padding-bottom: 26px">
					璇剧▼缂栧彿锛�<c:out value="${number}"/><br>
					
				</div>

			<div class="layui-form-item">
				<label class="layui-form-label">璇剧▼鍚嶇О锛�</label>
				<div class="layui-input-inline">
					<input type="text" name="mname" id="mname" class="layui-input"
						lay-verify="required" placeholder="璇疯緭鍏�">
				</div>
			</div>


			<div class="layui-form-item">
				<label class="layui-form-label">璇剧▼绾у埆锛�</label>
				<div class="layui-input-inline">
					<select name="mlevel" id="mlevel" lay-verify="required">
						<option value=""></option>
						<option value="鍒濈骇">鍒濈骇</option>
						<option value="涓骇">涓骇</option>
						<option value="楂樼骇">楂樼骇</option>
					
                        
					</select>
				</div>
					<!--  <button type="button"  	class="layui-btn layui-btn-primary" id="addmlevel">
						<i class="layui-icon">&#xe654;</i></button>-->
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">璇剧▼闃舵锛�</label>
					<div class="layui-input-inline">
						<select name="mstem" id="mstem" lay-verify="required">
							<option value=""></option>
							<option value="绗竴闃舵">绗竴闃舵</option>
							<option value="绗簩闃舵">绗簩闃舵</option>
							<option value="绗笁闃舵">绗笁闃舵</option>
							<option value="绗洓闃舵">绗洓闃舵</option>
							<option value="绗簲闃舵">绗簲闃舵</option>
							<option value="绗叚闃舵">绗叚闃舵</option>

						</select>
					</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">璇剧▼妯″潡锛�</label>
						<div class="layui-input-inline">
							<select name="mmodule" id="mmodule" lay-verify="required" >
								<option value=""></option>
								<option value="java鍩虹">java鍩虹</option>
								<option value="web鍓嶇">web鍓嶇</option>
								<option value="javaweb">javaweb</option>
								<option value="澶ф暟鎹�">澶ф暟鎹�</option>

							</select>
						</div>
						
							</div>
							
                          </div>
               <input type="hidden" id="mno" value="${number}">           
               <div id="showids"></div>
               
        
               
						<div class="layui-form-item"
							style="padding-left: 160px;padding-top: 38px">
							<div class="layui-input-block">

								<button type="submit" class="layui-btn" lay-submit=""
									lay-filter="demo1">绔嬪嵆鎻愪氦</button>
							</div>
						</div>
						<
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

					table.render({
						elem : '#tt',
						height : 500,
						url  :'/exam01/ListTopic',
						page : true
						,title:'鐭ヨ瘑鐐瑰垪琛�'
						,toolbar: '#toolbar'
						,defaultToolbar: ['filter', 'exports', 'print']
						,cols : [[ 
						   {
							  field : 'id',
							   width :50,
							   type:'checkbox',
						   },{
							field : 'tono',
							title : '璇剧▼缂栧彿',
							width : 120
						}, {
							field : 'toname',
							title : '鐭ヨ瘑鐐瑰悕绉�',
							width : 278
						}

						]]
					});

					

					
					form.on('submit(form1)', function(data) {
						$.post('/exam01/AddLesson', {
							mno : $('#mno').val(),
							mname : $('#mname').val(),
							mlevel : $('#mlevel').val(),
							mstem : $('#mstem').val(),
							mmodule : $('#mmodule').val()
                           
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