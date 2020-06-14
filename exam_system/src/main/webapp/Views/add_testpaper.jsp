<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加试卷</title>
</head>
<link href="/exam01/layui/css/layui.css" rel="stylesheet"
	type="text/css">
<body>
	<div class="layui-container" >
		<form action="" name="form1" class="layui-form" lay-filter="form1">

			<div class="layui-form-item">
			   <div style="font-size: 20px; padding-bottom: 30px">
					试卷编号：<c:out value="${number}"/><br>
					
				</div>
			
				<div class="layui-inline">
					<label class="layui-form-label">试卷名称：</label>
					<div class="layui-input-inline" >
						<input type="text" name="pname" id="pname"  class="layui-input"  lay-verify="required|title">
					</div>
				</div>
				
				<div class="layui-inline">
					<label class="layui-form-label">出卷人：</label>
					<div class="layui-input-inline">
						<input type="text" name="pman" id="pman" class="layui-input" lay-verify="required">
					</div>
				</div>
					
			</div>
		 
			<input type="hidden" name="ids" id="ids" value="">
			
				<input type="hidden" name="number" id="number" value="${number}">
				
			<hr>
		    <div class="layui-btn " id="num">已添加试题<span class="layui-badge layui-bg-gray layui-bg-green">0</span></div>
				注意：添加的试题不能多于50道题也不能少于50道题！！！！！      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
				<input class="layui-btn" type="submit" lay-submit  value="提交试卷">
							
            <div id="showids"></div>
           
            <hr> 
			<div class="demoTable" style="">
				
				知识点：
					
				   
					<div class="layui-input-inline">
						<select name="topic" id="topic"  lay-search="" >
							<option value="">直接选择或搜索</option>
							<c:forEach items="${tlist}" var="topic">
							<option value="${topic.toname}"><c:out value="${topic.toname}"/></option>
							</c:forEach>
						</select>
					
					</div>
				<button  type="button" class="layui-btn " id="select" name="select">搜索</button>
			</div>
		
		
	
			<!--
			<script type="text/html" id="toolbar">
            
			<a class="layui-btn layui-btn-sm" lay-event="add">一键添加</a>
			<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="remove">一键移除</a>
		    
		
        </script>
             -->
			<div style="width: 1151px">
				<table id="tt" lay-filter="tt"></table>
			</div>
			<script type="text/html" id="barDemo">
       
        </script>

	

	</form>
</div>

	<script type="text/javascript" src="/exam01/layui/layui.js"></script>
	<script>
		layui.use([ 'element', 'form', 'jquery', 'layer', 'table' ],
				function() {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;
					var tpno=$('#number').val();
                    var counter = 0;
					table.render({
						elem : '#tt',
						url : '/exam01/ListTestQuestion?tpno='+tpno,
						page : true,
						title : '试题列表',
						cols : [ [ {
							field : 'qno',
							width : 45,
							type : 'checkbox'
						}, {
							field : 'qno',
							title : '试题编号',
							width : 130
						}, {
							field : 'qstem',
							title : '试题',
							width : 950
						}
						] ]
					});


					
					
					/* table.on('toolbar(tt)',function(btn){
						var evt = btn.event;
						switch(evt){

						
						case 'add':
							layer.confirm("确认添加吗！", {
								icon : 1,
								/*
								var i=data;
								$('#test').val(i);
								var ids=$('#test').val();
								*/
								/* title : '询问'
							}, function(win) { 
								var now_ids = $('#ids').val();//1,2,3
								var checkStatus = table.checkStatus('tt');
								if (!checkStatus.data.length > 0) {
									layer.alert('请至少选取一行数据');
									layer.close(win);
									return;
								}
								layer.close(win);
								var qno = '';
								var tpnames='';
								layui.each(checkStatus.data, function(index,
										row) { 
									qno += row.qno + ",";
									tpnames += row.qstem +",";
								});
								qno = qno.substring(0, qno.length - 1);//2,3,4,
								var arr1 = new Array();
								var arr2 = new Array();
								//1001,1002   1001,1002
								arr1 = now_ids.split(",");
								arr2 = qno.split(",");
								var count = 0;
								var temp = now_ids;
								for(var i = 0; i < arr2.length; i++){
									for(var j = 0; j < arr1.length; j++){
											if(arr2[i]==arr1[j]){
												count++;	
											}
									}
									if(count==0){
										temp += ","+arr2[i];
									}
									count =0;
								}
								console.log(temp.substring(1,temp.length));
								$('#ids').val(temp);
								$('#num').html('已添加试题<span class="layui-badge layui-bg-gray">'
										+temp.substring(1,temp.length).split(",").length+'</span>');
							}, function() {
								
							});

							break;
						case 'remove':
							layer.confirm("确认移除吗！", {
								icon : 1,
								/*
								var i=data;
								$('#test').val(i);
								var ids=$('#test').val();
								
								title : '询问'
							}, function(win){
							var checkStatus = table.checkStatus('tt');
							/*$('#123 dd').on('click',function(){
								$('#select').val();
							});
							if (!checkStatus.data.length > 0) {
								layer.alert('请至少选取一行数据');
								layer.close(win);
								return;
							}
							layer.close(win);
							var qno = '';
							layui.each(checkStatus.data, function(index,
									row) { 
								qno += row.qno + ",";
							});
							qno = qno.substring(0, qno.length - 1);
							removeItem(qno);
							var ids = $('#ids').val();
							console.log(ids);
							var len = ids.split(",").length;
							if(ids==""){
								len = 0;
							}
							
							$('#num').html('已添加试题<span class="layui-badge layui-bg-gray">'
									+len+'</span>');
							});
							break;
						};
						
					});*/
                  //复选框点击事件
                  
					table.on('checkbox(tt)', function(obj){
						var qno = obj.data.qno;
						var LAY_CHECKED = obj.checked;
						
						console.log(obj.type);
							$.post('/exam01/CheckedAdd', {
									qno : obj.data.qno,
									tpno  : tpno,
									LAY_CHECKED : LAY_CHECKED
								}, function(res) {
									$('#num').html('已添加试题<span class="layui-badge layui-bg-gray">'
											+res.que_nums+'</span>');
							});
						if(obj.type=="all"){
							var item_table = table.cache['tt'];
							var qnonum = '';
							for (var index in item_table) {
							     console.log(item_table[index].LAY_CHECKED);
							     qnonum += item_table[index].qno+",";
							}
							qnonum = qnonum.substring(0,qnonum.length-1);
							console.log(qnonum);
							$.get('/exam01/CheckedAdds',{
                                qnos:qnonum,
								tpno:tpno,
								LAY_CHECKED:item_table[0].LAY_CHECKED
							},function(res){
								layer.msg(res.msg);
								$('#num').html('已添加试题<span class="layui-badge layui-bg-gray layui-bg-green">'
										+res.que_nums+'</span>');
								counter = res.que_nums;
							});
							//qno = qno.substring(0, qno.length - 1);
						} 

						//var now_counter = #('ids1').val; 
						var checkStatus = table.checkStatus('tt');
						counter = checkStatus.data.length 
						
						//now_counter = counter;
                      }); 

						
					/* //一键移除函数
					function removeItem(ids){
						var arr = new Array();
						arr = ids.split(",");
						var arr_now = new Array();
						var ids_now = $('#ids').val();
						arr_now = ids_now.split(",");
						var len_now = arr_now.length;
						temp = arr_now;
						var result = "";
						for(var i=0;i<arr.length;i++){
							for(var j=0;j<len_now;j++){
								if(arr[i]==temp[j]){
									arr_now.splice(j,1);
								}
							}
						}
						for(var k=0;k<arr_now.length;k++){
							result += arr_now[k]+",";	
						}
						result = result.substring(0, result.length - 1);
						$('#ids').val(result);
					} */

					 //表单验证 
					
                    form.verify({
                  	    title: function(value){
                  	      var tpname = $('#pname').val();
                  	      var message = ''; 
                  	    console.log(tpname);
                  	    $.ajax({
                			type:"POST",
                			url:'/exam01/SelectTpname',
                			async: false, 
                			data:{
                    			tpname:tpname
                    			},
                    			
                			success:function(data){
                				console.log(data);
                				if(data){
                					
                				}else{
                					message ="试卷已存在，请重新输入！"
                				}
                			}
                		});
                  	  if (message !== '') 
                      	return message;
           
                  	    }
                  	  }); 
                 
					//查询按钮点击事件
					$('#select').on('click',function(){
						var lesson = $('#lesson').val();
						var topic = $('#topic').val();
					
						$.ajax('/exam01/ListTestQuestion',{
							type:'get',
							asyn:false,
							data:{
								page:1,
								limit:10,
								tpno:tpno,
								toname:topic
							},
							success:function(res){
								console.log(res.data);
								table.render({
									elem : '#tt',
									height : 500,
									data:res.data,
									toolbar: '#toolbar',
									defaultToolbar: ['filter', 'exports', 'print'],
									page : true,
									title : '试题列表',
									cols : [ [ {
										field : 'id',
										width : 45,
										type : 'checkbox',
									}, {
										field : 'qno',
										title : '试题编号',
										width : 130
									}, {
										field : 'qstem',
										title : '试题',
										width : 950
									}

									] ]
								});
								
							},
							error: function() {
								
							}
						})
					});	
					
					form.on('submit(form1)', function(data) {
						
						if(counter > 50){
							layer.msg('不能大于50道题！！！！！！')
							}else{
							
							
                        
						$.post('/exam01/AddTestPaper', {
							tpno : $('#number').val(), 
							pname : $('#pname').val(),
							pman : $('#pman').val(),
                            //ids : $('#ids').val()  
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