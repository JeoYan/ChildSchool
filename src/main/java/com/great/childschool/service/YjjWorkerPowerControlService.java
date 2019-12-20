package com.great.childschool.service;

import com.great.childschool.entity.*;
import com.great.childschool.mapper.YjjParentLoginMapper;
import com.great.childschool.mapper.YjjWorkerPowerControlMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教职工权限管理业务层
 * by 严俊杰
 */

@Service
public class YjjWorkerPowerControlService
{

	@Resource
	private YjjWorkerPowerControlMapper workerPowerControlMapper;

	/**
	 * 教职工权限管理
	 * by 严俊杰
	 */
	//获得表格数据 by 严俊杰
	@Transactional
	public List<TblWorker> findWorker(YjjSearchInfo searchInfo)
	{
		return workerPowerControlMapper.findWorker(searchInfo);
	}

	//总条数 by 严俊杰
	@Transactional
	public List<TblWorker> totalPage(YjjSearchInfo searchInfo)
	{
		return workerPowerControlMapper.totalPage(searchInfo);
	}

	//修改用户状态 by严俊杰
	@Transactional
	public int updateStatus(String wid, String sid)
	{
		return workerPowerControlMapper.updateStatus(wid, sid);
	}

	//获得用户对应的一级菜单mid by严俊杰
	@Transactional
	public List<TblMenu> findFirstMenu(int wid)
	{
		return workerPowerControlMapper.findFirstMenu(wid);
	}

	//获得父级菜单对应子的菜单 by严俊杰
	public List<TblMenu> findChildMenu(int wid, int mid)
	{
		return workerPowerControlMapper.findChildMenu(wid, mid);
	}

	//查询获得用户的拥有的菜单是否勾选 by严俊杰
	public YjjTblMenuRole findMenuStatus(int wid,int mid){

		return workerPowerControlMapper.findMenuStatus(wid, mid);
	}


}
