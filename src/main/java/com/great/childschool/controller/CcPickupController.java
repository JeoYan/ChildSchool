package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcPickupService;
import com.great.childschool.service.CcTAttendanceService;
import com.great.childschool.tools.CcDateFormatUtil;
import com.great.childschool.tools.DateFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;


@Controller
public class CcPickupController
{
	@Resource
	private CcPickupService ccPickupService;


	/**
	 * 调用接送管理页面
	 * by 陈超
	 */
	@RequestMapping("/pickup.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("pickup");

		List<CcTblClassroom> list1 = ccPickupService.findclassroom();
		System.out.println("list1"+list1);
		modelAndView.addObject("cname",list1);

		return modelAndView;
	}


	/**
	 * 调用教师考勤数据显示
	 * by 陈超
	 */
	@RequestMapping("/pickupxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public MSG table(String bname,String cname,String starttime, String endtime, int page){
		System.out.println(page);
		System.out.println("bname"+bname);
		System.out.println("cname"+cname);
		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setBname(bname);
		ccTableInf.setCname(cname);
		ccTableInf.setPage((page-1)*5);
		//调用查询数据方法
		List<CcTblBaby> list= ccPickupService.find(ccTableInf);
		List<CcTblBaby> totalPagelist= ccPickupService.totalPage(ccTableInf);

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}


	/**
	 * @Author chenchao
	 * @Description //查询宝宝考勤
	 * @Date 2020-01-01
	 **/
	@RequestMapping("/babypickupSign.action")
	@ResponseBody
	public Map<String, Object> babySign(int bid,String flag,String thisDate){
		System.out.println("bid----"+bid);
		Date currentDate =new Date();

		Map<String, Object> map =new HashMap<>();
		try
		{
			if (null!=flag && flag.equals("pre")){
				Date tmpDate = DateFormatUtil.dateParseWeek(thisDate);

				currentDate = DateFormatUtil.getLastWeekMonday(tmpDate);
			}
			else if (null != flag && flag.equals("next"))
			{
				Date tmpDate = DateFormatUtil.dateParseWeek(thisDate);
				System.out.println();
				currentDate = DateFormatUtil.getNextWeekMonday(tmpDate);
			}
			List<Date> dateList = CcDateFormatUtil.dateToWeek(currentDate);

			List<String > dateString = new ArrayList<>();
			String beginDate =DateFormatUtil.dateString(dateList.get(0));
			String endDate = DateFormatUtil.dateString(dateList.get(4));

			List<CcTblBabySign> babySignList = ccPickupService.findtime(bid, beginDate, endDate);
			for(CcTblBabySign ccTblBabySign :babySignList){
				ccTblBabySign.setBsdate(DateFormatUtil.weekString(DateFormatUtil.dateParse(ccTblBabySign.getBsdate())));
			}

			for (Date date:dateList){
				dateString.add(DateFormatUtil.weekString(date));
			}
			map.put("babySignList",babySignList);
			map.put("dateString",dateString);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return map;
	}

}
