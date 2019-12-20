package com.great.childschool.mapper;


import com.great.childschool.entity.TblMenu;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.entity.YjjSearchInfo;
import com.great.childschool.entity.YjjTblMenuRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 权限管理访问数据库接口
 *     by 严俊杰
 */
@Mapper
public interface YjjWorkerPowerControlMapper
{
	//获得表格数据 by严俊杰
	List<TblWorker> findWorker(YjjSearchInfo searchInfo);

	//总条数 by严俊杰
	List<TblWorker> totalPage(YjjSearchInfo searchInfo);

	//修改用户状态 by严俊杰

	@Update("update tbl_worker set sid=#{sid} where wid=#{wid}")
	int updateStatus(@Param("wid")String wid,@Param("sid")String sid);

	//获得用户对应的一级菜单mid by严俊杰
	@Select("select tbl_menu.* from tbl_menu,(select mid from tbl_menu_role where wid=#{wid})A where tbl_menu.mid=A.mid and mpid=0")
	List<TblMenu> findFirstMenu(@Param("wid")int wid);

	//获得父级菜单对应子的菜单 by严俊杰
	@Select("select tbl_menu.* from tbl_menu,(select mid from tbl_menu_role where wid=#{wid})A where tbl_menu.mid=A.mid and mpid=#{mid}")
	List<TblMenu> findChildMenu(@Param("wid")int wid,@Param("mid")int mid);


	//查询获得用户的拥有的菜单是否勾选 by严俊杰 12.19
	@Select("select * from tbl_menu_role where wid=#{wid} and mid=#{mid}")
	YjjTblMenuRole findMenuStatus(@Param("wid")int wid, @Param("mid")int mid);

	//请空用户的菜单的勾选状态,4为不勾选 by严俊杰 12.20
	@Update("update tbl_menu_role set sid=4 where wid=#{wid}")
	int initSid(@Param("wid")String wid);

	//修改sid为勾选3, by严俊杰 12.20
	@Update("update tbl_menu_role set sid=3 where wid=#{wid} and mid=#{mid}")
	int updateSid(@Param("wid")String wid,@Param("mid")int mid);


}
