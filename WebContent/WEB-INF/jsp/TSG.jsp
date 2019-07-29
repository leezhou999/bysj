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
<script type="text/javascript" src="/bysj/js/teacher/tSG.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
   <div data-options="region:'center'" style="width:100%;height:520;padding:5px;background:#eee;">  
	<center>
		<table  id="dg"></table>
	</center>
	</div>
</div>
<!-- 答辩审批 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="editform" action="Admin/sApplyChk" method="post">	
				<span style="margin-left:-5">学生学号: </span>
				<input class="easyui-textbox" id="user_id" name="user_id" readonly="readonly"  style="width: 140px" ><br> <br>  
				<span style="margin-left:-5">学生姓名: </span>
				<input class="easyui-textbox" id="user_name" name="user_name" readonly="readonly"  style="width: 140px" ><br> <br> 
				<span style="margin-left:11">学生性别:</span>
				<input class="easyui-textbox" id="user_sex" name="user_sex" readonly="readonly"  style="width: 140px"><br><br>
				<span style="margin-left:-5">所属院系: </span>
				<input class="easyui-textbox" id="dName" name="dName"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属专业: </span>
				<input class="easyui-textbox" id="majorName" name="majorName"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属班级: </span>
				<input class="easyui-textbox" id="cName" name="cName"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">联系地址: </span>
				<input class="easyui-textbox" id="address" name="address"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">联系电话: </span>
				<input class="easyui-textbox" id="tel" name="tel"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:11">课题名称:</span>
				<input class="easyui-textbox" id="topicName" name="topicName" readonly="readonly" style="width: 140px"><br><br>
				<span style="margin-left:-5">答辩审批: </span>
				<select class="easyui-combobox" id="apply" name="apply" style="width: 140px;">
				 		<option value="2" selected="selected">同意答辩</option>
				 		<option value="1">拒绝答辩</option> 
    					</select> 
				 	<br><br> 
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交申请" />
			</form>
		</center>
	</div>
</body>
</html>
