package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcBabyDeptMapper;
import com.great.childschool.mapper.CcParentDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcParentService
{

    @Resource
    private CcParentDeptMapper ccParentDeptMapper;


    /**
     * 幼儿管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblParent> findTblParent(CcTableInf ccTableInf){

        return ccParentDeptMapper.findTblParent(ccTableInf);
    }
    /**
     * 幼儿管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblParent> totalPage1(CcTableInf ccTableInf){

        return ccParentDeptMapper.totalPage1(ccTableInf);
    }

    /**
     * 幼儿管理-删除方法
     * by 陈超
     */
    @Transactional
    public int deleteparent(int bid)
    {
        return ccParentDeptMapper.deleteparent(bid);
    }


    /**
     * 幼儿管理-修改方法
     * by 陈超
     */
    @Transactional
    public int updateparent(CcTblParent ccTblParent)
    {

        return ccParentDeptMapper.updateparent(ccTblParent);
    }

    /**
     * 幼儿管理-增加
     * by 陈超
     */
    @Transactional
    public int  addparent(CcTblParent ccTblParent){

        return ccParentDeptMapper.addparent(ccTblParent);
    }

    /**
     * 幼儿管理-下拉框获取宝宝名称
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> findbaby(){
        return  ccParentDeptMapper.findbaby();
    }

//    /**
//     * 幼儿管理-下拉框-查询亲子关系
//     * by 陈超
//     */
//    public List<CcTblParentBaby> findrelation(){
//        return ccParentDeptMapper.findrelation();
//    }
}
