<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>权限表</title>
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
<script type="text/javascript" src="/bysj/js/admin/permission.js"></script>
<script type="text/javascript" src="/bysj/js/admin/permissionAdd.js"></script>
<script type="text/javascript" src="/bysj/js/admin/permissionEdit.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	<div id="find" data-options="region:'north',title:'查询',split:false,collapsed:true" style="height:100px;">
    	<div id="fd">
    	权限名称：
    	<input type="text" id="findstr" name="permission_name" placeholder="请输入权限名称">
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
			<form id="addform" action="Admin/permissionAdd" method="post">
				权限编号: <input
					class="easyui-textbox" id="permission_id" name="permission_id" style="width: 140px" ><br> <br> 
				权限名称:<input class="easyui-textbox" id="permission_name" name="permission_name" style="width: 140px"><br><br>
				 权限地址: <input class="easyui-textbox" id="permission_url" name="permission_url"style="width: 140px"><br><br> 
				 父节点编号: <input class="easyui-textbox" id="parentId" name="parentId" value=0 style="width: 140px" ><br><br> 
				节点状态: <input class="easyui-textbox" id="state" name="state" style="width: 140px"><br><br> 
				节点图标: <input class="easyui-textbox" id="ionCls" name="ionCls" style="width: 140px" ><br><br>
					   <input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 编辑 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="editform" action="Admin/permissionEdit" method="post">
				权限编号: <input
					class="easyui-textbox" id="permission_id" name="permission_id" readonly="readonly" style="width: 140px" ><br> <br> 
				权限名称:<input class="easyui-textbox" id="permission_name"name="permission_name" style="width: 140px"><br><br>
				 权限地址: <input class="easyui-textbox" id="permission_url" name="permission_url"style="width: 140px"><br><br> 
				父节点编号: <input class="easyui-textbox" id="parentId" name="parentId" style="width: 140px" ><br><br> 
				节点状态: <input class="easyui-textbox" id="state" name="state" style="width: 140px"><br><br> 
				节点图标: <input class="easyui-textbox" id="ionCls"name="ionCls" style="width: 140px" ><br><br> 
					   <input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
</body>

</html>
