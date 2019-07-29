package bysj.zjl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bysj.zjl.entity.Classes;
import bysj.zjl.entity.ClassesVo;
import bysj.zjl.entity.Dept;
import bysj.zjl.entity.Document;
import bysj.zjl.entity.Major;
import bysj.zjl.entity.Notice;
import bysj.zjl.entity.Permission;
import bysj.zjl.entity.Role;
import bysj.zjl.entity.Score;
import bysj.zjl.entity.Speech_group;
import bysj.zjl.entity.Tea_group;
import bysj.zjl.entity.Topic;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info;
import bysj.zjl.entity.User_info_vo;
import bysj.zjl.mapper.AdminMapper;
import bysj.zjl.service.IAdminService;
@Service
public class AdminServiceImpl implements IAdminService {
	Logger log =LoggerFactory.getLogger(getClass());
	private AdminMapper adminMapper;
	@Autowired
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	/**
	 * 查询permission
	 * @return
	 */
	@Override
	public List<Permission> queryPermission(Permission permission) {
		return adminMapper.queryPermission(permission);
	}
	
	/**
	 * 编辑permission
	 */
	@Override
	public boolean permissionEdit(Permission permission) {
		return adminMapper.permissionEdit(permission);
	}
	
	/**
	 * 增加permission
	 */
	@Override
	public boolean permissionAdd(Permission permission) {
		// TODO Auto-generated method stub
		return adminMapper.permissionAdd(permission);
	}
	/**
	 * 删除permission
	 */
	@Override
	public boolean permissionDel(String id) {
		return adminMapper.permissionDel(id);
	}
	/**
	 * 查询userList
	 * @return
	 */
	@Override
	public List<User_info_vo> queryUserList(User_info_vo userInfoVo) {
		return adminMapper.queryUserList(userInfoVo);
	}
	/**
	 * 增加stuUser
	 */
	@Override
	public boolean userAdd(User_info_vo stu) {
		return adminMapper.userAdd(stu);
	}
	/**
	 * 增加stuUserInfo
	 */
	@Override
	public boolean stuUserInfoAdd(User_info_vo stu) {
		return adminMapper.stuUserInfoAdd(stu);
	}
	/**
	 * 增加otherUserInfo
	 */
	@Override
	public boolean otherUserInfoAdd(User_info_vo others) {
		return adminMapper.otherUserInfoAdd(others);
	}
	/**
	 * 添加user_role
	 */
	@Override
	public boolean userRoleAdd(User_info_vo user) {
		return adminMapper.userRoleAdd(user);
	}
	/**
	 * 删除user
	 */
	@Override
	public boolean userDel(String id) {
		return adminMapper.userDel(id)&&adminMapper.userInfoDel(id)&&adminMapper.userRoleDel(id);
	}
	
	/**
	 * 编辑user
	 */
	@Override
	public boolean userEdit(User_info_vo user) {
		return adminMapper.userEdit(user)&&adminMapper.userInfoEdit(user);
	}
	/**
	 * 查询role
	 * @return
	 */
	@Override
	public List<Role> queryRole(Role role) {
		return adminMapper.queryRole(role);
	}
	/**
	 * 编辑role
	 */
	@Override
	public boolean roleEdit(Role role) {
		return adminMapper.roleEdit(role);
	}
	/**
	 * 添加role
	 */
	@Override
	public boolean roleAdd(Role role) {
		return adminMapper.roleAdd(role);
	}
	/**
	 * 删除role
	 */
	@Override
	public boolean roleDel(String string) {
		return adminMapper.roleDel(string);
	}
	/**
	 * 删除role_permission关联信息
	 */
	@Override
	public boolean delRolePermission(String role_id) {
		return adminMapper.delRolePermission(role_id);
	}
	
	/**
	 * 角色授权
	 * 授权前先删除role_permission关联关系
	 */
	@Override
	public boolean changeAuthority(String role_id,String permission_id) {
		boolean flag=false;
		flag=adminMapper.changeAuthority(role_id,permission_id);
		return flag;
	}
	/**
	 * 对用户直接授权前，删除所有user_permission关系
	 */
	@Override
	public boolean delUserPermission(String user_id) {
		return adminMapper.delUserPermission(user_id);
	}
	/**
	 * 对用户直接授权
	 * @param string
	 * @return
	 */
	@Override
	public boolean changeAuthorityByUser(String user_id, String permission_id) {
		return adminMapper.changeAuthorityByUser(user_id, permission_id);
	}
	/**
	 * 统计
	 */
	@Override
	public int countNum(String role_id) {
		return adminMapper.countNum(role_id);
	}
	/**
	 * 统计user_permission
	 */
	@Override
	public int countNumUserPermission(String user_id) {
		return adminMapper.countNumUserPermission(user_id);
	}
	/**
	 * 教学秘书查询院系信息
	 */
	@Override
	public List<Dept> queryDeptInfo(User user) {
		return adminMapper.queryDeptInfo(user);
	}
	/**
	 * 教学秘书编辑院系信息
	 */
	@Override
	public boolean deptInfoEdit(Dept dept) {
		return adminMapper.deptInfoEdit(dept);
	}
	/**
	 * 教学秘书查询院专业信息
	 */
	@Override
	public List<Major> queryMajorInfo(User user) {
		return adminMapper.queryMajorInfo(user);
	}
	/**
	 * 教学秘书编辑院专业信息
	 */
	@Override
	public boolean majorInfoEdit(Major major) {
		return adminMapper.majorInfoEdit(major);
	}
	/**
	 * 教学秘书添加院专业信息
	 */
	@Override
	public boolean majorInfoAdd(Major major) {
		return adminMapper.majorInfoAdd(major);
	}
	/**
	 * 教学秘书删除院专业信息
	 */
	@Override
	public boolean majorInfoDel(String majorId) {
		return adminMapper.majorInfoDel(majorId);
	}
	/**
	 * 教学秘书查询院班级信息
	 */
	@Override
	public List<Classes> queryClassesInfo(User user) {
		return adminMapper.queryClassesInfo(user);
	}
	/**
	 * 教学秘书编辑院班级信息
	 */
	@Override
	public boolean classesInfoEdit(Classes classes) {
		return adminMapper.classesInfoEdit(classes);
	}
	/**
	 * 教学秘书添加院班级信息
	 */
	@Override
	public boolean classesInfoAdd(Classes classes) {
		return adminMapper.classesInfoAdd(classes);
	}
	/**
	 * 教学秘书删除院班级信息
	 */
	@Override
	public boolean classesInfoDel(String string) {
		return adminMapper.classesInfoDel(string);
	}
	
	/**
	 * 教师查询答辩小组信息
	 */
	@Override
	public List<User_info_vo> querySG(User user) {
		return adminMapper.querySG(user);
	}
	
	/**
	 * 教秘、院长查询答辩小组信息
	 */
	
	@Override
	public List<User_info_vo> queryDSG(User user) {
		return adminMapper.queryDSG(user);
	}
	/**
	 * 教秘院长查询本院小组信息
	 */
	@Override
	public List<User_info_vo> queryDSTGroupInfo(User user) {
		return adminMapper.queryDSTGroupInfo(user);
	}
	/**
	 * 教秘院长查询本院教师信息
	 */
	@Override
	public List<User_info_vo> queryDTInfo(User user) {
		return adminMapper.queryDTInfo(user);
	}
	/**
	 * 教务处查询全校小组信息
	 */
	@Override
	public List<User_info_vo> queryAllDSTGroupInfo(User user) {
		return adminMapper.queryAllDSTGroupInfo(user);
	}

	/**
	 * 教务处查询全校
	 */
	
	@Override
	public List<User_info_vo> queryAllDTInfo(User user) {
		return adminMapper.queryAllDTInfo(user);
	}
	/**
	 * 教师查询本人课题
	 * @param user
	 * @return
	 */
	@Override
	public List<Topic> queryTTopic(User user) {
		return adminMapper.queryTTopic(user);
	}
	/**
	 * 教师查询SA课题
	 */
	@Override
	public List<Topic> querySATopic(User_info_vo userInfoVo) {
		return adminMapper.querySATopic(userInfoVo);
	}
	/**
	 * 教秘查询DSTA课题
	 */
	@Override
	public List<Topic> queryDSTATopic(User_info_vo userInfoVo) {
		return adminMapper.queryDSTATopic(userInfoVo);
	}
	/**
	 * 删除课题
	 */
	@Override
	public boolean topicDel(User_info_vo user) {
		return adminMapper.topicDel(user);
	}
	
	
	/**
	 * 教务处查询全校课题
	 * @param findstr
	 * @return
	 */
	@Override
	public List<Topic> queryAllDTopic(User_info_vo userInfoVo) {
		return adminMapper.queryAllDTopic(userInfoVo);
	}
	/**
	 *教秘查询D课题
	 */
	@Override
	public List<Topic> queryDTopic(User_info_vo userInfoVo) {
		return adminMapper.queryDTopic(userInfoVo);
	}
	/**
	 * 教秘添加课题
	 */
	@Override
	public boolean topicAdd(Topic topic) {
		return adminMapper.topicAdd(topic);
	}
	/**
	 * 教秘更新课题
	 */
	@Override
	public boolean topicEdit(Topic topic) {
		return adminMapper.topicEdit(topic);
	}
	/**
	 * 教秘删除课题
	 */
	@Override
	public boolean topicDelInt(int topicId) {
		return adminMapper.topicDelInt(topicId);
	}
	/**
	 * 教师课题审批
	 * @param topic
	 * @return
	 */
	@Override
	public boolean chk(Topic topic) {
		return adminMapper.chk(topic);
	}
	/**
	 * 教秘课题审批
	 * @param topic
	 * @return
	 */
	@Override
	public boolean dChk(Topic topic) {
		return adminMapper.dChk(topic);
	}
	/**
	 * 教师审批学生答辩
	 */
	@Override
	public boolean sApplyChk(User_info_vo userInfoVo) {
		return adminMapper.sApplyChk(userInfoVo);
	}
	/**
	 * 教师查看学生成绩
	 * @param user
	 * @return
	 */
	@Override
	public List<Score> queryTScore(User user) {
		return adminMapper.queryTScore(user);
	}
	/**
	 * 教秘、院长查看学生成绩
	 * @param user
	 * @return
	 */
	@Override
	public List<Score> queryDScore(User user) {
		return adminMapper.queryDScore(user);
	}
	/**
	 * 教务处查看全校成绩
	 * @param user
	 * @return
	 */
	@Override
	public List<Score> queryAllDScore(User user) {
		return adminMapper.queryAllDScore(user);
	}
	/**
	 *更新成绩
	 */
	@Override
	public boolean scoreEdit(Score score) {
		return adminMapper.scoreEdit(score);
	}
	/**
	 * 录入成绩
	 */
	@Override
	public boolean scoreAdd(Score score) {
		return adminMapper.scoreAdd(score);
	}
	/**
	 * 删除成绩
	 */
	@Override
	public boolean scoreDel(String string) {
		return adminMapper.scoreDel(string);
	}
	/**
	 * 教秘、院长查询DTG
	 * @param tea_group
	 * @return
	 */
	@Override
	public List<Tea_group> queryDTG(User user) {
		return adminMapper.queryDTG(user);
	}
	/**
	 *教师查询DTG
	 * @param user
	 * @return
	 */
	@Override
	public List<Tea_group> queryTG(User user) {
		return adminMapper.queryTG(user);
	}
	/**
	 *教务处查询AllDTG
	 * @param user
	 * @return
	 */
	@Override
	public List<Tea_group> queryAllDTG(User user) {
		return adminMapper.queryAllDTG(user);
	}
	/**
	 * 编辑答辩组信息
	 * @param tea_group
	 * @return
	 */
	@Override
	public boolean dTGEdit(Tea_group tea_group) {
		return adminMapper.dTGEdit(tea_group);
	}
	
	/**
	 * 添加答辩组信息
	 * @param tea_group
	 * @return
	 */
	@Override
	public boolean dTGAdd(Tea_group tea_group) {
		return adminMapper.dTGAdd(tea_group);
	}
	
	/**
	 * 删除答辩组信息
	 * @param string
	 * @return
	 */
	@Override
	public boolean dTGDel(String string) {
		return adminMapper.dTGDel(string);
	}
	/**
	 * 教师查看所有学生文档
	 * @param user
	 * @return
	 */
	@Override
	public List<Document> tQueryAllSDoc(User user) {
		return adminMapper.tQueryAllSDoc(user);
	}
	/**
	 * 教秘、院长查看所有学生文档
	 * @param user
	 * @return
	 */
	@Override
	public List<Document> queryDDoc(User user) {
		return adminMapper.queryDDoc(user);
	}
	/**
	 * 教务粗查看所有学生文档
	 * @param user
	 * @return
	 */
	@Override
	public List<Document> queryAllDDoc() {
		return adminMapper.queryAllDDoc();
	}
	/**
	 * 教师查看学生待审核文档
	 * @param user
	 * @return
	 */
	@Override
	public List<Document> tQueryChkDoc(User user) {
		return adminMapper.tQueryChkDoc(user);
	}
	/**
	 * 教师审批学生文档
	 * @param document
	 * @return
	 */
	@Override
	public boolean tChk(Document document) {
		return adminMapper.tChk(document);
	}
	/**
	 * 教秘查看学生待审核文档
	 * @param user
	 * @return
	 */
	@Override
	public List<Document> dQueryChkDoc(User user) {
		return adminMapper.dQueryChkDoc(user);
	}
	
	/**
	 * 教秘审批学生文档
	 * @param document
	 * @return
	 */
	
	@Override
	public boolean dDocChk(Document document) {
		return adminMapper.dDocChk(document);
	}
	/**
	 * 发布新闻
	 * @param news
	 * @return
	 */
	@Override
	public boolean addNews(Notice news) {
		return adminMapper.addNews(news);
	}
	/**
	 * 学生查看答辩组信息
	 */
	@Override
	public List<Speech_group> sQuerySpeechGroup(User user) {
		return adminMapper.sQuerySpeechGroup(user);
	}
	/**组长校验**/
	@Override
	public int chkDTGIsHeadMan(String group_id) {
		return adminMapper.chkDTGIsHeadMan(group_id);
	}
	/**
	 * 账户校验
	 */
	@Override
	public User chkIsLocked(User user) {
		return adminMapper.chkIsLocked(user);
	}
	/**
	 * 教秘查看答辩组信息
	 */
	@Override
	public List<Speech_group> dQuerySpeechGroup(User user) {
		return adminMapper.dQuerySpeechGroup(user);
	}
	/**
	 * 编辑学生答辩组信息
	 * @param tea_group
	 * @return
	 */
	@Override
	public boolean dSpeechGroupEdit(Speech_group speechGroup) {
		return adminMapper.dSpeechGroupEdit(speechGroup);
	}
	/**
	 * 添加学生答辩组信息
	 * @param tea_group
	 * @return
	 */
	@Override
	public boolean dSpeechGroupAdd(Speech_group speechGroup) {
		return adminMapper.dSpeechGroupAdd(speechGroup);
	}
	/**
	 * 删除学生答辩组信息
	 * @param string
	 * @return
	 */
	@Override
	public boolean dSpeechGroupDel(String string) {
		return adminMapper.dSpeechGroupDel(string);
	}
	/**
	 * 教务处查看答辩组信息
	 */
	@Override
	public List<Speech_group> queryAllDSpeechGroup(User user) {
		return adminMapper.queryAllDSpeechGroup(user);
	}
	/**
	 * 重置密码
	 */
	@Override
	public boolean resetPassword(User user) {
		return adminMapper.resetPassword(user);
	}
	
}
