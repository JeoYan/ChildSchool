package com.great.childschool.entity;


public class YjjTblReadbook
{

  private int bookid;
  private String bookName;
  private String uploadDate;
  private String url;
  private int wid;
  private int delWid;
  private String wName;

  public int getBookid()
  {
    return bookid;
  }

  public String getwName()
  {
    return wName;
  }

  public void setwName(String wName)
  {
    this.wName = wName;
  }

  public void setBookid(int bookid)
  {
    this.bookid = bookid;
  }

  public String getBookName()
  {
    return bookName;
  }

  public void setBookName(String bookName)
  {
    this.bookName = bookName;
  }

  public String getUploadDate()
  {
    return uploadDate;
  }

  public void setUploadDate(String uploadDate)
  {
    this.uploadDate = uploadDate;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public int getWid()
  {
    return wid;
  }

  public void setWid(int wid)
  {
    this.wid = wid;
  }

  public int getDelWid()
  {
    return delWid;
  }

  public void setDelWid(int delWid)
  {
    this.delWid = delWid;
  }
}
