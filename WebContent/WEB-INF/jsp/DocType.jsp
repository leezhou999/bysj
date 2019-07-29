<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>文档类型查看</title>
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
<script type="text/javascript" src="/bysj/js/student/docType.js"></script>
<script type="text/javascript" src="/bysj/js/student/docTypeAdd.js"></script>
<script type="text/javascript" src="/bysj/js/student/docTypeEdit.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	<div id="find" data-options="region:'north',title:'查询',split:false,collapsed:true" style="height:100px;">
    	<div id="fd">
    	文档类型名：
    	<input type="text" id="findstr" name="typeName" placeholder="请输入文档类型名">
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
			<form id="addform" action="docTypeAdd" method="post">
				<span style="margin-left:-5">文档类型编号: </span>
				<input class="easyui-textbox" id="typeId" name="typeId" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">文档类型名:</span>
				<input class="easyui-textbox" id="typeName" name="typeName" style="width: 140px"><br><br>
				
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 编辑 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="editform" action="docTypeEdit" method="post">
				<span style="margin-left:-5">文档类型编号: </span>
				<input class="easyui-textbox" id="typeId" name="typeId" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">文档类型名:</span>
				<input class="easyui-textbox" id="typeName" name="typeName" style="width: 140px"><br><br>
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	
</body>

</html>
