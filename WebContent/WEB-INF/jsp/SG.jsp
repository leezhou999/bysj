<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--必须引入的--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 学生答辩分组 -->
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
<script type="text/javascript" src="/bysj/js/student/sG.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
<script type="text/javascript">
var stu_id = "<%=session.getAttribute("stu_id")%>"; 
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
<!-- 申请答辩 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="addform" action="Stu/sApply" method="post">	
				<!-- <form id="addform"> -->
				<span style="margin-left:-5">指导教师: </span>
				<input class="easyui-textbox" id="tea_name" name="tea_name" readonly="readonly" value="${topicInfo.user_name}" style="width: 140px" ><br> <br>  
				<span style="margin-left:-5">课题编号: </span>
				<input class="easyui-textbox" id="topicId" name="topicId" readonly="readonly" value="${topicInfo.topicId}" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">课题名称:</span>
				<input class="easyui-textbox" id="topicName" name="topicName" readonly="readonly" value="${topicInfo.topicName}" style="width: 140px"><br><br>
				<span style="margin-left:-5">课题简介: </span>
				<input class="easyui-textbox" data-options="multiline:true" id="abs" name="abs" value="${topicInfo.abs}" readonly="readonly" style="width: 140px;height:40" ><br> <br> 
				<span style="margin-left:-5">课题绑定学生: </span>
				<input class="easyui-textbox" id="stu_id" name="stu_id" value="${userLoginInfo.user_id}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">课题绑定教师: </span>
				<input class="easyui-textbox" id="tea_id" name="tea_id" value="${topicInfo.tea_id}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属院系: </span>
				<input class="easyui-textbox" id="dName" name="dName" value="${userInfo.dName}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属专业: </span>
				<input class="easyui-textbox" id="majorName" name="majorName" value="${userInfo.majorName}" readonly="readonly" style="width: 140px" ><br> <br>
				<input type="hidden" id="apply" name="apply" value="6">
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交申请" />
			</form>
		</center>
	</div>
</body>
</html>
