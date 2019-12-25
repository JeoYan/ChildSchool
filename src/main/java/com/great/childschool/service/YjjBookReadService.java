package com.great.childschool.service;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.*;
import com.great.childschool.mapper.YjjBookReadMapper;
import com.great.childschool.mapper.YjjWorkerPowerControlMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 亲子阅读业务层
 * by 严俊杰 12.20
 */

@Service
public class YjjBookReadService
{

	@Resource
	private YjjBookReadMapper bookReadMapper;


	//获得表格数据 by 严俊杰
	@Transactional
	public List<YjjTblReadbook> findBook(YjjSearchInfo searchInfo)
	{
		return bookReadMapper.findBook(searchInfo);
	}

	//总条数 by 严俊杰
	@Transactional
	public List<YjjTblReadbook> totalPage(YjjSearchInfo searchInfo)
	{
		return bookReadMapper.totalPage(searchInfo);
	}

	//根据书名查询对应的书籍 by 严俊杰
	@Transactional
	public YjjTblReadbook checkBook(String bookName)
	{
		return bookReadMapper.checkBook(bookName);
	}

	//新增书籍 by 严俊杰
	@Transactional
	public int addBook(Boolean status, YjjTblReadbook readbook, YjjTblBookPage bookPage)
	{
		int bookid = 0;
		if (status == true)
		{
			bookReadMapper.addReadBook(readbook);
			bookid = readbook.getBookid();
		} else
		{
			bookid = readbook.getBookid();
		}
		bookPage.setBookid(bookid);
		int i = bookReadMapper.addBookPage(bookPage);

		return i;
	}





	//获得表格数据 by 严俊杰
	@Transactional
	public List<YjjTblBookPage> findBookPage(YjjSearchInfo searchInfo)
	{
		return bookReadMapper.findBookPage(searchInfo);
	}

	//总条数 by 严俊杰
	@Transactional
	public List<YjjTblBookPage> totalBookPage(YjjSearchInfo searchInfo)
	{
		return bookReadMapper.totalBookPage(searchInfo);
	}


	//获得最后一页信息 by 严俊杰
	@Transactional
	public YjjTblBookPage findLastPage(String bName)
	{
		return bookReadMapper.findLastPage(bName);
	}


	//获删除tbl_book_read数据 by 严俊杰
	@Transactional
	public int delPage(String bpid, String bookid)
	{
		//删除tbl_book_read数据
		int x = bookReadMapper.delPage(bpid);
		//判断是否最后一页了
		List<YjjTblBookPage> listPage = bookReadMapper.findPage(bookid);
		if (listPage.size() == 0)
		{
			//删除tbl_readbook
			x = bookReadMapper.delBook(bookid);

		}

		return x;
	}


	//修改bookpage的信息
	@Transactional
	public int updateBookPage(YjjTblBookPage bookPage)
	{

		return bookReadMapper.updateBookPage(bookPage);
	}


	//删除tbl_book_read和tbl_readbook数据 by 严俊杰
	@Transactional
	public int delBook(String bookid)
	{
		//删除tbl_book_read数据
		int x = bookReadMapper.delBookPage(bookid);
		if(x>0){
			//删除tbl_readbook
			x = bookReadMapper.delBook(bookid);
		}

		return x;
	}


	@Transactional
	public int updateBookName(String bookid,String bName){
		int x=0;
		if(bookReadMapper.findBookName(bookid)!=null){
			x=bookReadMapper.updateBookName(bookid, bName);
		}

		return x;
	}

public List<YjjTblBookPage> findPage(String bookid){

	 return bookReadMapper.findPage(bookid);
}

}