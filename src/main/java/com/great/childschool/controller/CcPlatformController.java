package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcPlatformService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class CcPlatformController
{
	@Resource
	private CcPlatformService ccPlatformService;

	/**
	 * 平台资讯-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzplatform.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzplatform(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addplatform");


		return modelAndView;
	}

	/**
	 * 调用班级管理页面
	 * by 陈超
	 */
	@RequestMapping("/platform.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("platform");

		return modelAndView;
	}

	/**
	 * 班级管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgplatform.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgplatform(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updateplatform");


		return modelAndView;
	}

	/**
	 * 调用平台资讯数据显示
	 * by 陈超
	 */
	@RequestMapping("/platformxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询班级")
	public MSG table(String notitle,String starttime, String endtime, int page){

		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setNotitle(notitle);

		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);

		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblNotice> list= ccPlatformService.find(ccTableInf);

		List<CcTblNotice> totalPagelist= ccPlatformService.totalPage(ccTableInf);
		System.out.println("totalPagelist"+totalPagelist.toString());


		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 平台资讯--班级增加
	 * by 陈超
	 */
	@RequestMapping("/addplatform.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加班级")
	public MSG add(CcTblNotice ccTblNotice,HttpServletRequest request)
	{
		//宝宝
		String notitle=request.getParameter("notitle");
		String nconntext=request.getParameter("nconntext");
		String wid=request.getParameter("wid");

		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String time =sdf.format(date);

		ccTblNotice.setNdate(time);
		ccTblNotice.setNconntext(nconntext);
		ccTblNotice.setNotitle(notitle);

		int flag =ccPlatformService.addplatform(ccTblNotice);
		System.out.println(flag);

		MSG msg =new MSG();

		if (flag >0 )
		{
			msg.setMsg("1");
			System.out.println("平台资讯增加成功");

		}
		else
		{
			msg.setMsg("2");
			System.out.println("平台资讯增加失败");
		}
		return msg;


	}

	/**
	 * 平台资讯--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateplatform.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改平台资讯")
	public MSG update(int noid,String notitle,String nconntext)
	{
		CcTblNotice ccTblNotice =new CcTblNotice();

		ccTblNotice.setNoid(noid);
		ccTblNotice.setNconntext(nconntext);
		ccTblNotice.setNotitle(notitle);
		int flag = ccPlatformService.updateplatform(ccTblNotice);

		MSG msg =new MSG();

		if (flag >0)
		{
			msg.setMsg("1");
			System.out.println("平台资讯修改成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("平台资讯修改失败");
		}
		return msg;


	}

	/**
	 * 平台资讯--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deleteplatform.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除平台资讯")
	public MSG delete(String noid)
	{
		System.out.println("noid"+noid);
		int msg1= ccPlatformService.deleteplatform(Integer.valueOf(noid));

		MSG msg =new MSG();
		System.out.println();
		if (msg1 >0)
		{
			msg.setMsg("1");
			System.out.println("删除成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("删除失败");
		}
		return msg;

	}



}
