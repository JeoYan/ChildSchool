package com.great.childschool.mapper;

import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcBabyDeptMapper
{

	/**
	 * 幼儿管理-数据显示
	 * by 陈超
	 */
	public List<CcTblBaby> findTblBaby(CcTableInf ccTableInf);
	/**
	 * 幼儿管理-分页
	 * by 陈超
	 */
	public List<CcTblBaby> totalPage1(CcTableInf ccTableInf);

	/**
	 * 幼儿管理-删除方法
	 * by 陈超
	 */
	public int deletebaby(int bid);


	/**
	 * 幼儿管理-修改方法
	 * by 陈超
	 */
	public int updatebaby(CcTblBaby ccTblBaby);

	/**
	 * 幼儿管理-增加方法
	 * by 陈超
	 */
	public int  addbaby(CcTblBaby ccTblBaby);
}
