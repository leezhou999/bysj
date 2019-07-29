<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 引入easyui（样式）的包，还有jqery的包 -->
<link type="text/css" rel="stylesheet" href="/bysj/js/jquery-easyui-1.5.2/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="/bysj/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
<script type="text/javascript" src="/bysj/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="/bysj/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/bysj/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/bysj/js/title.js"></script>
<link rel="stylesheet" type="text/css" href="/bysj/css/Main.css" />
<title>毕业设计管理平台</title>
<script type="text/javascript">
  		//显示时间
  		var tname;
	   function showRealTime(clock){
			var d = new Date();
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			var date = d.getDate();
			var days = new Array("日","一","二","三","四","五","六");
			var day = d.getDay();
			var hour = (d.getHours() < 10) ? ("0" + d.getHours()) : d.getHours();
			var min = (d.getMinutes() < 10) ? ("0" + d.getMinutes()) : d.getMinutes();
			var sec = (d.getSeconds() < 10) ? ("0" + d.getSeconds()) : d.getSeconds();
			var now = year + "年" + month + "月" + date + "日&nbsp;&nbsp;星期" + days[day] + "&nbsp;&nbsp;" + hour + ":" + min + ":" + sec;
			clock.innerHTML = "当前时间:" + now;
		}
		function loadClock(){
			window.setInterval('showRealTime(clock)',1000);
		}

	</script>

</head>

<body onload="loadClock()">
	<div id="cc" class="easyui-layout"
		style="width: 100%; height: 100%; margin: auto;">
		<!-- 顶部banner -->
		<div id='north' data-options="region:'north',split:true"
			style="border:0;height: 6%; width: 100%;">
			
			<div id="clock" style="font-size:15px;position:absolute;left:12%;"></div>
			<div style="height:100%;width:500px;position:absolute;top:-10px;left:0px;bottom:0px;">
			<img src="images/logo4.png" height="50" onclick="javascript:location.href='toLogin'">
			</div>
			<div id="head1" style="height:100%;width:500px;position:absolute;top:0px;left:73%;bottom:0px;">
				<span id="userCenter">欢迎用户:${userLoginInfo.user_name}</a>&nbsp;&nbsp;用户类型:${role.role_name}&nbsp;&nbsp;</span><a href="exitLogin">退出</a>
			</div>
		</div>
		<!-- 底部 -->
		<div id='south' data-options="region:'south',split:true"
			style="height: 6%; width: 100%;">
			<br>
			<center>
				&copy;2019&nbsp;<a href="https://blog.csdn.net/qq_35206244">版权信息</a>
				&nbsp;<a href="https://blog.csdn.net/qq_35206244">意见反馈</a>
				<a href="https://blog.csdn.net/qq_35206244">联系作者</a>
			</center>
		</div>
		<!-- 左侧导航栏 -->
		<div data-options="region:'west',title:'页面导航',split:false"style="width:12%;">
			<div id="aa" class="easyui-accordion" style="height:500px !important;height:593!important;">
				<div title="菜单导航" style="overflow: auto; padding: 10px;">
					<ul id="tree" class="easyui-tree">
					</ul>
				</div>
			</div>
		</div>

		<!-- 中间 -->
		<div data-options="region:'center'"
			style="background:url(images/nav1.jpg); background-size:100% 100%;width: 80%;">

			<div id="tt" class="easyui-tabs" data-options="fit:true,pill:true"
				style="width: 100%; height: 517px;">
			</div>

		</div>
	</div>
</body>
</html>
