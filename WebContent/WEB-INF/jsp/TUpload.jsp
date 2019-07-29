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

<title>教师给给学生上传文件</title>
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
<script type="text/javascript" src="/bysj/js/teacher/tUpload.js"></script>
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
<!-- 教师上传文档 -->
	<div id="fileUpload" class="easyui-dialog"
		style="width: 420px; height: 450px; left: 15%; top: 40;" title="文件上传">
		<br>
		<center>
			<form id="myform" action="Admin/tUpload"
				enctype="multipart/form-data" method="post">
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
				文件类型: <select
					id="typeId" name="typeId" class="easyui-combobox"
					style="width: 140px">
					<option value="" selected="selected">--选择文档类型--</option>
					<option value="01">申报表</option>
					<option value="03">任务书</option>
					<option value="06">指导教师评阅表</option>
					<option value="07">评阅教师评审表</option>
					<option value="09">答辩专家组评审表</option>
					<option value="10">成绩表</option>
				</select><br> <br>
				 上传文件: <input class="easyui-filebox"
					style="width: 140px" id="location" name="location"><br><br> 
				<input type="submit" class="easyui-linkbutton" id="btn-ok"
					value="上传文件" />
			</form>
		</center>
	</div>
</body>
</html>
