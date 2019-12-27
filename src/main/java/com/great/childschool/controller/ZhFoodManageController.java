package com.great.childschool.controller;


import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.TblFood;
import com.great.childschool.entity.ZhSend;
import com.great.childschool.service.ZhFoodManageService;
import com.great.childschool.service.ZhMedicalManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 膳食控制层
 * by 张宏
 */
@Controller

public class ZhFoodManageController
{
	@Resource
	private ZhFoodManageService zhFoodManageService;

	@Resource
	private ZhMedicalManageService zhMedicalManageService;
	/**
	 * 保健端膳食
	 *
	 */

	//膳食界面显示
		@RequestMapping("/foodmanageview.action")
	public ModelAndView foodManageView()
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("foodmanage");

		return modelAndView;
	}


	//添加膳食界面显示
	@RequestMapping("/aadfoodview.action")
	public ModelAndView addfoodView()
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("addfood");

		return modelAndView;
	}

	//添加膳食界面显示
	@RequestMapping("/updatefoodview.action")
	public ModelAndView updatefoodView()
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("updatefood");

		return modelAndView;
	}

	//添加膳食
	@RequestMapping("/addfood.action")
	@ResponseBody
	public int addFood(TblFood tblFood){
		int num;
		TblFood newTblFood=zhFoodManageService.findFood(tblFood);
		if (newTblFood == null){
			num =zhFoodManageService.addFood(tblFood);
		}else {
			num=0;
		}

		return num;
	}

	//保健端膳食管理表格
	@RequestMapping("/findFoodManage.action")
	@ResponseBody
	public ZhSend findFoodManage(TblFood tblFood, String page)
	{

		//发送表格实体类
		ZhSend zhSend = new ZhSend();

		tblFood.setPage((Integer.valueOf(page) - 1) * 5);

		List<TblFood> list = zhFoodManageService.findFoodManage(tblFood);


		List<TblFood> pageList = zhFoodManageService.totalPageFoodManage(tblFood);

		int totalPage = pageList.size();

		zhSend.setCode(new BigDecimal(0));

		zhSend.setCount(new BigDecimal(totalPage));

		zhSend.setData(list);

		return zhSend;
	}

	//修改膳食
	@RequestMapping("/updateFood.action")
	@ResponseBody
	public int updateFood(TblFood tblFood){

		int num =zhFoodManageService.updateFood(tblFood);

		return num;
	}

	//删除体检信息
	@RequestMapping("/deleteFood.action")
	@ResponseBody
	public int deleteFood(TblFood tblFood){

		int num =zhFoodManageService.deleteFood(tblFood);

		return num;
	}


	/**
	 * 家长端膳食信息
	 *
	 */
	//家长端膳食信息界面
	@RequestMapping("/foodCaseView.action")
	public ModelAndView foodCaseView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		TblChecklist tblChecklist = new TblChecklist();
		tblChecklist.setPid(Long.valueOf(String.valueOf(request.getSession().getAttribute("pid"))).longValue());
		List<TblChecklist> pageList = zhMedicalManageService.totalPageMedicalCase(tblChecklist);
		modelAndView.addObject("bybyname", pageList.get(0).getBname());
		modelAndView.addObject("bybysex", pageList.get(0).getBsex());
		modelAndView.setViewName("foodcase");
		return modelAndView;
	}


}
