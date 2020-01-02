package com.great.childschool.controller;

import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.YjjTblBookPage;
import com.great.childschool.service.YjjParentLoginService;
import com.great.childschool.tools.RandomValidateCodeUtil;
import com.great.childschool.tools.YjjMessageSendTool;
import com.great.childschool.websocketdemo.WebSocketServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 前端家长登入入口类
 * by 严俊杰
 */
@Controller
@RequestMapping("/parentLogin")
public class YjjParentLoginController
{

	@Resource
	private YjjParentLoginService parentLoginService;


	private static Map<String, Session> tempSessionMapParent;

	/**
	 * 调用家长登入页面
	 * by 严俊杰
	 */
	@RequestMapping("parentLoginPage.action")
	public String callParentLoginPage(){
		return "index";
	}

	/**
	 * 调用家长注册页面
	 * by 严俊杰
	 */
	@RequestMapping("parentRegister.action")
	public String parentRegister(){
		return "parentregister";
	}


	/**
	 * 调用家长登入忘记密码页面
	 * by 严俊杰
	 */
	@RequestMapping("parentForgetPage.action")
	public String parentForgetPage(){
		return "forget";
	}

	/**
	 * 登入界面获得验证码
	 * by 严俊杰
	 */
	@RequestMapping("getVerCode.action")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			//设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpeg");
			//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			//			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();

			//输出验证码图片方法
			randomValidateCode.getRandcode(request, response);
		} catch (Exception e) {
			System.out.println("获取验证码失败>>>>"+e);
		}
	}

	/**
	 * 登入验证
	 * by 严俊杰
	 */
	//用于保存登入成的parent实体
	private TblParent parentSuccess;
	@RequestMapping("loginCheck.action")
	@ResponseBody
	public String loginCheck(String uPhone,String passWord,String verifyCode,HttpSession session)
	{
		System.out.println("-----------家长登入验证------------");
		System.out.println("-----------登入名------------"+uPhone);
		System.out.println("-----------登入密码------------"+passWord);
		System.out.println("-----------登入验证码------------"+verifyCode);
		String result="NotOk";
		//从session中获取随机数
		String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		System.out.println("-----------家长登入验证码------------"+random);
//		if(random.equalsIgnoreCase(verifyCode)){
			TblParent parent= parentLoginService.parentLoginCheck(uPhone,passWord);

			if(null!=parent){
				if(parent.getSid()==1)
				{
					parentSuccess = parent;
					result = "Ok";
				}else{
					result="StatusLock";
				}
			}else{
				result="NotExist";
			}
//		}else{
//			result="vercodeError";
//		}
		return result;
	}
	/**
	 * 登入跳转到家长菜单主页
	 * by 严俊杰
	 */
	@RequestMapping("/myIframe.action")
	public ModelAndView myIframe(HttpServletRequest request)
	{

		ModelAndView modelAndView=new ModelAndView();
		//		modelAndView.addObject("aname", tbladmin.getAname());
		modelAndView.setViewName("myiframe");
		return modelAndView;
	}



	@RequestMapping("login.action")
	public ModelAndView login(HttpServletRequest request)
	{
		//登入成功后，将家长的名字和id存在Session中
		request.getSession().setAttribute("pName",parentSuccess.getPname());
		request.getSession().setAttribute("pid",parentSuccess.getPid());

		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("pName",parentSuccess.getPname());
		modelAndView.addObject("pid",parentSuccess.getPid());
		modelAndView.setViewName("parentmenu");
		return modelAndView;
	}

	//忘记密码页面---获得验证码
	private String phoneVcode=null;
	@RequestMapping("getPhoneVcode.action")
	@ResponseBody
	public String getPhoneVcode(@Param("phone") String phone){
		System.out.println("-----phone--------------"+phone);
		phoneVcode= YjjMessageSendTool.sendMsg(phone);
		System.out.println("-----phoneVcode--------------"+phoneVcode);
		return phoneVcode;
	}



	//忘记密码页面---密码找回
	@RequestMapping("getPsw.action")
	@ResponseBody
	public String getPsw(String phone,String msgCode){
		String result="NotOk";
		System.out.println("-----phone--------------"+phone);
		System.out.println("-----msgCode--------------"+msgCode);
		if(phoneVcode!=null){
			if(phoneVcode.equals(msgCode)){
			TblParent parent=parentLoginService.getPsw(phone);
			if(parent!=null)
			{
				result = parent.getPpsw();
			}else{
				result = "parentNotExist";
			}

			}else{
				result="msgError";
			}
		}

		return result;
	}













	/**-------------------------------------------------------
	 *--------------------------------亲子阅读部分---------------
	 */

	@RequestMapping("readBookPage.action")
	public ModelAndView readBookPage(){
		ModelAndView modelAndView=new ModelAndView();
		//获得所有书籍第一页信息
		List<YjjTblBookPage> list= parentLoginService.getBookFirstPage();
		modelAndView.addObject("dataList",list);
		modelAndView.setViewName("readbook");
		return modelAndView;
	}















}
