package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CcPickupDeptMapper
{

	/**
	 * 接送管理-数据显示
	 * by 陈超
	 */
	public List<CcTblBaby> find(CcTableInf ccTableInf);
	/**
	 * 接送管理-分页
	 * by 陈超
	 */
	public List<CcTblBaby> totalPage(CcTableInf ccTableInf);

	/**
	 * 接送管理-下拉框获取班级教室名称
	 * by 陈超
	 */
	public List<CcTblClassroom> findclassroom();
//
//	/**
//	 * 教师管理-查询角色方法
//	 * by 陈超
//	 */
//	public List<TblRole>findrole();
//
	/**
	 * 教师考勤管理-查询方法
	 * by 陈超
	 */
	public List<CcTblBabySign>findtime(int bid, String beginDate, String endDate);

}
