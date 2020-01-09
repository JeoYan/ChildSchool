package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 访问数据库接口
 *     by 汤建志
 */

@Mapper
public interface TjzBackMapper
{

	/**
	 * 充值
	 * by 汤建志
	 */
	public int addMoney(TjzTblBaby baby);


	/**
	 * 查询余额
	 * by 汤建志
	 */
	public TjzTblBaby findMoney(int pid);



	/**
	 * 查询人脸
	 * by 汤建志
	 */
	public TjzTblWorker findFace(int wid);


	/**
	 * 添加人脸
	 * by 汤建志
	 */
	public int  addface(TjzTblWorker worker);



	/**
	 * 解除绑定卡
	 * by 汤建志
	 */
	public int unbindCard(String cardNum);


	/**
	 * 绑定卡
	 * by 汤建志
	 */
	public int bindCard(TjzTblCard card);


	/**
	 * 查询未绑定卡的宝宝
	 * by 汤建志
	 */
	public List<TjzTblCard> finNotCard( );

	/**
	 * 删除卡
	 * by 汤建志
	 */
	public int delCard(String cardNum);

	/**
	 * 批量插入卡片
	 * by 汤建志
	 */
	public int addCards(List<TjzTblCard> cardList);



	/**
	 * 已存在最大卡号
	 * by 汤建志
	 */
	public String findMaxCard( );


	/**
	 * 卡片管理
	 * by 汤建志
	 */
	public List<TjzTblCard> findCard(Map<String, Object> map);
	/**
	 * 卡片管理数量
	 * by 汤建志
	 */
	public int findCardNum(Map<String, Object> map);


	/**
	 * 考试记录分数赋值
	 * by 汤建志
	 */
	public int addTotalScore(TjzTblSafeStudy safeStudy);


	/**
	 * 查看得分
	 * by 汤建志
	 */
	public List<TjzTblquestion> queryScore(Map<String, Object> map);

	/**
	 * 添加考试记录
	 * by 汤建志
	 */
	public int addTestRecord(TjzTblSafeStudy safeStudy);

	/**
	 * 验证试题答案
	 * by 汤建志
	 */
	public TjzTblquestion checkAnswer(Map<String, Object> map);


	/**
	 * 修改安全教育考试题目
	 * by 汤建志
	 */
	public int updateQuestion(TjzTblquestion tblquestion);
	/**
	 * 删除安全教育考试题目
	 * by 汤建志
	 */
	public int deleteQuestion(int questionId);


	/**
	 * 添加安全教育试题
	 * by 汤建志
	 */
	public int addSafeStudyTest(TjzTblquestion tblquestion);

	/**
	 * 安全教育试题页面
	 * by 汤建志
	 */
	public List<TjzTblquestion> addSafeStudyTestView(int safeId);


	/**
	 * 教师修改安全教育视频
	 * by 汤建志
	 */
	public int updateVideo(TjzTblSafeStudy safeStudy);

	/**
	 * 删除安全教育视频
	 * by 汤建志
	 */
	public int delSafeStudyVideo(int safeId);

	/**
	 * 电子围栏查询报警日志
	 * by 汤建志
	 */
	public  List<TjzTblWarning> findWarning(Map<String, Object> map);
	/**
	 * 电子围栏查询报警日志数量
	 * by 汤建志
	 */
	public int findWarningNum(Map<String, Object> map);



	/**
	 * 电子围栏查询孩子
	 * by 汤建志
	 */
	public TjzTblBaby fenceBaby(int bid);


	/**
	 * 添加报警信息
	 * by 汤建志
	 */
	public int addWarning(TjzTblWarning warning);


	/**
	 * 教师查看班级安全教育
	 * by 汤建志
	 */
	public  List<TjzTblBaby> classSafeStudy(Map<String, Object> map);
	/**
	 * 教师查看班级安全教育数目
	 * by 汤建志
	 */
	public int classSafeStudyNum(Map<String, Object> map);
	/**
	 * 家长查看安全教育数目
	 * by 汤建志
	 */
	public int parentSafeStudyNum(Map<String, Object> map);


	/**
	 * 家长查看安全教育
	 * by 汤建志
	 */
	public List<TjzTblSafeStudy> parentSafeStudy(Map<String, Object> map);

	/**
	 * 教师上传安全教育试题
	 * by 汤建志
	 */
	public int uploadTest(TjzTblSafeStudy safeStudy);


	/**
	 * 教师上传安全教育视频
	 * by 汤建志
	 */
	public int uploadVideo(TjzTblSafeStudy safeStudy);

	/**
	 * 安全教育管理
	 * by 汤建志
	 */
	public List<TjzTblSafeStudy> safeStudyManagement(Map<String, Object> map);

	/**
	 * 安全教育管理数量
	 * by 汤建志
	 */
	public int safeStudyManagementNum(Map<String, Object> map);




	/**
	 * 按月统计日志
	 * by 汤建志
	 */
	public List<TjzLogCount> logCountByMonth();

	/**
	 * 按人员统计日志
	 * by 汤建志
	 */
	public List<TjzLogCount> logCountByWid();


	/**
	 * 家长修改密码
	 * by 汤建志
	 */
	public int parentChangePassword(TjzTblParent parent);

	/**
	 * 家长查询旧密码是否正确
	 * by 汤建志
	 */
	public TjzTblParent parentOldPassword(int pid);

	/**
	 * 家长查看孩子班级
	 * by 汤建志
	 */
	public List<TjzTbClassRoom> parentCourseQuery(Map<String, Object> map);

	/**
	 * 家长查看孩子班级数目
	 * by 汤建志
	 */
	public int parentCourseQueryNum(Map<String, Object> map);

	/**
	 * 家长查询课程表
	 * by 汤建志
	 */
	public List<TjzTbCourse> parentCourseTable(Map<String, Object> map);

	/**
	 * 教师查看班级
	 * by 汤建志
	 */
	public List<TjzTbClassRoom> teacherCourseQuery(Map<String, Object> map);

	/**
	 * 教师查看班级数目
	 * by 汤建志
	 */
	public int teacherCourseQueryNum(Map<String, Object> map);


	/**
	 * 教师查看课程表
	 * by 汤建志
	 */
	public List<TjzTbCourse> teacherCourseTable(Map<String, Object> map);


	/**
	 * 园长排课
	 * by 汤建志
	 */
	public int addSubject(TjzTbCourse course);


	/**
	 * 科目下拉框
	 * by 汤建志
	 */
	public List<TjzTbSubject> findSubject();

	/**
	 * 园长查看排课
	 * by 汤建志
	 */
	public List<TjzTbCourse> courseTable(Map<String, Object> map);


	/**
	 * 园长查看班级
	 * by 汤建志
	 */
	public List<TjzTbClassRoom> courseManagement(Map<String, Object> map);


	/**
	 * 园长查看班级数目
	 * by 汤建志
	 */
	public int courseManagementNum(Map<String, Object> map);


	/**
	 * 查看日志
	 * by 汤建志
	 */
	public List<TjzTbLog> findLog(Map<String, Object> map);


	/**
	 * 查看日志数量数目
	 * by 汤建志
	 */
	public int findLogNum(Map<String, Object> map);


	/**
	 * 添加日志
	 * by 汤建志
	 */
	public int addLog(TjzTbLog log);


	/**
	 * 批量插入未排班课程
	 * by 汤建志
	 */
	public int insertCodeBatch(List<TjzTbCourse> courseList);



}
