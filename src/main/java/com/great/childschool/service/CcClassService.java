package com.great.childschool.service;

import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcClassDeptMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcClassService
{

    @Resource
    private CcClassDeptMapper ccClassDeptMapper;


    /**
     * 班级管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblClassroom> find(CcTableInf ccTableInf){

        return ccClassDeptMapper.find(ccTableInf);
    }
    /**
     * 班级管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblClassroom> totalPage(CcTableInf ccTableInf){

        return ccClassDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 班级管理-删除方法
     * by 陈超
     */
    @Transactional
    public int deleteclass(int cid)
    {
        return ccClassDeptMapper.deleteclass(cid);
    }


    /**
     * 班级管理-修改方法
     * by 陈超
     */
    @Transactional
    public int updateclass(CcTblClassroom ccTblClassroom){
        return ccClassDeptMapper.updateclass(ccTblClassroom);
    }

    /**
     * 班级管理-增加
     * by 陈超
     */
    @Transactional
    public int  addclass(CcTblClassroom ccTblClassroom){

        return ccClassDeptMapper.addclass(ccTblClassroom);
    }

//
//    public CcTblBaby findbid (String bname){
//        return ccBabyDeptMapper.findbid(bname);
//    }
//
//    public CcTblParent findpid (String pname){
//
//        return ccBabyDeptMapper.findpid(pname);
//    }

    /**
     * 班级管理-下拉框获取班级教室名称
     * by 陈超
     */
    @Transactional
    public List<CcTblClassroom> findclassroom(){
        return ccClassDeptMapper.findclassroom();
    }

    /**
     * 班级管理-根据cid查询对应的教室
     * by 陈超
     */
    @Transactional
    public CcTblClassroom findclassroom1(int cid){
        return ccClassDeptMapper.findclassroom1(cid);
    }


    /**
     * 班级管理-下拉框获取班级教师名称
     * by 陈超
     */
    @Transactional
    public List<TblWorker> findtea(){
        return ccClassDeptMapper.findtea();
    }
}
