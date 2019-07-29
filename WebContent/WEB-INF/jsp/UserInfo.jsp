<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户个人信息查询</title>
<link type="text/css" rel="stylesheet"
	href="/bysj/js/jquery-easyui-1.5.2/themes/icon.css" />
<link type="text/css" rel="stylesheet"
	href="/bysj/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
<script type="text/javascript"
	src="/bysj/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="/bysj/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/bysj/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<style>
td {
	width: 60;
}

#attr {
	font-weight: bolder;
}

table {
	border-color: #95b8e7;
	border-style: solid;
}
</style>
<body style="background-color: #e4eef9;">
	<center>
		<table border="1" width="450">
			<tr>
				<td id="attr">用户编号:</td>
				<td>${userInfo.user_id}</td>
			</tr>
			<tr>
				<td id="attr">用户姓名:</td>
				<td>${userInfo.user_name}</td>
			</tr>
			<tr>
				<td id="attr">用户性别:</td>
				<td>${userInfo.user_sex}</td>
			</tr>
			<tr>
				<td id="attr">身份证号:</td>
				<td>${userInfo.sfzh}</td>
			</tr>
			<tr>
				<td id="attr">联系地址:</td>
				<td>${userInfo.address}</td>
			</tr>
			<tr>
				<td id="attr">联系电话:</td>
				<td>${userInfo.tel}</td>
			</tr>
			<%-- <tr>
				<td id="attr">职称:</td>
				<td>${userInfo.rankName}</td>
			</tr> --%>
			

		</table>
	</center>
</body>
</html>
