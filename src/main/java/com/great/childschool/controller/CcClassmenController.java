package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcClassService;
import com.great.childschool.service.CcClassmenService;
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
public class CcClassmenController
{
	@Resource
	private CcClassmenService ccClassmenService;

	/**
	 * 班级成员管理-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzclassmen.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addclassmen");

		return modelAndView;
	}

	/**
	 * 调用班级成员管理页面
	 * by 陈超
	 */
	@RequestMapping("/classmen.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("classmen");

		return modelAndView;
	}

	/**
	 * 班级成员管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgclassmen.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updateclassmen");

		List<CcTblClassroom> list = ccClassmenService.findclassroom();
		System.out.println("list"+list);
		modelAndView.addObject("cname",list);

		List<TblWorker> list1 = ccClassmenService.findtea();
		System.out.println("list1"+list1);
		modelAndView.addObject("teacher",list1);

		return modelAndView;
	}

	/**
	 * 调用班级成员管理数据显示
	 * by 陈超
	 */
	@RequestMapping("/classmenxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询班级成员")
	public MSG table(String cname,String bname,String starttime, String endtime, int page){

		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setCname(cname);
		ccTableInf.setBname(bname);
		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		System.out.println("starttime"+starttime);
		System.out.println("endtime"+endtime);
		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblClassroom> list= ccClassmenService.find(ccTableInf);

		List<CcTblClassroom> totalPagelist= ccClassmenService.totalPage(ccTableInf);

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

//	/**
//	 * 班级成员管理--增加
//	 * by 陈超
//	 */
//	@RequestMapping("/addclassmen.action")
//	@ResponseBody
//	@Log(operationType = "增加操作", operationName = "添加班级成员")
//	public MSG add(CcTblClassroom ccTblClassroom,HttpServletRequest request)
//	{
//		//宝宝
//		String bname=request.getParameter("bname");
//		String bsex=request.getParameter("bsex");
//		String bbirth=request.getParameter("bbirth");
//		String baddress=request.getParameter("baddress");
//
//
//		String prelation=request.getParameter("prelation");
//
//		Date date =new Date();
//		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//		String time =sdf.format(date);
//
//		return null;
//
//	}
	/**
	 * 班级成员管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateclassmen.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改班级成员信息")
	public MSG update(int bid,int cid,int wid,String bname,String cname)
	{

		CcTblBaby ccTblBaby =new CcTblBaby();

		ccTblBaby.setBid(bid);
		ccTblBaby.setCid(cid);
		ccTblBaby.setBname(bname);
		int flag1= ccClassmenService.updateb(ccTblBaby);

		CcTblClassroom ccTblClassroom= ccClassmenService.findcname(cid);
		ccTblClassroom.setCname(ccTblClassroom.getCname());
		ccTblClassroom.setCid(cid);

		ccTblClassroom.setWid(wid);
		int flag2 = ccClassmenService.updatec(ccTblClassroom);

		MSG msg =new MSG();

		if (flag1 >0 && flag2 >0)
		{
			msg.setMsg("1");
			System.out.println("修改班级成员成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("修改班级成员失败");
		}
		return msg;

	}

	/**
	 * 班级成员管理--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deleteclassmen.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除班级成员")
	public MSG delete(String  cid,String  bid)
	{
		System.out.println("bid"+bid);
		CcTblBaby ccTblBaby =new CcTblBaby();
		CcTblParentBaby ccTblParentBaby =ccClassmenService.findp(Integer.valueOf(bid));

		ccTblBaby.setCid(Integer.valueOf(cid));
		ccTblBaby.setBid(Integer.valueOf(bid));

		int msg1=ccClassmenService.deletebaby(ccTblBaby);
		int pid=ccTblParentBaby.getPid();
		int msg2=ccClassmenService.deleteparent(pid);

		ccTblParentBaby.setPid(pid);
		ccTblParentBaby.setBid(Integer.valueOf(bid));
		int msg3=ccClassmenService.deletepb(ccTblParentBaby);

		MSG msg =new MSG();

		if (msg1 >0 && msg2 >0 && msg3 >0)
		{
			msg.setMsg("1");
			System.out.println("删除班级成员成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("删除班级成员失败");
		}
		return msg;

	}



}

