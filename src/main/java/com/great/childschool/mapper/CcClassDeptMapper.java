package com.great.childschool.mapper;

import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcClassDeptMapper
{

	/**
	 * 班级管理-数据显示
	 * by 陈超
	 */
	public List<CcTblClassroom> find(CcTableInf ccTableInf);
	/**
	 * 班级管理-分页
	 * by 陈超
	 */
	public List<CcTblClassroom> totalPage(CcTableInf ccTableInf);


	/**
	 * 班级管理-删除方法
	 * by 陈超
	 */
	public int deleteclass(int cid);

	/**
	 * 班级管理-修改方法
	 * by 陈超
	 */
	public int updateclass(CcTblClassroom ccTblClassroom);

	/**
	 * 班级管理-增加方法
	 * by 陈超
	 */
	public int  addclass(CcTblClassroom ccTblClassroom);


	/**
	 * 班级管理-下拉框获取班级教室名称
	 * by 陈超
	 */
	public List<CcTblClassroom> findclassroom();

	/**
	 * 班级管理-根据cid查询对应的教室
	 * by 陈超
	 */
	public CcTblClassroom findclassroom1(int cid);

	/**
	 * 班级管理-下拉框获取班级教师名称
	 * by 陈超
	 */
	public List<TblWorker> findtea();

}
