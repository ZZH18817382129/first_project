<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理界面</title>
<link type="text/css" rel="stylesheet"
	href="/exam01/layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header" style="line-height:60px;">
			<div class="layui-logo">
				<img alt="" src="/exam01/images/xty.png"
					style="width: 150px; height: 100px; margin-top: -40px;">
			</div>
			<span style="font-size: 25px; color: #FFF; margin-left: 50%;">新投云考试系统后台</span>
		</div>
		<div>
			<div class="layui-side layui-bg-black" style="float: left;">
				<div class="layui-side-scroll">
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed"><a
							href="javascript:;">菜单</a> <c:forEach items="${privs}" var="priv"
								varStatus="go">
								<dl class="layui-nav-child">
									<dd class="ddtest" style="height: 40px;" title="${priv.pname}">
										<!-- <div style="display: inline-block;height: 40px;">
													<img style="margin-top: -28px;"  src="/exam01/images/user_manage.png" width="20px;"height="20px;">
												</div> -->
										<a style="line-height: 40px;" href="javascript:;"
											act-url="${priv.url}" lay-id="${go.count}">${priv.pname}</a>
									</dd>
								</dl>
							</c:forEach></li>
					</ul>
				</div>
			</div>
			<div class="layui-tab" style="margin-left: 220px;" lay-filter="show"
				lay-allowclose="true">
				<ul class="layui-tab-title">
				</ul>
				<div class="layui-tab-content" style="height: 600px;"></div>
			</div>
		</div>
	</div>

	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui
				.use(
						[ 'layer', 'form', 'jquery', 'transfer', 'element' ],
						function() {
							var transfer = layui.transfer;
							var layer = layui.layer
							var form = layui.form;
							var $ = layui.$;
							var element = layui.element;

							function setColor() {
								$('.layui-tab-title li')
										.attr('style',
												'background-color:#EEEFFF;border:1px solid;border-color:#CED0D6;');
								$('.layui-this').attr('style',
										'background-color:#FFFFFF;');
								$('.ddtest').attr('style',
										'background-color:#393D49;');
								form.render();
							}
							element.on('tab(show)', function(data) {
								setColor();
							});

							$('.ddtest a')
									.on(
											'click',
											function() {
												var iframes = $('.layui-tab-content iframe');
												var layids = $('.ddtest a');
												var isExits = false;
												for (var i = 0; i < iframes.length; i++) {
													if ($(iframes[i]).attr(
															'frm-id') == $(this)
															.attr('lay-id')) {
														console
																.log($(
																		iframes[i])
																		.attr(
																				'frm-id'));
														isExits = true;
													}
												}
												if (isExits == false) {
													element
															.tabAdd(
																	'show',
																	{
																		title : this.innerText,
																		content : '<iframe frm-id="'
																				+ $(
																						this)
																						.attr(
																								'lay-id')
																				+ '" src="'
																				+ $(
																						this)
																						.attr(
																								'act-url')
																				+ '" frameborder="0" height="900px;" width="100%"></iframe>',
																		id : $(
																				this)
																				.attr(
																						'lay-id')
																	})
												}
												element.tabChange('show', $(
														this).attr('lay-id'));
												isExits = false;
												var all_tabs = $('.layui-tab-title li');
												for (var i = 0; i < all_tabs.length; i++) {
													console.log(all_tabs[i]);
												}
												setColor();
											});
						});
	</script>
</body>
</html>