package com.great.childschool.service;



import com.great.childschool.entity.TbLog;
import com.great.childschool.entity.TbTable;
import com.great.childschool.mapper.BackMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TjzBackService
{
	@Resource
	private BackMapper backMapper;


//	//用户分页
//	@Transactional
//	public TbTable findByTbDocument(String page, String limit, String documentName)
//	{
//		TbTable tbBean = new TbTable();
//		Map<String, Object> map = new HashMap<String, Object>();
//		int psize = Integer.valueOf(limit);
//		int pstart = (Integer.valueOf(page) - 1) * psize;
//		map.put("pstart", pstart);
//		map.put("psize", psize);
//		map.put("documentName", documentName);
//		List<TbDocument> list = backMapper.findByTbDocument(map);
//		tbBean.setData(list);
//		tbBean.setCount(String.valueOf(backMapper.findByTbDocumentNum(map)));
//		tbBean.setCode("0");
//		tbBean.setMsg(null);
//		return tbBean;
//	}



//	//查询登录角色
//	@Transactional
//	public int findLoginRole(int adminId)
//	{
//		int roleId = backMapper.findLoginRole(adminId);
//		return roleId;
//	}
//
//	//查询角色
//	@Transactional
//	public List<TbRole> findRole()
//	{
//		List<TbRole> list = backMapper.findRole();
//		return list;
//	}
//
//	//修改树
//	@Transactional
//	public int updateTree(Map<String, Object> map)
//	{
//		int flag = backMapper.updateTree(map);
//		return flag;
//	}
//
//	//菜单置为未选
//	@Transactional
//	public int clearChecked(int updateRoleId)
//	{
//		int flag = backMapper.clearChecked(updateRoleId);
//		return flag;
//	}
//
//	//查询一级菜单
//	@Transactional
//	public List<TbMenu> findFirstMenu(int personID)
//	{
//		List<TbMenu>  firstMenu=backMapper.findFirstMenu(personID);
//		return firstMenu;
//	}
//
//	//查询二级菜单
//	@Transactional
//	public List<TbMenu> findChildMenu(Map<String, Object> map)
//	{
//
//		List<TbMenu> childMenu= backMapper.findChildMenu(map);
//		return childMenu;
//	}
//
//	//登录
//	@Transactional
//	public TbAdmin login(String personID, String personPass)
//	{
//		TbAdmin tbAdmin = new TbAdmin();
//		tbAdmin.setAdminId(Integer.valueOf(personID));
//		tbAdmin.setAdminPass(Md5Test.toMD5(personPass));
//		TbAdmin tbAdmin1 = backMapper.findByTbAdmin(tbAdmin);
//		return tbAdmin1;
//	}
//
//	//菜单
//	@Transactional
//	public List<TbMenu> findMenu(String personID)
//	{
//		List<TbMenu>menuList=backMapper.findMenu(Integer.valueOf(personID));
//		return menuList;
//	}
//	//用户分页
//	@Transactional
//	public TbTable showTable(String page, String limit, String startDate, String endDate, String userName)
//	{
//		TbTable tbBean = new TbTable();
//		Map<String, Object> map = new HashMap<String, Object>();
//		int psize = Integer.valueOf(limit);
//		int pstart = (Integer.valueOf(page) - 1) * psize;
//		map.put("pstart", pstart);
//		map.put("psize", psize);
//		map.put("startDate", startDate);
//		map.put("endDate", endDate);
//		map.put("userName", userName);
//		List<TbUser> list = backMapper.findByTbUser(map);
//		tbBean.setData(list);
//		tbBean.setCount(String.valueOf(backMapper.findByTbUserNum(map)));
//		tbBean.setCode("0");
//		tbBean.setMsg(null);
//		return tbBean;
//	}

	//日志分页
	@Transactional
	public TbTable showLogTable(String page, String limit, String startDate, String endDate, String userName)
	{
		TbTable tbBean = new TbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userName", userName);
		List<TbLog> list = backMapper.findLog(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(backMapper.findLogNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}
//
//	//删除
//	@Transactional
//	public int delUser(int userId)
//	{
//		int flag = backMapper.deleteTbUserById(userId);
//		return flag;
//
//	}


//	//增加
//	@Transactional
//	public int addlUser(TbUser user)
//	{
//		int flag = backMapper.addTbUser(user);
//		return flag;
//
//	}

	//增加日志
	@Transactional
	public int addLog(TbLog log)
	{
		int flag = backMapper.addLog(log);
		return flag;

	}

//	//修改
//	@Transactional
//	public int updateUser(TbUser user)
//	{
//		int flag = backMapper.updateUser(user);
//		return flag;
//	}
//
//	//文件上传
//	@Transactional
//	public int upload(TbDocument document)
//	{
//		int flag = backMapper.upload(document);
//		return flag;
//	}

}