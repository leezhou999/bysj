//教师显示本人课题
var arr;
var row;
$(function(){
	$("#dg").datagrid({
		width:1250,
		height:520,
		nowrap:false,
		fitColumns:true,
		url:"Admin/queryTTopic",
		method:'post',
		columns:[[
             {title:'本人开放课题一览',colspan:12},
            ],[
			{field:'topicId',title:'课题编号',width:80,align:'center'},
			{field:'topicName',title:'课题名称',width:180,align:'center'},
			{field:'abs',title:'课题简介',width:200,align:'center'},
			{field:'typeId',title:'命题类型',width:70,align:'center',
				formatter : function(value, row, index) {
					var result = row.typeId;
					if (result == "21") {
						result = '<font style="color:GREEN;">学生自拟</font>';
					}else if (result == "22") {
						result = '<font style="color:BLUE;">教师命题</font>';
					}
					return result;
				}},
			{field:'user_name',title:'命题者',width:70,align:'center'},
			{field:'user_id',title:'命题者编号',width:100,align:'center'},
			{field:'stu_id',title:'申请学生编号',width:100,align:'center'},
			{field:'stu_name',title:'申请学生姓名',width:80,align:'center'},
			{field:'tea_id',title:'课题绑定教师',width:100,align:'center'},
			{field:'dName',title:'所属院系',width:80,align:'center'},
			{field:'majorName',title:'所属专业',width:70,align:'center'},
			{field:'stateId',title:'课题状态 | 学生选题情况',width:180,align:'center',
			formatter : function(value, row, index) {
					var result = row.stateId;
					var stu_id=row.stu_id;
					if (result == "2") {
						result = '<font style="color:GREEN;">教秘审核通过</font>';
					}else if (result == "1"){
						result = '<font style="color:RED;">教秘审核未通过</font>';
					}
					else if (result == "6"){
						result = '<font style="color:BLUE;">学生已申请</font>';
					}
					else if (result == "0"){
						result = '<font style="color:RED;">新增课题未审核</font>';
					}
					if(stu_id!=""){
						result+='<font style="color:BLUE;"> | 学生已申请</font>';
					}else{
						result+='<font style="color:RED;"> | 学生未申请</font>';
					} 
					return result;
				}},
		]],
		toolbar:[{
    		text:'新增课题',
    		iconCls:'icon-add',
    		handler:function(){
    			
    			$("#addform").form('reset');//重置表单
    			$("#addform").form('load',{
    				topicId:maxTopicId
    			});
    			$("#add").dialog('open');
    			$("#add").dialog({
    				title:'新增课题',
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
    		text:'删除课题',
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
    						ids=ids+arr[i].topicId+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/topicDel',
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
    		text:'编辑课题',
    		iconCls:'icon-edit',
    		handler:function(){
    		//1.获取用户的选择
    		  arr=$("#dg").datagrid('getSelections');
    		//2.如果用户的选择多于一行，提示只能针对一行进行更新
    		if(arr.length != 1){
    			alert("请选择一条记录更新！");
    		}else{
    			if(arr[0].stateId!='2'){
    				//1.打开对话框，将用户选择的记录信息写入表单控件
        			$("#edit").dialog({
        				title:'编辑课题信息',
        			});
        			$("#editform").form('reset');//重置表单
        			
        			$("#editform").form('load',{
        				topicId:arr[0].topicId,
        				topicName:arr[0].topicName,
        				abs:arr[0].abs,
        				user_name:arr[0].user_name,
        				user_id:arr[0].user_id,
        				tea_id:arr[0].tea_id,
        				dName:arr[0].dName,
        				majorName:arr[0].majorName
        			});
        			$("#edit").dialog('open');
        			
        			$("#edit").dialog({
        				title:'编辑课题',
        				maximizable:true,
        				resizable:false,
        				draggable:false,
        				border:'thin',
        				top:20,
        				left:415,
        				closable:true
        			});
    			}else{
    				alert("课题已审批，无法修改！");
    			}
    			
    			
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