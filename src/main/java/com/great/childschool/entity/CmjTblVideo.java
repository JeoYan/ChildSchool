package com.great.childschool.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *视频实体类
 *@author MJChen
 *@date 2019/12/30 10:14
 *@version 1.0
 **/
public class CmjTblVideo
{
	private Integer vid;
	private String vName;
	private String url;
	private CmjTblVideo parent;
	private List<CmjTblVideo> children = new ArrayList<>();

	public CmjTblVideo getParent()
	{
		return parent;
	}

	public void setParent(CmjTblVideo parent)
	{
		this.parent = parent;
	}
	public List<CmjTblVideo> getChildren()
	{
		return children;
	}

	public void setChildren(List<CmjTblVideo> children)
	{
		this.children = children;
	}

	public Integer getVid()
	{
		return vid;
	}

	public void setVid(Integer vid)
	{
		this.vid = vid;
	}

	public String getvName()
	{
		return vName;
	}

	public void setvName(String vName)
	{
		this.vName = vName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@Override
	public String toString()
	{
		return "CmjTblVideo{" + "vid=" + vid + ", vName='" + vName + '\'' + ", url='" + url + '\'' + '}';
	}
}
