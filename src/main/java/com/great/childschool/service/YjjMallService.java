package com.great.childschool.service;

import com.great.childschool.entity.*;
import com.great.childschool.mapper.YjjBookReadMapper;
import com.great.childschool.mapper.YjjMallMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小卖部业务层
 * by 严俊杰 12.20
 */

@Service
public class YjjMallService
{

	@Resource
	private YjjMallMapper mallMapper;


	/**获得表格数据 by 严俊杰
	 *
	 * @param searchInfo
	 * @return
	 */
	@Transactional
	public List<YjjTblCommodity> findComm(YjjSearchInfo searchInfo)
	{
		return mallMapper.findComm(searchInfo);
	}

	//总条数 by 严俊杰
	@Transactional
	public List<YjjTblCommodity> totalPage(YjjSearchInfo searchInfo)
	{
		return mallMapper.totalPage(searchInfo);
	}


	/**
	 * 删除商品时，同时删除tblcommodity数据和tblcommodity_pic的数据
	 */
	@Transactional
	public int delComm(String comid){
		int x=0;
		int y= mallMapper.delComm(comid);
		if(y>0){
			x=mallMapper.delCommPic(comid);
		}
		return x;
	}

	/**
	 * 新增商品的信息
	 */

	@Transactional
	public int addComm(YjjTblCommodity commodity){

		mallMapper.addComm(commodity);
		int comid=commodity.getComid();
		return comid;
	}


	@Transactional
	public int addCommPic(YjjTblCommodityPic commodityPic){

		return mallMapper.addCommPic(commodityPic);
	}



}