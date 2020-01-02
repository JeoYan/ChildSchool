package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcBabyDeptMapper;
import com.great.childschool.mapper.YjjHomeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YjjHomeService
{

	@Resource
	private YjjHomeMapper homeMapper;


	/**
	 * 获得公告信息
	 * by 严俊杰
	 */
	@Transactional
	public List<YjjTblNotice> findNotice()
	{

		return homeMapper.findNotice();
	}

	/**
	 * 获得新闻信息
	 * by 严俊杰
	 */
	@Transactional
	public List<YjjTblNews> findNews()
	{

		return homeMapper.findNews();
	}

	/**
	 * 获得某一条公告信息
	 * by 严俊杰
	 */
	@Transactional
	public YjjTblNotice getNotice(String noid)
	{

		return homeMapper.getNotice(noid);
	}

	/**
	 * 获得某一条新闻信息
	 * by 严俊杰
	 */
	@Transactional
	public YjjTblNews getNews(String nid)
	{

		return homeMapper.getNews(nid);
	}



}
