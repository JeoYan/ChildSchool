package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcTAttendanceDeptMapper;
import com.great.childschool.mapper.CcTeacherDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcTAttendanceService
{

    @Resource
    private CcTAttendanceDeptMapper ccTAttendanceDeptMapper;

    /**
     * 教师管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<TblWorker> find(CcTableInf ccTableInf){

        return ccTAttendanceDeptMapper.find(ccTableInf);
    }
    /**
     * 教师管理-分页
     * by 陈超
     */
    @Transactional
    public List<TblWorker> totalPage(CcTableInf ccTableInf){

        return ccTAttendanceDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 教师管理-查询角色方法
     * by 陈超
     */
    @Transactional
    public List<TblRole>findrole(){

    	return ccTAttendanceDeptMapper.findrole();
    }

    /**
     * 教师考勤管理-查询方法
     * by 陈超
     */
    public List<CcTblWorkerSign>findtime(int wid,String beginDate,String endDate){
        return  ccTAttendanceDeptMapper.findtime(wid, beginDate, endDate);
    }

}
