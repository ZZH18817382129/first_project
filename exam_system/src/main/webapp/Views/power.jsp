<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link type="text/css" rel="stylesheet"
	href="/exam01/layui/css/layui.css" media="all">
</head>
<body>
	<div>
		<form action="/exam01/update_priv" class="layui-form layui-form-pane"
			style="" lay-filter="loginform" method="post">
			<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
			<div id="privs"></div>
			<table id="users" lay-filter="users"></table>
			<script type="text/html" id="toolbar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-sm" id="add" lay-event="add">添加用户</a>
			<a class="layui-btn layui-btn-sm" lay-event="delm" id="delm">批量删除</a>
			<a class="layui-btn layui-btn-sm" lay-event="give_role" id="give_role">批量授予角色</a>
			<a class="layui-btn layui-btn-sm" lay-event="resetpwd" id="resetpwd">重置密码</a>
		</div>
	</script>
			<script type="text/html" id="bar">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-xs" lay-event="edit" id="edit">修改信息</a>
			<a class="layui-btn layui-btn-xs" lay-event="userable" id="userable">激活</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="userdisable" id="userdisable">禁用</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" id="del">删除</a>
		</div>
	</script>
		</form>
	</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'layer', 'form', 'jquery', 'transfer', 'table' ],
				function() {
					var layer = layui.layer
					var form = layui.form;
					var $ = layui.$;
					var transfer = layui.transfer;
					var table = layui.table;
					var contextPath=$('#contextPath').val();
					table.render({
						elem : '#users',
						url : contextPath+'/get_users',
						page : true,
						toolbar : '#toolbar',
						defaultToolbar : [ 'filter', 'print', 'exports' ],
						limit : 10,
						limits : [ 5, 10, 15 ],
						cols : [ [ {
							field : 'uno',
							type : 'checkbox'
						}, {
							field : 'uno',
							align : 'center',
							title : '用户工/学号  ',
							width : '12%',
							minWidth : 150
						}, {
							field : 'identity',
							align : 'center',
							title : '用户身份',
							width : '10%'
						}, {
							field : 'uname',
							align : 'center',
							title : '用户姓名',
							width : '10%',
							templet : function(data) {
								if (data.uname != '') {
									return '<span>待完善</span>';
								} else {
									return '<span>' + data.uname + '</span>';
								}
							}
						}, {
							field : 'uemail',
							align : 'center',
							title : '用户邮箱',
							width : '15%',
							templet : function(data) {
								if (data.uemail != '') {
									return '<span>待完善</span>';
								} else {
									return '<span>' + data.uemail + '</span>';
								}
							}
						}, {
							field : 'uphone',
							align : 'center',
							title : '联系电话',
							width : '12%',
							templet : function(data) {
								if (data.uphone != '') {
									return '<span>待完善</span>';
								} else {
									return '<span>' + data.uphone + '</span>';
								}
							}
						}, {
							field : 'ustatus',
							align : 'center',
							title : '账号状态',
							width : '10%'
						}, {
							field : 'utime',
							align : 'center',
							title : '创建日期',
							width : '13%'
						}, {
							fixed : 'right',
							align : 'center',
							title : '操作',
							toolbar : '#bar',
							width : 250
						} ] ]
					});
					table.reload('users');
					table.on('toolbar(users)', function(btn) {
						var evt = btn.event;
						switch (evt) {
						case 'add':
							layer.open({
								type : 2,
								title : '添加用户',
								closeBtn : 1,
								offset : 100,
								area : [ '520px', '350px' ],
								maxmin : false,
								btn : false,
								content : [ '/exam01/get_roles', 'yes' ],
								end : function() {
									table.reload('users');
								}
							});
							break;
						case 'give_role':
							//跳转到授予身份页面,从服务端获取到所有身份信息
							var checkStatus = table.checkStatus('users');
							if (!checkStatus.data.length > 0) {
								layer.msg('请至少选取一个用户。');
								return;
							} else {
								var unos = '';
								layui.each(checkStatus.data, function(index,
										row) {
									unos += row.uno + ',';
								});
								unos = unos.substring(0, unos.length - 1);
								layer.open({
									type : 2,
									title : '批量授予身份',
									closeBtn : 1,
									offset : 100,
									area : [ '520px', '350px' ],
									maxmin : false,
									btn : false,
									content : [
											'/exam01/forwardto_give_role?unos='
													+ unos, 'yes' ],
									end : function() {
										table.reload('users');
									}
								});
							}
							break;
						case 'delm':
							var checkStatus = table.checkStatus('users');
							if (!checkStatus.data.length > 0) {
								layer.msg('请至少选取一个用户。');
								return;
							}
							layer.confirm("确认要删除吗？一旦删除数据不可恢复！", {
								icon : 0,
								title : '确认删除吗?'
							}, function(win) {
								layer.close(win);
								var uno = '';
								layui.each(checkStatus.data, function(index,
										row) {
									uno += "'" + row.uno + "',";
								});
								uno = uno.substring(0, uno.length - 1);
								$.get('/exam01/user_delm', {
									ids : uno
								}, function(res) {
									layer.msg(res.msg);
								});
								table.reload('users');
							}, function() {
							});
							break;
						case 'resetpwd':
							var checkStatus = table.checkStatus('users');
							if (!checkStatus.data.length > 0) {
								layer.msg('请至少选取一个用户。');
								return;
							}
							layer.confirm("确认要重置选中用户的密码为初始密码吗？", {
								icon : 0,
								offset : 200,
								title : '确认重置密码吗?'
							}, function(win) {
								layer.close(win);
								var uno = '';
								layui.each(checkStatus.data, function(index,
										row) {
									uno += "'" + row.uno + "',";
								});
								uno = uno.substring(0, uno.length - 1);
								$.get('/exam01/reset_pwd?uno=' + uno, function(
										res) {
									layer.msg(res.msg);
								});
								table.reload('users');
							}, function() {
							});
							break;
						}
					});
					table.on('tool(users)', function(btn) {
						var evt = btn.event;
						var uno = btn.data.uno;
						var identity = btn.data.identity;
						console.log(uno);
						console.log(identity);
						switch (evt) {
						case 'edit':
							layer.open({
								type : 2,
								title : '修改用户信息',
								closeBtn : 1,
								area : [ '520px', '500px' ],
								maxmin : true,
								btn : false,
								content : [
										'/exam01/user_modify?uno=' + uno
												+ '&identity=' + identity,
										'yes' ],
								end : function() {
									table.reload('users');
								}
							});
							break;
						case 'del':
							layer.confirm("确认要删除吗？一旦删除数据不可恢复！", {
								icon : 0,
								title : '确认删除吗?'
							}, function() {
								$.post('/exam01/removeUser?uno=' + uno
										+ '&identity=' + identity);
								table.reload('users');
							}, function() {
							});
							break;
						case 'userdisable':
							layer.confirm("确认要禁用吗?可点击激活按钮再次激活", {
								icon : 0,
								title : '确认禁用吗?'
							}, function() {
								$.get('/exam01/set_user_disable?uno=' + uno
										+ '&ridentity=' + identity, function(
										res) {
									layer.msg(res.msg);
								});
								table.reload('users');
							}, function() {
							});
							break;
						case 'userable':
							layer.confirm("确认要激活吗?", {
								icon : 0,
								title : '确认激活吗?'
							}, function() {
								$.get('/exam01/set_user_able?uno=' + uno
										+ '&ridentity=' + identity, function(
										res) {
									layer.msg(res.msg);
								});
								table.reload('users');
							}, function() {
							});
							break;
						}
					});
				});
	</script>
</body>
</html>