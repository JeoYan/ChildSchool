package com.great.childschool.controller;

import com.great.childschool.entity.CmjBabySign;
import com.great.childschool.entity.CmjTblBaby;
import com.great.childschool.entity.DataBean;
import com.great.childschool.service.CmjClassSignService;
import com.great.childschool.tools.DateFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *@classname CmjClassSignController
 *@description 班级考勤控制层
 *@author MJChen
 *@date 2019/12/24 10:01
 *@version 1.0
 **/
@Controller
@RequestMapping("/ClassSignController")
public class CmjClassSignController
{
	@Resource
	private CmjClassSignService classSignService;
	/**
	 * @Author MJChen
	 * @Description //跳转到某一界面
	 * @Date 14:51 2019/12/18
	 **/
	@RequestMapping("/query/{url}")
	public String turnToJsp(@PathVariable(value = "url") String path){
		return path;
	}
	@RequestMapping("/classSign")
	@ResponseBody
	public DataBean<CmjTblBaby> classSign(String page,String limit,String babyName){
		int size = Integer.valueOf(limit);
		int curPage = (Integer.valueOf(page)-1)*size;
		DataBean<CmjTblBaby> dataBean = new DataBean<>();
		List<CmjTblBaby> cmjTblBabies = classSignService.classSign(curPage,size,babyName);
		Integer count = classSignService.countSign(babyName);
		dataBean.setCount(count);
		dataBean.setData(cmjTblBabies);
		return dataBean;
	}
	/**
	 * @Author MJChen
	 * @Description //查询宝宝考勤
	 * @Date 14:59 2019/12/24
	 **/
	@RequestMapping("/queryBabySign")
	@ResponseBody
	public Map<String, Object> queryBabySign(int bid,String flag,String thisDate){
		Date currentDate = new Date();
		Map<String, Object> map = new HashMap<>();
		try
		{

			if (null != flag && flag.equals("pre"))
			{
				Date tmpDate = DateFormatUtil.dateParseWeek(thisDate);
				currentDate = DateFormatUtil.getLastWeekMonday(tmpDate);
			} else if (null != flag && flag.equals("next"))
			{
				Date tmpDate = DateFormatUtil.dateParseWeek(thisDate);
				currentDate = DateFormatUtil.getNextWeekMonday(tmpDate);
			}
			List<Date> dateList = DateFormatUtil.dateToWeek(currentDate);
			List<String> dateString = new ArrayList<>();
			String beginDate = DateFormatUtil.dateString(dateList.get(0));
			String endDate = DateFormatUtil.dateString(dateList.get(4));
			List<CmjBabySign> babySignList = classSignService.babySigin(bid, beginDate, endDate);
			for (CmjBabySign cmjBabySign : babySignList)
			{
				cmjBabySign.setBsDate(DateFormatUtil.weekString(DateFormatUtil.dateParse(cmjBabySign.getBsDate())));
			}
			for (Date date : dateList)
			{
				dateString.add(DateFormatUtil.weekString(date));
			}
			map.put("babySignList",babySignList);
			map.put("dateString",dateString);
		}
		 catch (ParseException e)
		{
			e.printStackTrace();
		}

		return map;
	}
}
