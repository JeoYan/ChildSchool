package com.great.childschool.mapper;


import com.great.childschool.entity.TblBaby;
import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.TblClassroom;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 体检管理表格dao层
 * by 张宏
 */

@Mapper
public interface ZhMedicalManageDao
{

	//查询全部班级
	@Select("select * from  tbl_classroom")
	public List<TblClassroom> allClass();

	//查询班级内的宝宝
	@Select("select * from  tbl_baby where cid=#{cId} ")
	public List<TblBaby> findbaby(@Param("cId") long cId);


	//增加体检信息
	@Insert("insert tbl_checklist (bid,height,weight,vision,temperature,sid,checkdate) values (#{bId},#{height},#{weight},#{vision},#{temperature},#{sId},#{checkDate})")
	public int addMedical(TblChecklist tblChecklist);

	//保健端体检管理表格
	public List<TblChecklist> findMedicalManage (TblChecklist tblChecklist);

	//保健端体检管理表格的总页数
	public List<TblChecklist>  totalPageMedicalManage(TblChecklist tblChecklist);

   //修改体检信息
   @Update("update tbl_checklist set height=#{height},weight=#{weight},vision=#{vision},temperature=#{temperature},sid=#{sId} where bid=#{bId} AND checkdate=#{checkDate}")
   public int updateMedical(TblChecklist tblChecklist);




}
