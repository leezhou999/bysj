//流程管理，除教秘角色外，只可查询
var arr;
$(function() {
	
	$("#dg").datagrid({
		width : 1300,
		height : 520,
		nowrap:false,
		fitColumns:true,
		url : "queryProcess",
		method : 'post',
		columns : [ [
             {title:'毕业设计总体流程表',colspan:5},
            ],[ {
			title : '流程编号',
			field : 'process_id',
			width : 120,
			align : 'center',
			
		}, {
			title : '相关用户',
			field : 'user_name',
			width : 120,
			align : 'center',
			
		}, {
			title : '用户操作',
			field : 'operate',
			width : 150,
			align : 'left',
			
			
		}, {
			title : '流程状态',
			field : 'state',
			width : 150,
			align : 'center',
			formatter : function(value, row, index) {
				var result = row.state;
				if (result == "已完成") {
					result = '<font style="color:GREEN;">已完成</font>';
				}else if (result == "未完成") {
					result = '<font style="color:RED;">未完成</font>';
				}
				return result;
			}
				
		}, {
			title : '流程详情',
			field : 'remark',
			width : 500,
			align : 'left',
			
		}]],
		toolbar:[{
    		text:'查询',
    		iconCls:'icon-search',
    		handler:function(){
    		$("#cc").layout('expand','north');
    		}
    	}
    	],
    	striped:'true',//斑马条纹
        pagination:true,//显示分页工具栏
        pageSize:10,
        pageList:[10,15,20],
	});
	$("#btn-find").click(function(){
		//1.获取查询文本框中的信息
		 param =$("#findstr").val();
		
		$("#dg").datagrid('load',{
			remark:param
		});
	});
	//重置搜索框
	$("#btn-reset").click(function(){
		document.getElementById("findstr").value="";
	});
});