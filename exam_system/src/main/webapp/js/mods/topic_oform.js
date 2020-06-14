/**
 * 
 */
layui.define([ 'element', 'form', 'jquery', 'layer' ], function(exports) {//layui.use加载所需模块
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			//表单事件监听，拿到修改后的值
			form.on('submit(form1)', function(data){	
				$.post('/exam01/topic_upd', {
					toname :form.val('form1').toname,
					//tono :form.val('form1').tono,
					tolevel :form.val('form1').tolevel,
					tostage :form.val('form1').tostage,
					tomodule :form.val('form1').tomodule,
					id:form.val('form1').id
				}, function(res) {
					layer.alert(res.msg);
				});
				
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return false;
			});exports('topic_oform',{});//js页
});