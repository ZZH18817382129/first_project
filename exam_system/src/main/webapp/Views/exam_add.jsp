<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加考试</title>
<link rel="stylesheet" href="/exam01/layui/css/layui.css">
</head>
<body>
	<div class="layui-container wrapper" style="margin-top: 30px">
		<form class="layui-form" id="form1" lay-filter="form1">
			<input type="hidden" id="number" value="${number}">
			<input type="hidden" id="tpno" value="">
			<div style="font-size: 20px; padding-bottom: 30px">
					考试编号：<c:out value="${number}"/><br>
			</div>
			<div class="layui-form-item layui-inline">
				<label class="layui-form-label">考试名称:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="ename" id="ename"
						style="width: 150px" value="${exam.ename}" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item layui-inline">
				<label class="layui-form-label">考试类型:</label>
				<div class="layui-input-block" style="width: 150px">
					<select name="interest" id="etype">
						<option value=""></option>
						<option value="理论">理论</option>
						<option value="上机">上机</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-inline">
				<label class="layui-form-label">开始时间:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="stime" id="stime"
						style="width: 150px" value="${exam.estarttime}"
						lay-verify="required" placeholder="yyyy-MM-dd">
				</div>
			</div>
			<div class="layui-form-item layui-inline">
				<label class="layui-form-label">结束时间:</label>
				<div class="layui-input-block">
					<input class="layui-input" name="etime" id="etime"
						style="width: 150px" value="${exam.eendtime}"
						lay-verify="required" placeholder="yyyy-MM-dd">
				</div>
			</div>
			<div class="layui-form-item layui-inline">
				<label class="layui-form-label">监考老师:</label>
				<div class="layui-input-block" style="width: 150px">
					<select name="etno" id="etno" lay-search>
						<c:forEach items="${teacher}" var="tea">
						 <option value="${tea.tno}">${tea.tno.concat(":").concat(tea.tname)}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-inline">
				<div class="layui-input-block">
					<input class="layui-input" name="estatus" id="estatus" type="hidden"
						style="width: 150px" value="待考" lay-verify="required">
				</div>
           </div>

			<div class="layui-form-item" style="padding-top: 40px">
				<div class="layui-inline">
					<label class="layui-form-label" style="font-size: 12px">试卷名称</label>
					<div class="layui-input-inline" style="width: 150px">
						<input type="text" name="tpname" id="tpname" autocomplete="off"
							class="layui-input" placeholder="请输入">
					</div>
				</div>
				<div class="layui-inline">
					<button type="button" class="layui-btn"
						id="select" lay-sbumit>查询</button>
				</div>
			</div>
			<div class="layui-container">
		<table id="order" lay-filter="order"></table>
	</div>
			<div class="layui-form-item">
				<div class="layui-input-block" style="padding-top: 10px">
					<button class="layui-btn" lay-submit id="select">保存</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/exam01/layui/layui.js" type="text/javascript"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer' ,'laydate','table'], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			var laydate = layui.laydate;
			var table = layui.table;
			

			laydate.render({
			    elem: '#stime'
			    ,theme: 'grid'
			  });
			laydate.render({
			    elem: '#etime'
			    ,theme: 'grid'
			  });
			


			table.render({
				elem : '#order',
				url : '/exam01/test_list',
				page : true,
				limit:4,
				limits:[4,5,6],
				defaultToolbar : [ 'filter', 'print', 'exports' ],
				cols : [ [ {
					  	field : 'id'
					   ,type : 'radio'
				      },{
				    	field:'tpno'
                       ,title:'试卷编号'
					   },{
						   field:'tpname'
                           ,title:'试卷名称'
						},{
							field:'tpteacher'
                            ,title:'出卷人'
						}] ]
			});

			table.on('radio(order)', function(obj){
				var checkStatus = table.checkStatus('order');
				var tpno='';
				layui.each(checkStatus.data, function(index,
						row) { 
					tpno = row.tpno;
				});
				$('#tpno').val(tpno);
				if(tpno!=''){
					layer.msg("添加试卷"+tpno+"成功！");
				}
            });
			
			//查询试卷点击事件
			$('#select').on('click',function(){
				var  tpname = $('#tpname').val();
				$.ajax('/exam01/test_find',{
					type:'get',
					asyn:false,
					data:{
						tpname:tpname,
					},
					success:function(res){
						console.log(res.data);
						table.render({
							elem : '#order',
							page : true,
							data:res.data,
							limit:4,
							limits:[4,5,6],
							defaultToolbar : [ 'filter', 'print', 'exports' ],
							cols : [ [ {
								  	field : 'id'
								   ,type : 'radio'
							      },{
							    	field:'tpno'
			                       ,title:'试卷编号'
								   },{
									   field:'tpname'
			                           ,title:'试卷名称'
									},{
										field:'tpteacher'
			                            ,title:'出卷人'
									}] ]
						});
					},
					error: function(res) {
						layer.alert(res.msg);
					}
				})
			});	

			
			form.on('submit(form1)', function(data) {
				var num = $('#number').val();
				num = "'"+num+"'";
				console.log(num);
				$.ajax('/exam01/exam_add',{
					type:'post',
					asyn:false,
					data:{
						eno:num,
						ename:form.val('form1').ename,
						etype:$('#etype').val(),
						estarttime:form.val('form1').stime,
						eendtime:form.val('form1').etime,
						etno : $('#etno').val(),
						estatus:form.val('form1').estatus,
						tpno:$('#tpno').val(),
					},
					success:function(res){
						layer.alert(res.msg);
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},
					error: function(res) {
						layer.alert(res.msg);
					}
				})
				return false;
			});
		});
	</script>
</body>
</html>
