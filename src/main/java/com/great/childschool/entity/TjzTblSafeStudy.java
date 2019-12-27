package com.great.childschool.entity;

/**
 * 安全教育实体类
 * by 汤建志
 */

public class TjzTblSafeStudy
{
	private int safeId;
	private String safeName;
	private String safeDate;
	private String vUrl;
	private String testUrl;
	private String startDate;
	private String endDate;
	private int wid;

	public TjzTblSafeStudy()
	{
	}

	public int getSafeId()
	{
		return safeId;
	}

	public void setSafeId(int safeId)
	{
		this.safeId = safeId;
	}

	public String getSafeName()
	{
		return safeName;
	}

	public void setSafeName(String safeName)
	{
		this.safeName = safeName;
	}

	public String getSafeDate()
	{
		return safeDate;
	}

	public void setSafeDate(String safeDate)
	{
		this.safeDate = safeDate;
	}

	public String getvUrl()
	{
		return vUrl;
	}

	public void setvUrl(String vUrl)
	{
		this.vUrl = vUrl;
	}

	public String getTestUrl()
	{
		return testUrl;
	}

	public void setTestUrl(String testUrl)
	{
		this.testUrl = testUrl;
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

	public int getWid()
	{
		return wid;
	}

	public void setWid(int wid)
	{
		this.wid = wid;
	}
}
