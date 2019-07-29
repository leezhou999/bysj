//显示权限信息
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
var row;
var authNodes;//选中的节点
var fatherId="";
function convert(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId) return true;
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.parentId)){
			nodes.push({
				id:row.id,
				text:row.text,
			});
		}
	}
	
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.parentId == node.id){
				var child = {id:row.id,text:row.text,attributes:row.attributes,checked:row.checked};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
/**
 * 对用户直接授权,权限只能新增，不能比默认角色拥有的少
 * @returns
 */
function authorize(){
	authNodes = $('#tt').tree('getChecked');//获取:checked的结点.
	fatherId=$('#tt').tree('getChecked',['indeterminate']);
	var fatherIds="";
	for(var i1=0;i1<fatherId.length;i1++){
		fatherIds+=fatherId[i1].id+",";
	}
	var authIds="";
	
	for(var i=0;i<authNodes.length;i++){
		authIds+=authNodes[i].id+",";
	}
	//authIds=authIds.substring(0,authIds.length-1);//处理逗号
    authIds+=fatherIds;
    $.ajax({
		url:'Admin/changeAuthorityByUserId',//
		method:'post',
		data:{
			ids:authIds,
			user_id:row.user_id
		},
		dataType:'json',
		success:function(result){
			$.messager.show({
				title:'提示',
				msg:result.info,
				timeout:3000,
			});
		},
	});
    $("#menu").dialog('close');
}
function edit(index) {
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	row =$('#dg').datagrid('getSelected');
	$('#menu').dialog('open');
	$("#tt").tree({
	    method:"post",
	    url:'queryTreeByUserId?user_id='+row.user_id+'&role_id='+row.role_id, //查询当前用户已有权限（按角色查询联合user_permission)
	    lines:true,
	    checkbox:true,
		animate:true,
		loadFilter : function(rows) {
			return convert(rows);
		}
		
	});
	$("#tt2").tree({
	    method:"post",
	    url:'queryTreeByUser2?user_id='+row.user_id, //查询当前用户所属角色权限，去除checkbox
	    lines:true,
	    checkbox:false,
		animate:true,
		loadFilter : function(rows) {
			return convert(rows);
		}
		
	});
}
$(function(){
	$("#dg").datagrid({
		width:1280,
		height:520,
		fitColumns:true,
		url:"Admin/queryUserList",
		method:'post',
		columns:[[
			{field:'user_id',title:'用户编号',width:150,align:'center'},
			{field:'user_name',title:'用户名',width:100,align:'center'},
			{field:'user_sex',title:'用户性别',width:150,align:'center'},
			{field:'role_name',title:'用户角色',width:150,align:'center'},
			{field : 'button',title : '操作',width : 150,align : 'center',
			formatter : function(val,row,index){
					return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='edit("
					+ index
					+ ")'>对用户直接授权</a>";
			 }
			},
		/*	{field:'isOk',title:'账户状态',width:150,align:'center',
			formatter : function(value, row, index) {
				var result = row.isOk;
				if (result == "可用") {
					result = '<font style="color:GREEN;">可用</font>';
				}else if (result == "锁定") {
					result = '<font style="color:RED;">锁定</font>';
				}
				return result;
			}
					},*/

		]],
		toolbar:[{
    		text:'增加',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'增加用户信息 ',
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
    						ids=ids+arr[i].user_id+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/delUser',
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
    				title:'编辑用户信息',
    			});
    			$("#editform").form('reset');//重置表单
    			
    			$("#editform").form('load',{
    				user_id:arr[0].user_id,
    				user_name:arr[0].user_name,
    				user_sex:arr[0].user_sex,
    				role_id:arr[0].role_name,//格式转换
    				/*isOk:arr[0].isOk,*/
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
    	onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'对用户直接授权',plain:true,iconCls:'icon-man'}); 
        },
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	$("#btn-find").click(function(){
		//1.获取查询文本框中的信息
		 param =$("#findstr").val();
		
		$("#dg").datagrid('load',{
			findstr:param
		});
	});
	$(document).keyup(function(event){
		if(event.which==13){//添加搜索框，键盘响应事件
		//1.获取查询文本框中的信息
		 param =$("#findstr").val();
		
		$("#dg").datagrid('load',{
			findstr:param
		});
		}
	});
	//重置搜索框
	$("#btn-reset").click(function(){
		document.getElementById("findstr").value="";
	});

 });