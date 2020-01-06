package com.great.childschool.controller;

import com.great.childschool.entity.CmjTblBaby;
import com.great.childschool.entity.DataBean;
import com.great.childschool.service.CmjClassInfoService;
import com.great.childschool.tools.FaceSpot;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 *@className CmjClassInfoController
 *@description TODO
 *@author MJChen
 *@date 2019/12/18 14:14
 *@version 1.0
 **/
@Controller
@RequestMapping("/ClassInfoController")
public class CmjClassInfoController
{
	@Resource
	private CmjClassInfoService classInfoService;
	/**
	 * @Author MJChen
	 * @Description //跳转到某一界面
	 * @Date 14:51 2019/12/18
	 **/
	@RequestMapping("/query/{url}")
	public String turnToJsp(@PathVariable (value = "url") String path){
		return path;
	}
	/**
	 * @Author MJChen
	 * @Description //查询班级信息
	 * @Date 14:52 2019/12/18
	 **/
	@RequestMapping("/queryClassInfo")
	@ResponseBody
	public DataBean<CmjTblBaby> queryClassInfo(String page,String limit,String beginDate,String endDate){
		int size = Integer.valueOf(limit);
		int curPage = (Integer.valueOf(page)-1)*size;
		DataBean<CmjTblBaby> dataBean= new DataBean();
		List<CmjTblBaby> babyList = classInfoService.queryClassInfo(curPage, size,beginDate,endDate);
		Integer count = classInfoService.countClassInfo(beginDate,endDate);
		dataBean.setCount(count);
		dataBean.setData(babyList);
		return dataBean;
	}
	/**
	 * @Author MJChen
	 * @Description //查询宝宝信息
	 * @Date 14:52 2019/12/18
	 **/
	@RequestMapping("/queryBabyInfo")
	@ResponseBody
	public List<Object> queryBabyInfo(int bid){
		List<Object> babyFamily = classInfoService.findBabyFamily(bid);
		return babyFamily;
	}

	@RequestMapping("/searchFace")
	@ResponseBody
	public String searchFace(String img) {
		System.out.println(img);
		JSONObject js = FaceSpot.searchFace(img, "chen", "cmj");
		System.out.println(js.toString(2));
		return js.toString();
	}
}
