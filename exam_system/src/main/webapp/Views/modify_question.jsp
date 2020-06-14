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
<title>修改试题</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
<style type="text/css">
	.this_items{}
	.wrapper{margin-top: 20px;width:800px}
</style>
</head>
<body>
	<div class="layui-container wrapper">
		<form class="layui-form" id="form1" action="" method="post"
			lay-filter="form1">
			<input type="hidden" name="qno" value="${c0.qno}">
			<input type="hidden" name="ino0" value="${c0.ino}">
			<input type="hidden" name="ino1" value="${c1.ino}">
			<input type="hidden" name="ino2" value="${c2.ino}">
			<input type="hidden" name="ino3" value="${c3.ino}">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">试题内容：</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" class="layui-textarea" 
					id="qstem" name="qstem">${c0.qstem}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">A:</label>
				<div class="layui-input-block ">
					<input class="layui-input" name="item0" id="
						item0"
						value="${c0.item}" lay-verify="required">
					
				</div>
				
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">B:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="item1" id="
						item1"
						value="${c1.item}" lay-verify="required">
						
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">C:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="item2" id="
						item2"
						value="${c2.item}" lay-verify="required">
						
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">D:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="item3" id="
						item3"
						value="${c3.item}" lay-verify="required">
						
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">正确答案</label>
				<div class="layui-input-block">
				<input ino="${c0.ino}" type="radio" name="correct" istatus="${c0.istatus}" class="this_items"  value="A" title="A" >	
				<input ino="${c1.ino}" type="radio" name="correct" istatus="${c1.istatus}" class="this_items" value="B" title="B" >	
				<input ino="${c2.ino}" type="radio" name="correct" istatus="${c2.istatus}" class="this_items" value="C" title="C" >
				<input ino="${c3.ino}" type="radio" name="correct" istatus="${c3.istatus}" class="this_items" value="D" title="D" >
				</div>
			</div>
			<div class="layui-form-item" lay-filter="test1">
				<label class="layui-form-label">知识点：</label>
				<div class="layui-input-block">
				
				<div class="layui-inline" id="temp">
					<input class="layui-input" id="toname" name="toname" value="" style="width:500px">
				</div>
				 <div class="layui-inline">
				 <button id="select" type="button" class="layui-btn" lay-sbumit>点此查询</button>
					<!-- <input class="layui-btn" type="button" id="selects" value="点此选择"> -->
				</div> 
				<div class="layui-table">
					<table id="topic" lay-filter="topic"></table>
				</div>
					</div>	
			  </div>	
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input class="layui-btn" lay-submit type="submit" value="保存">
				</div>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer','table'], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var table = layui.table;
	
			form.on('submit(form1)', function() {
				var all_items = $('.this_items');
				var rino = "";
				for(var i = 0;i<4;i++){
					if(all_items[""+i].checked==true){
						//得到该答案的ino.
						console.log(all_items[""+i]);
						rino+=all_items[""+i].attributes[0].value;
					}
				}
				var data = form.val('form1');
				$.post('/exam01/Question_upd', {
					qno : data.qno,
					qstem: data.qstem,
					//tono : data.toname,
					item0: data.item0,
					item1: data.item1,
					item2: data.item2,
					item3: data.item3,
					ino0 : data.ino0,
					ino1 : data.ino1,
					ino2 : data.ino2,
					ino3 : data.ino3,
					rino : rino
				}, function(res) {
					layer.alert(res.msg);
				});
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return false;
			});
			//设置正确答案为被选中状态.
			var all_items = $('.this_items');
			for(var i = 0;i<4;i++){
				var istatus = all_items[""+i].attributes[3].value;
				if(istatus=='1'){
					all_items[""+i].checked=true;
					form.render();
				}
			};
			
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
				},{
					field : 'tono',
					title : '知识点编号 ',
					width : '300'
				} ] ]
			});
			
			table.on('checkbox(topic)', function(obj){
				var tono=obj.data.tono;
				var LAY_CHECKED=obj.checked;
			   $.post('/exam01/Checked_topic_Add',{
				tono:obj.data.tono,
				LAY_CHECKED:LAY_CHECKED
				},function(res){
			}); 
				if(obj.type=="all"){
					
				}else{
					var checkStatus = table.checkStatus('topic');
					var toname = '';
					layui.each(checkStatus.data, function(index,row) {
						toname += row.toname + ",";
					});
					toname=toname.substring(0,toname.length-1);
					
				}
			}); 
			//点击查询事件
			 $('#select').on('click',function(){
					var toname= $('#toname').val();
					$.ajax('/exam01/question_topic_find',{
						type:'get',
						asyn:false,
						data:{
							toname:toname
						},
						success:function(res){
							console.log(res.data);
							table.render({
								elem : '#topic',
								data:res.data,
								page : true,
								cols : [ [ {
									field : 'id',
									type : 'checkbox'
								}, {
									field : 'toname',
									title : '知识点名称  ',
									width : '300'
								},{
									field : 'tono',
									title : '知识点编号 ',
									width : '300'
								} ] ]
							});
							var index = parent.layer.getFrameIndex(window.name);
						},
						error: function(res) {
							layer.alert(res.msg);
						}
					})
				});	
			
		});
	</script>
</body>
</html>
