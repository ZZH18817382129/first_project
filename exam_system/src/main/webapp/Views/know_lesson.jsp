<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑知识点</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>

	<div class="layui-container" style="margin-top: 16px">
	
	
	
		<form action="" name="form1" method="post" class="layui-form"
			lay-filter="form1" id="from1">
         		
               <div style=" font-size: 30px;color: #5FB878">请选择知识点：</div>       
               
                          <div id="test1" style="padding-top: 16px"></div> 
			
						<div class="layui-form-item"
							style="padding-left: 300px;padding-top:20px">
								<button type="submit" class="layui-btn" lay-submit=""
									lay-filter="demo1">保存</button>
						</div>
						<input type="hidden" id="mno" value="${mno}">
		</form>
	</div>

	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table','transfer' ],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;
					var transfer = layui.transfer;
					var mno =  $('#mno').val();
					//console.log(mno);
					$.get('/exam01/ListLessons?mno='+mno, function(res) {
					 transfer.render({
					      elem: '#test1'
					      ,data:res.data
					      ,title:['未选用的知识点','已选用的知识点 ']
					      ,width: 300 
					      ,height: 460
					      ,value: res.value
					      ,showSearch: true
					      ,id: 'test'
					    });
					});
					
					
					
					form.on('submit(form1)', function(data) {
						console.log(transfer.getData('test'));
						var da = transfer.getData('test');
                        var a = '';
                        for(var i = 0; i < da.length;i++){
                            a += "'"+da[i].value+"',";
                            }
                        
						
						$.post('/exam01/AddLessonTopic', {
							mno : mno,
							data1: a
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