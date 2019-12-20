/**
 * 页数实体类
 * by 陈超
 */
package com.great.childschool.entity;

/**
 * 表格查询条件实体
 * by 严俊杰
 *
 */


public class YjjSearchInfo
{

	private int limit ;
	private int page ;
	private String wName ;
	private String rid;



	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public String getwName()
	{
		return wName;
	}

	public void setwName(String wName)
	{
		this.wName = wName;
	}

	public String getRid()
	{
		return rid;
	}

	public void setRid(String rid)
	{
		this.rid = rid;
	}
}
