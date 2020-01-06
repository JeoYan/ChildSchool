package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CcInformationDeptMapper
{

	/**
	 * 修改密码
	 * by 陈超
	 */
	public TblWorker findpsw(int wid);



	/**
	 * 修改密码
	 * by 陈超
	 */
	public int updatepsw(TblWorker tblWorker);

	/**
	 * 个人信息
	 * by 陈超
	 */
	public CcTblInfo findinfo(int wid);

	/**
	 * 家长端-宝宝信息
	 * by 陈超
	 */
	public CcTblParentBaby findb(int pid);
	public CcTblBabyInfo findbabyinfo(int bid);
}
