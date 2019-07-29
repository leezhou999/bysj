/**
 *教师文件上传对话框文件
 */
var row;
var arr;
function tUpload(index){
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	row = $('#dg').datagrid('getSelected');
	arr=$("#dg").datagrid('getSelections');
	
	$("#stu_id").textbox('textbox').css("color", "#a2b5cd");
	$("#tea_id").textbox('textbox').css("color", "#a2b5cd");
	$("#deptId").textbox('textbox').css("color", "#a2b5cd");
	$("#majorId").textbox('textbox').css("color", "#a2b5cd");
	$("#classesId").textbox('textbox').css("color", "#a2b5cd");
	//设置选择文件按钮
	$("#location").filebox({
		 buttonText: '选择文件', 
		 buttonAlign: 'right' 
	});
//	设置上传按钮
	$("#btn-ok").linkbutton({ 
		 width:120,
		 height:30,
		 size:'large'
	});
	$("#fileUpload").dialog({
		title:'文件上传',
		maximizable:true,
		resizable:false,
		draggable:false,
		border:'thin',
		top:20,
		left:415,
		closable:true
	});
	
	$("#fileUpload").form('load',{
		stu_id:arr[0].user_id,
		tea_id:arr[0].tea_name,
		deptId:arr[0].dName,
		majorId:arr[0].majorName,
		classesId:arr[0].cName,
		
	});
	$("#fileUpload").dialog('open');
	$("#btn-ok").click(function(event){
		var type = $("#typeId").val();
		var doc = $("#location").filebox('getValue');
		if(type==""&&doc==""){
			alert("请选择上传文件并选择文档类型！");
			return false;
		}
		else if(type==""){
			alert("请选择文件类型！");
			return false;
		}
		else if(doc==""){
			alert("请选择上传文件！");
			return false;
		}
		else{
			return true;
		}
	});
}

$(function() {
		$("#fileUpload").dialog('close');
		function format(val,row,index){
				return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='tUpload("
				+index+ ")'>上传文档</a>";
		 }
		$("#dg").datagrid({
			width :1280,
			height : 540,
			fitColumns:true,
			nowrap:false,
			url : "Stu/tQuerySGroupInfo",
			method : 'post',
			columns:[
				[
	             {title:'小组信息',colspan:11},
	            ],[
				{field:'user_id',title:'学号',width:100,align:'center'},
				{field:'user_name',title:'学生姓名',width:100,align:'center'},
				{field:'user_sex',title:'性别',width:50,align:'center'},
				{field:'dName',title:'所属院系',width:100,align:'center'},
				{field:'majorName',title:'所属专业',width:100,align:'center'},
				{field:'cName',title:'所属班级',width:100,align:'center'},
				{field:'address',title:'联系地址',width:150,align:'center'},
				{field:'tel',title:'联系电话',width:100,align:'center'},
				{field:'tea_name',title:'导师姓名',width:100,align:'center'},
				{field:'topicName',title:'课题名称',width:230,align:'left'},
				{field :'button',title : '操作',width : 150,align : 'center',
					formatter : format
				}
			]],
			onLoadSuccess : function(data) {// 此处意思是当dategrid加载成功时候，设置样式
				$("a[name='opera']").linkbutton({
					text : '上传文档',
					plain : true,
					iconCls : 'icon-man'
				});
			},
			pagination : 'true',
			pageSize : 10,
			SelectOnCheck : 'true',
			pageList : [ 10, 15, 20 ]
		});
	});
