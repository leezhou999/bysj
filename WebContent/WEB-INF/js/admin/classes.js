//显示班级信息
var arr;
$(function(){
	$("#dg").datagrid({
		width:1280,
		height:540,
		url:"Admin/queryClassesInfo",
		method:'post',
		columns:[[
			{field:'classesId',title:'班级编号',width:150,align:'center'},
			{field:'cName',title:'班级名称',width:100,align:'center'},
			{field:'majorName',title:'所属专业',width:150,align:'center'},
			{field:'majorId',title:'专业编号',width:150,align:'center'}

		]],
		toolbar:[{
    		text:'增加',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'增加班级 ',
    		});
    		//$("#addform").form('reset');//重置表单
    		$("#add").dialog('open');
    		}
    	},'-',{
    		text:'删除',
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
    			$.messager.confirm('提示','确定删除吗?',function(r){
    				if(r){
    					//1.获取用户要删除记录的id
    					var ids="";
    					for(var i=0;i<arr.length;i++){
    						ids=ids+arr[i].classesId+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/classesInfoDel',
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
    				title:'编辑班级信息',
    			});
    			
    			$("#editform").form('reset');//重置表单
    			$("#editform").form('load',{
    				classesId:arr[0].classesId,
    				cName:arr[0].cName,
    				majorId:arr[0].majorId
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