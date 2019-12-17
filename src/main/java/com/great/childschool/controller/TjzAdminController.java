package com.great.childschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TjzAdminController
{
	@RequestMapping("/web/{url}/{url1}/{url2}")
	public String matchUrl(@PathVariable(value = "url") String path, @PathVariable(value = "url1") String url1, @PathVariable(value = "url2") String url12)
	{
		System.out.println(path);
		return path + "/" + url1 + "/" + url12;

	}

	@RequestMapping("/web/{url}/{url1}")
	public String matchUrl(@PathVariable(value = "url") String path, @PathVariable(value = "url1") String url1)
	{
		System.out.println(path);
		return path + "/" + url1;

	}

	@RequestMapping("/web/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		System.out.println(path);
		return path;

	}


}