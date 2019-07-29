package bysj.zjl.entity;
import java.io.Serializable;
/**
 * 用户_角色表
 * @author zhoujiali
 */ 
public class User_role implements Serializable{
	
	private int user_role_id;
	private String user_id;
	private String role_id;
	
	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	
}

