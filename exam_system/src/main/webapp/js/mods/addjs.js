/**
 * 
 */
layui.define([ 'element', 'form', 'jquery', 'layer' ], function(exports) {//layui.use加载所需模块
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			
			var layer = layui.layer;
			form.on('submit(form3)', function(data){	
				$.post('/exam01/role_add', {
					ridentity :form.val('form3').ridentity,
					id:form.val('form3').id
				}, function(res) {
					layer.alert(res.msg);
				});
				
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return false;
			});exports('addjs',{});//js页
});