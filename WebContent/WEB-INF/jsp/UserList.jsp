<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户列表</title>
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
<script type="text/javascript" src="/bysj/js/teacher/userList.js"></script>
<script type="text/javascript" src="/bysj/js/teacher/userAdd.js"></script>
<script type="text/javascript" src="/bysj/js/teacher/userEdit.js"></script>
<!-- 表头颜色 -->
<style>
    .datagrid-header-row td{background-color:#e0ecff;color:black}
</style>
</head>
<body style="background-color: #e4eef9;">
<div id="cc" class="easyui-layout" style="width:100%;height:563">
	<div id="find" data-options="region:'north',title:'查询',split:false,collapsed:true" style="height:100px;">
    	<div id="fd">
    	用户名：
    	<input type="text" id="findstr" name="findstr" placeholder="请输入用户名">
    	<input type="button" id="btn-find" value="查询 ">
    	<input type="button" id="btn-reset" value="重置">
    	</div>
    </div> 
   <div data-options="region:'center'" style="width:100%;height:520;padding:5px;background:#eee;">  
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
			<form id="addform" action="Admin/userAdd" method="post">
				<span style="margin-left:-5">用户编号: </span><input
					class="easyui-textbox" id="user_id" name="user_id" style="width: 140px" ><br> <br> 
				<span style="margin-left:11">用户名:</span><input class="easyui-textbox" id="user_name" name="user_name" style="width: 140px"><br><br>
				<span style="margin-left:-5">用户密码:</span><input class="easyui-textbox" id="password" name="password" value="111111" style="width: 140px" ><br><br>
				<span style="margin-left:-78">用户性别:</span><input type="radio" name="user_sex" value="男">男&nbsp;&nbsp;
                		<input type="radio" name="user_sex" value="女">女<br><br>
				<span>用户角色:</span><select class="easyui-combobox" id="role_id" name="role_id"style="width: 140px">
				 		<option value="0">--请选择用户类别--</option>
				 		<option value="0">临时用户</option>
				 		<option value="1">学生</option>   
    					<option value="2">教师</option>
    					<option value="3">教学秘书</option> 
    					<option value="6">教学院长</option>
    					</select> 
				 	<br><br> 
				<span style="margin-left:-5">所属院系:</span><select class="easyui-combobox" id="deptId" name="deptId"style="width: 140px">
				 		<option value="">--请选择院系信息--</option>   
				 		<option value="10">计算机学院</option>   
    					<option value="11">教育与科学学院</option>
    					<option value="12">体育学院</option> 
    					</select> 
				 	<br><br> 
				<span style="margin-left:-5">所属专业:</span><select class="easyui-combobox" id="majorId" name="majorId"style="width: 140px">
				 		<option value="">--请选择专业信息--</option>
				 		<option value="1">软件工程</option>   
    					<option value="2">物联网工程</option>
    					</select> 
				 	<br><br> 
				<span style="margin-left:-5">所属班级:</span><select class="easyui-combobox" id="classesId" name="classesId"style="width: 140px">
				 		<option value="">--请选择班级信息--</option>
				 		<option value="1001">软件1501</option>   
    					<option value="1002">软件1502</option>
    					<option value="1003">物联1501</option> 
    					</select> 
				 	<br><br> 
				<!-- <span>账户状态:</span><select class="easyui-combobox" id="isOk" name="isOk" style="width: 140px">
						<option value="可用" selected="selected">可用</option>   
    					<option value="锁定">锁定</option>   
					    </select>   -->
				<br><br> 
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<!-- 编辑 -->
	<div id="edit" class="easyui-dialog"
		style="width: 400; height: 500; left: 15%; top: 40;">
		<br>
		<center>
			<form id="editform" action="Admin/userEdit" method="post">
				<span style="margin-left:-5">用户编号: </span><input
					class="easyui-textbox" id="user_id" name="user_id" style="width: 140px" readonly="readonly" ><br> <br> 
				<span style="margin-left:11">用户名:</span><input class="easyui-textbox" id="user_name" name="user_name" style="width: 140px"><br><br>
				<span style="margin-left:-78">用户性别:</span><input type="radio" name="user_sex" value="男">男&nbsp;&nbsp;
                		<input type="radio" name="user_sex" value="女">女<br><br>
				
				
				<!-- <span>账户状态:</span><select class="easyui-combobox" id="isOk" name="isOk" style="width: 140px">
						<option value="可用" selected="selected">可用</option>   
    					<option value="锁定">锁定</option>   
					    </select>   -->
				<br><br> 
				<input type="submit" class="easyui-linkbutton" id="btn-ok"value="提交更改" />
			</form>
		</center>
	</div>
	<div id="menu" class="easyui-dialog" title="对用户直接授权" closed="true" style="left:595;top:0;width:700px;height:595px;padding:5px;">
        <div style="width:98%;height:40;margin-top:0;">
        <span style="float:left;margin-left:3;font-size:20">对用户直接授权<br><font style="color:red">注意:特殊授权只能扩展权限，不能减少！</font></span>
        <span style="margin-left:-57;font-size:20">该用户所属角色默认权限</span>
        </div>
        
        <div style="width:49%;float:left;border-right:2px dashed #7e8484;">
        <ul id="tt" class="easyui-tree" ></ul>
        </div>
        
        <div style="width:49%;float:right;padding-left:10">
        <ul id="tt2" class="easyui-tree"></ul>
        </div><br/>
        
        
        
        <div style="float:left;width:90%;margin-left:5;">
        <input  type="button" style="margin-left:100" value="对用户直接授权" onclick="authorize()"/>
        </div>
        
    </div>
	
</body>

</html>
