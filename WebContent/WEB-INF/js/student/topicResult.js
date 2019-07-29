//显示本院可选课题
var arr;
var row;
var param;
//申请该课题
function sApplyCancel(index){
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	row = $('#dg').datagrid('getSelected');
	arr=$("#dg").datagrid('getSelections');
	$.messager.confirm('提示','确定撤销申请吗?',function(r){
		if(r){
			var topicId = arr[0].topicId;
			$.ajax({
				url:'Stu/sApplyCancel',
				method:'post',
				data:{topicId:topicId},
				dataType:'json',
				success:function(result){
					$.messager.show({
						title:'撤销申请结果',
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
		var stateId =row.stateId;
		var chk=row.chk;
		var typeId = row.typeId;
		if(typeId=="21"){//自拟类型
			if(stateId!="2"&&stateId!="4"){
			return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='sApplyCancel("
			+index+ ")'>撤回申请</a>";
			}else{
				return '<font style="color:RED;">当前状态，不可撤回</font>';
			}
		}else if(typeId=="22"){//教师命题
			if(chk=="0"||chk=="1"||chk=="7"||chk=="6"){//未审核，不通过，退回修改，已申请
				return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='sApplyCancel("
				+index+ ")'>撤回申请</a>";
			}else{
				return '<font style="color:RED;">当前状态，不可撤回</font>';
			}
		}
	 }
	$("#dg").datagrid({
		width:1300,
		height:520,
		fitColumns:true,
		nowrap:false,
		url:"Stu/queryTopicResult",
		method:'post',
		columns:[[
             {title:'课题申请结果查询',colspan:13},
            ],[
			{field:'topicId',title:'课题编号',width:80,align:'center'},
			{field:'topicName',title:'教师课题名称',width:280,align:'center'},
			{field:'abs',title:'课题简介',width:150,align:'center'},
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
			{field:'stateId',title:'课题状态',width:140,align:'center',
			formatter : function(value, row, index) {
					var result = row.stateId;
					var typeId =row.typeId;
					//学生自拟
					if(typeId=="21"){
						if (result == "6"){
							result = '<font style="color:RED;">自拟课题未审核</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">教师审核未通过</font>';
						}else if (result == "2") {
							result = '<font style="color:GREEN;">教师审核通过</font>';
						}else if (result == "3"){
							result = '<font style="color:RED;">教学秘书审核未通过</font>';
						}else if (result == "4"){
							result = '<font style="color:GREEN;">教学秘书审核通过</font>';
						}
						//教师命题
					}if(typeId="22"){
						if (result == "0"){
							result = '<font style="color:RED;">新增课题未审核</font>';
						}else if (result == "1"){
							result = '<font style="color:RED;">教学秘书审核未通过</font>';
						}else if (result == "2"){
							result = '<font style="color:GREEN;">教学秘书审核通过</font>';
						}
					}
					return result;
				}},
				{field:'chk',title:'师生双向选题',width:120,align:'center',
					formatter : function(value, row, index) {
							var result = row.chk;
							if (result == "0") {
								result = '<font style="color:RED;">教师未审核</font>';
							}else if (result == "1"){
								result = '<font style="color:RED;">教师拒绝选题</font>';
							}else if (result == "2"){
								result = '<font style="color:GREEN;">教师同意选题</font>';
							}else if (result == "7"){
								result = '<font style="color:RED;">退回修改</font>';
							}else if (result == "6"){
								result = '<font style="color:GREEN;">学生已申请</font>';
							}
							return result;
						}},
			{field :'button',title : '操作',width : 210,align : 'center',
			formatter : format
			}
		]],
		
    	onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'撤回申请',plain:true,iconCls:'icon-remove'}); 
        },
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	

 });