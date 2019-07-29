<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--必须引入的--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 教秘二审学生文档 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>学生文档</title>
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
<script type="text/javascript" src="/bysj/js/admin/dChk.js"></script>
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
<!-- 教秘文档审批 -->
	<div id="dChk" class="easyui-dialog"
		style="width: 420px; height: 450px; left: 15%; top: 40;" title="文档审批">
		<br>
		<center>
			<form id="myform" action="Admin/dDocChk" method="post">
				学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号: <input
					class="easyui-textbox" id="stu_id" name="stu_id" readonly="true"
					style="width: 140px"><br> <br>
				导&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;师:<input
					class="easyui-textbox" id="tea_id" name="tea_id" readonly='true'
					style="width: 140px"><br> <br>
				所属院系: <input class="easyui-textbox" id="deptId" name="deptId"
					readonly="readonly" style="width: 140px"><br><br> 
				所属专业: <input class="easyui-textbox" id="majorId" name="majorId"
					readonly="readonly" style="width: 140px"><br><br> 
				所在班级: <input class="easyui-textbox" id="classesId"
					name="classesId" readonly="true" style="width: 140px"
					><br> <br> 
					<input id="typeId" name="typeId" hidden="true">
				审批意见: <select
					id="stateId" name="stateId" class="easyui-combobox"
					style="width: 140px">
					<option value="" selected="selected">--选择审批意见--</option>
					<option value="3">驳回</option>
					<option value="4">通过</option>
					
				</select><br> <br>
				
				<input type="submit" class="easyui-linkbutton" id="btn-ok"
					value="提交审批" />
			</form>
		</center>
	</div>
</body>
</html>
