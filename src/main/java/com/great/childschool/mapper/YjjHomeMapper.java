package com.great.childschool.mapper;

import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.YjjTblBookPage;
import com.great.childschool.entity.YjjTblNews;
import com.great.childschool.entity.YjjTblNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 主页访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjHomeMapper
{

	/**
	 * 获得公告信息
	 *     by 严俊杰
	 */
	@Select("select tbl_notice.*,tbl_worker.wname FROM tbl_notice,tbl_worker where tbl_worker.wid=tbl_notice.wid")
	List<YjjTblNotice> findNotice();


	/**
	 * 获得新闻信息
	 *     by 严俊杰
	 */
	@Select("select tbl_news.*,tbl_worker.wname FROM tbl_news,tbl_worker where tbl_worker.wid=tbl_news.wid")
	List<YjjTblNews> findNews();


	/**
	 * 获得某一条公告信息
	 *     by 严俊杰
	 */
	@Select("select tbl_notice.*,tbl_worker.wname FROM tbl_notice,tbl_worker where tbl_worker.wid=tbl_notice.wid and noid=#{noid}")
	YjjTblNotice getNotice(@Param("noid")String noid);



	/**
	 * 获得某一条新闻信息
	 *     by 严俊杰
	 */
	@Select("select tbl_news.*,tbl_worker.wname FROM tbl_news,tbl_worker where tbl_worker.wid=tbl_news.wid and nid=#{nid}")
	YjjTblNews getNews(@Param("nid")String nid);


}
