package com.great.childschool.mapper;



import com.great.childschool.entity.ZhTblWorker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZhFaceMapper
{
	//查询所有工作人员
	@Select("select * from tbl_worker ")
	public List<ZhTblWorker> findAllWork();

	@Insert("insert into tbl_worker (wname,wpsw,wface) values (#{wname},#{wpsw},#{wface,typeHandler=org.apache.ibatis.type.BlobTypeHandler})")
	public int save(ZhTblWorker tbladmin);


	@Select("select * from tbl_worker where FACE=#{face,typeHandler=org.apache.ibatis.type.BlobTypeHandler}")
	public List<ZhTblWorker> login(ZhTblWorker zhTblWorker);

}
