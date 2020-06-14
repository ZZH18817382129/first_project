<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改试卷</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	<div class="layui-container" style="margin-top: 50px">
		<form action="" method="post" class="layui-form"
			id="form1" lay-filter="form1">


<hr>    

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">试卷名称：</label>
					<div class="layui-input-inline" style="width: 300px">
						<input type="text" name="pname" id="pname" value="${tp.tpname}" class="layui-input">
					</div>
				</div>
                       
				<div class="layui-inline">
					<label class="layui-form-label">出卷人：</label>
					<div class="layui-input-inline" style="width: 300px">
						<input type="text" name="pman" id="pman" value="${tp.tpteacher}" class="layui-input">
					</div>
				</div>
			</div>
			<input type="hidden"  id="ids" value="${tpno1}">
           <!--   <div style="width: 1200px">
				<table id="tt" lay-filter="tt"></table>
			</div>
			-->
			
        
	
         

			<div class="layui-form-item" style="padding-left: 90px">
				<div class="layui-input-block">
					<input class="layui-btn" type="submit" value="保存">
				</div>
			</div>


	</form>
	</div>


	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer','table' ], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var table = layui.table;
			var tpno = $('#ids').val();
			
			console.log(tpno);
			table.render({
				elem : '#tt',
				height : 500,
				url  :'/exam01/ListQuestion?tpno=' + tpno,
				page : true
				,title:'试卷列表'
				,cols : [[ 
				   {
					  field : 'id',
					   width : 45
					   
				   },{
					field : 'qno',
					title : '试题编号',
					width : 130
				}, {
					field : 'qstem',
					title : '试题',
					width : 830
				},{
					fixed: 'right',
					title:'操作', 
					toolbar: '#barDemo', 
					width:190
				}

				]]
			});
			
			
			
			form.on('submit(form1)', function(data) {
				$.post('/exam01/UpdateTestPaper', {
					tpno : tpno,
					pname : $('#pname').val(),
					pman : $('#pman').val()
                    
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