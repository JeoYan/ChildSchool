package com.great.childschool.mapper;

import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblBaby;
import com.great.childschool.entity.CcTblParent;
import com.great.childschool.entity.CcTblParentBaby;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcParentDeptMapper
{

	/**
	 * 幼儿管理-数据显示
	 * by 陈超
	 */
	public List<CcTblParent> findTblParent(CcTableInf ccTableInf);
	/**
	 * 幼儿管理-分页
	 * by 陈超
	 */
	public List<CcTblParent> totalPage1(CcTableInf ccTableInf);

	/**
	 * 幼儿管理-删除方法
	 * by 陈超
	 */
	public int deleteparent(int bid);


	/**
	 * 幼儿管理-修改方法
	 * by 陈超
	 */
	public int updateparent(CcTblParent ccTblParent);

	/**
	 * 幼儿管理-增加方法
	 * by 陈超
	 */
	public int  addparent(CcTblParent ccTblParent);

	/**
	 * 幼儿管理-下拉框获取宝宝名称
	 * by 陈超
	 */
	public List<CcTblBaby> findbaby();

//	/**
//	 * 幼儿管理-下拉框-查询亲子关系
//	 * by 陈超
//	 */
//	public List<CcTblParentBaby> findrelation();
}
