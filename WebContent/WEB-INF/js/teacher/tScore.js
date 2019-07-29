//教师查看学生成绩

$(function(){
	
	$("#dg").datagrid({
		width:1270,
		height:520,
		fitColumns:true,
		nowrap:false,
		url:"Admin/queryTScore",
		method:'post',
		columns:[
			[
             {title:'学生成绩',colspan:14},
            ],[
			{field:'stu_id',title:'学号',width:100,align:'center'},
			{field:'user_name',title:'学生姓名',width:100,align:'center'},
			{field:'user_sex',title:'性别',width:50,align:'center'},
			{field:'dName',title:'所属院系',width:100,align:'center'},
			{field:'majorName',title:'所属专业',width:100,align:'center'},
			{field:'cName',title:'所属班级',width:100,align:'center'},
			{field:'topicName',title:'课题名称',width:280,align:'left'},
			{field:'tea_name',title:'指导教师',width:100,align:'center'},
			{field:'score1',title:'指导教师评阅成绩',width:140,align:'center'},
			{field:'score2',title:'评阅教师评审成绩',width:140,align:'center'},
			{field:'score3',title:'答辩成绩',width:100,align:'center'},
			{field:'score4',title:'总成绩',width:80,align:'center',
				formatter : function(value, row, index) {
				result=row.score1*0.2+row.score2*0.2+row.score3*0.6
				return result;
				},},
			{field:'scoreType',title:'成绩类别',width:100,align:'center',
				formatter : function(value, row, index) {
					var result = row.scoreType;
					if (result == "11") {
						result = '<font style="color:GREEN;">一辩</font>';
					}else if (result == "12"){
						result = '<font style="color:RED;">二辩</font>';
					}
					return result;}},
			{field:'remark',title:'备注',width:150,align:'center'},
			
		]],
		
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});

 });