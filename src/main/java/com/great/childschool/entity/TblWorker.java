package com.great.childschool.entity;


public class TblWorker {

  private int wid;
  private String wname;
  private String wpsw;
  private String wbrith;
  private String wsex;
  private String wdate;
  private int sid;
  private String rname;
  private int rid;
  private byte[] wface;
  private String wphone;



  public String getWphone()
  {
    return wphone;
  }

  public void setWphone(String wphone)
  {
    this.wphone = wphone;
  }

  public byte[] getWface()
  {
    return wface;
  }

  public void setWface(byte[] wface)
  {
    this.wface = wface;
  }

  public String getRname()
  {
    return rname;
  }

  public void setRname(String rname)
  {
    this.rname = rname;
  }

  public int getWid()
  {
    return wid;
  }

  public void setWid(int wid)
  {
    this.wid = wid;
  }

  public String getWname()
  {
    return wname;
  }

  public void setWname(String wname)
  {
    this.wname = wname;
  }

  public String getWpsw()
  {
    return wpsw;
  }

  public void setWpsw(String wpsw)
  {
    this.wpsw = wpsw;
  }

  public String getWbrith()
  {
    return wbrith;
  }

  public void setWbrith(String wbrith)
  {
    this.wbrith = wbrith;
  }

  public String getWsex()
  {
    return wsex;
  }

  public void setWsex(String wsex)
  {
    this.wsex = wsex;
  }

  public String getWdate()
  {
    return wdate;
  }

  public void setWdate(String wdate)
  {
    this.wdate = wdate;
  }

  public int getSid()
  {
    return sid;
  }

  public void setSid(int sid)
  {
    this.sid = sid;
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
