package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcInformationService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CcInformationController
{
	@Resource
	private CcInformationService ccInformationService;

	/**
	 * 修改密码
	 * by 陈超
	 */
	@RequestMapping("/psw.action")
	@Log(operationType = "访问操作", operationName = "访问修改密码")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("psw");

		return modelAndView;
	}

	/**
	 * 修改密码
	 * by 陈超
	 */
	@RequestMapping("/information.action")
	@Log(operationType = "访问操作", operationName = "访问个人信息")
	public ModelAndView information(HttpServletRequest request){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("information");
		int wid=  (int)request.getSession().getAttribute("wid");

		System.out.println("wid"+wid);
		CcTblInfo ccTblInfo= ccInformationService.findinfo(wid);
		request.getSession().setAttribute("wname",ccTblInfo.getWname());
		request.getSession().setAttribute("wbrith",ccTblInfo.getWbrith());
		request.getSession().setAttribute("rname",ccTblInfo.getRname());
		request.getSession().setAttribute("wsex",ccTblInfo.getWsex());
		request.getSession().setAttribute("wdate",ccTblInfo.getWdate());
		request.getSession().setAttribute("sname",ccTblInfo.getSname());
		request.getSession().setAttribute("wface",ccTblInfo.getWface());
		return modelAndView;
	}

	/**
	 * 修改密码验证
	 * by 陈超
	 */
	@RequestMapping("/workerLogin/pswCheck.action")
	@ResponseBody
	public String loginCheck(int wid,String oldpsw, String newpsw, String newpsw1, TblWorker tblWorker,HttpSession session)
	{
		tblWorker=ccInformationService.findpsw(wid);
		System.out.println("-----------旧密码------------" + oldpsw);
		System.out.println("-----------旧密码------------" + tblWorker.getWpsw());
		System.out.println("-----------新密码------------" + newpsw);
		System.out.println("-----------确认码------------" + newpsw1);

		String result = "NotOk";

		if(oldpsw !=null && newpsw !=null && newpsw1 !=null)
		{
			if (oldpsw.equals(tblWorker.getWpsw()))
			{
				System.out.println("原密码正确");
				if (newpsw.equals(newpsw1))
				{
					System.out.println("密码一致确认修改");
					System.out.println(newpsw);
					tblWorker.setWpsw(newpsw);
					tblWorker.setWid(wid);
					int msg=ccInformationService.updatepsw(tblWorker);
					if (msg>0)
					{
						result = "Ok";
					}
				} else
				{
					System.out.println("新密码输入不一致");
					result = "newpswError";
				}
			} else
			{
				System.out.println("原密码不正确");
				result="NotExist";
			}
		}else{
			System.out.println("密码不能为空");
			result="NotNull";
		}
		return null;
	}

}
