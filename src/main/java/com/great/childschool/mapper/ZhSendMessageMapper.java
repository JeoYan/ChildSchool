package com.great.childschool.mapper;


import com.great.childschool.entity.ZhTblNotice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 消息业务dao层
 * by 张宏
 */
@Mapper
public interface ZhSendMessageMapper
{

	//增加公告消息
	@Insert("insert tbl_notice (notitle,nconntext,ndate,wid) values (#{notitle},#{nconntext},#{ndate},#{wid})")
	public int addNotice(ZhTblNotice zhTblNotice);


	//园长端消息管理表格
	public List<ZhTblNotice> findNoticelManage (ZhTblNotice zhTblNotice);

	//园长端消息管理表格的总页数
	public List<ZhTblNotice>  totalPageNoticelManage(ZhTblNotice zhTblNotice);


	//修改公告

	@Update("update tbl_notice set notitle=#{notitle},nconntext=#{nconntext} where wid=#{wid} AND ndate=#{ndate}")
	public int updateNotice(ZhTblNotice zhTblNotice);


	//删除公告
	@Delete("delete from tbl_notice where  wid=#{wid} AND ndate=#{ndate}")
	public int deleteNotice(ZhTblNotice zhTblNotice);


}
