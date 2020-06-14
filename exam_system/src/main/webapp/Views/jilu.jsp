<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>成绩记录</title>
<link type="text/css" rel="stylesheet" href="/exam01/layui/css/layui.css">
<link type="text/html" href="/exam01/js/mods/jilu.js">
<link type="text/css" href="/exam01/css/jilu.css">
</head>
<body>
<div>
		<table id="jilu" lay-filter="jilu"></table>
	</div>
	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script type="text/javascript">
		layui.config({
			base : '/exam01/js/mods/',
			version : '1.0'
		}).use('jilu');
	</script>
</body>
</html>