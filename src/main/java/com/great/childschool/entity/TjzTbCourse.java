package com.great.childschool.entity;

/**
 * 汤建志
 * 课程实体类
 */
public class TjzTbCourse
{
	private int couId;
	private String cDate;
	private int wid;
	private int cid;
	private int tid;
	private int subId;

	public TjzTbCourse()
	{
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

	@Override
	public String toString()
	{
		return "TjzTbCourse{" + "couId=" + couId + ", cDate='" + cDate + '\'' + ", wid=" + wid + ", cid=" + cid + ", tid=" + tid + ", subId=" + subId + '}';
	}
}
