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
	
	//数据表格的更新渲染
	table.render({
		elem : '#score',
		url : '/exam01/score_list',
		page : true,
		cols : [ [ {
			field : 'sno',
			type : 'checkbox'
		}, {
			field : 'sname',
			title : '学生姓名',
			width : 200
		}, {
			field : 'sno',
			title : '学生学号',
			width : 200
		}, {
			field : 'cname',
			title : '班级名称',
			width : 200
		}, {
			field : 'tpname',
			title : '考试名称',
			width : 200
		}, {
			field : 'esgrade',
			title : '成绩',
			
		} ,{
			fixed:'right',
			title:'操作',
			toolbar:'#bar'
			}] ]
	});
	$('#xs').on('click',function(){
		layer.open({
			type:2,
			title:'学生成绩详情',
			closeBtn:1,
			area : [ '1000px', '450px' ],
			offset:'150px',
			maxmin : true,
			btn : false,
			content : [ '/exam01/Views/cjoform.jsp', 'no' ],
			end : function() {
				table.reload('score');
			}
		});
	});
	$('#jl').on('click',function(){
		layer.open({
			type:2,
			title:'成绩记录',
			closeBtn:1,
			area : [ '1000px', '450px' ],
			offset:'150px',
			maxmin : true,
			btn : false,
			content : [ '/exam01/Views/jilu.jsp', 'no' ],
			end : function() {
				table.reload('score');
			}
		});
	});
	//查询功能的实现
	$('#chaxun1').on('click',function(){
		var sname = $('#sname').val();
		var sno = $('#sno').val();
		var cname = $('#cname').val();
		var tpname = $('#tpname').val();
		var esgrade = $('#esgrade').val();		

			$.get('/exam01/score_cx?sname='+sname+'&sno='+sno+'&cname='+cname
					+'&tpname='+tpname+'&esgrade='+esgrade, function(res) {
				
				layer.alert(res.msg);
				table.render({
					elem : '#score',
					 data: res.data,
					page : true,
					cols : [ [ {
						field : 'sno',
						type : 'checkbox'
					}, {
						field : 'sname',
						title : '学生姓名',
						width : 200
					}, {
						field : 'sno',
						title : '学生学号',
						width : 200
					}, {
						field : 'cname',
						title : '班级名称',
						width : 200
					}, {
						field : 'tpname',
						title : '考试名称',
						width : 200
					}, {
						field : 'esgrade',
						title : '成绩'
					},{	fixed:'right',
						title:'操作',
						toolbar:'#bar'
					}] ]
				});
			});
	});
	table.on('tool(score)', function(btn) {
		var eve = btn.event;
		//知识点的增删改查
		switch(eve){
		//添加知识点
		case 'edit':
			layer.open({
				type : 2,
				title : '编辑',
				closeBtn : 1,
				area : [ '750px', '450px' ],
				offset:'150px',
				maxmin : true,
				btn : false,
				content : [ '/exam01/score_upd?sno='+btn.data.sno+'&ename='+btn.data.tpname, 'no' ],
				end : function() {
					table.reload('score');
				}
			});
			 break;
		case 'xs':
			layer.open({
				type:2,
				title:'学生成绩详情',
				closeBtn:1,
				area : [ '750px', '450px' ],
				offset:'150px',
				maxmin : true,
				btn : false,
				content : [ '/exam01/Views/cjoform.jsp', 'no' ],
				end : function() {
					table.reload('score');
				}
			});
			break;	
		}
	});
	exports('score_test', {});
});