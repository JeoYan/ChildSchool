package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CcTeacherDeptMapper
{

	/**
	 * 教师管理-数据显示
	 * by 陈超
	 */
	public List<TblWorker> findTableInf(CcTableInf ccTableInf);
	/**
	 * 教师管理-分页
	 * by 陈超
	 */
	public List<TblWorker> totalPage(CcTableInf ccTableInf);

	/**
	 * 教师管理-删除方法
	 * by 陈超
	 */
	public int deleteteacher(int wid);

	/**
	 * 教师管理-据wName查询
	 * by 陈超
	 */
	public int findwid(String wname);

	/**
	 * 教师管理-增加方法
	 * by 陈超
	 */
	public int  addteacher(TblWorker tblWorker);

	/**
	 * 教师管理-工作人员关系表 增加
	 * by 陈超
	 */
	public int  addworkerrole(TblWorkerRole tblWorkerRole);

	/**
	 * 教师管理-修改方法
	 * by 陈超
	 */
	public int updateteacher(TblWorker tblWorker);

	/**
	 * 教师管理-查询角色方法
	 * by 陈超
	 */
	public List<TblRole>findrole();


	/**
	 * 查询所有菜单的mid
	 * by 严俊杰12.18
	 */
	@Select("select * from tbl_menu")
	List<TblMenu> findAllMenu();

	/**
	 * 对tbl_menu_role绑定菜单
	 * by 严俊杰12.18
	 */
	@Insert("insert into tbl_menu_role (mid,wid,sid) values (#{mid},#{wid},4)")
	int addMenuRole(@Param("mid")String mid, @Param("wid")String wid);

	/**
	 * 获得rid初始对应的菜单id
	 * by 严俊杰12.18
	 */
	@Select("select * from tbl_menu where rid=#{rid}")
	List<TblMenu> findRoleMenu(@Param("rid")String rid);

	/**
	 * 用获取到的mid更改tbl_menu_role的sid为3
	 * by 严俊杰12.18
	 */
	@Update("update tbl_menu_role set sid=3 where mid=#{mid} and wid=#{wid}")
	int updateMenuRoleSid(@Param("mid")int mid,@Param("wid")int wid);



}
