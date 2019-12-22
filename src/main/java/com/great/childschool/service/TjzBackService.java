package com.great.childschool.service;



import com.great.childschool.entity.*;
import com.great.childschool.mapper.TjzBackMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TjzBackService
{
	@Resource
	private TjzBackMapper tjzBackMapper;



	//园长班级分页
	@Transactional
	public TjzTbTable teacherCourseQuery(String page, String limit, String startDate, String endDate, String cName,String wid)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("cName", cName);
		map.put("wid", wid);
		List<TjzTbClassRoom> list = tjzBackMapper.teacherCourseQuery(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.teacherCourseQueryNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}

	//教师查看课程表
	public Map<String, List<TjzTbCourse>>  teacherCourseTable(Map<String, Object> map2 )
	{
		Map<String, List<TjzTbCourse>> map = null;
		List<TjzTbCourse> lis=tjzBackMapper.teacherCourseTable(map2);
		map = new HashMap<>();
		for (int i = 0; i < lis.size(); i++)
		{

			TjzTbCourse tjzTbCourse = lis.get(i);
			if (map.containsKey(tjzTbCourse.getcOrder()))
			{
				List<TjzTbCourse> list = map.get(tjzTbCourse.getcOrder());
				list.add(tjzTbCourse);
			} else
			{
				List<TjzTbCourse> list = new ArrayList<>();
				list.add(tjzTbCourse);
				map.put(tjzTbCourse.getcOrder(), list);
			}
		}
		return map;
	}


	//插入未排班课程
	public int insertCodeBatch(List<TjzTbCourse > courseList){
		return tjzBackMapper.insertCodeBatch(courseList);
	};


	//排课
	public int addSubject(TjzTbCourse course){
		return tjzBackMapper.addSubject(course);
	};

	//查询科目
	@Transactional
	public List<TjzTbSubject> findSubject()
	{
		List<TjzTbSubject> list = tjzBackMapper.findSubject();
		return list;
	}


	//园长班级分页
	@Transactional
	public TjzTbTable courseManagement(String page, String limit, String startDate, String endDate, String cName)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("cName", cName);
		List<TjzTbClassRoom> list = tjzBackMapper.courseManagement(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.courseManagementNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}

	//日志分页
	@Transactional
	public TjzTbTable showLogTable(String page, String limit, String startDate, String endDate, String userName)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userName", userName);
		List<TjzTbLog> list = tjzBackMapper.findLog(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.findLogNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}


	//增加日志
	@Transactional
	public int addLog(TjzTbLog log)
	{
		int flag = tjzBackMapper.addLog(log);
		return flag;

	}


	//园长课程表
	public Map<String, List<TjzTbCourse>>  courseTable(Map<String, Object> map2 )
	{
		Map<String, List<TjzTbCourse>> map = null;
		List<TjzTbCourse> lis=tjzBackMapper.courseTable(map2);
		map = new HashMap<>();
		for (int i = 0; i < lis.size(); i++)
		{

			TjzTbCourse tjzTbCourse = lis.get(i);
			if (map.containsKey(tjzTbCourse.getcOrder()))
			{
				List<TjzTbCourse> list = map.get(tjzTbCourse.getcOrder());
				list.add(tjzTbCourse);
			} else
			{
				List<TjzTbCourse> list = new ArrayList<>();
				list.add(tjzTbCourse);
				map.put(tjzTbCourse.getcOrder(), list);
			}
		}
		return map;
	}


}