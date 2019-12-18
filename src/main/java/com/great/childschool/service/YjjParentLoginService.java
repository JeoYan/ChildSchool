package com.great.childschool.service;

import com.great.childschool.entity.TblParent;
import com.great.childschool.mapper.YjjParentLoginMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * 家长登入业务层
 *     by 严俊杰
 */

@Service
public class YjjParentLoginService
{

	@Resource
	private YjjParentLoginMapper parentLoginMapper;

	/**
	 * 家长登入，查询账号和密码是否存在
	 *     by 严俊杰
	 */
	@Transactional
	public TblParent parentLoginCheck(String uPhone,String passWord){

		return parentLoginMapper.parentLoginCheck(uPhone,passWord);
	}




}
