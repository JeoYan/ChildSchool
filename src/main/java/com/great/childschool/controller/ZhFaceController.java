package com.great.childschool.controller;


import com.great.childschool.entity.TblMenu;
import com.great.childschool.entity.ZhTblWorker;
import com.great.childschool.service.YjjWorkerLoginService;
import com.great.childschool.service.ZhFaceService;
import com.great.childschool.tools.ZhGetTon;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ZhFaceController
{
	@Resource
	private ZhFaceService zhFaceService;
	private static String accessToken;
	//菜单
	@Resource
	private YjjWorkerLoginService workerLoginService;

	//用于保存登入成的worker的实体
	private    ZhTblWorker workerSuccess;




	//	//获得账号对应的菜单
//	List<TblMenu> list= workerLoginService.workerMenuFind(String.valueOf(workerSuccess.getWid()));
//
//	Map<String, List<TblMenu>> map=findMenuMap(list);
//		modelAndView.addObject("workerMenu",map);
//		modelAndView.setViewName("WorkMenu");


	//人脸识别界面
	@RequestMapping("/faceWorkerLoginView.action")
	public ModelAndView faceWorkerLoginView(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("faceworkerlogin");
		return modelAndView;
	}


	//后台人脸识别的登录
	@RequestMapping("/facelogin.action")
	@ResponseBody
	public String onListStudent(HttpServletRequest request,
	                                  HttpServletResponse response, HttpSession httpSession) {

		ModelAndView modelAndView =new ModelAndView();

		String type;
		// 获取前端页面传过来的参数
		String base = request.getParameter("base");
		System.out.println(base+"------------base---------------");


		try {
			ZhTblWorker u = new ZhTblWorker();

			u.setWface(base.getBytes());


			//把前端抓取到的图片保存到数据库
			//				      adminLoginService.save(u);
			List<ZhTblWorker> users = zhFaceService.findAllWork();
			String base64 = "";
			PrintWriter writer = response.getWriter();
			response.reset();
			for (ZhTblWorker user : users) {

				if( user.getWface() != null){

					base64 = new String(user.getWface());
					boolean result = getResult(base, base64);
					System.out.println(result+"-------------result-------------");
					if (result==true) {

						if(user.getSid()==1)
						{
							System.out.println("---------------登录成功----------------");
//							modelAndView.setViewName("WorkMenu");

							workerSuccess=user;
							return "OK";

						}else{
							System.out.println("---------------被禁用----------------");
							return "StatusLock";
						}

					}

				}


			}
		} catch (Exception e) {
			e.printStackTrace();
			//			return "redirect:/page/404.jsp";
		}

		return "notok";

	}



	@RequestMapping("workfacelogin.action")
	public ModelAndView login(HttpServletRequest request)
	{
		//登入成功后，将工作人员的名字和id存在Session中
		request.getSession().setAttribute("wName", workerSuccess.getWname());
		request.getSession().setAttribute("wid", workerSuccess.getWid());
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
	 *
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




	//人脸识别界面
	@RequestMapping("/AddWorker.action")
	public ModelAndView addView()
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("AddWorker");
		return modelAndView;
	}



////	//添加后台管理员
//	@RequestMapping("/addface")
//	@ResponseBody
//	public int AddWorker(ZhTblWorker worker){
//
//		int i= zhFaceService.save(worker);
//
//		return i;
//
//	}









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

		double resultList = jsonArray.getDouble("score");

		if (resultList >= 90) {
			System.out.println("tttt");
			flag = true;

		}

		return flag;
	}






}
