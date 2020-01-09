package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcComOrderDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcComOrderService
{
    @Resource
    private CcComOrderDeptMapper ccComOrderDeptMapper;

    /**
     * 订单查询-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblOrder> find(CcTableInf ccTableInf){

        return ccComOrderDeptMapper.find(ccTableInf);
    }
    /**
     * 订单查询-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblOrder> totalPage(CcTableInf ccTableInf){

        return ccComOrderDeptMapper.totalPage(ccTableInf);
    }

    /**
     * 订单查询-增加方法
     * by 陈超
     */
    @Transactional
    public int  add(CcTblOrder ccTblOrder){

        return ccComOrderDeptMapper.add(ccTblOrder);
    }

    /**
     * 查询宝宝list
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> findbid (){

        return ccComOrderDeptMapper.findbid();
    }



    /**
     * 根据宝宝id查询家长id
     * by 陈超
     */
    @Transactional
    public int  findp (int bid){

        return ccComOrderDeptMapper.findp(bid);
    }

    /**
     * 根据家长id查询家长名
     * by 陈超
     */
    @Transactional
    public int pid (String pname){

        return ccComOrderDeptMapper.pid(pname);
    }

    /**
     * 根据家长名查询家长id
     * by 陈超
     */
    @Transactional
    public CcTblParent findpname (int pid){
        return ccComOrderDeptMapper.findpname(pid);
    }


    /**
     * 根据商品名查询商品表
     * by 陈超
     */
    @Transactional
    public CcTblCommodity findcomid (String comtitle){
        return ccComOrderDeptMapper.findcomid(comtitle);
    }

    /**
     * 订单查询-删除方法
     * by 陈超
     */
    @Transactional
    public int delete(int oid){
        return ccComOrderDeptMapper.delete(oid);
    }

    /**
     * 订单查询-修改方法
     * by 陈超
     */
    @Transactional
    public int update(CcTblOrder ccTblOrder){

        return ccComOrderDeptMapper.update(ccTblOrder);
    }

}
