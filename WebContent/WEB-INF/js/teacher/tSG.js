//教师查看学生答辩分组
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
function chk(index){
	//1.获取用户的选择
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	/*row = $('#dg').datagrid('getSelected');*/
	  arr=$("#dg").datagrid('getSelections');
	//2.如果用户的选择多于一行，提示只能针对一行进行更新
	if(arr.length != 1){
		alert("请选择一条记录审核！");
	}else{
		//1.打开对话框，将用户选择的记录信息写入表单控件
		$("#edit").dialog({
			title:'答辩审批-信息确认',
			modal: true
		});
		$("#editform").form('reset');//重置表单
		
		$("#editform").form('load',{
			user_id:arr[0].user_id,
			user_name:arr[0].user_name,
			user_sex:arr[0].user_sex,
			dName:arr[0].dName,
			majorName:arr[0].majorName,
			cName:arr[0].cName,
			address:arr[0].address,
			tel:arr[0].tel,
			topicName:arr[0].topicName,
		});
		$("#edit").dialog('open');
	}
}
$(function(){
	$("#edit").dialog('close');
	function format(val,row,index){
		if(row.apply!="2"){
			return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='chk("
			+index+ ")'>答辩审批</a>";
		}
		return '<font style="color:BLUE;">不可重复审批</font>';
	 }
	$("#dg").datagrid({
		width:1270,
		height:520,
		fitColumns:true,
		nowrap:false,
		url:"Admin/querySG",
		method:'post',
		columns:[
			[
             {title:'学生答辩审批',colspan:12},
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
			{field:'topicName',title:'课题名称',width:280,align:'left'},
			{field:'apply',title:'答辩申请状态',widht:230,align:'center',
				formatter : function(value, row, index) {
					var result = row.apply;
					if (result == "0") {
						result = '<font style="color:RED;">学生未申请</font>';
					}else if (result == "1"){
						result = '<font style="color:RED;">指导教师不通过</font>';
					}else if (result == "2"){
						result = '<font style="color:GREEN;">指导教师通过</font>';
					}else if (result == "6"){
						result = '<font style="color:BLUE;">学生已申请</font>';
					}
					return result;}},
			{field :'button',title : '操作',width : 150,align : 'center',
						formatter : format
					}
			
		]],
		onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
        	$("a[name='opera']").linkbutton({text:'答辩审批',plain:true,iconCls:'icon-edit'}); 
        	
        },
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});

 });