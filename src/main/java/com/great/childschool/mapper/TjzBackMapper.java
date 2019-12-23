package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TjzBackMapper
{

	//家长修改密码
	public int parentChangePassword(TjzTblParent parent);

	//家长查询旧密码是否正确
	public TjzTblParent parentOldPassword(int pid);

	//家长查看班级
	public List<TjzTbClassRoom> parentCourseQuery(Map<String, Object> map);

	public int parentCourseQueryNum(Map<String, Object> map);

	public List<TjzTbCourse> parentCourseTable(Map<String, Object> map);

	//教师查看班级
	public List<TjzTbClassRoom> teacherCourseQuery(Map<String, Object> map);

	public int teacherCourseQueryNum(Map<String, Object> map);

	//教师查看排课
	public List<TjzTbCourse> teacherCourseTable(Map<String, Object> map);

	public int addSubject(TjzTbCourse course);

	public List<TjzTbSubject> findSubject();

	//园长查看排课
	public List<TjzTbCourse> courseTable(Map<String, Object> map);

	public List<TjzTbClassRoom> courseManagement(Map<String, Object> map);

	public int courseManagementNum(Map<String, Object> map);

	public List<TjzTbLog> findLog(Map<String, Object> map);

	public int findLogNum(Map<String, Object> map);

	public int addLog(TjzTbLog log);

	public int insertCodeBatch(List<TjzTbCourse > courseList);



}
