package bysj.zjl.entity;

import java.io.Serializable;

/**
 * 用户表
 * @author zhoujiali
 *
 */
public class User implements Serializable{
	
	private String user_id;
	private String user_name;
	private String password;
	private String question;
	private String answer;
	private String isOk;
	private String role_id;

	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", question="
				+ question + ", answer=" + answer + ", isOk=" + isOk + ", role_id=" + role_id + "]";
	}
	
	
	
}
