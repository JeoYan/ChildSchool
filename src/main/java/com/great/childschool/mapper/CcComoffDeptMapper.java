package com.great.childschool.mapper;

import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblCommodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcComoffDeptMapper
{

	/**
	 * 商店下架-数据显示
	 * by 陈超
	 */
	public List<CcTblCommodity> findxj(CcTableInf ccTableInf);
	/**
	 * 商店下架-分页
	 * by 陈超
	 */
	public List<CcTblCommodity> totalPagexj(CcTableInf ccTableInf);

	/**
	 * 商店管理-下架方法
	 * by 陈超
	 */
	public int deletecommodity(CcTblCommodity ccTblCommodity);


}
