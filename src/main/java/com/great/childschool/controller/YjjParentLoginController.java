package com.great.childschool.controller;

import com.great.childschool.entity.TblParent;
import com.great.childschool.service.YjjParentLoginService;
import com.great.childschool.tools.RandomValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	/**
	 * 调用家长登入页面
	 * by 严俊杰
	 */
	@RequestMapping("parentLoginPage.action")
	public String callParentLoginPage(){
		return "index";
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
		System.out.println("-----------登入验证------------");
		System.out.println("-----------登入名------------"+uPhone);
		System.out.println("-----------登入密码------------"+passWord);
		System.out.println("-----------登入验证码------------"+verifyCode);
		String result="NotOk";
		//从session中获取随机数
		String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		if(random.equalsIgnoreCase(verifyCode)){
			TblParent parent= parentLoginService.parentLoginCheck(uPhone,passWord);
		if(null!=parent){
			parentSuccess=parent;
			result="Ok";
			}

		}else{
			result="vercodeError";
		}







		return result;

	}


	/**
	 * 登入跳转到家长菜单主页
	 * by 严俊杰
	 */
	@RequestMapping("login.action")
	public ModelAndView login(HttpServletRequest request)
	{
		//登入成功后，将家长的名字和id存在Session中
		request.getSession().setAttribute("pName",parentSuccess.getPname());
		request.getSession().setAttribute("pid",parentSuccess.getPid());
		ModelAndView modelAndView=new ModelAndView();
//		modelAndView.addObject("aname", tbladmin.getAname());
		modelAndView.setViewName("parentmenu");
		return modelAndView;
	}






}
