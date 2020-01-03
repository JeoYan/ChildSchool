package com.great.childschool.controller;


import com.great.childschool.entity.*;
import com.great.childschool.service.ZhWeChatService;
import com.great.childschool.tools.DateFormatUtil;
import com.great.childschool.tools.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * 微信业务Controller层
 * by 张宏
 */
@Controller
public class ZhWeChatController
{
	@Resource
	private ZhWeChatService zhWeChatService;

	@RequestMapping("/weChatLogin.action")
	@ResponseBody
	public TblParent weChatLogin(String uPhone,String passWord){
//
//		System.out.println(uPhone+"-----------getPphone------------");
//		System.out.println(passWord+"-----------getPpsw------------");

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);


		for (int i = 0; i <days.size() ; i++)
		{
			System.out.println(days.get(i).toString()+"----------days-------------");
		}

		System.out.println(days+"----------days-------------");
		TblParent tblParent=zhWeChatService.weChatLogin(uPhone,passWord);

		if (tblParent!=null){

			return tblParent;
		}


		return null;
	}





	//查询课程表
	@RequestMapping("/findCourse.action")
	@ResponseBody
	public Map<String,List<ZhTblSubject>>  findCourse( String pid)
	{
		Map<String,List<ZhTblSubject>> map=new HashMap<>();

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		List <List<ZhTblSubject>> arr=new ArrayList<List<ZhTblSubject>>();

		for (int i = 0; i <days.size() ; i++)
		{
			List<ZhTblSubject> list=new ArrayList<>();
			list=zhWeChatService.findCourse(pid,days.get(i).toString());
			if (i==0){
				map.put("kb1_2",list);
			}
			if (i==1){
				map.put("kb3_4",list);
			}
			if (i==2){
				map.put("kb5_6",list);
			}
			if (i==3){
				map.put("kb7_8",list);
			}
			if (i==4){
				map.put("kb9_10",list);
			}
		}

		return map;

	}



	//查询作业评分

	@RequestMapping("/findScore.action")
	@ResponseBody
	public  List<ZhTblHomework>findScore(ZhTblHomework zhTblHomework){


//		zhTblHomework.setPid(1);
		List<ZhTblHomework> list =zhWeChatService.findScore(zhTblHomework);

		return list;
	}

	//体检情况表格
	@RequestMapping("/weChatFindMedicalCase.action")
	@ResponseBody
	public List<TblChecklist> weChatFindMedicalCase(TblChecklist tblChecklist)
	{
		System.out.println(tblChecklist.getPid()+"+++++++++++getPid+++++++++++");
		System.out.println(tblChecklist.getCheckDate()+"+++++++++++getCheckDate+++++++++++");
//		tblChecklist.setPid(1);

		List<TblChecklist> list=zhWeChatService.findMedicalCase(tblChecklist);

		System.out.println(list.size()+"-------------list.size()-----------------");
		return list;
	}


	//膳食
	@RequestMapping("/weChatFindFoodManage.action")
	@ResponseBody
	public List<TblFood> findFoodManage(TblFood tblFood){
		System.out.println(tblFood.getFdate());

		List<TblFood> list=zhWeChatService.findFoodManage(tblFood);


		return list;
	}



	//查询宝宝考勤
	@RequestMapping("/findAttendance.action")
	@ResponseBody
	public Map<String, List<ZhBabySign>> findAttendance(String pid)
	{
		Map<String,List<ZhBabySign>> map =new HashMap<>();
		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);

		for (int i = 0; i <days.size() ; i++)
		{
			List<ZhBabySign> list=new ArrayList<>();
			list=zhWeChatService.findAttendance(pid,days.get(i).toString());
			System.out.println(days.get(i).toString()+"-------------days.get(i).toString()-----------");

			System.out.println(list.size()+"-------------size-----------");

//			map.put(days.get(i).toString(),list);

			if (i==0){
				map.put("kb1_2",list);
			}
			if (i==1){
				map.put("kb3_4",list);
			}
			if (i==2){
				map.put("kb5_6",list);
			}
			if (i==3){
				map.put("kb7_8",list);
			}
			if (i==4){
				map.put("kb9_10",list);
			}
		}


		return  map;
	}



	//个人信息
	@RequestMapping("/findInformation.action")
	@ResponseBody
	public ZhInformation findInformation(String pid)
	{

		ZhInformation zhInformation =zhWeChatService.findInformation(pid);

		return zhInformation;
	}


	//通讯录
	@RequestMapping("/findAddress.action")
	@ResponseBody
	public List<TblWorker> findAddress(){
		List<TblWorker> list =zhWeChatService.findAddress();
		return list;
	}


}
