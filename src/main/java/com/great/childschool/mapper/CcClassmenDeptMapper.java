package com.great.childschool.mapper;

import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcClassmenDeptMapper
{

	/**
	 * 班级成员管理-数据显示
	 * by 陈超
	 */
	public List<CcTblClassroom> find(CcTableInf ccTableInf);
	/**
	 * 班级成员管理-分页
	 * by 陈超
	 */
	public List<CcTblClassroom> totalPage(CcTableInf ccTableInf);



	/**
	 * 班级成员管理-删除宝宝方法
	 * by 陈超
	 */
	public int deletebaby(CcTblBaby ccTblBaby);


//	根据bid查到pid
	public CcTblParentBaby findp(int bid);

	/**
	 * 班级成员管理-删除家长方法
	 * by 陈超
	 */
	public int deleteparent(int pid);
	/**
	 * 班级成员管理-删除亲子关系
	 * by 陈超
	 */
	public int deletepb(CcTblParentBaby ccTblParentBaby);

	/**
	 * 班级成员管理-下拉框获取班级教室名称
	 * by 陈超
	 */
	public List<CcTblClassroom> findclassroom();


	/**
	 * 班级成员管理-下拉框获取班级教师名称
	 * by 陈超
	 */
	public List<TblWorker> findtea();

	/**
	 * 班级成员管理-根据cid查询对应的教室名称Sql
	 * by 陈超
	 */
	public CcTblClassroom findcname(int cid);

	/**
	 * 班级成员管理-修改宝宝名称方法
	 * by 陈超
	 */
	public int updateb(CcTblBaby ccTblBaby);

	/**
	 * 班级成员管理-修改教室名称方法
	 * by 陈超
	 */
	public int updatec(CcTblClassroom ccTblClassroom);

}
