package com.great.childschool.service;

import com.great.childschool.entity.CcTblInfo;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.mapper.CcFaceDeptMapper;
import com.great.childschool.mapper.CcInformationDeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CcFaceService
{

    @Resource
    private CcFaceDeptMapper ccFaceDeptMapper;

    //查询所有职工
    @Transactional
    public List<TblWorker> selectAllWorkers(){
        return ccFaceDeptMapper.selectAllWorkers();
    }

    //人脸识别-增加
    @Transactional
    public void save(TblWorker tblWorker){
        ccFaceDeptMapper.save(tblWorker);
    }

    //人脸识别数据查询
    public TblWorker selectface(){
        return ccFaceDeptMapper.selectface();
    }

	/**
	 * 修改
	 * by 陈超
	 */
	public int update(int wid){
		return ccFaceDeptMapper.update(wid);
	}
}
