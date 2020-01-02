package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 新闻中心访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjNewsMapper
{
	//获得表格数据 by严俊杰
	List<YjjTblNews> findNews(YjjSearchInfo searchInfo);

	//总条数 by严俊杰
	List<YjjTblNews> totalPage(YjjSearchInfo searchInfo);


	@Insert("insert into tbl_news (ntitle,nconntext,ndate,wid) values(#{ntitle},#{nconntext},#{ndate},#{wid})")
	int addNews(YjjTblNews news);

}
