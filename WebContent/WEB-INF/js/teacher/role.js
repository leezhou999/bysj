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
 * 角色授权
 * @returns
 */
/*var authIds="";*/
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
		url:'Admin/changeAuthority',
		method:'post',
		data:{
			ids:authIds,
			role_id:row.role_id
		},
		dataType:'json',
		success:function(result){
			//authIds="";
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
	row = $('#dg').datagrid('getSelected');
	$('#menu').dialog('open');
	$("#tt").tree({
	    method:"post",
	    url:'queryTree?role_id='+row.role_id, // 加载权限树,当前角色已有权限，并作checked state处理
	    lines:true,
	    checkbox:true,
		animate:true,
		loadFilter : function(rows) {
			return convert(rows);
		}
		
	});
	$("#tt2").tree({
	    method:"post",
	    url:'queryTreeByRole?role_id='+row.role_id, // 加载权限树byRole，直接显示当前角色tree，去除checkbox
	    lines:true,
	    checkbox:false,
		animate:true,
		loadFilter : function(rows) {
			return convert(rows);
		}
		
	});
}


$(function(){
	function format(val, row, index) {// 用来将最后一列由标签设置为easyUI的按钮
		// 主要看下面的返回值
		return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='edit("
				+ index
				+ ")'>角色授权</a>";
	}
	$("#dg").datagrid({
		width:1280,
		height:520,
		fitColumns:true,
		url:"Admin/queryRole",
		method:'post',
		columns:[[
			{field:'role_id',title:'角色编号',width:150,align:'center'},
			{field:'role_name',title:'角色名称',width:100,align:'center'},
			{
				title : '操作',
				field : 'button',
				width : 100,
				align : 'center',
				formatter : format
			},
			{field:'role_remark',title:'备注',width:150,align:'center'},
		]],
		toolbar:[{
    		text:'增加',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'增加角色 ',
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
    						ids=ids+arr[i].role_id+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/delRole',
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
    				title:'编辑角色信息',
    			});
    			$("#editform").form('reset');//重置表单
    			
    			$("#editform").form('load',{
    				role_id:arr[0].role_id,
    				role_name:arr[0].role_name,
    				role_remark:arr[0].role_remark,
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
    	onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'角色授权',plain:true,iconCls:'icon-man'}); 
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
			role_name:param
		});
	});
	//重置搜索框
	$("#btn-reset").click(function(){
		document.getElementById("findstr").value="";
	});

 });