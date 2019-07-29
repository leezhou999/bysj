package bysj.zjl.entity;
/**
 * 课题表
 * @author Administrator
 *
 */
public class Topic extends User_info_vo{
	
	private int topicId;
	private String topicName;
	private String abs;
	private String typeId;
	private String user_name;
	private String user_id;
	private String stu_id;
	private String stu_name;
	private String tea_id;
	private String tea_name;
	private String deptId;
	private String dName;
	private String majorId;
	private String majorName;
	private String stateId;
	private String chk;
	private String apply;
	
	
	
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getAbs() {
		return abs;
	}
	public void setAbs(String abs) {
		this.abs = abs;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
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
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
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
	
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", abs=" + abs + ", typeId=" + typeId
				+ ", user_name=" + user_name + ", user_id=" + user_id + ", stu_id=" + stu_id + ", stu_name=" + stu_name
				+ ", tea_id=" + tea_id + ", tea_name=" + tea_name + ", deptId=" + deptId + ", dName=" + dName
				+ ", majorId=" + majorId + ", majorName=" + majorName + ", stateId=" + stateId + ", chk=" + chk
				+ ", apply=" + apply + "]";
	}
	
}
