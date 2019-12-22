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
     * 家长管理-数据显示
     * by 陈超
     */
    @Transactional
    public List<CcTblParent> findTblParent(CcTableInf ccTableInf){

        return ccParentDeptMapper.findTblParent(ccTableInf);
    }
    /**
     * 家长管理-分页
     * by 陈超
     */
    @Transactional
    public List<CcTblParent> totalPage1(CcTableInf ccTableInf){

        return ccParentDeptMapper.totalPage1(ccTableInf);
    }

    /**
     * 家长管理-删除家长方法
     * by 陈超
     */
    @Transactional
    public int deleteparent(int pid)
    {
        return ccParentDeptMapper.deleteparent(pid);
    }
	//	根据bid查到pid
	public CcTblParentBaby findb (int pid){

		return ccParentDeptMapper.findb(pid);
	}

	/**
	 * 家长管理-删除方法
	 * by 陈超
	 */
	@Transactional
	public int deletebaby(int bid)
	{
		return ccParentDeptMapper.deletebaby(bid);
	}

	/**
	 * 家长管理-删除亲子关系
	 * by 陈超
	 */
	public int deletepb(CcTblParentBaby ccTblParentBaby){

		return ccParentDeptMapper.deletepb(ccTblParentBaby);
	}

    /**
     * 家长管理-修改方法
     * by 陈超
     */
    @Transactional
    public int updateparent(CcTblParent ccTblParent)
    {

        return ccParentDeptMapper.updateparent(ccTblParent);
    }

//    /**
//     * 家长管理-增加
//     * by 陈超
//     */
//    @Transactional
//    public int  addparent(CcTblParent ccTblParent){
//
//        return ccParentDeptMapper.addparent(ccTblParent);
//    }

    /**
     * 家长管理-下拉框获取宝宝名称
     * by 陈超
     */
    @Transactional
    public List<CcTblBaby> findbaby(){
        return  ccParentDeptMapper.findbaby();
    }


}
