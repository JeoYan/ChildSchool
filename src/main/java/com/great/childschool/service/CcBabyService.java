package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcBabyDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcBabyService
{

    @Resource
    private CcBabyDeptMapper ccBabyDeptMapper;


    /**
     * 幼儿管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> findTblBaby(CcTableInf ccTableInf){

        return ccBabyDeptMapper.findTblBaby(ccTableInf);
    }
    /**
     * 幼儿管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> totalPage1(CcTableInf ccTableInf){

        return ccBabyDeptMapper.totalPage1(ccTableInf);
    }

    /**
     * 幼儿管理-删除方法
     * by 陈超
     */
    @Transactional
    public int deletebaby(int bid)
    {
        return ccBabyDeptMapper.deletebaby(bid);
    }

    //	根据bid查到pid
    public CcTblParentBaby findp (int bid){
        return ccBabyDeptMapper.findp(bid);
    }

    /**
     * 幼儿管理-删除家长方法
     * by 陈超
     */
    public int deleteparent(int pid){
        return ccBabyDeptMapper.deleteparent(pid);
    }
    /**
     * 幼儿管理-删除亲子关系
     * by 陈超
     */
    public int deletepb(CcTblParentBaby ccTblParentBaby){
        return ccBabyDeptMapper.deletepb(ccTblParentBaby);
    }

    /**
     * 幼儿管理-修改方法
     * by 陈超
     */
    @Transactional
    public int updatebaby(CcTblBaby ccTblBaby)
    {

        return ccBabyDeptMapper.updatebaby(ccTblBaby);
    }

    /**
     * 幼儿管理-增加
     * by 陈超
     */
    @Transactional
    public int  addbaby(CcTblBaby ccTblBaby){

        return ccBabyDeptMapper.addbaby(ccTblBaby);
    }

    /**
     * 幼儿管理-入园信息增加宝宝
     * by 陈超
     */
    @Transactional
    public int  addAdmissionb(CcTblBaby ccTblBaby){
        return ccBabyDeptMapper.addAdmissionb(ccTblBaby);
    }


    /**
     * 幼儿管理-入园信息增加家长
     * by 陈超
     */
    @Transactional
    public int  addAdmissionp(CcTblParent ccTblParent){
        return ccBabyDeptMapper.addAdmissionp(ccTblParent);
    }


    /**
     * 幼儿管理-入园信息增加亲子关系
     * by 陈超
     */
    public int  addAdmissionpb(CcTblParentBaby ccTblParentBaby){
        return ccBabyDeptMapper.addAdmissionpb(ccTblParentBaby)
;    }

    /**
     * 幼儿管理-下拉框获取班级教室名称
     * by 陈超
     */
    public List<CcTblClassroom> findcname(){
        return  ccBabyDeptMapper.findcname();
    }

    //根据宝宝名查询宝宝id
    @Transactional
    public CcTblBaby findbid (String bname){
        return ccBabyDeptMapper.findbid(bname);
    }

    //	根据家长名查询家长id
    @Transactional
    public CcTblParent findpid (String pname){

        return ccBabyDeptMapper.findpid(pname);
    }
}
