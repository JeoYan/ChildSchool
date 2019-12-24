package com.great.childschool.entity;

/**
 * 日志实体类
 * by 汤建志
 */
public class TjzTbLog
{
	private int lid;
	private int wid;
	private	String lTime;
	private	String wName;
	private String lEvent;


	public TjzTbLog()
	{
	}

	public int getLid()
	{
		return lid;
	}

	public void setLid(int lid)
	{
		this.lid = lid;
	}

	public int getWid()
	{
		return wid;
	}

	public void setWid(int wid)
	{
		this.wid = wid;
	}

	public String getlTime()
	{
		return lTime;
	}

	public void setlTime(String lTime)
	{
		this.lTime = lTime;
	}

	public String getlEvent()
	{
		return lEvent;
	}

	public void setlEvent(String lEvent)
	{
		this.lEvent = lEvent;
	}

	public String getwName()
	{
		return wName;
	}

	public void setwName(String wName)
	{
		this.wName = wName;
	}

	@Override
	public String toString()
	{
		return "TjzTbLog{" + "lid=" + lid + ", wid=" + wid + ", lTime='" + lTime + '\'' + ", wName='" + wName + '\'' + ", lEvent='" + lEvent + '\'' + '}';
	}
}
