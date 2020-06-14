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
	$.get('/exam01/items', function(res) {
		table.render({
			elem : '#jilu',
			data : res.data,
			page : true,
			cols : [ [ {
				field : 'A',
				title : 'A',
				width : 120
			}, {
				field : 'B',
				title : 'B',
				width : 120
			},{
				field : 'C',
				title : 'C',
				width : 120
			}, {
				field : 'D',
				title : 'D',
				width : 120

			}, {
				field : 'correct',
				title : '正确个数',
				width : 120

			}, {
				field : 'incorrect',
				title : '错误个数',
				width : 120
			},{
				field : 'accu',
				title : '正确率',
				width : 120
			},{
				field : 'ritem',
				title : '正确答案',
			}] ]
		});
		form.render();
	});
	// 数据表格的更新渲染
	exports('jilu', {});
});