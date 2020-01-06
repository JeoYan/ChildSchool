package com.great.childschool.mapper;


import com.great.childschool.entity.ZhStudentCount;
import com.great.childschool.entity.ZhTblRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 园长端统计dao层
 * by 张宏
 */
@Mapper
public interface ZhCountMapper
{
	//查询全部角色
	@Select("select * from  tbl_role where rid BETWEEN   1  and 4 ")
	public List<ZhTblRole> findAllRole();


	//查询全部角色人数
	@Select("select count(wid) from tbl_worker_role where rid=#{rid} ")
	public int findRoleNumber(@Param("rid") int rid);

	//查询每个班级拥有男女人数
	@Select("select c.cname as classname,sum(case when b.bsex='男' then 1 else 0 end) boy,sum(case when b.bsex='女' then 1 else 0 end) girl from tbl_baby b LEFT JOIN tbl_classroom  c on b.cid=c.cid GROUP BY c.cid ")
	public List<ZhStudentCount> findStudentNumber();

}
