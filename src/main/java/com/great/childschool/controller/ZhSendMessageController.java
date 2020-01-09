package com.great.childschool.controller;


import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.ZhSend;
import com.great.childschool.entity.ZhTblNotice;
import com.great.childschool.service.ZhSendMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 消息业务Controller层
 * by 张宏
 */
@Controller

public class ZhSendMessageController
{

	@Resource
	private ZhSendMessageService zhSendMessageService;


	//教师端发送公告界面显示
	@RequestMapping("/sendNoticeView.action")
	public ModelAndView sendNoticeView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();


		modelAndView.setViewName("sendnotice");
		return modelAndView;
	}

	//教师发送公告
	@RequestMapping("/addNotice.action")
	@ResponseBody
	public int addNotice( ZhTblNotice zhTblNotice){
		//时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取时间
		String s = simpleDateFormat.format(date);
		zhTblNotice.setNdate(s);

		int num =zhSendMessageService.addNotice(zhTblNotice);

		return num;
	}


	//园长端公告界面显示
	@RequestMapping("/noticeManageView.action")
	public ModelAndView noticeManageView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("noticemanage");
		return modelAndView;
	}

	//园长端添加公告界面显示
	@RequestMapping("/addNoticeView.action")
	public ModelAndView addNoticeView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addnotice");
		return modelAndView;
	}

	//园长端修改公告界面显示
	@RequestMapping("/updateNoticeView.action")
	public ModelAndView updateNoticeView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();


		modelAndView.setViewName("updatenotice");
		return modelAndView;
	}


	//园长端消息管理表格
	//表格
	@RequestMapping("/findNoticelManage.action")
	@ResponseBody
	public ZhSend findNoticelManage(ZhTblNotice zhTblNotice, String page)
	{
		System.out.println(zhTblNotice.getNotitle()+"--------------getNotitle--------------");
		//发送表格实体类
		ZhSend zhSend = new ZhSend();

		zhTblNotice.setPage((Integer.valueOf(page) - 1) * 5);

		List<ZhTblNotice> list = zhSendMessageService.findNoticelManage(zhTblNotice);

		List<ZhTblNotice> pageList = zhSendMessageService.totalPageNoticelManage(zhTblNotice);

		int totalPage = pageList.size();

		zhSend.setCode(new BigDecimal(0));

		zhSend.setCount(new BigDecimal(totalPage));

		zhSend.setData(list);


		return zhSend;
	}



	//修改公告信息
	@RequestMapping("/updateNotice.action")
	@ResponseBody
	public int updateNotice(ZhTblNotice zhTblNotice){

		int num =zhSendMessageService.updateNotice(zhTblNotice);
		return num;
	}

	//删除公告信息
	@RequestMapping("/deleteNoticel.action")
	@ResponseBody
	public int deleteNoticel(ZhTblNotice zhTblNotice){

		int num =zhSendMessageService.deleteNotice(zhTblNotice);

		return num;
	}


}
