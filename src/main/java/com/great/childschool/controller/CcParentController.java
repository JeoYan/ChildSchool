package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcParentService;
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
public class CcParentController
{
	@Resource
	private CcParentService ccParentService;

	/**
	 * 家长管理-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzparent.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addparent");

		List<CcTblBaby> list1 = ccParentService.findbaby();
		System.out.println("list1"+list1);
		modelAndView.addObject("baby",list1);

		return modelAndView;
	}

	/**
	 * 调用家长管理页面
	 * by 陈超
	 */
	@RequestMapping("/parent.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("parent");

		return modelAndView;
	}

	/**
	 * 家长管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgparent.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updateparent");

		List<CcTblBaby> list1 = ccParentService.findbaby();
		System.out.println("list1"+list1);
		modelAndView.addObject("baby",list1);

		return modelAndView;
	}

	/**
	 * 调用家长管理数据显示
	 * by 陈超
	 */
	@RequestMapping("/parentxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询家长")
	public MSG table(String pname,String starttime, String endtime, int page){

		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setPname(pname);

		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		System.out.println("starttime"+starttime);
		System.out.println("endtime"+endtime);
		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblParent> list= ccParentService.findTblParent(ccTableInf);
		System.out.println("list"+list.toString());

		List<CcTblParent> totalPagelist= ccParentService.totalPage1(ccTableInf);
		System.out.println("totalPagelist"+totalPagelist.toString());


		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 家长管理--增加逻辑
	 * by 陈超
	 */

	@RequestMapping("/addparent.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加家长")
	public MSG add(CcTblParent ccTblParent, HttpServletRequest request)
	{
		String pname=request.getParameter("pname");
		String bname=request.getParameter("bname");
		String prelation=request.getParameter("prelation");
		String pphone=request.getParameter("pphone");
		String pjob=request.getParameter("pjob");

		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String time =sdf.format(date);

		ccTblParent.setPname(pname);
		ccTblParent.setBname(bname);
		ccTblParent.setPrelation(prelation);
		ccTblParent.setPphone(pphone);
		ccTblParent.setPjob(pjob);
		ccTblParent.setPdate(time);

		int flag =ccParentService.addparent(ccTblParent);

		MSG msg =new MSG();

		if (flag >0 )
		{
			msg.setMsg("1");
			System.out.println("增加家长成功");
		}
		else
		{
			msg.setMsg("2");
			System.out.println("增加家长失败");
		}
		return msg;

	}

	/**
	 * 家长管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateparent.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改家长信息")
	public MSG update(int pid,String pname,String bname,String prelation,String pphone,String pjob)
	{

		CcTblParent ccTblParent =new CcTblParent();
//		tblParent.setpid(pid);
		ccTblParent.setPname(pname);
		ccTblParent.setBname(bname);
		ccTblParent.setPrelation(prelation);
		ccTblParent.setPphone(pphone);
		ccTblParent.setPjob(pjob);


		int flag = ccParentService.updateparent(ccTblParent);

		MSG msg =new MSG();

		if (flag >0)
		{
			msg.setMsg("1");
			System.out.println("修改成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("修改失败");
		}
		return msg;
	}

	/**
	 * 家长管理--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deleteparent.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除家长")
	public MSG deletebaby(String pid)
	{
		System.out.println("pid"+pid);
		int msg = ccParentService.deleteparent(Integer .valueOf(pid));
		MSG msg1 =new MSG();

		if (msg >0)
		{
			msg1.setMsg("1");
			System.out.println("删除成功");
		} else
		{
			msg1.setMsg("2");
			System.out.println("删除失败");
		}
		return msg1;
	}



}
