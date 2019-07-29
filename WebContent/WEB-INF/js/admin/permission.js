//显示权限信息
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
$(function(){
	$("#dg").datagrid({
		width:1280,
		height:520,
		fitColumns:true,
		url:"Admin/queryPermission",
		method:'post',
		columns:[[
			{field:'permission_id',title:'权限编号',width:150,align:'center'},
			{field:'permission_name',title:'权限名称',width:100,align:'center'},
			{field:'permission_url',title:'权限地址',width:150,align:'center'},
			{field:'parentId',title:'父节点编号',width:150,align:'center'},
			{field:'state',title:'节点状态',width:125,align:'center'},
			{field:'ionCls',title:'节点图标',width:150,align:'center',hidden:'true'},
			
		]],
		toolbar:[{
    		text:'增加',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'增加权限信息 ',
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
    						ids=ids+arr[i].permission_id+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/delPermission',
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
    				title:'编辑权限信息',
    			});
    			$("#editform").form('reset');//重置表单
    			
    			$("#editform").form('load',{
    				permission_id:arr[0].permission_id,
    				permission_name:arr[0].permission_name,
    				permission_url:arr[0].permission_url,
    				parentId:arr[0].parentId,
    				state:arr[0].state,
    				ionCls:arr[0].ionCls,
    			});
    			$("#edit").dialog('open');
    		}
    		}
    	},'-',{
    		text:'查询',
    		iconCls:'icon-search',
    		handler:function(){
    		$("#cc").layout('expand','north');
    		}
    	},
    	],
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	$("#btn-find").click(function(){
		//1.获取查询文本框中的信息
		 param =$("#findstr").val();
		
		$("#dg").datagrid('load',{
			permission_name:param
		});
	});
	$(document).keyup(function(event){
		if(event.which==13){//添加搜索框，键盘响应事件
		//1.获取查询文本框中的信息
		 param =$("#findstr").val();
		
		$("#dg").datagrid('load',{
			permission_name:param
		});
		}
	});
	//重置搜索框
	$("#btn-reset").click(function(){
		document.getElementById("findstr").value="";
	});

 });