<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户信息编辑</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript"
	src="/bysj/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="/bysj/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/bysj/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="/bysj/js/jquery-easyui-1.5.2/themes/icon.css" />
<link type="text/css" rel="stylesheet"
	href="/bysj/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
	<script type="text/javascript"
	src="/bysj/js/userInfoEdit.js"></script>
</head>
<body style="background-color: #e4eef9;">
	<div id="userInfoEdit" class="easyui-dialog"
		style="width: 420px; height: 450px; left: 15%; top: 40;" title="用户信息编辑">
		<br>
		<center>
			<form id="myform" action="userInfoEdit"method="post">
				用户编号: <input
					class="easyui-textbox" id="user_id" name="user_id" readonly="readonly"
					style="width: 140px" value="${userInfo.user_id}"><br> <br>
				用户姓名:<input
					class="easyui-textbox" id="user_name" name="user_name" readonly="readonly"
					style="width: 140px" value="${userInfo.user_name}"><br> <br>
				用户性别: <input class="easyui-textbox" id="user_sex" name="user_sex" readonly="readonly"
					 style="width: 140px" value="${userInfo.user_sex}"><br><br> 
				身份证号: <input class="easyui-textbox" id="sfzh" name="sfzh"
					 style="width: 140px" value="${userInfo.sfzh}"><br><br> 
				联系地址: <input class="easyui-textbox" id="address" name="address"
					 style="width: 140px" value="${userInfo.address}"><br><br> 
				联系电话: <input class="easyui-textbox" id="tel" name="tel"
					 style="width: 140px" value="${userInfo.tel}"><br><br> 
				<input type="submit" class="easyui-linkbutton" id="btn-ok"
					value="提交更改" />
			</form>
		</center>
	</div>
</body>
</html>
