//package com.great.childschool.controller;
//
//
//import com.great.childschool.aoplog.Log;
//import com.great.childschool.entity.TblWorker;
//import com.great.childschool.service.CcFaceService;
//import com.great.childschool.tools.CcGetTon;
// import net.sf.json.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//public class CcFaceController
//{
//	@Resource
//	private CcFaceService ccFaceService;
//	private static String accessToken;
//
//	/**
//	 * 幼儿管理-新增弹窗
//	 * by 陈超
//	 */
//	@RequestMapping("/face.action")
//	@Log(operationType = "访问操作", operationName = "访问考勤弹窗")
//	public ModelAndView xzteacher(){
//		ModelAndView modelAndView =new ModelAndView();
//		modelAndView.setViewName("Ccface");
//
//		return modelAndView;
//	}
//
//
//	//后台人脸识别的登录
//	@RequestMapping("/facelogin11111.action")
//	@ResponseBody
//	public int onListStudent(HttpServletRequest request,
//	                            HttpServletResponse response, HttpSession httpSession) {
//		// 获取前端页面传过来的参数
//		String base = request.getParameter("base");
//		System.out.println(base+"-----------------base");
//		try {
////			TblWorker u =new TblWorker();
////			u.setWface(base.getBytes());
//			//把前端抓取到的图片保存到数据库
//			//				      adminLoginService.save(u);
//			List<TblWorker> users = ccFaceService.selectAllWorkers();
//			String base64 = "";
//			PrintWriter writer = response.getWriter();
//			response.reset();
//			for (TblWorker user : users) {
//				if(user.getWface()!=null)
//				{
//					base64 = new String(user.getWface());
//					System.out.println("base64--------------"+base64);
//					boolean result = getResult(base, base64);
//					System.out.println("result-----------"+result);
//					if (result == true)
//					{
//
//
//						int flag = ccFaceService.update(user.getWid());
//						return flag;
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			//			return "redirect:/page/404.jsp";
//		}
//
//		return 0;
//	}
//
//	/** 人脸识别 比对 */
//	public boolean getResult(String imStr1, String imgStr2) {
//
//		accessToken = CcGetTon.getToken();
//		boolean flag = false;
//		BufferedReader br = null;
//		String result = "";
//		// 定义请求地址
//		String mathUrl = "https://aip.baidubce.com/rest/2.0/face/v3/match";
//		try {
//			//页面抓拍到的人脸
//			List<JSONObject> images = new ArrayList<>();
//			JSONObject image1 = new JSONObject();
//			image1.put("image", imStr1);
//			image1.put("image_type", "BASE64");
//			image1.put("face_type", "LIVE");
//			image1.put("quality_control", "LOW");
//			image1.put("liveness_control", "NORMAL");
//
//			//数据库中人脸
//			JSONObject image2 = new JSONObject();
//			image2.put("image", imgStr2);
//			image2.put("image_type", "BASE64");
//			image2.put("face_type", "LIVE");
//			image2.put("quality_control", "LOW");
//			image2.put("liveness_control", "NORMAL");
//			images.add(image1);
//			images.add(image2);
//			// 调用百度云 【人脸对比】接口
//			String genrearlURL = mathUrl + "?access_token=" + accessToken;
//			// 创建请求对象
//			URL url = new URL(genrearlURL);
//			// 打开请求链接
//			HttpURLConnection connection = (HttpURLConnection) url
//					.openConnection();
//			// 设置请求方法
//			connection.setRequestMethod("POST");
//			// 设置通用的请求属性
//			connection.setRequestProperty("Content-Type",
//					"application/json");
//			connection.setRequestProperty("Connection", "Keep-Alive");
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//			// 获得请求输出流对象
//			DataOutputStream out = new DataOutputStream(
//					connection.getOutputStream());
//			out.writeBytes(images.toString());
//			// 刷新流
//			out.flush();
//			// 关闭流
//			out.close();
//			// 建立实际链接
//			connection.connect();
//			// 读取URL的响应
//			br = new BufferedReader(new InputStreamReader(
//					connection.getInputStream()));
//			String line = "";
//			while ((line = br.readLine()) != null) {
//				result += line;
//			}
//			br.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// result ="{"error_msg":"Unsupported openapi method","error_code":3}"
////		System.out.println(result);
//		JSONObject fromObject = JSONObject.fromObject(result);
//
//		JSONObject jsonArray = fromObject.getJSONObject("result");
//
//		double resultList = jsonArray.getDouble("score");
//		if (resultList >= 90) {
//			System.out.println("tttt");
//			flag = true;
//
//		}
//		return flag;
//	}
//
//
//}
