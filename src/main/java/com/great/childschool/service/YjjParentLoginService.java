package com.great.childschool.service;

import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.YjjTblBookPage;
import com.great.childschool.mapper.YjjParentLoginMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
	 * by 严俊杰
	 */
	@Transactional
	public TblParent parentLoginCheck(String uPhone, String passWord)
	{

		return parentLoginMapper.parentLoginCheck(uPhone, passWord);
	}


	/**
	 * 获得密码
	 */
	@Transactional
	public TblParent getPsw(String phone)
	{

		return parentLoginMapper.getPsw(phone);
	}






	/**
	 * 获得亲子阅读的首页信息
	 * by 严俊杰
	 */

	public List<YjjTblBookPage> getBookFirstPage()
	{
		List<YjjTblBookPage> mylist = new ArrayList<>();
		//查询书籍
		List<YjjTblBookPage> list1 = parentLoginMapper.findBookid();
		for (YjjTblBookPage bookPage : list1)
		{
			//获得各书籍的信息
			List<YjjTblBookPage> list2 = parentLoginMapper.findBookPage(bookPage.getBookid());
			//将首页信息保存
			mylist.add(list2.get(0));
		}

		return mylist;
	}



}

