package com.great.childschool.entity;

public class TbLog
{
	private int logId;;
	private	String operator;
	private String operationTime;
	private	String	operationalMatters;

	public TbLog()
	{
	}

	public int getLogId()
	{
		return logId;
	}

	public void setLogId(int logId)
	{
		this.logId = logId;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getOperationTime()
	{
		return operationTime;
	}

	public void setOperationTime(String operationTime)
	{
		this.operationTime = operationTime;
	}

	public String getOperationalMatters()
	{
		return operationalMatters;
	}

	public void setOperationalMatters(String operationalMatters)
	{
		this.operationalMatters = operationalMatters;
	}

	@Override
	public String toString()
	{
		return "TbLog{" + "logId=" + logId + ", operator='" + operator + '\'' + ", operationTime='" + operationTime + '\'' + ", operationalMatters='" + operationalMatters + '\'' + '}';
	}
}
