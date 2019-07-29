//教师审核
var arr;
var row;
//申请该课题

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
		$("#edit").dialog({
			title:'审核课题',
			modal: true
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
			stateId:arr[0].stateId
		});
		$("#edit").dialog('open');
	}
}
$(function(){
	$("#edit").dialog('close');
	function format(val,row,index){
		if(row.chk="0"){
			return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='chk("
			+index+ ")'>审核</a>";
		}
	 }
	$("#dg").datagrid({
		width:1280,
		height:520,
		nowrap:false,
		fitColumns:true,
		url:"Admin/querySATopic",
		method:'post',
		columns:[[
             {title:'学生申请课题一览',colspan:14},
            ],[
			{field:'topicId',title:'课题编号',width:80,align:'center'},
			{field:'topicName',title:'课题名称',width:180,align:'center'},
			{field:'abs',title:'课题简介',width:180,align:'center'},
			{field:'typeId',title:'命题类型',width:90,align:'center',
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
			{field:'stu_id',title:'申请学生编号',width:120,align:'center'},
			{field:'stu_name',title:'申请学生姓名',width:120,align:'center'},
			{field:'tea_id',title:'课题绑定教师',width:120,align:'center'},
			{field:'dName',title:'所属院系',width:120,align:'center'},
			{field:'majorName',title:'所属专业',width:90,align:'center'},
			{field:'stateId',title:'课题状态 | 学生申请状态',width:250,align:'center',
			formatter : function(value, row, index) {
					var result = row.stateId;
					var stu_id=row.stu_id;
					var typeId = row.typeId;
					if(typeId=="21"){
						if (result == "0") {
							result = '<font style="color:RED;">教师未审核</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">教师审核未通过</font>';
						}else if (result == "2"){
							result = '<font style="color:GREEN;">教师审核通过</font>';
						}else if (result == "3"){
							result = '<font style="color:RED;">教秘审核未通过</font>';
						}else if (result == "4"){
							result = '<font style="color:GREEN;">教秘审核通过</font>';
						}
					}else if(typeId=="22"){
						if (result == "2") {
							result = '<font style="color:GREEN;">教秘审核通过</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">教秘审核未通过</font>';
						}else if (result == "0"){
							result = '<font style="color:RED;">新增课题未审核</font>';
						}
					}
					
					/*else if (result == "6"){
						result = '<font style="color:BLUE;">自拟课题待审核</font>';
					}*/
					
					if(stu_id!=""){
						result+='<font style="color:BLUE;"> | 学生已申请</font>';
					}else{
						result+='<font style="color:RED;"> | 学生未申请</font>';
					} 
					return result;
				}
			},
			{field:'chk',title:'师生双向选题',width:150,align:'center',
				formatter : function(value, row, index) {
						var result = row.chk;
						if (result == "6") {
							result = '<font style="color:BLUE;">学生已申请</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">拒绝选题</font>';
						}
						else if (result == "2"){
							result = '<font style="color:GREEN;">同意选题</font>';
						}
						else if (result == "7"){
							result = '<font style="color:RED;">教师退回建议修改</font>';
						}
						
						return result;
					}
				},
			{field :'button',title : '操作',width : 180,align : 'center',
				formatter : format
			}
		]],
		onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'审核',plain:true,iconCls:'icon-edit'}); 
        	
        },
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	

 });