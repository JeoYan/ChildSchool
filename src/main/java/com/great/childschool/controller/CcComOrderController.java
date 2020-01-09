package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;

import com.great.childschool.service.CcComOrderService;

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
public class CcComOrderController
{
	@Resource
	private CcComOrderService ccComOrderService;


	/**
	 * 调用订单查询页面
	 * by 陈超
	 */
	@RequestMapping("/commodityorder.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("commodityorder");

		return modelAndView;
	}

	/**
	 * 订单查询-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzcomorder.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addcomorder");

		List<CcTblBaby> list = ccComOrderService.findbid();
		System.out.println("list"+list);
		modelAndView.addObject("baby",list);

		return modelAndView;
	}

	/**
	 * 订单查询-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgcomorder.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updatecomorder");

		List<CcTblBaby> list = ccComOrderService.findbid();
		System.out.println("list"+list);
		modelAndView.addObject("baby",list);

		return modelAndView;
	}

	/**
	 * 调用订单查询数据显示
	 * by 陈超
	 */
	@RequestMapping("/comorderxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "订单查询")
	public MSG table(String comtitle,String starttime, String endtime, int page){

		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setComtitle(comtitle);

		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);

		ccTableInf.setPage((page-1)*5);

		//调用查询数据方法
		List<CcTblOrder> list= ccComOrderService.find(ccTableInf);

		List<CcTblOrder> totalPagelist= ccComOrderService.totalPage(ccTableInf);
		System.out.println("totalPagelist"+totalPagelist.toString());

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 订单查询--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/delete.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除订单")
	public MSG delete(String oid)
	{
		System.out.println("oid"+oid);
		int msg1= ccComOrderService.delete(Integer.valueOf(oid));

		MSG msg =new MSG();
		System.out.println();
		if (msg1 >0)
		{
			msg.setMsg("1");
			System.out.println("删除订单成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("删除订单失败");
		}
		return msg;

	}

	/**
	 * 查询对应的家长
	 * by 陈超
	 */
	@RequestMapping("/getpid.action")
	@Log(operationType = "查询操作", operationName = "查询对应的家长")
	@ResponseBody
	public String   get(int bid){
		System.out.println("bid"+bid);
		int pid=ccComOrderService.findp(bid);
		CcTblParent ccTblParent =ccComOrderService.findpname(pid);
		return ccTblParent.getPname();
	}

	/**
	 * 订单查询--订单查询增加
	 * by 陈超
	 */
	@RequestMapping("/addcomorder.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加订单查询")
	public MSG add(CcTblOrder ccTblOrder, HttpServletRequest request)
	{
		String pname=request.getParameter("pname");
		int pid= ccComOrderService.pid(pname);

		String comtitle=request.getParameter("comtitle");
		CcTblCommodity ccTblCommodity=ccComOrderService.findcomid(comtitle);

		String bid=request.getParameter("bid");
		String address=request.getParameter("address");

		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String time =sdf.format(date);

		ccTblOrder.setComid(ccTblCommodity.getComid());
		ccTblOrder.setBid(Integer.valueOf(bid));
		ccTblOrder.setPid(pid);
		ccTblOrder.setAddress(address);
		ccTblOrder.setOtime(time);

		int flag =ccComOrderService.add(ccTblOrder);

		MSG msg =new MSG();

		if (flag >0 )
		{
			msg.setMsg("1");
			System.out.println("增加订单成功");
		}
		else
		{
			msg.setMsg("2");
			System.out.println("增加订单失败");
		}
		return msg;
	}

	/**
	 * 订单查询--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updatecomorder.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改订单")
	public MSG update(int oid,int bid,String comtitle,String address,String pname)
	{
		int pid= ccComOrderService.pid(pname);

		CcTblCommodity ccTblCommodity=ccComOrderService.findcomid(comtitle);
		int comid=ccTblCommodity.getComid();

		CcTblOrder ccTblOrder=new CcTblOrder();
		ccTblOrder.setComid(comid);
		ccTblOrder.setPid(pid);
		ccTblOrder.setAddress(address);
		ccTblOrder.setBid(bid);
		ccTblOrder.setOid(oid);
		int flag = ccComOrderService.update(ccTblOrder);

		MSG msg =new MSG();

		if (flag >0)
		{
			msg.setMsg("1");
			System.out.println("订单修改成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("订单修改失败");
		}
		return msg;


	}
}
