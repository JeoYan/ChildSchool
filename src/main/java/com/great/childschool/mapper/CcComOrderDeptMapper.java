package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcComOrderDeptMapper
{

	/**
	 * 订单查询-数据显示
	 * by 陈超
	 */
	public List<CcTblOrder> find(CcTableInf ccTableInf);
	/**
	 * 订单查询-分页
	 * by 陈超
	 */
	public List<CcTblOrder> totalPage(CcTableInf ccTableInf);

	/**
	 * 订单查询-删除方法
	 * by 陈超
	 */
	public int delete(int oid);

	/**
	 * 订单查询-修改方法
	 * by 陈超
	 */
	public int update(CcTblOrder ccTblOrder);

	/**
	 * 订单查询-增加方法
	 * by 陈超
	 */
	public int  add(CcTblOrder ccTblOrder);

	/**
	 * 查询宝宝list
	 * by 陈超
	 */
	public List<CcTblBaby> findbid ();


	/**
	 * 根据宝宝id查询家长id
	 * by 陈超
	 */
	public int  findp (int bid);


	/**
	 * 根据家长id查询家长名
	 * by 陈超
	 */
	public CcTblParent findpname (int pid);


	/**
	 * 根据家长名查询家长id
	 * by 陈超
	 */
	public int pid (String pname);


	/**
	 * 根据商品名查询商品表
	 * by 陈超
	 */
	public CcTblCommodity findcomid (String comtitle);

}
