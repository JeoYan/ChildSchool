package com.great.childschool.mapper;


import com.great.childschool.entity.TblWorker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcFaceDeptMapper
{
	//查询所有职工
	public List<TblWorker> selectAllWorkers();

	//人脸识别-增加
	public void save(TblWorker tblWorker);

	//人脸识别数据查询
	public TblWorker selectface();

	/**
	 * 修改
	 * by 陈超
	 */
	public int update(int wid);
}
