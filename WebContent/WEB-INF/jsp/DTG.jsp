<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--必须引入的--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 教秘、院长查看专家组答辩分组 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>专家组分组</title>
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
<script type="text/javascript" src="/bysj/js/admin/dTG.js"></script>
<script type=text/javascript>
var role_id = "<%=session.getAttribute("role_id")%>"; 
</script>
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
<!-- 添加答辩专家组 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="addform" action="Admin/dTGAdd" method="post">	
				
				<span style="margin-left:-5">组编号: </span>
				<input class="easyui-textbox" id="groupId" name="groupId" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">组名称:</span>
				<input class="easyui-textbox" id="groupName" name="groupName" data-options="multiline:true" style="width: 140px"><br><br>
				<span style="margin-left:-5">教师工号: </span>
				<input class="easyui-textbox" id="tea_id" name="tea_id"  style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属院系:</span>
				<select class="easyui-combobox" id="deptId" name="deptId"style="width: 140px">
				 		<option value="10" selected="selected">计算机学院</option>   
    			</select> 
				 	<br><br> 
				 <span style="margin-left:-5">是否组长:</span>
				<select class="easyui-combobox" id="isHeadMan" name="isHeadMan"style="width: 140px">
				 		<option value="1">是</option> 
				 		<option value="0" selected="selected">否</option>   
    			</select> 
				<br><br>
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交信息" />
			</form>
		</center>
	</div>
	<!-- 更新答辩专家组 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="editform" action="Admin/dTGEdit" method="post">	
				
				<span style="margin-left:-5">组编号: </span>
				<input class="easyui-textbox" id="groupId" name="groupId" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">组名称:</span>
				<input class="easyui-textbox" id="groupName" name="groupName" data-options="multiline:true" style="width: 140px"><br><br>
				<span style="margin-left:-5">教师工号: </span>
				<input class="easyui-textbox" id="tea_id" name="tea_id"  style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属院系:</span>
				<select class="easyui-combobox" id="deptId" name="deptId" readonly="readonly" style="width: 140px">
				 		<option value="10" selected="selected">计算机学院</option>   
    			</select> 
    			
				 	<br><br> 
				 	<span style="margin-left:-5">是否组长:</span>
				<select class="easyui-combobox" id="isHeadMan" name="isHeadMan"style="width: 140px">
				 		<option value="1">是</option> 
				 		<option value="0" selected="selected">否</option>   
    			</select> 
				<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
</body>
</html>
