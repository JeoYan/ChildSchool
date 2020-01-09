package com.great.childschool.entity;

public class ZhTblCourse
{
	private String  cdate;
	private String  corder;
	private String  cname;
	private String  classroom;
	private int page;

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCdate()
	{
		return cdate;
	}

	public void setCdate(String cdate)
	{
		this.cdate = cdate;
	}

	public String getCorder()
	{
		return corder;
	}

	public void setCorder(String corder)
	{
		this.corder = corder;
	}


	public String getClassroom()
	{
		return classroom;
	}

	public void setClassroom(String classroom)
	{
		this.classroom = classroom;
	}
}
