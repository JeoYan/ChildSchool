package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


}
