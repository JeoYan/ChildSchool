package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcFaceDeptMapper
{
	//查询所有职工
	public List<TblWorker> selectAllWorkers();

	//查询所有宝宝
	public List<CcTblBaby> selectAllBaby();
//	//人脸识别-增加
//	public void save(TblWorker tblWorker);
//
//	//人脸识别数据查询
//	public TblWorker selectface();
//
//	/**
//	 * 修改
//	 * by 陈超
//	 */
//	public int update(int wid);

	/**
	 * 考勤方法-增加职工语句
	 * by 陈超
	 */
	public int  add(CcTblWorkerSign ccTblWorkerSign);
	/**
	 * 考勤方法-增加宝宝语句
	 * by 陈超
	 */
	public int  add1(CcTblBabySign ccTblBabySign);
}
