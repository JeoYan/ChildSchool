package com.great.childschool.entity;


public class ZhTblHomework
{

  private long hid;
  private long subid;
  private String hname;
  private String hdate;
  private String url;
  private long wid;
  private String score;
  private long pid;


  public long getPid()
  {
    return pid;
  }

  public void setPid(long pid)
  {
    this.pid = pid;
  }

  public String getScore()
  {
    return score;
  }

  public void setScore(String score)
  {
    this.score = score;
  }

  public long getHid() {
    return hid;
  }

  public void setHid(long hid) {
    this.hid = hid;
  }


  public long getSubid() {
    return subid;
  }

  public void setSubid(long subid) {
    this.subid = subid;
  }


  public String getHname() {
    return hname;
  }

  public void setHname(String hname) {
    this.hname = hname;
  }


  public String getHdate() {
    return hdate;
  }

  public void setHdate(String hdate) {
    this.hdate = hdate;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

}
