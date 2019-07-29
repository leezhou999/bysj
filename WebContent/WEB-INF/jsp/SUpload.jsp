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

<title>学生上传文件</title>
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
<script type="text/javascript" src="/bysj/js/student/sUpload.js"></script>
</head>

<body style="background-color: #e4eef9;">
	<div id="fileUpload" class="easyui-dialog"
		style="width: 420px; height: 450px; left: 15%; top: 40;" title="文件上传">
		<br>
		<center>
			<form id="myform" action="Stu/sUpload"
				enctype="multipart/form-data" method="post">
				学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号: <input
					class="easyui-textbox" id="stu_id" name="stu_id" readonly="true"
					style="width: 140px" value="${userInfo.user_id}"><br> <br>
				导&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;师:<input
					class="easyui-textbox" id="tea_id" name="tea_id" readonly='true'
					style="width: 140px" value="${teaInfo.user_name}"><br> <br>
				所属院系: <input class="easyui-textbox" id="deptId" name="deptId"
					readonly="readonly" style="width: 140px" value="${userInfo.dName}"><br><br> 
				所属专业: <input class="easyui-textbox" id="majorId" name="majorId"
					readonly="readonly" style="width: 140px" value="${userInfo.majorName}"><br><br> 
				所在班级: <input class="easyui-textbox" id="classesId"
					name="classesId" readonly="true" style="width: 140px"
					value="${userInfo.cName}"><br> <br> 
				文件类型: <select
					id="typeId" name="typeId" class="easyui-combobox"
					style="width: 140px">
					<option value="" selected="selected">--选择文档类型--</option>
					<option value="02">选题表</option>
					<option value="04">开题报告</option>
					<option value="05">指导记录表</option>
					<option value="08">答辩申请表</option>
					<option value="99">毕业论文</option>
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
