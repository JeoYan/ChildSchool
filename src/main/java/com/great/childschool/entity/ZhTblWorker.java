package com.great.childschool.entity;

public class ZhTblWorker
{
	private int wid;
	private String wname;
	private String wpsw;
	private String wbrith;
	private String wsex;
	private String wdate;
	private int sid;
	private String rname;
	private byte[] wface;


	public byte[] getWface()
	{
		return wface;
	}

	public void setWface(byte[] wface)
	{
		this.wface = wface;
	}

	public int getWid()
	{
		return wid;
	}

	public void setWid(int wid)
	{
		this.wid = wid;
	}

	public String getWname()
	{
		return wname;
	}

	public void setWname(String wname)
	{
		this.wname = wname;
	}

	public String getWpsw()
	{
		return wpsw;
	}

	public void setWpsw(String wpsw)
	{
		this.wpsw = wpsw;
	}

	public String getWbrith()
	{
		return wbrith;
	}

	public void setWbrith(String wbrith)
	{
		this.wbrith = wbrith;
	}

	public String getWsex()
	{
		return wsex;
	}

	public void setWsex(String wsex)
	{
		this.wsex = wsex;
	}

	public String getWdate()
	{
		return wdate;
	}

	public void setWdate(String wdate)
	{
		this.wdate = wdate;
	}

	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public String getRname()
	{
		return rname;
	}

	public void setRname(String rname)
	{
		this.rname = rname;
	}
}
