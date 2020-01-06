package com.great.childschool.entity;


public class CcTblBaby
{

  private int bid;
  private String bname;
  private String bbirth;
  private String bsex;
  private String bdate;
  private int cid;
  private String cname;
  private String baddress;
  private byte[] bface;
  private int wid;
  private String wname;

  public byte[] getBface()
  {
    return bface;
  }

  public void setBface(byte[] bface)
  {
    this.bface = bface;
  }

  public String getBname() {
    return bname;
  }

  public void setBname(String bname) {
    this.bname = bname;
  }


  public String getBbirth() {
    return bbirth;
  }

  public void setBbirth(String bbirth) {
    this.bbirth = bbirth;
  }


  public String getBsex() {
    return bsex;
  }

  public void setBsex(String bsex) {
    this.bsex = bsex;
  }


  public String getBdate() {
    return bdate;
  }

  public void setBdate(String bdate) {
    this.bdate = bdate;
  }


  public int getBid()
  {
    return bid;
  }

  public void setBid(int bid)
  {
    this.bid = bid;
  }

  public int getCid()
  {
    return cid;
  }

  public void setCid(int cid)
  {
    this.cid = cid;
  }

  public String getBaddress() {
    return baddress;
  }

  public void setBaddress(String baddress) {
    this.baddress = baddress;
  }

  public String getCname()
  {
    return cname;
  }

  public void setCname(String cname)
  {
    this.cname = cname;
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
}
