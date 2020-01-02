package com.great.childschool.mapper;

import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblClassroom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcClassmenDeptMapper
{

	/**
	 * 幼儿管理-数据显示
	 * by 陈超
	 */
	public List<CcTblClassroom> find(CcTableInf ccTableInf);
	/**
	 * 幼儿管理-分页
	 * by 陈超
	 */
	public List<CcTblClassroom> totalPage(CcTableInf ccTableInf);



//	/**
//	 * 幼儿管理-删除宝宝方法
//	 * by 陈超
//	 */
//	public int deletebaby(int bid);
//
////	根据bid查到pid
//	public CcTblParentBaby findp(int bid);
//
//	/**
//	 * 幼儿管理-删除家长方法
//	 * by 陈超
//	 */
//	public int deleteparent(int pid);
//	/**
//	 * 幼儿管理-删除亲子关系
//	 * by 陈超
//	 */
//	public int deletepb(CcTblParentBaby ccTblParentBaby);
//
//
//	/**
//	 * 幼儿管理-修改方法
//	 * by 陈超
//	 */
//	public int updatebaby(CcTblBaby ccTblBaby);
//
//	/**
//	 * 幼儿管理-增加方法
//	 * by 陈超
//	 */
//	public int  addbaby(CcTblBaby ccTblBaby);
//
//	/**
//	 * 幼儿管理-入园信息增加宝宝
//	 * by 陈超
//	 */
//	public int  addAdmissionb(CcTblBaby ccTblBaby);
//
//	/**
//	 * 幼儿管理-入园信息增加家长
//	 * by 陈超
//	 */
//	public int  addAdmissionp(CcTblParent ccTblParent);
//
//	/**
//	 * 幼儿管理-入园信息增加亲子关系
//	 * by 陈超
//	 */
//	public int  addAdmissionpb(CcTblParentBaby ccTblParentBaby);
//
//	public CcTblBaby findbid(String bname);
//	public CcTblParent findpid(String pname);
}
