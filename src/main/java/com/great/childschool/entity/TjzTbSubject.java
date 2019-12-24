package com.great.childschool.entity;


/**
 * 科目实体类
 * by 汤建志
 */
public class TjzTbSubject
{
	private int subId;
	private String subName;

	public TjzTbSubject()
	{
	}

	public int getSubId()
	{
		return subId;
	}

	public void setSubId(int subId)
	{
		this.subId = subId;
	}

	public String getSubName()
	{
		return subName;
	}

	public void setSubName(String subName)
	{
		this.subName = subName;
	}

	@Override
	public String toString()
	{
		return "TjzTbSubject{" + "subId=" + subId + ", subName='" + subName + '\'' + '}';
	}
}
