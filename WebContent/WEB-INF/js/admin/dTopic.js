//教秘查询本院所有课题,审批教师课题
var arr;
var row;
var param;
//教秘课题审批
function sApplyTopic1(index){
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	row = $('#dg').datagrid('getSelected');
	arr=$("#dg").datagrid('getSelections');
	var topicId = arr[0].topicId;
			/*alert(topicId);*/
			$.ajax({
				url:'Admin/dTopicChk',
				method:'post',
				data:{strid:topicId},
				dataType:'json',
				success:function(result){
					$.messager.show({
						title:'审批结果',
						msg:result.info,
						timeout:3000,
					});
				},
			});
			$("#dg").datagrid('reload');
}
$(function(){
	$("#dg").datagrid({
		width:1300,
		height:520,
		nowrap:false,
		fitColumns:true,
		url:"Admin/queryDTopic",
		method:'post',
		columns:[[
             {title:'本院课题一览',colspan:12},
            ],[
               {title:'注意：教师课题需教学秘书一审通过，学生自拟课题需教师一审通过，教学秘书二审通过！',colspan:12}
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
			{field:'stateId',title:'课题状态 | 学生申请状态',width:200,align:'center',
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
							result = '<font style="color:RED;">教师课题待审核</font>';
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
		]],
		toolbar:[{
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

