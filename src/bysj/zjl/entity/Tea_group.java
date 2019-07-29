package bysj.zjl.entity;
/**
 * 答辩专家组
 * @author Administrator
 *
 */
public class Tea_group extends User_info{
	
	private int tea_group_id;
	private String groupName;
	private String tea_id;
	private String groupId;
	private String deptId;
	private String user_name;
	private String dName;
	private String isHeadMan;
	
	
	
	public String getIsHeadMan() {
		return isHeadMan;
	}
	public void setIsHeadMan(String isHeadMan) {
		this.isHeadMan = isHeadMan;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public int getTea_group_id() {
		return tea_group_id;
	}
	public void setTea_group_id(int tea_group_id) {
		this.tea_group_id = tea_group_id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
	@Override
	public String toString() {
		return "Tea_group [tea_group_id=" + tea_group_id + ", groupName=" + groupName + ", tea_id=" + tea_id
				+ ", groupId=" + groupId + ", deptId=" + deptId + ", user_name=" + user_name + ", dName=" + dName
				+ ", isHeadMan=" + isHeadMan + "]";
	}
	
	
	
	
}
