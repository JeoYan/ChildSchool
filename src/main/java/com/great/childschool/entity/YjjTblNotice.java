package com.great.childschool.entity;


public class YjjTblNotice
{

  private int noid;
  private String notitle;
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

  public int getNoid()
  {
    return noid;
  }

  public void setNoid(int noid)
  {
    this.noid = noid;
  }

  public String getNotitle()
  {
    return notitle;
  }

  public void setNotitle(String notitle)
  {
    this.notitle = notitle;
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
