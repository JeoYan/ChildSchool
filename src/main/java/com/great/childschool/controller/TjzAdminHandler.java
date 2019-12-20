package com.great.childschool.controller;



import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.TjzTbCourse;
import com.great.childschool.entity.TjzTbSubject;
import com.great.childschool.entity.TjzTbTable;
import com.great.childschool.service.TjzBackService;
import com.great.childschool.tools.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/BackAction")
public class TjzAdminHandler
{
	@Resource
	private TjzBackService tjzBackService;

	@RequestMapping("/insertCodeBatch.action")
	public int insertCodeBatch(){
		List<TjzTbCourse > courseList2=new ArrayList<TjzTbCourse>();

		for (int i = 0; i <3 ; i++)
		{
			TjzTbCourse course=new TjzTbCourse();
			course.setcDate("2019-12-19");
			course.setCid(1);
			course.setSubId(1);
			course.setcOrder("1");
			courseList2.add(course);
		}
		tjzBackService.insertCodeBatch(courseList2);
		return 2;
	};


	//查询科目
	@RequestMapping("/findSubject.action")
	public ModelAndView findSubject(HttpServletRequest req)
	{
		List<TjzTbSubject>  subjects=tjzBackService.findSubject();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("courseadd");
		modelAndView.addObject("subjects",subjects);
		return modelAndView;
	}

	//添加课程
	@RequestMapping("/addSubject.action")
	@ResponseBody
	public TjzTbTable addSubject(HttpServletRequest req)
	{
		TjzTbTable tjzTbTable=new TjzTbTable();
		String subjects = req.getParameter("subjects");
		String couId = req.getParameter("couId");
		TjzTbCourse course=new TjzTbCourse();
		course.setCouId(Integer.valueOf(couId));
		course.setSubId(Integer.valueOf(subjects));
		int flag= tjzBackService.addSubject(course);
		if (flag > 0)
		{
			tjzTbTable.setMsg("1");

		} else
		{
			tjzTbTable.setMsg("0");
		}
		return tjzTbTable;
	}





	@RequestMapping("/weekcourseTable.action")
	@Log(operationType = "查询操作", operationName = "课程表")
	public String weekcourseTable(HttpServletRequest request, HttpServletResponse response,String cid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		int intCid=Integer.valueOf(cid);
		if ("上一周".equals(doWhich))
		{

			List<Date> dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));
			List<String> daySting = Tool.getDateType(dateList);
			Date toDay = new Date(System.currentTimeMillis());
			List<Date> toDays = Tool.dateToWeek(toDay);
			List<String> toDaysSting = Tool.getDateType(toDays);

			Date date1=Tool.getDateType((toDaysSting.get(6)));
			Date date2=Tool.getDateType((daySting.get(6)));
			if(dateList.get(6).getTime()>toDays.get(6).getTime()){

				List<String> days = Tool.getDateType(dateList);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("startDate",days.get(0));
				map2.put("endDate",days.get(days.size() - 1));
				map2.put("cid", cid);
				Map<String, List<TjzTbCourse>> map= tjzBackService.courseTable(map2);
				request.setAttribute("cid", cid);
				request.setAttribute("tableBody", map);
				request.setAttribute("tableHead", days);
				return "coursetable";
			}else {


				List<String> days = Tool.getDateType(toDays);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("startDate",days.get(0));
				map2.put("endDate",days.get(days.size() - 1));
				map2.put("cid", cid);
				Map<String, List<TjzTbCourse>> map= tjzBackService.courseTable(map2);
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

		}
		else
		{

			List<Date> dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));
			List<String> days = Tool.getDateType(dateList);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("startDate",days.get(0));
			map2.put("endDate",days.get(days.size() - 1));
			map2.put("cid", cid);
			Map<String, List<TjzTbCourse>> map= tjzBackService.courseTable(map2);

			if (!(map.size()>0)){
				List<TjzTbCourse > courseList2=new ArrayList<TjzTbCourse>();
				for (int i = 0; i <5 ; i++)
				{
					for (int j = 0; j <5 ; j++)
					{

						TjzTbCourse course=new TjzTbCourse();
						course.setcDate(days.get(i));
						course.setCid(intCid);
						course.setSubId(1);
						course.setcOrder(String.valueOf(j+1));
						courseList2.add(course);
					}

				}
				tjzBackService.insertCodeBatch(courseList2);
				 map= tjzBackService.courseTable(map2);
			}

			request.setAttribute("cid", cid);
			request.setAttribute("tableBody", map);
				request.setAttribute("tableHead", days);
				return "coursetable";
		}
	}

	@RequestMapping("/courseTable.action")
	@Log(operationType = "查询操作", operationName = "课程表")
	public String courseTable(HttpServletRequest request,String cid)
	{

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate",days.get(0));
		map2.put("endDate",days.get(days.size() - 1));
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map= tjzBackService.courseTable(map2);
		int intCid=Integer.valueOf(cid);
		if (!(map.size()>0)){
			List<TjzTbCourse > courseList2=new ArrayList<TjzTbCourse>();
			for (int i = 0; i <5 ; i++)
			{
				for (int j = 0; j <5 ; j++)
				{

					TjzTbCourse course=new TjzTbCourse();
					course.setcDate(days.get(i));
					course.setCid(intCid);
					course.setSubId(1);
					course.setcOrder(String.valueOf(j+1));
					courseList2.add(course);
				}

			}
			tjzBackService.insertCodeBatch(courseList2);
			map= tjzBackService.courseTable(map2);
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




	@RequestMapping("/courseManagement.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "课程管理")
	public TjzTbTable courseManagement(String page, String limit, String startDate, String endDate, String cName)
	{

		TjzTbTable tbBean = tjzBackService.courseManagement(page, limit, startDate, endDate, cName);
		return tbBean;
	}

	@RequestMapping("/findLog.action")
	@ResponseBody
	public TjzTbTable FindLog(String page, String limit, String startDate, String endDate, String userName)
	{
		TjzTbTable tbBean = tjzBackService.showLogTable(page, limit, startDate, endDate, userName);
		return tbBean;
	}









}
