package com.great.childschool.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.TjzBackService;
import com.great.childschool.tools.MessageSendDemo;
import com.great.childschool.tools.TjzGetTon;
import com.great.childschool.tools.Tool;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 控制类
 * by 汤建志
 */

@Controller
@RequestMapping("/BackAction")
public class TjzAdminHandler
{
	@Resource
	private TjzBackService tjzBackService;
	private static String accessToken;

	public final static String ISAFESTUDY_PATH = "\\src\\main\\resources\\static\\safestudy\\";


	private List bidList;


	//支付宝属性
	private final String APP_ID = "2016101500692907";
	//私钥
	private final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCoFZEuFOMUMMaDLS4bPNPPRXaE+oFGCi8kz/7iO89vmNdm+cSubeCZdKHlucDccGDvMG4RIa8W5ENGQ8yD6+QEdLNbznrFU+hc58j3n7twK46xy1PUlC/Z3helVA3KusV3DTaQpnCiHIL/FGhBWkhqzoi2yZuKhMOQPkjTUzTO61OU1Hfdizho0yUirUdRCo94KvuqBeriYO4y1p3cLzmWwyevETX4HsxJ0Cf1V6YPy35zKX9yXWKg2yIVVFe0iLv9mV0tGi8/Fx8HxbaClpwRCU7QfRanDAoU4T0z4ajaUSgUcbOUfyW4MG6fE52r3y2+0CVUwyoIeqCwOb68p3zPAgMBAAECggEAAiDzy7+b+J99hB/IW7PzgsaAb8K0bGyomDDCK59SSzmGUNmM8iCWHZtqiXxGwwPxVlYorrLcgs+1QmiNaIZORvgrZVhCGcmsQbD1zJ0LRKpojjvi/eg6iunTJtbfrdebtJxIOqDBAGyYBB8ACwPXsvKWrKn0/FYeG++OAI8nNg+jk9bXw8iMTuLr+kjYWe6CnEj2cVMwI7Uel8J0moj/nKBBGJ+T335aWu7PREEqM+OGWLfRCzE+3M6jyrdxgxb2g0W99xvQRfFJ6GhhHBX9hPYdUwvJiNNQvm438cLVq5KclOTXc79DWy1yZeKeflMahTHkcmGBpFTmNw3hvsTmwQKBgQD23T2kxNytElRzqbVUpT4yxFrAvH7lR+pPnM56NkpUC5WFIOVIp1DKqIfKUS7p04bQe2ZQ6NPjFcbX8SpzeYQXi2Ni4d41TH3qrgNETUnr+S9124aQKuj8Cbi2PCgc86XdxYV4zKFUE1l08GQQHfev7Xc0N7hddbeInKF+zInIfwKBgQCuTfmlQUxvIupg6F4m3X+0DWQNzsxGtxFcT2K07Fb4yEoYuST9BhgTvUyfUV2lFhtG4EVqD+zMBMFx0b1AmKKu3DvDv0YJcc7rCXpr5WJmZHSX0+0X4GyYTN3FRw93azVrxYoKe8VruZDIifoTvYb/4n4QfUm2an+v1xyhM8WjsQKBgQCbK1GWAg/b0cu3sBLEk+FWs8l/oHv6zotfY2b9tqZd0bI4Lgcw9cp4uBoyd3kPjOAOp6IWdWLKOGP2VCak0trOmdTs0KCFzADRxHNVOWefpc/JOyWH9RDha5LpKULlf9jgX0mYNrepS0hNktSytMN9l7v33JZMWLP2cM2qEBMDLQKBgQCi/5wjI6s8iqQvN11EbwIK+DLGjsqMnomHj10434J9Z5+S+yBR1S8S1oUev1IZHVEouHVxN68zIodbzs1x6MrJRn5FEUuHbXwYY2auG3YnVm2Jxc8D8wTab5c78rXN8N2mCLaMEy6NDJ0OS6kmaKgkBkPpW6BhC7qYT0sgnG+IIQKBgEzqPzMpaHwIePS3c1KJJjkLTtaCq+lgixxXr5BQBnRvJoFFSdQLkn6JST08xPGNgmesKIFwyr4qesiB2/S4FR5uyJr5EdUFjwCyFipgzXEawMV8D3jBwU4E5KS6yq+BcyOlnSQUbFuZi0K5nsDtFTKcAMEScCNnAPzWiWTkVolQ";
	private final String CHARSET = "UTF-8";
	private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh2R6w+HaQAwbGBXxd7bzeX83wwaPqSw/6gn7zQDTvouEG2L/D1T+7FJcPpCL8l+WIRvnfP3q0d1U4AFElf6fToweLvLAkWdNcgckCHx0cqUhbrUaZFNsQZrkFE4D5o4eRbVXdx+7jfrTdxURxuUF+tH7k0gpIOnLThOsHK3dHM8PEMtoFjI8Ku9CYwToObaBi74O2AdvAiusRzCW9YFM7aeuEdoPlR9jNmPFesXxb8KtAcvYaeqQrbRMlaq4qj7+tZn04pMnshkuUiA1p8BgffgzjFhBjTRq/RPCn1J0DBKYDJMd7HGtIHySYEQeKqeDo1R67Ods40qdFX0IH9e1UwIDAQAB";
	//这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
	private final String FORMAT = "JSON";
	//签名方式
	private final String SIGN_TYPE = "RSA2";
	//支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	private final String NOTIFY_URL = "http://公网地址/notifyUrl";
	//支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	private final String RETURN_URL = "http://localhost:8080/ChildSchool/BackAction/returnUrl";




	/**
	 * 卡充值界面
	 * by 汤建志
	 */
	@RequestMapping("/cardRechargeView.action")
	public String cardRechargeView(HttpServletRequest request)
	{

		String pid=String.valueOf(request.getSession().getAttribute("pid"));
		TjzTblBaby baby=tjzBackService.findMoney(Integer.valueOf(pid));
		List<TjzTblCard> cards =tjzBackService.finNotCard();
		request.setAttribute("cardNum", baby.getCardNum());
		request.setAttribute("cardMoney", baby.getCardMoney());
		request.setAttribute("bid", baby.getBid());
		request.setAttribute("bName", baby.getbName());
		return "cardrecharge";
	}


	//支付
	@RequestMapping(value = "/myAlipay.action" )
	@ResponseBody
//	@Log(operationType="支付",operationName="支付宝")
	public void alipay(HttpServletResponse httpResponse,String rechargeMoney) throws IOException
	{
		System.out.println(rechargeMoney+"+++++++rechargeMoney+++++++++");
		System.out.println("支付宝进入");
		//实例化客户端,填入所需参数
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//在公共参数中设置回跳和通知地址
		request.setReturnUrl(RETURN_URL);
		request.setNotifyUrl(NOTIFY_URL);
		//		System.out.println("订单号1---------"+inout.getMid());
		//		System.out.println("金额1---------"+inout.getMmoney());

		//		//根据订单编号,查询订单相关信息
		//		Order order = payService.selectById(orderId);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		int out_trade_no =nextItemNo();
		//付款金额，必填
		int total_amount = Integer.valueOf(rechargeMoney);
		//订单名称，必填
		//		String subject = order.getOrderName();
		//		商品描述，可空
		System.out.println("订单号2---------"+out_trade_no);
		System.out.println("金额2---------"+total_amount);
		String body = "";
		request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+"卡充值" +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		String form = "";
		try {
			form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();

	}


	//订单界面
	@RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
	//	@ResponseBody

	public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AlipayApiException
	{
		System.out.println("=================================同步回调=====================================");
		// 获取支付宝GET过来反馈信息
		System.out.println("支付成功, 进入同步通知接口...");
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		System.out.println(params);//查看参数都有哪些
		boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
		//验证签名通过
		ModelAndView mv1 = new ModelAndView();
		if (signVerified)
		{
			TjzPay pay=new TjzPay();
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			System.out.println("商户订单号=" + out_trade_no);
			System.out.println("支付宝交易号=" + trade_no);
			System.out.println("付款金额=" + total_amount);
			pay.setOut_trade_no(out_trade_no);
			pay.setTotal_amount(total_amount);
			pay.setTrade_no(trade_no);

			//操作
			String pid=String.valueOf(request.getSession().getAttribute("pid"));
			System.out.println(pid+"------------------pid-----------------------");
			System.out.println(pid+"------------------thisBookid-----------------------");

			TjzTblBaby baby=tjzBackService.findMoney(Integer.valueOf(pid));
			TjzTblBaby baby2=new TjzTblBaby();
			baby2.setBid(baby.getBid());
			Float totalMoney=baby.getCardMoney()+Float.valueOf(total_amount);
			baby2.setCardMoney(totalMoney);
			int flag=tjzBackService.addMoney( baby2);
			if (flag>0){
				ModelAndView mv = new ModelAndView();
				mv.addObject("out_trade_no", out_trade_no);
				mv.addObject("trade_no", trade_no);
				mv.addObject("save_amount", total_amount);
				mv.addObject("totalMoney", totalMoney);
				mv.addObject("flag", "success");
				mv.setViewName("cardsuccess");
				return mv;
			} else
			{

				return mv1;//跳转付款失败页面
			}

			//			return "alipaySuccess";//跳转付款成功页面
		} else
		{

			return mv1;//跳转付款失败页面
		}
	}


	//生成订单号
	public static Integer nextItemNo(){
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("HHmmssSSS");
		String id = sdformat.format(date);
		Integer integer = Integer.parseInt(id);
		return integer;
	}








	//	/**
//	 * 后台人脸验证
//	 * by 汤建志
//	 */
//	@RequestMapping("/faceCheck.action")
//	@ResponseBody
//	public void faceCheck(HttpServletRequest request,  HttpServletResponse response, HttpSession httpSession, Model model) {
//
//
//		int myLoginYzm=(int)((Math.random()*9+1)*100000);
//		String response = MessageSendDemo.send("44962", "b3cb27bee6dbb9f1d717950da9fbd627", phone, "【智慧幼儿园】亲爱的用户,您的验证码为："+myLoginYzm);
//		request.getSession().setAttribute("myLoginYzm", myLoginYzm);
//		System.out.println(response);
//
//	}




	/**
	 * 后台人脸验证
	 * by 汤建志
	 */
	@RequestMapping("/faceCheck.action")
	@ResponseBody
	public Map<String, Object> faceCheck(HttpServletRequest request,
	                            HttpServletResponse response, HttpSession httpSession, Model model) {
		// 获取前端页面传过来的参数
		Map<String, Object> map=new HashMap<String, Object>();
		String base = request.getParameter("base");
		String wid = request.getSession().getAttribute("wid").toString();
//		try {
			TjzTblWorker u = new TjzTblWorker();
			u.setWface(base.getBytes());
			//把前端抓取到的图片保存到数据库
			//				      adminLoginService.save(u);
			TjzTblWorker user = tjzBackService.findFace(Integer.valueOf(wid));
			String base64 = "";
			response.reset();


				base64 = new String(user.getWface());
				boolean result = getResult(base, base64);
				if (result) {
					map.put("msg",1);
				}else {
					map.put("msg",0);
				}

		return map;
	}



	/** 人脸识别 比对 */
	public boolean getResult(String imStr1, String imgStr2) {

		accessToken = TjzGetTon.getToken();
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





	/**
	 * 添加后台管理员
	 * by 汤建志
	 */
	@RequestMapping("/addface")
	@ResponseBody
	public Map<String, Object> addface(TjzTblWorker worker){

		Map<String, Object> map = new HashMap<String, Object>();
		int i= tjzBackService.addface(worker);
		if(i>0){

			map.put("msg", "1");

		}else {
			map.put("msg", "2");
		}
		return map;
	}



	/**
	 * 解除绑定卡
	 * by 汤建志
	 */
	@PostMapping("/unbindCard.action")
	@ResponseBody
	public Map<String, Object> unbindCard(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String cardNum = request.getParameter("cardNum");
		int flag = tjzBackService.unbindCard(cardNum);
		if (flag > 0)
		{
			map.put("msg", "ok");
		} else
		{
			map.put("msg", "error");

		}

		return map;
	}

	/**
	 * 绑定卡
	 * by 汤建志
	 */
	@PostMapping("/bindCard.action")
	@ResponseBody
	public Map<String, Object> bindCard(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String cardNum = request.getParameter("cardNum");
		String bid = request.getParameter("bid");

		TjzTblCard card=new TjzTblCard();
		card.setCardNum(cardNum);
		card.setBid(Integer.valueOf(bid));
		int flag = tjzBackService.bindCard(card);
		if (flag > 0)
		{
			map.put("msg", "ok");
		} else
		{
			map.put("msg", "error");

		}

		return map;
	}

	/**
	 * 卡绑定界面
	 * by 汤建志
	 */
	@RequestMapping("/cardBindView.action")
	public String cardBindView(HttpServletRequest request)
	{


		List<TjzTblCard> cards =tjzBackService.finNotCard();
		request.setAttribute("cards", cards);
		return "cardbindview";

	}



	/**
	 * 删除卡
	 * by 汤建志
	 */
	@PostMapping("/delCard.action")
	@ResponseBody
	public Map<String, Object> delCard(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String cardNum = request.getParameter("cardNum");

			int flag = tjzBackService.delCard(cardNum);
			if (flag > 0)
			{
				map.put("msg", "ok");
			} else
			{
				map.put("msg", "error");

			}

		return map;
	}


	/**
	 * 批量插入卡
	 * by 汤建志
	 */
	@RequestMapping("/addCards.action")
	@ResponseBody
	public Map<String, Object> addCards(String cardNum,String cardStart)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		int intCardNum=Integer.valueOf(cardNum);
		int intCardStart=Integer.valueOf(cardStart);
		int intCardEnd=intCardStart+intCardNum-1;
		Date day = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String toDay = dateFormat.format(day);
		List<TjzTblCard> cardList = new ArrayList<TjzTblCard>();


		for (int i = intCardStart; i <= intCardEnd; i++)
		{


			TjzTblCard card = new TjzTblCard();
			card.setCardNum(String.format("%08d", i));
			card.setCardTime(toDay);
			cardList.add(card);
		}
		int flag= tjzBackService.addCards(cardList);
		if (flag>0){
			map.put("msg",1);
		}else {
			map.put("msg",0);
		}
		return  map;
	}

	/**
	 * 卡入库页面
	 * by 汤建志
	 */
	@RequestMapping("/addCardView.action")
	public String addCardView(HttpServletRequest request )
	{


		String maxCard = tjzBackService.findMaxCard( );
		if (null != maxCard && maxCard.length() > 0)
		{
			request.setAttribute("maxCard", String.format("%08d", Integer.valueOf(maxCard)+1));
			return "addcard";
		} else
		{
			System.out.println("fail");
			return "addcard";
		}
	}


	/**
	 * 卡片管理
	 * by 汤建志
	 */
	@RequestMapping("/findCard.action")
	@ResponseBody
	public TjzTbTable findCard(HttpServletRequest request, String page, String limit, String startNum, String endNum, String bName, String cardTime)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("bName",bName);
		map.put("cardTime",cardTime);
		if (startNum==null ||"".equals(startNum)){
			startNum="0";
		}
		if (endNum==null ||"".equals(endNum)){
			endNum="999999";
		}
		map.put("startNum",String.format("%08d", Integer.valueOf(startNum)));
		map.put("endNum",String.format("%08d", Integer.valueOf(endNum)));

//		map.put("startNum",startNum);
//		map.put("endNum",endNum);
		map.put("pstart",pstart);
		map.put("psize",psize);
		TjzTbTable tbBean = tjzBackService.findCard(map);
		return tbBean;

	}



	/**
	 * 安全教育考试
	 * by 汤建志
	 */
	@RequestMapping("/queryScore.action")
	public String queryScore(HttpServletRequest request, String safeId, String totalScore)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		String pid = request.getSession().getAttribute("pid").toString();
		map.put("pid", pid);
		map.put("safeId", Integer.valueOf(safeId));
		List<TjzTblquestion> tblquestions = tjzBackService.queryScore(map);
		if (null != tblquestions && tblquestions.size() > 0)
		{
			request.setAttribute("tableBody", tblquestions);
			request.setAttribute("safeId", safeId);
			request.setAttribute("totalScore", totalScore + "分");
			request.setAttribute("questionNum", tblquestions.size());
			return "queryscore";
		} else
		{
			System.out.println("fail");
			request.setAttribute("safeId", safeId);
			return "queryscore";
		}
	}


	/**
	 * 提交答案
	 * by 汤建志
	 */
	@RequestMapping("/submitAnswers.action")
	@ResponseBody
	public Map<String, Object> submitAnswers(HttpServletRequest request)
	{


		Map<String, Object> map = new HashMap<String, Object>();
		String pid = request.getSession().getAttribute("pid").toString();
		String answer = request.getParameter("answer");
		String safeId = request.getParameter("safeId");
		System.out.println(answer);
		JSONObject jsonObject = JSONObject.fromObject(answer);
		Map<String, String> answerMap = (Map) jsonObject;
		int flag = tjzBackService.checkAnswer(answerMap, pid, safeId);
		if (flag == 0)
		{
			map.put("msg", "0");
			return map;
		} else
		{
			map.put("msg", flag);
			return map;
		}


	}

	/**
	 * 安全教育考试
	 * by 汤建志
	 */
	@RequestMapping("/SafeStudyTest.action")
	public String SafeStudyTest(HttpServletRequest request, String safeId, String endDate, String startDate)
	{


		List<TjzTblquestion> tblquestions = tjzBackService.addSafeStudyTestView(Integer.valueOf(safeId));
		if (null != tblquestions && tblquestions.size() > 0)
		{


			Date day = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String toDay = dateFormat.format(day);
			System.out.println("toDay" + toDay);
			System.out.println("endDate" + endDate);
			System.out.println("startDate" + startDate);
			String type = "";
			int result = toDay.compareTo(endDate);
			int result2 = toDay.compareTo(startDate);
			if (result > 0)
			{
				type = "0";
				System.out.println("已过期");

			} else if (result2 < 0)
			{
				type = "1";
				System.out.println("未开始");
			} else
			{
				type = "2";
				System.out.println("开始");
			}
			request.setAttribute("tableBody", tblquestions);
			request.setAttribute("safeId", safeId);
			request.setAttribute("type", type);
			request.setAttribute("questionNum", tblquestions.size());
			return "safestudytest";
		} else
		{
			System.out.println("fail");
			request.setAttribute("safeId", safeId);
			return "safestudytest";
		}
	}


	/**
	 * 修改安全教育考试题目
	 * by 汤建志
	 */
	@RequestMapping("/updateQuestion.action")
	@ResponseBody
	public Map<String, Object> updateQuestion(HttpServletRequest request)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		String question = request.getParameter("question");
		String optiona = request.getParameter("optiona");
		String optionb = request.getParameter("optionb");
		String safeId = request.getParameter("safeId");
		String answer = request.getParameter("answer");
		String questionId = request.getParameter("questionId");
		TjzTblquestion tblquestion = new TjzTblquestion();
		tblquestion.setQuestion(question);
		tblquestion.setOptionA(optiona);
		tblquestion.setOptionB(optionb);
		tblquestion.setSafeId(35);
		tblquestion.setAnswer(answer);
		tblquestion.setQuestionId(Integer.valueOf(questionId));

		int flag = tjzBackService.updateQuestion(tblquestion);
		if (flag > 0)
		{

			map.put("msg", "1");
			return map;
		} else
		{
			map.put("msg", "0");
			return map;

		}
	}

	/**
	 * 删除安全教育考试题目
	 * by 汤建志
	 */
	@PostMapping("/deleteQuestion.action")
	@ResponseBody
	public Map<String, Object> deleteQuestion(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String questionId = request.getParameter("questionId");

		int flag = tjzBackService.deleteQuestion(Integer.valueOf(questionId));
		if (flag > 0)
		{
			map.put("msg", "ok");
		} else
		{
			map.put("msg", "error");

		}

		return map;
	}


	/**
	 * 添加安全教育试题
	 * by 汤建志
	 */
	@RequestMapping("/addSafeStudyTest.action")
	@ResponseBody
	public Map<String, Object> addSafeStudyTest(HttpServletRequest request)
	{

		Map<String, Object> map = new HashMap<String, Object>();


		String question = request.getParameter("question");
		String optiona = request.getParameter("optiona");
		String optionb = request.getParameter("optionb");
		String answer = request.getParameter("answer");
		String safeId = request.getParameter("safeId");
		TjzTblquestion tblquestion = new TjzTblquestion();
		tblquestion.setQuestion(question);
		tblquestion.setOptionA(optiona);
		tblquestion.setOptionB(optionb);
		tblquestion.setSafeId(Integer.valueOf(safeId));
		tblquestion.setAnswer(answer);

		int flag = tjzBackService.addSafeStudyTest(tblquestion);

		if (flag > 0)
		{

			map.put("msg", "1");
			return map;
		} else
		{
			map.put("msg", "0");
			return map;

		}
	}

	/**
	 * 安全教育考试题目页面
	 * by 汤建志
	 */
	@RequestMapping("/addSafeStudyTestView.action")
	public String addSafeStudyTestView(HttpServletRequest request, String safeId)
	{

		List<TjzTblquestion> tblquestions = tjzBackService.addSafeStudyTestView(Integer.valueOf(safeId));
		if (null != tblquestions && tblquestions.size() > 0)
		{
			request.setAttribute("tableBody", tblquestions);
			request.setAttribute("safeId", safeId);
			request.setAttribute("questionNum", tblquestions.size());
			return "addsafestudytest";
		} else
		{
			System.out.println("fail");
			request.setAttribute("safeId", safeId);
			return "addsafestudytest";
		}
	}


	/**
	 * 教师修改安全教育视频
	 * by 汤建志
	 */
	@RequestMapping(value = "/updateVideo.action")
	@ResponseBody
	@Log(operationType = "上传操作", operationName = "修改教育视频")
	public Map<String, Object> updateVideo(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String safeName = request.getParameter("safeName");
		String oldSafeName = request.getParameter("oldSafeName");
		String rangeDate = request.getParameter("rangeDate");
		String safeId = request.getParameter("safeId");
		if (safeName.equals("") || safeName == null)
		{
			map.put("msg", "请输入文件名");
		} else if (rangeDate.equals("") || rangeDate == null)
		{
			map.put("msg", "请输入时间范围");
		} else
		{
			String[] arr = rangeDate.split("\\s+");
			String startDate = arr[0];
			String endDate = arr[2];
			String wid = request.getSession().getAttribute("wid").toString();
			String originalFilename = file.getOriginalFilename();
			System.out.println(originalFilename + "----------文件名-----------");
			String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			Date day = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			TjzTblSafeStudy safeStudy = new TjzTblSafeStudy();
			safeStudy.setSafeName(safeName);
			safeStudy.setStartDate(startDate);
			safeStudy.setEndDate(endDate);
			safeStudy.setSafeDate(dateFormat.format(day));
			safeStudy.setWid(Integer.valueOf(wid));
			String newFilename = safeName + "." + suffix;
			String oldFilename = oldSafeName + "." + suffix;
			System.out.println(System.getProperty("user.dir"));
			String saveFilePath = System.getProperty("user.dir") + ISAFESTUDY_PATH;
			safeStudy.setTestUrl(null);
			File newFilePath = new File(saveFilePath);
			if (newFilePath.exists())
			{


				File oldFile = new File(saveFilePath + oldFilename);
				if (oldFile.exists())
				{

					File newFile = new File(saveFilePath + newFilename);
					if (newFile.exists() && !oldFilename.equals(newFilename))
					{

						map.put("msg", "该文件名已存在");
					}else {
						try
						{

							oldFile.delete();
							//保存文件到服务器
							file.transferTo(newFile);
							safeStudy.setvUrl(saveFilePath);
							safeStudy.setSafeId(Integer.valueOf(safeId));
							//保存到数据库
							int flag = tjzBackService.updateVideo(safeStudy);
							if (flag > 0)
							{
								map.put("msg", "ok");
							} else
							{
								map.put("msg", "error");
								newFile.delete();
							}
						} catch (IOException e)
						{
							map.put("msg", "error");
							e.printStackTrace();
						}
					}

				} else
				{
					map.put("msg", "error");
				}

			} else
			{
				map.put("msg", "error");
			}

		}
		return map;
	}

	/**
	 * 删除安全教育视频
	 * by 汤建志
	 */
	@PostMapping("/delSafeStudyVideo.action")
	@ResponseBody
	public Map<String, Object> delSafeStudyVideo(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String safeName = request.getParameter("safeName");
		String safeId = request.getParameter("safeId");
		String newFilename = safeName + "." + "mp4";
		String saveFilePath = System.getProperty("user.dir") + ISAFESTUDY_PATH + newFilename;
		System.out.println(saveFilePath);
		File newFile = new File(saveFilePath);
		if (newFile.exists())
		{
			int flag = tjzBackService.delSafeStudyVideo(Integer.valueOf(safeId));
			if (flag > 0)
			{
				map.put("msg", "ok");
				newFile.delete();
			} else
			{
				map.put("msg", "error");

			}
		} else
		{
			map.put("msg", "error");
		}
		return map;
	}


	/**
	 * 电子围栏查询报警日志
	 * by 汤建志
	 */
	@RequestMapping("/findWarning.action")
	@ResponseBody
	public TjzTbTable findWarning(String page, String limit, String startDate, String endDate, String area)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		if (startDate == null || startDate.equals(""))
		{
			map.put("startDate", "0000" + " 00:00:00");
		} else
		{
			map.put("startDate", startDate + " 00:00:00");
		}
		if (endDate == null || endDate.equals(""))
		{
			map.put("endDate", "9999" + " 24:00:00");
		} else
		{
			map.put("endDate", endDate + " 24:00:00");
		}
		map.put("area", area);
		return tjzBackService.findWarning(map);
	}


	/**
	 * 添加报警信息
	 * by 汤建志
	 */
	@RequestMapping("/addWarning.action")
	@ResponseBody
	public synchronized Map<String, Object> addWarning(String bid, String area, HttpServletRequest request)
	{

		if (bidList == null)
		{
			bidList = new ArrayList();
		}
		System.out.println("+++++bid" + bid);
		System.out.println("+++++area" + area);
		Map<String, Object> map;
		if (bidList.contains(bid))
		{
			map = new HashMap<String, Object>();
			map.put("msg", "0");
			System.out.println("存在");//false
		}
		{
			bidList.add(bid);
			System.out.println("不存在");//false
			map = new HashMap<String, Object>();
			TjzTblWarning warning = new TjzTblWarning();
			warning.setBid(Integer.valueOf(bid));
			warning.setArea(area);
			Date day = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datTime = dateFormat.format(day);
			warning.setWarnTime(datTime);
			warning.setWarnName("越界");
			int flag = tjzBackService.addWarning(warning);
			if (flag > 0)
			{
				map.put("msg", "1");
				TjzTblBaby baby = tjzBackService.fenceBaby(Integer.valueOf(bid));
				System.out.println(baby.getpPhone() + "+++" + baby.getbName());
				String pPhone = baby.getpPhone();
				String bName = baby.getbName();
				String content = "【智慧幼儿园】亲爱的家长：" + baby.getpName() + "，" + datTime + "，您的宝宝【" + bName + "】越过电子围栏【" + area + "】触发报警。请及时与班主任联系。";
				System.out.println(content);
				String response = MessageSendDemo.send("44962", "b3cb27bee6dbb9f1d717950da9fbd627", pPhone, content);
				System.out.println(response);
			} else
			{
				map.put("msg", "0");
			}
		}
		return map;
	}

	;


	@RequestMapping("/babySafeStudy.action")
	public String babySafeStudy(HttpServletRequest request, String pid)
	{

		request.setAttribute("pid", pid);
		return "babysafestudy";
	}


	/**
	 * 教师查看家长提交
	 * by 汤建志
	 */
	@RequestMapping("/querySafeStudy.action")
	@ResponseBody
	public TjzTbTable querySafeStudy(HttpServletRequest request, String page, String limit, String startDate, String endDate, String safeName)
	{
		String pid = request.getParameter("pid");
		System.out.println("+++++pid" + pid);
		TjzTbTable tbBean = tjzBackService.parentSafeStudy(page, limit, startDate, endDate, safeName, pid);
		return tbBean;
	}

	;


	/**
	 * 家长提交答案
	 * by 汤建志
	 */
	@RequestMapping(value = "/uploadAnswer.action")
	@ResponseBody
	@Log(operationType = "上传操作", operationName = "上传教育试题")
	public Map<String, Object> uploadAnswer(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String safeName = request.getParameter("safeName");
		String safeId = request.getParameter("safeId");
		String originalFilename = file.getOriginalFilename();//
		System.out.println(originalFilename + "----------文件名-----------");
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		TjzTblSafeStudy safeStudy = new TjzTblSafeStudy();
		String newFilename = safeName + "试题" + "." + suffix;
		String saveFilePath = System.getProperty("user.dir") + ISAFESTUDY_PATH + newFilename;
		safeStudy.setTestUrl(saveFilePath);
		safeStudy.setSafeId(Integer.valueOf(safeId));
		try
		{
			//保存文件到服务器
			File newFile = new File(saveFilePath);
			if (newFile.exists())
			{
				map.put("msg", "该文件名已存在");
			} else
			{
				file.transferTo(newFile);
				//保存到数据库
				int flag = tjzBackService.uploadTest(safeStudy);
				if (flag > 0)
				{
					map.put("msg", "ok");
				} else
				{
					map.put("msg", "error");
					newFile.delete();
				}
			}

		} catch (IOException e)
		{
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;


	}


	@RequestMapping("/download.action")
	public ResponseEntity<byte[]> export(String fileName) throws IOException
	{
		System.out.println("文件下载：" + fileName);
		String downloadPath = System.getProperty("user.dir") + ISAFESTUDY_PATH;
		File file = new File(downloadPath + fileName);
		HttpHeaders headers = new HttpHeaders();
		// MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}


	@RequestMapping("/teacherBabySafe.action")
	//	@Log(operationType = "查询操作", operationName = "课程表")
	public String teacherBabySafe(HttpServletRequest request, String cid)
	{


		request.setAttribute("cid", cid);
		return "teacherbabysafe";
	}

	/**
	 * 教师查看班级安全教育宝宝情况
	 * by 汤建志
	 */
	@RequestMapping("/classSafeStudy.action")
	@ResponseBody
	//	@Log(operationType = "查询操作", operationName = "课程表")
	public TjzTbTable classSafeStudy(HttpServletRequest request, String page, String limit, String bName, String cid)
	{


		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("bName", bName);
		map.put("cid", cid);
		TjzTbTable tbTable = tjzBackService.classSafeStudy(map);
		return tbTable;
	}


	/**
	 * 家长查看安全教育
	 * by 汤建志
	 */
	@RequestMapping("/parentSafeStudy.action")
	@ResponseBody
	public TjzTbTable parentSafeStudy(HttpServletRequest request, String page, String limit, String startDate, String endDate, String safeName)
	{
		String pid = request.getSession().getAttribute("pid").toString();
		System.out.println("+++++pid" + pid);
		TjzTbTable tbBean = tjzBackService.parentSafeStudy(page, limit, startDate, endDate, safeName, pid);
		return tbBean;
	}

	;


	/**
	 * 教师上传安全教育试题
	 * by 汤建志
	 */
	@RequestMapping(value = "/uploadTest.action")
	@ResponseBody
	@Log(operationType = "上传操作", operationName = "上传教育试题")
	public Map<String, Object> uploadTest(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String safeName = request.getParameter("safeName");
		String safeId = request.getParameter("safeId");
		String originalFilename = file.getOriginalFilename();//
		System.out.println(originalFilename + "----------文件名-----------");
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		TjzTblSafeStudy safeStudy = new TjzTblSafeStudy();
		String newFilename = safeName + "试题" + "." + suffix;
		String saveFilePath = System.getProperty("user.dir") + ISAFESTUDY_PATH + newFilename;
		safeStudy.setTestUrl(saveFilePath);
		safeStudy.setSafeId(Integer.valueOf(safeId));
		try
		{
			//保存文件到服务器
			File newFile = new File(saveFilePath);
			if (newFile.exists())
			{
				map.put("msg", "该文件名已存在");
			} else
			{
				file.transferTo(newFile);
				//保存到数据库
				int flag = tjzBackService.uploadTest(safeStudy);
				if (flag > 0)
				{
					map.put("msg", "ok");
				} else
				{
					map.put("msg", "error");
					newFile.delete();
				}
			}

		} catch (IOException e)
		{
			map.put("msg", "error");
			e.printStackTrace();
		}
		return map;


	}


	/**
	 * 新增安全教育视频
	 * by 汤建志
	 */
	@RequestMapping(value = "/uploadVideo.action")
	@ResponseBody
	@Log(operationType = "上传操作", operationName = "上传教育视频")
	public Map<String, Object> uploadVideo(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String safeName = request.getParameter("safeName");
		String rangeDate = request.getParameter("rangeDate");
		if (safeName.equals("") || safeName == null)
		{
			map.put("msg", "请输入文件名");
		} else if (rangeDate.equals("") || rangeDate == null)
		{
			map.put("msg", "请输入时间范围");
		} else
		{
			String[] arr = rangeDate.split("\\s+");
			String startDate = arr[0];
			String endDate = arr[2];
			String wid = request.getSession().getAttribute("wid").toString();
			String originalFilename = file.getOriginalFilename();
			System.out.println(originalFilename + "----------文件名-----------");
			String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			Date day = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			TjzTblSafeStudy safeStudy = new TjzTblSafeStudy();
			safeStudy.setSafeName(safeName);
			safeStudy.setStartDate(startDate);
			safeStudy.setEndDate(endDate);
			safeStudy.setSafeDate(dateFormat.format(day));
			safeStudy.setWid(Integer.valueOf(wid));
			String newFilename = safeName + "." + suffix;
			System.out.println(System.getProperty("user.dir"));
			String saveFilePath = System.getProperty("user.dir") + ISAFESTUDY_PATH;
			safeStudy.setTestUrl(null);
			File newFilePath = new File(saveFilePath);
			if (newFilePath.exists())
			{
				saveFilePath += newFilename;
				File newFile = new File(saveFilePath);
				if (newFile.exists())
				{
					map.put("msg", "该文件名已存在");
				} else
				{
					try
					{
						//保存文件到服务器
						file.transferTo(newFile);
						safeStudy.setvUrl(saveFilePath);
						//保存到数据库
						int flag = tjzBackService.uploadVideo(safeStudy);
						if (flag > 0)
						{
							map.put("msg", "ok");
						} else
						{
							map.put("msg", "error");
							newFile.delete();
						}
					} catch (IOException e)
					{
						map.put("msg", "error");
						e.printStackTrace();
					}
				}
			} else
			{
				map.put("msg", "error");
			}

		}
		return map;
	}


	/**
	 * 安全教育管理
	 * by 汤建志
	 */
	@RequestMapping("/safeStudyManagement.action")
	@ResponseBody
	public TjzTbTable safeStudyManagement(String page, String limit, String startDate, String endDate, String safeName)
	{
		TjzTbTable tbBean = tjzBackService.safeStudyManagement(page, limit, startDate, endDate, safeName);
		return tbBean;
	}

	;


	/**
	 * 按月统计日志界面
	 * by 汤建志
	 */
	@RequestMapping("/logCountByMonthView.action")
	public ModelAndView logCountByMonthView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logcountbymonth");
		return modelAndView;
	}

	/**
	 * 按人员统计日志界面
	 * by 汤建志
	 */
	@RequestMapping("/logCountByWidView.action")
	public ModelAndView logCountByWidView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logcountbywid");
		return modelAndView;
	}

	/**
	 * 按月统计日志
	 * by 汤建志
	 */
	@RequestMapping("/logCountByMonth.action")
	@ResponseBody
	public List<TjzLogCount> logCountByMonth()
	{
		List<TjzLogCount> logCounts = tjzBackService.logCountByMonth();
		System.out.println(logCounts.toString());
		return logCounts;
	}

	/**
	 * 按人员统计日志
	 * by 汤建志
	 */
	@RequestMapping("/logCountByWid.action")
	@ResponseBody
	public List<TjzLogCount> logCountByWid()
	{
		List<TjzLogCount> logCounts = tjzBackService.logCountByWid();
		System.out.println(logCounts.toString());
		return logCounts;
	}


	/**
	 * 查询日志
	 * by 汤建志
	 */
	@RequestMapping("/findLog.action")
	@ResponseBody
	public TjzTbTable findLog(String page, String limit, String startDate, String endDate, String wName)
	{
		TjzTbTable tbBean = tjzBackService.showLogTable(page, limit, startDate, endDate, wName);
		return tbBean;
	}

	/**
	 * 家长修改密码
	 * by 汤建志
	 */
	@RequestMapping("/parentChangePassword.action")
	@ResponseBody
	@Log(operationType = "修改密码", operationName = "家长修改密码")
	public TjzTbTable parentChangePassword(HttpServletRequest request, HttpServletResponse response)
	{

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");
		String pid = request.getSession().getAttribute("pid").toString();
		TjzTbTable tbTable = new TjzTbTable();
		if (!newPassword.equals(reNewPassword))
		{
			tbTable.setMsg("0");
		} else
		{

			TjzTblParent parent = tjzBackService.parentOldPassword(Integer.valueOf(pid));
			if (!parent.getPpsw().equals(oldPassword))
			{
				tbTable.setMsg("1");
			} else
			{

				parent.setPid(Integer.valueOf(pid));
				parent.setPpsw(newPassword);
				int flag = tjzBackService.parentChangePassword(parent);
				if (flag > 0)
				{
					tbTable.setMsg("2");
				} else
				{
					tbTable.setMsg("3");
				}

			}

		}


		return tbTable;

	}

	/**
	 * 家长查看上下周课程表
	 * by 汤建志
	 */
	@RequestMapping("/parentWeekCourseTable.action")
	//	@Log(operationType = "查询操作", operationName = "查询课程表")
	public String parentWeekCourseTable(HttpServletRequest request, HttpServletResponse response, String bid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		List<Date> dateList;
		if ("上一周".equals(doWhich))
		{
			dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));

		} else
		{
			dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));
		}
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("bid", bid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.parentCourseTable(map2);
		request.setAttribute("bid", bid);
		request.setAttribute("tableBody", map);
		request.setAttribute("tableHead", days);
		return "parentcoursetable";


	}


	/**
	 * 家长查看本周课程表
	 * by 汤建志
	 */
	@RequestMapping("/parentCourseTable.action")
	//	@Log(operationType = "查询操作", operationName = "查询课程表")
	public String parentCourseTable(HttpServletRequest request, String bid)
	{

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("bid", bid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.parentCourseTable(map2);
		if (null != map)
		{
			request.setAttribute("bid", bid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "parentcoursetable";
		} else
		{
			System.out.println("fail");
			return "parentcoursetable";

		}
	}


	/**
	 * 家长查看孩子所在班级信息
	 * by 汤建志
	 */
	@RequestMapping("/parentCourseQuery.action")
	@ResponseBody
	//	@Log(operationType = "查询操作", operationName = "课程管理")
	public TjzTbTable parentCourseQuery(HttpServletRequest request)
	{

		String pid = request.getSession().getAttribute("pid").toString();
		TjzTbTable tbBean = tjzBackService.parentCourseQuery(pid);
		return tbBean;
	}

	/**
	 * 教师查看班级信息
	 * by 汤建志
	 */
	@RequestMapping("/teacherCourseQuery.action")
	@ResponseBody
	//	@Log(operationType = "查询操作", operationName = "课程管理")
	public TjzTbTable teacherCourseQuery(HttpServletRequest request, String page, String limit, String startDate, String endDate, String cName)
	{

		String wid = request.getSession().getAttribute("wid").toString();
		TjzTbTable tbBean = tjzBackService.teacherCourseQuery(page, limit, startDate, endDate, cName, wid);
		return tbBean;
	}


	/**
	 * 教师查看上下周课程表
	 * by 汤建志
	 */
	@RequestMapping("/teacherWeekcourseTable.action")
	//	@Log(operationType = "查询操作", operationName = "课程表")
	public String teacherWeekcourseTable(HttpServletRequest request, HttpServletResponse response, String cid, String wid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		List<Date> dateList;
		if ("上一周".equals(doWhich))
		{
			dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));


		} else
		{

			dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));

		}
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("wid", wid);
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.teacherCourseTable(map2);
		request.setAttribute("cid", cid);
		request.setAttribute("wid", wid);
		request.setAttribute("tableBody", map);
		request.setAttribute("tableHead", days);
		return "teachercoursetable";


	}


	/**
	 * 教师查看本周课程表
	 * by 汤建志
	 */
	@RequestMapping("/teacherCourseTable.action")
	//	@Log(operationType = "查询操作", operationName = "课程表")
	public String teacherCourseTable(HttpServletRequest request, String cid, String bid)
	{


		String wid = request.getSession().getAttribute("wid").toString();
		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("wid", wid);
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.teacherCourseTable(map2);
		if (null != map)
		{
			request.setAttribute("cid", cid);
			request.setAttribute("wid", wid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "teachercoursetable";
		} else
		{
			System.out.println("fail");
			return "teachercoursetable";

		}
	}

	/**
	 * 批量插入空课程表
	 * by 汤建志
	 */
	@RequestMapping("/insertCodeBatch.action")
	public int insertCodeBatch()
	{
		List<TjzTbCourse> courseList2 = new ArrayList<TjzTbCourse>();

		for (int i = 0; i < 3; i++)
		{
			TjzTbCourse course = new TjzTbCourse();
			course.setcDate("2019-12-19");
			course.setCid(1);
			course.setSubId(1);
			course.setcOrder("1");
			courseList2.add(course);
		}
		tjzBackService.insertCodeBatch(courseList2);
		return 2;
	}


	/**
	 * 科目下拉框
	 * by 汤建志
	 */
	@RequestMapping("/findSubject.action")
	public ModelAndView findSubject(HttpServletRequest req)
	{
		List<TjzTbSubject> subjects = tjzBackService.findSubject();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("courseadd");
		modelAndView.addObject("subjects", subjects);
		return modelAndView;
	}

	/**
	 * 排课
	 * by 汤建志
	 */
	@RequestMapping("/addSubject.action")
	@ResponseBody
	@Log(operationType = "插入操作", operationName = "园长排课")
	public TjzTbTable addSubject(HttpServletRequest req)
	{
		TjzTbTable tjzTbTable = new TjzTbTable();
		String subjects = req.getParameter("subjects");
		String couId = req.getParameter("couId");
		TjzTbCourse course = new TjzTbCourse();
		course.setCouId(Integer.valueOf(couId));
		course.setSubId(Integer.valueOf(subjects));
		int flag = tjzBackService.addSubject(course);
		if (flag > 0)
		{
			tjzTbTable.setMsg("1");

		} else
		{
			tjzTbTable.setMsg("0");
		}
		return tjzTbTable;
	}

	/**
	 * 园长查看上下周课程表
	 * by 汤建志
	 */
	@RequestMapping("/weekcourseTable.action")
	//	@Log(operationType = "查询操作", operationName = "课程表")
	public String weekcourseTable(HttpServletRequest request, HttpServletResponse response, String cid)
	{
		String nowDate = request.getParameter("now-Date");
		String doWhich = request.getParameter("doWhich");
		Date date = Tool.getDateType(nowDate);
		int intCid = Integer.valueOf(cid);
		Map<String, List<TjzTbCourse>> map;
		List<String> days;
		if ("上一周".equals(doWhich))
		{
			List<Date> dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));
			List<String> daySting = Tool.getDateType(dateList);
			Date toDay = new Date(System.currentTimeMillis());
			List<Date> toDays = Tool.dateToWeek(toDay);
			List<String> toDaysSting = Tool.getDateType(toDays);
			Date date1 = Tool.getDateType((toDaysSting.get(6)));
			Date date2 = Tool.getDateType((daySting.get(6)));
			if (dateList.get(6).getTime() > toDays.get(6).getTime())
			{
				days = Tool.getDateType(dateList);
			} else
			{
				days = Tool.getDateType(toDays);
			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("startDate", days.get(0));
			map2.put("endDate", days.get(days.size() - 1));
			map2.put("cid", cid);
			map = tjzBackService.courseTable(map2);
		} else
		{
			List<Date> dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));
			days = Tool.getDateType(dateList);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("startDate", days.get(0));
			map2.put("endDate", days.get(days.size() - 1));
			map2.put("cid", cid);
			map = tjzBackService.courseTable(map2);
			if (!(map.size() > 0))
			{
				List<TjzTbCourse> courseList2 = new ArrayList<TjzTbCourse>();
				for (int i = 0; i < 5; i++)
				{
					for (int j = 0; j < 5; j++)
					{
						TjzTbCourse course = new TjzTbCourse();
						course.setcDate(days.get(i));
						course.setCid(intCid);
						course.setSubId(1);
						course.setcOrder(String.valueOf(j + 1));
						courseList2.add(course);
					}
				}
				tjzBackService.insertCodeBatch(courseList2);
				map = tjzBackService.courseTable(map2);
			}
		}
		if (null != map)
		{
			request.setAttribute("cid", cid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "coursetable";
		} else
		{
			System.out.println("fail");
			return "coursetable";
		}
	}

	/**
	 * 园长查看本周课程表
	 * by 汤建志
	 */
	@RequestMapping("/courseTable.action")
	//	@Log(operationType = "查询操作", operationName = "课程表")
	public String courseTable(HttpServletRequest request, String cid)
	{

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startDate", days.get(0));
		map2.put("endDate", days.get(days.size() - 1));
		map2.put("cid", cid);
		Map<String, List<TjzTbCourse>> map = tjzBackService.courseTable(map2);
		int intCid = Integer.valueOf(cid);
		if (!(map.size() > 0))
		{
			List<TjzTbCourse> courseList2 = new ArrayList<TjzTbCourse>();
			for (int i = 0; i < 5; i++)
			{
				for (int j = 0; j < 5; j++)
				{

					TjzTbCourse course = new TjzTbCourse();
					course.setcDate(days.get(i));
					course.setCid(intCid);
					course.setSubId(1);
					course.setcOrder(String.valueOf(j + 1));
					courseList2.add(course);
				}

			}
			tjzBackService.insertCodeBatch(courseList2);
			map = tjzBackService.courseTable(map2);
		}


		if (null != map)
		{
			request.setAttribute("cid", cid);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
			return "coursetable";
		} else
		{
			System.out.println("fail");
			return "coursetable";

		}
	}


	/**
	 * 园长课程管理
	 * by 汤建志
	 */
	@RequestMapping("/courseManagement.action")
	@ResponseBody
	//	@Log(operationType = "管理操作", operationName = "课程管理")
	public TjzTbTable courseManagement(String page, String limit, String startDate, String endDate, String cName)
	{

		TjzTbTable tbBean = tjzBackService.courseManagement(page, limit, startDate, endDate, cName);
		return tbBean;
	}


}
