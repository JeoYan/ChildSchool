/**
 * msg实体类
 * by 陈超
 */
package com.great.childschool.entity;

public class MSG
{
	private int code;
	private String msg;
	private int count;
	private Object data;

	public MSG()
	{
	}

	public MSG(int code, String msg, int count, Object data)
	{
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public Object getData()
	{
		return data;
	}

	public void setDate(Object data)
	{
		this.data = data;
	}
}
