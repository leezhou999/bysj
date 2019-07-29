/**
 * 对话框文件
 */
var arr;
var row;
function show(index){
	 $('#result').datagrid('selectRow',index);// 重点！！！这里得到所选择的行数 
     var row = $('#result').datagrid('getSelected'); 
     //alert(row.location);
    if(row.location!=null){
 
    	/*window.open("/doc"+row.location);*/
    	location.href="Student/download?location="+row.location;
    }		
    
}
function view(index){
	 $('#result').datagrid('selectRow',index);// 重点！！！这里得到所选择的行数 
    var row = $('#result').datagrid('getSelected'); 
    //alert(row.location);
   if(row.location!=null){

   	/*window.open("/doc"+row.location);*/
	   //alert(row.location.substring(0,row.location.length-4));
	   location.href="js/pdfjs/generic/web/viewer.html?file="+row.location.substring(0,row.location.lastIndexOf("."))+".pdf";
   }		
   
}
$(function(){

	$("#btn-find").click(function(){
		var stuId = $("#stuId").val();
		var sName = $("#sName").val();
		var tName = $("#tName").val();
		var cl = $("#cl").val();
		$("#result").datagrid('load',{
			stuId:stuId,
			sName:sName,
			tName:tName,
			cl:cl
		});
		
	});

	function format(val,row,index){//用来将最后一列由标签设置为easyUI的按钮
		 //主要看下面的返回值
  	 return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='show("+index+")'>下载</a>|" +
  	 		"<a href='javascript:void(0);' name='view' class='easyui-linkbutton' onclick='view("+index+")'>在线预览</a>";
 	 }
	$("#stu_doc").dialog('close');
	$("#stu_info").dialog('close');
	$("#add").dialog('open');
	$('#add').dialog({    
	    title: '多条件查询',    
	    width: 400,    
	    height: 200,    
	    closed: false,    
	    cache: false,     
	    modal: true,
	    draggable:false
	});  
	$("#btn-ok").click(function(){
		
			var select= $('input[name="type"]:checked ').val();
			if(select==null){
				alert("请选择查询条件");
			}
			if(select=="teacher"){
				$("#add").dialog('close');
				$("#result").datagrid({
					width:800,
					height:400,
					url:"Office/QuerryTeacher",
					method:'post',
					dataType:"json",
			        columns:[[ 
			        {
			        	 field:'',
				       	 title:'all',
			             width:100,
			             checkbox:true,
			             align:'center'

			        	},    
			        {
			        		field:'workId',
			        		title:'工号',
			        		width:100,
			        		align:'center'
			        	},    
			        {
			        		field:'tName',
			        		title:'姓名',
			        		width:100,
			        		align:'center'
			        	},
			        {
			        		field:'tSex',
			        		title:'性别',
			        		width:100,
			        		align:'center'
			        	}, 
			        {
			        		field:'tTel',
			        		title:'电话',
			        		width:150,
			        		align:'center'
			        	} , 
			      
			        ]],
			        singleSelect:'true',//每次只能选择一个  单选
			        pagination:'true',   //添加分页工具栏
			        pageNumber:1,   //初始化页码
			        pageSize:10,      //初始化每页记录数
			        pageList:[10,15,20],   //页面显示记录数选择列表
			        toolbar:[{
						text:'选择查询条件',
						iconCls:'icon-add',
						handler:function(){//点击按钮时执行的操作
							$("#add").dialog('open');
						}
			        },{
						text:'查询指导学生',
						iconCls:'icon-add',
						handler:function(){
							//查询指定老师指导的学生
							arr = $("#result").datagrid('getSelections');
							
							//$("#stu_info").dialog('open');
							$("#result").datagrid({
								width:950,
								url:"Office/QuerryStudent?workId="+arr[0].workId,
								method:'post',
								dataType:"json",
						        columns:[[ 
						        	    {field:'',title:'all',width:100,checkbox:true,align:'center'}, 
						        	    {field:'stuId',title:'学生学号',width:100},
						        	    {field:'sName',title:'学生姓名',width:100},
						    			{field:'cName',title:'班级',width:100},
						    			{field:'dName',title:'院系',width:100},
						    			{field:'sSex',title:'性别',width:100},
						    			{field:'sTel',title:'联系电话',width:100},
                                 ]],
						    	singleSelect:'true',//每次只能选择一个  单选
						        pagination:'true',   //添加分页工具栏
						        pageNumber:1,   //初始化页码
						        pageSize:10,      //初始化每页记录数
						        pageList:[10,15,20,25,30],   //页面显示记录数选择列表
						        toolbar:[{
									text:'查询该生文档',
									iconCls:'icon-add',
									handler:function(){//点击按钮时执行的操作
										arr = $("#result").datagrid('getSelections');
										//$("#stu_info").dialog('close');
										//$("#stu_doc").dialog('open');
										$("#result").datagrid({
											url:"Office/QuerryStuDoc?stuId="+arr[0].stuId,
											method:'post',
											dataType:"json",
									        columns:[[
									        	 {field:'',title:'all',width:100,checkbox:true,align:'center'},
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
													title :'上传者',
													field :'uploader',
													width :80,
													align:'center',
													formatter : function(value, row, index) {
														var result = row.docName;
														if (result == "申报表") {
															result = '<font style="color:RED;">教师</font>';
														} else if (result == "任务书") {
															result = '<font style="color:RED;">教师</font>';
														} else if (result == "评阅教师评审表") {
															result = '<font style="color:RED;">教师</font>';
														} else if (result == "答辩专家组评审表") {
															result = '<font style="color:RED;">教师</font>';
														} else if (result == "成绩表") {
															result = '<font style="color:RED;">教师</font>';
														} else if(result=="指导教师评阅表"){
															result = '<font style="color:RED;">教师</font>';
														}else if (result == "选题表") {
															result = '<font style="color:GREEN;">学生</font>';
														} else if (result == "开题报告") {
															result = '<font style="color:GREEN;">学生</font>';
														} else if (result == "指导记录表") {
															result = '<font style="color:GREEN;">学生</font>';
														} else if (result == "答辩申请表") {
															result = '<font style="color:GREEN;">学生</font>';
														} else if (result == "毕业论文") {
															result = '<font style="color:GREEN;">学生</font>';
														} 
														return result;
													},
												} ,
												{
													title:'文档状态',
													field:'type',
													width:80,
													align:'center',
													formatter: function (value, row, index) {
														var result = value;
														if(result=="未审核"){
															result = '<font style="color:RED;">未审核</font>';
														}
														else if(result=="一级审核未通过"){
															result = '<font style="color:RED;">一级审核未通过</font>';
														}
														else if(result=="一级审核通过"){
															result = '<font style="color:GREEN;">一级审核通过</font>';
														}
														else if(result=="二级审核未通过"){
															result = '<font style="color:RED;">二级审核未通过</font>';
														}
														else if(result=="二级审核通过"){
															result = '<font style="color:GREEN;">二级审核通过</font>';
														}
														return result;
														},
												},
												{
													title:'操作',
													field:'button',
													width:120,
													align:'center',
													formatter:format
												}
											]],
									    	singleSelect:'true',//每次只能选择一个  单选
									        pagination:'true',   //添加分页工具栏
									        pageNumber:1,   //初始化页码
									        pageSize:10,      //初始化每页记录数
									        pageList:[10,15,20],   //页面显示记录数选择列表
									        toolbar:[{
												text:'选择查询条件',
												iconCls:'icon-add',
												handler:function(){//点击按钮时执行的操作
													$("#add").dialog('open');
												}
									        }]
									     });
									}
						        }
						        ]
						     });
						}
			        }
			        ]
			     });
			}else if(select=="student"){
				$("#add").dialog('close');
				$("#result").datagrid({
					width:800,
					height:400,
					url:"Office/QuerryAllStudent",
					method:'post',
					dataType:"json",
			        columns:[[ 
			        	{field:'',title:'all',width:100,checkbox:true,align:'center'}, 
		        	    {field:'stuId',title:'学生学号',width:100},
		        	    {field:'sName',title:'学生姓名',width:100},
		    			{field:'cName',title:'班级',width:100},
		    			{field:'dName',title:'院系',width:100},
		    			{field:'sSex',title:'性别',width:100},
		    			{field:'sTel',title:'联系电话',width:100},
			        ]],
			        singleSelect:'true',//每次只能选择一个  单选
			        pagination:'true',   //添加分页工具栏
			        pageNumber:1,   //初始化页码
			        pageSize:10,      //初始化每页记录数
			        pageList:[10,15,20],   //页面显示记录数选择列表
			        toolbar:[{
						text:'选择查询条件',
						iconCls:'icon-add',
						handler:function(){//点击按钮时执行的操作
							$("#add").dialog('open');
						}
			        },{
						text:'查询该生文档',
						iconCls:'icon-add',
						handler:function(){//点击按钮时执行的操作
							arr = $("#result").datagrid('getSelections');
						//	$("#stu_info").dialog('close');
							//$("#stu_doc").dialog('open');
							$("#result").datagrid({
								width:880,
								url:"Office/QuerryStuDoc?stuId="+arr[0].stuId,
								method:'post',
								dataType:"json",
						        columns:[[
						        	 {field:'',title:'all',width:100,checkbox:true,align:'center'},
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
										title:'文档状态',
										field:'type',
										width:80,
										align:'center',
										formatter: function (value, row, index) {
											var result = value;
											if(result=="未审核"){
												result = '<font style="color:RED;">未审核</font>';
											}
											else if(result=="一级审核未通过"){
												result = '<font style="color:RED;">一级审核未通过</font>';
											}
											else if(result=="一级审核通过"){
												result = '<font style="color:GREEN;">一级审核通过</font>';
											}
											else if(result=="二级审核未通过"){
												result = '<font style="color:RED;">二级审核未通过</font>';
											}
											else if(result=="二级审核通过"){
												result = '<font style="color:GREEN;">二级审核通过</font>';
											}
											return result;
											},
									},
									{
										title:'操作',
										field:'button',
										width:120,
										align:'center',
										formatter:format
									}
								]],
						    	singleSelect:'true',//每次只能选择一个  单选
						        pagination:'true',   //添加分页工具栏
						        pageNumber:1,   //初始化页码
						        pageSize:10,      //初始化每页记录数
						        pageList:[10,15,20],   //页面显示记录数选择列表
						        toolbar:[{text:'选择查询条件',
									iconCls:'icon-add',
									handler:function(){//点击按钮时执行的操作
										$("#add").dialog('open');
									}
						        }]
						     });
						}
			        
			        }
			        ]
			     });
			}else if(select=="alldoc"){
				
				$("#add").dialog('close');
				
				
				$("#result").datagrid({
					width:945,
					height:400,
					url:"Office/QuerryAllDoc",
					method:'post',
					dataType:"json",
			        columns:[[
			        	 {field:'',title:'all',width:100,checkbox:true,align:'center'},
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
							title :'上传者',
							field :'uploader',
							width :80,
							align:'center',
							formatter : function(value, row, index) {
								var result = row.docName;
								if (result == "申报表") {
									result = '<font style="color:RED;">教师</font>';
								} else if (result == "任务书") {
									result = '<font style="color:RED;">教师</font>';
								} else if (result == "评阅教师评审表") {
									result = '<font style="color:RED;">教师</font>';
								} else if (result == "答辩专家组评审表") {
									result = '<font style="color:RED;">教师</font>';
								} else if (result == "成绩表") {
									result = '<font style="color:RED;">教师</font>';
								} else if(result=="指导教师评阅表"){
									result = '<font style="color:RED;">教师</font>';
								}else if (result == "选题表") {
									result = '<font style="color:GREEN;">学生</font>';
								} else if (result == "开题报告") {
									result = '<font style="color:GREEN;">学生</font>';
								} else if (result == "指导记录表") {
									result = '<font style="color:GREEN;">学生</font>';
								} else if (result == "答辩申请表") {
									result = '<font style="color:GREEN;">学生</font>';
								} else if (result == "毕业论文") {
									result = '<font style="color:GREEN;">学生</font>';
								} 
								return result;
							},
						} ,
						{
							title:'文档状态',
							field:'type',
							width:80,
							align:'center',
							formatter: function (value, row, index) {
								var result = value;
								if(result=="未审核"){
									result = '<font style="color:RED;">未审核</font>';
								}
								else if(result=="一级审核未通过"){
									result = '<font style="color:RED;">一级审核未通过</font>';
								}
								else if(result=="一级审核通过"){
									result = '<font style="color:GREEN;">一级审核通过</font>';
								}
								else if(result=="二级审核未通过"){
									result = '<font style="color:RED;">二级审核未通过</font>';
								}
								else if(result=="二级审核通过"){
									result = '<font style="color:GREEN;">二级审核通过</font>';
								}
								return result;
								},
						},
						{
							title:'操作',
							field:'button',
							width:120,
							align:'center',
							formatter:format
						}
					]],
			    	singleSelect:'true',//每次只能选择一个  单选
			        pagination:'true',   //添加分页工具栏
			        pageNumber:1,   //初始化页码
			        pageSize:10,      //初始化每页记录数
			        pageList:[10,15,20],   //页面显示记录数选择列表
			        toolbar:[{text:'多条件查询',
						iconCls:'icon-add',
						handler:function(){//点击按钮时执行的操作
							$("#cc").layout('expand','north');
						}
			        }]
			        

			     });
			}
			
		
	});
	
	
});
