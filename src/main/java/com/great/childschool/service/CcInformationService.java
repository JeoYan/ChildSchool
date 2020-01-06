package com.great.childschool.service;

import com.great.childschool.entity.CcTblBabyInfo;
import com.great.childschool.entity.CcTblInfo;
import com.great.childschool.entity.CcTblParentBaby;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.mapper.CcInformationDeptMapper;
import com.great.childschool.mapper.CcTAttendanceDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CcInformationService
{

    @Resource
    private CcInformationDeptMapper ccInformationDeptMapper;


    /**
     * 修改密码
     * by 陈超
     */
    @Transactional
    public TblWorker findpsw(int wid){
        return ccInformationDeptMapper.findpsw(wid);
    }

    /**
     * 修改密码
     * by 陈超
     */
    @Transactional
    public int updatepsw(TblWorker tblWorker){
        return ccInformationDeptMapper.updatepsw(tblWorker);
    }

    /**
     * 个人信息
     * by 陈超
     */
    public CcTblInfo findinfo(int wid){
        return  ccInformationDeptMapper.findinfo(wid);
    }

    /**
     * 家长端-宝宝信息
     * by 陈超
     */
    public CcTblParentBaby findb(int pid){
        return ccInformationDeptMapper.findb(pid);
    }
    public CcTblBabyInfo findbabyinfo(int bid){
        return ccInformationDeptMapper.findbabyinfo(bid);
    }

}
