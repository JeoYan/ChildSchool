package com.great.childschool.service;

import com.great.childschool.entity.CmjTblBaby;
import com.great.childschool.entity.TblParent;
import com.great.childschool.mapper.ClassInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *@className CmjClassInfoService
 *@author MJChen
 *@date 2019/12/18 14:07
 *@version 1.0
 **/
@Service
public class CmjClassInfoService
{
	@Resource
	private ClassInfoMapper classInfoMapper;
	/**
	 * @Author MJChen
	 * @Description //查询班级信息
	 * @Date 14:15 2019/12/18
	 * @Param []
	 * @return java.util.List<com.great.childschool.entity.CmjTblBaby>
	 **/
	public List<CmjTblBaby> queryClassInfo(int page,int size,String beginDate,String endDate){
		return classInfoMapper.queryClassInfo(page,size,beginDate,endDate);
	}
	public Integer countClassInfo(String beginDate,String endDate){
		return classInfoMapper.countClassInfo(beginDate,endDate);
	}

	public List<Object> findBabyFamily(int bid){
		CmjTblBaby babyInfo = classInfoMapper.findBabyInfo(bid);
		TblParent parentInfo = classInfoMapper.findParentInfo(bid);
		List<Object> babyFamilyList = new ArrayList<Object>();
		babyFamilyList.add(babyInfo);
		babyFamilyList.add(parentInfo);
		return babyFamilyList;
	}
}
