package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcClassService;
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
public class CcClassController
{
	@Resource
	private CcClassService ccClassService;

	/**
	 * 幼儿管理-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzclass.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addclass");

		List<CcTblClassroom> list = ccClassService.findclassroom();
		System.out.println("list"+list);
		modelAndView.addObject("classroom",list);

		List<TblWorker> list1 = ccClassService.findtea();
		System.out.println("list1"+list1);
		modelAndView.addObject("teacher",list1);

		return modelAndView;
	}

	/**
	 * 调用幼儿管理页面
	 * by 陈超
	 */
	@RequestMapping("/class.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("class");

		return modelAndView;
	}

	/**
	 * 幼儿管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgclass.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updateclass");

		List<CcTblClassroom> list = ccClassService.findclassroom();
		System.out.println("list"+list);
		modelAndView.addObject("classroom",list);

		List<TblWorker> list1 = ccClassService.findtea();
		System.out.println("list1"+list1);
		modelAndView.addObject("teacher",list1);

		return modelAndView;
	}

	/**
	 * 调用幼儿管理数据显示
	 * by 陈超
	 */
	@RequestMapping("/classxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询宝宝")
	public MSG table(String cname,String starttime, String endtime, int page){

		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setCname(cname);

		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		System.out.println("starttime"+starttime);
		System.out.println("endtime"+endtime);
		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblClassroom> list= ccClassService.find(ccTableInf);
		System.out.println("list"+list.toString());
		for (int i = 0; i <list.size() ; i++)
		{
			System.out.println(list.get(i).getWname());
		}
		List<CcTblClassroom> totalPagelist= ccClassService.totalPage(ccTableInf);
		System.out.println("totalPagelist"+totalPagelist.toString());


		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 幼儿管理--入园信息增加
	 * by 陈超
	 */
	@RequestMapping("/addclass.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加宝宝")
	public MSG add(CcTblClassroom ccTblClassroom,HttpServletRequest request)
	{
		//宝宝
		String cname=request.getParameter("cname");
		String wid=request.getParameter("wid");
		String cid=request.getParameter("cid");
		ccTblClassroom=ccClassService.findclassroom1(Integer.valueOf(cid));
		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String time =sdf.format(date);

		ccTblClassroom.setCourseadddate(time);
		ccTblClassroom.setWid(Integer.valueOf(wid));
		ccTblClassroom.setCname(cname);
		ccTblClassroom.setClassroom(ccTblClassroom.getClassroom());
		int flag =ccClassService.addclass(ccTblClassroom);
		System.out.println(flag);

		MSG msg =new MSG();

		if (flag >0 )
		{
			msg.setMsg("1");
			System.out.println("增加入园信息成功");

		}
		else
		{
			msg.setMsg("2");
			System.out.println("增加入园信息失败");
		}
		return msg;


	}

	/**
	 * 幼儿管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateclass.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改宝宝信息")
	public MSG update(int cid,String cname,int wid,String classroom)
	{
		CcTblClassroom ccTblClassroom =new CcTblClassroom();


		ccTblClassroom.setWid(wid);
		ccTblClassroom.setCname(cname);
		ccTblClassroom.setCid(cid);
		ccTblClassroom.setClassroom(classroom);
		int flag = ccClassService.updateclass(ccTblClassroom);

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
	 * 幼儿管理--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deleteclass.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除宝宝")
	public MSG delete(String cid)
	{
		System.out.println("cid"+cid);
		int msg1= ccClassService.deleteclass(Integer.valueOf(cid));

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
