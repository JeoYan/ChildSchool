package com.great.childschool.entity;

/**
 * 角色实体类
 * by 陈超
 */
public class TblRole {

  private int rid;
  private String rName;

  public TblRole()
  {
  }

  public TblRole(int rid, String rName)
  {
    this.rid = rid;
    this.rName = rName;
  }

  public int getRid() {
    return rid;
  }

  public void setRid(int rid) {
    this.rid = rid;
  }


  public String getrName()
  {
    return rName;
  }

  public void setrName(String rName)
  {
    this.rName = rName;
  }
}
