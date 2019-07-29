/**
 *学生文件上传对话框文件
 */
$(function(){
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
	
});