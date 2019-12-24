package com.great.childschool.entity;


/**
 * 家长实体类
 * by 汤建志
 */
public class TjzTblParent
{

  private int pid;
  private String ppsw;

  public TjzTblParent()
  {
  }

  public int getPid()
  {
    return pid;
  }

  public void setPid(int pid)
  {
    this.pid = pid;
  }

  public String getPpsw()
  {
    return ppsw;
  }

  public void setPpsw(String ppsw)
  {
    this.ppsw = ppsw;
  }

  @Override
  public String toString()
  {
    return "TjzTblParent{" + "pid=" + pid + ", ppsw='" + ppsw + '\'' + '}';
  }
}
