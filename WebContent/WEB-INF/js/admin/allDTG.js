//教务处查看专家组答辩分组
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
$(function(){
	$("#dg").datagrid({
		width:1300,
		height:520,
		fitColumns:true,
		url:"Admin/queryAllDTG",
		method:'post',
		columns:[
			[
             {title:'答辩专家组分组',colspan:6},
            ],[
			{field:'groupId',title:'组编号',width:100,align:'center'},
			{field:'groupName',title:'组名称',width:100,align:'center'},
			{field:'user_name',title:'教师名称',width:50,align:'center'},
			{field:'tel',title:'联系方式',width:50,align:'center'},
			{field:'dName',title:'所属院系',width:100,align:'center'},
			{field:'isHeadMan',title:'是否答辩组组长',width:100,align:'center',
				formatter : function(value){
					if(value=='1') 
						return '<font style="color:GREEN;">是</font>';
	            },},

			
		]],
		
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});

 });