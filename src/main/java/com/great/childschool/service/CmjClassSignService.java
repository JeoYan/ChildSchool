package com.great.childschool.service;

import com.great.childschool.entity.CmjBabySign;
import com.great.childschool.entity.CmjTblBaby;
import com.great.childschool.mapper.ClassSignMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@className CmjClassSignService
 *@description 班级考勤service
 *@author MJChen
 *@date 2019/12/24 9:58
 *@version 1.0
 **/
@Service
public class CmjClassSignService
{
	@Resource
	private ClassSignMapper classSignMapper;
	public List<CmjTblBaby> classSign(int curPage,int size,String babyName){
		return classSignMapper.classSign(curPage,size,babyName);
	}
	public Integer countSign(String babyName){
		return classSignMapper.countSign(babyName);
	}

	public List<CmjBabySign> babySigin(int bid,String beginDate,String endDate){
		return classSignMapper.babySigin(bid,beginDate,endDate);
	}
}
