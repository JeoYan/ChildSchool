/**
 * 页数实体类
 * by 陈超
 */
package com.great.childschool.entity;

public class CcTableInf
{
	private String starttime;
	private String endtime;
	private String wname;
	private String bname;
	private String rname;
	private String pname;
	private int page;

	public CcTableInf()
	{
	}

	public String getStarttime()
	{
		return starttime;
	}

	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	public String getEndtime()
	{
		return endtime;
	}

	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

	public String getWname()
	{
		return wname;
	}

	public void setWname(String wname)
	{
		this.wname = wname;
	}

	public String getBname()
	{
		return bname;
	}

	public void setBname(String bname)
	{
		this.bname = bname;
	}

	public String getRname()
	{
		return rname;
	}

	public void setRname(String rname)
	{
		this.rname = rname;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}
}

