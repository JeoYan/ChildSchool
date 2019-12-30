package com.great.childschool.service;



import com.great.childschool.entity.*;
import com.great.childschool.mapper.TjzBackMapper;
import com.great.childschool.tools.Tool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 业务层
 * by 汤建志
 */


@Service
public class TjzBackService
{
	@Resource
	private TjzBackMapper tjzBackMapper;


	/**
	 * 教师查看班级安全教育
	 * by 汤建志
	 */
	public TjzTbTable classSafeStudy(Map<String, Object> map){
		TjzTbTable tbBean = new TjzTbTable();
		List<TjzTblBaby> list = tjzBackMapper.classSafeStudy(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.classSafeStudyNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	};


	/**
	 * 家长查看安全教育
	 * by 汤建志
	 */
	public TjzTbTable parentSafeStudy(String page, String limit, String startDate, String endDate, String safeName,String pid){
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("safeName", safeName);
		map.put("pid", pid);
		List<TjzTblSafeStudy> list = tjzBackMapper.parentSafeStudy(map);
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i <list.size() ; i++)
		{

			try
			{
				Date date2 = sdf.parse(list.get(i).getEndDate());
				if(date.getTime()<date2.getTime()){

					if (null==list.get(i).getScore()||list.get(i).getScore().equals("")){
						list.get(i).setState("未完成");
					}else {
						list.get(i).setState("已经完成");
					}
				}else{
					list.get(i).setState("已过期");
				}
			} catch (ParseException e)
			{
				e.printStackTrace();
			}

		}
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.parentSafeStudyNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	};


	/**
	 * 教师上传安全教育试题
	 * by 汤建志
	 */
	@Transactional
	public int uploadTest(TjzTblSafeStudy safeStudy)
	{
		return tjzBackMapper.uploadTest(safeStudy);
	}



	/**
	 * 教师上传安全教育视频
	 * by 汤建志
	 */
	@Transactional
	public int upload(TjzTblSafeStudy safeStudy)
	{
		return tjzBackMapper.upload(safeStudy);
	}



	/**
	 * 教师发布安全教育试题
	 * by 汤建志
	 */
	public TjzTbTable uploadsafestudy(String page, String limit, String startDate, String endDate, String safeName,String wid){
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("safeName", safeName);
		map.put("wid", wid);
		List<TjzTblSafeStudy> list = tjzBackMapper.safeStudy(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.safeStudyNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	};




	/**
	 * 按月统计日志
	 * by 汤建志
	 */
	@Transactional
	public List<TjzLogCount> logCountByMonth( ){
		return tjzBackMapper.logCountByMonth( );
	};

	/**
	 * 按人员统计日志
	 * by 汤建志
	 */
	@Transactional
	public List<TjzLogCount> logCountByWid( ){
		return tjzBackMapper.logCountByWid( );
	};


	/**
	 * 家长修改密码
	 * by 汤建志
	 */
	@Transactional
	public int parentChangePassword(TjzTblParent parent){
		return tjzBackMapper.parentChangePassword(parent);
	};

	/**
	 * 家长查询旧密码是否正确
	 * by 汤建志
	 */
	@Transactional
	public TjzTblParent parentOldPassword(int pid){
		return tjzBackMapper.parentOldPassword(pid);
	};

	/**
	 * 家长查看课程表
	 * by 汤建志
	 */
	@Transactional
	public Map<String, List<TjzTbCourse>>  parentCourseTable(Map<String, Object> map2 )
	{
		Map<String, List<TjzTbCourse>> map = null;
		List<TjzTbCourse> lis=tjzBackMapper.parentCourseTable(map2);
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


	/**
	 * 家长班级分页
	 * by 汤建志
	 */
	@Transactional
	public TjzTbTable parentCourseQuery(String pid)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", pid);
		List<TjzTbClassRoom> list = tjzBackMapper.parentCourseQuery(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.parentCourseQueryNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}

	/**
	 * 教师班级分页
	 * by 汤建志
	 */
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

	/**
	 * 教师查看课程表
	 * by 汤建志
	 */
	@Transactional
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


	/**
	 * 插入未排班课程
	 * by 汤建志
	 */
	@Transactional
	public int insertCodeBatch(List<TjzTbCourse > courseList){
		return tjzBackMapper.insertCodeBatch(courseList);
	};


	/**
	 * 园长排课
	 * by 汤建志
	 */
	@Transactional
	public int addSubject(TjzTbCourse course){
		return tjzBackMapper.addSubject(course);
	};

	/**
	 * /查询科目下拉框
	 * by 汤建志
	 */
	@Transactional
	public List<TjzTbSubject> findSubject()
	{
		List<TjzTbSubject> list = tjzBackMapper.findSubject();
		return list;
	}


	/**
	 * 园长班级分页
	 * by 汤建志
	 */
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

	/**
	 * 日志分页
	 * by 汤建志
	 */
	@Transactional
	public TjzTbTable showLogTable(String page, String limit, String startDate, String endDate, String wName)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("wName", wName);
		List<TjzTbLog> list = tjzBackMapper.findLog(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.findLogNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}


	/**
	 * 添加日志
	 * by 汤建志
	 */
	@Transactional
	public int addLog(TjzTbLog log)
	{
		int flag = tjzBackMapper.addLog(log);
		return flag;

	}


	/**
	 * 园长课程表
	 * by 汤建志
	 */
	@Transactional
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