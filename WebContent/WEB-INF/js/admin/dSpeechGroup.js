//教秘查看学生答辩分组

$(function(){
	$("#add").dialog('close');
	$("#edit").dialog('close');
	$("#dg").datagrid({
		width:1280,
		height:520,
		fitColumns:true,
		url:"Admin/queryDSpeechGroup",
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
		toolbar:[{
    		text:'新增学生答辩分组',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'新增学生答辩分组 ',
    		});
    		//$("#addform").form('reset');//重置表单
    		$("#add").dialog('open');
    		$("#add").dialog({
				title:'新增学生答辩分组',
				maximizable:true,
				resizable:false,
				draggable:false,
				border:'thin',
				top:20,
				left:415,
				closable:true
			});
    		}
    	},'-',{
    		text:'删除分组',
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
    						ids=ids+arr[i].stu_id+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/dSpeechGroupDel',
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
    		text:'更新分组信息',
    		iconCls:'icon-edit',
    		handler:function(){
    		//1.获取用户的选择
    		  arr=$("#dg").datagrid('getSelections');
    		//2.如果用户的选择多于一行，提示只能针对一行进行更新
    		if(arr.length != 1){
    			alert("请选择一条记录更新！");
    		}else{
    			//1.打开对话框，将用户选择的记录信息写入表单控件
    			
    			$("#editform").form('reset');//重置表单
    			$("#editform").form('load',{
    				stu_id:arr[0].stu_id,
    				groupId:arr[0].groupId,
    				time:arr[0].time,
    				location:arr[0].location
    				
    			});
    			$("#edit").dialog({
    				title:'更新学生答辩分组信息',
    				maximizable:true,
    				resizable:false,
    				draggable:false,
    				border:'thin',
    				top:20,
    				left:415,
    				closable:true
    			});
    			$("#stu_id1").textbox('textbox').css("color", "#a2b5cd");
    			$("#groupId1").textbox('textbox').css("color", "#a2b5cd");
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