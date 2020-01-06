package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcClassmenDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcClassmenService
{

    @Resource
    private CcClassmenDeptMapper ccClassmenDeptMapper;


    /**
     *  班级成员管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblClassroom> find(CcTableInf ccTableInf){

        return ccClassmenDeptMapper.find(ccTableInf);
    }
    /**
     *  班级成员管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblClassroom> totalPage(CcTableInf ccTableInf){
        return ccClassmenDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 班级成员管理-删除宝宝方法
     * by 陈超
     */
    @Transactional
    public int deletebaby(CcTblBaby ccTblBaby){
        return ccClassmenDeptMapper.deletebaby(ccTblBaby);
    }

    //    //	根据bname查到bid
    //    public CcTblBaby findbaby(String bname){
    //        return ccClassmenDeptMapper.findbaby(bname);
    //    }

    //	根据bid查到pid
    @Transactional
    public CcTblParentBaby findp (int bid){
        return ccClassmenDeptMapper.findp(bid);
    }

    /**
     * 班级成员管理-删除家长方法
     * by 陈超
     */
    @Transactional
    public int deleteparent(int pid){
        return ccClassmenDeptMapper.deleteparent(pid);
    }


    /**
     * 班级成员管理-下拉框获取班级教室名称
     * by 陈超
     */
    @Transactional
    public List<CcTblClassroom> findclassroom(){
        return ccClassmenDeptMapper.findclassroom();
    }

    /**
     * 班级成员管理-删除亲子方法
     * by 陈超
     */
    @Transactional
    public int deletepb(CcTblParentBaby ccTblParentBaby){
        return ccClassmenDeptMapper.deletepb(ccTblParentBaby);
    }

    /**
     * 班级成员管理-下拉框获取班级教师名称
     * by 陈超
     */
    @Transactional
    public List<TblWorker> findtea(){
        return ccClassmenDeptMapper.findtea();
    }

    /**
     * 班级成员管理-根据cid查询对应的教室名称Sql
     * by 陈超
     */
    public CcTblClassroom findcname(int cid){

        return ccClassmenDeptMapper.findcname(cid);
    }


    /**
     * 班级成员管理-修改宝宝名称方法
     * by 陈超
     */
    @Transactional
    public int updateb(CcTblBaby ccTblBaby){
        return ccClassmenDeptMapper.updateb(ccTblBaby);
    }

    /**
     * 班级成员管理-修改教室名称方法
     * by 陈超
     */
    @Transactional
    public int updatec(CcTblClassroom ccTblClassroom){
        return ccClassmenDeptMapper.updatec(ccTblClassroom);
    }

}
