package com.great.childschool.entity;


/**
 * 数据表格实体类
 * by 汤建志
 */
public class TjzTbTable
{
	private String code;
	private String msg;
	private String count;
	private Object data;


	public TjzTbTable()
	{
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
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

	public String getCount()
	{
		return count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "TjzTbTable{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + ", count='" + count + '\'' + ", data=" + data + '}';
	}
}
