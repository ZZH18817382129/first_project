/**
 * 
 */
layui.define([ 'element', 'form', 'jquery', 'layer', 'table' ], function(
		exports) {
	var element = layui.element;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;
	var table = layui.table;
	
	
	//数据表格的渲染
	table.render({
		elem : '#topic',
		toolbar : '#toolbar',//头部功能栏
		url : '/exam01/topic_list',//列出所有知识点
		page : true,  //分页开启
		defaultToolbar : [ 'filter', 'print', 'exports' ],
		cols : [ [ {
			field : 'id',
			type : 'checkbox'
		}, {
			field : 'toname',
			title : '知识点名称',
			width : 200
		}, {
			field : 'tono',
			title : '知识点编号',
			width : 200
		}, {
			field : 'tolevel',
			title : '级别',
			width : 200
		}, {
			field : 'tostage',
			title : '阶段',
			width : 200
		}, {
			field : 'tomodule',
			title : '模块',
			width : 200
		}, {
			fixed : 'right',
			title : '操作',
		  toolbar : '#bar'
		} ] ]

	});
	//查询功能的实现，先给值后判断
	$('#chaxun').on('click',function(){
		var toname = $('#toname').val();
		var tono = $('#tono').val();
		var tolevel = $('#tolevel').val();
		var tostage = $('#tostage').val();
		var tomodule = $('#tomodule').val();
		//判断知识点每个字段是否为空
		if((tono==''||tono==null)&&(toname==''||toname==null)&&(tolevel==''||tolevel==null)
				&&(tostage==''||tostage==null)&&(tomodule==''||tomodule==null)){
			layer.msg('请至少输入一个查询条件',{
				icon : 0,
				title : '询问'
			},function(){
				layer.close();
			});
		}else{
			//拿到添加的值，再进行数据的更新渲染
			$.get('/exam01/topic_cx?toname='+toname+'&tono='+tono+'&tolevel='+tolevel
					+'&tostage='+tostage+'&tomodule='+tomodule, function(res) {
				layer.alert(res.msg);
				table.render({
					elem : '#topic',
					toolbar : '#toolbar',
					data:res.data,
					page : true,
					defaultToolbar : [ 'filter', 'print', 'exports' ],
					cols : [ [ {
						field : 'id',
						type : 'checkbox'
					}, {
						field : 'toname',
						title : '知识点名称',
						
						width : 200
					}, {
						field : 'tono',
						title : '知识点编号',
						width : 200
					}, {
						field : 'tolevel',
						title : '级别',
						width : 200
					}, {
						field : 'tostage',
						title : '阶段',
						width : 200
					}, {
						field : 'tomodule',
						title : '模块',
						width : 200
					}, {
						fixed : 'right',
						title : '操作',
						toolbar : '#bar',
					} ] ]
				});
			});
		}
	});
	table.on('toolbar(topic)', function(btn) {
		var eve = btn.event;
		//知识点的增删改查
		switch(eve){
		//添加知识点
		case 'addo':
			layer.open({
				type : 2,
				title : '新增知识点',
				closeBtn : 1,
				area : [ '750px', '450px' ],
				offset:'150px',
				maxmin : true,
				btn : false,
				content : [ '/exam01/get_topics', 'no' ],
				end : function() {
					table.reload('topic');
				}
			});
			 break;
			 //批量删除知识点
		case 'deltopic':
			layer.confirm("删除", {//layer-confirm 确认框
				icon : 3,
				tiltle : '询问',
				offset:'150px'
			}, function(win){
				var checkStatus = table.checkStatus('topic');//checkStatus获取选中行
				if (!checkStatus.data.length > 0) {
					layer.alert('请至少选择一行数据');
					layer.close(win);
					return; 
				}
				layer.close(win);
				var id = '';

				layui.each(checkStatus.data, function(index,//layui.each遍历
						row) {
					id += row.id + ",";
				});
				id = id.substring(0, id.length - 1);
				$.post('/exam01/zsd_del', {
					id : id
				}, function(res) {
					layer.alert(res.msg);
				});
				table.reload('topic');//重新加载
			}, function() {

			});
			break;
		}
		;
	});
	
	table.on('tool(topic)', function(btn) {
		var eve = btn.event;
		//对知识点进行修改
		switch (eve) {
		case 'edit':
			layer.open({
				type : 2,
				title : '编辑',
				closeBtn : 1,
				area : [ '750px', '450px' ],
				offset:'150px',
				maxmin : true,
				btn : false,
				content : [ '/exam01/topic_upd?id=' + btn.data.id, 'no' ],
				end : function() {
					table.reload('topic');
				}
			});
			break;
			//删除一个知识点
		case 'del':
			layer.confirm('确定删除当前数据？', {
				icon : 0,
				title : '询问'
			}, function(win) {
				layer.close();
				var id = btn.data.id;
				$.get('/exam01/topic_del?id=' + id, function(res) {
					layer.alert(res.msg);
				});
				table.reload('topic');
			}, function() {

			});
			break;
		}
		;
	});
	exports('zsd', {});

});