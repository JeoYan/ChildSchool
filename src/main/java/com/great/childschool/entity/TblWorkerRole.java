/**
 * 工作人员角色关系  实体类
 * by 陈超
 */
package com.great.childschool.entity;


public class TblWorkerRole {

  private int wrid;
  private int  wid;
  private int rid;

  public TblWorkerRole()
  {
  }

  public TblWorkerRole(int wrid, int wid, int rid)
  {
    this.wrid = wrid;
    this.wid = wid;
    this.rid = rid;
  }

  public int getWrid()
  {
    return wrid;
  }

  public void setWrid(int wrid)
  {
    this.wrid = wrid;
  }

  public int getWid()
  {
    return wid;
  }

  public void setWid(int wid)
  {
    this.wid = wid;
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
