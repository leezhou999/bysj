package bysj.zjl.entity;
import java.io.Serializable;
/**
 * 角色表
 * @author zhoujiali
 */ 
public class Role implements Serializable{
	
	private String role_id;
	private String role_name;
	private String role_remark;
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_remark() {
		return role_remark;
	}
	public void setRole_remark(String role_remark) {
		this.role_remark = role_remark;
	}
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name + ", role_remark=" + role_remark + "]";
	}
	
	
}

