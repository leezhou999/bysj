<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>新闻发布</title>
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
 <!--wangEditor是一款基于jquery框架开发的插件-->
<script type="text/javascript">
$(function(){

    var E = window.wangEditor;
    //这里的id为<div id="editor"中的id.
    var editor = new E('#editor');
    // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
    editor.customConfig.uploadImgServer = 'upload/editor/img';
    //配置属性名称，绑定请求的图片数据
    //controller会用到，可以随便设置，但是一定要与controller一致
    editor.customConfig.uploadFileName = 'img';
    //创建编辑器
    editor.create();

    $("#editorSetBtn").click(function(){
        //这是设置编辑器内容
        editor.txt.html("dsafdfadsfdafdsfds");
    })
     $("#editorGetBtn1").click(function(){
     　//获取编辑器的内容，带样式
     　//一般使用这个获取数据，通过ajax发送给服务端　，然后服务端以Ｓtring接收，保存到数据库．
         alert(editor.txt.html());
    })
     $("#editorGetBtn2").click(function(){
    　　　 //获取编辑器的内容，不带样式，纯文本
         alert(editor.txt.text());
    })
})

</script>

</head>
<body>
<!--标题、摘要、类别 -->
	<div>
	<div
		style="width: 960px; height: auto; margin-top: 1%;margin-left:173px;"
		class="banner">
		<span class="label label-danger" style="width: 200px;">公告标题：</span>
		<input type="text" style="width:884" class="form-control" id="title" placeholder="标题">
		

	</div>
	<div
		style="width: 960px; height: 33px; margin-left: 173px; margin-top: 10px;">
		
		<span class="label label-danger" style="width: 200px;">摘要信息：</span>
		<!-- <textarea class="form-control" style="width:884;margin-top:12px;" rows="1"  id="summary"></textarea> -->
		<input type="text" style="width:884" class="form-control" id="abs" placeholder="摘要信息">	<br><br>
		<span class="label label-danger" style="width: 200px;">公告类别：</span>
		<select id="type" class="form-control">
			<option value="0">通知公告</option>
			<option value="1">管理规定</option>
			<option value="2">教务安排</option>
			<option value="3">毕业相关</option>
		</select>
	</div>
	</div>
	<!-- 编辑器 -->
	<div style="margin-top:73px;height:300px;">
		<button id="editorSetBtn">设置内容</button>
		<button id="editorGetBtn1">获取内容1</button>
		<button id="editorGetBtn2">获取内容2</button>
	
		<div id="editor">
　		<!-- 默认显示 -->
   		<p>欢迎使用公告编辑器</p>
		</div>
	
	</div>
	<!--  -->
     <div style="margin-top: 100px; text-align: center;">
		<button class="btn btn-primary btn-lg" id="submit">提 交</button>

	</div>
         <!--按照官网上的说明，js和css的这两个引用应该放在body的末尾-->
         <script type="text/javascript" src="/bysj/js/wangEditor.js"></script>
</body>

</html>
