/**
 *修改密码
 */
$(function(){
	$("#user_id").textbox('textbox').css("color", "#a2b5cd");
	$("#user_name").textbox('textbox').css("color", "#a2b5cd");
	$("#user_sex").textbox('textbox').css("color", "#a2b5cd");
	
	
	$("#userInfoEdit").dialog({
		maximizable:true,
		resizable:false,
		draggable:false,
		border:'thin',
		top:20,
		left:415,
		closable:true
	});
	
	
});