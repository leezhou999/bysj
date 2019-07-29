package bysj.zjl.entity;
import java.io.Serializable;
/**
 * 权限表
 * @author zhoujiali
 */ 
public class Permission implements Serializable{
	
	private int permission_id;
	private String permission_name;
	private String permission_url;
	private int parentId;
	private String state;
	private String ionCls;
	
	public int getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public String getPermission_url() {
		return permission_url;
	}
	public void setPermission_url(String permission_url) {
		this.permission_url = permission_url;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIonCls() {
		return ionCls;
	}
	public void setIonCls(String ionCls) {
		this.ionCls = ionCls;
	}
	@Override
	public String toString() {
		return "Permission [permission_id=" + permission_id + ", permission_name=" + permission_name
				+ ", permission_url=" + permission_url + ", parentId=" + parentId + ", state=" + state + ", ionCls="
				+ ionCls + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Permission) {
            Permission p = (Permission) obj;
            return (Integer.toString(this.permission_id)).equals(Integer.toString(p.permission_id));
        }
		return super.equals(obj);
	}
	
	
}

