//教师查看学生答辩分组

$(function(){
	
	$("#dg").datagrid({
		width:1280,
		height:520,
		fitColumns:true,
		url:"Admin/querySSpeechGroup",
		method:'post',
		columns:[
			[
             {title:'学生答辩分组',colspan:7},
            ],[
            {field:'stu_id',title:'学生学号',width:100,align:'center'},
    		{field:'stu_name',title:'学生姓名',width:100,align:'center'},
			{field:'groupId',title:'分组编号',width:100,align:'center'},
			{field:'groupName',title:'分组名称',width:100,align:'center'},
			{field:'user_name',title:'答辩组组长',width:150,align:'center'},
			{field:'time',title:'答辩时间',width:100,align:'center',
				formatter : function(value){
					var unixTimestamp = new Date(value);  
		            return unixTimestamp.toLocaleString();  
	            }},
			{field:'location',title:'答辩地点',width:100,align:'center'},
			
		]],
		
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});

 });