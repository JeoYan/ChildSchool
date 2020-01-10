package com.great.childschool.controller;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.service.YjjBookReadService;
import com.great.childschool.tools.ZipUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.math.BigDecimal;

import java.util.*;



import static com.great.childschool.tools.YjjTools.getDatea;

/**
 * 亲子阅读控制类
 * by 严俊杰
 */
@Controller
@RequestMapping("/readBook")
public class YjjReadBookController
{

	@Resource
	private YjjBookReadService bookReadService;

	/**
	 * 调用亲子阅读页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("/readBookPage.action")
	public String callParentLoginPage()
	{
		return "readbookmanage";
	}

	/**
	 * 获得亲子读物数据显示
	 * by 严俊杰12.20
	 */
	@RequestMapping("/getBookTable.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询亲子读物")
	public YjjTableData table(HttpServletRequest request){
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		String bName = request.getParameter("bName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		System.out.println("---------limit------------" + limit);
		System.out.println("---------page------------" + page);
		System.out.println("---------bName------------" + bName);
		System.out.println("---------startDate------------" + startDate);
		System.out.println("---------endDate------------" + endDate);
		YjjSearchInfo searchInfo = new YjjSearchInfo();
		searchInfo.setLimit(Integer.valueOf(limit));
		searchInfo.setPage(Integer.valueOf(page));
		searchInfo.setbName(bName);
		searchInfo.setStartDate(startDate);
		searchInfo.setEndDate(endDate);
		List<YjjTblReadbook> list = bookReadService.findBook(searchInfo);
		List<YjjTblReadbook> list1 =  bookReadService.totalPage(searchInfo);

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
	 * 调用亲子阅读页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("/callAddPage.action")
	public String callAddPage()
	{
		return "addreadbook";
	}

	/**
	 * 调用增加亲子阅读图片页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("/addBookPage.action")
	public String addBookPage()
	{
		return "addbookpage";
	}

	/**
	 * 调用增加亲子阅读图片页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("/updateBookPage.action")
	public String updateBookPage()
	{
		return "updatebookpage";
	}

	/**
	 * 调用修改亲子阅读页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("/updateReadBook.action")
	public String updateReadBook()
	{
		return "updatereadbook";
	}






	/**
	 * 调用增加亲子阅读图片页面
	 * by 严俊杰 12.20
	 */
	@RequestMapping("getBookPage.action")
	@ResponseBody
	@Log(operationType = "查询操作", operationName = "查询教师")
	public YjjTableData getPageDate(HttpServletRequest request){
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		String bName = request.getParameter("bName");
		System.out.println("---------limit------------" + limit);
		System.out.println("---------page------------" + page);
		System.out.println("---------bName------------" + bName);
		YjjSearchInfo searchInfo = new YjjSearchInfo();
		searchInfo.setLimit(Integer.valueOf(limit));
		searchInfo.setPage(Integer.valueOf(page));
		searchInfo.setbName(bName);
		List<YjjTblBookPage> list = bookReadService.findBookPage(searchInfo);
		List<YjjTblBookPage> list1 =  bookReadService.totalBookPage(searchInfo);

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
	 * 设置新新加表格的页数
	 * by 严俊杰 12.23
	 */
	@RequestMapping("/getNewPage.action")
	@ResponseBody
	public String getNewPage(String bookName)
	{

		System.out.println("---------bookName-----------"+bookName);
		 int newpage;
		//获得绘本的最后一页
		YjjTblBookPage bookLastPage=bookReadService.findLastPage(bookName);
		if(bookLastPage==null){
			newpage=1;
		}else{
		newpage=bookLastPage.getPageNum()+1;
		}
		return newpage+"";
	}







	/**
	 * 上传绘本及保存数据库
	 * by 严俊杰 12.23
	 */

	/**
	项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
	 */
	public final static String UPLOAD_PATH_PREFIX =System.getProperty("user.dir") + "/src/main/resources/static/";
	@RequestMapping("/doUpload.action")
	@ResponseBody
	public Map<String,Object> doUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
	{
		Map map = new HashMap<String,Object>();
		System.out.println("-------------fileName----------------"+request.getParameter("fileName"));

		if(file.isEmpty()){
			//返回选择文件提示
			map.put("msg","notEixst");
			return map;
		}

			//书名
		String bookName=request.getParameter("bookName");
		System.out.println("-------------bookName----------------"+bookName);
		//页码
		String pageNum = request.getParameter("pageNum");
		//对应页码的说明
		String pageContent = request.getParameter("pageContent");
		//上传获得的文件名
		String filename = file.getOriginalFilename();
		//上传人的id
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		RequestContextHolder.setRequestAttributes(sra, true);
		request = sra.getRequest();
		Object wid = request.getSession().getAttribute("wid");
		// 后缀名
		String suffixName = filename.substring(filename.lastIndexOf("."));
		System.out.println("------------------suffixName--------------" + suffixName);
		//前缀
		String prefixName =filename.substring(0, filename.indexOf("."));
		System.out.println("------------------prefixName--------------" + prefixName);

		//构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
		String realPath =UPLOAD_PATH_PREFIX+"uploadFile";
//		System.out.println("------------------realPath--------------" + realPath);

		//存放上传文件的文件夹
		File file1 = new File(realPath);
		if(!file1.isDirectory()){
			file1.mkdirs();
		}

		String uuid = UUID.randomUUID().toString().replace("-", "");
		String newName = uuid + filename.substring(filename.lastIndexOf("."));
		System.out.println("----------newName-------------"+newName);


		//file1.getAbsolutePath()获得绝对路径.file1.getpath()相对路径
		String url=file1.getPath() + File.separator +prefixName+"_"+ newName;

		System.out.println("-----file1.getAbsolutePath()------"+file1.getAbsolutePath());
		System.out.println("-----file1.getpath()------"+file1.getPath());
		//工程文件所在的路径
		System.out.println("-----System.getProperty('user.dir')------"+System.getProperty("user.dir"));


		File newFile = new File(url);
		System.out.println(newFile);




		//判断书籍是否已存在
		//1.根据书名查询数据库
		YjjTblReadbook readbook= bookReadService.checkBook(bookName);
		//绘本是否存在的标识
		Boolean status=false;
			try
			{
				//保存文件到服务器
//				file.transferTo(new File(savepath));

				file.transferTo(newFile);



				if(readbook==null)
				{
					//绘本不存在
					status=true;
					//保存到数据库
					//tbl_readbook需要的数据
					readbook = new YjjTblReadbook();
					readbook.setBookName(bookName);
					readbook.setWid((int) wid);
					readbook.setUploadDate(getDatea());
				}

				//tbl_book_page需要的数据
				YjjTblBookPage bookPage = new YjjTblBookPage();
				bookPage.setPageNum(Integer.valueOf(pageNum));
				bookPage.setBpContent(pageContent);
				bookPage.setWid((int)wid);
				bookPage.setbDate(getDatea());
//				bookPage.setUrl(savepath);

				//将路径中的\换成/
				bookPage.setUrl(newFile.getPath().replace("\\", "/"));
				//上传的书籍写入数据库中
				bookReadService.addBook(status,readbook,bookPage);
				//返回json
				map.put("msg", "ok");


			} catch (IOException e)
			{
				map.put("msg", "error");
				e.printStackTrace();
			}


		return map;
	}

	/**
	 * 删除tbl_book_page 的数据
	 * by 严俊杰 12.23
	 */
	@RequestMapping("/delPage.action")
	@ResponseBody
	public String delPage(String bpid,String bookid)
	{

		System.out.println("---------bpid-----------"+bpid);
		System.out.println("---------bookid-----------"+bookid);
		//获得绘本的最后一页
		int x=bookReadService.delPage(bpid,bookid);

		return x+"";
	}




	/**
	 * 上传绘本及保存数据库
	 * by 严俊杰 12.23
	 */

	@RequestMapping("/doUpdate.action")
	@ResponseBody
	public Map<String,Object> doUpdate(@RequestParam("file") MultipartFile file, HttpServletRequest request)
	{
		System.out.println("------------------9999---------------------");
		Map map = new HashMap<String,Object>();
		//书id
		String bpid = request.getParameter("bpid");
		//书名
		String bookName=request.getParameter("bookName");
		System.out.println("-------------bookName----------------"+bookName);
		//对应页码的说明
		String pageContent = request.getParameter("pageContent");
		//上传人的id
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		RequestContextHolder.setRequestAttributes(sra, true);
		request = sra.getRequest();
		Object wid = request.getSession().getAttribute("wid");
		//上传获得的文件名
		String filename = file.getOriginalFilename();
		//前缀
		String prefixName =filename.substring(0, filename.indexOf("."));
		System.out.println("------------------prefixName--------------" + prefixName);

		//构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
		String realPath =UPLOAD_PATH_PREFIX+"uploadFile";
		System.out.println("------------------realPath--------------" + realPath);
		//存放上传文件的文件夹
		File file1 = new File(realPath);
		if(!file1.isDirectory()){
			//递归生成文件夹
			file1.mkdirs();
		}

		String uuid = UUID.randomUUID().toString().replace("-", "");
		String newName = uuid + filename.substring(filename.lastIndexOf("."));
		System.out.println("----------newName-------------"+newName);

		//file1.getAbsolutePath()获得绝对路径.file1.getpath()相对路径
		String url=file1.getAbsolutePath() + File.separator +prefixName+"_"+ newName;
		File newFile = new File(url);
		System.out.println(newFile);

		try
		{
			//保存文件到服务器
			file.transferTo(newFile);
			//tbl_book_page需要的数据
			YjjTblBookPage bookPage = new YjjTblBookPage();
			bookPage.setBpid(Integer.valueOf(bpid));
			bookPage.setBpContent(pageContent);
			bookPage.setWid((int)wid);
			bookPage.setbDate(getDatea());
			//将路径中的\换成/
			bookPage.setUrl(newFile.getPath().replace("\\", "/"));
			//上传的书籍写入数据库中
			bookReadService.updateBookPage(bookPage);
			//返回json
			map.put("msg", "ok");


		} catch (IOException e)
		{
			map.put("msg", "error");
			e.printStackTrace();
		}


		return map;
	}


	//图片预览
	/**
	 * 显示单张图片
	 * 1.JSP采用<img src="">标签    2.后台获取图片路径并以流的方式输出
	 * @return
	 */
	@RequestMapping("showImage.action")
	public void showImageByType( String url,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("----------url-------------"+url);

		InputStream inputStream = null;
		OutputStream writer = null;
		try {
			inputStream = new FileInputStream(new File( url));
					writer = response.getOutputStream();

			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buf)) != -1) {
				writer.write(buf, 0, len); //写
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally{
			try {
				if(inputStream != null){
					inputStream.close();
				}
				if(writer != null){
					writer.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * 删除tbl_book_page和tbl_readbook 的数据
	 * by 严俊杰 12.23
	 */
	@RequestMapping("/delBook.action")
	@ResponseBody
	public String delBook(String bookid)
	{
		System.out.println("---------bookid-----------"+bookid);
		//获得绘本的最后一页
		int x=bookReadService.delBook(bookid);
		return x+"";
	}

		//更新书名
		@RequestMapping("/updateBookName.action")
		@ResponseBody
		public String updateBookName(String bookid,String bName)
		{
			System.out.println("---------bookid-----------"+bookid);
			//获得绘本的最后一页
			int x=bookReadService.updateBookName(bookid,bName);
			return x+"";
		}

	//获取数本信息数据
	@RequestMapping("/getBook.action")
	@ResponseBody
	public YjjWatchPicInfo getBook(String bookid)
	{
		System.out.println("---------bookid6666666-----------"+bookid);
		YjjWatchPicInfo watchPicInfo=new  YjjWatchPicInfo();
		List<YjjPicInfo> list=new ArrayList<>();
		List<YjjTblBookPage> pagelist= bookReadService.findPage(bookid);
		System.out.println("----------pagelist----------------"+pagelist.size());

		for (YjjTblBookPage bookPage : pagelist)
		{
			YjjPicInfo picInfo=new YjjPicInfo();
			picInfo.setAlt(bookPage.getBpContent());
			picInfo.setPid(bookPage.getBpid());
			String url ="/ChildSchool/readBook/showImage.action?url="+bookPage.getUrl();
			picInfo.setSrc(url);
			System.out.println("----------url----------------"+url);
			list.add(picInfo);
		}

		watchPicInfo.setData(list);
		watchPicInfo.setId(Integer.valueOf(bookid));
		watchPicInfo.setStart(0);
		watchPicInfo.setTitle("");

		//获得绘本的最后一页
		return watchPicInfo;
	}


	/**
	 * 下载绘本
	 */
	//获取数本信息数据
	@RequestMapping("/downloadBook.action")
	public ResponseEntity<byte[]>  batchDownLoadDatumList(String bookid,String bookName, HttpServletResponse response) {
		ResponseEntity<byte[]> responseEntity=null;
		System.out.println("---------------bookid------"+bookid);
		System.out.println("---------------bookName------"+bookName);
			//查询文件列表
			List<YjjTblBookPage> bookList = bookReadService.findPage(bookid);
			//查询获得书名

			//压缩文件
			List<File> files = new ArrayList<>();
			for (YjjTblBookPage bookPage : bookList)
			{
				File file = new File(bookPage.getUrl());
				files.add(file);
			}
		try
		{
			responseEntity=ZipUtil.downLoadFiles(files,bookName,response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	return responseEntity;
	}







}










