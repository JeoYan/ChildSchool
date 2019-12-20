package com.great.childschool.controller;

import java.math.BigDecimal;

public class YjjTableData
{
	private BigDecimal code;

	private String msg;

	private BigDecimal count;

	private Object data;







	public YjjTableData()
	{
	}

	public YjjTableData(BigDecimal code, String msg, BigDecimal count, Object data)
	{
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}


	public BigDecimal getCode()
	{
		return code;
	}

	public void setCode(BigDecimal code)
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

	public BigDecimal getCount()
	{
		return count;
	}

	public void setCount(BigDecimal count)
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


}
