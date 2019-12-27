package com.great.childschool.controller;

import com.great.childschool.service.CcTAttendanceService;
import com.great.childschool.tools.CcDateFormatUtil;
import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class CcTAttendanceController
{
	@Resource
	private CcTAttendanceService ccTAttendanceService;


	/**
	 * 调用教师考勤页面
	 * by 陈超
	 */
	@RequestMapping("/teacherattendance.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("teacherattendance");

		List<TblRole> list1 = ccTAttendanceService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}


	/**
	 * 调用教师考勤数据显示
	 * by 陈超
	 */
	@RequestMapping("/attendancexs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public MSG table(String wname,String rname,String starttime, String endtime, int page){
		System.out.println(page);
		System.out.println("rname"+rname);
		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setWname(wname);
		ccTableInf.setRname(rname);
		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		ccTableInf.setPage((page-1)*5);
		//调用查询数据方法
		List<TblWorker> list= ccTAttendanceService.find(ccTableInf);
		List<TblWorker> totalPagelist= ccTAttendanceService.totalPage(ccTableInf);

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * @Author MJChen
	 * @Description //查询宝宝考勤
	 * @Date 14:59 2019/12/24
	 **/
	@RequestMapping("/teacherSign.action")
	@ResponseBody
	public Map<String, Object> teacherSign(int wid){
		Date currentDate =new Date();
		List<String > dateString = new ArrayList<>();
		Map<String, Object> map =new HashMap<>();

		List<Date> dateList = CcDateFormatUtil.dateToWeek(currentDate);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String beginDate =sdf.format(dateList.get(0));
		String endDate = sdf.format(dateList.get(4));
		System.out.println(beginDate);
		System.out.println(endDate);

		List<CcTblWorkerSign> teacherSignList =ccTAttendanceService.findtime(wid,beginDate,endDate);

		SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd EEE");

		try
		{
			for(CcTblWorkerSign ccTblWorkerSign :teacherSignList){
				ccTblWorkerSign.setWsdate(sdf1.format(new SimpleDateFormat("yyyy-MM-dd")
				.parse(ccTblWorkerSign.getWsdate())));
			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
			for (Date date:dateList){
				dateString.add(sdf1.format(date));
			}
			map.put("teacherSignList",teacherSignList);
			map.put("dateString",dateString);
		return map;
	}



}
