/**
 *学生编辑个人信息
 */
$(function(){
	$("#user_id").textbox('textbox').css("color", "#a2b5cd");
	$("#user_name").textbox('textbox').css("color", "#a2b5cd");
	$("#user_sex").textbox('textbox').css("color", "#a2b5cd");
	$("#deptId").textbox('textbox').css("color", "#a2b5cd");
	$("#majorId").textbox('textbox').css("color", "#a2b5cd");
	$("#classesId").textbox('textbox').css("color", "#a2b5cd");
	
	$("#stuInfoEdit").dialog({
		maximizable:true,
		resizable:false,
		draggable:false,
		border:'thin',
		top:20,
		left:415,
		closable:true
	});
	
	
});