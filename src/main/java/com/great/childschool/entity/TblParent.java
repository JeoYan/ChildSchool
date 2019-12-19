package com.great.childschool.entity;



public class TblParent {

  private int pid;
  private String pname;
  private String ppsw;
  private String pphone;
  private String pjob;
  private int sid;
  private String prelation;

  public String getPrelation()
  {
    return prelation;
  }

  public void setPrelation(String prelation)
  {
    this.prelation = prelation;
  }

  public int getPid()
  {
    return pid;
  }

  public void setPid(int pid)
  {
    this.pid = pid;
  }

  public String getPname()
  {
    return pname;
  }

  public void setPname(String pname)
  {
    this.pname = pname;
  }

  public String getPpsw()
  {
    return ppsw;
  }

  public void setPpsw(String ppsw)
  {
    this.ppsw = ppsw;
  }

  public String getPphone()
  {
    return pphone;
  }

  public void setPphone(String pphone)
  {
    this.pphone = pphone;
  }

  public String getPjob()
  {
    return pjob;
  }

  public void setPjob(String pjob)
  {
    this.pjob = pjob;
  }

  public int getSid()
  {
    return sid;
  }

  public void setSid(int sid)
  {
    this.sid = sid;
  }
}
