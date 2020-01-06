package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcParentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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
//	@RequestMapping("/xzparent.action")
//	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
//	public ModelAndView xzteacher(){
//		ModelAndView modelAndView =new ModelAndView();
//		modelAndView.setViewName("addparent");
//
//		List<CcTblBaby> list1 = ccParentService.findbaby();
//		System.out.println("list1"+list1);
//		modelAndView.addObject("baby",list1);
//
//		return modelAndView;
//	}

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

//	@RequestMapping("/addparent.action")
//	@ResponseBody
//	@Log(operationType = "增加操作", operationName = "添加家长")
//	public MSG add(CcTblParent ccTblParent, HttpServletRequest request)
//	{
//		String pname=request.getParameter("pname");
//		String bname=request.getParameter("bname");
//		String prelation=request.getParameter("prelation");
//		String pphone=request.getParameter("pphone");
//		String pjob=request.getParameter("pjob");
//
//		Date date =new Date();
//		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//		String time =sdf.format(date);
//
//		ccTblParent.setPname(pname);
//		ccTblParent.setBname(bname);
//		ccTblParent.setPrelation(prelation);
//		ccTblParent.setPphone(pphone);
//		ccTblParent.setPjob(pjob);
//		ccTblParent.setPdate(time);
//
//		int flag =ccParentService.addparent(ccTblParent);
//
//		MSG msg =new MSG();
//
//		if (flag >0 )
//		{
//			msg.setMsg("1");
//			System.out.println("增加家长成功");
//		}
//		else
//		{
//			msg.setMsg("2");
//			System.out.println("增加家长失败");
//		}
//		return msg;
//
//	}

	/**
	 * 家长管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateparent.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改家长信息")
	public MSG update(int pid,String pname,String bid,String prelation,String pphone,String pjob)
	{

		CcTblParent ccTblParent =new CcTblParent();

		CcTblParentBaby ccTblParentBaby =new CcTblParentBaby();

		ccTblParent.setPid(pid);
		ccTblParent.setPname(pname);
		ccTblParent.setPphone(pphone);
		ccTblParent.setPjob(pjob);

		int flag1 = ccParentService.updateparent(ccTblParent);

		CcTblBaby ccTblBaby=ccParentService.findbname(Integer.valueOf(bid));
		ccTblBaby.setBname(ccTblBaby.getBname());
		ccTblBaby.setBid(Integer.valueOf(bid));
		int flag2 =ccParentService.updatebaby(ccTblBaby);

		ccTblParentBaby.setPid(pid);
		ccTblParentBaby.setPrelation(prelation);
		int flag3 =ccParentService.updatepb(ccTblParentBaby);

		MSG msg =new MSG();

		if (flag1 >0 && flag2 >0 && flag3 >0)
		{
			msg.setMsg("1");
			System.out.println("修改家长成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("修改家长失败");
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
		CcTblParentBaby ccTblParentBaby =ccParentService.findb(Integer.valueOf(pid));
		int bid =ccTblParentBaby.getBid();
		int msg1 = ccParentService.deleteparent(Integer .valueOf(pid));
		int msg2 =ccParentService.deletebaby(bid);

		ccTblParentBaby.setBid(bid);
		ccTblParentBaby.setPid(Integer .valueOf(pid));
		int msg3 =ccParentService.deletepb(ccTblParentBaby);

		MSG msg =new MSG();

		if (msg1 >0 && msg2>0 && msg3>0)
		{
			msg.setMsg("1");
			System.out.println("删除家长成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("删除家长失败");
		}
		return msg;
	}



}
