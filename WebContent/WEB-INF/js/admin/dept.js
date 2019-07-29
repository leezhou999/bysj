//显示dept
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
$(function(){
	$("#dg").datagrid({
		width:1280,
		height:540,
		url:"Admin/queryDeptInfo",
		method:'post',
		columns:[[
			{field:'deptId',title:'院系编号',width:150,align:'center'},
			{field:'dName',title:'院系名称',width:100,align:'center'},
			
		]],
		toolbar:[{
    		text:'更新',
    		iconCls:'icon-edit',
    		handler:function(){
    		flag='update';
    		//1.获取用户的选择
    		  arr=$("#dg").datagrid('getSelections');
    		//2.如果用户的选择多于一行，提示只能针对一行进行更新
    		if(arr.length != 1){
    			alert("请选择一条记录更新！");
    		}else{
    			//1.打开对话框，将用户选择的记录信息写入表单控件
    			$("#edit").dialog({
    				title:'编辑院系信息',
    			});
    			$("#editform").form('reset');//重置表单
    			
    			$("#editform").form('load',{
    				deptId:arr[0].deptId,
    				dName:arr[0].dName,
    				
    			});
    			$("#edit").dialog('open');
    		}
    		}
    	}
    	],
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	

 });