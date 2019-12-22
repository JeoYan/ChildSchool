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
	 * 家长管理-数据显示
	 * by 陈超
	 */
	public List<CcTblParent> findTblParent(CcTableInf ccTableInf);
	/**
	 * 家长管理-分页
	 * by 陈超
	 */
	public List<CcTblParent> totalPage1(CcTableInf ccTableInf);

	/**
	 * 家长管理-删除家长方法
	 * by 陈超
	 */
	public int deleteparent(int pid);

	//	根据pid查到bid
	public CcTblParentBaby findb (int pid);
	/**
	 * 家长管理-删除宝宝方法
	 * by 陈超
	 */
	public int deletebaby(int bid);
	/**
	 * 家长管理-删除亲子关系
	 * by 陈超
	 */
	public int deletepb(CcTblParentBaby ccTblParentBaby);

	/**
	 * 家长管理-修改方法
	 * by 陈超
	 */
	public int updateparent(CcTblParent ccTblParent);

//	/**
//	 * 家长管理-增加方法
//	 * by 陈超
//	 */
//	public int  addparent(CcTblParent ccTblParent);

	/**
	 * 家长管理-下拉框获取宝宝名称
	 * by 陈超
	 */
	public List<CcTblBaby> findbaby();

//	/**
//	 * 家长管理-下拉框-查询亲子关系
//	 * by 陈超
//	 */
//	public List<CcTblParentBaby> findrelation();
}
