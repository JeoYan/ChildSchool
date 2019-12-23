package com.great.childschool.entity;

import java.util.List;

public class YjjTreeData
{
	private String title;

	private int id;

	private boolean checked;

	private String href;

	private List<YjjTreeData> children;

	public YjjTreeData()
	{
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public List<YjjTreeData> getChildren()
	{
		return children;
	}

	public void setChildren(List<YjjTreeData> children)
	{
		this.children = children;
	}
}
