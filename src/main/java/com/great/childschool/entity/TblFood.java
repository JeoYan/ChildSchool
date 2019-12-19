package com.great.childschool.entity;


public class TblFood {

  private long fid;
  private String fdate;
  private String fitem;
  private String fname;
  private int page;

  public int getPage()
  {
    return page;
  }

  public void setPage(int page)
  {
    this.page = page;
  }

  public long getFid() {
    return fid;
  }

  public void setFid(long fid) {
    this.fid = fid;
  }


  public String getFdate() {
    return fdate;
  }

  public void setFdate(String fdate) {
    this.fdate = fdate;
  }


  public String getFitem() {
    return fitem;
  }

  public void setFitem(String fitem) {
    this.fitem = fitem;
  }


  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

}
