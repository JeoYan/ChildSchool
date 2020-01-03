package com.great.childschool.entity;


public class ZhTblNotice
{

  private long noid;
  private String notitle;
  private String nconntext;
  private String ndate;
  private long wid;
  private String startDate;
  private String endDate;
  private String wname;
  private int page;

  public String getWname()
  {
    return wname;
  }

  public void setWname(String wname)
  {
    this.wname = wname;
  }

  public String getStartDate()
  {
    return startDate;
  }

  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }

  public String getEndDate()
  {
    return endDate;
  }

  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }

  public int getPage()
  {
    return page;
  }

  public void setPage(int page)
  {
    this.page = page;
  }

  public long getNoid() {
    return noid;
  }

  public void setNoid(long noid) {
    this.noid = noid;
  }


  public String getNotitle() {
    return notitle;
  }

  public void setNotitle(String notitle) {
    this.notitle = notitle;
  }


  public String getNconntext() {
    return nconntext;
  }

  public void setNconntext(String nconntext) {
    this.nconntext = nconntext;
  }


  public String getNdate() {
    return ndate;
  }

  public void setNdate(String ndate) {
    this.ndate = ndate;
  }


  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

}
