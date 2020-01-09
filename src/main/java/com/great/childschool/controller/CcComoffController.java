package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblCommodity;
import com.great.childschool.entity.MSG;
import com.great.childschool.service.CcComoffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class CcComoffController
{
	@Resource
	private CcComoffService ccComoffService;


	/**
	 * 调用商品管理页面
	 * by 陈超
	 */
	@RequestMapping("/comoff.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("comoff");

		return modelAndView;
	}

	/**
	 * 调用商品下架数据显示
	 * by 陈超
	 */
	@RequestMapping("/comoffxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询商品下架")
	public MSG table( int page){
		System.out.println("page"+page);
		CcTableInf ccTableInf =new CcTableInf();

		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblCommodity> list= ccComoffService.findxj(ccTableInf);

		List<CcTblCommodity> totalPagelist= ccComoffService.totalPagexj(ccTableInf);
		System.out.println("totalPagelist"+totalPagelist.toString());

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}


	/**
	 * 商品管理--下架逻辑
	 * by 陈超
	 */
	@RequestMapping("/deletecommodity.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "商品下架")
	public MSG delete(String comid,String sname)
	{
		int msg1 =0;
		System.out.println("comid"+comid);
		System.out.println("sname"+sname);
		CcTblCommodity ccTblCommodity =new CcTblCommodity();
		ccTblCommodity.setComid(Integer.valueOf(comid));
		if (sname.equals("在售"))
		{
			msg1 = ccComoffService.deletecommodity(ccTblCommodity);
		}
		MSG msg =new MSG();
		System.out.println();
		if (msg1 >0)
		{
			msg.setMsg("1");
			System.out.println("下架成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("下架失败");
		}
		return msg;

	}



}
