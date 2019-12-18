package com.great.childschool.entity;


/**
 * 汤建志
 * 班级实体类
 */
public class TjzTbClassRoom
{
	private int cid;
	private String cName;
	private String classroom;
	private int wid;
	private String courseAddDate;
	private String wName;

	public TjzTbClassRoom()
	{
	}

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public String getcName()
	{
		return cName;
	}

	public void setcName(String cName)
	{
		this.cName = cName;
	}

	public String getClassroom()
	{
		return classroom;
	}

	public void setClassroom(String classroom)
	{
		this.classroom = classroom;
	}

	public int getWid()
	{
		return wid;
	}

	public void setWid(int wid)
	{
		this.wid = wid;
	}

	public String getCourseAddDate()
	{
		return courseAddDate;
	}

	public void setCourseAddDate(String courseAddDate)
	{
		this.courseAddDate = courseAddDate;
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
		return "TjzTbClassRoom{" + "cid=" + cid + ", cName='" + cName + '\'' + ", classroom='" + classroom + '\'' + ", wid=" + wid + ", courseAddDate='" + courseAddDate + '\'' + ", wName='" + wName + '\'' + '}';
	}
}
