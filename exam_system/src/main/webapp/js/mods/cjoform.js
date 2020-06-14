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
	
	$.get('/exam01/max', function(res) {
		table.render({
			elem : '#chengji',
			data : res.data,
			page : true,
			cols : [ [{
				field : 'cname',
				title : '班级名称',
				width : 100
			}, {
				field : 'cnum',
				title : '班级人数',
				width : 100
			}, {
				field : 'AVG',
				title : '平均成绩',
				width : 100
			}, {
				field : 'MAX',
				title : '最大值',
				width : 100
			}, {
				field : 'MIN',
				title : '最小值',
				width : 100

			}, {
				field : 'prate',
				title : '及格率',
				width : 100

			}, {
				field : 'rnum',
				title : '及格人数',
				width : 100

			}, {
				field : 'yrate',
				title : '优秀率',
				width : 100

			}, {
				field : 'gnum',
				title : '优秀人数'

			} ] ]
		});
		form.render();
	});
	// 数据表格的更新渲染
	exports('cjoform', {});
});