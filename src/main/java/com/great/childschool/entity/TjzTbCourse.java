package com.great.childschool.entity;

/**
 * 课程实体类
 * by 汤建志
 */
public class TjzTbCourse
{
	private int couId;
	private String cDate;
	private int wid;
	private int cid;
	private int tid;
	private int subId;
	private String subName;
	private String cOrder;


	public TjzTbCourse()
	{
	}

	public String getcOrder()
	{
		return cOrder;
	}

	public void setcOrder(String cOrder)
	{
		this.cOrder = cOrder;
	}

	public int getCouId()
	{
		return couId;
	}

	public void setCouId(int couId)
	{
		this.couId = couId;
	}

	public String getcDate()
	{
		return cDate;
	}

	public void setcDate(String cDate)
	{
		this.cDate = cDate;
	}

	public int getWid()
	{
		return wid;
	}

	public void setWid(int wid)
	{
		this.wid = wid;
	}

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public int getTid()
	{
		return tid;
	}

	public void setTid(int tid)
	{
		this.tid = tid;
	}

	public int getSubId()
	{
		return subId;
	}

	public void setSubId(int subId)
	{
		this.subId = subId;
	}

	public String getSubName()
	{
		return subName;
	}

	public void setSubName(String subName)
	{
		this.subName = subName;
	}

	@Override
	public String toString()
	{
		return "TjzTbCourse{" + "couId=" + couId + ", cDate='" + cDate + '\'' + ", wid=" + wid + ", cid=" + cid + ", tid=" + tid + ", subId=" + subId + ", subName='" + subName + '\'' + ", cOrder='" + cOrder + '\'' + '}';
	}
}
