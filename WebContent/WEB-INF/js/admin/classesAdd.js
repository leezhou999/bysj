/**
*对话框文件
*/

$(function(){
	
	/*$("#add").dialog({
		title:'添加信息',
		height:400,
		width:410,
	});*/
	$("#add").dialog('close');
	//对文本框进行验证
	$("#user_id").textbox({
		required:false,//必须输入
		validType:'length[1,10]',//长度限定
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	$("#user_name").textbox({
		required:false,
		validType:'length[1,50]',
		iconCls:'icon-man',
		iconAlign:'left',
		width:150,
	});
	$("#role_id").textbox({
		required:false,
		validType:'length[1,50]',
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
	$("#password").textbox({
		required:false,
		validType:'length[1,10]',
		width:150,
	});
	$("#isOk").textbox({
		required:false,
		validType:'length[1,10]',
		width:150,
	});

	$("#btn-cancle").click(function(){
		$("#add").dialog('close');
	});
	
	
});