package com.great.childschool.mapper;


import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.YjjTblBookPage;
import com.great.childschool.entity.ZhTblParentReadbook;
import org.apache.ibatis.annotations.*;


/**
 * 支付dao业务层
 * by 张宏
 */
@Mapper
public interface ZhAlipayMapper
{
	//查询书籍支付状态
	@Select("select * from tbl_parent_readbook where pid=#{pid} and bookid=#{bookid}")
	ZhTblParentReadbook findbooktype(@Param("pid")String pid, @Param("bookid")String bookid);


	//修改体检信息
	@Update("update tbl_readbook set paytype=#{paytype} where bid=#{bId} AND checkdate=#{checkDate}")
	public int updateMedical(@Param("paytype")String paytype);

	//增加支付书籍信息
	@Insert("insert tbl_parent_readbook (pid,bookid,paytype) values (#{pid},#{bookid},'已支付')")
	public int addParentReadbook(@Param("pid")String pid,@Param("bookid")String bookid);

}
