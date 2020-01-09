package com.great.childschool.service;


import com.great.childschool.entity.CcTableInf;
import com.great.childschool.entity.CcTblCommodity;
import com.great.childschool.mapper.CcComoffDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcComoffService
{

    @Resource
    private CcComoffDeptMapper ccComoffDeptMapper;


    /**
     * 商店下架-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblCommodity> findxj(CcTableInf ccTableInf){

        return ccComoffDeptMapper.findxj(ccTableInf);
    }
    /**
     * 商店下架-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblCommodity> totalPagexj(CcTableInf ccTableInf){
        return ccComoffDeptMapper.totalPagexj(ccTableInf);
    }

    /**
     * 商店管理-下架方法
     * by 陈超
     */
    @Transactional
    public int deletecommodity(CcTblCommodity ccTblCommodity)
    {

        return ccComoffDeptMapper.deletecommodity(ccTblCommodity);
    }


}
