package com.great.childschool.controller;


import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;

import com.great.childschool.service.YjjMallService;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import java.util.*;

import static com.great.childschool.tools.YjjTools.getDate;


/**
 * 亲子阅读控制类
 * by 严俊杰
 */
@Controller
@RequestMapping("/mall")
public class YjjMallController
{
	private final static String REAL_PATH = System.getProperty("user.dir") + "/src/main/resources/static/";

	@Resource
	private YjjMallService mallService;


	@RequestMapping("/merchandisePage.action")
	public String merchandisePage()
	{
		return "merchandise";
	}


	/**
	 * 获得商品数据显示
	 * by 严俊杰1.08
	 */
	@RequestMapping("/getComTable.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询商品表格")
	public YjjTableData table(HttpServletRequest request)
	{
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		String bName = request.getParameter("bName");
		String startDate = request.getParameter("startDate");

		System.out.println("---------limit------------" + limit);
		System.out.println("---------page------------" + page);
		System.out.println("---------bName------------" + bName);
		System.out.println("---------startDate------------" + startDate);

		YjjSearchInfo searchInfo = new YjjSearchInfo();
		searchInfo.setLimit(Integer.valueOf(limit));
		searchInfo.setPage(Integer.valueOf(page));
		searchInfo.setbName(bName);
		searchInfo.setStartDate(startDate);

		List<YjjTblCommodity> list = mallService.findComm(searchInfo);
		List<YjjTblCommodity> list1 = mallService.totalPage(searchInfo);

		//总条数
		int totalPage = list1.size();
		YjjTableData send = new YjjTableData();
		send.setCode(new BigDecimal(0));
		send.setCount(new BigDecimal(totalPage));
		send.setData(list);
		System.out.println(limit + "limit+----------------+page" + page);
		return send;

	}

	/**
	 * 删除商品数据
	 * by 严俊杰1.08
	 */
	@RequestMapping("/delComm.action")
	@ResponseBody
	public String delComm(String comid)
	{

		System.out.println("+++++++++comid+++++++++++" + comid);
		int x = mallService.delComm(comid);
		return x + "";
	}


	/**
	 * 新增商品数据
	 * by 严俊杰1.08
	 */
	@RequestMapping("/addComm.action")
	public String addCommPage()
	{
		return "addcommpic";
	}



	@RequestMapping(value = "/doUpload.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doUpload(HttpServletRequest request)
	{
		Map map = new HashMap<String, Object>();
		//获得文件集合
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartHttpServletRequest.getFiles("file");

		if ("".equals(files.get(0).isEmpty()))
		{
			//返回选择文件提示
			map.put("msg", "notEixst");
			return map;
		}

		//商品名
		String commName = request.getParameter("commName");
		//类型
		String type = request.getParameter("type");
		//库存
		String commNum = request.getParameter("commNum");
		//原价
		String price = request.getParameter("price");
		//优惠后的价格
		String newPrice = request.getParameter("newPrice");
		//是否设置推荐
		String setHome = request.getParameter("setHome");
		//说明
		String commContent = request.getParameter("commContent");
		//上架人的id
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		RequestContextHolder.setRequestAttributes(sra, true);
		request = sra.getRequest();
		Object wid = request.getSession().getAttribute("wid");
		YjjTblCommodity commodity = new YjjTblCommodity();
		System.out.println("----------commName----------------" + commName);
		System.out.println("----------type----------------" + type);
		System.out.println("----------commNum----------------" + commNum);
		System.out.println("----------price----------------" + price);
		System.out.println("----------newPrice----------------" + newPrice);
		System.out.println("----------setHome----------------" + setHome);
		System.out.println("----------commContent----------------" + commContent);
		System.out.println("----------wid----------------" + wid);
		commodity.setComtitle(commName);
		commodity.setType(Integer.valueOf(type));
		commodity.setComnum(Integer.valueOf(commNum));
		commodity.setPrice(Integer.valueOf(price));
		commodity.setNewprice(Integer.valueOf(newPrice));
		commodity.setHome(setHome);
		commodity.setComconntext(commContent);
		commodity.setComdate(getDate());
		commodity.setStatusid(8);
		commodity.setWid((int) wid);
		//将图片的基本信息保存到数据库,并获得新增的商品id
		int comid = mallService.addComm(commodity);
		if (comid > 0)
		{
			for (MultipartFile file : files)
			{
				System.out.println("--------------" + file.getOriginalFilename());
				//获得上传过来文件带后缀的名字
				String filename = file.getOriginalFilename();
				System.out.println("----------filename------------" + filename);
				// 后缀名
				String suffixName = filename.substring(filename.lastIndexOf("."));
				System.out.println("------------------suffixName--------------" + suffixName);
				//前缀
				String prefixName = filename.substring(0, filename.indexOf("."));
				System.out.println("------------------prefixName--------------" + prefixName);
				//构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
				String realPath = REAL_PATH + "wechatPicUpload";
				//存放上传文件的文件夹
				File file1 = new File(realPath);
				if (!file1.isDirectory())
				{
					file1.mkdirs();
				}

				String uuid = UUID.randomUUID().toString().replace("-", "");
				String newName = uuid + "_" + filename;
				System.out.println("----------newName-------------" + newName);
				//file1.getAbsolutePath()获得绝对路径.file1.getpath()相对路径
				//File.separator自动补斜杠
				String url = file1.getPath() + File.separator + newName;

				System.out.println("-----file1.getAbsolutePath()------" + file1.getAbsolutePath());
				System.out.println("-----file1.getpath()------" + file1.getPath());
				//工程文件所在的路径
				System.out.println("-----System.getProperty('user.dir')------" + System.getProperty("user.dir"));

				File newFile = new File(url);
				System.out.println(newFile);
				try
				{
					//保存文件到服务器
					file.transferTo(newFile);
					YjjTblCommodityPic commodityPic = new YjjTblCommodityPic();
					commodityPic.setComid(comid);
					//将路径中的\换成/
					commodityPic.setUrl(newFile.getPath().replace("\\", "/"));
					//上传的书籍写入数据库中
					mallService.addCommPic(commodityPic);
					//返回json
					map.put("msg", "ok");
				} catch (IOException e)
				{
					map.put("msg", "error");
				}
			}
		}
		return map;
	}


}












