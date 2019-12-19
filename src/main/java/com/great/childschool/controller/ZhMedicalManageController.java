package com.great.childschool.controller;


import com.great.childschool.entity.TblBaby;
import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.TblClassroom;
import com.great.childschool.entity.ZhSend;
import com.great.childschool.service.ZhMedicalManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 体检控制层
 * by 张宏
 */

@Controller

public class ZhMedicalManageController
{

	@Resource
	private ZhMedicalManageService zhMedicalManageService;


	/**
	 * 保健端体检
	 *
	 */

	//体检界面显示
	@RequestMapping("/medicalmanage.action")
	public ModelAndView tableView()
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("medicalmanage");

		return modelAndView;
	}

	//体检修改窗口
	@RequestMapping("/updatemedicalview.action")
	public ModelAndView updatemedicalview()
	{
		ModelAndView modelAndView = new ModelAndView();
//		List<TblClassroom> list =zhMedicalManageService.allClass();
//		modelAndView.addObject("allClass", list);
		modelAndView.setViewName("updatemedical");

		return modelAndView;
	}

	//体检增加窗口
	@RequestMapping("/addmedicalview.action")
	public ModelAndView addmedical()
	{
		ModelAndView modelAndView = new ModelAndView();
		List<TblClassroom> list =zhMedicalManageService.allClass();
		modelAndView.addObject("allClass", list);
		modelAndView.setViewName("addmedical");

		return modelAndView;
	}

	//查询全部班级
	@RequestMapping("/allClass.action")
	public ModelAndView allClass(){
		ModelAndView modelAndView = new ModelAndView();
		List<TblClassroom> list =zhMedicalManageService.allClass();
		modelAndView.addObject("allClass", list);
		return modelAndView;
	}

	//查询班级宝宝
	@RequestMapping("/findBaby.action")
	@ResponseBody
	public List findBaby(TblClassroom tblClassroom){
		List<TblBaby> list = zhMedicalManageService.findbaby(tblClassroom.getcId());
		return list;
	}

	//增加体检信息

	@RequestMapping("/addmedical.action")
	@ResponseBody
	public int addMedical(TblChecklist tblChecklist){
		//时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//获取时间
		String s = simpleDateFormat.format(date);
		tblChecklist.setCheckDate(s);
		int num;
		TblChecklist newTblChecklist=zhMedicalManageService.findMedical(tblChecklist);
		if (newTblChecklist == null){
			num =zhMedicalManageService.addMedical(tblChecklist);
		}else {
			num=0;
		}




		return num;
	}


	//保健端体检管理表格
	//表格
	@RequestMapping("/findMedicalManage.action")
	@ResponseBody
	public ZhSend findMedicalManage(TblChecklist tblChecklist, String page)
	{

		//发送表格实体类
		ZhSend zhSend = new ZhSend();

		tblChecklist.setPage((Integer.valueOf(page) - 1) * 5);

		List<TblChecklist> list = zhMedicalManageService.findMedicalManage(tblChecklist);

		List<TblChecklist> pageList = zhMedicalManageService.totalPageMedicalManage(tblChecklist);


		int totalPage = pageList.size();


		zhSend.setCode(new BigDecimal(0));

		zhSend.setCount(new BigDecimal(totalPage));

		zhSend.setData(list);


		return zhSend;
	}


	//修改体检信息
	@RequestMapping("/updateMedical.action")
	@ResponseBody
	public int updateMedical(TblChecklist tblChecklist){

//		System.out.println(tblChecklist.getTemperature()+"------------getTemperature-----------");
//		//时间
//		Date date = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		//获取时间
//		String s = simpleDateFormat.format(date);
//		tblChecklist.setCheckDate(s);

		int num =zhMedicalManageService.updateMedical(tblChecklist);

		return num;
	}


	//删除体检信息
	@RequestMapping("/deleteMedical.action")
	@ResponseBody
	public int deleteMedical(TblChecklist tblChecklist){

//		System.out.println(tblChecklist.getTemperature()+"------------getTemperature-----------");


		int num =zhMedicalManageService.deleteMedical(tblChecklist);

		return num;
	}



	/**
	 * 家长端体检信息
	 *
	 */

	//家长端体检情况界面
	@RequestMapping("/medicalcase.action")
	public ModelAndView medicalcaseView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		TblChecklist tblChecklist = new TblChecklist();
		tblChecklist.setPid(Long.valueOf(String.valueOf(request.getSession().getAttribute("pid"))).longValue());


		List<TblChecklist> pageList = zhMedicalManageService.totalPageMedicalCase(tblChecklist);
		modelAndView.addObject("bybyname", pageList.get(0).getBname());
		modelAndView.addObject("bybysex", pageList.get(0).getBsex());

		System.out.println(pageList.get(0).getBname()+"------------getBname------------");
		System.out.println(pageList.get(0).getBsex()+"------------getBsex------------");
		modelAndView.setViewName("medicalcase");

		return modelAndView;
	}


	//家长端体检情况表格
	@RequestMapping("/findMedicalCase.action")
	@ResponseBody
	public ZhSend findMedicalCase(TblChecklist tblChecklist, String page, HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView();

		//获取家长id
		tblChecklist.setPid(Long.valueOf(String.valueOf(request.getSession().getAttribute("pid"))).longValue());
		System.out.println(tblChecklist.getPid()+"----------getPid-------------");

		//发送表格实体类
		ZhSend zhSend = new ZhSend();

		tblChecklist.setPage((Integer.valueOf(page) - 1) * 5);

		List<TblChecklist> list = zhMedicalManageService.findMedicalCase(tblChecklist);

		System.out.println(list.get(0).getCheckDate()+"++++++++++++getCheckDate++++++++++++++++");

		List<TblChecklist> pageList = zhMedicalManageService.totalPageMedicalCase(tblChecklist);

		int totalPage = pageList.size();

		zhSend.setCode(new BigDecimal(0));

		zhSend.setCount(new BigDecimal(totalPage));

		zhSend.setData(list);

		return zhSend;
	}


}
