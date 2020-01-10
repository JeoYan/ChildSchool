package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 亲子阅读访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjMallMapper
{
	//获得表格数据 by严俊杰
	List<YjjTblCommodity> findComm(YjjSearchInfo searchInfo);

	//总条数 by严俊杰
	List<YjjTblCommodity> totalPage(YjjSearchInfo searchInfo);

	//删除TblCommodity表的数据
	@Delete("delete from tbl_commodity where comid=#{comid}")
	int delComm(@Param("comid")String comid);

	//删除tblcommodity_pic的数据
	@Delete("delete from tbl_commodity_pic where comid=#{comid}")
	int delCommPic(@Param("comid")String comid);

	//新增商品
	@Insert("insert into tbl_commodity (comtitle,comconntext,price,newprice,comnum,comdate,wid,type,statusid,home) values(#{comtitle},#{comconntext},#{price},#{newprice},#{comnum},#{comdate},#{wid},#{type},#{statusid},#{home})")
	@Options(useGeneratedKeys=true,keyProperty="comid")
	int addComm(YjjTblCommodity commodity);


	//新增商品对应的图片
	@Insert("insert into tbl_commodity_pic (comid,url) values (#{comid},#{url})")
	 int addCommPic(YjjTblCommodityPic commodityPic);


}
