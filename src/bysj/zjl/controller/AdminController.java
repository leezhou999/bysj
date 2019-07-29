package bysj.zjl.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.io.filefilter.FalseFileFilter;
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
import bysj.zjl.service.IAdminService;
import bysj.zjl.service.IStuService;
import bysj.zjl.service.IUserService;
import bysj.zjl.service.impl.UserServiceImpl;
import bysj.zjl.util.FileOperate;
import bysj.zjl.util.MD5Utils;
import bysj.zjl.util.ToPdf;


/**
 * 管理员Controller
 * @author zhoujiali
 *
 */
@Controller
@RequestMapping(value="/Admin")
public class AdminController {
	private Logger log = LoggerFactory.getLogger(getClass());
	private IAdminService adminService;

	@Autowired
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
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
	 * 查询permission
	 * @return
	 */
	@RequestMapping(value="/queryPermission")
	@ResponseBody
	public Object queryPermission(HttpServletRequest request,Permission permission) {
		List<Permission> li=adminService.queryPermission(permission);
		//记录本页页码
		//log.info(("li.size:"+li.size()));
		//log.info("requestPage:"+request.getParameter("page"));
		int page = Integer.parseInt(request.getParameter("page"));
		//log.info("page"+page);
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//log.info("rows"+rows);
		
		//新建存储本页数据的list
		List<Permission> list = new ArrayList<Permission>();
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
	 * 编辑permission
	 */
	@RequestMapping(value="/permissionEdit")
	//@ResponseBody
	public void permissionEdit(HttpServletRequest request,HttpServletResponse response,Permission permission) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		log.info("permission"+permission.toString());
		boolean flag=adminService.permissionEdit(permission);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加permission
	 */
	@RequestMapping(value="/permissionAdd")
	//@ResponseBody
	public  void permissionAdd(HttpServletRequest request,HttpServletResponse response,Permission permission) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		log.info("permission:"+permission);
		boolean flag=adminService.permissionAdd(permission);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除permission
	 */
	@RequestMapping(value="/delPermission")
	//@ResponseBody
	public  void permissionDel(HttpServletRequest request,HttpServletResponse response,Permission permission) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		String ids = request.getParameter("strid");
		String []str = ids.split(","); 
		log.info("ids:"+ids);
		boolean flag = true;// 只要有一个删除失败，则退出
		for (int i = 0; i < str.length; i++) {
			if (!adminService.permissionDel(str[i])) {
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
	 * 查询系统用户列表
	 * @return
	 */
	@RequestMapping(value="/queryUserList")
	@ResponseBody
	public Object queryUserList(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		
		userInfoVo =(User_info_vo)session.getAttribute("userInfo");
		String findstr = request.getParameter("findstr");
		userInfoVo.setFindstr(findstr);
		log.info(userInfoVo.toString());
		List<User_info_vo> li=adminService.queryUserList(userInfoVo);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 增加用户
	 */
	@RequestMapping(value="/userAdd")
	//@ResponseBody
	public  void userAdd(HttpServletRequest request,HttpServletResponse response,User_info_vo userInfoVo) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		//添加学生用户，添加id,name,password,sex,role,dept,major,classes,isOk
		//除学生，教务处，管理员外其他类型角色,添加id,name,password,sex,role,isOk
		User_info_vo stu = new User_info_vo();
		User_info_vo others = new User_info_vo();
		
		
		stu.setUser_id(userInfoVo.getUser_id());
		stu.setUser_name(userInfoVo.getUser_name());
		stu.setPassword(MD5Utils.md5Password(userInfoVo.getPassword()));
		stu.setUser_sex(userInfoVo.getUser_sex());
		stu.setRole_id(userInfoVo.getRole_id());
		stu.setDeptId(userInfoVo.getDeptId());
		stu.setMajorId(userInfoVo.getMajorId());
		stu.setClassesId(userInfoVo.getClassesId());
		stu.setIsOk(userInfoVo.getIsOk());
		
		others.setUser_id(userInfoVo.getUser_id());
		others.setUser_name(userInfoVo.getUser_name());
		others.setPassword(MD5Utils.md5Password(userInfoVo.getPassword()));
		others.setUser_sex(userInfoVo.getUser_sex());
		others.setRole_id(userInfoVo.getRole_id());
		others.setDeptId(userInfoVo.getDeptId());
		others.setIsOk(userInfoVo.getIsOk());
		
		boolean flag=false;
		if(userInfoVo.getRole_id().equals("1")) {
			//同时添加基本信息和详细信息,user_role
			if(adminService.userAdd(stu)&&adminService.stuUserInfoAdd(stu)&&adminService.userRoleAdd(stu)) {
				flag =true;
			}
		}else if(!userInfoVo.getRole_id().equals("1")) {
			if(adminService.userAdd(others)&&adminService.otherUserInfoAdd(others)&&adminService.userRoleAdd(others)) {
				flag =true;
			}
		}
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除user
	 */
	@RequestMapping(value="/delUser")
	//@ResponseBody
	public  void userDel(HttpServletRequest request,HttpServletResponse response,User_info_vo user) {
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
			if (!adminService.userDel(str[i])) {
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
	 * 编辑user
	 */
	@RequestMapping(value="/userEdit")
	//@ResponseBody
	public void userEdit(HttpServletRequest request,HttpServletResponse response,User_info_vo user) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		boolean flag=adminService.userEdit(user);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 查询角色
	 * @return
	 */
	@RequestMapping(value="/queryRole")
	@ResponseBody
	public Object queryRole(HttpServletRequest request,HttpSession session,Role role) {
		
		
		List<Role> li=adminService.queryRole(role);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Role> list = new ArrayList<Role>();
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
	 * 编辑role
	 */
	@RequestMapping(value="/roleEdit")
	//@ResponseBody
	public void roleEdit(HttpServletRequest request,HttpServletResponse response,Role role) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		log.info("permission"+role.toString());
		boolean flag=adminService.roleEdit(role);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加role
	 */
	@RequestMapping(value="/roleAdd")
	//@ResponseBody
	public  void roleAdd(HttpServletRequest request,HttpServletResponse response,Role role) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		log.info("permission:"+role);
		boolean flag=adminService.roleAdd(role);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除role
	 */
	@RequestMapping(value="/delRole")
	//@ResponseBody
	public  void roleDel(HttpServletRequest request,HttpServletResponse response,Role role) {
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
			if (!adminService.roleDel(str[i])) {
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
	 * 角色授权
	 */
	@RequestMapping(value="/changeAuthority")
	@ResponseBody
	public  Object changeAuthority(HttpServletRequest request,HttpServletResponse response,Role role,Permission permission) {
		String ids = request.getParameter("ids");
		String role_id=request.getParameter("role_id");
		String []str = ids.split(","); //permission_id
		boolean flag = false;
		String info="";
		if(adminService.countNum(role_id)==0) {//记录数判断
			for (int i = 0; i < str.length; i++) {
				if (!adminService.changeAuthority(role_id, str[i])) {// 只要有一个更新失败，则退出
					flag = false;
					break;
				}
			}
			flag=true;
			log.info("flag"+flag);
		}else if(adminService.countNum(role_id)>0) {
			flag=adminService.delRolePermission(role_id);//删除关联
			if (flag) {//关联删除成功，进行授权
				for (int i = 0; i < str.length; i++) {
					if (!adminService.changeAuthority(role_id, str[i])) {// 只要有一个更新失败，则退出
						flag = false;
						break;
					}
				}
			}
			flag = true;
			log.info("flag"+flag);
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
			ov.setInfo("授权成功");
		}else {
			ov.setInfo("授权失败");
		}
		return ov;
	}
	/**
	 * 对用户直接授权
	 */
	@RequestMapping(value="/changeAuthorityByUserId")
	@ResponseBody
	public  Object changeAuthorityByUserId(HttpServletRequest request,HttpServletResponse response,User_info_vo userInfoVo,Permission permission) {
		String ids = request.getParameter("ids");
		String user_id=request.getParameter("user_id");
		
		String []str = ids.split(","); //permission_id
		boolean flag = false;
		if(adminService.countNumUserPermission(user_id)==0) {
			for (int i = 0; i < str.length; i++) {
				if (!adminService.changeAuthorityByUser(user_id, str[i])) {// 只要有一个更新失败，则退出
						flag = false;
						break;
					}
				}
				flag=true;
			
		}else if(adminService.countNumUserPermission(user_id)>0) {
			if(adminService.delUserPermission(user_id)) {//授权前删除user_permission关联信息
				for (int i = 0; i < str.length; i++) {
					if (!adminService.changeAuthorityByUser(user_id, str[i])) {// 只要有一个更新失败，则退出
							flag = false;
							break;
						}
					}
					flag=true;
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
			ov.setInfo("直接对用户授权成功");
		}else {
			ov.setInfo("授权失败");
		}
		return ov;
	}
	
	/**
	 * 教学秘书查询院系信息
	 */
	@RequestMapping(value="/queryDeptInfo")
	@ResponseBody
	public Object queryDeptInfo(HttpServletRequest request,HttpSession session) {
		User user = (User)session.getAttribute("userLoginInfo");
		
		List<Dept> li=adminService.queryDeptInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Dept> list = new ArrayList<Dept>();
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
	 * 编辑院系信息
	 */
	@RequestMapping(value="/deptInfoEdit")
	//@ResponseBody
	public void deptInfoEdit(HttpServletRequest request,HttpServletResponse response,Dept dept) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=adminService.deptInfoEdit(dept);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	
	/**
	 * 教学秘书查询
	 */
	@RequestMapping(value="/queryMajorInfo")
	@ResponseBody
	public Object queryMajorInfo(HttpServletRequest request,HttpSession session,Major major) {
		User user = (User)session.getAttribute("userLoginInfo");
		List<Major> li=adminService.queryMajorInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Major> list = new ArrayList<Major>();
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
	 * 编辑专业信息
	 */
	@RequestMapping(value="/majorInfoEdit")
	//@ResponseBody
	public void majorInfoEdit(HttpServletRequest request,HttpServletResponse response,Major major) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=adminService.majorInfoEdit(major);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加majorInfo
	 */
	@RequestMapping(value="/majorInfoAdd")
	//@ResponseBody
	public  void majorInfoAdd(HttpServletRequest request,HttpServletResponse response,Major major) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=adminService.majorInfoAdd(major);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除majorInfo
	 */
	@RequestMapping(value="/majorInfoDel")
	//@ResponseBody
	public  void majorInfoDel(HttpServletRequest request,HttpServletResponse response,Major major) {
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
			if (!adminService.majorInfoDel(str[i])) {
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
	 * 教学秘书查询ClassesInfo
	 */
	@RequestMapping(value="/queryClassesInfo")
	@ResponseBody
	public Object queryClassesInfo(HttpServletRequest request,HttpSession session) {
		User user = (User)session.getAttribute("userLoginInfo");
		List<Classes> li=adminService.queryClassesInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Classes> list = new ArrayList<Classes>();
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
	 * 编辑classesInfo
	 */
	@RequestMapping(value="/classesInfoEdit")
	//@ResponseBody
	public void classesInfoEdit(HttpServletRequest request,HttpServletResponse response,Classes classes) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=adminService.classesInfoEdit(classes);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加classesInfo
	 */
	@RequestMapping(value="/classesInfoAdd")
	//@ResponseBody
	public  void classesInfoAdd(HttpServletRequest request,HttpServletResponse response,Classes classes) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=adminService.classesInfoAdd(classes);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除classesInfo
	 */
	@RequestMapping(value="/classesInfoDel")
	//@ResponseBody
	public  void classesInfoDel(HttpServletRequest request,HttpServletResponse response,Classes classes) {
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
			if (!adminService.classesInfoDel(str[i])) {
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
	 * 教师查询答辩小组信息
	 */
	@RequestMapping(value="/querySG")
	@ResponseBody
	public Object querySG(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=adminService.querySG(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 教秘、院长查询答辩小组信息
	 */
	@RequestMapping(value="/queryDSG")
	@ResponseBody
	public Object queryDSG(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=adminService.queryDSG(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 查询小组信息
	 * 教秘，院长等查询本院小组信息
	 */
	@RequestMapping(value="/queryDSTInfo")
	@ResponseBody
	public Object queryDSTGroupInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=adminService.queryDSTGroupInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 教秘，院长等查询本院教师信息
	 * 
	 */
	@RequestMapping(value="/queryDTInfo")
	@ResponseBody
	public Object queryDTInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=adminService.queryDTInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 查询小组信息
	 * 教务处查询全校小组信息
	 */
	@RequestMapping(value="/queryAllDSTGroupInfo")
	@ResponseBody
	public Object queryAllDSTGroupInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=adminService.queryAllDSTGroupInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 教务处查询全校教师信息
	 * 
	 */
	@RequestMapping(value="/queryAllDTInfo")
	@ResponseBody
	public Object queryAllDTInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=adminService.queryAllDTInfo(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<User_info_vo> list = new ArrayList<User_info_vo>();
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
	 * 教师查询本人课题
	 */
	@RequestMapping(value="/queryTTopic")
	@ResponseBody
	public Object queryTTopic(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		
		List<Topic> li=adminService.queryTTopic(user);
		
		int maxTopicId = userService.maxTopicId();
		maxTopicId +=1;
		session.setAttribute("maxTopicId", maxTopicId);
		
		log.info("maxTopicId=========="+maxTopicId);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Topic> list = new ArrayList<Topic>();
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
	 * 教务处查询全校课题
	 */
	@RequestMapping(value="/queryAllDTopic")
	@ResponseBody
	public Object queryAllDTopic(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		String findstr = request.getParameter("findstr");
		userInfoVo.setFindstr(findstr);
		List<Topic> li=adminService.queryAllDTopic(userInfoVo);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Topic> list = new ArrayList<Topic>();
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
	 * 教秘查询D课题
	 */
	@RequestMapping(value="/queryDTopic")
	@ResponseBody
	public Object queryDTopic(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		String findstr = request.getParameter("findstr");
		userInfoVo.setFindstr(findstr);
		userInfoVo.setUser_id(user.getUser_id());
		List<Topic> li=adminService.queryDTopic(userInfoVo);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Topic> list = new ArrayList<Topic>();
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
	 * 教师查询SA课题
	 */
	@RequestMapping(value="/querySATopic")
	@ResponseBody
	public Object querySATopic(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		String findstr = request.getParameter("findstr");
		userInfoVo.setFindstr(findstr);
		userInfoVo.setUser_id(user.getUser_id());
		List<Topic> li=adminService.querySATopic(userInfoVo);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Topic> list = new ArrayList<Topic>();
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
	 * 教秘查询DSTA课题
	 */
	@RequestMapping(value="/queryDSTATopic")
	@ResponseBody
	public Object queryDSTATopic(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		String findstr = request.getParameter("findstr");
		userInfoVo.setFindstr(findstr);
		userInfoVo.setUser_id(user.getUser_id());
		List<Topic> li=adminService.queryDSTATopic(userInfoVo);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Topic> list = new ArrayList<Topic>();
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
	 * 编辑课题
	 */
	@RequestMapping(value="/topicEdit")
	//@ResponseBody
	public void topicEdit(HttpServletRequest request,HttpServletResponse response,Topic topic) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean flag=adminService.topicEdit(topic);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加topic
	 */
	@RequestMapping(value="/topicAdd")
	//@ResponseBody
	public  void topicAdd(HttpServletRequest request,HttpServletResponse response,HttpSession session,Topic topic) {
		User user = (User)session.getAttribute("userLoginInfo");
		topic.setUser_id(user.getUser_id());
		topic.setTea_id(user.getUser_id());
		
		Dept dept = new Dept();
		dept.setdName(topic.getdName());
		String deptId =userService.queryDeptInfoByParam(dept).getDeptId();
		
		Major major = new Major();
		major.setMajorName(topic.getMajorName());
		String majorId = userService.queryMajorInfoByParam(major).getMajorId();
		
		topic.setUser_name(user.getUser_name());
		topic.setDeptId(deptId);
		topic.setMajorId(majorId);
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		boolean flag=adminService.topicAdd(topic);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除topic
	 */
	@RequestMapping(value="/topicDel")
	//@ResponseBody
	public  void topicDel(HttpServletRequest request,HttpServletResponse response,Topic topic) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		String ids = request.getParameter("strid");
		String[]str = ids.split(","); 
		boolean flag = true;// 只要有一个删除失败，则退出
		for (int i = 0; i < str.length; i++) {
			int j = Integer.parseInt(str[i]);
			if (!adminService.topicDelInt(j)) {
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
	 * 教师审批学生课题
	 */
	@RequestMapping(value="/chk")
	//@ResponseBody
	public void chk(HttpServletRequest request,HttpServletResponse response,Topic topic) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//审批类型
		//审批状态，同步更新学生自拟课题状态
		if(topic.getTypeId().equals("21")) {//如果是学生自拟课题，若教师退回修改或拒绝选题，则修改state为1
			if(topic.getChk().equals("1")||topic.getChk().equals("7")) {//未通过或退回修改，同时设置自拟课题为不通过
				topic.setStateId("1");
			}else if(topic.getChk().equals("2")) {//通过选题申请，同时设置自拟课题状态为通过，交由教学秘书二次审批
				topic.setStateId("2");
			}else if(topic.getChk().equals("6")) {//通过选题申请，同时设置自拟课题状态为通过，交由教学秘书二次审批
				topic.setStateId("0");
			}
		}
		
		log.info("topic==="+topic.toString());
		boolean flag=adminService.chk(topic);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 教秘审批学生、教师课题
	 */
	@RequestMapping(value="/dChk")
	//@ResponseBody
	public void dChk(HttpServletRequest request,HttpServletResponse response,Topic topic) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean flag=false;
		//教秘只审批学生自拟课题
		log.info("topic==="+topic.toString());
		if(topic.getTypeId().equals("21")) {//如果是学生自拟课题
			flag=adminService.dChk(topic);
		}else if(topic.getTypeId().equals("22")) {//如果是教师命题
			flag=adminService.dChk(topic);

		}
		
		if(flag){
			out.println("<script>alert('审批成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('审批失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 教师审批学生答辩
	 */
	@RequestMapping(value="/sApplyChk")
	//@ResponseBody
	public void sApplyChk(HttpServletRequest request,HttpServletResponse response,User_info_vo userInfoVo) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("userInfoVo==="+userInfoVo.toString());
		boolean flag=adminService.sApplyChk(userInfoVo);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 教师查看学生成绩
	 */
	@RequestMapping(value="/queryTScore")
	@ResponseBody
	public Object queryTScore(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Score> li=adminService.queryTScore(user);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Score> list = new ArrayList<Score>();
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
	 * 教秘、院长查看学生成绩
	 */
	@RequestMapping(value="/queryDScore")
	@ResponseBody
	public Object queryDScore(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Score> li=adminService.queryDScore(user);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Score> list = new ArrayList<Score>();
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
	 * 教务处查看全校成绩
	 */
	@RequestMapping(value="/queryAllDScore")
	@ResponseBody
	public Object queryAllDScore(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Score> li=adminService.queryAllDScore(user);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Score> list = new ArrayList<Score>();
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
	 * 编辑score
	 */
	@RequestMapping(value="/scoreEdit")
	//@ResponseBody
	public void scoreEdit(HttpServletRequest request,HttpServletResponse response,Score score) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		log.info("score"+score.toString());
		boolean flag=adminService.scoreEdit(score);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 增加score
	 */
	@RequestMapping(value="/scoreAdd")
	//@ResponseBody
	public  void scoreAdd(HttpServletRequest request,HttpServletResponse response,Score score) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		log.info("score:"+score);
		boolean flag=adminService.scoreAdd(score);;
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('添加失败！请核对后重新添加！');history.back();</script>");
		}
	}
	/**
	 * 删除score
	 */
	@RequestMapping(value="/scoreDel")
	//@ResponseBody
	public  void scoreDel(HttpServletRequest request,HttpServletResponse response,Score score) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		String ids = request.getParameter("strid");
		String []str = ids.split(","); 
		log.info("ids:"+ids);
		boolean flag = true;// 只要有一个删除失败，则退出
		for (int i = 0; i < str.length; i++) {
			if (!adminService.scoreDel(str[i])) {
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
	 * 教秘、院长查看TG
	 */
	@RequestMapping(value="/queryDTG")
	@ResponseBody
	public Object queryDTG(HttpServletRequest request,HttpSession session,Tea_group tea_group) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Tea_group> li=adminService.queryDTG(user);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Tea_group> list = new ArrayList<Tea_group>();
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
	 * 教师查看TG
	 */
	@RequestMapping(value="/queryTG")
	@ResponseBody
	public Object queryTG(HttpServletRequest request,HttpSession session,Tea_group tea_group) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Tea_group> li=adminService.queryTG(user);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Tea_group> list = new ArrayList<Tea_group>();
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
	 * 教务处查看AllDTG
	 */
	@RequestMapping(value="/queryAllDTG")
	@ResponseBody
	public Object queryAllDTG(HttpServletRequest request,HttpSession session,Tea_group tea_group) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Tea_group> li=adminService.queryAllDTG(user);
		
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		//新建存储本页数据的list
		List<Tea_group> list = new ArrayList<Tea_group>();
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
	 * 编辑DTG
	 */
	@RequestMapping(value="/dTGEdit")
	//@ResponseBody
	public void dTGEdit(HttpServletRequest request,HttpServletResponse response,Tea_group tea_group) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		log.info("tea_group"+tea_group.toString());
		boolean flag=false;
		if(adminService.chkDTGIsHeadMan(tea_group.getGroupId())=='0') {//不存在组长可继续操作
			flag=adminService.dTGEdit(tea_group);
		}else {
			out.println("<script>alert('编辑失败！只能有一位组长！');history.back();</script>");
		}
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}
	}
	/**
	 * 增加DTG
	 */
	@RequestMapping(value="/dTGAdd")
	//@ResponseBody
	public  void dTGAdd(HttpServletRequest request,HttpServletResponse response,Tea_group tea_group) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		log.info("tea_group:"+tea_group);
		boolean flag=false;
		if(adminService.chkDTGIsHeadMan(tea_group.getGroupId())=='0') {//不存在组长可继续操作
			flag=adminService.dTGAdd(tea_group);
		}else {
			out.println("<script>alert('添加失败！只能有一位组长！');history.back();</script>");
		}
		if(flag){
			out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
		}
	}
	/**
	 * 删除DTG
	 */
	@RequestMapping(value="/dTGDel")
	//@ResponseBody
	public  void dTGDel(HttpServletRequest request,HttpServletResponse response,Tea_group tea_group) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		String ids = request.getParameter("strid");
		String []str = ids.split(","); 
		log.info("ids:"+ids);
		boolean flag = true;// 只要有一个删除失败，则退出
		for (int i = 0; i < str.length; i++) {
			if (!adminService.dTGDel(str[i])) {
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
	 * 教师查看学生文档
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/tQueryAllSDoc")
	@ResponseBody
	public Object  tQueryAllSDoc(HttpSession session,HttpServletRequest request) {
		User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		User user = new User();
		user.setUser_id(stuInfo.getUser_id());
		List<Document> li=adminService.tQueryAllSDoc(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//新建存储本页数据的list
		List<Document> list = new ArrayList<Document>();
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
	 * 教秘、院长查看学生文档
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryDDoc")
	@ResponseBody
	public Object  queryDDoc(HttpSession session,HttpServletRequest request) {
		User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		User user = new User();
		user.setUser_id(stuInfo.getUser_id());
		List<Document> li=adminService.queryDDoc(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//新建存储本页数据的list
		List<Document> list = new ArrayList<Document>();
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
	 * 教秘、院长查看学生文档
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryAllDDoc")
	@ResponseBody
	public Object  queryAllDDoc(HttpSession session,HttpServletRequest request) {
	
		List<Document> li=adminService.queryAllDDoc();
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//新建存储本页数据的list
		List<Document> list = new ArrayList<Document>();
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
	 * 教师文件上传
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/tUpload",method = RequestMethod.POST)
	
	public void sUpload(@RequestParam(value="location",required=false) MultipartFile mulFile,HttpServletRequest request,HttpServletResponse response,HttpSession session) {

		 ToPdf toPdf = new ToPdf();
		 Document document=new Document();//文档实体
		 User user = new User();
		
		 String stu_id = request.getParameter("stu_id");
		 user.setUser_id(stu_id);
		 User_info_vo stuInfo = userService.stuInfo(user);
		 
		 System.out.println("teaInfoSESSION============："+stuInfo);
		 
	     String docPath = "D:\\bysjFile\\"; //磁盘绝对路径根路径
	     String absPath = null;//绝对路径
	     String newFileName = null;//文档名
	     String virPath="/doc/";//数据库存储虚拟相对路径
	     String dirDName = stuInfo.getDeptId();//文件夹命名：院系id
	     String dirMName = stuInfo.getMajorId();//文件夹命名：专业id
	     String dirCName = stuInfo.getClassesId();//文件夹命名：班级id
	     String dirYear = "20"+stuInfo.getUser_id().substring(0,2);//截取学号2位，组合为年级，如：2015级
	     String dirSName =stuInfo.getUser_id();//文件命名：学号+文档类型
	     String docType =request.getParameter("typeId");//文档类型
	     String tea_id = ((User_info_vo)stuService.queryTInfo(user)).getUser_id();//教师工号
	     String docId =stuInfo.getUser_id()+docType;//文档编号：学号+文档类型
	     boolean isUploadSuccess=false;
	     
	     String originalFileName = null;//源文件名
	     originalFileName = mulFile.getOriginalFilename();
	     
	     document.setDocId(docId);
		 document.setStu_id(stuInfo.getUser_id());
		 document.setTea_id(tea_id);
		 document.setDeptId(stuInfo.getDeptId());
		 document.setMajorId(stuInfo.getMajorId());
		 document.setClassesId(stuInfo.getClassesId());
		 document.setTypeId(docType);
		    
		 System.out.println("document实体"+document);
		 System.out.println("oriname"+originalFileName); 
	     
	     PrintWriter out = null;
	     File newFile = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		 String docAdviceId = stuInfo.getUser_id()+"1"+docType.substring(1);
		 System.out.println("修改过docAdviceId:"+docAdviceId);
		 //上传文件之前，删除pdf文件
		 FileOperate.delFile(docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\"+stuInfo.getUser_id()+docType+".pdf");
		 System.out.println("文件上传前已删除pdf:"+docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\"+stuInfo.getUser_id()+docType+".pdf");
	     	//查询指定id文档是否已存在，不存在才能正常上传文档
	     if((stuService.queryDocByDocId(stuInfo.getUser_id()+docType))==null) {
	    	 System.out.println("执行到这里----------1-----------");
	     // 继续判断是否存在对应文档的修改意见，不存在才能正常上传
	    	 if(stuService.queryDocByDocId(docAdviceId)==null) {
	    		 System.out.println("执行到这里----------2-----------");
	    		 //判断表单信息，文件类型和表单信息不为空，才能正常上传
	    		 if (mulFile != null && originalFileName != null && originalFileName.length() > 0) {
	    			 System.out.println("执行到这里----------3-----------");
	    			 //开始上传，接收参数，文件
	 		    	newFileName = stuInfo.getUser_id()+docType+ originalFileName.substring(originalFileName.lastIndexOf("."));//新文件名  
	 		    	absPath = docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\";//绝对路径
	 		        
	 		    	newFile = new File(absPath + newFileName);
	 		        if(!newFile.exists()){//创建文件
	 		        	 System.out.println("执行到这里----------4-----------");
	 		        	//路径判断
	 					File dir = new File(absPath);
	 					dir.mkdirs();
	 				}
	 		     // 将内存中的数据写入磁盘
	 		        try {
	 					mulFile.transferTo(newFile);
	 				} catch (IllegalStateException | IOException e) {
	 					e.printStackTrace();
	 				}
	 		        virPath+=dirDName+"/"+dirYear+"/"+dirCName+"/"+dirSName+"/"+newFileName;
	 		        document.setLocation(virPath);
	 		        //设置上传标记
	 		        isUploadSuccess = stuService.sUpload(document);   
	 		    } 
	    		 if (isUploadSuccess) {
	    			 System.out.println("执行到这里----------5-----------");
		 				out.println("<script>alert('文件上传成功!');history.back();</script>");
		 				//str="{\"info\":\"提交信息成功!\"}";
		 			}else {
		 				 System.out.println("执行到这里----------6-----------");
		 				out.println("<script>alert('文件上传失败!');history.back();</script>");
		 			}
	    	 } else if(stuService.queryDocByDocId(docAdviceId)!=null){//存在文件意见
	    		 System.out.println("执行到这里----------7-----------");
	    		 //删除意见文档和数据库记录，上传文档
	    		 newFileName = stuInfo.getUser_id()+docType+ originalFileName.substring(originalFileName.lastIndexOf("."));//新文件名  
	 		     absPath = docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\";//绝对路径
	 		     
	    		 stuService.deleteDocByDocId(docAdviceId);
	    		 System.out.println("数据库:文档意见记录已删除1");
	    		 
	    		 FileOperate.delFile(absPath+newFileName);
	    		 System.out.println("磁盘:文档意见记录已删除1");
	    		 
	    		//判断表单信息，文件类型和表单信息不为空，才能正常上传
	    		 if (mulFile != null && originalFileName != null && originalFileName.length() > 0) {
	    			 System.out.println("执行到这里----------8-----------");
	    			 //开始上传，接收参数，文件
	 		    	newFileName = stuInfo.getUser_id()+docType+ originalFileName.substring(originalFileName.lastIndexOf("."));//新文件名  
	 		    	absPath = docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\";//绝对路径
	 		        newFile = new File(absPath + newFileName);
	 		        if(!newFile.exists()){
	 		        	 System.out.println("执行到这里----------9-----------");
	 		        	//路径判断
	 					File dir = new File(absPath);
	 					dir.mkdirs();
	 				}
	 		     // 将内存中的数据写入磁盘
	 		        try {
	 					mulFile.transferTo(newFile);
	 				} catch (IllegalStateException | IOException e) {
	 					e.printStackTrace();
	 				}
	 		        virPath+=dirDName+"/"+dirYear+"/"+dirCName+"/"+dirSName+"/"+newFileName;
	 		        document.setLocation(virPath);
	 		        //设置上传标记
	 		        //studentService.deleteDocByDocId(docId);
	 		        isUploadSuccess = stuService.sUpload(document);   
	 		    }  
	    		 if (isUploadSuccess) {// 当保存成功时
	    			 System.out.println("执行到这里----------10-----------");
	    			 
		 				out.println("<script>alert('文件上传成功!');history.back();</script>");
		 				//str="{\"info\":\"提交信息成功!\"}";
		 			}else {
		 				 System.out.println("执行到这里----------11-----------");
		 				out.println("<script>alert('文件上传失败!');history.back();</script>");
		 			}
	    	 } 
	     }//存在文档
	     else if((stuService.queryDocByDocId(stuInfo.getUser_id()+docType))!=null){
	    	 System.out.println("执行到这里----------12-----------");
	    	 //文件已存在，但状态为0,1,3，5时，可继续上传
	    	 String stateFlag =stuService.queryDocByDocId(stuInfo.getUser_id()+docType).getStateId();
	    	 if(stateFlag.equals("0")||stateFlag.equals("1")||stateFlag.equals("3")||stateFlag.equals("5")) {
	    		 System.out.println("执行到这里----------13-----------");
	    		 //判断是否存在意见，不存在，直接上传
	    		 if(stuService.queryDocByDocId(docAdviceId)==null) {
	    			 System.out.println("执行到这里----------14-----------");
	    			//判断表单信息，文件类型和表单信息不为空，才能正常上传
		    		 if (mulFile != null && originalFileName != null && originalFileName.length() > 0) {
		    			 System.out.println("执行到这里----------15-----------");
		    			 //开始上传，接收参数，文件
		 		    	newFileName = stuInfo.getUser_id()+docType+ originalFileName.substring(originalFileName.lastIndexOf("."));//新文件名  
		 		    	absPath = docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\";//绝对路径
		 		        newFile = new File(absPath + newFileName);
		 		        if(!newFile.exists()){
		 		        	 System.out.println("执行到这里----------16-----------");
		 		        	//路径判断
		 					File dir = new File(absPath);
		 					dir.mkdirs();
		 				}
		 		     // 将内存中的数据写入磁盘
		 		        try {
		 		        	FileOperate.delFile(absPath+newFileName);
		 		        	System.out.println("覆盖上传前，删除旧文件");
		 					mulFile.transferTo(newFile);
		 				} catch (IllegalStateException | IOException e) {
		 					e.printStackTrace();
		 				}
		 		        virPath+=dirDName+"/"+dirYear+"/"+dirCName+"/"+dirSName+"/"+newFileName;
		 		        document.setLocation(virPath);
		 		        //设置上传标记
		 		        stuService.deleteDocByDocId(docId);
		 		        System.out.println("0,1,3,5状态，覆盖上传，已删除数据库中文档记录");
		 		        isUploadSuccess = stuService.sUpload(document);   
		 		    }  
		    		 if (isUploadSuccess) {// 当保存成功时
		    			 System.out.println("执行到这里----------17-----------");
			 				out.println("<script>alert('文件上传成功!');history.back();</script>");
			 				//str="{\"info\":\"提交信息成功!\"}";
			 			}else {
			 				 System.out.println("执行到这里----------18-----------");
			 				out.println("<script>alert('文件上传失败!');history.back();</script>");
			 			}
	    		 }else if(stuService.queryDocByDocId(docAdviceId)!=null){
	    			 System.out.println("执行到这里----------19-----------");
	    			 //存在意见，删除意见文件和数据库记录，继续上传
	    			 
	    			//删除意见文档和数据库记录，上传文档
		    		 newFileName = stuInfo.getUser_id()+docType+ originalFileName.substring(originalFileName.lastIndexOf("."));//新文件名  
		 		     absPath = docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\";//绝对路径
		 		     
		    		 stuService.deleteDocByDocId(docAdviceId);
		    		 System.out.println("数据库:文档意见记录已删除2");
		    		 
		    		 FileOperate.delFile(absPath+newFileName);
		    		 System.out.println("磁盘:文档意见记录已删除2");
		    		 
		    		//判断表单信息，文件类型和表单信息不为空，才能正常上传
		    		 if (mulFile != null && originalFileName != null && originalFileName.length() > 0) {
		    			 System.out.println("执行到这里----------20-----------");
		    			 //开始上传，接收参数，文件
		 		    	newFileName = stuInfo.getUser_id()+docType+ originalFileName.substring(originalFileName.lastIndexOf("."));//新文件名  
		 		    	absPath = docPath +dirDName+"\\"+dirYear+"\\"+dirCName+"\\"+dirSName+"\\";//绝对路径
		 		        newFile = new File(absPath + newFileName);
		 		        if(!newFile.exists()){
		 		        	 System.out.println("执行到这里----------21-----------");
		 		        	//路径判断
		 					File dir = new File(absPath);
		 					dir.mkdirs();
		 				}
		 		     // 将内存中的数据写入磁盘
		 		        try {
		 					mulFile.transferTo(newFile);
		 				} catch (IllegalStateException | IOException e) {
		 					e.printStackTrace();
		 				}
		 		        virPath+=dirDName+"/"+dirYear+"/"+dirCName+"/"+dirSName+"/"+newFileName;
		 		        document.setLocation(virPath);
		 		        //设置上传标记
		 		       stuService.deleteDocByDocId(docId);
		 		        System.out.println("0,1,3,5状态，覆盖上传，已删除数据库中文档记录");
		 		        isUploadSuccess = stuService.sUpload(document);   
		 		    }  
		    		 if (isUploadSuccess) {// 当保存成功时
		    			 System.out.println("执行到这里----------22-----------");
			 				out.println("<script>alert('文件上传成功!');history.back();</script>");
			 				//str="{\"info\":\"提交信息成功!\"}";
			 			}else {
			 				 System.out.println("执行到这里----------23-----------");
			 				out.println("<script>alert('文件上传失败!');history.back();</script>");
			 			}
	    		 }
	    	 }else {
	    		 System.out.println("执行到这里----------24-----------");
	    		 //存在id，状态为2,4，上传失败
		    	 out.println("<script>alert('文件审核中,请勿重复上传！');history.back();</script>");
	    	 }
	    	
	     }
	    
	     System.out.println("数据库表虚拟路径virPath:"+virPath);
	     System.out.println("文件磁盘绝对路径newFile:"+newFile);  
	     toPdf.OtherToPdf(newFile, new File(absPath+stuInfo.getUser_id()+docType+".pdf"));
	}
	/**
	 * 教师查询待审批学生文档
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/tQueryChkDoc")
	@ResponseBody
	public Object  tQueryChkDoc(HttpSession session,HttpServletRequest request) {
		User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		User user = new User();
		user.setUser_id(stuInfo.getUser_id());
		List<Document> li=adminService.tQueryChkDoc(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//新建存储本页数据的list
		List<Document> list = new ArrayList<Document>();
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
	 * 教师审批学生文档
	 */
	@RequestMapping(value="/tChk")
	//@ResponseBody
	public void tChk(HttpServletRequest request,HttpServletResponse response,Document document) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		//log.info("document"+document.toString());
		document.setDocId(document.getStu_id()+document.getTypeId());
		boolean flag=adminService.tChk(document);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	/**
	 * 教秘查询待审批学生文档
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/dQueryChkDoc")
	@ResponseBody
	public Object  dQueryChkDoc(HttpSession session,HttpServletRequest request) {
		User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		User user = new User();
		user.setUser_id(stuInfo.getUser_id());
		List<Document> li=adminService.dQueryChkDoc(user);
		//记录本页页码
		int page = Integer.parseInt(request.getParameter("page"));
		//记录本页大小
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//新建存储本页数据的list
		List<Document> list = new ArrayList<Document>();
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
	 * 教秘审批学生文档
	 */
	@RequestMapping(value="/dDocChk")
	//@ResponseBody
	public void dChk(HttpServletRequest request,HttpServletResponse response,Document document) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		//log.info("document"+document.toString());
		document.setDocId(document.getStu_id()+document.getTypeId());
		boolean flag=adminService.dDocChk(document);
		if(flag){
			out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
		}else{
			out.println("<script>alert('编辑失败！请重新操作！');history.back();</script>");
		}
	}
	//编辑新闻
		@RequestMapping(value = "/addNews")
		@ResponseBody
		public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session,Notice news) {
			log.info("========"+news.toString());
			boolean flag = adminService.addNews(news);
			if (flag) {
				return "{\"flag\":true}";
			} else {
				return "{\"flag\":false}";
			}

		}
		 /**
		 * 教师查看学生答辩分组
		 * @param session
		 * @return
		 */
		@RequestMapping(value="/querySSpeechGroup")
		@ResponseBody
		public Object  querySSpeechGroup(HttpSession session,HttpServletRequest request) {
			User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
			User user = new User();
			user.setUser_id(stuInfo.getUser_id());
			List<Speech_group> li=adminService.sQuerySpeechGroup(user);
			//记录本页页码
			int page = Integer.parseInt(request.getParameter("page"));
			//记录本页大小
			int rows = Integer.parseInt(request.getParameter("rows"));
			
			//新建存储本页数据的list
			List<Speech_group> list = new ArrayList<Speech_group>();
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
		 * 教秘、院长查看学生答辩分组
		 * @param session
		 * @return
		 */
		@RequestMapping(value="/queryDSpeechGroup")
		@ResponseBody
		public Object  queryDSpeechGroup(HttpSession session,HttpServletRequest request) {
			User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
			User user = new User();
			user.setUser_id(stuInfo.getUser_id());
			List<Speech_group> li=adminService.dQuerySpeechGroup(user);
			//记录本页页码
			int page = Integer.parseInt(request.getParameter("page"));
			//记录本页大小
			int rows = Integer.parseInt(request.getParameter("rows"));
			
			//新建存储本页数据的list
			List<Speech_group> list = new ArrayList<Speech_group>();
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
		 * 编辑DSpeechGroup
		 */
		@RequestMapping(value="/dSpeechGroupEdit")
		//@ResponseBody
		public void dSpeechGroupEdit(HttpServletRequest request,HttpServletResponse response,Speech_group speechGroup) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			};
			log.info("Speech_group"+speechGroup.toString());
			boolean flag=false;
			if(adminService.dSpeechGroupEdit(speechGroup)) {
				flag=true;
			}
			if(flag){
				out.println("<script>alert('编辑成功！请刷新页面！');history.back();</script>");
			}else {
				out.println("<script>alert('编辑失败！请确认信息！');history.back();</script>");

			}
		}
		/**
		 * 增加DSpeechGroup
		 */
		@RequestMapping(value="/dSpeechGroupAdd")
		//@ResponseBody
		public  void dSpeechGroupAdd(HttpServletRequest request,HttpServletResponse response,Speech_group speechGroup) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			};
			log.info("Speech_group:"+speechGroup);
			boolean flag=false;
			if(adminService.dSpeechGroupAdd(speechGroup)) {
				flag=true;
			}
			if(flag){
				out.println("<script>alert('添加成功！请刷新页面！');history.back();</script>");
			}else {
				out.println("<script>alert('添加失败！请确认信息！');history.back();</script>");

			}
		}
		/**
		 * 删除DSpeechGroup
		 */
		@RequestMapping(value="/dSpeechGroupDel")
		//@ResponseBody
		public  void dSpeechGroupDel(HttpServletRequest request,HttpServletResponse response,Speech_group speechGroup) {
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
				if (!adminService.dSpeechGroupDel(str[i])) {
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
		 * 教务处查看学生答辩分组
		 * @param session
		 * @return
		 */
		@RequestMapping(value="/queryAllDSpeechGroup")
		@ResponseBody
		public Object  queryAllDSpeechGroup(HttpSession session,HttpServletRequest request) {
			User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
			User user = new User();
			user.setUser_id(stuInfo.getUser_id());
			List<Speech_group> li=adminService.queryAllDSpeechGroup(user);
			//记录本页页码
			int page = Integer.parseInt(request.getParameter("page"));
			//记录本页大小
			int rows = Integer.parseInt(request.getParameter("rows"));
			
			//新建存储本页数据的list
			List<Speech_group> list = new ArrayList<Speech_group>();
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
		 * 重置密码
		 */
		@RequestMapping(value="/resetPassword")
	/*	@ResponseBody*/
		public void resetPassword(HttpServletRequest request,HttpSession session,HttpServletResponse response,User user) {
			log.info("user_info"+user.toString());
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			};
		
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			User u1 = new User();
			u1.setUser_id(user.getUser_id());
			u1.setPassword(MD5Utils.md5Password(password1));
			boolean flag = false;
			String view = null;

			if(password1.equals(password2)){
				flag=adminService.resetPassword(u1);
			}
			if(flag) {
				out.println("<script>alert('修改成功，请重新登录！');history.back();</script>");
			}else {
				out.println("<script>alert('两次密码输入不一致,请核对后重新提交！');history.back();</script>");
			}
		}
}
