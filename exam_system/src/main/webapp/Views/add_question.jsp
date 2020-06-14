<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>添加试题</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<style type="text/css">
.this_items {
	
}

.wrapper {
	margin-top: 20px;
	width: 800px
}
</style>
</head>
<body>
	<input type="hidden" value="" id="ci">
	<div class="layui-container wrapper">
		<form class="layui-form" id="form1" action="" method="post"
			lay-filter="form1">

			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">试题内容：</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" class="layui-textarea" id="qstem"
						name="qstem" lay-verify="required"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">A:</label>
				<div class="layui-input-block ">
					<input class="layui-input" name="item0" id="
						item0" value=""
						lay-verify="required">

				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">B:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="item1" id="
						item1" value=""
						lay-verify="required">

				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">C:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="item2" id="
						item2" value=""
						lay-verify="required">

				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">D:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="item3" id="
						item3" value=""
						lay-verify="required">

				</div>
			</div>



			<div class="layui-form-item">
				<label class="layui-form-label">正确答案</label>
				<div class="layui-input-block">
					<input ino="0" type="radio" name="correct" value="A" title="A"
						class="this_items"> <input ino="1" type="radio"
						name="correct" value="B" title="B" class="this_items"> <input
						ino="2" type="radio" name="correct" value="C" title="C"
						class="this_items"> <input ino="3" type="radio"
						name="correct" value="D" title="D" class="this_items">
				</div>
			</div>
			<div class="layui-form-item" lay-filter="test1">
				<label class="layui-form-label">知识点：</label>
				<div class="layui-input-block">

					<div class="layui-inline" id="temp">
						<input class="layui-input" id="toname" name="toname" value=""
							style="width: 500px">
					</div>
					<div class="layui-inline">
						<button id="select" type="button" class="layui-btn" lay-sbumit>点此查询</button>
					</div>
					<div class="layui-table">
						<table id="topic" lay-filter="topic"></table>
					</div>
				</div>
			</div>
			<div class="layui-form-item layui-container">
				<div class="layui-input-block">
					<input class="layui-btn" lay-submit type="submit" value="保存">
				</div>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table' ],
				function() {
					var $ = layui.jquery;
					var element = layui.element;
					var form = layui.form;
					var layer = layui.layer;
					var table = layui.table;

					layui.form.render();
					form.on('submit(form1)', function() {
						console.log(123);
						var all_items = $('.this_items');
						var rino = "";
						for (var i = 0; i < 4; i++) {
							if (all_items["" + i].checked == true) {
								//得到该答案的ino.
								rino += all_items["" + i].attributes[0].value;
							}
						}
						var data = form.val('form1');
						$.post('/exam01/Question_add', {
							qstem : data.qstem,
							tono : 1,
							item0 : data.item0,
							item1 : data.item1,
							item2 : data.item2,
							item3 : data.item3,
							rino : rino
						}, function(res) {
							layer.alert(res.msg);
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						return false;
					});
					table.render({
						elem : '#topic',
						url : '/exam01/Question_topic_list',
						page : true,
						limit : 10,
						cols : [ [ {
							field : 'id',
							type : 'checkbox'
						}, {
							field : 'toname',
							title : '知识点名称  ',
							width : '300'
						}, {
							field : 'tono',
							title : '知识点编号 ',
							width : '307'
						} ] ]
					});

					table.on('checkbox(topic)', function(obj) {
						var tono = obj.data.tono;
						var LAY_CHECKED = obj.checked;
						$.post('/exam01/Checked_topic_Add', {
							tono : obj.data.tono,
							LAY_CHECKED : LAY_CHECKED
						}, function(res) {
						});
						if (obj.type == "all") {

						} else {
							var checkStatus = table.checkStatus('topic');
							var toname = '';
							layui.each(checkStatus.data, function(index, row) {
								toname += row.toname + ",";
							});
							toname = toname.substring(0, toname.length - 1);
						}
					});

					//点击查询事件
					$('#select').on(
							'click',
							function() {
								var toname = $('#toname').val();
								$.ajax('/exam01/question_topic_find', {
									type : 'get',
									asyn : false,
									data : {
										toname : toname
									},
									success : function(res) {
										console.log(res.data);
										table.render({
											elem : '#topic',
											data : res.data,
											page : true,
											cols : [ [ {
												field : 'id',
												type : 'checkbox'
											}, {
												field : 'toname',
												title : '知识点名称  ',
												width : '300'
											}, {
												field : 'tono',
												title : '知识点编号 ',
												width : '307'
											} ] ]
										});
										var index = parent.layer
												.getFrameIndex(window.name);
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
