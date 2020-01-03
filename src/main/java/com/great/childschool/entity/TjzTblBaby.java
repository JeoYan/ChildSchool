package com.great.childschool.entity;

/**
 * 宝宝实体类
 * by 汤建志
 */
public class TjzTblBaby
{
	private int bid;
	private String cName;
	private String bName;
	private String pName;
	private int pid;

	private String sPoiLng;
	private String sPoiLat;
	private String ePoiLng;
	private String ePoiLat;
	private Boolean flag;
	private String pPhone;


	public TjzTblBaby()
	{
	}

	public String getpPhone()
	{
		return pPhone;
	}

	public void setpPhone(String pPhone)
	{
		this.pPhone = pPhone;
	}

	public Boolean getFlag()
	{
		return flag;
	}

	public void setFlag(Boolean flag)
	{
		this.flag = flag;
	}

	public String getsPoiLng()
	{
		return sPoiLng;
	}

	public void setsPoiLng(String sPoiLng)
	{
		this.sPoiLng = sPoiLng;
	}

	public String getsPoiLat()
	{
		return sPoiLat;
	}

	public void setsPoiLat(String sPoiLat)
	{
		this.sPoiLat = sPoiLat;
	}

	public String getePoiLng()
	{
		return ePoiLng;
	}

	public void setePoiLng(String ePoiLng)
	{
		this.ePoiLng = ePoiLng;
	}

	public String getePoiLat()
	{
		return ePoiLat;
	}

	public void setePoiLat(String ePoiLat)
	{
		this.ePoiLat = ePoiLat;
	}

	public int getBid()
	{
		return bid;
	}

	public void setBid(int bid)
	{
		this.bid = bid;
	}

	public String getcName()
	{
		return cName;
	}

	public void setcName(String cName)
	{
		this.cName = cName;
	}

	public String getbName()
	{
		return bName;
	}

	public void setbName(String bName)
	{
		this.bName = bName;
	}

	public String getpName()
	{
		return pName;
	}

	public void setpName(String pName)
	{
		this.pName = pName;
	}

	public int getPid()
	{
		return pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}
}
