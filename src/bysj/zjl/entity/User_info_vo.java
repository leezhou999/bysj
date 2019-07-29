package bysj.zjl.entity;
/**
 * 用户信息Vo
 * 继承User_info
 * @author Administrator
 *
 */
public class User_info_vo extends User_info {

	/**
	 * 扩展User、Major、Classes、Dept属性
	 */
	private String dName;
	private String majorName;
	private String cName;
	private String deptId;
	private String majorId;
	private String classesId;
	private String role_id;
	private String password;
	private String role_name;
	private String isOk;
	private String findstr;
	private String topicName;//课题名称
	private String tea_name;
	private int topicId;
	private String stateId;
	private String apply;
	private String user_id;
	private String user_name;
	
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getClassesId() {
		return classesId;
	}
	public void setClassesId(String classesId) {
		this.classesId = classesId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public String getFindstr() {
		return findstr;
	}
	public void setFindstr(String findstr) {
		this.findstr = findstr;
	}
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	@Override
	public String toString() {
		return "User_info_vo [dName=" + dName + ", majorName=" + majorName + ", cName=" + cName + ", deptId=" + deptId
				+ ", majorId=" + majorId + ", classesId=" + classesId + ", role_id=" + role_id + ", password="
				+ password + ", role_name=" + role_name + ", isOk=" + isOk + ", findstr=" + findstr + ", topicName="
				+ topicName + ", tea_name=" + tea_name + ", topicId=" + topicId + ", stateId=" + stateId + ", apply="
				+ apply + ", user_id=" + user_id + ", user_name=" + user_name + "]";
	}
	
	

	
	
}
