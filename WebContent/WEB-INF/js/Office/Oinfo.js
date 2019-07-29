/**
 * 对话框文件
 */
var arr;
$(function(){
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
			alert(select);
			if(select=="teacher"){
				$("#add").dialog('close');
				$("#result").datagrid({
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
			        pageSize:5,      //初始化每页记录数
			        pageList:[5,10,15,20,25,30],   //页面显示记录数选择列表
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
							
							$("#stu_info").dialog('open');
							$("#stu_info_a").datagrid({
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
						        pageSize:5,      //初始化每页记录数
						        pageList:[5,10,15,20,25,30],   //页面显示记录数选择列表
						        toolbar:[{
									text:'查询该生文档',
									iconCls:'icon-add',
									handler:function(){//点击按钮时执行的操作
										arr = $("#stu_info_a").datagrid('getSelections');
										$("#stu_info").dialog('close');
										$("#stu_doc").dialog('open');
										$("#stu_doc_a").datagrid({
											url:"Office/QuerryStuDoc?stuId="+arr[0].stuId,
											method:'post',
											dataType:"json",
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
												}
											]],
									    	singleSelect:'true',//每次只能选择一个  单选
									        pagination:'true',   //添加分页工具栏
									        pageNumber:1,   //初始化页码
									        pageSize:5,      //初始化每页记录数
									        pageList:[5,10,15,20,25,30],   //页面显示记录数选择列表
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
			        pageSize:5,      //初始化每页记录数
			        pageList:[5,10,15,20,25,30],   //页面显示记录数选择列表
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
							$("#stu_info").dialog('close');
							$("#stu_doc").dialog('open');
							$("#stu_doc_a").datagrid({
								url:"Office/QuerryStuDoc?stuId="+arr[0].stuId,
								method:'post',
								dataType:"json",
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
									}
								]],
						    	singleSelect:'true',//每次只能选择一个  单选
						        pagination:'true',   //添加分页工具栏
						        pageNumber:1,   //初始化页码
						        pageSize:5,      //初始化每页记录数
						        pageList:[5,10,15,20,25,30],   //页面显示记录数选择列表
						     });
						}
			        
			        }
			        ]
			     });
			}else if(select=="class"){
				
			}else if(select=="alldoc"){
				$("#add").dialog('close');
				$("#result").datagrid({
					url:"Office/QuerryAllDoc",
					method:'post',
					dataType:"json",
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
						}
					]],
			    	singleSelect:'true',//每次只能选择一个  单选
			        pagination:'true',   //添加分页工具栏
			        pageNumber:1,   //初始化页码
			        pageSize:5,      //初始化每页记录数
			        pageList:[5,10,15,20,25,30],   //页面显示记录数选择列表
			     });
			}
			
		
	});
	
	
});
