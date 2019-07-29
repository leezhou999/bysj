/**
 * tree转换函数
 * @param rows
 * @returns
 */

function convert(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId) return true;
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.parentId)){
			nodes.push({
				id:row.id,
				text:row.text,
			});
		}
	}
	
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.parentId == node.id){
				var child = {id:row.id,text:row.text,attributes:row.attributes};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
$(function(){
	   //定义动态菜单的动作
	   $("#aa").accordion({
			//height:400,
			weigth:"auto",
			border:false,
			align:'center'
		});
	  
	   //tree异步加载
	   /*debugger;*/
	   $("#tree").tree({
		    method:"post",
		    url:'queryTreeByUser', // The url will be mapped to NodeController class and getNodes method
		    lines:true,
			/*动画效果*/
			animate:true,
		    loadFilter : function(rows) {
				return convert(rows);
			},	
			onClick:function(rows){
				 var info = rows.id+rows.text+rows.state+rows.checked+rows.attributes+rows.children;
				  var src=rows.attributes.url;
				   tname = rows.text;//标题
				   //3.判断选项卡是否已经创建
				   if($("#tt").tabs('exists',tname))
					 {
					   $("#tt").tabs('select',tname);
					   
					 }else{
						$("#tt").tabs('add',{
							title:tname,
							content:'<iframe frameborder=0 style=width:100%;height:100% src='+src+'></iframe>',
							closable:true,
						});
					 }
			}
		});
   });
