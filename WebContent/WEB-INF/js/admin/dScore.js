//教秘、院长查看本院学生成绩
var row;
var arr;
$(function(){
	
	$("#dg").datagrid({
		width:1270,
		height:520,
		fitColumns:true,
		nowrap:false,
		url:"Admin/queryDScore",
		method:'post',
		columns:[
			[
             {title:'学生成绩',colspan:14},
            ],[
			{field:'stu_id',title:'学号',width:100,align:'center'},
			{field:'user_name',title:'学生姓名',width:100,align:'center'},
			{field:'user_sex',title:'性别',width:50,align:'center'},
			{field:'dName',title:'所属院系',width:100,align:'center'},
			{field:'majorName',title:'所属专业',width:100,align:'center'},
			{field:'cName',title:'所属班级',width:100,align:'center'},
			{field:'topicName',title:'课题名称',width:280,align:'left'},
			{field:'tea_name',title:'指导教师',width:100,align:'center'},
			{field:'score1',title:'指导教师评阅成绩',width:140,align:'center'},
			{field:'score2',title:'评阅教师评审成绩',width:140,align:'center'},
			{field:'score3',title:'答辩成绩',width:100,align:'center'},
			{field:'score4',title:'总成绩',width:80,align:'center',
				formatter : function(value, row, index) {
					result=row.score1*0.2+row.score2*0.2+row.score3*0.6+"";
					var score=result.substring(0,result.indexOf(".") + 3);
					return score;
				},},
			{field:'scoreType',title:'成绩类别',width:100,align:'center',
				formatter : function(value, row, index) {
					var result = row.scoreType;
					if (result == "11") {
						result = '<font style="color:GREEN;">一辩</font>';
					}else if (result == "12"){
						result = '<font style="color:RED;">二辩</font>';
					}
					return result;}},
			{field:'remark',title:'备注',width:150,align:'center'},
			
		]],
		toolbar:[{
    		text:'录入成绩',
    		iconCls:'icon-add',
    		handler:function(){
    		$("#add").dialog({
    			title:'录入成绩 ',
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
    						ids=ids+arr[i].stu_id+",";
    					}
    					ids=ids.substring(0,ids.length-1);//处理逗号
    					//console.log(ids);
    					//2.将id传给服务器，完成删除
    					$.ajax({
    						url:'Admin/scoreDel',
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
    		text:'更新成绩',
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
    				user_name:arr[0].user_name,
    				user_sex:arr[0].user_sex,
    				dName:arr[0].dName,
    				majorName:arr[0].majorName,
    				cName:arr[0].cName,
    				topicName:arr[0].topicName,
    				tea_name:arr[0].tea_name,
    				score1:arr[0].score1,
    				score2:arr[0].score2,
    				score3:arr[0].score3,
    				scoreType:arr[0].scoreType
    			});
    			$("#edit").dialog({
    				title:'更新学生成绩-信息确认',
    				maximizable:true,
    				resizable:false,
    				draggable:false,
    				border:'thin',
    				top:20,
    				left:415,
    				closable:true
    			});
    			$("#stu_id1").textbox('textbox').css("color", "#a2b5cd");
    			$("#user_name").textbox('textbox').css("color", "#a2b5cd");
    			$("#dName").textbox('textbox').css("color", "#a2b5cd");
    			$("#majorName").textbox('textbox').css("color", "#a2b5cd");
    			$("#cName").textbox('textbox').css("color", "#a2b5cd");
    			$("#topicName").textbox('textbox').css("color", "#a2b5cd");
    			$("#tea_name").textbox('textbox').css("color", "#a2b5cd");
    			
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