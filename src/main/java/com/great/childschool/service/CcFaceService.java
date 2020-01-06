package com.great.childschool.service;

import com.great.childschool.entity.*;
import com.great.childschool.mapper.CcFaceDeptMapper;
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

	//查询所有宝宝
	public List<CcTblBaby> selectAllBaby(){
    	return ccFaceDeptMapper.selectAllBaby();
	}

//    //人脸识别-增加
//    @Transactional
//    public void save(TblWorker tblWorker){
//        ccFaceDeptMapper.save(tblWorker);
//    }
//
//    //人脸识别数据查询
//    public TblWorker selectface(){
//        return ccFaceDeptMapper.selectface();
//    }
//
//	/**
//	 * 修改
//	 * by 陈超
//	 */
//	public int update(int wid){
//		return ccFaceDeptMapper.update(wid);
//	}

	/**
	 * 考勤方法-增加教师语句
	 * by 陈超
	 */
	public int  add(CcTblWorkerSign ccTblWorkerSign){
		return ccFaceDeptMapper.add(ccTblWorkerSign);
	}

	/**
	 * 考勤方法-增加宝宝语句
	 * by 陈超
	 */
	public int  add1(CcTblBabySign ccTblBabySign){
		return ccFaceDeptMapper.add1(ccTblBabySign);
	}
}
