/**
 * 
 */
/**
 * 
 */
layui.define([ 'element', 'form', 'jquery', 'layer' ], function(exports) {//layui.use加载所需模块
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			form.on('submit(form1)', function(data){
				var id=$('#rid').val();
				console.log(id);
				if(id==''||id==null){
					$.get('/exam01/do_role_add', {
						ridentity :form.val('form1').ridentity
					}, function(res) {
						layer.msg(res.msg);
					});
				}
				else{
					$.get('/exam01/do_role_upd', {
						ridentity :form.val('form1').ridentity,
						id:form.val('form1').id
					}, function(res) {
						layer.msg(res.msg);
					});
				}
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return false;
			});exports('role_oform',{});
});