/**
 * 
 */
layui.define([ 'element', 'form', 'jquery', 'layer' ], function(exports) {//layui.use加载所需模块
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			//事件监听
			form.on('submit(form2)', function(data){
				//判断输入数据是否为空
				var toname = $('#toname').val();  	
				if(toname=='' || toname == null){
					layer.msg("知识点名称不能为空，请重新输入");
					return false;
				}
				var tolevel = $('#tolevel').val();
				if(tolevel=='' || tolevel == null){
					layer.msg("级别不能为空，请重新输入");
					return false;
				}
				var tostage = $('#tostage').val();
				if(tostage=='' || tostage == null){
					layer.msg("阶段不能为空，请重新输入");
					return false;
				}
				var tomodule = $('#tomodule').val();
				if(tomodule=='' || tomodule == null){
					layer.msg("模块不能为空，请重新输入");
					return false;
				}
				$.post('/exam01/topic_add', {
					toname :form.val('form2').toname,
					tono :form.val('form2').tono,
					tolevel :form.val('form2').tolevel,
					tostage :form.val('form2').tostage,
					tomodule :form.val('form2').tomodule,
					id:form.val('form2').id
				}, function(res) {
					layer.alert(res.msg);
				});
				
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return false;
			});exports('addtopic_oform',{});//js页
});