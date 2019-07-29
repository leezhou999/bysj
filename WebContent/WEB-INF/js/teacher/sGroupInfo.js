//显示sGroupInfo,教师查看所在小组信息
var flag;//全局变量，标记'确定'按钮的含义，是增加还是更新
var arr;
var param;
$(function(){
	$("#dg").datagrid({
		width:1280,
		height:549,
		fitColumns:true,
		nowrap:false,
		url:"Stu/tQuerySGroupInfo",
		method:'post',
		columns:[
			[
             {title:'小组信息',colspan:10},
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
		]],
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	

 });