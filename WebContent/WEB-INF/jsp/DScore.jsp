<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--必须引入的--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 教秘、院长查看本院学生成绩 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>教秘、院长查看本院学生成绩</title>
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
<script type="text/javascript" src="/bysj/js/admin/dScore.js"></script>
<script type="text/javascript" src="/bysj/js/admin/dScoreEdit.js"></script>
<script type="text/javascript" src="/bysj/js/admin/dScoreAdd.js"></script>
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
<!-- 教秘录入成绩 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="addform" action="Admin/scoreAdd" method="post">	
				
				<span style="margin-left:-5">学生学号: </span>
				<input class="easyui-textbox" id="stu_id" name="stu_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:-52">指导教师评阅成绩:</span>
				<input class="easyui-textbox" id="score1" name="score1" style="width: 140px"><br><br>
				<span style="margin-left:-49">评阅教师评审成绩:</span>
				<input class="easyui-textbox" id="score2" name="score2" style="width: 140px"><br><br>
				<span style="margin-left:-1">答辩成绩:</span>
				<input class="easyui-textbox" id="score3" name="score3" style="width: 140px"><br><br>
				<span style="margin-left:17">备注: </span>
				<input class="easyui-textbox" id="remark" name="remark" data-options="multiline:true"  value="暂无" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">成绩类型: </span>
				<select class="easyui-combobox" id="scoreType" name="scoreType" style="width: 140px;">
				 		<option value="11" selected="selected">一辩</option>
				 		<option value="12">二辩</option> 
    					</select> 
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="录入成绩" />
			</form>
		</center>
	</div>
	<!-- 教秘更新成绩 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="editform" action="Admin/scoreEdit" method="post">	
				
				<span style="margin-left:-5">学生学号: </span>
				<input class="easyui-textbox" id="stu_id1" name="stu_id" readonly="readonly" style="width: 140px" ><br> <br> 
				<span style="margin-left:-5">学生姓名:</span>
				<input class="easyui-textbox" id="user_name" name="user_name" readonly="readonly" style="width: 140px"><br><br>
				
				<span style="margin-left:-5">所属院系: </span>
				<input class="easyui-textbox" id="dName" name="dName"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属专业: </span>
				<input class="easyui-textbox" id="majorName" name="majorName"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属班级: </span>
				<input class="easyui-textbox" id="cName" name="cName"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">课题名称: </span>
				<input class="easyui-textbox" id="topicName" name="topicName" data-options="multiline:true"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">指导教师: </span>
				<input class="easyui-textbox" id="tea_name" name="tea_name"  readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-52">指导教师评阅成绩:</span>
				<input class="easyui-textbox" id="score1" name="score1" style="width: 140px"><br><br>
				<span style="margin-left:-49">评阅教师评审成绩:</span>
				<input class="easyui-textbox" id="score2" name="score2" style="width: 140px"><br><br>
				<span style="margin-left:-1">答辩成绩:</span>
				<input class="easyui-textbox" id="score3" name="score3" style="width: 140px"><br><br>
				<span style="margin-left:17">备注: </span>
				<input class="easyui-textbox" id="remark" name="remark" data-options="multiline:true"  value="暂无" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">成绩类型: </span>
				<select class="easyui-combobox" id="scoreType" name="scoreType" style="width: 140px;">
				 		<option value="11" selected="selected">一辩</option>
				 		<option value="12">二辩</option> 
    					</select> 
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
</body>
</html>
