package com.great.childschool.entity;


public class TblMenu {

  private int mid;
  private String mname;
  private int mpid;
  private String url;
  private int rid;
  private String childName;


  public String getChildName()
  {
    return childName;
  }

  public void setChildName(String childName)
  {
    this.childName = childName;
  }

  public int getMid()
  {
    return mid;
  }

  public void setMid(int mid)
  {
    this.mid = mid;
  }

  public String getMname()
  {
    return mname;
  }

  public void setMname(String mname)
  {
    this.mname = mname;
  }

  public int getMpid()
  {
    return mpid;
  }

  public void setMpid(int mpid)
  {
    this.mpid = mpid;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public int getRid()
  {
    return rid;
  }

  public void setRid(int rid)
  {
    this.rid = rid;
  }
}
