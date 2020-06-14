<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>班级管理</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css"
	type="text/css">
<link rel="stylesheet" href="/exam01/css/ClassManage.css"
	type="text/css">
</head>
<body>
	<div class="layui-form-item"
		style="margin-top: 8px; margin-left: -75px;">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label ClassName">班级名：</label>
				<div class="layui-input-inline" style="width: 150px">
					<input type="tel" id="cname" name="cname" lay-verify=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label ClassName">班主任：</label>
				<div class="layui-input-inline" style="width: 150px">
					<input type="text" id="cadviser" name="cadviser" lay-verify=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label ClassName">讲师：</label>
				<div class="layui-input-inline" style="width: 150px">
					<input type="text" id="cteacher" name="cteacher" lay-verify=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<button id="select" type="button" class="layui-btn" lay-sbumit>查询</button>
			</div>
		</div>
	</div>
	<script type="text/html" id="toolbar">
			 <a class="layui-btn layui-btn-sm" lay-event="add">新增班级</a>
            <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delmany">批量删除</a>
	</script>

	<script type="text/html" id="bar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
						url : '/exam01/Classes_list',
						page : true,
						toolbar : '#toolbar',
						defaultToolbar : [ 'filter', 'print', 'exports' ],
						limit : 10,
						cols : [ [ {
							field : 'id',
							type : 'checkbox'
						}, {
							field : 'cno',
							title : '班级编号 ',
							width : '15%'
						}, {
							field : 'cname',
							title : '班级名称 ',
							width : '20%'
						}, {
							field : 'ctime',
							title : '开班时间 ',
							width : '15%'
						}, {
							field : 'cnum',
							title : '人数',
							width : '12.3%'
						}, {
							field : 'cadviser',
							title : '班主任',
							width : '10%'
						}, {
							field : 'cteacher',
							title : '讲师 ',
							width : '10%'
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
								title : '增加班级',
								closeBtn : 1,
								area : [ '600px', '400px' ],
								offset : '150px',
								maxmin : true,
								btn : false,
								content : [ '/exam01/get_teachers', 'no' ],
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
								layui.each(checkStatus.data, function(index,
										row) {
									id += row.id + ",";
								});
								id = id.substring(0, id.length - 1);
								$.get('/exam01/Classes_delmore', {
									id : id
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
							layer
									.open({
										type : 2,
										title : '修改班级信息',
										closeBtn : 1,
										area : [ '600px', '400px' ],
										offset : '150px',
										maxmin : true,
										btn : false,
										content : [
												'/exam01/Classes_upd?id='
														+ btn.data.id, 'no' ],
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
								layui.each(checkStatus.data, function(index,
										row) {
									id += row.id + ",";
								});
								id = id.substring(0, id.length - 1);
								$.get('/exam01/Classes_del', {
									id : id
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
								var cname = $('#cname').val();
								var cadviser = $('#cadviser').val();
								var cteacher = $('#cteacher').val();
								$.ajax('/exam01/classes_find', {
									type : 'get',
									asyn : false,
									data : {
										cname : cname,
										cadviser : cadviser,
										cteacher : cteacher
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
												field : 'cno',
												title : '班级编号 ',
												width : '15%'
											}, {
												field : 'cname',
												title : '班级名称 ',
												width : '20%'
											}, {
												field : 'ctime',
												title : '开班时间 ',
												width : '15%'
											}, {
												field : 'cnum',
												title : '人数',
												width : '12.3%'
											}, {
												field : 'cadviser',
												title : '班主任',
												width : '10%'
											}, {
												field : 'cteacher',
												title : '讲师 ',
												width : '10%'
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
