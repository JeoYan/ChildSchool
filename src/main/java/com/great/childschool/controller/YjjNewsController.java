package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.entity.YjjSearchInfo;
import com.great.childschool.entity.YjjTableData;
import com.great.childschool.entity.YjjTblNews;
import com.great.childschool.service.YjjNewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

import static com.great.childschool.tools.YjjTools.getDatea;

@Controller
@RequestMapping("/news")
public class YjjNewsController
{
	@Resource
	private YjjNewsService newsService;
	/**
	 * 获得新闻中心页面
	 * @return
	 */
	@RequestMapping("/getNewsPage.action")
	public String getNewsPage(){
		return "newsmanager";
	}

	/**
	 * 获得新增新闻页面
	 * @return
	 */
	@RequestMapping("/addNewsView.action")
	public String addNewsView(){
		return "addnews";
	}

	/**
	 * 获得新增新闻页面
	 * @return
	 */
	@RequestMapping("/updateNewsView.action")
	public String updateNewsView(){
		return "updatenews";
	}



	/**
	 * 调用新闻中心管理数据显示
	 * by 严俊杰12.19
	 */
	@RequestMapping("/findNews.action")
	@ResponseBody
	@Log(operationType = "查询新闻", operationName = "查询新闻")
	public YjjTableData table(HttpServletRequest request){
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String ntitle = request.getParameter("ntitle");
		String worker = request.getParameter("worker");
		System.out.println("---------limit------------" + limit);
		System.out.println("---------page------------" + page);
		System.out.println("---------ntitle------------" + ntitle);


		YjjSearchInfo searchInfo = new YjjSearchInfo();
		searchInfo.setLimit(Integer.valueOf(limit));
		searchInfo.setPage(Integer.valueOf(page));
		searchInfo.setStartDate(startDate);
		searchInfo.setEndDate(endDate);
		searchInfo.setNtitle(ntitle);
		searchInfo.setwName(worker);
		List<YjjTblNews> list = newsService.findNews(searchInfo);
		List<YjjTblNews> list1 =  newsService.totalPage(searchInfo);
		//总条数
		int totalPage = list1.size();
		YjjTableData send = new YjjTableData();
		send.setCode(new BigDecimal(0));
		send.setCount(new BigDecimal(totalPage));
		send.setData(list);
		System.out.println(limit + "limit+----------------+page" + page);
		return send;
	}




	/**
	 * 新增新闻
	 * @return
	 */
	@RequestMapping("/addNews.action")
	@ResponseBody
	public String addNews(HttpServletRequest request){
		String result="NotOk";
		String wid=request.getParameter("wid");
		String ntitle=request.getParameter("ntitle");
		String nconntext=request.getParameter("nconntext");
		YjjTblNews news=new YjjTblNews();
		news.setWid(Integer.valueOf(wid));
		news.setNtitle(ntitle);
		news.setNconntext(nconntext);
		news.setNdate(getDatea());
		int x=newsService.addNews(news);
		if(x>0){
			result="Ok";
		}

		return result;
	}


	/**
	 * 删除新闻
	 * @return
	 */
	@RequestMapping("/deleteNews.action")
	@ResponseBody
	public String deleteNews(HttpServletRequest request){
		String result="NotOk";
		String wid=request.getParameter("wid");
		String nid=request.getParameter("nid");
		int x=newsService.delNews(nid);
		if(x>0){
			result="Ok";
		}

		return result;
	}


	/**
	 * 更新新闻
	 * @return
	 */
	@RequestMapping("/updateNews.action")
	@ResponseBody
	public String updateNews(HttpServletRequest request){
		String result="NotOk";
		String wid=request.getParameter("wid");
		String ntitle=request.getParameter("ntitle");
		String nconntext=request.getParameter("nconntext");
		String nid=request.getParameter("nid");
		YjjTblNews news=new YjjTblNews();
		news.setNid(Integer.valueOf(nid));
		news.setWid(Integer.valueOf(wid));
		news.setNtitle(ntitle);
		news.setNconntext(nconntext);
		news.setNdate(getDatea());
		int x=newsService.updateNews(news);

		if(x>0){
			result="Ok";
		}

		return result;
	}



}
