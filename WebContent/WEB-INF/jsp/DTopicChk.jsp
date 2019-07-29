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
<title>教秘审查院系课题</title>
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
<script type="text/javascript" src="/bysj/js/admin/dSTATopicChk.js"></script>
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
	<!-- 教秘审批教师课题 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="editform" action="Admin/dChk" method="post">	
				
				<span style="margin-left:-5">课题编号: </span>
				<input class="easyui-textbox" id="topicId" name="topicId" readonly="readonly" style="width: 140px"><br> <br> 
				<span style="margin-left:11">课题名称:</span>
				<input class="easyui-textbox" id="topicName" name="topicName" readonly="readonly" style="width: 140px"><br><br>
				<span style="margin-left:-5">课题简介: </span>
				<input class="easyui-textbox" data-options="multiline:true" id="abs" name="abs" readonly="readonly" style="width: 140px;height:40" ><br> <br> 
				<span style="margin-left:-5">命题者: </span>
				<input class="easyui-textbox" id="user_name" name="user_name" value="${userLoginInfo.user_name}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">命题者编号: </span>
				<input class="easyui-textbox" id="user_id" name="user_id" value="${userLoginInfo.user_id}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">课题绑定教师: </span>
				<input class="easyui-textbox" id="tea_id" name="tea_id" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属院系: </span>
				<input class="easyui-textbox" id="dName" name="dName" value="${userInfo.dName}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属专业: </span>
				<input class="easyui-textbox" id="majorName" name="majorName" value="${userInfo.majorName}" readonly="readonly" style="width: 140px" ><br> <br>
				
				<input type="hidden" id="typeId" name="typeId">
				<!-- <input type="hidden" id="chk" name="chk"> -->
				<span style="margin-left:-5">选题审批: </span>
				<select class="easyui-combobox" id="stateId" name="stateId"style="width: 140px">
				 		<option value="0">未审核</option>
				 		<option value="1">驳回课题</option>   
    					<option value="2">批准课题</option>
    					<option value="7">退回修改</option> 
    					</select> 
				 	<br><br> 
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 教秘审批学生课题 -->
	<div id="edit2" class="easyui-dialog"
		style="width: 400; height: 585; left: 15%; top: 0;">
		<br>
		<center>
			<form id="editform2" action="Admin/dChk" method="post">	
				
				<span style="margin-left:-5">课题编号: </span>
				<input class="easyui-textbox" id="topicId" name="topicId" readonly="readonly" style="width: 140px"><br> <br> 
				<span style="margin-left:11">课题名称:</span>
				<input class="easyui-textbox" id="topicName" name="topicName" readonly="readonly" style="width: 140px"><br><br>
				<span style="margin-left:-5">课题简介: </span>
				<input class="easyui-textbox" data-options="multiline:true" id="abs" name="abs" readonly="readonly" style="width: 140px;height:40" ><br> <br> 
				<span style="margin-left:-5">命题者: </span>
				<input class="easyui-textbox" id="user_name" name="user_name" value="${userLoginInfo.user_name}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">命题者编号: </span>
				<input class="easyui-textbox" id="user_id" name="user_id" value="${userLoginInfo.user_id}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">课题绑定教师: </span>
				<input class="easyui-textbox" id="tea_id" name="tea_id" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属院系: </span>
				<input class="easyui-textbox" id="dName" name="dName" value="${userInfo.dName}" readonly="readonly" style="width: 140px" ><br> <br>
				<span style="margin-left:-5">所属专业: </span>
				<input class="easyui-textbox" id="majorName" name="majorName" value="${userInfo.majorName}" readonly="readonly" style="width: 140px" ><br> <br>
				
				<input type="hidden" id="typeId" name="typeId">
				<!-- 教秘只审批自拟课题，不审批师生选题关系，chk默认置2 -->
				<!-- <input type="hidden" id="chk" name="chk" value="2"> -->
				<span style="margin-left:-5">选题审批: </span>
				<select class="easyui-combobox" id="stateId" name="stateId"style="width: 140px">
				 		
				 		<option value="3">驳回课题</option>   
    					<option value="4">批准课题</option>
    					</select> 
				 	<br><br> 
    			<br><br>
						
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
</body>
</html>
