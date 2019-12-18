package com.great.childschool.controller;



import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.TjzTbTable;
import com.great.childschool.service.TjzBackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/BackAction")
public class TjzAdminHandler
{
	@Resource
	private TjzBackService tjzBackService;

	@RequestMapping("/courseManagement.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "课程管理")
	public TjzTbTable courseManagement(String page, String limit, String startDate, String endDate, String cName)
	{
		TjzTbTable tbBean = tjzBackService.courseManagement(page, limit, startDate, endDate, cName);
		return tbBean;
	}

	@RequestMapping("/findLog.action")
	@ResponseBody
	public TjzTbTable FindLog(String page, String limit, String startDate, String endDate, String userName)
	{
		TjzTbTable tbBean = tjzBackService.showLogTable(page, limit, startDate, endDate, userName);
		return tbBean;
	}





}
