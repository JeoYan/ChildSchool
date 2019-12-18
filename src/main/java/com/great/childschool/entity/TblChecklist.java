package com.great.childschool.entity;


public class TblChecklist {

  private long chId;
  private long bId;
  private String height;
  private String weight;
  private String vision;
  private String temperature;
  private String sId;
  private String checkDate;
  private int page;
  private String bname;
  private long cid;
  private String cname;

  public int getPage()
  {
    return page;
  }

  public void setPage(int page)
  {
    this.page = page;
  }

  public String getBname()
  {
    return bname;
  }

  public void setBname(String bname)
  {
    this.bname = bname;
  }

  public long getCid()
  {
    return cid;
  }

  public void setCid(long cid)
  {
    this.cid = cid;
  }

  public String getCname()
  {
    return cname;
  }

  public void setCname(String cname)
  {
    this.cname = cname;
  }

  public long getChId()
  {
    return chId;
  }

  public void setChId(long chId)
  {
    this.chId = chId;
  }

  public long getbId()
  {
    return bId;
  }

  public void setbId(long bId)
  {
    this.bId = bId;
  }

  public String getHeight()
  {
    return height;
  }

  public void setHeight(String height)
  {
    this.height = height;
  }

  public String getWeight()
  {
    return weight;
  }

  public void setWeight(String weight)
  {
    this.weight = weight;
  }

  public String getVision()
  {
    return vision;
  }

  public void setVision(String vision)
  {
    this.vision = vision;
  }

  public String getTemperature()
  {
    return temperature;
  }

  public void setTemperature(String temperature)
  {
    this.temperature = temperature;
  }

  public String getsId()
  {
    return sId;
  }

  public void setsId(String sId)
  {
    this.sId = sId;
  }

  public String getCheckDate()
  {
    return checkDate;
  }

  public void setCheckDate(String checkDate)
  {
    this.checkDate = checkDate;
  }
}
