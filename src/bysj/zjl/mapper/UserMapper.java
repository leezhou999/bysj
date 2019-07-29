package bysj.zjl.mapper;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

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

public interface UserMapper {
	/**
	 * 用户登录
	 */
	public User userLogin(User user);
	/**
	 * 查询树byUser
	 */
	public List<Permission> queryTreeByUser(User user);
	
	/**
	 * 查询树
	 */
	public List<Permission> queryTree();
	/**
	 * 
	 * @param 查询树byRole
	 * @return
	 */
	public List<Permission> queryTreeByRole(Role role);
	/**
	 * 获取子节点数量
	 */
	public int countChildNode(String id);
	/**
	 * 获取Stu基本信息
	 */
	public User_info_vo stuInfo(User user);
	/**
	 * 修改Stu信息
	 * @param userInfo
	 * @return
	 */
	public boolean stuInfoEdit(User_info_vo userInfoVo);
	/**
	 * 获取用户基本信息
	 */
	public User_info_vo userInfo(User user);
	/**
	 * 获取tea基本信息
	 */
	public User_info_vo teaInfo(User user);
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	public boolean userInfoEdit(User_info_vo userInfoVo);
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public boolean userPasswordEdit(User user);
	/**
	 * 查询毕业设计进度
	 * @param process 
	 */
	public List<Process> queryProcess(Process process);
	/**
	 * 添加process
	 * @param process
	 * @return
	 */
	public boolean processAdd(Process process);
	/**
	 * 编辑process
	 */
	public boolean processEdit(Process process);
	/**
	 * 删除process
	 */
	public boolean processDel(String process_id);
	/**
	 * 根据参数查询院系信息
	 */
	public Dept queryDeptInfoByParam(Dept dept);
	/**
	 * 根据参数查询专业信息
	 */
	public Major queryMajorInfoByParam(Major major);
	/**
	 * 根据参数查询班级信息
	 */
	public Classes queryClassesInfoByParam(Classes classes);
	/**
	 * 查询用户类型|角色
	 */
	public Role queryRoleNameById(User user);
	/**
	 * 查询学生小组基本信息
	 */
	public List<User_info> groupInfo(User user);
	/**
	 * 教学秘书、教务处查询所有学生基本信息
	 */
	public List<User_info> AllSgroupInfo(User user);
	/**
	 * 教学秘书、教务处查询所有教师基本信息
	 */
	public List<User_info> AllTgroupInfo(User user);
	/**
	 * 教学秘书查询院系信息
	 */
	public List<Dept> queryDeptInfo(User user);
	
	/**
	 * 教学秘书查询院班级信息
	 */
	public List<ClassesVo> queryClassesInfo(User user);
	/**
	 * 查询docType
	 */
	public List<DocType> queryDocType(DocType docType);
	/**
	 * 添加docType
	 * @param docType
	 * @return
	 */
	public boolean docTypeAdd(DocType docType);
	/**
	 * 编辑docType
	 */
	public boolean docTypeEdit(DocType docType);
	/**
	 * 删除docType
	 */
	public boolean docTypeDel(String typeId);
	/**
	 * 获取topic 最大编号
	 */
	public int maxTopicId();
}
