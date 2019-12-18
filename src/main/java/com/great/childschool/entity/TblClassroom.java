package com.great.childschool.entity;


public class TblClassroom {

  private long cId;
  private String cName;
  private String classRoom;
  private long wId;



  public long getcId()
  {
    return cId;
  }

  public void setcId(long cId)
  {
    this.cId = cId;
  }

  public String getcName()
  {
    return cName;
  }

  public void setcName(String cName)
  {
    this.cName = cName;
  }

  public String getClassRoom()
  {
    return classRoom;
  }

  public void setClassRoom(String classRoom)
  {
    this.classRoom = classRoom;
  }

  public long getwId()
  {
    return wId;
  }

  public void setwId(long wId)
  {
    this.wId = wId;
  }
}
