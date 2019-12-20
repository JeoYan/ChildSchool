package com.great.childschool.mapper;


import com.great.childschool.entity.TjzTbCourse;
import com.great.childschool.entity.TjzTbLog;
import com.great.childschool.entity.TjzTbClassRoom;
import com.great.childschool.entity.TjzTbSubject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TjzBackMapper
{


	/**树置为未选*/
	public int addSubject(TjzTbCourse course);

	public List<TjzTbSubject> findSubject();

	public List<TjzTbCourse> courseTable(Map<String, Object> map);

	public List<TjzTbClassRoom> courseManagement(Map<String, Object> map);

	public int courseManagementNum(Map<String, Object> map);

	public List<TjzTbLog> findLog(Map<String, Object> map);

	public int findLogNum(Map<String, Object> map);

	public int addLog(TjzTbLog log);

	public int insertCodeBatch(List<TjzTbCourse > courseList);



}
