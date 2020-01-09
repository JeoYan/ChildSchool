package com.great.childschool.mapper;

import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblClassroom;
import com.great.childschool.entity.CcTblNotice;
import com.great.childschool.entity.TblWorker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcPlatformDeptMapper
{

	/**
	 * 平台资讯-数据显示
	 * by 陈超
	 */
	public List<CcTblNotice> find(CcTableInf ccTableInf);
	/**
	 * 平台资讯-分页
	 * by 陈超
	 */
	public List<CcTblNotice> totalPage(CcTableInf ccTableInf);



	/**
	 * 平台资讯-删除方法
	 * by 陈超
	 */
	public int deleteplatform(int noid);

	/**
	 * 平台资讯-修改方法
	 * by 陈超
	 */
	public int updateplatform(CcTblNotice ccTblNotice);

	/**
	 * 平台资讯-增加方法
	 * by 陈超
	 */
	public int  addplatform(CcTblNotice ccTblNotice);

}
