package com.great.childschool.service;



import com.great.childschool.entity.TbLog;
import com.great.childschool.entity.TbTable;
import com.great.childschool.entity.TjzTbClassRoom;
import com.great.childschool.entity.TjzTbCourse;
import com.great.childschool.mapper.TjzBackMapper;
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
	private TjzBackMapper tjzBackMapper;


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
//		List<TbDocument> list = tjzBackMapper.findByTbDocument(map);
//		tbBean.setData(list);
//		tbBean.setCount(String.valueOf(tjzBackMapper.findByTbDocumentNum(map)));
//		tbBean.setCode("0");
//		tbBean.setMsg(null);
//		return tbBean;
//	}



//	//查询登录角色
//	@Transactional
//	public int findLoginRole(int adminId)
//	{
//		int roleId = tjzBackMapper.findLoginRole(adminId);
//		return roleId;
//	}
//
//	//查询角色
//	@Transactional
//	public List<TbRole> findRole()
//	{
//		List<TbRole> list = tjzBackMapper.findRole();
//		return list;
//	}
//
//	//修改树
//	@Transactional
//	public int updateTree(Map<String, Object> map)
//	{
//		int flag = tjzBackMapper.updateTree(map);
//		return flag;
//	}
//
//	//菜单置为未选
//	@Transactional
//	public int clearChecked(int updateRoleId)
//	{
//		int flag = tjzBackMapper.clearChecked(updateRoleId);
//		return flag;
//	}
//
//	//查询一级菜单
//	@Transactional
//	public List<TbMenu> findFirstMenu(int personID)
//	{
//		List<TbMenu>  firstMenu=tjzBackMapper.findFirstMenu(personID);
//		return firstMenu;
//	}
//
//	//查询二级菜单
//	@Transactional
//	public List<TbMenu> findChildMenu(Map<String, Object> map)
//	{
//
//		List<TbMenu> childMenu= tjzBackMapper.findChildMenu(map);
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
//		TbAdmin tbAdmin1 = tjzBackMapper.findByTbAdmin(tbAdmin);
//		return tbAdmin1;
//	}
//
//	//菜单
//	@Transactional
//	public List<TbMenu> findMenu(String personID)
//	{
//		List<TbMenu>menuList=tjzBackMapper.findMenu(Integer.valueOf(personID));
//		return menuList;
//	}

	/**
	 * 课程管理分页
	 */
	@Transactional
	public TbTable findClassRooms(String page, String limit, String startDate, String endDate, String cName)
	{
		TbTable tbBean = new TbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("cName", cName);
		List<TjzTbClassRoom> list = tjzBackMapper.findClassRooms(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.findClassRoomNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}

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
		List<TbLog> list = tjzBackMapper.findLog(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.findLogNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}
//
//	//删除
//	@Transactional
//	public int delUser(int userId)
//	{
//		int flag = tjzBackMapper.deleteTbUserById(userId);
//		return flag;
//
//	}


//	//增加
//	@Transactional
//	public int addlUser(TbUser user)
//	{
//		int flag = tjzBackMapper.addTbUser(user);
//		return flag;
//
//	}

	//增加日志
	@Transactional
	public int addLog(TbLog log)
	{
		int flag = tjzBackMapper.addLog(log);
		return flag;

	}

//	//修改
//	@Transactional
//	public int updateUser(TbUser user)
//	{
//		int flag = tjzBackMapper.updateUser(user);
//		return flag;
//	}
//
//	//文件上传
//	@Transactional
//	public int upload(TbDocument document)
//	{
//		int flag = tjzBackMapper.upload(document);
//		return flag;
//	}

}