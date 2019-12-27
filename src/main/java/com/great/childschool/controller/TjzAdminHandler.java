package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.TjzBackService;
import com.great.childschool.tools.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * 控制类
 * by 汤建志
 */

@Controller
@RequestMapping("/BackAction")
public class TjzAdminHandler
{
	@Resource
	private TjzBackService tjzBackService;


	@RequestMapping(value = "/upload.action")
	@ResponseBody
	@Log(operationType = "上传操作", operationName = "上传文件")
	public Map<String,Object> backUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
	{
		String originalFilename =file.getOriginalFilename();
		System.out.println(originalFilename+"----------文件名-----------");
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String safeName=request.getParameter("safeName");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		TjzTblSafeStudy safeStudy=new TjzTblSafeStudy();
		safeStudy.setSafeName(safeName);
		safeStudy.setStartDate(startDate);
		safeStudy.setEndDate(endDate);
		String newFilename = safeName + "." + suffix;
		String saveFilePath = "G:\\传一科技java培训\\" + newFilename;
		Map<String,Object> map = new HashMap<String,Object>();
		try
		{
			//保存到数据库
			int flag = tjzBackService.upload(safeStudy);
			if (flag>0){
				//保存文件到服务器
				file.transferTo(new File(saveFilePath));
				map.put("msg","ok");
			}else {
				map.put("msg","error");
			}
		} catch (IOException e)
		{
			map.put("msg","error");
			e.printStackTrace();
		}
		return map;
	}


	/**
	 * 教师发布安全教育试题
	 * by 汤建志
	 */
	@RequestMapping("/uploadSafeStudy.action")
	@ResponseBody
	public TjzTbTable uploadSafeStudy(HttpServletRequest request, String page, String limit, String startDate, String endDate, String safeName){
		String wid = request.getSession().getAttribute("wid").toString();
		TjzTbTable tbBean = tjzBackService.uploadsafestudy(page, limit, startDate, endDate, safeName, wid);
		return tbBean;
	};



	/**
	 * 按月统计日志界面
	 * by 汤建志
	 */
	@RequestMapping("/logCountByMonthView.action")
	public ModelAndView logCountByMonthView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logcountbymonth");
		return modelAndView;
	}

	/**
	 * 按人员统计日志界面
	 * by 汤建志
	 */
	@RequestMapping("/logCountByWidView.action")
	public ModelAndView logCountByWidView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logcountbywid");
		return modelAndView;
	}

	/**
	 * 按月统计日志
	 * by 汤建志
	 */
	@RequestMapping("/logCountByMonth.action")
	@ResponseBody
	public List<TjzLogCount> logCountByMonth(){
		List<TjzLogCount> logCounts=tjzBackService.logCountByMonth();
		System.out.println(logCounts.toString());
		return logCounts;
	}

	/**
	 * 按人员统计日志
	 * by 汤建志
	 */
	@RequestMapping("/logCountByWid.action")
	@ResponseBody
	public List<TjzLogCount> logCountByWid(){
		List<TjzLogCount> logCounts=tjzBackService.logCountByWid();
		System.out.println(logCounts.toString());
		return logCounts;
	}



	/**
	 * 查询日志
	 * by 汤建志
	 */
	@RequestMapping("/findLog.action")
	@ResponseBody
	public TjzTbTable findLog(String page, String limit, String startDate, String endDate, String wName)
	{
		TjzTbTable tbBean = tjzBackService.showLogTable(page, limit, startDate, endDate, wName);
		return tbBean;
	}

	/**
	 * 家长修改密码
	 * by 汤建志
	 */
	@RequestMapping("/parentChangePassword.action")
	@ResponseBody
	@Log(operationType = "修改密码", operationName = "家长修改密码")
	public TjzTbTable parentChangePassword(HttpServletRequest request, HttpServletResponse response)
	{

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");
		String pid = request.getSession().getAttribute("pid").toString();
		TjzTbTable tbTable = new TjzTbTable();
		if (!newPassword.equals(reNewPassword))
		{
			tbTable.setMsg("0");
		} else
		{

			TjzTblParent parent = tjzBackService.parentOldPassword(Integer.valueOf(pid));
			if (!parent.getPpsw().equals(oldPassword))
			{
				tbTable.setMsg("1");
			} else
			{

				parent.setPid(Integer.valueOf(pid));
				parent.setPpsw(newPassword);
				int flag=tjzBackService.parentChangePassword(parent);
				if (flag>0){
					tbTable.setMsg("2");
				}else {
					tbTable.setMsg("3");
				}

			}

		}


		return tbTable;

}

	/**
	 * 家长查看上下周课程表
	 * by 汤建志
	 */
	@RequestMapping("/parentWeekCourseTable.action")
//	@Log(operationType = "查询操作", operationName = "查询课程表")
	public String parentWeekCourseTable(HttpServletRequest request, HttpServletResponse response, String bid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		List<Date> dateList;
		if ("上一周".equals(doWhich))
		{
			dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));

		} else
		{
			dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));
		}
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("bid", bid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.parentCourseTable(map2);
		request.setAttribute("bid", bid);
		request.setAttribute("tableBody", map);
		request.setAttribute("tableHead", days);
		return "parentcoursetable";


	}


	/**
	 * 家长查看本周课程表
	 * by 汤建志
	 */
	@RequestMapping("/parentCourseTable.action")
//	@Log(operationType = "查询操作", operationName = "查询课程表")
	public String parentCourseTable(HttpServletRequest request, String bid)
	{

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("bid", bid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.parentCourseTable(map2);
		if (null != map)
		{
			request.setAttribute("bid", bid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "parentcoursetable";
		} else
		{
			System.out.println("fail");
			return "parentcoursetable";

		}
	}



	/**
	 * 家长查看孩子所在班级信息
	 * by 汤建志
	 */
	@RequestMapping("/parentCourseQuery.action")
	@ResponseBody
//	@Log(operationType = "查询操作", operationName = "课程管理")
	public TjzTbTable parentCourseQuery(HttpServletRequest request)
	{

		String pid = request.getSession().getAttribute("pid").toString();
		TjzTbTable tbBean = tjzBackService.parentCourseQuery(pid);
		return tbBean;
	}

	/**
	 * 教师查看班级信息
	 * by 汤建志
	 */
	@RequestMapping("/teacherCourseQuery.action")
	@ResponseBody
//	@Log(operationType = "查询操作", operationName = "课程管理")
	public TjzTbTable teacherCourseQuery(HttpServletRequest request, String page, String limit, String startDate, String endDate, String cName)
	{

		String wid = request.getSession().getAttribute("wid").toString();
		TjzTbTable tbBean = tjzBackService.teacherCourseQuery(page, limit, startDate, endDate, cName, wid);
		return tbBean;
	}


	/**
	 * 教师查看上下周课程表
	 * by 汤建志
	 */
	@RequestMapping("/teacherWeekcourseTable.action")
//	@Log(operationType = "查询操作", operationName = "课程表")
	public String teacherWeekcourseTable(HttpServletRequest request, HttpServletResponse response, String cid, String wid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		List<Date> dateList;
		if ("上一周".equals(doWhich))
		{
			dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));


		} else
		{

			dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));

		}
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("wid", wid);
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.teacherCourseTable(map2);
		request.setAttribute("cid", cid);
		request.setAttribute("wid", wid);
		request.setAttribute("tableBody", map);
		request.setAttribute("tableHead", days);
		return "teachercoursetable";


	}


	/**
	 * 教师查看本周课程表
	 * by 汤建志
	 */
	@RequestMapping("/teacherCourseTable.action")
//	@Log(operationType = "查询操作", operationName = "课程表")
	public String teacherCourseTable(HttpServletRequest request, String cid, String bid)
	{


		String wid = request.getSession().getAttribute("wid").toString();
		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("wid", wid);
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.teacherCourseTable(map2);
		if (null != map)
		{
			request.setAttribute("cid", cid);
			request.setAttribute("wid", wid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "teachercoursetable";
		} else
		{
			System.out.println("fail");
			return "teachercoursetable";

		}
	}

	/**
	 * 批量插入空课程表
	 * by 汤建志
	 */
	@RequestMapping("/insertCodeBatch.action")
	public int insertCodeBatch()
	{
		List<TjzTbCourse> courseList2 = new ArrayList<TjzTbCourse>();

		for (int i = 0; i < 3; i++)
		{
			TjzTbCourse course = new TjzTbCourse();
			course.setcDate("2019-12-19");
			course.setCid(1);
			course.setSubId(1);
			course.setcOrder("1");
			courseList2.add(course);
		}
		tjzBackService.insertCodeBatch(courseList2);
		return 2;
	}


	/**
	 * 科目下拉框
	 * by 汤建志
	 */
	@RequestMapping("/findSubject.action")
	public ModelAndView findSubject(HttpServletRequest req)
	{
		List<TjzTbSubject> subjects = tjzBackService.findSubject();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("courseadd");
		modelAndView.addObject("subjects", subjects);
		return modelAndView;
	}

	/**
	 * 排课
	 * by 汤建志
	 */
	@RequestMapping("/addSubject.action")
	@ResponseBody
	@Log(operationType = "插入操作", operationName = "园长排课")
	public TjzTbTable addSubject(HttpServletRequest req)
	{
		TjzTbTable tjzTbTable = new TjzTbTable();
		String subjects = req.getParameter("subjects");
		String couId = req.getParameter("couId");
		TjzTbCourse course = new TjzTbCourse();
		course.setCouId(Integer.valueOf(couId));
		course.setSubId(Integer.valueOf(subjects));
		int flag = tjzBackService.addSubject(course);
		if (flag > 0)
		{
			tjzTbTable.setMsg("1");

		} else
		{
			tjzTbTable.setMsg("0");
		}
		return tjzTbTable;
	}

	/**
	 * 园长查看上下周课程表
	 * by 汤建志
	 */
	@RequestMapping("/weekcourseTable.action")
//	@Log(operationType = "查询操作", operationName = "课程表")
	public String weekcourseTable(HttpServletRequest request, HttpServletResponse response, String cid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		int intCid = Integer.valueOf(cid);
		Map<String, List<TjzTbCourse>> map;
		List<String> days;
		if ("上一周".equals(doWhich))
		{
			List<Date> dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));
			List<String> daySting = Tool.getDateType(dateList);
			Date toDay = new Date(System.currentTimeMillis());
			List<Date> toDays = Tool.dateToWeek(toDay);
			List<String> toDaysSting = Tool.getDateType(toDays);
			Date date1 = Tool.getDateType((toDaysSting.get(6)));
			Date date2 = Tool.getDateType((daySting.get(6)));
			if (dateList.get(6).getTime() > toDays.get(6).getTime())
			{
				days = Tool.getDateType(dateList);
			} else
			{
				days = Tool.getDateType(toDays);
			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("startDate", days.get(0));
			map2.put("endDate", days.get(days.size() - 1));
			map2.put("cid", cid);
			map = tjzBackService.courseTable(map2);
		} else
		{
			List<Date> dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));
			days = Tool.getDateType(dateList);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("startDate", days.get(0));
			map2.put("endDate", days.get(days.size() - 1));
			map2.put("cid", cid);
			map = tjzBackService.courseTable(map2);
			if (!(map.size() > 0))
			{
				List<TjzTbCourse> courseList2 = new ArrayList<TjzTbCourse>();
				for (int i = 0; i < 5; i++)
				{
					for (int j = 0; j < 5; j++)
					{
						TjzTbCourse course = new TjzTbCourse();
						course.setcDate(days.get(i));
						course.setCid(intCid);
						course.setSubId(1);
						course.setcOrder(String.valueOf(j + 1));
						courseList2.add(course);
					}
				}
				tjzBackService.insertCodeBatch(courseList2);
				map = tjzBackService.courseTable(map2);
			}
		}
		if (null != map)
		{
			request.setAttribute("cid", cid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "coursetable";
		} else
		{
			System.out.println("fail");
			return "coursetable";
		}
	}

	/**
	 * 园长查看本周课程表
	 * by 汤建志
	 */
	@RequestMapping("/courseTable.action")
//	@Log(operationType = "查询操作", operationName = "课程表")
	public String courseTable(HttpServletRequest request, String cid)
	{

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.courseTable(map2);
		int intCid = Integer.valueOf(cid);
		if (!(map.size() > 0))
		{
			List<TjzTbCourse> courseList2 = new ArrayList<TjzTbCourse>();
			for (int i = 0; i < 5; i++)
			{
				for (int j = 0; j < 5; j++)
				{

					TjzTbCourse course = new TjzTbCourse();
					course.setcDate(days.get(i));
					course.setCid(intCid);
					course.setSubId(1);
					course.setcOrder(String.valueOf(j + 1));
					courseList2.add(course);
				}

			}
			tjzBackService.insertCodeBatch(courseList2);
			map = tjzBackService.courseTable(map2);
		}


		if (null != map)
		{
			request.setAttribute("cid", cid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "coursetable";
		} else
		{
			System.out.println("fail");
			return "coursetable";

		}
	}


	/**
	 * 园长课程管理
	 * by 汤建志
	 */
	@RequestMapping("/courseManagement.action")
	@ResponseBody
//	@Log(operationType = "管理操作", operationName = "课程管理")
	public TjzTbTable courseManagement(String page, String limit, String startDate, String endDate, String cName)
	{

		TjzTbTable tbBean = tjzBackService.courseManagement(page, limit, startDate, endDate, cName);
		return tbBean;
	}




}
