package com.great.childschool.controller;


import com.google.gson.Gson;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.TbTable;
import com.great.childschool.service.TjzBackService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/BackAction")
public class TjzAdminHandler
{
	@Resource
	private TjzBackService tjzBackService;



//	@RequestMapping("/findByTbDocument.action")
//	@ResponseBody
//	@Log(operationType = "查询操作", operationName = "查询文档")
//	public TbTable findByTbDocument(String page, String limit, String documentName)
//	{
//		TbTable tbBean = tjzBackService.findByTbDocument(page, limit, documentName);
//		return tbBean;
//	}


//
//	@RequestMapping("/Download.action")
//	public ResponseEntity<byte[]> export(String fileName) throws IOException
//	{
//		System.out.println("文件下载："+fileName);
//		File file = new File("G:\\传一科技java培训\\" + fileName);//上传保存的文件路径
////		File file = new File("G:\\传一科技java培训\\日报2019.txt");//上传保存的文件路径
//		HttpHeaders headers = new HttpHeaders();
//		// MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
//		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//		headers.setContentDispositionFormData("attachment", fileName);
//		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
//	}
//
//
//	@RequestMapping("/handleRequest.action")
//	public ModelAndView handleRequest()
//	{
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("Back-login");
//		return modelAndView;
//	}
//
//
//	//查询角色
//	@RequestMapping("/BackTree.action")
//	public String treePage(HttpServletRequest req)
//	{
//		List<TbRole>roleList=tjzBackService.findRole();
//		if (null != roleList)
//		{
//			System.out.println("success");
//			req.setAttribute("roleList",roleList);
//			return "Back-tree";
//		} else
//		{
//			System.out.println("fail");
//			return "Back-main";
//
//		}
//	}
//
//	@RequestMapping("/updateTree.action")
//	@ResponseBody
//	@Log(operationType = "更新操作", operationName = "更新树")
//	public TbTable   backUpdateTree(String data,String roleId)
//	{
//		TbTable tbTable = new TbTable();
//		int updateRoleId=Integer.valueOf(roleId);
//		List<LayuiTreeData> list = new Gson().fromJson(data, new TypeToken<List<LayuiTreeData>>(){}.getType());
//		Map<String, Object> map=new HashMap<String, Object>();
//		int flag =0;
//
//		flag= tjzBackService.clearChecked(updateRoleId);
//		if (flag > 0)
//		{
//			for (int i = 0; i <list.size() ; i++)
//			{
//				map.put("menuId",Integer.valueOf(list.get(i).getId()));
//				map.put("roleId",updateRoleId);
//				flag = tjzBackService.updateTree(map);
//				List<LayuiTreeData> childMenu =list.get(i).getChildren();
//				for (int j = 0; j <childMenu.size() ; j++)
//				{
//					map.put("menuId",Integer.valueOf(childMenu.get(j).getId()));
//					map.put("roleId",updateRoleId);
//					flag = tjzBackService.updateTree(map);
//					if (flag > 0)
//					{
//						tbTable.setMsg("1");
//					} else
//					{
//						tbTable.setMsg("0");
//					}
//
//				}
//			}
//		} else
//		{
//			tbTable.setMsg("0");
//		}
//
//
//		return tbTable;
//
//	}
//
//	@RequestMapping("/findTree.action")
//	@ResponseBody
//	@Log(operationType = "查询操作", operationName = "查询树")
//	public List<LayuiTreeData>  backFindTree(String adminId,String roleId)
//	{
//		List<LayuiTreeData> list=new ArrayList<LayuiTreeData>();
//		List<TbMenu> menuList = tjzBackService.findFirstMenu(Integer.valueOf(adminId));
//		Map<String, Object> map=new HashMap<String, Object>();
//		int updateRoleId=Integer.valueOf(roleId);
//		for (int i = 0; i <menuList.size() ; i++)
//		{
//			System.out.println(menuList.get(i).getcMenuId());
//				LayuiTreeData layuiTreeData=new LayuiTreeData();
//				layuiTreeData.setTitle(menuList.get(i).getpMenuName());
//				layuiTreeData.setId(String.valueOf(menuList.get(i).getpMenuId()));
//				layuiTreeData.setSpread(false);
//				layuiTreeData.setChecked(false);
//
//			map.put("pMenuId",menuList.get(i).getcMenuId());
//			map.put("roleId",updateRoleId);
//				layuiTreeData.setChildren(findChild(map));
//
//				list.add(layuiTreeData);
//		}
//		return list;
//	}
//
//	public List<LayuiTreeData>  findChild(Map<String, Object> map)
//	{
//		List<LayuiTreeData> list=new ArrayList<LayuiTreeData>();
//		List<TbMenu> menuList = tjzBackService.findChildMenu(map);
//
//		for (int i = 0; i <menuList.size() ; i++)
//		{
//			LayuiTreeData layuiTreeData=new LayuiTreeData();
//			layuiTreeData.setTitle(menuList.get(i).getcMenuName());
//			layuiTreeData.setId(String.valueOf(menuList.get(i).getcMenuId()));
//					if (menuList.get(i).getMenuChecked().equals("1")){
//						layuiTreeData.setChecked(true);
//					}else {
//						layuiTreeData.setChecked(false);
//					}
//			layuiTreeData.setSpread(false);
//			list.add(layuiTreeData);
//		}
//		return list;
//	}
//
//
//	@RequestMapping("/login.action")
//	@Log(operationType = "登录操作", operationName = "管理员登录")
//	public String backLogin(String loginID, String loginPass,HttpServletRequest req, HttpServletResponse resp)
//	{
//		TbAdmin tbAdmin = tjzBackService.login(loginID, loginPass);
//		List<TbMenu> menuList = tjzBackService.findMenu(loginID);
//		HashMap<String, ArrayList<TbMenu>> map = new HashMap<>();
//		for (int i = 0; i < menuList.size(); i++)
//		{
//			TbMenu menu = menuList.get(i);
//			if (map.containsKey(menu.getpMenuName()))
//			{
//				ArrayList<TbMenu> list = map.get(menu.getpMenuName());
//				list.add(menu);
//			} else
//			{
//				ArrayList<TbMenu> list = new ArrayList<>();
//				list.add(menu);
//				map.put(menu.getpMenuName(), list);
//			}
//		}
//		if (null != tbAdmin)
//		{
//			String adminName=tbAdmin.getAdminName();
//			int adminId=tbAdmin.getAdminId();
//			System.out.println("success");
//			int roleId=tjzBackService.findLoginRole(Integer.valueOf(loginID));
//			req.setAttribute("menu",map);
//			req.setAttribute("adminName",adminName);
//			req.getSession().setAttribute("adminName",adminName);
//			req.getSession().setAttribute("roleId",roleId);
//			req.getSession().setAttribute("adminId",adminId);
//			return "Back-main";
//		} else
//		{
//			System.out.println("fail");
//			return "Back-login";
//
//		}
//	}
//
//
//	@RequestMapping("/findUser.action")
//	@ResponseBody
//	@Log(operationType = "查询操作", operationName = "查询用户")
//	public TbTable backFindUser(String page, String limit, String startDate, String endDate, String userName)
//	{
//		TbTable tbBean = tjzBackService.showTable(page, limit, startDate, endDate, userName);
//		return tbBean;
//	}
//
//	@RequestMapping("/findLog.action")
//	@ResponseBody
//	public TbTable FindLog(String page, String limit, String startDate, String endDate, String userName)
//	{
//		TbTable tbBean = tjzBackService.showLogTable(page, limit, startDate, endDate, userName);
//		return tbBean;
//	}
//
//
//	@RequestMapping("/delUser.action")
//	@ResponseBody
//	@Log(operationType = "删除操作", operationName = "删除用户")
//	public TbTable backDelUser(HttpServletRequest req, HttpServletResponse resp)
//	{
//		String userId = req.getParameter("userId");
//		int flag = tjzBackService.delUser(Integer.parseInt(userId));
//		TbTable tbTable = new TbTable();
//		if (flag > 0)
//		{
//			tbTable.setMsg("1");
//		} else
//		{
//			tbTable.setMsg("0");
//		}
//		return tbTable;
//	}
//
//	@RequestMapping("/addUser.action")
//	@ResponseBody
//	@Log(operationType = "增加操作", operationName = "添加用户")
//	public TbTable backAddUser(HttpServletRequest req, HttpServletResponse resp)
//	{
//		String userName = req.getParameter("userName");
//		String userState = req.getParameter("userState");
//		TbUser user = new TbUser();
//		user.setUserName(userName);
//		user.setUserState(userState);
//		int flag = tjzBackService.addlUser(user);
//		TbTable tbTable = new TbTable();
//		if (flag > 0)
//		{
//			tbTable.setMsg("1");
//		} else
//		{
//			tbTable.setMsg("0");
//		}
//		return tbTable;
//	}
//
//	@RequestMapping("/updateUser.action")
//	@ResponseBody
//	@Log(operationType = "更新操作", operationName = "更新用户")
//	public TbTable backUpdateUser(HttpServletRequest req, HttpServletResponse resp)
//	{
//		String userId = req.getParameter("userId");
//		String userName = req.getParameter("userName");
//		String userState = req.getParameter("userState");
//		TbUser user = new TbUser();
//		user.setUserId(Integer.parseInt(userId));
//		user.setUserName(userName);
//		user.setUserState(userState);
//		int flag = tjzBackService.updateUser(user);
//		TbTable tbTable = new TbTable();
//		if (flag > 0)
//		{
//			tbTable.setMsg("1");
//		} else
//		{
//			tbTable.setMsg("0");
//		}
//		return tbTable;
//	}
//
//	@RequestMapping(value = "/upload.action")
//	@ResponseBody
//	@Log(operationType = "上传操作", operationName = "上传文件")
//		public Map<String,Object> backUpload(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request)
//		{
//			String originalFilename =file.getOriginalFilename();
//			System.out.println(originalFilename+"----------文件名-----------");
//			String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//			String documentName=request.getParameter("documentName");
//			String documentBrief=request.getParameter("documentBrief");
//			String documentScore=request.getParameter("documentScore");
//			TbDocument document=new TbDocument();
//			document.setDocumentName(documentName);
//			document.setDocumentBrief(documentBrief);
//			document.setDocumentScore(documentScore);
//			String newFilename = documentName + "." + suffix;
//			String saveFilePath = "G:\\传一科技java培训\\" + newFilename;
//			Map<String,Object> map = new HashMap<String,Object>();
//			try
//			{
//				//保存到数据库
//				int flag = tjzBackService.upload(document);
//				if (flag>0){
//					//保存文件到服务器
//					file.transferTo(new File(saveFilePath));
//					map.put("msg","ok");
//				}else {
//					map.put("msg","error");
//				}
//			} catch (IOException e)
//			{
//				map.put("msg","error");
//				e.printStackTrace();
//			}
//			return map;
//		}


}
