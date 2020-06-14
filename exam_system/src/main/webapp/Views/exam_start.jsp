<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>答题页</title>
<link type="text/css" rel="stylesheet" href="/exam01/layui/css/layui.css">
<style type="text/css">
	.stem{
		font-size: 20px;
	}
	.item{
		font-size: 16px;
	}
	.question{
		font-size:18px;
		margin-left: 20px;
		margin-top: 70px;
	}
	.right{
		display: inline;
		font-size: 20px;
		margin-left: 70px;
		width: 300px;
	}
	.item_btn{
		border-radius:5px;
		height: 30px;
		line-height: 30px;
		width:70px;
	}
	.error{
		background-color: #FF5722;;
	}
</style>
</head>
<body >
<div style="margin-top: -50px;margin-left: 20px;">
	<form action="" class="layui-form layui-form-pane" style="width: 1000px;" lay-filter="loginform" method="post" >
	<div id="grades" class="question">
	</div>
	<div class="question">
		<input style="border: 0px;" type="text" value="" id="stem"><br><br><br><br><br><br>
	</div>
	<div class="layui-form-item" style="margin-left: 30px;" id="itemlist"></div>
	<div style="margin-left: 30px;" >
		<input type="text" style="border: 0px solid;" id="ritem" value="">
	</div>
			<input type="hidden" id="now_grades" value="-1">
			<input style="border: 0px;" type="hidden" value="${errorQues}" id="errorQues">
			<input type="hidden" id="now_btn_id" name="now_btn_id" value="1">
			<input type="hidden" id="tpno" value="${exam.tpno}">
			<input type="hidden" id="eno" value="${exam.eno}">
			<input type="hidden" id="uno" value="${user.uno}">
			<input type="hidden" value="" id="error">
				<div class="right" id="commit">
					<div style="margin-left: 460px;margin-top: -300px;margin-bottom: 100px;">
						<div id="show_time"></div><br><br>
						<button class="layui-btn layui-btn-lg" style="background-color:#2F7FFF; border-radius: 5px;height: 40px;line-height: 40px;margin-left: 100px;">
				 	 		交卷
				 	 	</button>
					</div>
				</div>
			
		 	 <div class="layui-form-item" style="margin-top: 50px;">
			 	 	<button id="next_item" class="layui-btn layui-btn-lg layui-btn-normal" type="button" style="border-radius: 5px;height: 30px;line-height: 30px;">
				 	 	上一题
				 	 </button>
				 	 <button id="before_item" class="layui-btn layui-btn-lg layui-btn-normal" type="button" style="border-radius: 5px;height: 30px;line-height: 30px;">
				 	 	下一题
				 	 </button>
		 	 </div>
		 	 <div>
					<c:forEach items="${items}" var="var" varStatus="go">
						<!-- 点击按钮时把id为qno的隐藏表单域的value赋值为该题选项的值,
						提交时获取所有表单域的值,组装成字符串提交到后台处理 -->
						<!-- 考生选择的选项的隐藏表单域 -->
						<input type="hidden" value='${var.key.qno.concat(":").concat("this question is no answer")}' id="${var.key.qno}">
						<!-- 正确答案的隐藏表单域 -->
						<input type="hidden" value="this question is no answer too" id="${go.count+50}">
				 	 	<button  name="ino" value="${var.key.qno}" id="${go.count}" class="layui-btn layui-btn-lg item_btn" style="background-color:#6E6D73;" type="button" >
					 	 	<c:out value="${go.count}"></c:out>
					 	 </button>
						<c:if test="${go.count%10==0}">
							<br><br>
						</c:if>
					</c:forEach>
		 	 </div>
	</form>
</div>
	<script src="/exam01/layui/layui.js"></script>
	<script>
		layui.use(['layer', 'form','jquery'], function(){
		  	var layer = layui.layer
		 	var form = layui.form;
		 	var $ = layui.$;
			//初始化,显示第一道题目的信息
			var qfirst = $('#1').attr('value');
		 	getQuestionMsg(qfirst,1);
		 	setBtnToNoComSel(1);
		 	//从服务器得到考试结束时间
			var sno=$('#uno').val();
			var eno=$('#eno').val();
		 	$.get('/exam01/get_endtime',{
			 	sno:sno,
			 	eno:eno
			 },function(res){
			 	TimeDown('show_time',res.seconds);//显示考试剩余时间
			});
		 	//所有题号按钮的点击事件
			$('.item_btn').on('click',function(btn){
				//更改当前id,调用设置按钮为被选中状态方法
				var qno=btn.currentTarget.attributes["1"].value;
				var before_id = $('#now_btn_id').val();
				var id=btn.currentTarget.attributes[2].value;
				setItem(before_id);
				commit(before_id);
				change_btn_to_beSelected(before_id,id);
				$('#now_btn_id').val(id);
				getQuestionMsg(qno,id);
			});
			//下一题按钮的点击事件			
		 	$('#before_item').on('click',function(){
			 	var now_btn_id = $('#now_btn_id').val();//获取当前按钮的id
			 	var next_id = parseInt(now_btn_id)+1;//获取下一个按钮的id
				if(now_btn_id < 50){//小于50才能继续翻页
				 	var qno=$('#'+next_id)["0"].attributes[1].value;//下一题的题号
				 	setItem(now_btn_id);
				 	commit(now_btn_id);//提交当前考生答案
				 	change_btn_to_beSelected(now_btn_id,next_id);//设置当前按钮和下一题按钮的状态
				 	$('#now_btn_id').val(next_id);
					getQuestionMsg(qno,next_id);//执行切换题目函数获得下一题的题干和选项
				}
			});
			//上一题按钮的点击事件
		 	$('#next_item').on('click',function(){
			 	var now_btn_id = $('#now_btn_id').val();
			 	var next_id = parseInt(now_btn_id)-1;
				if(now_btn_id > 1){
			 		var qno=$('#'+next_id)["0"].attributes[1].value;
				 	setItem(now_btn_id);
				 	commit(now_btn_id);
				 	change_btn_to_beSelected(now_btn_id,next_id);
				 	$('#now_btn_id').val(next_id);
					//执行切换题目函数
					getQuestionMsg(qno,next_id);
				}
			});
			//如果当前试题已作答,插入到数据库中
			function commit(id){
				var qno=$('#'+id)["0"].attributes[1].value;//根据id得到当前题号
				var sitem = $('#'+qno).val().split(":")[1];//得到考生选择的答案
				var sno = $('#uno').val();
				var eno=$('#eno').val();
				if(sitem!=(qno+':'+'this question is no answer too')){//如果考生已选
					$.get('/exam01/commitQue',{//上传考生的选择结果
						qno:qno,
						sno:sno,
						eno:eno,
						sitem:sitem
					});
				}
			}
			//表单提交
			form.on('submit(loginform)', function(data){
				setItem($('#now_btn_id').val());
				var all_items = "";
				var tpno = $('#tpno').val();
				var eno = $('#eno').val();
				var sno = $('#uno').val();
				for (var i = 1;i<=50;i++){
					var qno=$('#'+i)["0"].attributes[1].value;
					all_items+=$('#'+qno).val()+",";
				}
				$.get('/exam01/exam_commit?all_items='+all_items+'&tpno='+tpno+'&eno='+eno+'&sno='+sno,function(res){
					//根据返回的数据判断哪些题目要更改样式
					var btns = $('button[name=ino]');
					var ques = res.errorQues;
					var grades = res.grades;
					//显示成绩函数
					showGrades(grades);
					$('#now_grades').val(grades);
					//对从51到100的50个隐藏表单域,依次得到对应的按钮的qno,
					//如果等于结果集里的某个qno,则该表单域赋值为对应的正确答案
					for(var j=51;j<=100;j++){
						for (var i=0;i<ques.length;i++){
							for(var key in ques[""+i]){
								//key为题号,ques[""+i][key]为正确答案
								var qno = key;
								var ritem = ques[""+i][key];
								//得到题目的题号
								var id=j-50;
								var now_qno=$('#'+id)["0"].attributes[1].value;
								var sitem = $('#'+qno).val().split(":")[1];
						        if(qno==now_qno){
							    	    $('#'+j).val("正确答案:"+ritem);
							    	   // 如果ritem和题号为id对应的隐藏表单域的value不同,则说明此题错误,更改按钮样式
							    	   if (ritem!=sitem){
									    	//更改按钮样式方法
							    			setBtnToError(id);
										}else{
											setBtnToRight(id);
										}
							    }
							}
						}
					}
					//隐藏交卷按钮和考试剩余时间,增加退出按钮,跳转到exam_index.jsp页面并且该考试不能再进入
					$('#commit').attr('style','visibility:hidden;');
					getQuestionMsg($('#1').val(),1)
					setBtnToNoComSel(1);
					$('#now_btn_id').val(1);
				});
				return false;
			});
		 	//发送请求函数,传入题号qno发送一个请求,改变当前题目信息
			function getQuestionMsg(qno,id){
				//根据题号获得题干和选项信息并设置到界面上
				//change_btn_to_beSelected(id);
				showErrorQuesRitems(id);
				$.get('/exam01/getQueStem?qno='+qno+'&now_id='+id,function(res){
					//获得当前选择的选项值
					$('#stem').val(res.stem);
					var all_items = res.tp_items;
					//循环打印选项,如果该题已经作答,把对应的选项设置为被选中状态
					var grades = $('#now_grades').val();
					if(grades>=0){
						var in_put = '<input class="ir" type="radio" disabled="disabled" name="que" value="';
					}else{
						var in_put = '<input class="ir" type="radio" name="que" value="';
					}
					var temp = '';
					for(var i = 0; i < 4; i++){
						var it = all_items[i].item;
						var itemp = $('#'+qno).val();
						var strs = new Array();
						strs = itemp.split(":");
						var s1 = strs[1];
						//找到考生刚才的选项并设为选中状态
						//未实现:如果成绩大于等于0则全部按钮设置为不可更改
						if(s1==it){
								temp+=in_put+all_items[i].item+'" checked>'+all_items[i].item+'<br>';
						} else{
							temp+=in_put+all_items[i].item+'">'+all_items[i].item+'<br>';
						}
					}
					$('#itemlist').html(temp);
					form.render();
				});
			}
			//显示错题答案方法
			function showErrorQuesRitems(id){
				var rid = parseInt(id)+50;
				var ritem = $('#'+rid).attr('value');
				if(ritem!='this question is no answer too'){
					$('#ritem').val(ritem);
				} else{
					$('#ritem').val('');
				}
			}
			//为id为qno的隐藏表单域赋值
			function setItem(id){
				//找到处于被选中状态的按钮,拿到它的value
				var qno=$('#'+id)["0"].attributes[1].value;
				var item = $("input[name=que]:checked").val();
				if(item!=undefined){
					$('#'+qno).val(qno+":"+item);
				}
			}
			//时间倒计时
			function TimeDown(id,totalSeconds) {
			    var modulo = totalSeconds % (60 * 60 * 24);
			    var hours = Math.floor(modulo / (60 * 60));
			    modulo = modulo % (60 * 60);
			    var minutes = Math.floor(modulo / 60);
			    var seconds = modulo % 60;
			  	$('#'+id).html("距离考试结束还剩:" +hours + "小时" + minutes + "分钟" + seconds + "秒");
			    setTimeout(function () {
				    if(totalSeconds==1){
						//调用提交表单方法    
					}
			        TimeDown(id,totalSeconds-1);
			    }, 1000)
			}
			//显示成绩函数
			function showGrades(grades){
				var temp = '<h2>你本次考试成绩为'+grades+'分</h2>';
				$('#grades').html(temp);
			}
			//更改按钮样式为错误样式
			function setBtnToError(id){
				$('#'+id).attr('style','background-color:#f74b07;');
			}
			//更改按钮样式为正确样式
			function setBtnToRight(id){
				$('#'+id).attr('style','background-color:#1E9FFF;');
			}
			//更改按钮样式为已做样式
			function setBtnToHaveDone(id){
				$('#'+id).attr('style','background-color:#1E9FFF;');
			}
			//更改按钮样式为未做样式
			function setBtnToNoDone(id){
				$('#'+id).attr('style','background-color:#6E6D73;');
			}
			//更改按钮样式为已交卷被选中样式
			function setBtnToHaveComSel(id){
				$('#'+id).attr('style','background-color:#96B97D;');
			}
			//更改按钮样式为未交卷被选中样式
			function setBtnToNoComSel(id){
				$('#'+id).attr('style','background-color:#96B97D;');
			}
			//切换题目函数,传入一个id,把该id对应的按钮的样式改变为被选中状态
		 	function change_btn_to_beSelected(before_id,now_id){
			 	var grades = $('#now_grades').val();
			 	//判断上一个按钮的状态为已交卷时
			 	if(grades>=0){
				 	if(checkIsRight(grades,before_id)==2){
				 		setBtnToRight(before_id);
					}else if(checkIsRight(grades,before_id)==3){
						setBtnToError(before_id);
					}
				 	setBtnToHaveComSel(now_id);
				}else{
					//判断上一个按钮的状态为未交卷时
					if(checkIsRight(grades,before_id)==0){
						setBtnToNoDone(before_id);
					}else if(checkIsRight(grades,before_id)==1){
						setBtnToHaveDone(before_id);
					}
				 	setBtnToNoComSel(now_id);
				}
			}
		 	//判断该题状态:0为未作答,1为已作答,2为答案正确,3为答案错误
			function checkIsRight(grades,id){
				var qno=$('#'+id)["0"].attributes[1].value;
				var temp = $('#'+qno).val();
				var strs = new Array();
				strs = temp.split(":");
				var sitem = strs[1];
				var ritem = $('#'+(parseInt(id)+50)).val().split(":")[1];
				if(grades>=0){
					if (sitem==ritem){
						return 2;
					}
					return 3;
				}else{
					if(sitem=='this question is no answer'){
						return 0;
					} 
					return 1;
				}
			}
		});
	</script>
</body>
</html>