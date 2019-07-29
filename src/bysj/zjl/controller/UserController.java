package bysj.zjl.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bysj.zjl.entity.Classes;
import bysj.zjl.entity.ClassesVo;
import bysj.zjl.entity.Dept;
import bysj.zjl.entity.DocType;
import bysj.zjl.entity.Major;
import bysj.zjl.entity.Permission;
import bysj.zjl.entity.Role;
import bysj.zjl.entity.TreeNode;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info;
import bysj.zjl.entity.User_info_vo;
import bysj.zjl.entity.Process;
import bysj.zjl.service.IStuService;
import bysj.zjl.service.IUserService;
import bysj.zjl.util.MD5Utils;


/**
 * 用户控制Controller
 * 
 * @author zhoujiali
 *
 */
@Controller
/* @RequestMapping(value="/User") */
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private IUserService userService;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	private IStuService stuService;

	@Autowired
	public void setStuService(IStuService stuService) {
		this.stuService = stuService;
	}
	/**
	 * 跳转至top.jsp
	 */
	@RequestMapping(value = "/toTop")
	public String Top() {
		return "top";
	}
	/**
	 * 跳转至login.jsp
	 */
	@RequestMapping(value = "/toLogin")
	public String Login() {
		return "login";
	}
	/**
	 * 跳转至UserInfo.jsp
	 */
	@RequestMapping(value = "/userInfo")
	public String UserInfo(HttpSession session) {
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {
			return "StuInfo";
		}
		return "UserInfo";
	}
	/**
	 * 跳转至UserInfoEdit.jsp
	 */
	@RequestMapping(value = "/userInfoEditPage")
	public String UserInfoEdit(HttpSession session) {
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {
			return "StuInfoEdit";
		}
		return "UserInfoEdit";
	}
	/**
	 * 跳转至UserPasswordEdit.jsp
	 */
	@RequestMapping(value = "/userPasswordEditPage")
	public String UserPasswordEdit() {
		return "UserPasswordEdit";
	}
	/**
	 * 跳转至permission.jsp
	 */
	@RequestMapping(value="/toPermission")
	public String Permission() {
		return "permission";
	}
	/**
	 * 跳转至UserList.jsp
	 */
	@RequestMapping(value="/toUserList")
	public String UserList() {
		return "UserList";
	}
	/**
	 * 跳转至Role.jsp
	 */
	@RequestMapping(value="/toRole")
	public String Role() {
		return "Role";
	}
	/**
	 * 跳转至DocType.jsp
	 */
	@RequestMapping(value="/toDocType")
	public String DocType() {
		return "DocType";
	}
	/**
	 * 跳转至Process.jsp
	 */
	@RequestMapping(value="/toProcess")
	public String Process(HttpSession session) {
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("3")) {
			return "Process";
		}
		return "NormalProcess";
	}
	/**
	 * 跳转至DeptInfo.jsp
	 */
	@RequestMapping(value="/toDeptInfo")
	public String DeptInfo(HttpSession session) {
		return "DeptInfo";
	}
	/**
	 * 跳转至MajorInfo.jsp
	 */
	@RequestMapping(value="/toMajorInfo")
	public String MajorInfo(HttpSession session) {
		return "MajorInfo";
	}
	/**
	 * 跳转至ClassesInfo.jsp
	 */
	@RequestMapping(value="/toClassesInfo")
	public String ClassesInfo(HttpSession session) {
		return "ClassesInfo";
	}
	/**
	 * 跳转至STopic.jsp
	 */
	@RequestMapping(value="/toTopic")
	public String Topic(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//学生,查看本院课题
			mav="STopic";
		}else if(role.getRole_id().equals("2")) {//指导教师,查看自己课题，可审批学生申请和学生自拟课题
			mav="TTopic";
		}else if(role.getRole_id().equals("3")) {//教学秘书，查看本院系所有课题,可审批教师课题
			mav="DTopic";
		}else if(role.getRole_id().equals("6")) {//教学院长，可查询本院所有课题
			mav="DTopic";
		}
		else if(role.getRole_id().equals("4")) {//教务处，所有院系
			mav="AllDTopic";
		}
		return mav;
	}
	/**
	 * 学生查看文档
	 * @return
	 */
	@RequestMapping(value="/toSRead")
	public String toRead() {
		return "SRead";
	}
	/**
	 * 跳转至resetPassword.jsp
	 */
	@RequestMapping(value="/toResetPassword")
	public String resetPassword(HttpSession session) {
		return "resetPassword";
	}
	/**
	 * 跳转至STopicResult.jsp
	 */
	@RequestMapping(value="/toTopicResult")
	public String TopicResult(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//学生,申请课题结果
			mav="STopicResult";
		}else if(role.getRole_id().equals("2")) {//指导教师查看学生申请
			mav="TTopicResult";
		}else if(role.getRole_id().equals("3")) {//教秘查看教师申请
			mav="DTopicResult";
		}
		return mav;
	}
	/**
	 * 课题审核
	 */
	@RequestMapping(value="/toTopicChk")
	public String TopicChk(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("2")) {//教师,审核学生课题
			mav="TTopicChk";
		}else if(role.getRole_id().equals("3")) {//教秘,二级审核学生课题和一级审核教师课题
			mav="DTopicChk";
		}
		return mav;
	}
	/**
	 * 跳转成绩查询
	 */
	@RequestMapping(value="/toScore")
	public String Score(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//学生,查看本人成绩
			mav="SScore";
		}else if(role.getRole_id().equals("2")) {//指导教师,查看自己指导学生成绩
			mav="TScore";
		}else if(role.getRole_id().equals("3")) {//教学秘书，查看本院系所有成绩
			mav="DScore";
		}else if(role.getRole_id().equals("6")) {//教学院长，可查询本院所有成绩
			mav="DScore";
		}
		else if(role.getRole_id().equals("4")) {//教务处，所有院系成绩
			mav="AllDScore";
		}
		return mav;
	}
/**
 * 退出登录
 * @param session
 * @return
 */
	@RequestMapping(value="/exitLogin")
	public String exitLogin(HttpSession session) {
			session.invalidate();
			return "login";
		}
	/**
	 * 小组信息查询分类
	 */
	@RequestMapping(value="toGroupInfo")
	public String GroupInfo(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//学生,查看本小组及指导教师信息
			mav="SGroupInfo";
		}else if(role.getRole_id().equals("2")) {//指导教师,查看所指导小组信息
			mav="TGroupInfo";
		}else if(role.getRole_id().equals("3")||role.getRole_id().equals("6")) {//院长，院系教学秘书，查看本院系所有指导教师指导的小组
			mav="DSTGroupInfo";
		}else if(role.getRole_id().equals("4")) {//教务处，所有院系
			mav="AllDSTGroupInfo";
		}
		return mav;
	}
	/**
	 * 学生查看可申请答辩信息
	 */
	@RequestMapping(value="toSG")
	public String SG(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//学生,查看本人申请状态
			mav="SG";
		}
		return mav;
	}
	/**
	 * 查看学生答辩申请状态
	 */
	@RequestMapping(value="toSSpeechA")
	public String toSSpeechA(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("3")||role.getRole_id().equals("6")) {//院长，院系教学秘书，查看本院系所有指导教师指导的小组
			mav="DSG";
		}else if(role.getRole_id().equals("4")) {//教务处，所有院系
			mav="AllDSG";
		}
		return mav;
	}
	/**
	 * 答辩审批
	 */
	@RequestMapping(value="toSSpeechApply")
	public String SSpeechApply(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("2")) {//指导教师,查看所指导小组信息
			mav="TSG";
		}
		return mav;
	}
	/**
	 * 答辩专家组
	 */
	@RequestMapping(value="toTG")
	public String TG(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("2")) {//教师,查看所处答辩组信息
			mav="TG";
		}else if(role.getRole_id().equals("3")||role.getRole_id().equals("6")) {//院长，院系教学秘书，查看本院系所有答辩组
			mav="DTG";
		}else if(role.getRole_id().equals("4")) {//教务处，所有院系
			mav="AllTG";
		}
		return mav;
	}
	/**
	 * 文档上传
	 * @return
	 */
	@RequestMapping(value="/toUpload")
	public String Upload(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//
			mav="SUpload";
		}else if(role.getRole_id().equals("2")) {
			mav="TUpload";
		}
		return mav;
	}
	/**
	 * 查询学生文档
	 */
	@RequestMapping(value="toSDoc")
	public String SDoc(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("2")) {//教师,查看所处答辩组信息
			mav="SRead2";
		}else if(role.getRole_id().equals("3")||role.getRole_id().equals("6")) {//院长，院系教学秘书，查看本院系所有答辩组
			mav="SRead3";
		}else if(role.getRole_id().equals("4")) {//教务处，所有院系
			mav="SRead4";
		}
		return mav;
	}
	/**
	 * 文档审核
	 */
	@RequestMapping(value="toDocChk")
	public String DocChk(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("2")) {//教师,一审
			mav="tChk";
		}else if(role.getRole_id().equals("3")) {
			mav="dChk";
		}
		return mav;
	}
	/**
	 * 学生答辩分组
	 */
	@RequestMapping(value="toSpeechGroup")
	public String SpeechGroup(HttpSession session) {
		String mav="";
		Role role = (Role) session.getAttribute("role");
		if(role.getRole_id().equals("1")) {//
			mav="SSpeechGroup";
		}else if(role.getRole_id().equals("2")) {
			mav="TSpeechGroup";
		}else if(role.getRole_id().equals("3")||role.getRole_id().equals("6")) {
			mav="DSpeechGroup";
		}else if(role.getRole_id().equals("4")) {
			mav="AllDSpeechGroup";
		}
		return mav;
	}
	/**
	 * main
	 * @return
	 */
	@RequestMapping(value="/toMain")
	public String Main() {
		return "main";
	}
	// 跳转至发布新闻页
		@RequestMapping(value = "/toNews")
		public String News() {
			return "news";
		}
	/**
	 * 登录验证 登录时通过user_id匹配密码，匹配成功加载权限树
	 * 
	 * @param u
	 * @param session
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User u, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView mav = new ModelAndView();
		u.setPassword(MD5Utils.md5Password(u.getPassword()));
		log.info("User/login:页面传递的对象User:" + u.toString());
		User userLoginInfo = userService.userLogin(u);
		if (u != null&&userLoginInfo!=null) {
			
			Role role = userService.queryRoleNameById(userLoginInfo);
			if(userLoginInfo.getRole_id().equals("1")) {
				User_info_vo userInfo = userService.stuInfo(u);
				log.info("User/login:service返回值:" + userLoginInfo.toString());
				User_info_vo teaInfo=stuService.queryTInfo(userLoginInfo);
				if (userLoginInfo != null) {
					// 数据库中根据user_id查询用户信息,学生查至班级
					mav = new ModelAndView("main");
					session.setAttribute("userLoginInfo", userLoginInfo);
					session.setAttribute("userInfo", userInfo);
					session.setAttribute("role", role);
					session.setAttribute("role_id",userLoginInfo.getRole_id());
					if(teaInfo!=null) {
						session.setAttribute("teaInfo",teaInfo);
						log.info("userController============"+teaInfo.toString());
					}
					return mav;
				} 
			}else if(userLoginInfo.getRole_id().equals("2")) {
				User_info_vo userInfo = userService.teaInfo(u);
				log.info("User/login:service返回值:" + userLoginInfo.toString());
				if (userLoginInfo != null) {
					// 数据库中根据user_id查询用户信息，教师查至专业
					mav = new ModelAndView("main");
					session.setAttribute("userLoginInfo", userLoginInfo);
					session.setAttribute("userInfo", userInfo);
					session.setAttribute("role", role);
					session.setAttribute("role_id",userLoginInfo.getRole_id());
					return mav;
			}
			}
			else{
				User_info_vo userInfo = userService.userInfo(u);
				log.info("User/login:service返回值:" + userLoginInfo.toString());
				if (userLoginInfo != null) {
					// 数据库中根据user_id查询用户信息
					mav = new ModelAndView("main");
					session.setAttribute("userLoginInfo", userLoginInfo);
					session.setAttribute("userInfo", userInfo);
					session.setAttribute("role", role);
					session.setAttribute("role_id",userLoginInfo.getRole_id());
					return mav;
			}
		}
	}
		mav = new ModelAndView("login");
		mav.addObject("info","忘记密码？请联系教学秘书重置密码！");
		System.out.println("======"+mav.toString());
		return mav;
}
	/**
	 * 登录验证 登录时通过user_id匹配密码，匹配成功加载权限树
	 * 
	 * @param u
	 * @param session
	 * @param request 
	 * @param response 
	 *//*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(User u, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView mav = new ModelAndView();
		//String flag = null;	
		class massage{
			private String info;
			public String getInfo() {
				return info;
			}
			public void setInfo(String info) {
				this.info = info;
			}
		}
		massage ov = new massage();
		u.setPassword(MD5Utils.md5Password(u.getPassword()));
		log.info("User/login:页面传递的对象User:" + u.toString());
		User userLoginInfo = userService.userLogin(u);
		
		if (u != null&&userLoginInfo!=null) {
			
			Role role = userService.queryRoleNameById(userLoginInfo);
			if(userLoginInfo.getRole_id().equals("1")) {
				User_info_vo userInfo = userService.stuInfo(u);
				log.info("User/login:service返回值:" + userLoginInfo.toString());
				User_info_vo teaInfo=stuService.queryTInfo(userLoginInfo);
				if (userLoginInfo != null) {
					// 数据库中根据user_id查询用户信息,学生查至班级
					//mav = new ModelAndView("main");
					//flag = "{\"flag\":\"success\",\"info\":\"登录成功\",\"status\":\"y\"}";
					ov.setInfo("success");
					session.setAttribute("userLoginInfo", userLoginInfo);
					session.setAttribute("userInfo", userInfo);
					session.setAttribute("role", role);
					session.setAttribute("role_id",userLoginInfo.getRole_id());
					session.setAttribute("teaInfo",teaInfo);
					log.info("userController============"+teaInfo.toString());
					//return flag;
					//return mav;
					return ov;
				} 
			}else if(userLoginInfo.getRole_id().equals("2")) {
				User_info_vo userInfo = userService.teaInfo(u);
				log.info("User/login:service返回值:" + userLoginInfo.toString());
				if (userLoginInfo != null) {
					// 数据库中根据user_id查询用户信息，教师查至专业
					//mav = new ModelAndView("main");
					//flag = "{\"flag\":\"success\",\"info\":\"登录成功\",\"status\":\"y\"}";
					ov.setInfo("success");
					session.setAttribute("userLoginInfo", userLoginInfo);
					session.setAttribute("userInfo", userInfo);
					session.setAttribute("role", role);
					session.setAttribute("role_id",userLoginInfo.getRole_id());
					//return flag;
					//return mav;
					return ov;
			}
			}
			else{
				User_info_vo userInfo = userService.userInfo(u);
				log.info("User/login:service返回值:" + userLoginInfo.toString());
				if (userLoginInfo != null) {
					// 数据库中根据user_id查询用户信息
					//mav = new ModelAndView("main");
					//flag = "{\"flag\":\"success\",\"info\":\"登录成功\",\"status\":\"y\"}";
					ov.setInfo("success");
					session.setAttribute("userLoginInfo", userLoginInfo);
					session.setAttribute("userInfo", userInfo);
					session.setAttribute("role", role);
					session.setAttribute("role_id",userLoginInfo.getRole_id());
					//return flag;
					//return mav;
					return ov;
			}
		}
	}
		//mav = new ModelAndView("login");
		//return mav;
		//flag = "{\"flag\":\"fail\",\"info\":\"用户名或密码错误\",\"status\":\"n\"}";
		ov.setInfo("fail");
		return  ov;
}*/
	/**
	 * 加载权限树byUser
	 * @param u
	 */
	@RequestMapping(value="/queryTreeByUser")
	@ResponseBody
	public List<TreeNode> queryTreeByUser(HttpServletRequest request,HttpSession session) {
		
		List<TreeNode> listTree = new ArrayList<TreeNode>();//封装为EasyUI Tree 数据格式
		//根据用户ID查询权限树
		List<Permission> listPermission = userService.queryTreeByUser((User)session.getAttribute("userLoginInfo"));
		
		for(Permission item:listPermission) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			listTree.add(treeNode);
		}
		return listTree;
	}
	/**
	 * 加载权限树byUser2
	 * @param u
	 */
	@RequestMapping(value="/queryTreeByUser2")
	@ResponseBody
	public List<TreeNode> queryTreeByUser2(HttpServletRequest request,HttpSession session) {
		
		List<TreeNode> listTree = new ArrayList<TreeNode>();//封装为EasyUI Tree 数据格式
		//根据用户ID查询权限树
		String user_id = request.getParameter("user_id");
		User user = new User();
		user.setUser_id(user_id);
		List<Permission> listPermission = userService.queryTreeByUser(user);
		
		for(Permission item:listPermission) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			listTree.add(treeNode);
		}
		return listTree;
	}
	/**
	 * 加载权限树byUserId
	 * 用户直接对用户授权
	 * @param u
	 */
	@RequestMapping(value="/queryTreeByUserId")
	@ResponseBody
	public List<TreeNode> queryTreeByUserId(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		User user = new User();
		user.setUser_id(String.valueOf(userInfoVo.getUser_id()));
		
		List<TreeNode> listTree = new ArrayList<TreeNode>();//封装为EasyUI Tree 数据格式
		
		List<Permission> list1 = userService.queryTree();//查询系统所有权限树
		List<Permission> list2 = userService.queryTreeByUser(user);
		//根据user_id查询用户所属角色权限联合user_permission表，查询用户当前权限
		
		list1.removeAll(list2);//差集
		
		for(Permission item:list2) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			treeNode.setChecked(true);
			
			listTree.add(treeNode);
		}
		for(Permission item:list1) {//设置差集checkbox为false
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			treeNode.setChecked(false);
			listTree.add(treeNode);
		}
		return listTree;
	}
	/**
	 * 加载权限树,当前角色已有权限，并作checked state处理
	 * @param u
	 */
	@RequestMapping(value="/queryTree")
	@ResponseBody
	public List<TreeNode> queryTree(HttpServletRequest request,HttpSession session,Role role) {
		
		List<TreeNode> listTree = new ArrayList<TreeNode>();//封装为EasyUI Tree 数据格式
		
		List<Permission> list1 = userService.queryTree();//查询所有树
		List<Permission> list2 = userService.queryTreeByRole(role);//根据role查询树
		
		list1.removeAll(list2);//差集
		
		for(Permission item:list2) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			treeNode.setChecked(true);
			
			listTree.add(treeNode);
		}
		for(Permission item:list1) {//设置差集checkbox为false
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			treeNode.setChecked(false);
			listTree.add(treeNode);
		}
		return listTree;
	}
	/**
	 * 加载权限树byRole，直接显示当前角色tree，去除checkbox
	 * @param u
	 */
	@RequestMapping(value="/queryTreeByRole")
	@ResponseBody
	public List<TreeNode> queryTreeByRole(HttpServletRequest request,HttpSession session,Role role) {
		
		List<TreeNode> listTree = new ArrayList<TreeNode>();//封装为EasyUI Tree 数据格式
		List<Permission> listPermission = userService.queryTreeByRole(role);
		
		for(Permission item:listPermission) {
			TreeNode treeNode = new TreeNode(); 
			treeNode.setParentId(String.valueOf(item.getParentId()));
			treeNode.setId(String.valueOf(item.getPermission_id()));
			treeNode.setText(item.getPermission_name());
			Map map = new HashMap();
			map.put("url",item.getPermission_url());
			treeNode.setAttributes(map);
			treeNode.setState(item.getState());
			listTree.add(treeNode);
		}
		return listTree;
	}
	/**
	 * Stu信息修改
	 */
	@RequestMapping(value="/stuInfoEdit")
	//@ResponseBody
	public void stuInfoEdit(HttpServletRequest request,HttpServletResponse response,User_info_vo userInfo) {
		log.info("user_info"+userInfo.toString());
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		Dept dept = new Dept();
		Major major = new Major();
		Classes classes = new Classes();
		
		String dName = request.getParameter("deptId");
		String majorName = request.getParameter("majorId");
		String cName = request.getParameter("classesId");
		
		dept.setdName(dName);
		major.setMajorName(majorName);
		classes.setcName(cName);
		
		dept = userService.queryDeptInfoByParam(dept);
		major = userService.queryMajorInfoByParam(major);
		classes = userService.queryClassesInfoByParam(classes);
		
		userInfo.setDeptId(dept.getDeptId());
		userInfo.setMajorId(major.getMajorId());
		userInfo.setClassesId(classes.getClassesId());
		
		boolean flag=userService.userInfoEdit(userInfo);
		if(flag){
			//return "{\"info\":\"编辑成功!\"}";
			 out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			 out.println("<script>alert('编辑失败！请核对后重新编辑！');history.back();</script>");
		}
	}
	/**
	 * 用户信息修改
	 */
	@RequestMapping(value="/userInfoEdit")
	//@ResponseBody
	public void userInfoEdit(HttpServletRequest request,HttpServletResponse response,User_info_vo userInfo) {
		log.info("user_info"+userInfo.toString());
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		boolean flag=userService.userInfoEdit(userInfo);
		if(flag){
			//return "{\"info\":\"编辑成功!\"}";
			 out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			 out.println("<script>alert('编辑失败！请核对后重新编辑！');history.back();</script>");
		}
	}
	/**
	 * 用户密码修改
	 */
	@RequestMapping(value="/userPasswordEdit")
/*	@ResponseBody*/
	public void userPasswordEdit(HttpServletRequest request,HttpSession session,HttpServletResponse response,User user) {
		log.info("user_info"+user.toString());
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		User u = (User)session.getAttribute("userLoginInfo");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User u1 = new User();
		u1.setUser_id(u.getUser_id());
		u1.setPassword(MD5Utils.md5Password(password1));
		boolean flag = false;
		String view = null;
		//log.info("user======:"+user.getPassword());
		//log.info("SESSIONu======:"+u.getPassword());

		if(MD5Utils.md5Password(user.getPassword()).equals(u.getPassword())&&password1.equals(password2)){
			flag=userService.userPasswordEdit(u1);
			//view= "exitLogin";
			out.println("<script>alert('修改成功，请重新登录！');history.back();window.open('toLogin');window.opener=null;window.parent.close();</script>");
		}else {
			out.println("<script>alert('两次密码输入不一致,请核对后重新提交！');history.back();</script>");
		}
		//return view;
	}
	/**
	 * 查询进度信息
	 */
	@RequestMapping(value="/queryProcess")
	@ResponseBody
	public Object queryProcess(HttpServletRequest request,HttpSession session,Process process) {
		
		
		List<Process> li=userService.queryProcess(process);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Process> list = new ArrayList<Process>();
		//定义开始位置
		int start = (page-1)*rows;
		//定义结束位置
		int end = start + rows;
		if(end>li.size()) {
			end = li.size();
		}
		//对本页进行赋值
		for(int i = start;i<end;i++) {
			list.add(li.get(i));
		}
		//设置数据包
		Map<String, Object> map = new HashMap<String, Object>();
		//对数据包中添加数据，total：表示数据总数（从数据库中查出来的）
		map.put("total", li.size());
		//本页数据
		map.put("rows", list);
		return map;
	}
	/**
	 * 编辑process
	 */
	@RequestMapping(value="/processEdit")
	//@ResponseBody
	public void processEdit(HttpServletRequest request,HttpServletResponse response,Process process) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=userService.processEdit(process);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加process
	 */
	@RequestMapping(value="/processAdd")
	//@ResponseBody
	public  void processAdd(HttpServletRequest request,HttpServletResponse response,Process process) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=userService.processAdd(process);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除process
	 */
	@RequestMapping(value="/processDel")
	//@ResponseBody
	public  void processDel(HttpServletRequest request,HttpServletResponse response,Process process) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		String ids = request.getParameter("strid");
		String []str = ids.split(","); 
		log.info("ids:"+ids);
		boolean flag = true;// 只要有一个删除失败，则退出
		for (int i = 0; i < str.length; i++) {
			if (!userService.processDel(str[i])) {
				flag = false;
				break;
			}
		}
		if (flag) {
			out.println("<script>alert('删除成功！请刷新页面！');history.back();</script>");
		} else {
			out.println("<script>alert('删除失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 查询docType
	 * @return
	 */
	@RequestMapping(value="/queryDocType")
	@ResponseBody
	public Object queryDocType(HttpServletRequest request,HttpSession session,DocType docType) {
		List<DocType> li=userService.queryDocType(docType);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<DocType> list = new ArrayList<DocType>();
		//定义开始位置
		int start = (page-1)*rows;
		//定义结束位置
		int end = start + rows;
		if(end>li.size()) {
			end = li.size();
		}
		//对本页进行赋值
		for(int i = start;i<end;i++) {
			list.add(li.get(i));
		}
		//设置数据包
		Map<String, Object> map = new HashMap<String, Object>();
		//对数据包中添加数据，total：表示数据总数（从数据库中查出来的）
		map.put("total", li.size());
		//本页数据
		map.put("rows", list);
		return map;
	}
	/**
	 * 增加docType
	 */
	@RequestMapping(value="/docTypeAdd")
	//@ResponseBody
	public  void docTypeAdd(HttpServletRequest request,HttpServletResponse response,DocType docType) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=userService.docTypeAdd(docType);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 编辑docType
	 */
	@RequestMapping(value="/docTypeEdit")
	//@ResponseBody
	public void docTypeEdit(HttpServletRequest request,HttpServletResponse response,DocType docType) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=userService.docTypeEdit(docType);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 删除docType
	 */
	@RequestMapping(value="/docTypeDel")
	@ResponseBody
	public  Object docTypeDel(HttpServletRequest request,HttpServletResponse response) {
		
		String ids = request.getParameter("strid");
		String []str = ids.split(","); 
		boolean flag = true;// 只要有一个删除失败，则退出
		for (int i = 0; i < str.length; i++) {
			if (!userService.docTypeDel((str[i]))) {
				flag = false;
				break;
			}
		}
		class massage{
			private String info;
			public String getInfo() {
				return info;
			}
			public void setInfo(String info) {
				this.info = info;
			}
		}
		massage ov = new massage();
		if(flag) {
			ov.setInfo("删除成功");
		}else {
			ov.setInfo("删除失败");
		}
		return ov;
	}
	
	/*@ResponseBody
    @RequestMapping(value="/upload/editor/img")
    //RequestParam中的属性名称要和前端定义的一致，上面说明了．所以写"img"
    //使用List<MultipartFile>进行接收
    //返回的是一个Ｄto类，后面会说明，使用@ResponseBody会将其转换为Ｊson格式数据
    public ImgResultDto uploadEditorImg(@RequestParam("img") List<MultipartFile> list) {    
        //这里是我在web中定义的两个初始化属性，保存目录的绝对路径和相对路径，你们可以自定义    
        //String imgUploadAbsolutePath = request.getServletContext().getInitParameter("imgUploadAbsolutePath");
        //String imgUploadRelativePath = request.getServletContext().getInitParameter("imgUploadRelativePath");

    　　//服务曾处理数据，返回Dto
        ImgResultDto imgResult= addCommodityService.upLoadEditorImg(list, imgUploadAbsolutePath, 
                                                imgUploadRelativePath,1);
            return imgResult;           
    }*/


}