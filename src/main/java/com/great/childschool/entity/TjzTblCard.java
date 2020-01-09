package com.great.childschool.entity;

public class TjzTblCard
{
	private int cardId;
	private String cardNum;
	private String cardTime;
	private int bid;
	private String cardState;
	private String bName;
	public TjzTblCard()
	{
	}

	public int getBid()
	{
		return bid;
	}

	public void setBid(int bid)
	{
		this.bid = bid;
	}

	public String getCardState()
	{
		return cardState;
	}

	public void setCardState(String cardState)
	{
		this.cardState = cardState;
	}

	public String getbName()
	{
		return bName;
	}

	public void setbName(String bName)
	{
		this.bName = bName;
	}

	public int getCardId()
	{
		return cardId;
	}

	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}

	public String getCardNum()
	{
		return cardNum;
	}

	public void setCardNum(String cardNum)
	{
		this.cardNum = cardNum;
	}

	public String getCardTime()
	{
		return cardTime;
	}

	public void setCardTime(String cardTime)
	{
		this.cardTime = cardTime;
	}
}
