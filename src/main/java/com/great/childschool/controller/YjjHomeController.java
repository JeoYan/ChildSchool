package com.great.childschool.controller;

import com.great.childschool.entity.YjjTblNews;
import com.great.childschool.entity.YjjTblNotice;
import com.great.childschool.mapper.YjjHomeMapper;
import com.great.childschool.service.YjjHomeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class YjjHomeController
{

	@Resource
	private YjjHomeService homeService;
	/**
	 * 主页
	 *
	 */
	@RequestMapping("/home.action")
	public ModelAndView homePage(){
		List<YjjTblNotice> noticeList =homeService.findNotice();
		List<YjjTblNews> newsList=homeService.findNews();
		ModelAndView ma=new ModelAndView();
		ma.addObject("noticeList",noticeList);
		ma.addObject("newsList",newsList);
		ma.setViewName("/home/index");
		return ma;

	}

	@RequestMapping("/about.action")
	public ModelAndView aboutPage(){
		ModelAndView ma=new ModelAndView();
		ma.setViewName("/home/about");
		return ma;

	}

	@RequestMapping("/teach.action")
	public ModelAndView teachPage(){
		ModelAndView ma=new ModelAndView();
		ma.setViewName("/home/teach");
		return ma;

	}


	@RequestMapping("/event.action")
	public ModelAndView eventPage(){
		ModelAndView ma=new ModelAndView();
		ma.setViewName("/home/event");
		return ma;

	}


	@RequestMapping("/form.action")
	public ModelAndView formPage(){
		ModelAndView ma=new ModelAndView();
		ma.setViewName("/home/form");
		return ma;

	}

	@RequestMapping("/contact.action")
	public ModelAndView contactPage(){
		ModelAndView ma=new ModelAndView();
		ma.setViewName("/home/contact");
		return ma;

	}

	@RequestMapping("/map.action")
	public ModelAndView mapPage(){
		ModelAndView ma=new ModelAndView();
		ma.setViewName("/home/map");
		return ma;
	}

	@RequestMapping("/notice_detail.action")
	public ModelAndView notice_detail(@Param("noid")String noid){
		System.out.println("----------noid-------------"+noid);
		YjjTblNotice notice=homeService.getNotice(noid);
		ModelAndView ma=new ModelAndView();
		ma.addObject("notice",notice);
		ma.setViewName("/home/notice_detail");
		return ma;
	}


	@RequestMapping("/news_detail.action")
	public ModelAndView news_detail(@Param("nid")String nid){
		System.out.println("----------nid-------------"+nid);
		YjjTblNews news=homeService.getNews(nid);
		ModelAndView ma=new ModelAndView();
		ma.addObject("news",news);
		ma.setViewName("/home/news_detail");
		return ma;
	}


	@RequestMapping("/notice_more.action")
	public ModelAndView notice_more(){
		List<YjjTblNotice> noticeList=homeService.findNotice();
		ModelAndView ma=new ModelAndView();
		ma.addObject("noticeList",noticeList);
		ma.setViewName("/home/notice_more");
		return ma;

	}

	@RequestMapping("/news_more.action")
	public ModelAndView news_more(){
		List<YjjTblNews> newsList=homeService.findNews();
		ModelAndView ma=new ModelAndView();
		ma.addObject("newsList",newsList);
		ma.setViewName("/home/news_more");
		return ma;

	}



}
