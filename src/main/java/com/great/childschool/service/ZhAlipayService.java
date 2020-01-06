package com.great.childschool.service;


import com.great.childschool.entity.YjjTblBookPage;
import com.great.childschool.entity.YjjTblReadbook;
import com.great.childschool.entity.ZhTblParentReadbook;
import com.great.childschool.mapper.ZhAlipayMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 支付Service业务层
 * by 张宏
 */
@Service
public class ZhAlipayService
{
	@Resource
	private ZhAlipayMapper zhAlipayMapper;


	//查询书籍支付状态
    public ZhTblParentReadbook findbooktype(String pid, String bookid){

    	return zhAlipayMapper.findbooktype(pid,bookid);
    }


	//增加支付书籍信息
	public int addParentReadbook(String pid,String bookid){
    	return zhAlipayMapper.addParentReadbook(pid,bookid);
	}


}
