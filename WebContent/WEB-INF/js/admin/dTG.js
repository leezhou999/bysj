//院长、教秘查看专家组答辩分组
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
$(function(){
	$("#add").dialog('close');
	$("#edit").dialog('close');
	$("#dg").datagrid({
		width:1280,
		height:520,
		fitColumns:true,
		url:"Admin/queryDTG",
		method:'post',
		columns:[
			[
             {title:'答辩专家组分组',colspan:8},
            ],[
			{field:'groupId',title:'组编号',width:100,align:'center'},
			{field:'groupName',title:'组名称',width:100,align:'center'},
			{field:'user_name',title:'教师名称',width:50,align:'center'},
			{field:'tea_id',title:'教师工号',width:50,align:'center'},
			{field:'tel',title:'联系方式',width:50,align:'center'},
			{field:'dName',title:'所属院系',width:100,align:'center'},
			{field:'deptId',title:'院系编号',width:100,align:'center',hidden:'true'},
			{field:'isHeadMan',title:'是否答辩组组长',width:100,align:'center',
				formatter : function(value){
					if(value=='1') 
						return '<font style="color:GREEN;">是</font>';
	            }},
			
		]],
		toolbar:[{
    		text:'添加答辩专家组信息',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'添加答辩专家组信息 ',
    		});
    		//$("#addform").form('reset');//重置表单
    		$("#add").dialog('open');
    		}
    	},'-',{
    		text:'删除记录',
    		iconCls:'icon-remove',
    		handler:function(){
    		//1.判断用户是否进行了选择,返回一个数组
    	  arr=$("#dg").datagrid('getSelections');
    		//2.当用户未选择时进行提示
    		if(arr.length<=0){
    			/*$.messager.show({
    				title:'提示',
    				msg:'请选择要删除的记录',
    				timeout:3000,
    			});*/
    			alert("请选择要删除的记录!");
    		}else{
    			//当用户选择时，弹出确认窗口
    			$.messager.confirm('提示','请慎重操作！',function(r){
    				if(r){
    					//1.获取用户要删除记录的id
    					var ids="";
    					for(var i=0;i<arr.length;i++){
    						ids=ids+arr[i].tea_id+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/dTGDel',
    						method:'post',
    						data:{strid:ids},
    						dataType:'json',
    						success:function(result){
    						},
    					});
    					alert("删除成功!");
    					$("#dg").datagrid('reload');
    				}
    			});
    		}
    		}
    	},'-',{
    		text:'更新专家组信息',
    		iconCls:'icon-edit',
    		handler:function(){
    		//1.获取用户的选择
    		  arr=$("#dg").datagrid('getSelections');
    		//2.如果用户的选择多于一行，提示只能针对一行进行更新
    		if(arr.length != 1){
    			alert("请选择一条记录更新！");
    		}else{
    			//1.打开对话框，将用户选择的记录信息写入表单控件
    			$("#edit").dialog({
    				title:'更新答辩专家组信息-信息确认',
    			});
    			
    			$("#editform").form('reset');//重置表单
    			$("#editform").form('load',{
    				groupId:arr[0].groupId,
    				groupName:arr[0].groupName,
    				user_name:arr[0].user_name,
    				tel:arr[0].tel,
    				dName:arr[0].dName,
    				tea_id:arr[0].tea_id
    			});
    			
    			$("#edit").dialog('open');
    		}
    		}
    	}],
    	onLoadSuccess:function(){
    		if(role_id!='3'){
        		$('div.datagrid div.datagrid-toolbar').hide();
    		}
    	},
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});

 });