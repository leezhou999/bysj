package bysj.zjl.test;

import java.util.List;
import java.util.Random;
import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bysj.zjl.entity.Topic;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info_vo;
import bysj.zjl.mapper.StuMapper;


public class StuUnitTest extends BaseTest {
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private StuMapper StuMapper;
	/**
	 * 增、删、改、查 测试
	 */
	@Test
	public void getTopic() {
		
		String user_id ="1";
		String user_name="周";
		User_info_vo user =new User_info_vo();
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		List<Topic> list =StuMapper.queryDTopic(user);
		for(Topic t:list) {
			System.out.println(t.toString());
		}
		System.out.println(list.size());
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

