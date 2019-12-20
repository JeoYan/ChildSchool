package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcTeacherDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcTeacherService
{

    @Resource
    private CcTeacherDeptMapper ccTeacherDeptMapper;

    /**
     * 教师管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<TblWorker> findTableInf(CcTableInf ccTableInf){

        return ccTeacherDeptMapper.findTableInf(ccTableInf);
    }
    /**
     * 教师管理-分页
     * by 陈超
     */
    @Transactional
    public List<TblWorker> totalPage(CcTableInf ccTableInf){

        return ccTeacherDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 教师管理-删除方法
     * by 陈超
     */
    @Transactional
    public int deleteteacher(int wid)
    {
        return ccTeacherDeptMapper.deleteteacher(wid);
    }

	/**
	 * 教师管理-删除菜单方法
	 * by 陈超
	 */
	@Transactional
	public int deletemenu(int wid)
	{
		return ccTeacherDeptMapper.deletemenu(wid);
	}


	/**
	 * 教师管理-据wName查询
	 * by 陈超
	 */
//    public int findwid(String wname){
//        return ccTeacherDeptMapper.findwid(wname);
//    }



    /**
     * 教师管理-增加方法
     * by 陈超
     */

	/**
	 * 修改业务层获得自增长id
	 * by 严俊杰 12.18
	 */
	@Transactional
	public int  addteacher(TblWorker tblWorker){
		ccTeacherDeptMapper.addteacher(tblWorker);
		int wid = tblWorker.getWid();
		return wid;
	}


	/**
	 * 查询所有菜单的mid
	 * by 严俊杰12.18
	 */
	@Transactional
	public List<TblMenu> findAllMenu(){
		return ccTeacherDeptMapper.findAllMenu();
	}


	/**
	 * 对tbl_menu_role绑定菜单
	 * by 严俊杰12.18
	 */
	@Transactional
	public int addMenuRole(List<TblMenu> list,int wid){

		int i=0;
		for (TblMenu tblMenu : list)
		{
			int x=ccTeacherDeptMapper.addMenuRole(String.valueOf(tblMenu.getMid()),String.valueOf(wid));
			if(x>0){
				i++;
			}
		}
		return i;
	}



	/**
	 * 获得rid初始对应的菜单id
	 * by 严俊杰12.18
	 */
	@Transactional
	public List<TblMenu> findRoleMenu(String rid){
		return ccTeacherDeptMapper.findRoleMenu(rid);
	}



	/**
	 * 用获取到的mid更改tbl_menu_role的sid为3
	 * by 严俊杰12.18
	 */
	@Transactional
	public void updateMenuRoleSid(List<TblMenu> list,int wid){

		for (TblMenu tblMenu : list)
		{
			ccTeacherDeptMapper.updateMenuRoleSid(tblMenu.getMid(),wid);
		}
	}








    /**
     * 教师管理-工作人员关系表 增加
     * by 陈超
     */
    @Transactional
    public int  addworkerrole(TblWorkerRole tblWorkerRole){

        return ccTeacherDeptMapper.addworkerrole(tblWorkerRole);
    }

    /**
     * 教师管理-修改方法
     * by 陈超
     */
    @Transactional
    public int updateteacher(TblWorker tblWorker)
    {

        return ccTeacherDeptMapper.updateteacher(tblWorker);
    }
    /**
     * 教师管理-查询角色方法
     * by 陈超
     */
    @Transactional
    public List<TblRole>findrole(){
        return ccTeacherDeptMapper.findrole();
    }
    

}
