package com.great.childschool.mapper;

import com.great.childschool.entity.TblParent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 家长登入访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjParentLoginMapper
{

	/**
	 * 家长登入，查询账号和密码是否存在
	 *     by 严俊杰
	 */
	@Select("Select * from tbl_parent where pphone=#{uPhone} and ppsw=#{passWord}")
	TblParent parentLoginCheck(@Param("uPhone")String uPhone,@Param("passWord")String passWord);


}
