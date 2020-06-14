<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看试卷</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	<div class="layui-container" >
		
   <div>
				<table id="tt" lay-filter="tt"></table>
				<input type="hidden" id="ids" value="${tpno}">
	</div>
	
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

			table.render({
				elem : '#tt',
				url  :'/exam01/ListQuestion?tpno=' + tpno
				
				,btn:  '关闭'
				,title:'试卷列表'
				,cols : [[ 
				   {
					field : 'qno',
					title : '试题编号',
					width : '20%'
				}, {
					field : 'qstem',
					title : '试题',
					width : '80%'
				}
				]]
			});
			
			
		
			

		});
	</script>
</body>
</html>