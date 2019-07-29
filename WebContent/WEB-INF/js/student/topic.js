//学生显示本院可选课题
var arr;
var row;
var param;
//直接选择课题
function sApplyTopic1(index){
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	row = $('#dg').datagrid('getSelected');
	arr=$("#dg").datagrid('getSelections');
	$.messager.confirm('提示','确定选择吗?',function(r){
		if(r){
			$.ajax({
				url:'Stu/sApplyTopic1',
				method:'post',
				data:{
					topicId:arr[0].topicId,
					tea_id:arr[0].tea_id,
					},
				dataType:'json',
				success:function(result){
					$.messager.show({
						title:'选择结果',
						msg:result.info,
						timeout:3000,
					});
				},
			});
			$("#dg").datagrid('reload');
		}
	});
	$("#dg").datagrid('reload');
}
	
$(function(){
	function format(val,row,index){
		if(row.stu_id!=""){
			return '<font style="color:RED;">不可选择该课题</font>';
		}
		return  "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='sApplyTopic1("
		+index+ ")'>立即选择</a>";
	 }
	$("#dg").datagrid({
		width:1280,
		height:520,
		nowrap:false,
		fitColumns:true,
		url:"Stu/queryDTopic",
		method:'post',
		columns:[[
             {title:'全院课题一览',colspan:12},
            ],[
               {title:'友情提示：课题被他人选择时，可选择其他老师，或申请自拟课题！',colspan:12}
                ],[
			{field:'topicId',title:'课题编号',width:80,align:'center'},
			{field:'topicName',title:'课题名称',width:200,align:'center'},
			{field:'abs',title:'课题简介',width:200,align:'center'},
			{field:'typeId',title:'命题类型',width:100,align:'center',
				formatter : function(value, row, index) {
					var result = row.typeId;
					if (result == "21") {
						result = '<font style="color:GREEN;">学生自拟</font>';
					}else if (result == "22") {
						result = '<font style="color:BLUE;">教师命题</font>';
					}
					return result;
				}},
			{field:'user_name',title:'命题者',width:100,align:'center'},
			{field:'user_id',title:'命题者编号',width:100,align:'center'},
			{field:'stu_id',title:'课题绑定学生',width:120,align:'center'},
			{field:'tea_id',title:'课题绑定教师',width:120,align:'center'},
			{field:'dName',title:'所属院系',width:100,align:'center'},
			{field:'majorName',title:'所属专业',width:100,align:'center'},
			{field:'stateId',title:'课题是否可选',width:120,align:'center',
			formatter : function(value, row, index) {
					var result = row.stu_id;
					if (result == "") {
						result = '<font style="color:GREEN;">可选</font>';
					}else{
						result = '<font style="color:RED;">不可选</font>';
					}
					return result;
				}},
			
			{field :'button',title : '操作',width : 250,align : 'center',
			formatter : format
			}
		]],
		toolbar:[{
    		text:'查询',
    		iconCls:'icon-search',
    		handler:function(){
    		$("#cc").layout('expand','north');
    		}
    	},'-',{
    		text:'申请自拟课题',
    		iconCls:'icon-add',
    		handler:function(){
    		//1.获取用户的选择
    		 arr=$("#dg").datagrid('getSelections');
    		//2.如果用户的选择多于一行，提示只能针对一行进行更新
    		if(arr.length != 1){
    			alert("请选择一位老师进行申请！");
    		}else{
    			//1.打开对话框，将用户选择的记录信息写入表单控件
    			/*$("#add").dialog({
    				
    			});*/
    			
    			$("#addform").form('reset');//重置表单
    			$("#addform").form('load',{
    				topicId:maxTopicId,
    				tea_name:arr[0].user_name,
    				tea_id:arr[0].tea_id
    			});
    			$("#add").dialog('open');
    			$("#add").dialog({
    				title:'自拟课题信息填写',
    				maximizable:true,
    				resizable:false,
    				draggable:false,
    				border:'thin',
    				top:20,
    				left:415,
    				closable:true
    			});
    		}
    	
    		}
    	}
    	],
    	onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'立即选择',plain:true,iconCls:'icon-add'}); 
        	$("a[name='opera1']").linkbutton({text:'不可选择该课题',plain:true,color:'RED'}); 
        	$("a[name='opera2']").linkbutton({text:'申请自拟课题',plain:true,iconCls:'icon-add'});
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