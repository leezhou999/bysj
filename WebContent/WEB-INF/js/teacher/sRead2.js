//教师查看所有学生文档
var row;

function downloadDoc(index) {
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	var row = $('#dg').datagrid('getSelected');
	//alert(row.location);
	if (row.location != null) {
		/* window.open("/doc"+row.location); */
		location.href = "Stu/download?location=" + row.location;
	}
}
//文档预览
function view(index) {
	$('#dg').datagrid('selectRow', index);// 重点！！！这里得到所选择的行数
	var row = $('#dg').datagrid('getSelected');
	//alert(row.location);
	if (row.location != null) {
		/* window.open("/doc"+row.location); */
		//alert(row.location.substring(0, row.location.length - 4));
		location.href="js/pdfjs/generic/web/viewer.html?file="+row.location.substring(0,row.location.lastIndexOf("."))+".pdf";
	}
}
$(function() {
	/*function showSliderBar() {
	      this.classList.toggle('toggled');
	      outerContainer.classList.add('sidebarMoving');
	      outerContainer.classList.toggle('sidebarOpen');
	      PDFViewerApplication.sidebarOpen =
	        outerContainer.classList.contains('sidebarOpen');
	      if (PDFViewerApplication.sidebarOpen) {
	        PDFViewerApplication.refreshThumbnailViewer();
	      }
	      PDFViewerApplication.forceRendering();
	};*/
	function format(val, row, index) {// 用来将最后一列由标签设置为easyUI的按钮
		// 主要看下面的返回值
		return "<a href='javascript:void(0);' name='opera' class='easyui-linkbutton' onclick='downloadDoc("
				+ index
				+ ")'>下载</a>|"
				+ "<a href='javascript:void(0);' name='view' class='easyui-linkbutton' onclick='view("
				+ index + ")'>在线预览</a>";
	}
	$("#dg").datagrid({
		width :1280,
		height : 540,
		fitColumns:true,
		url : "Admin/tQueryAllSDoc",
		method : 'post',
		columns : [[
             {title:'学生文档一览',colspan:11},
            ], [ {
			title : '文档类型',
			field : 'typeName',
			width : 100,
			align : 'center'
		}, {
			title : '学号',
			field : 'stu_id',
			width : 120,
			align : 'center'
		},{
			title : '姓名',
			field : 'stu_name',
			width : 120,
			align : 'center'
		},  {
			title : '指导教师',
			field : 'user_name',
			width : 80,
			align : 'center'
		}, {
			title : '所属院系',
			field : 'dName',
			width : 80,
			align : 'center'
		}, {
			title : '所属专业',
			field : 'majorName',
			width : 80,
			align : 'center'
		}, 
		{
			title :'班级',
			field:'cName',
			width:60,
			align:'center'
		},
		{
			title : '上传时间',
			field : 'time',
			width : 123,
			align : 'center',
			formatter : function(value){
				var unixTimestamp = new Date(value);  
	            return unixTimestamp.toLocaleString();  
            }
		},{
			title :'上传者',
			field :'typeId',
			width :80,
			align:'center',
			formatter : function(value, row, index) {
				var result = row.typeId;
				if(result=="01"||result=="03"||result=="06"||result=="07"||result=="09"||result=="10"){
					result = '<font style="color:BLUE;">教师</font>';
				}else{
					result = '<font style="color:GREEN;">学生</font>';
				}
				return result;
			},
		} ,
		{
			title : '文档状态',
			field : 'stateName',
			width : 120,
			align : 'center',
			formatter : function(value, row, index) {
				var result = row.stateName;
				if (result == "未审核") {
					result = '<font style="color:RED;">未审核</font>';
				} else if (result == "一级审核未通过") {
					result = '<font style="color:RED;">一级审核未通过</font>';
				} else if (result == "一级审核通过") {
					result = '<font style="color:GREEN;">一级审核通过</font>';
				} else if (result == "二级审核未通过") {
					result = '<font style="color:RED;">二级审核未通过</font>';
				} else if (result == "二级审核通过") {
					result = '<font style="color:GREEN;">二级审核通过</font>';
				}
				return result;
			},
		}, {
			title : '操作',
			field : 'button',
			width : 150,
			align : 'center',
			formatter : format
		} ] ],
		onLoadSuccess : function(data) {// 此处意思是当dategrid加载成功时候，设置样式
			$("a[name='opera']").linkbutton({
				text : '下载',
				plain : true,
				iconCls : 'icon-man'
			});
			$("a[name='view']").linkbutton({
				text : '在线预览',
				plain : true,
				iconCls : 'icon-man'
			});

		},
		pagination : 'true',
		pageSize : 10,
		SelectOnCheck : 'true',
		pageList : [ 10, 15, 20 ]
	});
});