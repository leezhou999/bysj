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
<title>课题查询</title>
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
<script type="text/javascript" src="/bysj/js/student/topic.js"></script>
<script type="text/javascript" src="/bysj/js/student/topicSApply.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
<script type=text/javascript>
var maxTopicId = "<%=session.getAttribute("maxTopicId")%>"; 
</script>
</head>

<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	<div id="find" data-options="region:'north',title:'查询',split:false,collapsed:true" style="height:100px;">
    	<div id="fd">
    	快捷查询：
    	<input type="text" id="findstr" name="findstr" placeholder="请输入指导教师名称">
    	<input type="button" id="btn-find" value="查询 ">
    	<input type="button" id="btn-reset" value="重置">
    	</div>
    </div> 
   <div data-options="region:'center'" style="width:100%;padding:5px;background:#eee;">  
	<center>
		<table id="dg"></table>
	</center>
	</div>
</div>
<!-- 申请自拟课题 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="addform" action="Stu/sApplyTopic2" method="post">	
				<!-- <form id="addform"> -->
				<span style="margin-left:-5">指导教师: </span>
				<input class="easyui-textbox" id="tea_name" name="tea_name" readonly="readonly" style="width: 140px" ><br> <br>  
				<span style="margin-left:-5">课题编号: </span>
				<input class="easyui-textbox" id="topicId" name="topicId" readonly="readonly" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">课题名称:</span>
				<input class="easyui-textbox" id="topicName" name="topicName" style="width: 140px"><br><br>
				<span style="margin-left:-5">课题简介: </span>
				<input class="easyui-textbox" data-options="multiline:true" id="abs" name="abs" style="width: 140px;height:40" ><br> <br> 
				<span style="margin-left:-5">命题者: </span>
				<input class="easyui-textbox" id="user_name" name="user_name" value="${userLoginInfo.user_name}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">命题者编号: </span>
				<input class="easyui-textbox" id="user_id" name="user_id" value="${userLoginInfo.user_id}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">课题绑定学生: </span>
				<input class="easyui-textbox" id="stu_id" name="stu_id" value="${userLoginInfo.user_id}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">课题绑定教师: </span>
				<input class="easyui-textbox" id="tea_id" name="tea_id" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属院系: </span>
				<input class="easyui-textbox" id="dName" name="dName" value="${userInfo.dName}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属专业: </span>
				<input class="easyui-textbox" id="majorName" name="majorName" value="${userInfo.majorName}" readonly="readonly" style="width: 140px" ><br> <br>
				<input type="hidden" id="typeId" name="typeId" value="21">
				<input type="hidden" id="stateId" name="stateId" value="6">
				<input type="hidden" id="chk" name="chk" value="6">
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交申请" />
			</form>
		</center>
	</div>
	
	
</body>
</html>
