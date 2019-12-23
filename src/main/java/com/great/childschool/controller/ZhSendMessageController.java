package com.great.childschool.controller;


import com.great.childschool.service.ZhSendMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 消息业务dao层
 * by 张宏
 */
@Controller

public class ZhSendMessageController
{

	@Resource
	private ZhSendMessageService zhSendMessageService;


	//职位人员统计界面显示
	@RequestMapping("/sendNoticeView.action")
	public ModelAndView sendNoticeView()
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("sendnotice");
		return modelAndView;
	}







}
