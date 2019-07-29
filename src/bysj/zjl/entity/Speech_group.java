package bysj.zjl.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 学生答辩分组表
 * @author Administrator
 *
 */
public class Speech_group {
	private String stu_id;
	private String groupId;
	//private Date time;//sql date
	private String creatTimeStr;
	private String location;
	private String time;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String groupName;
	private String user_name;
	private String stu_name;
	
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/*public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
        String t = sdf.format(time);
        setCreatTimeStr(t);
	}*/
	public String getCreatTimeStr() {
		return creatTimeStr;
	}
 
	public void setCreatTimeStr(String creatTimeStr) {
		this.creatTimeStr = creatTimeStr;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public String toString() {
		return "Speech_group [stu_id=" + stu_id + ", groupId=" + groupId + ", time=" + time + ", location=" + location
				+ ", groupName=" + groupName + ", user_name=" + user_name + "]";
	}
	
	
}
