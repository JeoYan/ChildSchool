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
	private String bName;
	private String startDate;
	private String endDate;
	private String ntitle;

	public String getNtitle()
	{
		return ntitle;
	}

	public void setNtitle(String ntitle)
	{
		this.ntitle = ntitle;
	}

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

	public String getbName()
	{
		return bName;
	}

	public void setbName(String bName)
	{
		this.bName = bName;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
}
