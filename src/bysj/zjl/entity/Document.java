package bysj.zjl.entity;
/**
 * 文档表
 * @author Administrator
 *
 */
public class Document {

	private int id;
	private String docId;
	private String stu_id;
	private String tea_id;
	private String deptId;
	private String majorId;
	private String classesId;
	private String typeId;
	private String location;
	private String stateId;
	private String time;
	private String dName;
	private String majorName;
	private String cName;
	private String user_name;
	private String stateName;
	private String typeName;
	private String stu_name;
	
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
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
	public String getClassesId() {
		return classesId;
	}
	public void setClassesId(String classesId) {
		this.classesId = classesId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", docId=" + docId + ", stu_id=" + stu_id + ", tea_id=" + tea_id + ", deptId="
				+ deptId + ", majorId=" + majorId + ", classesId=" + classesId + ", typeId=" + typeId + ", location="
				+ location + ", stateId=" + stateId + ", time=" + time + ", dName=" + dName + ", majorName=" + majorName
				+ ", cName=" + cName + ", user_name=" + user_name + ", stateName=" + stateName + ", typeName="
				+ typeName + ", stu_name=" + stu_name + "]";
	}
	
	
	
}
