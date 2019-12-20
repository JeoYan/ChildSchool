package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.YjjWorkerPowerControlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 前端幼儿园工作人员登入入口类
 * by 严俊杰
 */
@Controller
@RequestMapping("/power")
public class YjjWorkerPowerController
{

	@Resource
	private YjjWorkerPowerControlService workerPowerControlService;

	/**
	 * 调用工作人员登入页面
	 * by 严俊杰
	 */
	@RequestMapping("powerPage.action")
	public String callParentLoginPage()
	{
		return "powercontrol";
	}


	/**
	 * 调用教师管理数据显示
	 * by 严俊杰12.19
	 */
	@RequestMapping("/getTeacherTable.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public YjjTableData table(HttpServletRequest request){
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		String wName = request.getParameter("wName");
		String rid = request.getParameter("rid");
		System.out.println("---------limit------------" + limit);
		System.out.println("---------page------------" + page);
		System.out.println("---------wName------------" + wName);
		System.out.println("---------rid------------" + rid);
		YjjSearchInfo searchInfo = new YjjSearchInfo();
		searchInfo.setLimit(Integer.valueOf(limit));
		searchInfo.setPage(Integer.valueOf(page));
		searchInfo.setRid(rid);
		searchInfo.setwName(wName);
		List<TblWorker> list = workerPowerControlService.findWorker(searchInfo);
		List<TblWorker> list1 =  workerPowerControlService.totalPage(searchInfo);
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
	 * 职工权限管理之禁用启用
	 * by 严俊杰12.19
	 */
	@RequestMapping("/updateStatus.action")
	@ResponseBody
	@Log(operationType = "2", operationName = "禁用启用")
	public String updateStatus(String wid, String statusCode){
		System.out.println("------wid--------"+wid);
		System.out.println("------statusCode--------"+statusCode);

		String result="NotOK";
		int i = workerPowerControlService.updateStatus(wid, statusCode);
		if (i > 0)
		{
			result = "Ok";
		}
		return result;
	}


	/**
	 * 职工权限管理之禁用启用
	 * by 严俊杰12.19
	 */
	@RequestMapping("/callPowerPage.action")
	@Log(operationType = "1", operationName = "禁用启用")
	public String callPowerPage(){
		return "powertree";
	}


	/**
	 * 职工权限管理之获得wid对应的菜单
	 * by 严俊杰12.19
	 */
	//获得用户已拥有的菜单
	@RequestMapping("/getMenu.action")
	@ResponseBody
	public List<YjjTreeData> getMenu(String wid)
	{
		//获得一级菜单得到
		List<TblMenu> firstMenuList = workerPowerControlService.findFirstMenu(Integer.valueOf(wid));
		List<YjjTreeData> list=new ArrayList<>();

		if(firstMenuList!=null)
		{
			for (TblMenu tblMenu : firstMenuList)
			{
				YjjTreeData treeSend=new YjjTreeData();
				//获得wid 和mid对应的sid状态
				YjjTblMenuRole tblmenuowner= workerPowerControlService.findMenuStatus(Integer.valueOf(wid),tblMenu.getMid());
				//判断菜单有没有子菜单
				List<TblMenu> childMyMenu = workerPowerControlService.findChildMenu(Integer.valueOf(wid),tblmenuowner.getMid());
				if(childMyMenu.size()==0){
					if(tblmenuowner.getSid()==3){
						treeSend.setChecked(true);
					}
				}
				treeSend.setTitle(tblMenu.getMname());
				treeSend.setId(tblMenu.getMid());
				treeSend.setChildren(findChild(Integer.valueOf(wid),tblMenu.getMid()));
				treeSend.toString();
				list.add(treeSend);
			}
		}
		return list;
	}

	//获得子菜单 严俊杰12.19
	public List<YjjTreeData> findChild(int wid,int mid){
		List<YjjTreeData> list=new ArrayList<>();
		//查询获得子菜单
		List<TblMenu> childMyMenu = workerPowerControlService.findChildMenu(wid,mid);
		if(childMyMenu!=null)
		{
			for (TblMenu childMenu : childMyMenu)
			{
				YjjTreeData treeSend=new YjjTreeData();
				YjjTblMenuRole tblmenuowner= workerPowerControlService.findMenuStatus(wid, childMenu.getMid());
				//判断菜单有没有子菜单
				List<TblMenu> childMyMenu1 = workerPowerControlService.findChildMenu(wid,tblmenuowner.getMid());
				if(childMyMenu1.size()==0){
					if(tblmenuowner.getSid()==3){
						treeSend.setChecked(true);
					}
				}
				treeSend.setTitle(childMenu.getMname());
				treeSend.setId(childMenu.getMid());
				treeSend.setChildren(findChild(wid,childMenu.getMid()));
				list.add(treeSend);
			}
		}
		return list;
	}


	@RequestMapping("/updateTree.action")
	@ResponseBody
	public String updateTree(String wid){

		return "ok";
	}





}
