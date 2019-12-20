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

}
