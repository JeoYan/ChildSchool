package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.TblMenu;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.entity.YjjSearchInfo;
import com.great.childschool.entity.YjjTblReadbook;
import com.great.childschool.service.YjjBookReadService;
import com.great.childschool.service.YjjWorkerLoginService;
import com.great.childschool.tools.RandomValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 亲子阅读控制类
 * by 严俊杰
 */
@Controller
@RequestMapping("/readBook")
public class YjjReadBookController
{

	@Resource
	private YjjBookReadService bookReadService;

	/**
	 * 调用亲子阅读页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("readBookPage.action")
	public String callParentLoginPage()
	{
		return "readbookmanage";
	}

	/**
	 * 获得亲子读物数据显示
	 * by 严俊杰12.20
	 */
	@RequestMapping("/getBookTable.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public YjjTableData table(HttpServletRequest request){
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		String bName = request.getParameter("bName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		System.out.println("---------limit------------" + limit);
		System.out.println("---------page------------" + page);
		System.out.println("---------bName------------" + bName);
		System.out.println("---------startDate------------" + startDate);
		System.out.println("---------endDate------------" + endDate);
		YjjSearchInfo searchInfo = new YjjSearchInfo();
		searchInfo.setLimit(Integer.valueOf(limit));
		searchInfo.setPage(Integer.valueOf(page));
		searchInfo.setbName(bName);
		searchInfo.setStartDate(startDate);
		searchInfo.setEndDate(endDate);
		List<YjjTblReadbook> list = bookReadService.findBook(searchInfo);
		List<YjjTblReadbook> list1 =  bookReadService.totalPage(searchInfo);

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
	 * 登入界面获得验证码
	 * by 严俊杰
	 */


	/**
	 * 登入验证
	 * by 严俊杰
	 */
	//用于保存登入成的worker的实体




	/**
	 * 登入跳转到工作人员菜单主页,携带菜单
	 * by 严俊杰
	 */


	/**
	 * 将或得到的菜单list,转化存在Map中
	 * by 严俊杰
	 */


}
