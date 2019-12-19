package com.great.childschool.mapper;

import com.great.childschool.entity.TblMenu;
import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.TblWorker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 幼儿园家长登入访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjWorkerLoginMapper
{

	/**
	 * 幼儿园工作人员登入，查询账号和密码是否存在
	 *     by 严俊杰
	 */
	@Select("Select * from tbl_worker where wid=#{wid} and wpsw=#{passWord}")
	TblWorker workerLoginCheck(@Param("wid") String wid, @Param("passWord") String passWord);

	/**
	 * 幼儿园工作人员登入，根据对应账号获得对应匹配的菜单
	 *     by 严俊杰
	 */
	@Select("select A.mid,A.mname,B.mpid,B.url,B.mname as childName from" + "(select tbl_menu.* from tbl_menu,(select mid from tbl_menu_role where wid=#{wid} and sid=3)A where tbl_menu.mid=A.mid)A,(select tbl_menu.* from tbl_menu,(select mid from tbl_menu_role where wid=#{wid} and sid=3)A where tbl_menu.mid=A.mid)B where A.mid=B.mpid")
	List<TblMenu> workerMenuFind(@Param("wid") String wid);





}
