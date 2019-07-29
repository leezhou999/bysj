<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--必须引入的--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 教师查看学生答辩分组 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>学生答辩分组</title>
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
<script type="text/javascript" src="/bysj/js/admin/dSpeechGroup.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
<script type=text/javascript>
var role_id = "<%=session.getAttribute("role_id")%>"; 
</script>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
   <div data-options="region:'center'" style="width:100%;height:520;padding:5px;background:#eee;">  
	<center>
		<table  id="dg"></table>
	</center>
	</div>
</div>
<!-- 教秘添加分组 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 450; left: 15%; top: 0;">
		<br>
		<center>
			<form id="addform" action="Admin/dSpeechGroupAdd" method="post">	
				
				<span style="margin-left:-5">学生学号: </span>
				<input class="easyui-textbox" id="stu_id" name="stu_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:-7">分组编号:</span>
				<input class="easyui-textbox" id="groupId" name="groupId" style="width: 140px"><br><br>
				<span style="margin-left:-4">答辩时间:</span>
				<input class="easyui-datetimebox" id="time" name="time" style="width: 140px"><br><br>
				<span style="margin-left:-2">答辩地点:</span>
				<input class="easyui-textbox" id="location" name="location" style="width: 140px"><br><br>
				
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="新增分组" />
			</form>
		</center>
	</div>
	<!-- 教秘更新分组信息 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 450; left: 15%; top: 0;">
		<br>
		<center>
			<form id="editform" action="Admin/dSpeechGroupEdit" method="post">	
				
				<span style="margin-left:-5">学生学号: </span>
				<input class="easyui-textbox" id="stu_id1" name="stu_id" readonly="readonly" style="width: 140px" ><br> <br> 
				<span style="margin-left:-7">分组编号:</span>
				<input class="easyui-textbox" id="groupId1" name="groupId" readonly="readonly" style="width: 140px"><br><br>
				<span style="margin-left:-4">答辩时间:</span>
				<input class="easyui-datetimebox" id="time" name="time" style="width: 140px"><br><br>
				<span style="margin-left:-2">答辩地点:</span>
				<input class="easyui-textbox" id="location" name="location" style="width: 140px"><br><br>
				
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
</body>
</html>
