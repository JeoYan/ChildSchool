package com.great.childschool.service;


import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblClassroom;
import com.great.childschool.entity.CcTblNotice;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.mapper.CcClassDeptMapper;
import com.great.childschool.mapper.CcPlatformDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcPlatformService
{

    @Resource
    private CcPlatformDeptMapper ccPlatformDeptMapper;


    /**
     * 班级管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblNotice> find(CcTableInf ccTableInf){

        return ccPlatformDeptMapper.find(ccTableInf);
    }
    /**
     * 班级管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblNotice> totalPage(CcTableInf ccTableInf){

        return ccPlatformDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 班级管理-删除方法
     * by 陈超
     */
    @Transactional
    public int deleteplatform(int noid)
    {

        return ccPlatformDeptMapper.deleteplatform(noid);
    }


    /**
     * 班级管理-修改方法
     * by 陈超
     */
    public int updateplatform(CcTblNotice ccTblNotice){
        return ccPlatformDeptMapper.updateplatform(ccTblNotice);
    }

    /**
     * 班级管理-增加
     * by 陈超
     */
    @Transactional
    public int  addplatform(CcTblNotice ccTblNotice){

        return ccPlatformDeptMapper.addplatform(ccTblNotice);
    }

}
