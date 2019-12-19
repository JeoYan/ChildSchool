package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcTeacherService;
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
public class CcTeacherController
{
	@Resource
	private CcTeacherService ccTeacherService;

	/**
	 * 教师管理-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzteacher.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addteacher");

		List<TblRole> list1 = ccTeacherService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}

	/**
	 * 调用教师管理页面
	 * by 陈超
	 */
	@RequestMapping("/teacher.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("teacher");

		List<TblRole> list1 = ccTeacherService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}

	/**
	 * 教师管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgteacher.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updateteacher");

		List<TblRole> list1 = ccTeacherService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}

	/**
	 * 调用教师管理数据显示
	 * by 陈超
	 */
	@RequestMapping("/teacherxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public MSG table(String wname,String rname,String starttime, String endtime, int page){
		System.out.println(page);
		System.out.println("rname"+rname);
		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setWname(wname);
		ccTableInf.setRname(rname);
		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		ccTableInf.setPage((page-1)*5);
		//调用查询数据方法
		List<TblWorker> list= ccTeacherService.findTableInf(ccTableInf);
		List<TblWorker> totalPagelist= ccTeacherService.totalPage(ccTableInf);

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 教师管理--增加逻辑
	 * by 陈超
	 */

	@RequestMapping("/addteacher.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加教师")
	public MSG add(TblWorker tblWorker,HttpServletRequest request)
	{
		TblWorkerRole tblWorkerRole =new TblWorkerRole();
		String wname=request.getParameter("wname");

		String rid=request.getParameter("rid");
		String wsex =request.getParameter("wsex");
		String wbrith =request.getParameter("wbrith");


		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String time =sdf.format(date);

		tblWorker.setWdate(time);
		tblWorker.setWname(wname);
		tblWorker.setWbrith(wbrith);
		tblWorker.setWsex(wsex);

		int wid =ccTeacherService.addteacher(tblWorker);

		//获得id
		System.out.println("--------wid--------"+wid);
		System.out.println("---------------rid---------------"+rid);

		//获得所有的菜单id

		List<TblMenu> tblMenuList=ccTeacherService.findAllMenu();
		//将所有菜单id和wid绑定，默认sid为4，插入到tbl_menu_role表中
		int i=ccTeacherService.addMenuRole(tblMenuList,wid);
		//获得rid初始对应的菜单id
		List<TblMenu> tblMenuList1=ccTeacherService.findRoleMenu(rid);
		//用获取到的mid更改tbl_menu_role的sid为3
		ccTeacherService.updateMenuRoleSid( tblMenuList1,wid);





		System.out.println("wid"+wid);
		System.out.println("rid"+rid);

		tblWorkerRole.setWid(wid);
		tblWorkerRole.setRid(Integer.valueOf(rid));
		int flag1 =ccTeacherService.addworkerrole(tblWorkerRole);

		MSG msg =new MSG();
		if (flag1>0)
		{
			msg.setMsg("1");
			System.out.println("增加成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("增加失败");
		}
		return msg;
	}

	/**
	 * 教师管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateteacher.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改教师信息")
	public MSG update(int wid,String wname,String rid)
	{
		System.out.println("rid"+rid);
		TblWorker tblWorker =new TblWorker();
		tblWorker.setWid(wid);
		tblWorker.setWname(wname);
//		tblWorker.setRname(rid);

		int flag = ccTeacherService.updateteacher(tblWorker);
		System.out.println("flag"+flag);
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
	 * 教师管理--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deleteteacher.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除教师")
	public MSG deleteteacher(String wid)
	{
		System.out.println("wid"+wid);
		int msg = ccTeacherService.deleteteacher(Integer .valueOf(wid));
		int msg2 =ccTeacherService.deletemenu(Integer .valueOf(wid));
		MSG msg1 =new MSG();
		if (msg >0&msg2 >0)
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
