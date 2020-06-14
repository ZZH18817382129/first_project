<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>试题管理</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css"
	type="text/css">
<link rel="stylesheet" href="/exam01/css/ClassManage.css"
	type="text/css">
</head>
<body>
	<div class="layui-form-item" style="margin-top: 5px;margin-left:-60px;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label ClassName">试题编号：</label>
				<div class="layui-input-inline" style="width: 150px">
					<input id="qno" name="qno" lay-verify="" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label ClassName">试题内容：</label>
				<div class="layui-input-inline" style="width: 150px">
					<input id="qstem" name="qstem" lay-verify="" class="layui-input"
						style="margin-top: 8px;">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label ClassName">试题知识点：</label>
				<div class="layui-input-inline" style="width: 150px">
					<input id="toname" name="toname" lay-verify="" class="layui-input"
						style="margin-top: 8px;">
				</div>
			</div>
			<div class="layui-inline">
				<button id="select" type="button" class="layui-btn" lay-sbumit>查询</button>
			</div>
		</div>
	</div>

	<script type="text/html" id="toolbar">
		
      <div class="layui-btn-container">
			 <a class="layui-btn layui-btn-sm" lay-event="add">新增试题</a>
            <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delmany">批量删除</a>
       </div>
		
	</script>

	<script type="text/html" id="bar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</div>
	</script>
	<div class="layui-table">
		<table id="order" lay-filter="order"></table>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table' ],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;

					table.render({
						elem : '#order',
						url : '/exam01/Question_list',
						page : true,
						toolbar : '#toolbar',
						defaultToolbar : [ 'filter', 'print', 'exports' ],
						limit : 10,
						cols : [ [ {
							field : 'id',
							type : 'checkbox'
						}, {
							field : 'qno',
							title : '试题编号  ',
							width : '18%'
						}, {
							field : 'qstem',
							title : '试题内容',
							width : '44.2%'
						}, {
							field : 'toname',
							title : '试题知识点 ',
							width : '20%'
						}, {
							fixed : 'right',
							title : '操作',
							toolbar : '#bar',
							width : '15%'
						} ] ]
					});

					table.on('toolbar(order)', function(btn) {
						var evt = btn.event;
						switch (evt) {
						case 'add':
							layer.open({
								type : 2,
								title : '增加试题 ',
								closeBtn : 1,
								area : [ '900px', '700px' ],
								offset : '70px',
								maxmin : true,
								btn : false,
								content : [ '/exam01/get_topic', 'yes' ],
								end : function() {
									table.reload('order');
								}

							});
							break;
						case 'delmany':
							layer.confirm("确认要删除么？", {
								icon : 2,
								title : '询问'
							}, function(win) {
								var checkStatus = table.checkStatus('order');
								if (!checkStatus.data.length > 0) {
									layer.alert('请至少选中一行数据 ');
									layer.close(win);
									return;
								}
								layer.close(win);
								var id = '';
								var qno = '';
								layui.each(checkStatus.data, function(index,
										row) {
									id += row.id + ",";
									qno += "'" + row.qno + "'" + ",";
								});
								id = id.substring(0, id.length - 1);
								qno = qno.substring(0, qno.length - 1);
								$.get('/exam01/Question_delmore', {
									id : id,
									qno : qno
								}, function(res) {
									layer.alert(res.msg);
								});
								table.reload('order');
							}, function() {
								layer.alert('点了取消 ');
							});
							break;
						}
						;
					});

					table.on('tool(order)', function(btn) {
						var evt = btn.event;
						switch (evt) {
						case 'edit':
							layer.open({
								type : 2,
								title : '修改试题 ',
								closeBtn : 1,
								area : [ '900px', '700px' ],
								offset : '70px',
								maxmin : true,
								btn : false,
								content : [
										'/exam01/Question_upd?id='
												+ btn.data.id, 'yes' ],
								end : function() {
									table.reload('order');
								}

							});
							break;

						case 'del':
							layer.confirm("确认要删除么？", {
								icon : 2,
								title : '询问'
							}, function(win) {
								var checkStatus = table.checkStatus('order');
								if (!checkStatus.data.length > 0) {
									layer.alert('请至少选中一行数据 ');
									layer.close(win);
									return;
								}
								layer.close(win);
								var id = '';
								var qno = '';
								layui.each(checkStatus.data, function(index,
										row) {
									id += row.id + ",";
									qno = row.qno;
								});
								id = id.substring(0, id.length - 1);
								$.get('/exam01/Question_del', {
									id : id,
									qno : qno
								}, function(res) {
									layer.alert(res.msg);
								});
								table.reload('order');
							}, function() {
								layer.alert('点了取消 ');
							});
							break;
						}
						;
					});
					//查询按钮点击事件
					$('#select').on(
							'click',
							function() {
								var qno = $('#qno').val();
								var qstem = $('#qstem').val();
								var toname = $('#toname').val();
								$.ajax('/exam01/question_find', {
									type : 'get',
									asyn : false,
									data : {
										qno : qno,
										qstem : qstem,
										toname : toname
									},
									success : function(res) {
										console.log(res.data);
										table.render({
											elem : '#order',
											data : res.data,
											page : true,
											toolbar : '#toolbar',
											defaultToolbar : [ 'filter',
													'print', 'exports' ],
											cols : [ [ {
												field : 'id',
												type : 'checkbox'
											}, {
												field : 'qno',
												title : '试题编号  ',
												width : '18%'
											}, {
												field : 'qstem',
												title : '试题内容',
												width : '44.2%'
											}, {
												field : 'toname',
												title : '试题知识点 ',
												width : '20%'
											}, {
												fixed : 'right',
												title : '操作',
												toolbar : '#bar',
												width : '15%'
											} ] ]
										});
										var index = parent.layer
												.getFrameIndex(window.name);
										parent.layer.close(index);
									},
									error : function(res) {
										layer.alert(res.msg);
									}
								})
							});
				});
	</script>
</body>
</html>
