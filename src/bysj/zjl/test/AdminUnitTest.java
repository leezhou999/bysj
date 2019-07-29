package bysj.zjl.test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bysj.zjl.entity.Permission;
import bysj.zjl.entity.Topic;
import bysj.zjl.entity.User;
import bysj.zjl.mapper.AdminMapper;
import bysj.zjl.mapper.UserMapper;

public class AdminUnitTest extends BaseTest {
	Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private AdminMapper adminMapper;
	
	/*
	 * @Test public void queryPermission() { List<Permission>
	 * list=adminMapper.queryPermission(); System.out.println("size:"+list.size());
	 * for(Permission p:list) { System.out.println(p.toString()); } }
	 */
	/**
	 * 增、删、改、查 测试
	 */
	@Test
	public void permissionEdit() {
		Permission p = new Permission();
		p.setPermission_id(54);
		p.setPermission_name("test54");
		adminMapper.permissionAdd(p);
	}
	@Test
	public void test() {
		String ids="22,22,21,";
		String []id = ids.split(",");
		for(int i=0;i<id.length;i++) {
			System.out.println(id[i]);
		}
	}
	@Test
	public void genRandomNum(){  
	      int  maxNum = 36;  
	      int i;  
	      int count = 0;  
	      char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
	        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
	        'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
	      StringBuffer pwd = new StringBuffer("");  
	      Random r = new Random();  
	      while(count < 8){  
	       i = Math.abs(r.nextInt(maxNum));     
	       if (i >= 0 && i < str.length) {  
	        pwd.append(str[i]);  
	        count ++;  
	       }  
	      }  
	      System.out.println(pwd.toString());  
	    } 
	
	
}

