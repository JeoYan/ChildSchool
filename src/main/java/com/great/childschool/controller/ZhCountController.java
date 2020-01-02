package com.great.childschool.controller;


import com.great.childschool.entity.ZhPostCount;
import com.great.childschool.entity.ZhStudentCount;
import com.great.childschool.entity.ZhTblRole;
import com.great.childschool.service.ZhCountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 园长端统计功能控制层
 * by 张宏
 */
@Controller
public class ZhCountController
{

	@Resource
	private ZhCountService zhCountService;


	//职位人员统计界面显示
	@RequestMapping("/postcountview.action")
	public ModelAndView foodManageView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("postcount");
		return modelAndView;
	}



	//职位人员统计方法
	@RequestMapping("/postcount.action")
	@ResponseBody
	public List<ZhPostCount> postcount(){

		List<ZhPostCount>  postCountList = new ArrayList<ZhPostCount>();

		List<ZhTblRole>  allRoleList = zhCountService.findAllRole();

		for (ZhTblRole zhTblRole : allRoleList)
		{
			ZhPostCount postCount = new ZhPostCount();
			postCount.setName(zhTblRole.getRname());
			int a=zhCountService.findRoleNumber(zhTblRole.getRid());
			System.out.println(a+"---------------a a a a a ----------------");
			postCount.setRolenum(a);
			postCountList.add(postCount);

		}

		return postCountList;
	}


	//学生统计界面
	//职位人员统计界面显示
	@RequestMapping("/studentCountView.action")
	public ModelAndView studentCountView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studentcount");
		return modelAndView;
	}


	//职位人员统计方法
	@RequestMapping("/studentCount.action")
	@ResponseBody
	public List<ZhStudentCount> studentCount(){

		List <ZhStudentCount> studentCountList = zhCountService.findStudentNumber();

		return studentCountList;
	}

}
