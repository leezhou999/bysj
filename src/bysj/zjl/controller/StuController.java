package bysj.zjl.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import bysj.zjl.entity.Dept;
import bysj.zjl.entity.Document;
import bysj.zjl.entity.Major;
import bysj.zjl.entity.Score;
import bysj.zjl.entity.Speech_group;
import bysj.zjl.entity.Topic;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info_vo;
import bysj.zjl.service.IAdminService;
import bysj.zjl.service.IStuService;
import bysj.zjl.service.IUserService;
import bysj.zjl.util.FileOperate;
import bysj.zjl.util.ToPdf;

/**
 * 学生controller
 * @author zhoujiali
 *
 */
@Controller
@RequestMapping(value="/Stu")
public class StuController {

	private Logger log = LoggerFactory.getLogger(getClass());
	private IStuService stuService;

	@Autowired
	public void setStuService(IStuService stuService) {
		this.stuService = stuService;
	}
	
	private IUserService userService;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private IAdminService adminServie;
	@Autowired
	public void setAdminServie(IAdminService adminServie) {
		this.adminServie = adminServie;
	}
	/**
	 * 学生查询指导教师信息
	 */
	@RequestMapping(value="/queryTInfo")
	@ResponseBody
	public Object queryTInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		User_info_vo  teaInfo=stuService.queryTInfo(user);
		List<User_info_vo> li = new ArrayList<User_info_vo>();
		li.add(teaInfo);
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
	 * 学生查询本组信息
	 */
	@RequestMapping(value="/querySGroupInfo")
	@ResponseBody
	public Object querySGroupInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=stuService.querySGroupInfo(user);
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
	 * 查询答辩小组信息
	 * 学生查询本组信息
	 */
	@RequestMapping(value="/querySG")
	@ResponseBody
	public Object querySG(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		
		User_info_vo userInfoVo = new User_info_vo();
		userInfoVo.setUser_id(user.getUser_id());
		
		List<Topic> topicList =stuService.queryTopicResult(userInfoVo);//设置topicInfo
		Topic topic =new Topic();
		if(topicList.size()>0) {
			topic  = topicList.get(0);
		}
		//log.info("topic---"+topic.toString());
		session.setAttribute("topicInfo", topic);
		session.setAttribute("stu_id",topic.getStu_id());//设置学生id
		
		
		List<User_info_vo> li=stuService.querySG(user);
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
	 * 教师查询小组信息
	 * 
	 */
	@RequestMapping(value="/tQuerySGroupInfo")
	@ResponseBody
	public Object tQuerySGroupInfo(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<User_info_vo> li=stuService.tQuerySGroupInfo(user);
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
	 * 学生查询D课题
	 */
	@RequestMapping(value="/queryDTopic")
	@ResponseBody
	public Object queryDTopic(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		String findstr = request.getParameter("findstr");
		
		int maxTopicId = userService.maxTopicId();
		maxTopicId +=1;
		session.setAttribute("maxTopicId", maxTopicId);
		
		userInfoVo.setFindstr(findstr);
		userInfoVo.setUser_id(user.getUser_id());
		List<Topic> li=stuService.queryDTopic(userInfoVo);
		
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
	 * 学生申请课题,并直接绑定stu_group
	 */
	@RequestMapping(value="/sApplyTopic1")
	@ResponseBody
	public  Object sApplyTopic1(HttpServletRequest request,HttpServletResponse response,HttpSession session,Topic topic) {
		User user = (User)session.getAttribute("userLoginInfo");
		
		
		String topicId = request.getParameter("topicId");
		String tea_id = request.getParameter("tea_id");//从前台获取tea_id
		
		User_info_vo tea= stuService.queryTInfoByTId(tea_id);
		
		//topic.setUser_id(user.getUser_id());
		topic.setStu_id(user.getUser_id());//当前学生用户id设置为学生id
		topic.setStu_name(user.getUser_name());//当前学生用户name设置为学生name
		topic.setTea_id(tea_id);//前台传递tea_id
		topic.setTea_name(tea.getUser_name());
		
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
				
		log.info("topic======"+topic.toString());
		//申请课题前判断是否已经申请过
		if(stuService.sApplyCount(topic)==0) {
			if(stuService.sApplyTopic1(topic)&&stuService.bindStuGroup(topic)) {//记录数判断
				//学生提交申请后直接绑定stu_group关系，学生撤销后，删除绑定
				ov.setInfo("选题成功！师生关系已绑定！");
			}else {
				ov.setInfo("选题失败");
			}
		}else {
			ov.setInfo("选题失败,当前用户已选择过课题！");
		}
		return ov;
	}
	/**
	 * 学生查询申请结果
	 */
	@RequestMapping(value="/queryTopicResult")
	@ResponseBody
	public Object queryTopicResult(HttpServletRequest request,HttpSession session,User_info_vo userInfoVo) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		String findstr = request.getParameter("findstr");
		userInfoVo.setFindstr(findstr);
		userInfoVo.setUser_id(user.getUser_id());
		List<Topic> li=stuService.queryTopicResult(userInfoVo);
		
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
	 * 学生撤销申请课题,取消绑定stu_group
	 */
	@RequestMapping(value="/sApplyCancel")
	@ResponseBody
	public  Object sApplyCancel(HttpServletRequest request,HttpServletResponse response,HttpSession session,Topic topic) {
		String topicId = request.getParameter("topicId");
		User user = (User)session.getAttribute("userLoginInfo");
		topic.setUser_id(user.getUser_id());
		topic.setTopicId(Integer.parseInt(topicId));
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
		
		if(stuService.sApplyChk(topic).getStu_id()!="") {//若存在学生记录,进入撤销步骤
			if(stuService.sApplyChk(topic).getTypeId().equals("21")) {//存在学生记录，且课题为自拟课题时，撤销需同时删除课题
				if(adminServie.topicDel(topic)&&stuService.stuGroupDel(topic)) {//记录数判断
					ov.setInfo("撤销成功！师生关系已解除！");
				}else {
					ov.setInfo("撤销失败");
				}
			}else if(stuService.sApplyChk(topic).getTypeId().equals("22")) {
				if(stuService.sApplyCancel(topic)&&stuService.stuGroupDel(topic)) {//记录数判断
					ov.setInfo("撤销成功！师生关系已解除！");
				}else {
					ov.setInfo("撤销失败");
				}
			}
			
		}else if(stuService.sApplyChk(topic)==null){
			ov.setInfo("撤销失败,当前用户不存在申请记录！");
		}
		return ov;
	}
	/**
	 * 学生申请自拟课题,并直接绑定stu_group
	 */
	@RequestMapping(value="/sApplyTopic2")
	/*@ResponseBody*/
	public  void sApplyTopic2(HttpServletRequest request,HttpServletResponse response,HttpSession session,Topic topic) {
		User user = (User)session.getAttribute("userLoginInfo");
		topic.setUser_id(user.getUser_id());
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};
		log.info("topic==="+topic.toString());
		
		Dept dept = new Dept();
		dept.setdName(topic.getdName());
		String deptId =userService.queryDeptInfoByParam(dept).getDeptId();
		
		Major major = new Major();
		major.setMajorName(topic.getMajorName());
		String majorId = userService.queryMajorInfoByParam(major).getMajorId();
		
		topic.setStu_name(topic.getUser_name());
		topic.setDeptId(deptId);
		topic.setMajorId(majorId);
		
		String info;
		if(stuService.sApplyCount(topic)==0) {//记录数判断,topic 存在学生记录则不能申请
			if(stuService.sApplyTopic2(topic)&&stuService.bindStuGroup(topic)) {
				//学生提交申请后直接绑定stu_group关系，学生撤销后，删除绑定
				info ="<script>alert('申请成功！请刷新页面！');history.back();</script>";
			}else {
				info ="<script>alert('申请失败！请刷新页面！');history.back();</script>";
			}
		}else {
			info ="<script>alert('申请失败！当前用户已申请课题！');history.back();</script>";
		}
		out.println(info);
	}
	/**
	 * 学生答辩申请
	 */
	@RequestMapping(value="/sApply")
	/*@ResponseBody*/
	public  void sApply(HttpServletRequest request,HttpServletResponse response,HttpSession session,Topic topic) {
		User user = (User)session.getAttribute("userLoginInfo");
		
		log.info("topic:----"+topic.toString());
	
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		};	
		String info;
			if(stuService.sApply(topic)) {//记录数判断
				info ="<script>alert('申请成功！请刷新页面！');history.back();</script>";
			}else {
				info ="<script>alert('申请失败！请重新申请！');history.back();</script>";
			}
		
			out.println(info);
	}
	/**
	 * 学生查询成绩
	 */
	@RequestMapping(value="/querySScore")
	@ResponseBody
	public Object querySScore(HttpServletRequest request,HttpSession session) {
		
		User user = (User)session.getAttribute("userLoginInfo");
		List<Score> li=stuService.querySScore(user);
		
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
	 * 学生文件上传
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/sUpload",method = RequestMethod.POST)
	
	public void sUpload(@RequestParam(value="location",required=false) MultipartFile mulFile,HttpServletRequest request,HttpServletResponse response,HttpSession session) {

		 ToPdf toPdf = new ToPdf();
		 Document document=new Document();//文档实体
		 User user = new User();
		 
		 User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		 user.setUser_id(stuInfo.getUser_id());
		 System.out.println("stuLoginSESSION============："+stuInfo);
		 System.out.println("character:================="+request.getCharacterEncoding());
		 
		 //log.info("前台传递doc对象:"+doc.toString());
		 //TeacherInfo tInfo = studentService.queryTeacherInfo(stuLogin);//查询教师信息
		 
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
	 * 学生查看文档
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryAllDoc")
	@ResponseBody
	public Object  queryAllDoc(HttpSession session,HttpServletRequest request) {
		User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		User user = new User();
		user.setUser_id(stuInfo.getUser_id());
		List<Document> li=stuService.queryAllDoc(user);
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
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/download")  
    public void download(HttpServletRequest request,HttpServletResponse response,Document document) throws Exception{  
        //模拟文件,myfile.txt为需要下载的文件  
    	//studentService.queryAllDoc(stuLogin)
        /*String location = request.getSession().getServletContext().getRealPath("doc"); */
        //System.out.println("Location"+location);
        //获取输入流  
    	String location=document.getLocation().substring(4).replaceAll("/","\\\\\\\\");
    	//System.out.println("location:"+location);
    	String fileName = "D:\\bysjFile\\"+location;
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        //String filename = "下载文件.txt";  
        //转码，免得文件名中文乱码  
       // filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
        bis.close();
    }  
    /**
	 * 学生查看答辩分组
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/querySSpeechGroup")
	@ResponseBody
	public Object  querySSpeechGroup(HttpSession session,HttpServletRequest request) {
		User_info_vo stuInfo = (User_info_vo) session.getAttribute("userInfo");//获取session，取得stuId，deptId，classId
		User user = new User();
		user.setUser_id(stuInfo.getUser_id());
		List<Speech_group> li=stuService.sQuerySpeechGroup(user);
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
}
