package com.great.childschool.service;

import com.great.childschool.entity.TblFood;
import com.great.childschool.mapper.ZhFoodManageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 膳食ervice层
 * by 张宏
 */
@Service
public class ZhFoodManageService
{
	@Resource
	private ZhFoodManageMapper zhFoodManageMapper;


	//查询是否已增加餐食
	public TblFood findFood(TblFood tblFood){
		return zhFoodManageMapper.findFood(tblFood);
	}


	//增加膳食
	public int addFood(TblFood tblFood){

		return zhFoodManageMapper.addFood(tblFood);
	}

	//保健端膳食管理表格
	public List<TblFood> findFoodManage(TblFood tblFood){
		return zhFoodManageMapper.findFoodManage(tblFood);
	}

	//保健端膳食管理表格总页数
	public List<TblFood> totalPageFoodManage(TblFood tblFood){
		return zhFoodManageMapper.totalPageFoodManage(tblFood);
	}

	//修改膳食
	public int updateFood(TblFood tblFood){
		return zhFoodManageMapper.updateFood(tblFood);
	}

	//删除膳食
	public int deleteFood(TblFood tblFood){ return zhFoodManageMapper.deleteFood(tblFood); }


}
