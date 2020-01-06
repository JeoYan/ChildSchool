package com.great.childschool.entity;


/**
 * 报警实体类
 * by 汤建志
 */

public class TjzTblWarning
{

	private int bid;
	private int warnId;
	private String area;
	private String warnTime;
	private String warnName;
	private String bName;

	public TjzTblWarning()
	{
	}

	public String getbName()
	{
		return bName;
	}

	public void setbName(String bName)
	{
		this.bName = bName;
	}

	public int getWarnId()
	{
		return warnId;
	}

	public void setWarnId(int warnId)
	{
		this.warnId = warnId;
	}

	public String getWarnName()
	{
		return warnName;
	}

	public void setWarnName(String warnName)
	{
		this.warnName = warnName;
	}

	public int getBid()
	{
		return bid;
	}

	public void setBid(int bid)
	{
		this.bid = bid;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getWarnTime()
	{
		return warnTime;
	}

	public void setWarnTime(String warnTime)
	{
		this.warnTime = warnTime;
	}
}
