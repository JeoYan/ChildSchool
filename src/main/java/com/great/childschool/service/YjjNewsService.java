package com.great.childschool.service;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.mapper.YjjNewsMapper;
import com.great.childschool.mapper.YjjWorkerPowerControlMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 新闻中心管理业务层
 * by 严俊杰
 */

@Service
public class YjjNewsService
{

	@Resource
	private YjjNewsMapper newsMapper;

	/**
	 * 获得表格数据
	 * by 严俊杰
	 */

	@Transactional
	public List<YjjTblNews> findNews(YjjSearchInfo searchInfo)
	{
		return newsMapper.findNews(searchInfo);
	}

	/**
	 * 总条数 by 严俊杰
	 * by 严俊杰
	 */

	@Transactional
	public List<YjjTblNews> totalPage(YjjSearchInfo searchInfo)
	{
		return newsMapper.totalPage(searchInfo);
	}


	/**
	 * 新增新闻
	 *
	 * @return
	 */
	@Transactional
	public int addNews(YjjTblNews news)
	{
		return newsMapper.addNews(news);
	}

	/**
	 * 删除新闻
	 *
	 * @return
	 */
	@Transactional
	public int delNews(String nid)
	{
		return newsMapper.delNews(nid);
	}



	/**
	 * 更新新闻
	 *
	 * @return
	 */
	@Transactional
	public int updateNews(YjjTblNews news)
	{
		return newsMapper.updateNews(news);
	}




}
