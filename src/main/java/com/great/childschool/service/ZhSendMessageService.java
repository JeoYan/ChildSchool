package com.great.childschool.service;


import com.great.childschool.mapper.ZhSendMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消息业务dao层
 * by 张宏
 */
@Service
public class ZhSendMessageService
{
	@Resource
	private ZhSendMessageMapper zhSendMessageMapper;

}
