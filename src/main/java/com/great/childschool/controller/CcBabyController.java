package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;

import com.great.childschool.service.CcBabyService;
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
public class CcBabyController
{
	@Resource
	private CcBabyService ccBabyService;

	/**
	 * 幼儿管理-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzbaby.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addbaby");

		return modelAndView;
	}

	/**
	 * 调用幼儿管理页面
	 * by 陈超
	 */
	@RequestMapping("/baby.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("baby");

		return modelAndView;
	}

	/**
	 * 幼儿管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgbaby.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updatebaby");
		return modelAndView;
	}

	/**
	 * 调用幼儿管理数据显示
	 * by 陈超
	 */
	@RequestMapping("/babyxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询宝宝")
	public MSG table(String bname,String starttime, String endtime, int page){

		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setBname(bname);

		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		System.out.println("starttime"+starttime);
		System.out.println("endtime"+endtime);
		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblBaby> list= ccBabyService.findTblBaby(ccTableInf);
		System.out.println("list"+list.toString());

		List<CcTblBaby> totalPagelist= ccBabyService.totalPage1(ccTableInf);
		System.out.println("totalPagelist"+totalPagelist.toString());


		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 幼儿管理--增加逻辑
	 * by 陈超
	 */

	@RequestMapping("/addbaby.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加宝宝")
	public MSG add(CcTblBaby ccTblBaby,HttpServletRequest request)
	{

		String bname=request.getParameter("bname");
		String bsex=request.getParameter("bsex");
		String bbirth=request.getParameter("bbirth");

		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String time =sdf.format(date);
		System.out.println();
		ccTblBaby.setBdate(time);
		ccTblBaby.setBname(bname);
		ccTblBaby.setBsex(bsex);
		ccTblBaby.setBbirth(bbirth);

		System.out.println();
		int flag =ccBabyService.addbaby(ccTblBaby);
		System.out.println(flag);

		MSG msg =new MSG();

		if (flag >0 )
		{
			msg.setMsg("1");
			System.out.println("增加宝宝成功");
		}
		else
		{
			msg.setMsg("2");
			System.out.println("增加宝宝失败");
		}
		return msg;

	}

	/**
	 * 幼儿管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updatebaby.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改宝宝信息")
	public MSG update(int bid,String bname,String bbirth,String bsex)
	{

		CcTblBaby ccTblBaby =new CcTblBaby();
		ccTblBaby.setBid(bid);
		ccTblBaby.setBbirth(bbirth);
		ccTblBaby.setBsex(bsex);
		ccTblBaby.setBname(bname);
		int flag = ccBabyService.updatebaby(ccTblBaby);

		MSG msg =new MSG();
		System.out.println();
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
	 * 幼儿管理--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deletebaby.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除宝宝")
	public MSG deletebaby(String bid)
	{
		System.out.println("bid"+bid);
		int msg = ccBabyService.deletebaby(Integer .valueOf(bid));
		MSG msg1 =new MSG();
		System.out.println();
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
