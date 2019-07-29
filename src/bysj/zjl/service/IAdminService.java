package bysj.zjl.service;

import java.util.List;

import bysj.zjl.entity.Classes;
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

/**
 * 管理员service
 * @author zhoujiali
 *
 */
public interface IAdminService {

	/**
	 * 查询permission
	 * @return
	 */
	public List<Permission> queryPermission(Permission permission);
	
	/**
	 * 编辑permission
	 */
	public boolean permissionEdit(Permission permission);
	/**
	 * 增加permission
	 */
	public boolean permissionAdd(Permission permission);
	/**
	 * 删除permission
	 */
	public boolean permissionDel(String id);
	/**
	 * 查询userList
	 * @return
	 */
	public List<User_info_vo> queryUserList(User_info_vo userInfoVo);
	/**
	 * 增加user
	 */
	public boolean userAdd(User_info_vo stu);
	/**
	 * 增加stuUserInfo
	 */
	public boolean stuUserInfoAdd(User_info_vo stu);
	
	/**
	 * 增加otherUserInfo
	 */
	public boolean otherUserInfoAdd(User_info_vo others);
	/**
	 * 添加user_role
	 */
	public boolean userRoleAdd(User_info_vo user);
	/**
	 * 删除user
	 */
	public boolean userDel(String id);
	
	/**
	 * 编辑user
	 */
	public boolean userEdit(User_info_vo user);
	/**
	 * 查询userList
	 * @return
	 */
	public List<Role> queryRole(Role role);
	/**
	 * 编辑role
	 * @param role
	 * @return
	 */
	public boolean roleEdit(Role role);
	/**
	 * 添加role
	 * @param role
	 * @return
	 */
	public boolean roleAdd(Role role);
	/**
	 * 删除role
	 * @param string
	 * @return
	 */
	public boolean roleDel(String string);
	/**
	 * 删除role_permission关联信息
	 */
	public boolean delRolePermission(String role_id);
	/**
	 * 对用户直接授权前，删除所有user_permission关系
	 */
	public boolean delUserPermission(String user_id);
	/**
	 * 角色授权
	 * @param string
	 * @return
	 */
	public boolean changeAuthority(String role_id,String permission_id);
	/**
	 * 对用户直接授权
	 * @param string
	 * @return
	 */
	public boolean changeAuthorityByUser(String user_id, String permission_id);
	/**
	 * 统计
	 */
	public int countNum(String role_id);
	/**
	 * 统计
	 */
	public int countNumUserPermission(String user_id);
	/**
	 * 教师查询答辩小组信息
	 */
	public List<User_info_vo> querySG(User user);
	/**
	 * 教秘、院长查询答辩小组信息
	 */
	public List<User_info_vo> queryDSG(User user);
	/**
	 * 教学秘书查询院系信息
	 */
	public List<Dept> queryDeptInfo(User user);
	/**
	 * 教学秘书编辑院系信息
	 */
	public boolean deptInfoEdit(Dept dept);
	/**
	 * 教学秘书查询院专业信息
	 */
	public List<Major> queryMajorInfo(User user);
	/**
	 * 教学秘书编辑院专业信息
	 */
	public boolean majorInfoEdit(Major major);
	/**
	 * 教学秘书添加院专业信息
	 */
	public boolean majorInfoAdd(Major major);
	/**
	 * 教学秘书删除院专业信息
	 */
	public boolean majorInfoDel(String majorId);
	/**
	 * 教学秘书查询classesInfo
	 */
	public List<Classes> queryClassesInfo(User user);
	/**
	 * 教学秘书编辑classesInfo
	 */
	public boolean classesInfoEdit(Classes classes);
	/**
	 * 教学秘书添加classesInfo
	 */
	public boolean classesInfoAdd(Classes classes);
	/**
	 * 教学秘书删除classesInfo
	 */
	public boolean classesInfoDel(String classesId);
	/**
	 * 教秘院长查询本院小组信息
	 */
	public List<User_info_vo> queryDSTGroupInfo(User user);
	/**
	 * 教秘院长查询本院教师信息
	 */
	public List<User_info_vo> queryDTInfo(User user);
	/**
	 * 教务处查询全校小组信息
	 */
	public List<User_info_vo> queryAllDSTGroupInfo(User user);
	/**
	 * 教务处查询全校
	 */
	public List<User_info_vo> queryAllDTInfo(User user);
	/**
	 * 教师查询本人课题
	 * @param user
	 * @return
	 */
	public List<Topic> queryTTopic(User user);
	/**
	 * 教师查询SA课题
	 */
	public List<Topic> querySATopic(User_info_vo userInfoVo);
	/**
	 * 教秘查询DSTA课题
	 */
	public List<Topic> queryDSTATopic(User_info_vo userInfoVo);
	/**
	 * 删除课题
	 */
	public boolean topicDel(User_info_vo user);
	/**
	 * 教务处查询全校课题
	 * @param topic
	 * @return
	 */
	public List<Topic> queryAllDTopic(User_info_vo userInfoVo);
	/**
	 * 教秘查询D课题
	 */
	public List<Topic> queryDTopic(User_info_vo userInfoVo);
	/**
	 * 教秘添加课题
	 */
	public boolean topicAdd(Topic topic);
	/**
	 * 教秘更新课题
	 */
	public boolean topicEdit(Topic topic);
	/**
	 * 教秘删除课题
	 */
	public boolean topicDelInt(int topicId);
	/**
	 * 教师课题审批
	 * @param topic
	 * @return
	 */
	public boolean chk(Topic topic);
	/**
	 * 教秘课题审批
	 * @param topic
	 * @return
	 */
	public boolean dChk(Topic topic);
	/**
	 * 教师审批学生答辩
	 */
	public boolean sApplyChk(User_info_vo userInfoVo);
	/**
	 * 教师查看学生成绩
	 * @param user
	 * @return
	 */
	public List<Score> queryTScore(User user);
	/**
	 * 教秘、院长查看学生成绩
	 * @param user
	 * @return
	 */
	public List<Score> queryDScore(User user);
	/**
	 * 教务处查看全校成绩
	 * @param user
	 * @return
	 */
	public List<Score> queryAllDScore(User user);
	/**
	 * 成绩更新
	 * @param score
	 * @return
	 */
	public boolean scoreEdit(Score score);
	/**
	 * 录入成绩
	 * @param score
	 * @return
	 */
	public boolean scoreAdd(Score score);
	/**
	 * 删除成绩
	 * @param score
	 * @return
	 */
	public boolean scoreDel(String string);
	/**
	 * 教秘、院长查询DTG
	 * @param user
	 * @return
	 */
	public List<Tea_group> queryDTG(User user);
	/**
	 *教师查询DTG
	 * @param user
	 * @return
	 */
	public List<Tea_group> queryTG(User user);
	/**
	 *教务处查询AllDTG
	 * @param user
	 * @return
	 */
	public List<Tea_group> queryAllDTG(User user);
	/**
	 * 编辑答辩组信息
	 * @param tea_group
	 * @return
	 */
	public boolean dTGEdit(Tea_group tea_group);
	/**
	 * 添加答辩组信息
	 * @param tea_group
	 * @return
	 */
	public boolean dTGAdd(Tea_group tea_group);
	/**
	 * 删除答辩组信息
	 * @param string
	 * @return
	 */
	public boolean dTGDel(String string);
	/**
	 * 教师查看所有学生文档
	 * @param user
	 * @return
	 */
	/**组长校验**/
	public int chkDTGIsHeadMan(String group_id);
	public List<Document> tQueryAllSDoc(User user);
	/**
	 * 教秘、院长查看所有学生文档
	 * @param user
	 * @return
	 */
	public List<Document> queryDDoc(User user);
	/**
	 * 教务处查看所有学生文档
	 * @param user
	 * @return
	 */
	public List<Document> queryAllDDoc();
	/**
	 * 教师查看学生待审核文档
	 * @param user
	 * @return
	 */
	public List<Document> tQueryChkDoc(User user);
	/**
	 * 教师审批学生文档
	 * @param document
	 * @return
	 */
	public boolean tChk(Document document);
	/**
	 * 教秘查看学生待审核文档
	 * @param user
	 * @return
	 */
	public List<Document> dQueryChkDoc(User user);
	/**
	 * 教秘审批学生文档
	 * @param document
	 * @return
	 */
	public boolean dDocChk(Document document);
	/**
	 * 发布新闻
	 * @param news
	 * @return
	 */
	public boolean addNews(Notice news);
	/**
	 * 学生查看答辩组信息
	 */
	public List<Speech_group> sQuerySpeechGroup(User user);
	/**
	 * 教秘查看答辩组信息
	 */
	public List<Speech_group> dQuerySpeechGroup(User user);
	/**
	 * 账户校验
	 */
	public User chkIsLocked(User user);
	/**
	 * 编辑学生答辩组信息
	 * @param tea_group
	 * @return
	 */
	public boolean dSpeechGroupEdit(Speech_group speechGroup);
	/**
	 * 添加学生答辩组信息
	 * @param tea_group
	 * @return
	 */
	public boolean dSpeechGroupAdd(Speech_group speechGroup);
	/**
	 * 删除学生答辩组信息
	 * @param string
	 * @return
	 */
	public boolean dSpeechGroupDel(String string);
	/**
	 * 教务处查看答辩组信息
	 */
	public List<Speech_group> queryAllDSpeechGroup(User user);
	/**
	 * 重置密码
	 */
	public boolean resetPassword(User user);
}
