package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.CcTblBaby;
import com.great.childschool.entity.CcTblBabySign;
import com.great.childschool.entity.CcTblWorkerSign;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.service.CcFaceService;
import com.great.childschool.tools.CcGetTon;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class CcFaceController
{
	@Resource
	private CcFaceService ccFaceService;
	private static String accessToken;

	/**
	 * 宝宝人脸录入
	 * by 陈超
	 */
	@RequestMapping("/babyfaceentry.action")
	@Log(operationType = "访问操作", operationName = "访问人脸录入弹窗")
	public ModelAndView babyfaceentry(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("Ccbabyfaceentry");

		return modelAndView;
	}

	/**
	 * 职工人脸识别考勤
	 * by 陈超
	 */
	@RequestMapping("/face.action")
	@Log(operationType = "访问操作", operationName = "访问考勤弹窗")
	public ModelAndView face(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("Ccface");

		return modelAndView;
	}
	/**
	 * 宝宝人脸识别考勤
	 * by 陈超
	 */
	@RequestMapping("/babyface.action")
	@Log(operationType = "访问操作", operationName = "访问考勤弹窗")
	public ModelAndView babyface(){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("Ccbabyface");

		return modelAndView;
	}

	//宝宝人脸识别考勤
	@RequestMapping("/babyfaceatt.action")
	@ResponseBody
	public int babyfaceatt(HttpServletRequest request,
	                         HttpServletResponse response, HttpSession httpSession) {
		try
		{
			// 获取前端页面传过来的参数
			int sid =0;
			String bsperiod =null;
			String bid= request.getParameter("bid");
			String base = request.getParameter("base");

			Date date =new Date();
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String d =sdf.format(date);
			String[] split = d.split(" ");
			String bsdate=split[0];
			String bstime=split[1];

			String adate3= bsdate+" "+"8:30";
			String adate4= bsdate+" "+"12:00";

			String pdate3= bsdate+" "+"14:00";
			String pdate4= bsdate+" "+"17:30";

			Date adate1= sdf.parse(adate3);
			Date adate2= sdf.parse(adate4);

			Date pdate1= sdf.parse(pdate3);
			Date pdate2= sdf.parse(pdate4);
			if (date.getTime() != adate1.getTime())
			{
				if (date.getTime() > adate1.getTime() && date.getTime() < adate2.getTime())
				{
					bsperiod = "上午";
					sid = 6;

				}
			} else
			{
				bsperiod = "上午";
				sid = 5;
			}
			if (date.getTime() != pdate1.getTime())
			{
				if (date.getTime() > pdate1.getTime() && date.getTime() < pdate2.getTime())
				{
					bsperiod = "下午";
					sid = 6;

				}
			}
			else {
				bsperiod="下午";
				sid = 5;
			}

			CcTblBabySign ccTblBabySign =new CcTblBabySign();
			ccTblBabySign.setBid(Integer.valueOf(bid));
			ccTblBabySign.setSid(sid);
			ccTblBabySign.setBsdate(bsdate);
			ccTblBabySign.setBstime(bstime);
			ccTblBabySign.setBsperiod(bsperiod);

			List<CcTblBaby> babys = ccFaceService.selectAllBaby();
			String base64 = "";
			PrintWriter writer = response.getWriter();
			response.reset();
			for (CcTblBaby baby : babys) {
				if(baby.getBface()!=null)
				{
					base64 = new String(baby.getBface());
					System.out.println("base64--------------"+base64);
					boolean result = getResult(base, base64);
					System.out.println("result-----------"+result);
					if (result == true)
					{
						int f =ccFaceService.add1(ccTblBabySign);
						return f;
					}
				}
			}

		} catch (ParseException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return 0;
	}

	//职工人脸识别考勤
	@RequestMapping("/faceattendence.action")
	@ResponseBody
	public int onListStudent(HttpServletRequest request,
	                            HttpServletResponse response, HttpSession httpSession) {

		try
		{
		// 获取前端页面传过来的参数
		int wsid =0;
		String wsperiod =null;
		String wid= request.getParameter("wid");
		String base = request.getParameter("base");
		System.out.println(wid+"-----------------wid");
		System.out.println(base+"-----------------base");

		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String d =sdf.format(date);
		String[] split = d.split(" ");
		String wsdate=split[0];
		String wstime=split[1];
		System.out.println("wsdate---------"+wsdate);
		System.out.println("wstime----------"+wstime);
		String adate3= wsdate+" "+"8:30";
		String adate4= wsdate+" "+"12:00";

		String pdate3= wsdate+" "+"14:00";
		String pdate4= wsdate+" "+"17:30";

			Date adate1= sdf.parse(adate3);
			Date adate2= sdf.parse(adate4);

			Date pdate1= sdf.parse(pdate3);
			Date pdate2= sdf.parse(pdate4);
			if (date.getTime() != adate1.getTime())
			{
				if (date.getTime() > adate1.getTime() && date.getTime() < adate2.getTime())
				{
					wsperiod = "上午";
					wsid = 6;

				}
			} else
			{
				wsperiod = "上午";
				wsid = 5;
			}
			if (date.getTime() != pdate1.getTime())
			{
				if (date.getTime() > pdate1.getTime() && date.getTime() < pdate2.getTime())
				{
					wsperiod = "下午";
					wsid = 6;

				}
			}
			else {
				wsperiod="下午";
				wsid = 5;
			}
			System.out.println("wsid99999"+wsid);
			System.out.println("wsperiod99999"+wsperiod);
			CcTblWorkerSign ccTblWorkerSign =new CcTblWorkerSign();
			ccTblWorkerSign.setWid(Integer.valueOf(wid));
			ccTblWorkerSign.setWsid(wsid);
			ccTblWorkerSign.setWsdate(wsdate);
			ccTblWorkerSign.setWstime(wstime);
			ccTblWorkerSign.setWsperiod(wsperiod);

			List<TblWorker> users = ccFaceService.selectAllWorkers();
			String base64 = "";
			PrintWriter writer = response.getWriter();
			response.reset();
			for (TblWorker user : users) {
				if(user.getWface()!=null)
				{
					base64 = new String(user.getWface());
					System.out.println("base64--------------"+base64);
					boolean result = getResult(base, base64);
					System.out.println("result-----------"+result);
					if (result == true)
					{
						int f =ccFaceService.add(ccTblWorkerSign);
						return f;
					}
				}
			}

		} catch (ParseException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		//		try {
////			TblWorker u =new TblWorker();
////			u.setWface(base.getBytes());
//			//把前端抓取到的图片保存到数据库
//			//				      adminLoginService.save(u);
////			List<TblWorker> users = ccFaceService.selectAllWorkers();
////			String base64 = "";
////			PrintWriter writer = response.getWriter();
////			response.reset();
////			for (TblWorker user : users) {
////				if(user.getWface()!=null)
////				{
////					base64 = new String(user.getWface());
////					System.out.println("base64--------------"+base64);
////					boolean result = getResult(base, base64);
////					System.out.println("result-----------"+result);
////					if (result == true)
////					{
////						int f =ccFaceService.add(ccTblWorkerSign);
////						return f;
////					}
////				}
////			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			//			return "redirect:/page/404.jsp";
//		}

		return 0;
	}

	/** 人脸识别 比对 */
	public boolean getResult(String imStr1, String imgStr2) {

		accessToken = CcGetTon.getToken();
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

		JSONObject fromObject = JSONObject.fromObject(result);

		JSONObject jsonArray = fromObject.getJSONObject("result");

		double resultList = jsonArray.getDouble("score");
		if (resultList >= 70) {
			System.out.println("tttt");
			flag = true;

		}
		return flag;
	}


}
