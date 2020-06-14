/**
 * 
 */
/**
 * 
 */
layui.define( ['element','form','jquery','layer','table'],
				function(exports) {
					var element = layui.element;
					var form = layui.form;
					var $ = layui.jquery;
					var layer = layui.layer;
					var table = layui.table;
					table.render({
						elem : '#order',
						url : '/exam01/role_lists',
						page : true,//分页
						toolbar : '#toolbar',	
						defaultToolbar : [ 'filter', 'print', 'exports' ],
						cols : [ [ {
							field : 'id',
							type : 'checkbox'
						}, {
							field : 'ridentity',
							title : '角色名'
						}, {
							fixed : 'right',
							align:'center',
							title : '操作',
							toolbar : '#bar',
							width : 250
						} ] ]
					});
					//工具栏事件处理
					table.on('toolbar(order)', function(btn) {//table.on事件监听，按钮事件,表单验证
						var eve = btn.event;
						switch (eve) {
						case 'addRole':
							if (eve == 'addRole') {
								layer.open({
									type : 2,
									title : '新增角色',
									closeBtn : 1,
									area:['450px','200px'],
									offset:'150px',
									maxmin : true,
									btn : false,
									content : [ '/exam01/forward_to_addrole', 'no' ],
									end : function() {
										table.reload('order');
									}
								});
							}
							break;
						case 'dellots':
							layer.confirm("删除", {//layer-confirm 确认框
								icon : 3,
								offset:150,
								tiltle : '询问'
							}, function(win){
								var checkStatus = table.checkStatus('order');//checkStatus获取选中行
								if (!checkStatus.data.length > 0) {
									layer.msg('请至少选择一行数据');
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
								$.post('/exam01/role_del', {
									id : id
								}, function(res) {
									layer.msg(res.msg);
								});
								table.reload('order');//加载
							}, function() {

							});
							break;
						case 'give_roles':
							layer.open({
								type : 2,
								title : '修改角色权限信息',
								closeBtn : 1,
								area : [ '550px', '650px' ],
								offset:'100px',
								maxmin : true,
								btn : false,
								content : [ '/exam01/forward_to_give_priv', 'no' ],
								end : function() {
									table.reload('order');
								}
							});
							break;
						};
					});
					table.on('tool(order)', function(btn) {
						var eve = btn.event;
						switch (eve) {
						case 'edit':
							layer.open({
								type: 2,
								title:'编辑角色',
								offset:100,
								closeBtn:1,//不显示关闭按钮
								area:['480px','200px'],
								maxmin:true,//开启最大化最小化按钮
								btn:false,
								content:['/exam01/role_upd?id='+btn.data.id,'no'],
								end:function(res){
									table.reload('order');
								}
							});
							break;
						case 'disable':
							layer.confirm('确定禁用该角色吗？',{
								icon:0,
								offset:150,
								title:'询问'
							},function(win){
								layer.close();
								var id = btn.data.id;
								$.get('/exam01/role_disable?rid='+btn.data.id,function(res){
									layer.msg(res.msg);
								});
								table.reload('order');//加载
							}, function(){
							});
							break;
						case 'able':
								layer.close();
								var id = btn.data.id;
								$.get('/exam01/role_able?rid='+btn.data.id,function(res){
									layer.msg(res.msg);
								});
								table.reload('order');
							break;
						case 'del':
							layer.confirm('确定删除当前数据？',{offset:150,icon:0,title:'询问'},function(win){
								layer.close();
								var id = btn.data.id;
								$.get('/exam01/role_del?id='+id,function(res){
									layer.msg(res.msg);
								});
								table.reload('order');//加载
							}, function(res){
								layer.msg(res.msg);
							});
							break;
						};
					});
					exports('role_js',{});//js页面
});