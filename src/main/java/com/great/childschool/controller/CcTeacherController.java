package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.CcTeacherService;
import com.great.childschool.service.ZhFaceService;
import com.great.childschool.tools.ZhGetTon;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class CcTeacherController
{
	@Resource
	private CcTeacherService ccTeacherService;



	@Resource
	private ZhFaceService zhFaceService;
	private static String accessToken;
	/**
	 * 教师管理-新增弹窗
	 * by 陈超
	 */
	@RequestMapping("/xzteacher.action")
	@Log(operationType = "访问操作", operationName = "访问新增弹窗")
	public ModelAndView xzteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("addteacher");

		List<TblRole> list1 = ccTeacherService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}

	/**
	 * 调用教师管理页面
	 * by 陈超
	 */
	@RequestMapping("/teacher.action")
	@Log(operationType = "访问操作", operationName = "访问欢迎页")
	public ModelAndView handleRequest(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("teacher");

		List<TblRole> list1 = ccTeacherService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}

	/**
	 * 教师管理-修改弹窗
	 * by 陈超
	 */
	@RequestMapping("/xgteacher.action")
	@Log(operationType = "访问操作", operationName = "访问修改弹窗")
	public ModelAndView xgteacher(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("updateteacher");

		List<TblRole> list1 = ccTeacherService.findrole();
		System.out.println("list1"+list1);
		modelAndView.addObject("role",list1);

		return modelAndView;
	}

	/**
	 * 调用教师管理数据显示
	 * by 陈超
	 */
	@RequestMapping("/teacherxs.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public MSG table(String wname,String rname,String starttime, String endtime, int page){

		System.out.println("rname"+rname);
		CcTableInf ccTableInf =new CcTableInf();
		ccTableInf.setWname(wname);
		ccTableInf.setRname(rname);

		ccTableInf.setStarttime(starttime);
		ccTableInf.setEndtime(endtime);
		ccTableInf.setPage((page-1)*5);
		//调用查询数据方法
		List<TblWorker> list= ccTeacherService.findTableInf(ccTableInf);
		List<TblWorker> totalPagelist= ccTeacherService.totalPage(ccTableInf);

		int page1=totalPagelist.size();
		System.out.println("page1"+page1);

		MSG msg=new MSG(0,"",page1,list);

		return msg;
	}

	/**
	 * 教师管理--增加逻辑
	 * by 陈超
	 */

	@RequestMapping("/addteacher.action")
	@ResponseBody
	@Log(operationType = "增加操作", operationName = "添加教师")
	public int add(TblWorker tblWorker,HttpServletRequest request)
	{
		TblWorkerRole tblWorkerRole =new TblWorkerRole();
//		String wname=request.getParameter("wname");
//
//		String rid=request.getParameter("rid");
//		String wsex =request.getParameter("wsex");
//		String wbrith =request.getParameter("wbrith");

		List<ZhTblWorker> users = zhFaceService.findAllWork();
		String base64 = "";
		String base = request.getParameter("wface");

		for (ZhTblWorker user : users) {

			if( user.getWface() != null){



				base64 = new String(user.getWface());
				boolean result = getResult(base, base64);
				System.out.println(result+"-----------555555555-------------");
				if (result==true){
					return 3;
				}else{

					Date date =new Date();
					SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
					String time =sdf.format(date);
					tblWorker.setWdate(time);


					int wid =ccTeacherService.addteacher(tblWorker);

					//获得id
					System.out.println("--------wid--------"+wid);


					//获得所有的菜单id

					List<TblMenu> tblMenuList=ccTeacherService.findAllMenu();
					//将所有菜单id和wid绑定，默认sid为4，插入到tbl_menu_role表中
					int i=ccTeacherService.addMenuRole(tblMenuList,wid);
					//获得rid初始对应的菜单id
					List<TblMenu> tblMenuList1=ccTeacherService.findRoleMenu(String.valueOf(tblWorker.getRid()));
					//用获取到的mid更改tbl_menu_role的sid为3
					ccTeacherService.updateMenuRoleSid( tblMenuList1,wid);

					System.out.println("wid"+wid);
					//		System.out.println("rid"+rid);

					tblWorkerRole.setWid(wid);
					tblWorkerRole.setRid(tblWorker.getRid());
					int flag1 =ccTeacherService.addworkerrole(tblWorkerRole);

					if (flag1>0)
					{
						System.out.println("增加成功");
						return 1;

					} else
					{
						System.out.println("增加失败");
						return 2;
					}
				}


			}


		}

		return 2;

	}

	/**
	 * 教师管理--修改逻辑
	 * by 陈超
	 */
	@RequestMapping("/updateteacher.action")
	@ResponseBody
	@Log(operationType = "更新操作", operationName = "修改教师信息")
	public MSG update(int wid,String wname,int rid)
	{
		System.out.println("rid"+rid);

		TblWorker tblWorker =new TblWorker();
		tblWorker.setWid(wid);
		tblWorker.setWname(wname);

		TblWorkerRole tblWorkerRole = new TblWorkerRole();
		tblWorkerRole.setWid(wid);
		tblWorkerRole.setRid(rid);

		int flag = ccTeacherService.updateteacher(tblWorker);
		int flag1=ccTeacherService.updatername(tblWorkerRole);

		MSG msg =new MSG();
		if (flag >0&&flag1 >0)
		{
			msg.setMsg("1");
			System.out.println("修改成功");
		} else
		{
			msg.setMsg("2");
			System.out.println("修改失败");
		}
		return msg;
	}

	/**
	 * 教师管理--删除逻辑
	 * by 陈超
	 */
	@RequestMapping("/deleteteacher.action")
	@ResponseBody
	@Log(operationType = "删除操作", operationName = "删除教师")
	public MSG deleteteacher(String wid)
	{
		System.out.println("wid"+wid);
		int msg = ccTeacherService.deleteteacher(Integer .valueOf(wid));
		int msg2 =ccTeacherService.deletemenu(Integer .valueOf(wid));
		int msg3 =ccTeacherService.deletewrole(Integer .valueOf(wid));
		MSG msg1 =new MSG();
		if (msg >0&&msg2 >0&&msg3 >0)
		{
			msg1.setMsg("1");
			System.out.println("删除成功");
		} else
		{
			msg1.setMsg("2");
			System.out.println("删除失败");
		}
		return msg1;
	}









	/** 人脸识别 比对 */
	public boolean getResult(String imStr1, String imgStr2) {

		accessToken = ZhGetTon.getToken();
		boolean flag = false;
		BufferedReader br = null;
		String result = "";
		// 定义请求地址
		String mathUrl = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		try {
			//页面抓拍到的人脸
			List<JSONObject> images = new ArrayList<>();
			JSONObject image1 = new JSONObject();
			image1.put("image", imStr1);
			image1.put("image_type", "BASE64");
			image1.put("face_type", "LIVE");
			image1.put("quality_control", "LOW");
			image1.put("liveness_control", "NORMAL");

			//数据库中人脸
			JSONObject image2 = new JSONObject();
			image2.put("image", imgStr2);
			image2.put("image_type", "BASE64");
			image2.put("face_type", "LIVE");
			image2.put("quality_control", "LOW");
			image2.put("liveness_control", "NORMAL");
			images.add(image1);
			images.add(image2);
			// 调用百度云 【人脸对比】接口
			String genrearlURL = mathUrl + "?access_token=" + accessToken;
			// 创建请求对象
			URL url = new URL(genrearlURL);
			// 打开请求链接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置请求方法
			connection.setRequestMethod("POST");
			// 设置通用的请求属性
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// 获得请求输出流对象
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(images.toString());
			// 刷新流
			out.flush();
			// 关闭流
			out.close();
			// 建立实际链接
			connection.connect();
			// 读取URL的响应
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// result ="{"error_msg":"Unsupported openapi method","error_code":3}"
		System.out.println(result);
		JSONObject fromObject = JSONObject.fromObject(result);

		JSONObject jsonArray = fromObject.getJSONObject("result");
		System.out.println(jsonArray+"------------jsonArray-------------------");

		double resultList = jsonArray.getDouble("score");

		if (resultList >= 90) {
			System.out.println("tttt");
			flag = true;

		}

		return flag;
	}





}
