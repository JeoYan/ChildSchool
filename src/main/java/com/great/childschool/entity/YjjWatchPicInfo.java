package com.great.childschool.entity;

import java.util.List;

/**
 * 图片预览保存的格式
 * by 严俊杰
 *
 */


public class YjjWatchPicInfo
{


	private String title ;
	private int id;
	private int start ;
	private List<YjjPicInfo> data;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public List<YjjPicInfo> getData()
	{
		return data;
	}

	public void setData(List<YjjPicInfo> data)
	{
		this.data = data;
	}
}
