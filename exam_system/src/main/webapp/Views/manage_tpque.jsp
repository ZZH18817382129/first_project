<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑试卷试题</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>

	<div class="layui-container" style="padding: 0px;margin-left: 43px">
	
		<form action="" name="form1" method="post" class="layui-form"
			lay-filter="form1" id="from1">
         		
                          <div id="test1"></div> 
						<div class="layui-form-item"
							style="padding-left: 318px">
								<button type="submit" class="layui-btn" lay-submit=""
									lay-filter="demo1">保存</button>
						</div>
						<input type="hidden" id="ids" value="${managetpno}">
		</form>
	</div>

	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table','transfer' ],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;
					var transfer = layui.transfer;
					var tpno = $('#ids').val();
					//console.log(mno);
					$.get('/exam01/TransferQuestion?tpno='+tpno, function(res) {
					 transfer.render({
					      elem: '#test1'
					      ,data:res.data
					      ,title:['未选用试题','已选用的试题 ']
					      ,width: 310 
					      ,height: 600
					      ,value: res.value
					      ,showSearch: true
					      ,id: 'test'
					    });
					});
					
					
					
					form.on('submit(form1)', function(data) {
						console.log(transfer.getData('test'));
						var da = transfer.getData('test');
                        var a = '';
                        for(var i = 0; i < da.length;i++){
                            a += "'"+da[i].value+"',";
                            }
                       
                        var arr = new Array();
                        arr = a.split(",");
                        if(arr.length > 51){

                        	  layer.msg('不能大于50道题！！！！');
                            }else{
                       
                       
                            
						console.log(arr.length);
						$.post('/exam01/TransferTpQuestion', {
							tpno : tpno,
							data1: a
						}, function(res) {
							layer.alert(res.msg);
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
                            }
					});

				});
	</script>
</body>
</html>