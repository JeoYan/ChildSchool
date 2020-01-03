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
	 * 按月统计日志
	 * by 汤建志
	 */
	public List<TjzLogCount> logCountByMonth( );

	/**
	 * 按人员统计日志
	 * by 汤建志
	 */
	public List<TjzLogCount> logCountByWid( );


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
	public int insertCodeBatch(List<TjzTbCourse > courseList);



}
