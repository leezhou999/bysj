<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>角色管理</title>
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
<script type="text/javascript" src="/bysj/js/teacher/role.js"></script>
<script type="text/javascript" src="/bysj/js/teacher/roleAdd.js"></script>
<script type="text/javascript" src="/bysj/js/teacher/roleEdit.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	<div id="find" data-options="region:'north',title:'查询',split:false,collapsed:true" style="height:100px;">
    	<div id="fd">
    	角色名：
    	<input type="text" id="findstr" name="role_name" placeholder="请输入角色名">
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
			<form id="addform" action="Admin/roleAdd" method="post">
				<span style="margin-left:-5">角色编号: </span>
				<input class="easyui-textbox" id="role_id" name="role_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">角色名:</span>
				<input class="easyui-textbox" id="role_name" name="role_name" style="width: 140px"><br><br>
				<span style="margin-left:-5">备注:</span>
				<input class="easyui-textbox" id="role_remark" name="role_remark" value="" style="width: 140px" ><br><br>
				
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 编辑 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="editform" action="Admin/roleEdit" method="post">
				<span style="margin-left:-5">角色编号: </span><input
					class="easyui-textbox" id="role_id" name="role_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">角色名:</span><input class="easyui-textbox" id="role_name" name="role_name" style="width: 140px"><br><br>
				<span style="margin-left:-5">备注:</span><input class="easyui-textbox" id="role_remark" name="role_remark" value="" style="width: 140px" ><br><br>
				<br><br> 
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<div id="menu" class="easyui-dialog" title="角色授权" closed="true" style="left:595;top:0;width:700px;height:595px;padding:5px;">
        <div style="width:98%;height:40;margin-top:0;">
        <span style="width:50%;float:left;margin-left:43;font-size:20">角色授权</span>
        <span style="margin-left:0;font-size:20">最后一次授权结果</span>
        </div>
        
        <div style="width:49%;float:left;border-right:2px dashed #7e8484;">
        <ul id="tt" class="easyui-tree" ></ul>
        </div>
        
        <div style="width:49%;float:right;padding-left:10">
        <ul id="tt2" class="easyui-tree"></ul>
        </div><br/>
        
        
        
        <div style="float:left;width:90%;margin-left:5;">
        <input  type="button" style="margin-left:100" value="角色授权" onclick="authorize()"/>
        </div>
        
    </div>

</body>

</html>
