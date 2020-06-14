/**
 * 
 */
layui.define([ 'element', 'form', 'jquery', 'layer' ], function(exports) {// layui.use加载所需模块
	var element = layui.element;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;
	// 表单事件监听，拿到修改后的值
	form.on('submit(form4)', function(data) {
		console.log(form.val('form4').tpname);
		$.post('/exam01/score_upd', {
			//cname : form.val('form4').cname,
			// tono :form.val('form1').tono,
			//sname : form.val('form4').sname,
			//获取表单数据
			sno : form.val('form4').sno,
			ename : form.val('form4').tpname,
			esgrade : form.val('form4').esgrade
		}, function(res) {
			layer.alert(res.msg);
		});

		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		return false;
	});
	exports('upd_oform', {});// js页
});