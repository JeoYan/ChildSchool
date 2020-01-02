package com.great.childschool.entity;
/**
 *@className CmjBabySign
 *@description 宝宝考勤实体类
 *@author MJChen
 *@date 2019/12/24 13:48
 *@version 1.0
 **/
public class CmjBabySign
{
	private String bsTime;
	private String bsPeriod;
	private String bsDate;
	private String sName;
	public String getBsTime()
	{
		return bsTime;
	}

	public void setBsTime(String bsTime)
	{
		this.bsTime = bsTime;
	}

	public String getBsPeriod()
	{
		return bsPeriod;
	}

	public void setBsPeriod(String bsPeriod)
	{
		this.bsPeriod = bsPeriod;
	}

	public String getBsDate()
	{
		return bsDate;
	}

	public void setBsDate(String bsDate)
	{
		this.bsDate = bsDate;
	}

	public String getsName()
	{
		return sName;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	@Override
	public String toString()
	{
		return "CmjBabySign{" + "bsTime='" + bsTime + '\'' + ", bsPeriod='" + bsPeriod + '\'' + ", bsDate='" + bsDate + '\'' + ", sName='" + sName + '\'' + '}';
	}
}
