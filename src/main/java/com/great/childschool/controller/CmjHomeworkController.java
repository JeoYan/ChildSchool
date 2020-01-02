package com.great.childschool.controller;

import com.great.childschool.entity.CmjHomework;
import com.great.childschool.entity.CmjTblBaby;
import com.great.childschool.entity.DataBean;
import com.great.childschool.service.CmjHomeworkService;
import com.great.childschool.tools.DateFormatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *@className CmjHomeworkController
 *@description 作业控制层
 *@author MJChen
 *@date 2019/12/25 10:18
 *@version 1.0
 **/
@Controller
@RequestMapping("/HomeworkController")
public class CmjHomeworkController
{
	@Resource
	private CmjHomeworkService homeworkService;
	@RequestMapping("/query/{url}")
	public String turnToJsp(@PathVariable(value = "url") String path){
		return path;
	}

	@RequestMapping("/turnPostHomework")
	public ModelAndView turnToJsp(){
		ModelAndView modelAndView = new ModelAndView();
		Date date= new Date();
		String dateString = DateFormatUtil.dateString(date);
		modelAndView.setViewName("posthomework");
		modelAndView.addObject("date",dateString);
		return modelAndView;
	}

	@RequestMapping("/lookHomework")
	@ResponseBody
	public DataBean turnToLookHomework(String page,String limit){
		int size = Integer.valueOf(limit);
		int curPage = (Integer.valueOf(page)-1)*size;
		Date date= new Date();
		String dateString = DateFormatUtil.dateString(date);
		DataBean<HashMap<String, String>> dataBean = new DataBean<>();
		List<HashMap<String, String>> list = homeworkService.classHomework(curPage, size, dateString);
		Integer count = homeworkService.countClassHomework(dateString);
		dataBean.setData(list);
		dataBean.setCount(count);
		return dataBean;
	}

	@RequestMapping("/queryPastHomework")
	@ResponseBody
	public DataBean<CmjHomework> queryPastHomework(String page,String limit){
		int size = Integer.valueOf(limit);
		int curPage = (Integer.valueOf(page)-1)*size;
		Date date= new Date();
		String dateString = DateFormatUtil.completeTime(date);
		DataBean<CmjHomework> dataBean = new DataBean<>();
		List<CmjHomework> pastHomework = homeworkService.findPastHomework(1, dateString, curPage, size);
		Integer count = homeworkService.countPastHomework(1,dateString);
		dataBean.setData(pastHomework);
		dataBean.setCount(count);
		return dataBean;
	}

	@RequestMapping("/turnBabyHomework")
	public ModelAndView turnJsp(){
		ModelAndView modelAndView = new ModelAndView();
		Date date= new Date();
		String dateString = DateFormatUtil.dateString(date);
		modelAndView.setViewName("babyhomework");
		List<CmjHomework> classHomework = homeworkService.findClassHomework(dateString);
		modelAndView.addObject("homework",classHomework);
		return modelAndView;
	}


	@RequestMapping("/download")
	public void download(HttpServletResponse response,int bWid){
		String url = homeworkService.findBabyHomeworkById(bWid);
		//设置字符名以及文件名、后缀
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data");
		String fileName = url.substring(url.lastIndexOf("\\"),url.length());
		try {
			response.setHeader("Content-Disposition", "attachment;fileName="+new String(fileName.getBytes(), "iso8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String path = url;
		File file = new File(path);
		if (file.exists()){
			System.out.println("文件有找到");
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len = bis.read(bytes))!= -1){
					bos.write(bytes,0,len);
					bos.flush();
				}
				bos.close();
				bis.close();
				System.out.println("写出完毕");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("文件没找到");
		}
	}


	@RequestMapping("/downloadHomework")
	public void downFile(HttpServletResponse response,String hid){
		CmjHomework homework = homeworkService.findHomeworkById(Integer.valueOf(hid));
		//设置字符名以及文件名、后缀
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data");
		String fileName = homework.gethName();
		try {
			response.setHeader("Content-Disposition", "attachment;fileName="+new String(fileName.getBytes(), "iso8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String path = homework.getUrl();
		File file = new File(path);
		if (file.exists()){
			System.out.println("文件有找到");
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len = bis.read(bytes))!= -1){
					bos.write(bytes,0,len);
					bos.flush();
				}
				bos.close();
				bis.close();
				System.out.println("写出完毕");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("文件没找到");
		}
	}


	@RequestMapping("/upload")
	@ResponseBody
	public Map<String,Object> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file,String flag)  {
		Map map = new HashMap<String,Object>();
		//如果文件不为空，写入上传路径
		try
		{
			if (!file.isEmpty()) {
				//上传文件名
				String filename = file.getOriginalFilename();
				File filepath = new File("D:fileUpload");
				if(!"teacher".equals(flag)){
					filepath = new File("D:homework");
				}

				//判断路径是否存在，如果不存在就创建一个
				if (!filepath.exists()) {
					filepath.mkdirs();
				}
				Path path = Paths.get(filepath.toString().substring(0,2),filepath.toString().substring(2),filename.substring(0,filename.lastIndexOf("."))+ UUID.randomUUID()+filename.substring(filename.lastIndexOf("."),filename.length()));
				//将上传文件保存到一个目标文件当中\
				file.transferTo(path);
				map.put("msg","ok");
				map.put("code",200);
				map.put("url",path.toString());
				map.put("filename",filename);
			}
		}catch (Exception e){
			map.put("msg","error");
			map.put("code",0);
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping("/submitHomework")
	@ResponseBody
	public String submitHomework(String fileUrl,int hid){
		Date date = new Date();
		String curDate = DateFormatUtil.completeTime(date);
		boolean b = homeworkService.submitHomework(6,hid,curDate,fileUrl);
		if (b){
			return "1";
		}
		return "0";
	}

	@RequestMapping("/markHomework")
	@ResponseBody
	public String markHomework(int bWid,String score){
		boolean b = homeworkService.markHomework(bWid,score);
		if (b){
			return "1";
		}
		return "0";
	}

	@RequestMapping("/postHomework")
	@ResponseBody
	public String postHomework(String filename,String fileUrl){
		Date date = new Date();
		String curDate = DateFormatUtil.completeTime(date);
		boolean b = homeworkService.postHomework(filename, fileUrl, curDate);
		if (b){
			return "1";
		}
		return "0";
	}
}
