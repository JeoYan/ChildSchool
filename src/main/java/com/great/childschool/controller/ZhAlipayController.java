package com.great.childschool.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;


import com.great.childschool.entity.*;
import com.great.childschool.service.ZhAlipayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 支付Controller业务层
 * by 张宏
 */
@Controller
public class ZhAlipayController
{
	@Resource
	private ZhAlipayService zhAlipayService;


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
	private final String RETURN_URL = "http://localhost:8080/ChildSchool/returnUrl";


	//全局书本id增加使用
	private String thisBookid;

	//查询书籍状态
	@RequestMapping("/findbooktype.action")
	@ResponseBody
	public int findbooktype(String bookid,HttpServletRequest request){
		String pid=String.valueOf(request.getSession().getAttribute("pid"));

		ZhTblParentReadbook zhTblParentReadbook =zhAlipayService.findbooktype(pid,bookid);
		System.out.println(zhTblParentReadbook);

		if (zhTblParentReadbook==null){
			return 1;
		}else {
			return 2;
		}
	}



	//支付宝界面
	@RequestMapping("/payView.action")
	public ModelAndView payView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("zhpay");

		return modelAndView;
	}


	//支付
	@RequestMapping(value = "/alipay.action" )
	@ResponseBody
	public void alipay(HttpServletResponse httpResponse,String bookid) throws IOException
	{
		System.out.println(bookid+"++++++++++++++++++++bookid+++++++++++++++++++++++");
		thisBookid=bookid;
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
		int total_amount = 10;
		//订单名称，必填
		//		String subject = order.getOrderName();
		//		商品描述，可空
		System.out.println("订单号2---------"+out_trade_no);
		System.out.println("金额2---------"+total_amount);
		String body = "";
		request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+"课外读物" +"\","
				//				+ "\"body\":\""+ body +"\","
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
		if (signVerified)
		{
			ZhPay pay=new ZhPay();
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
			zhAlipayService.addParentReadbook(pid,thisBookid);
			//userService.Paygai(pay);
			//			ModelAndView modelAndView=new Mp
			//支付成功，修复支付状态
			//			payService.updateById(Integer.valueOf(out_trade_no));
						ModelAndView mv = new ModelAndView();
						//			String index="alipaySuccess";
						mv.addObject("out_trade_no", out_trade_no);
						mv.addObject("trade_no", trade_no);
						mv.addObject("total_amount", total_amount);
						mv.addObject("flag", "success");
						mv.setViewName("zhalipayorder");
			return mv;

			//			return "alipaySuccess";//跳转付款成功页面
		} else
		{
			ModelAndView mv1 = new ModelAndView();
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



}
