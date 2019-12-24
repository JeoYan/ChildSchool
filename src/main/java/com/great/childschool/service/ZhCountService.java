package com.great.childschool.service;


import com.great.childschool.entity.ZhStudentCount;
import com.great.childschool.entity.ZhTblRole;
import com.great.childschool.mapper.ZhCountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 膳食service层
 * by 张宏
 */
@Service
public class ZhCountService
{

	@Resource
	private ZhCountMapper zhCountMapper;


	//查询全部角色
	public List<ZhTblRole> findAllRole(){
		return zhCountMapper.findAllRole();
	};
	//查询全部角色人数
	public int findRoleNumber(int rid){

		return zhCountMapper.findRoleNumber(rid);
	}

	//查询每个班级拥有男女人数
	public List<ZhStudentCount> findStudentNumber(){
		return  zhCountMapper.findStudentNumber();
	}
}
