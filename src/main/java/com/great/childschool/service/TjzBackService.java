package com.great.childschool.service;



import com.great.childschool.entity.TjzTbLog;
import com.great.childschool.entity.TjzTbTable;
import com.great.childschool.entity.TjzTbClassRoom;
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




	//用户分页
	@Transactional
	public TjzTbTable courseManagement(String page, String limit, String startDate, String endDate, String cName)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("cName", cName);
		List<TjzTbClassRoom> list = tjzBackMapper.courseManagement(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.courseManagementNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}

	//日志分页
	@Transactional
	public TjzTbTable showLogTable(String page, String limit, String startDate, String endDate, String userName)
	{
		TjzTbTable tbBean = new TjzTbTable();
		Map<String, Object> map = new HashMap<String, Object>();
		int psize = Integer.valueOf(limit);
		int pstart = (Integer.valueOf(page) - 1) * psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userName", userName);
		List<TjzTbLog> list = tjzBackMapper.findLog(map);
		tbBean.setData(list);
		tbBean.setCount(String.valueOf(tjzBackMapper.findLogNum(map)));
		tbBean.setCode("0");
		tbBean.setMsg(null);
		return tbBean;
	}


	//增加日志
	@Transactional
	public int addLog(TjzTbLog log)
	{
		int flag = tjzBackMapper.addLog(log);
		return flag;

	}


}