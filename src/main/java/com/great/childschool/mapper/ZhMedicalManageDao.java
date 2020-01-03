package com.great.childschool.mapper;


import com.great.childschool.entity.TblBaby;
import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.TblClassroom;
import com.great.childschool.entity.ZhTblCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 体检管理dao层
 * by 张宏
 */

@Mapper
public interface ZhMedicalManageDao
{

	/**
	 * 保健端体检管理
	 *
	 */



	//查询全部班级
	@Select("select * from  tbl_classroom")
	public List<TblClassroom> allClass();

	//查询班级内的宝宝
	@Select("select * from  tbl_baby where cid=#{cId} ")
	public List<TblBaby> findbaby(@Param("cId") long cId);

	//查询是否已增加完体检
	@Select("select * from  tbl_checklist  where  bid=#{bId} and  checkdate=#{checkDate}")
	public TblChecklist findMedical(TblChecklist tblChecklist);

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

	//删除体检信息
	@Delete("delete from tbl_checklist where bid=#{bId} AND checkdate=#{checkDate}")
	public int deleteMedical(TblChecklist tblChecklist);



	/**
	 * 家长端体检情况
	 *
	 */

	//家长端体检情况表格
	@Select("SELECT *  from  (select bid FROM tbl_parent_baby where pid=#{pid}) a, tbl_checklist c, tbl_baby b where  b.bid=a.bid  and  c.bid=b.bid  ORDER BY c.checkdate desc  limit #{page},5 ")
	public List<TblChecklist> findMedicalCase (TblChecklist tblChecklist);


	//家长的体检情况总条数
	@Select("SELECT *  from  (select bid FROM tbl_parent_baby where pid=#{pid}) a, tbl_checklist c, tbl_baby b where  b.bid=a.bid  and  c.bid=b.bid  ORDER BY c.checkdate desc ")
	public List<TblChecklist>  totalPageMedicalCase (TblChecklist tblChecklist);


	//查看体检课程
	public List<ZhTblCourse>  findMedicalCourse (ZhTblCourse zhTblCourse);

	public List<ZhTblCourse>  totalPageMedicalCourse (ZhTblCourse zhTblCourse);

}
