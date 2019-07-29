package bysj.zjl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bysj.zjl.entity.Classes;
import bysj.zjl.entity.ClassesVo;
import bysj.zjl.entity.Dept;
import bysj.zjl.entity.DocType;
import bysj.zjl.entity.Major;
import bysj.zjl.entity.Permission;
import bysj.zjl.entity.Role;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info;
import bysj.zjl.entity.User_info_vo;
import bysj.zjl.entity.Process;

import bysj.zjl.mapper.UserMapper;
import bysj.zjl.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	Logger log =LoggerFactory.getLogger(getClass());
	private UserMapper userMapper;
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	/**
	 * 用户登录
	 */
	@Override
	public User userLogin(User user) {
		User u = userMapper.userLogin(user);
		return u;
	}
	/**
	 * 权限树byUser
	 */
	@Override
	public List<Permission> queryTreeByUser(User user) {
		List<Permission> list = userMapper.queryTreeByUser(user);
		return list;
	}
	
	/**
	 *查询树
	 */
	@Override
	public List<Permission> queryTree() {
		return userMapper.queryTree();
	}
	
	/**
	 *查询树byRole
	 */
	@Override
	public List<Permission> queryTreeByRole(Role role) {
		return userMapper.queryTreeByRole(role);
	}
	/**
	 * 获取子节点数量
	 */
	@Override
	public int countChildNode(String id) {
		return userMapper.countChildNode(id);
	}
	
	/**
	 * Stu基本信息
	 */
	@Override
	public User_info_vo stuInfo(User user) {
		return userMapper.stuInfo(user);
	}
	/**
	 * 修改Stu信息
	 */
	@Override
	public boolean stuInfoEdit(User_info_vo userInfoVo) {
		return userMapper.stuInfoEdit(userInfoVo);
	}
	/**
	 * 用户信息
	 */
	@Override
	public User_info_vo userInfo(User user) {
		return userMapper.userInfo(user);
	}
	/**
	 * 获取tea基本信息
	 */
	@Override
	public User_info_vo teaInfo(User user) {
		return userMapper.teaInfo(user);
	}
	/**
	 * 用户信息编辑
	 */
	@Override
	public boolean userInfoEdit(User_info_vo userInfoVo) {
		return userMapper.userInfoEdit(userInfoVo);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean userPasswordEdit(User user) {
		return userMapper.userPasswordEdit(user);
	}
	/**
	 * 查询进度信息
	 */
	@Override
	public List<Process> queryProcess(Process process) {
		return userMapper.queryProcess(process);
	}
	/**
	 * 添加process
	 */
	@Override
	public boolean processAdd(Process process) {
		return userMapper.processAdd(process);
	}
	/**
	 * 编辑process
	 */
	@Override
	public boolean processEdit(Process process) {
		return userMapper.processEdit(process);
	}
	/**
	 * 删除process
	 */
	@Override
	public boolean processDel(String process_id) {
		return userMapper.processDel(process_id);
	}
	/**
	 * 根据参数查询院系信息
	 */
	@Override
	public Dept queryDeptInfoByParam(Dept dept) {
		return userMapper.queryDeptInfoByParam(dept);
	}
	/**
	 * 根据参数查询专业信息
	 */
	@Override
	public Major queryMajorInfoByParam(Major major) {
		return userMapper.queryMajorInfoByParam(major);
	}
	/**
	 * 根据参数查询班级信息
	 */
	@Override
	public Classes queryClassesInfoByParam(Classes classes) {
		return userMapper.queryClassesInfoByParam(classes);
	}
	/**
	 * 查询用户类型|角色
	 */
	@Override
	public Role queryRoleNameById(User user) {
		return userMapper.queryRoleNameById(user);
	}
	/**
	 * 查询学生小组基本信息
	 */
	@Override
	public List<User_info> groupInfo(User user) {
		return userMapper.groupInfo(user);
	}
	/**
	 * 教学秘书、教务处查询所有学生基本信息
	 */
	@Override
	public List<User_info> AllSgroupInfo(User user) {
		return userMapper.AllSgroupInfo(user);
	}
	/**
	 * 教学秘书、教务处查询所有教师基本信息
	 */
	@Override
	public List<User_info> AllTgroupInfo(User user) {
		return userMapper.AllTgroupInfo(user);
	}
	/**
	 * 查询docType
	 */
	@Override
	public List<DocType> queryDocType(DocType docType) {
		return userMapper.queryDocType(docType);
	}
	/**
	 *添加docType
	 */
	@Override
	public boolean docTypeAdd(DocType docType) {
		return userMapper.docTypeAdd(docType);
	}
	/**
	 * 编辑docType
	 */
	@Override
	public boolean docTypeEdit(DocType docType) {
		return userMapper.docTypeEdit(docType);
	}
	/**
	 * 删除docType
	 */
	@Override
	public boolean docTypeDel(String typeId) {
		return userMapper.docTypeDel(typeId);
	}
	/**
	 * 最大topicId
	 */
	@Override
	public int maxTopicId() {
		return userMapper.maxTopicId();
	}
	
}
