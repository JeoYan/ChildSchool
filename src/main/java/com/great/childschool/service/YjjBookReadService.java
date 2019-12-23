package com.great.childschool.service;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.mapper.YjjBookReadMapper;
import com.great.childschool.mapper.YjjWorkerPowerControlMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 亲子阅读业务层
 * by 严俊杰 12.20
 */

@Service
public class YjjBookReadService
{

	@Resource
	private YjjBookReadMapper bookReadMapper;


	//获得表格数据 by 严俊杰
	@Transactional
	public List<YjjTblReadbook> findBook(YjjSearchInfo searchInfo)
	{
		return bookReadMapper.findBook(searchInfo);
	}

	//总条数 by 严俊杰
	@Transactional
	public List<YjjTblReadbook> totalPage(YjjSearchInfo searchInfo)
	{
		return bookReadMapper.totalPage(searchInfo);
	}
















}
