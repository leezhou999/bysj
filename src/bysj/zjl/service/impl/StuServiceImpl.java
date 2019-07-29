package bysj.zjl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bysj.zjl.entity.Document;
import bysj.zjl.entity.Score;
import bysj.zjl.entity.Speech_group;
import bysj.zjl.entity.Topic;
import bysj.zjl.entity.User;
import bysj.zjl.entity.User_info;
import bysj.zjl.entity.User_info_vo;
import bysj.zjl.mapper.StuMapper;
import bysj.zjl.service.IStuService;
@Service
public class StuServiceImpl implements IStuService {

	Logger log =LoggerFactory.getLogger(getClass());
	private StuMapper stuMapper;
	@Autowired
	public void setStuMapper(StuMapper stuMapper) {
		this.stuMapper = stuMapper;
	}
	/**
	 * 查看小组信息
	 */
	@Override
	public List<User_info_vo> querySGroupInfo(User user) {
		return stuMapper.querySGroupInfo(user);
	}
	/**
	 * 查询答辩小组信息
	 */
	@Override
	public List<User_info_vo> querySG(User user) {
		return stuMapper.querySG(user);
	}
	
	/**
	 * 查看指导教师信息
	 */
	@Override
	public User_info_vo queryTInfo(User user) {
		return stuMapper.queryTInfo(user);
	}
	
	/**
	 * 教师查看小组信息
	 */
	@Override
	public List<User_info_vo> tQuerySGroupInfo(User user) {
		return stuMapper.tQuerySGroupInfo(user);
	}
	/**
	 * 学生查询D课题
	 */
	@Override
	public List<Topic> queryDTopic(User_info_vo userInfoVo) {
		return stuMapper.queryDTopic(userInfoVo);
	}
	/**
	 * 学生申请课题
	 */
	@Override
	public boolean sApplyTopic1(Topic topic) {
		return stuMapper.sApplyTopic1(topic);
	}
	/**
	 * 学生申请自拟课题
	 */
	@Override
	public boolean sApplyTopic2(Topic topic) {
		return stuMapper.sApplyTopic2(topic);
	}
	/**
	 * 学生申请课题前验证
	 */
	@Override
	public Topic sApplyChk(Topic topic) {
		return stuMapper.sApplyChk(topic);
	}
	/**
	 * 统计
	 */
	@Override
	public int sApplyCount(Topic topic) {
		return stuMapper.sApplyCount(topic);
	}
	/**
	 * 学生撤销申请课题
	 */
	@Override
	public boolean sApplyCancel(Topic topic) {
		return stuMapper.sApplyCancel(topic);
	}
	/**
	 * 学生查询课题申请结果
	 */
	@Override
	public List<Topic> queryTopicResult(User_info_vo userInfoVo) {
		return stuMapper.queryTopicResult(userInfoVo);
	}
	/**
	 * 根据教师id查询教师信息
	 */
	@Override
	public User_info_vo queryTInfoByTId(String user_id) {
		return stuMapper.queryTInfoByTId(user_id);
	}
	/**
	 * 学生申请课题成功后，stu_group 绑定
	 * @param topic
	 */
	@Override
	public boolean bindStuGroup(Topic topic) {
		return stuMapper.bindStuGroup(topic);
	}
	/**
	 * 学生撤销课题成功后，stu_group 解除绑定
	 * @param topic
	 */
	@Override
	public boolean stuGroupDel(Topic topic) {
		return stuMapper.stuGroupDel(topic);
	}
	/**
	 * 学生答辩申请
	 * @param topic
	 * @return
	 */
	@Override
	public boolean sApply(Topic topic) {
		return stuMapper.sApply(topic);
	}
	/**
	 * 学生成绩查询
	 */
	@Override
	public List<Score> querySScore(User user) {
		return stuMapper.querySScore(user);
	}
	/**
	 * 学生文件上传
	 */
	@Override
	public boolean sUpload(Document document) {
		return stuMapper.sUpload(document);
	}
	/**
	 * 根据docId查询指定文档
	 */
	@Override
	public Document queryDocByDocId(String docId) {
		return stuMapper.queryDocByDocId(docId);
	}
	/**
	 * 根据docId删除文档
	 */
	@Override
	public boolean deleteDocByDocId(String docId) {
		return stuMapper.deleteDocByDocId(docId);
	}
	/**
	 * 学生查看文档
	 * @param user
	 * @return
	 */
	@Override
	public List<Document> queryAllDoc(User user) {
		return stuMapper.queryAllDoc(user);
	}
	/**
	 * 学生查看答辩分组
	 */
	@Override
	public List<Speech_group> sQuerySpeechGroup(User user) {
		return stuMapper.sQuerySpeechGroup(user);
	}
	
}
