package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 亲子阅读访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjBookReadMapper
{
	//获得表格数据 by严俊杰
	List<YjjTblReadbook> findBook(YjjSearchInfo searchInfo);

	//总条数 by严俊杰
	List<YjjTblReadbook> totalPage(YjjSearchInfo searchInfo);

	//根据书名查询对应的书籍 by 严俊杰
	@Select("select * from tbl_readbook where bookname=#{bookName}")
	YjjTblReadbook checkBook(@Param("bookName")String bookName);

	//根据书名查询对应的tbl_readbook by 严俊杰
	@Insert("insert into tbl_readbook (bookname,uploaddate,wid) values(#{bookName},#{uploadDate},#{wid})")
	@Options(useGeneratedKeys=true,keyProperty="bookid")
	int addReadBook(YjjTblReadbook readbook);

	//根据书名查询对应的书籍 by 严俊杰
	@Insert("insert into tbl_book_page (bookid,pagenum,bpcontent,wid,bdate,url) values (#{bookid},#{pageNum},#{bpContent},#{wid},#{bDate},#{url})")
	int addBookPage(YjjTblBookPage bookPage);



	//获得表格数据 by严俊杰
	List<YjjTblBookPage> findBookPage(YjjSearchInfo searchInfo);

	//总条数 by严俊杰
	List<YjjTblBookPage> totalBookPage(YjjSearchInfo searchInfo);

	//后的最后一页的数据
	@Select("select max(pagenum) as pagenum from tbl_book_page,tbl_readbook where  tbl_book_page.bookid=tbl_readbook.bookid and tbl_readbook.bookname=#{bName}")
	YjjTblBookPage findLastPage(@Param("bName") String bName);

	//获删除tbl_book_read数据 by 严俊杰
	@Delete("delete  from tbl_book_page where bpid=#{bpid}")
	int delPage(@Param("bpid")String bpid);

	//判断是否最后一页了
	@Select("select * from tbl_book_page where bookid=#{bookid}")
	List<YjjTblBookPage> findPage(@Param("bookid") String bookid);

	//获删除tbl_readbook数据 by 严俊杰
	@Delete("delete  from tbl_readbook where bookid=#{bookid}")
	 int delBook(@Param("bookid") String  bookid);

	//更新tbl_readbook数据 by 严俊杰
	@Update("update tbl_book_page set bpcontent=#{bpContent},url=#{url} where bpid=#{bpid}")
	int updateBookPage(YjjTblBookPage bookPage);

	//删除tbl_book_read数据 by 严俊杰
	@Delete("delete  from tbl_book_page where bookid=#{bookid}")
	int delBookPage(@Param("bookid")String bookid);


	//更新书名
	@Select("select * from tbl_readbook where bookid=#{bookid}")
	YjjTblReadbook findBookName(@Param("bookid")String bookid);

	//更新书名
	@Update("update tbl_readbook set bookname=#{bName} where bookid=#{bookid}")
	int updateBookName(@Param("bookid")String bookid,@Param("bName")String bName);





}
