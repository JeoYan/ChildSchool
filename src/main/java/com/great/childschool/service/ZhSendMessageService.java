package com.great.childschool.service;


import com.great.childschool.entity.ZhTblNotice;
import com.great.childschool.mapper.ZhSendMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息业务Service层
 * by 张宏
 */
@Service
public class ZhSendMessageService
{
	@Resource
	private ZhSendMessageMapper zhSendMessageMapper;



	//增加公告消息
	public int addNotice(ZhTblNotice zhTblNotice){

		return zhSendMessageMapper.addNotice(zhTblNotice);
	}


	//园长端消息管理表格
	public List<ZhTblNotice> findNoticelManage (ZhTblNotice zhTblNotice){
		return zhSendMessageMapper.findNoticelManage(zhTblNotice);
	};

	//园长端消息管理表格的总页数
	public List<ZhTblNotice>  totalPageNoticelManage(ZhTblNotice zhTblNotice){
		return zhSendMessageMapper.totalPageNoticelManage(zhTblNotice);
	}


	//修改公告

	public int updateNotice(ZhTblNotice zhTblNotice){

		return zhSendMessageMapper.updateNotice(zhTblNotice);
	};


	//删除公告
	public int deleteNotice(ZhTblNotice zhTblNotice){

		return zhSendMessageMapper.deleteNotice(zhTblNotice);
	};

}
