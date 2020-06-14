<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>学生成绩详情弹窗</title>
<link type="text/css" rel="stylesheet" href="/exam01/layui/css/layui.css">
<link type="text/html" href="/exam01/js/mods/cjoform.js">
<link type="text/css" href="/exam01/css/cjoform.css">
</head>
<body>
	<div>
		<table id="chengji" lay-filter="chengji"></table>
	</div>
	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script type="text/javascript">
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.0'
		}).use('cjoform');
	</script>
	<!--  <div class="layui-footer">
			<p class="copyright">&copy; 2019 版权声明</p>
		</div>-->
</body>
</html>