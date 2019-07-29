package bysj.zjl.mapper;

import java.util.List;

import bysj.zjl.entity.Document;
import bysj.zjl.entity.Score;
import bysj.zjl.entity.Speech_group;
import bysj.zjl.entity.Topic;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info_vo;

public interface StuMapper {
	/**
	 * 查询指导教师基本信息
	 */
	public User_info_vo queryTInfo(User user);
	/**
	 * 查询小组信息
	 */
	public List<User_info_vo> querySGroupInfo(User user);
	/**
	 * 查询答辩小组信息
	 */
	public List<User_info_vo> querySG(User user);
	/**
	 * 教师查询小组信息
	 */
	public List<User_info_vo> tQuerySGroupInfo(User user);
	/**
	 * 学生查询D课题
	 */
	public List<Topic> queryDTopic(User_info_vo userInfoVo);
	/**
	 * 学生申请课题
	 */
	public boolean sApplyTopic1(Topic topic);
	/**
	 * 学生申请自拟课题
	 */
	public boolean sApplyTopic2(Topic topic); 
	/**
	 * 学生申请课题前验证
	 */
	public Topic sApplyChk(Topic topic);
	/**
	 * 课题统计
	 */
	public int sApplyCount(Topic topic);
	/**
	 * 学生撤销申请课题
	 */
	public boolean sApplyCancel(Topic topic);
	/**
	 * 学生查询课题申请结果
	 */
	public List<Topic> queryTopicResult(User_info_vo userInfoVo);
	/**
	 * 根据教师id查询教师信息
	 */
	public User_info_vo queryTInfoByTId(String user_id); 
	/**
	 * 学生申请课题成功后，stu_group 绑定
	 * @param topic
	 */
	public boolean bindStuGroup(Topic topic); 
	/**
	 * 学生撤销课题成功后，stu_group 绑定接触
	 * @param topic
	 */
	public boolean stuGroupDel(Topic topic); 
	/**
	 * 学生答辩申请
	 * @param topic
	 * @return
	 */
	public boolean sApply(Topic topic);
	/**
	 * 学生成绩查询
	 * @param user
	 * @return
	 */
	public List<Score> querySScore(User user);
	/**
	 * 学生文件上传
	 */
	public boolean sUpload(Document document);
	/**
	 * 根据docId查询指定文档
	 */
	public Document queryDocByDocId(String docId);
	/**
	 * 根据docId删除文档
	 */
	public boolean deleteDocByDocId(String docId);
	/**
	 * 学生查看文档
	 * @param user
	 * @return
	 */
	public List<Document> queryAllDoc(User user);
	/**
	 * 学生查看答辩组信息
	 */
	public List<Speech_group> sQuerySpeechGroup(User user);
}
