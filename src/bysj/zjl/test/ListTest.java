package bysj.zjl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import bysj.zjl.entity.Permission;
import bysj.zjl.entity.Role;
import bysj.zjl.entity.TreeNode;
import bysj.zjl.mapper.UserMapper;

public class ListTest extends BaseTest {
	
	@Resource
	private  UserMapper userMapper;
	
	@Test
	public void listTest() {
		Role role = new Role();
		role.setRole_id("1");
		
		List<TreeNode> listTree = new ArrayList<TreeNode>();//封装为EasyUI Tree 数据格式

		List<Permission> list1 = userMapper.queryTree();//查询所有树
		List<Permission> list2 = userMapper.queryTreeByRole(role);//根据role查询树
		
		System.out.println(list1.size());
		System.out.println(list2.size());
		
		list1.removeAll(list2);//差集
		
		for(Permission item:list2) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			treeNode.setChecked(true);
			
			listTree.add(treeNode);
		}
		for(Permission item:list1) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			treeNode.setChecked(false);
			
			listTree.add(treeNode);
		}
		System.out.println(list1.size());
		System.out.println(listTree.size());
		for(TreeNode t:listTree) {
			System.out.println(t.toString());
		}
	}

}
