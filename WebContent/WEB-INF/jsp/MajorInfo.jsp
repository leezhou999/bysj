<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--必须引入的--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>专业信息查询</title>
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
	<script type="text/javascript" src="/bysj/js/admin/majorInfo.js"></script>
<script type="text/javascript" src="/bysj/js/admin/majorInfoAdd.js"></script>
<script type="text/javascript" src="/bysj/js/admin/majorInfoEdit.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	
   <div data-options="region:'center'" style="width:100%;padding:5px;background:#eee;">  
	<center>
		<table id="dg"></table>
	</center>
	</div>
</div>
<!-- 添加 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="addform" action="Admin/majorInfoAdd" method="post">
				专业编号: <input
					class="easyui-textbox" id="majorId" name="majorId" style="width: 140px" ><br> <br> 
				专业名称:<input class="easyui-textbox" id="majorName" name="majorName" style="width: 140px"><br><br>
				 所属院系: <select class="easyui-combobox" id="deptId" name="deptId"style="width: 140px">
				 		<option value="">--请选择院系信息--</option>   
				 		<option value="10">计算机学院</option>   
    					
    					</select> 
				<br><br>
					   <input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 编辑 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="editform" action="Admin/majorInfoEdit" method="post">
				专业编号: <input
					class="easyui-textbox" id="majorId" name="majorId" style="width: 140px" ><br> <br> 
				专业名称:<input class="easyui-textbox" id="majorName" name="majorName" style="width: 140px"><br><br>
				 所属院系: <select class="easyui-combobox" id="deptId" name="deptId"style="width: 140px">
				 		<option value="">--请选择院系信息--</option>   
				 		<option value="10">计算机学院</option>   
    					
    					</select>  
				<br><br>
					   <input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
</body>
</html>
