package bysj.zjl.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bysj.zjl.entity.User;
import bysj.zjl.mapper.UserMapper;

public class UserUnitTest extends BaseTest {
	Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 登录测试
	 */
	
	  @Test 
	  public void stuLogin() { 
		User u = new User();
		u.setUser_id("1");
		u.setPassword("111111");
	  log.info("用户登录测试:"+userMapper.userLogin(u));
	  
	  }
	 /**
	 * 权限树测试
	 */
	  @Test
	  public void queryTree() {
		  User u = new User();
		  u.setUser_id("1");
		  
		  log.info("权限树测试"+userMapper.queryTree());
	  }
	
}

