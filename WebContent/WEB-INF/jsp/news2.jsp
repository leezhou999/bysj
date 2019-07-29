<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>新闻发布</title>
<script type="text/javascript" src="/bysj/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/bysj/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bysj/js/js_z.js"></script>

<link rel="stylesheet" type="text/css" href="/bysj/css/reset.css" />
<link rel="stylesheet" type="text/css" href="/bysj/css/thems.css">
<link rel="stylesheet" type="text/css" href="/bysj/css/responsive.css">


<!-- 这里是加载UE的js文件 -->
<script src="/bysj/ueditor/ueditor.config.js"></script>
<script src="/bysj/ueditor/ueditor.all.min.js"></script>
<script src="/bysj/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
	<!-- 这里是页面内容 -->
	<div
		style="width: 960px; height: auto; margin-top: 1%; "
		class="banner">
		<span class="label label-danger" style="width: 200px;">公告标题：</span>
		<input type="text" style="width:884" class="form-control" id="title" placeholder="标题">
		

	</div>
	<div
		style="width: 960px; height: 33px; margin-left: 173px; margin-top: 10px;">
		
		<span class="label label-danger" style="width: 200px;">摘要信息：</span>
		<!-- <textarea class="form-control" style="width:884;margin-top:12px;" rows="1"  id="summary"></textarea> -->
		<input type="text" style="width:884" class="form-control" id="abs" placeholder="摘要信息">	<br><br>
		<span class="label label-danger" style="width: 200px;">公告类别：</span>
		<select id="type" class="form-control">
			<option value="0">通知公告</option>
			<option value="1">管理规定</option>
			<option value="2">教务安排</option>
			<option value="3">毕业相关</option>
		</select>
	</div>
	<div
		style="margin-top: 4%; width: 960px; height: 500px; border: 1px solid #e0e0e0;"
		class="banner">

		<script id="editor" type="text/plain"></script>


		<script>
			$(function() {
				//实例化编辑器
				var ue = UE.getEditor('editor', {
					initialFrameWidth : "100%", //初始化宽度
					initialFrameHeight : "100%", //初始化高度 */
				});

				$('#submit').click(function() {
					//获取ueditor编辑框中的html文本内容
					var content = UE.getEditor('editor').getContent();
					var title = $("#title").val();//标题
					var author = '${sessionScope.stuLoginInfo.user_name}';
					var abs = $("#abs").val();//摘要
					/* var datetime = getNowFormatDate(); */
					var type1 = $("#type").val();//类别
					var userid = ${sessionScope.userLoginInfo.user_id};
					var r = confirm("确认发布该消息吗？");
					if (r == true) {
						$.ajax({
							url : 'addNews',
							type : 'POST',
							data : {
								type : type1,
								title : title,
								authorName : author,
								abs : abs,
								content : content,
							},
							dataType : 'json',
							success : function(flag) {
								alert("发布成功，即将跳转新的编辑界面");
								window.location = "toMain";
							},
							error : function(flag) {
								alert("保存失败");
							}
						});
						
					} else {
						alert("你取消了发布！");
					}
				})
			})
		</script>

	</div>

	<div style="margin-top: 10px; text-align: center;">
		<button class="btn btn-primary btn-lg" id="submit">提 交</button>

	</div>
</body>

</html>
