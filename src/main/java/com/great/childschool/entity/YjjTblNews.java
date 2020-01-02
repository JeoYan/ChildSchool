package com.great.childschool.entity;


public class YjjTblNews
{

  private int nid;
  private String ntitle;
  private String nconntext;
  private String ndate;
  private int wid;
  private String wName;

  public String getwName()
  {
    return wName;
  }

  public void setwName(String wName)
  {
    this.wName = wName;
  }


  public int getNid()
  {
    return nid;
  }

  public void setNid(int nid)
  {
    this.nid = nid;
  }

  public String getNtitle()
  {
    return ntitle;
  }

  public void setNtitle(String ntitle)
  {
    this.ntitle = ntitle;
  }

  public String getNconntext()
  {
    return nconntext;
  }

  public void setNconntext(String nconntext)
  {
    this.nconntext = nconntext;
  }

  public String getNdate()
  {
    return ndate;
  }

  public void setNdate(String ndate)
  {
    this.ndate = ndate;
  }

  public int getWid()
  {
    return wid;
  }

  public void setWid(int wid)
  {
    this.wid = wid;
  }
}
