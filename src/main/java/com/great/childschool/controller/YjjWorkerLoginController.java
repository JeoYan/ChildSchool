package com.great.childschool.controller;

import com.great.childschool.entity.TblMenu;
import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.mapper.YjjWorkerLoginMapper;
import com.great.childschool.service.YjjParentLoginService;
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
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端幼儿园工作人员登入入口类
 * by 严俊杰
 */
@Controller
@RequestMapping("/workerLogin")
public class YjjWorkerLoginController
{

	@Resource
	private YjjWorkerLoginService workerLoginService;



	/**
	 * 调用工作人员登入页面
	 * by 严俊杰
	 */
	@RequestMapping("workerLoginPage.action")
	public String callParentLoginPage()
	{
		return "workerlogin";
	}

	/**
	 * 调用登入忘记密码页面
	 * by 严俊杰
	 */
	@RequestMapping("parentForgetPage.action")
	public String parentForgetPage()
	{
		return "forget";
	}

	/**
	 * 登入界面获得验证码
	 * by 严俊杰
	 */
	@RequestMapping("getVerCode.action")
	public void getVerify(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			//设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpeg");
			//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			//输出验证码图片方法
			randomValidateCode.getRandcode(request, response);
		} catch (Exception e)
		{
			System.out.println("获取验证码失败>>>>" + e);
		}
	}

	/**
	 * 登入验证
	 * by 严俊杰
	 */
	//用于保存登入成的worker的实体
	private TblWorker workerSuccess;

	@RequestMapping("loginCheck.action")
	@ResponseBody
	public String loginCheck(String wid, String passWord, String verifyCode, HttpSession session)
	{
		System.out.println("-----------工作人员登入验证------------");
		System.out.println("-----------登入名------------" + wid);
		System.out.println("-----------登入密码------------" + passWord);
		System.out.println("-----------登入验证码------------" + verifyCode);
		String result = "NotOk";
		//从session中获取随机数
		String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		System.out.println("-----------工作人员登入验证码------------" + random);
		//判断验证码是否一致
//		if (random.equalsIgnoreCase(verifyCode))
//		{
			TblWorker worker = workerLoginService.workerLoginCheck(wid, passWord);
			//判断用户是否存在
			if(null!=worker){
				//判断是否被禁用
				if(worker.getSid()==1)
				{
					workerSuccess = worker;
					result = "Ok";
				}else{
					result="StatusLock";
				}
			}else{
				result="NotExist";
			}

//		} else
//		{
//			result = "vercodeError";
//		}
		return result;
	}

	/**
	 * 登入跳转到工作人员菜单主页,携带菜单
	 * by 严俊杰
	 */
	@RequestMapping("login.action")
	public ModelAndView login(HttpServletRequest request)
	{
		//登入成功后，将工作人员的名字和id存在Session中
		request.getSession().setAttribute("wName", workerSuccess.getWname());
		request.getSession().setAttribute("wid", workerSuccess.getWid());
		request.getSession().setAttribute("rid", workerSuccess.getRid());
		ModelAndView modelAndView = new ModelAndView();
		//获得账号对应的菜单
		List<TblMenu> list= workerLoginService.workerMenuFind(String.valueOf(workerSuccess.getWid()));

		Map<String, List<TblMenu>> map=findMenuMap(list);
		modelAndView.addObject("workerMenu",map);
		modelAndView.setViewName("WorkMenu");
		return modelAndView;
	}

	/**
	 * 将或得到的菜单list,转化存在Map中
	 * by 严俊杰
	 */
	public Map<String, List<TblMenu>> findMenuMap(List<TblMenu> lis){

		Map<String, List<TblMenu>> map=new HashMap<String, List<TblMenu>>();

		for (TblMenu tblmenu : lis)
		{
			//判断一级菜单是否存在主键中
			if (map.containsKey(tblmenu.getMname()))
			{
				//如果存在把Tblmenu放里面
				ArrayList<TblMenu> list1 = (ArrayList<TblMenu>) map.get(tblmenu.getMname());
				list1.add(tblmenu);
			}else{
				ArrayList<TblMenu> list1 = new ArrayList<TblMenu>();
				list1.add(tblmenu);
				map.put(tblmenu.getMname(),list1);
			}
		}
		return map;
	}

}
