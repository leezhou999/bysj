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
<title>毕业设计进度表</title>
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
	<script type="text/javascript" src="/bysj/js/process.js"></script>
<script type="text/javascript" src="/bysj/js/processAdd.js"></script>
<script type="text/javascript" src="/bysj/js/processEdit.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>

<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	<div id="find" data-options="region:'north',title:'查询',split:false,collapsed:true" style="height:100px;">
    	<div id="fd">
    	快捷查询：
    	<input type="text" id="findstr" name="remark" placeholder="请输入流程详情关键字">
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
<!-- 添加 -->
	<div id="add" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="addform" action="processAdd" method="post">
				<span style="margin-left:-5">流程编号: </span>
				<input class="easyui-textbox" id="process_id" name="process_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">相关用户:</span>
				<input class="easyui-textbox" id="user_name" name="user_name" style="width: 140px"><br><br>
				<span style="margin-left:-5">用户操作: </span>
				<input class="easyui-textbox" data-options="multiline:true" id="operate" name="operate" style="width: 140px;height:40" ><br> <br> 
				<span style="margin-left:11">流程状态:</span>
				<select class="easyui-combobox" id="state" name="state"style="width: 140px">
				 		<option value="未设置">--请选择流程状态--</option>
				 		<option value="未完成">未完成</option>
				 		<option value="已完成">已完成</option>   
    			</select> <br><br>
				<span style="float:left;margin-left:96">流程详情:</span><br>
				<input class="easyui-textbox" data-options="multiline:true" id="state" name="remark" style="width: 300px;height:200px;"><br><br>
				
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 编辑 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="editform" action="processEdit" method="post">
				<span style="margin-left:-5">流程编号: </span>
				<input class="easyui-textbox" id="process_id" name="process_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">相关用户:</span>
				<input class="easyui-textbox" id="user_name" name="user_name" style="width: 140px"><br><br>
				<span style="margin-left:-5">用户操作: </span>
				<input class="easyui-textbox" id="operate" data-options="multiline:true" name="operate" style="width: 140px;height:40" ><br> <br> 
				<span style="margin-left:11">流程状态:</span>
				<select class="easyui-combobox" id="state" name="state"style="width: 140px">
				 		<option value="未设置">--请选择流程状态--</option>
				 		<option value="未完成">未完成</option>
				 		<option value="已完成">已完成</option>   
    			</select><br><br>
				<span style="float:left;margin-left:96">流程详情:</span><br>
				<input class="easyui-textbox" id="state" data-options="multiline:true" name="remark" style="width: 300px;height:200px;"><br><br>
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	
</body>
</html>
