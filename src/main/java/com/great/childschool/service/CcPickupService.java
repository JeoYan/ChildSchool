package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcPickupDeptMapper;
import com.great.childschool.mapper.CcTAttendanceDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcPickupService
{

    @Resource
    private CcPickupDeptMapper ccPickupDeptMapper;

    /**
     * 接送管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> find(CcTableInf ccTableInf){

        return ccPickupDeptMapper.find(ccTableInf);
    }
    /**
     * 接送管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> totalPage(CcTableInf ccTableInf){

        return ccPickupDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 班级管理-下拉框获取班级教室名称
     * by 陈超
     */
    public List<CcTblClassroom> findclassroom(){

        return ccPickupDeptMapper.findclassroom();
    }
//    /**
//     * 教师管理-查询角色方法
//     * by 陈超
//     */
//    @Transactional
//    public List<TblRole>findrole(){
//
//    	return ccPickupDeptMapper.findrole();
//    }
//
    /**
     * 教师考勤管理-查询方法
     * by 陈超
     */
    public List<CcTblBabySign>findtime(int bid,String beginDate,String endDate){
        return  ccPickupDeptMapper.findtime(bid, beginDate, endDate);
    }

}
