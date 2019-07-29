/**
*对话框文件
*/
var param;
//教秘查询本院所有待审核课题,二级审核学生课题，一级审核教师课题
var arr;
var row;
//教秘课题审批

function chk(index){
	//1.获取用户的选择
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	/*row = $('#dg').datagrid('getSelected');*/
	  arr=$("#dg").datagrid('getSelections');
	//2.如果用户的选择多于一行，提示只能针对一行进行更新
	if(arr.length != 1){
		alert("请选择一条记录更新！");
	}else{
		//1.打开对话框，将用户选择的记录信息写入表单控件
		if(arr[0].typeId=='22'){
			$("#edit").dialog({
				title:'审核教师课题',
				modal: true//模态对话框
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
				majorName:arr[0].majorName,
				typeId:arr[0].typeId,
				stateId:arr[0].stateId,
			});
			$("#edit").dialog('open');
		}else if(arr[0].typeId=='21'){
			$("#edit2").dialog({
				title:'审核学生课题',
				modal: true
			});
			$("#editform2").form('reset');//重置表单
			
			$("#editform2").form('load',{
				topicId:arr[0].topicId,
				topicName:arr[0].topicName,
				abs:arr[0].abs,
				user_name:arr[0].user_name,
				user_id:arr[0].user_id,
				tea_id:arr[0].tea_id,
				dName:arr[0].dName,
				majorName:arr[0].majorName,
				typeId:arr[0].typeId,
				stateId:arr[0].stateId,
			});
			$("#edit2").dialog('open');
		}
		
	}
}
$(function(){
	
	$("#edit").dialog('close');
	$("#edit2").dialog('close');
	function format(val,row,index){
		if(row.chk="0"){
			return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='chk("
			+index+ ")'>审核</a>";
		}
	 }
	$("#dg").datagrid({
		width:1280,//影响center dg 横向滚动条
		height:520,
		nowrap:false,
		fitColumns:true,
		url:"Admin/queryDSTATopic",
		method:'post',
		columns:[[
             {title:'本院待审核课题一览',colspan:13},
            ],[
               {title:'注意：教师命题需教学秘书一审通过，学生自拟课题需教师一审通过，教学秘书二审通过！',colspan:13}
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
			{field:'majorName',title:'所属专业',width:80,align:'center'},
			{field:'stateId',title:'课题状态 | 学生申请状态',width:240,align:'center',
			formatter : function(value, row, index) {
					var result = row.stateId;
					var stu_id=row.stu_id;
					var typeId =row.typeId;
					if(typeId=="21"){//自拟
						if (result == "0"){
							result = '<font style="color:RED;">教师课题待审核</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">教师审核未通过</font>';
						}else if (result == "2") {
							result = '<font style="color:GREEN;">教师审核通过</font>';
						}else if (result == "3"){
							result = '<font style="color:RED;">教学秘书审核未通过</font>';
						}else if (result == "4"){
							result = '<font style="color:GREEN;">教学秘书审核通过</font>';
						}else if (result == "6"){
							result = '<font style="color:BLUE;">自拟课题待审核</font>';
						}
					}if(typeId="22"){//教师命题
						if (result == "0"){
							result = '<font style="color:RED;">教师新课题待审核</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">教学秘书审核未通过</font>';
						}else if (result == "2"){
							result = '<font style="color:GREEN;">教学秘书审核通过</font>';
						}else if (result == "7"){
							result = '<font style="color:RED;">教秘退回建议修改</font>';
						}
					}
					if(stu_id!=""){
						result+='<font style="color:BLUE;"> | 学生已申请</font>';
					}else{
						result+='<font style="color:RED;"> | 学生未申请</font>';
					} 
					return result;
				}},
				/*{field:'chk',title:'',width:0,hidden:'true'},*/
				{field :'button',title : '操作',width : 180,align : 'center',
					formatter : format
				}
		]],
		toolbar:[{
    		text:'查询',
    		iconCls:'icon-search',
    		handler:function(){
    		$("#cc").layout('expand','north');
    		}
    	},
    	],
    	onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'审核',plain:true,iconCls:'icon-edit'}); 
        	
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
	//对文本框进行验证
	$("#permission_id").textbox({
		required:false,//必须输入
		validType:'length[1,10]',//长度限定
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	$("#permission_name").textbox({
		required:false,
		validType:'length[1,50]',
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	$("#permission_url").textbox({
		required:false,
		validType:'length[1,50]',
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	/*$("#addr").combobox({
		required:true,
		valueField:'label',
		textField: 'value',
		data: [{
			label: '西安',
			value: '西安'
		},{
			label: '咸阳',
			value: '咸阳'
		},{
			label: '武汉',
			value: '武汉'
		}],
		width:150,
	});*/
	$("#parentId").textbox({
		required:false,
		validType:'length[1,15]',
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	$("#state").textbox({
		required:false,
		validType:'length[1,10]',
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	$("#ionCls").textbox({
		required:false,
		validType:'length[1,10]',
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});

	$("#btn-cancle").click(function(){
		$("#add").dialog('close');
	});
	
});