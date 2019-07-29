//文档类型
var arr;
$(function() {
	
	$("#dg").datagrid({
		width : 1270,
		height : 520,
		fitColumns:true,
		url : "queryDocType",
		method : 'post',
		columns : [ [ {
			title : '编号',
			field : 'id',
			width : 120,
			align : 'center'
		}, {
			title : '文档类型编号',
			field : 'typeId',
			width : 120,
			align : 'center'
		}, {
			title : '文档类型名',
			field : 'typeName',
			width : 150,
			align : 'center'
		}]],
		toolbar:[{
    		text:'增加',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'增加文档类型信息 ',
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
    						ids=ids+arr[i].typeId+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'docTypeDel',
    						method:'post',
    						data:{strid:ids},
    						dataType:'json',
    						success:function(result){
    							$.messager.show({
    								title:'提示',
    								msg:result.info,
    								timeout:3000,
    							});
    						},
    					});
    					$("#dg").datagrid('reload');
    				}
    			});
    		}
    		}
    	},'-',{
    		text:'更新',
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
    				title:'编辑文档类型信息',
    			});
    			$("#editform").form('reset');//重置表单
    			
    			$("#editform").form('load',{
    				typeId:arr[0].typeId,
    				typeName:arr[0].typeName,
    				
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
    	}
    	],
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:15,
        pageList:[15,20],
	});
	$("#btn-find").click(function(){
		//1.获取查询文本框中的信息
		 param =$("#findstr").val();
		
		$("#dg").datagrid('load',{
			typeName:param
		});
	});
	//重置搜索框
	$("#btn-reset").click(function(){
		document.getElementById("findstr").value="";
	});
});