<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>指导教师信息查询</title>
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
				<td>${TeaInfo.user_id}</td>
			</tr>
			<tr>
				<td id="attr">用户姓名:</td>
				<td>${TeaInfo.user_name}</td>
			</tr>
			<tr>
				<td id="attr">用户性别:</td>
				<td>${TeaInfo.user_sex}</td>
			</tr>
			<%-- <tr>
				<td id="attr">院系:</td>
				<td>${stuInfo.dName}</td>
			</tr>
			<tr>
				<td id="attr">班级:</td>
				<td>${stuInfo.cName}</td>
			</tr>
			<tr>
				<td id="attr">年级:</td>
				<td>${stuInfo.sDgree}</td>
			</tr>
			<tr>
				<td id="attr">导师:</td>
				<td>${stuInfo.tName}</td>
			</tr>
			<tr>
				<td id="attr">生日:</td>
				<td>${stuInfo.sBirthDate}</td>
			</tr>
			<tr>
				<td id="attr">身份证号码:</td>
				<td>${stuInfo.sIdCard}</td>
			</tr>
			<tr>
				<td id="attr">联系电话:</td>
				<td>${stuInfo.sTel}</td>
			</tr>
			<tr>
				<td id="attr">家庭住址:</td>
				<td>${stuInfo.sAddr}</td>
			</tr> --%>

		</table>
	</center>
</body>
</html>
