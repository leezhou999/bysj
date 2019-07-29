/**
*学生申请自拟课题
*/

$(function(){
	
	$("#add").dialog('close');
	$("#tea_name").textbox('textbox').css("color", "#a2b5cd");
	$("#topicId").textbox('textbox').css("color", "#a2b5cd");
	$("#user_name").textbox('textbox').css("color", "#a2b5cd");
	$("#user_id").textbox('textbox').css("color", "#a2b5cd");
	$("#stu_id").textbox('textbox').css("color", "#a2b5cd");
	$("#tea_id").textbox('textbox').css("color", "#a2b5cd");
	$("#dName").textbox('textbox').css("color", "#a2b5cd");
	$("#majorName").textbox('textbox').css("color", "#a2b5cd");
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