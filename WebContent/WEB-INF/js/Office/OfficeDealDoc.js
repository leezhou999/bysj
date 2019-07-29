//教师审核文档的js
var row;
function show(index) {
	$('#t1').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	var row = $('#t1').datagrid('getSelected');
	// alert('hello'+row.docName);
	if (row.docName != null) {
		$.messager.show({
			title : '提示',
			msg : '请慎重操作！',
			timeout : '1500'
		});
		$("#sName").textbox("setValue", row.sName);
		$("#docId").textbox("setValue", row.docId);
		$("#docName").textbox("setValue", row.docName);
		$("#dName").textbox("setValue", row.dName);
		$("#cName").textbox("setValue", row.cName);
		$("#time").textbox("setValue", row.time);
		$("#type").textbox("setValue", row.type);
		$("#sName").textbox('textbox').css("color", "#a2b5cd");
		$("#docId").textbox('textbox').css("color", "#a2b5cd");
		$("#docName").textbox('textbox').css("color", "#a2b5cd");
		$("#dName").textbox('textbox').css("color", "#a2b5cd");
		$("#cName").textbox('textbox').css("color", "#a2b5cd");
		$("#time").textbox('textbox').css("color", "#a2b5cd");
		$("#type").textbox('textbox').css("color", "#a2b5cd");
		$("#dia").dialog("open");
	}

}
$(function() {
	$("#btn-find").click(function(){
		var stuId1 = $("#stuId1").val();
		var sName1 = $("#sName1").val();
		var tName1 = $("#tName1").val();
		var cl1 = $("#cl1").val();
		$("#t1").datagrid('load',{
			stuId:stuId1,
			sName:sName1,
			tName:tName1,
			cl:cl1
		});
		
	});
	// dialog设置
	$("#dia").dialog({
		title : '审核学生文档',
		width : 350,
		height : 450,
		closed : false,
		cache : false,
		modal : true
	});
	$("#dia").dialog("center");
	$("#dia").dialog("close");

	// datagrid设置
	function format(val, row, index) {
		return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='show("
				+ index + ")'></a>";
	}
	$("#t1").datagrid({
		height:420,
		width:985,
		url : "Office/QuerryAllDocDeal",
		method : 'post',
		columns : [ [ {
			field : 'docId',
			title : '文档编号',
			width : 100,
			align : 'center'
		}, {
			field : 'docName',
			title : '文档名称',
			width : 100,
			align : 'center'
		}, {
			field : 'sName',
			title : '学生姓名',
			width : 60,
			align : 'center'
		},
		{
			title:'指导老师',
			field:'tName',
			width:80,
			align:'center'
		}, 
		{
			field : 'dName',
			title : '院系',
			width : 100,
			align : 'center'
		}, {
			field : 'cName',
			title : '班级',
			width : 100,
			align : 'center'
		}, {
			field : 'time',
			title : '上传时间',
			width : 140,
			align : 'center'
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
			field : 'type',
			title : '状态',
			width : 100,
			align : 'center',
			formatter : function(value, row, index) {
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
			}
		}, {
			title : '操作',
			field : 'button',
			width : 120,
			align : 'center',
			formatter : format
		} ] ],
		onLoadSuccess : function(data) {// 此处意思是当dategrid加载成功时候，设置样式
			$("a[name='opera']").linkbutton({
				text : '审核',
				plain : true,
				iconCls : 'icon-man'
			});
		},
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 15, 20],
		
		 toolbar:[{text:'多条件查询',
				iconCls:'icon-add',
				handler:function(){//点击按钮时执行的操作
					$("#cc").layout('expand','north');
				}
	        }]
	});
	// 按钮事件设置
	$("#btn-ok").click(function() {
		var change = $("#change").val();
		if (change == "0") {
			$.messager.show({
				title : '提示',
				msg : "请选择操作内容",
				timeout : 5000
			});
		} else {
			$.ajax({
				url : "Office/updateDoc",
				data : {
					docId : $("#docId").textbox("getValue"),
					state : $("#change").val()
				},
				method : "post",
				dataType : "json",
				success : function(data) {
					if (data.flag) {
						$("#dia").dialog("close");
						$.messager.show({
							title : '提示',
							msg : "操作成功！",
							timeout : '1500'
						});
						$("#t1").datagrid("reload");
					}
				}
			});
		}
	});
	$("#btn-cos").click(function() {
		$("#dia").dialog("close");
	});
})