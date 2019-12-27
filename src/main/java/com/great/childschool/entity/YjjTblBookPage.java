package com.great.childschool.entity;


public class YjjTblBookPage
{

  private int bpid;
  private int bookid;
  private int pageNum;
  private String bpContent;
  private int wid;
  private String bDate;
  private String url;
  private String wName;
  private String bookName;


  public String getBookName()
  {
    return bookName;
  }

  public void setBookName(String bookName)
  {
    this.bookName = bookName;
  }

  public String getwName()
  {
    return wName;
  }

  public void setwName(String wName)
  {
    this.wName = wName;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public int getBpid()
  {
    return bpid;
  }

  public void setBpid(int bpid)
  {
    this.bpid = bpid;
  }

  public int getBookid()
  {
    return bookid;
  }

  public void setBookid(int bookid)
  {
    this.bookid = bookid;
  }

  public int getPageNum()
  {
    return pageNum;
  }

  public void setPageNum(int pageNum)
  {
    this.pageNum = pageNum;
  }

  public String getBpContent()
  {
    return bpContent;
  }

  public void setBpContent(String bpContent)
  {
    this.bpContent = bpContent;
  }

  public int getWid()
  {
    return wid;
  }

  public void setWid(int wid)
  {
    this.wid = wid;
  }

  public String getbDate()
  {
    return bDate;
  }

  public void setbDate(String bDate)
  {
    this.bDate = bDate;
  }


  @Override
  public String toString()
  {
    return "YjjTblBookPage{" + "bpid=" + bpid + ", bookid=" + bookid + ", pageNum=" + pageNum + ", bpContent='" + bpContent + '\'' + ", wid=" + wid + ", bDate='" + bDate + '\'' + ", url='" + url + '\'' + ", wName='" + wName + '\'' + ", bookName='" + bookName + '\'' + '}';
  }
}
