package com.great.childschool.mapper;

import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.TblFood;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 膳食dao层
 * by 张宏
 */


@Mapper
public interface ZhFoodManageMapper
{

	//查询是否已增加餐食
	@Select("select * from  tbl_food  where  fdate=#{fdate} and  fitem=#{fitem}")
	public TblFood findFood(TblFood tblFood);

	//增加膳食
	@Insert("insert tbl_food (fdate,fitem,fname) values (#{fdate},#{fitem},#{fname})")
	public int addFood(TblFood tblFood);


	//保健端膳食管理表格
	public List<TblFood> findFoodManage(TblFood tblFood);

	//保健端膳食管理表格总页数
	public List<TblFood> totalPageFoodManage(TblFood tblFood);

	//修改膳食
	@Update("update tbl_food set fname=#{fname} where fdate=#{fdate} AND fitem=#{fitem}")
	public int updateFood(TblFood tblFood);

	//删除膳食
	@Delete("delete from tbl_food where fid=#{fid}")
	public int deleteFood(TblFood tblFood);

}
