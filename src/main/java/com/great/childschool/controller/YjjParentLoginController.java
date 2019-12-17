package com.great.childschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端家长登入入口类
 * by 严俊杰
 */
@Controller
@RequestMapping("/parentLogin")
public class YjjParentLoginController
{
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








}
