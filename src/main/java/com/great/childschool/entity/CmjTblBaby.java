package com.great.childschool.entity;
/**
 *@className CmjTblBaby
 *@description TODO
 *@author MJChen
 *@date 2019/12/18 14:05
 *@version 1.0
 **/
public class CmjTblBaby
{
	private long bid;
	private String bName;
	private String bBirth;
	private String bSex;
	private String bDate;
	private long cid;
	private String bAddress;
	private String pName;
	
	public long getBid()
	{
		return bid;
	}
	public void setBid(long bid)
	{
		this.bid = bid;
	}
	public String getbName()
	{
		return bName;
	}

	public void setbName(String bName)
	{
		this.bName = bName;
	}

	public String getbBirth()
	{
		return bBirth;
	}

	public void setbBirth(String bBirth)
	{
		this.bBirth = bBirth;
	}

	public String getbSex()
	{
		return bSex;
	}

	public void setbSex(String bSex)
	{
		this.bSex = bSex;
	}

	public String getbDate()
	{
		return bDate;
	}

	public void setbDate(String bDate)
	{
		this.bDate = bDate;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public String getbAddress()
	{
		return bAddress;
	}

	public void setbAddress(String bAddress)
	{
		this.bAddress = bAddress;
	}

	public String getpName()
	{
		return pName;
	}

	public void setpName(String pName)
	{
		this.pName = pName;
	}

	@Override
	public String toString()
	{
		return "TblBaby{" + "bid=" + bid + ", bName='" + bName + '\'' + ", bBirth='" + bBirth + '\'' + ", bSex='" + bSex + '\'' + ", bDate='" + bDate + '\'' + ", cid=" + cid + ", bAddress='" + bAddress + '\'' + ", pName='" + pName + '\'' + '}';
	}
}
