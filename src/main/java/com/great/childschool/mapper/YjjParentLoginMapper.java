package com.great.childschool.mapper;

import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.YjjTblBookPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 家长登入访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjParentLoginMapper
{

	/**
	 * 家长登入，查询账号和密码是否存在
	 *     by 严俊杰
	 */
	@Select("Select * from tbl_parent where pphone=#{uPhone} and ppsw=#{passWord}")
	TblParent parentLoginCheck(@Param("uPhone")String uPhone,@Param("passWord")String passWord);

	/**
	 * 获得密码
	 */
	@Select("Select * from tbl_parent where pphone=#{pphone}")
	 TblParent getPsw(@Param("pphone")String pphone);




	/**
	 * --------------------亲子阅读---------------------------
	 */

	/**
	 *
	 * 获得书籍id
	 */
	@Select("select DISTINCT(bookid) from tbl_book_page")
	List<YjjTblBookPage> findBookid();
	/**
	 * 获得书籍对应的page
	 *
	 */
	@Select("select tbl_book_page.*,tbl_readbook.bookname from tbl_book_page,tbl_readbook where tbl_book_page.bookid=#{bookid} and tbl_book_page.bookid=tbl_readbook.bookid ORDER BY pagenum ASC")
	List<YjjTblBookPage> findBookPage(@Param("bookid")int bookid);



}
