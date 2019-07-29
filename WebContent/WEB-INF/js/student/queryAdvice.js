//学生 所有文档查看
var row;
function show(index){
	 $('#ta').datagrid('selectRow',index);// 重点！！！这里得到所选择的行数 
     var row = $('#ta').datagrid('getSelected'); 
    // alert("下载:"+row.location);
    if(row.location!=null){
 
    	location.href="Student/download?location="+row.location;
    }		  
}
function view(index){
	 $('#ta').datagrid('selectRow',index);// 重点！！！这里得到所选择的行数 
    var row = $('#ta').datagrid('getSelected'); 
   if(row.location!=null){
	  // alert("在线预览"+row.location.substring(0,row.location.lastIndexOf(".")));
	   //点击预览时候更改文档类型为已读模式（一级审核已通过02）
	   $.ajax({
		   url:"Student/updateState",
			data:{
				location:row.location
			},
			method:"post",
			dataType:"json",
			success:function(data){
				if(data.flag){
					$("#t1").datagrid("reload");
					location.href="js/pdfjs/generic/web/viewer.html?file="+row.location.substring(0,row.location.lastIndexOf("."))+".pdf";
				}
			}
	   });
	   
   }		
   
}
$(function(){
	function format(val,row,index){//用来将最后一列由标签设置为easyUI的按钮
		 //主要看下面的返回值
   	 return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='show("+index+")'>下载</a>|" +
   	 		"<a href='javascript:void(0);' name='view' class='easyui-linkbutton' onclick='view("+index+")'>在线预览</a>";
  	 }
	$("#ta").datagrid({
		width:920,
		height:499,
		url:"Student/queryAdvice",
		method:'post',
		columns:[[
			{
				title:'文档名'	,
				field:'docName',
				width:120,
				align:'center'
			},
			{
				title:'学生姓名',
				field:'sName',
				width:80,
				align:'center'
			},
			{
				title:'指导老师',
				field:'tName',
				width:80,
				align:'center'
			},
			{
				title:'所属院系',
				field:'dName',
				width:120,
				align:'center'
			},
			{
				title:'所属专业',
				field:'cName',
				width:80,
				align:'center'
			},
			{
				title:'上传时间',
				field:'time',
				width:150,
				align:'center'
			},
			{
				title:'地址',
				field:'location',
				hidden:'true',
				width:10,
				align:'center'
			},
			{
				title:'状态',
				field:'type',
				width:80,
				align:'center',
				formatter: function (value, row, index) {
					var result = value;
					if(result=="未审核"){
						result = '<font style="color:RED;">未读</font>';
					}
					else if(result=="一级审核通过"){
						result = '<font style="color:GREEN;">已读</font>';
					}
					return result;
					},
			},
			{
				title:'操作',
				field:'button',
				width:191,
				align:'center',
				formatter:format
			}
		]],
		onLoadSuccess:function(data){//此处意思是当dategrid加载成功时候，设置样式 
			$("a[name='opera']").linkbutton({text:'下载',plain:true,iconCls:'icon-man'}); 
        	$("a[name='view']").linkbutton({text:'在线预览',plain:true,iconCls:'icon-man'});  
        	
        },
		pagination:'true',
		pageSize:10,
		SelectOnCheck:'true',
		pageList:[10,15,20]
	});
});