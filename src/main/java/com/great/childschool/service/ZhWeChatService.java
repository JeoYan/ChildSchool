package com.great.childschool.service;


import com.great.childschool.entity.*;
import com.great.childschool.mapper.ZhWeChatMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 微信业务Service层
 * by 张宏
 */
@Service
public class ZhWeChatService
{
	@Resource
	private ZhWeChatMapper zhWeChatMapper;


	@Transactional
	public TblParent weChatLogin(String uPhone, String passWord)
	{

		return zhWeChatMapper.weChatLogin(uPhone, passWord);


	};



	//查询课程表
	public List<ZhTblSubject> findCourse(String pid, String date){

		return zhWeChatMapper.findCourse(pid,date);
	}

	//查询作业评分
	public List<ZhTblHomework> findScore(ZhTblHomework zhTblHomework){

		return zhWeChatMapper.findScore(zhTblHomework);
	}


	//体检情况表格
	public List<TblChecklist> findMedicalCase(TblChecklist tblChecklist)
	{
		return zhWeChatMapper.findMedicalCase(tblChecklist);
	}

	//保健端膳食管理表格
	public List<TblFood> findFoodManage(TblFood tblFood){
		return zhWeChatMapper.findFoodManage(tblFood);
	}



	//查询考勤
	public List<ZhBabySign> findAttendance(String pid, String date){
		return zhWeChatMapper.findAttendance(pid,date);

	}

	//个人信息
	public ZhInformation findInformation(String pid)
	{
		return zhWeChatMapper.findInformation(pid);
	}


	//通讯录
	public List<TblWorker> findAddress(){
		return zhWeChatMapper.findAddress();
	}


	//我的订单
	public List<ZhTblorder> findOrder(ZhTblorder zhTblorder){
		return zhWeChatMapper.findOrder(zhTblorder);
	}

}
