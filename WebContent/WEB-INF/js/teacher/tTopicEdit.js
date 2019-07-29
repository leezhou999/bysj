/**
*对话框文件
*/
var param;
$(function(){
	
	$("#edit").dialog('close');
	$("#topicId2").textbox('textbox').css("color", "#a2b5cd");
	
	$("#user_name2").textbox('textbox').css("color", "#a2b5cd");
	$("#user_id2").textbox('textbox').css("color", "#a2b5cd");
	$("#tea_id2").textbox('textbox').css("color", "#a2b5cd");
	$("#dName2").textbox('textbox').css("color", "#a2b5cd");
	$("#majorName2").textbox('textbox').css("color", "#a2b5cd");
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