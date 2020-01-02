package com.great.childschool.entity;

/**
 * 作业实体类
 *@author MJChen
 *@date 2019/12/24 13:48
 *@version 1.0
 **/
public class CmjHomework
{

  private long hid;
  private long suBid;
  private String hName;
  private String hDate;
  private String score;
  private String url;
  private long wid;

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	public long getHid()
	{
		return hid;
	}

	public void setHid(long hid)
	{
		this.hid = hid;
	}

	public long getSuBid()
	{
		return suBid;
	}

	public void setSuBid(long suBid)
	{
		this.suBid = suBid;
	}

	public String gethName()
	{
		return hName;
	}

	public void sethName(String hName)
	{
		this.hName = hName;
	}

	public String gethDate()
	{
		return hDate;
	}

	public void sethDate(String hDate)
	{
		this.hDate = hDate;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public long getWid()
	{
		return wid;
	}

	public void setWid(long wid)
	{
		this.wid = wid;
	}

	@Override
	public String toString()
	{
		return "CmjHomework{" + "hid=" + hid + ", suBid=" + suBid + ", hName='" + hName + '\'' + ", hDate='" + hDate + '\'' + ", url='" + url + '\'' + ", wid=" + wid + '}';
	}
}
