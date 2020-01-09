package com.great.childschool.service;

import com.great.childschool.entity.CmjTblVideo;
import com.great.childschool.entity.TblRole;
import com.great.childschool.mapper.VideoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *视频service
 *@author MJChen
 *@date 2019/12/30 10:19
 *@version 1.0
 **/
@Service
public class CmjVideoService
{
	@Resource
	private VideoMapper videoMapper;
	public List<CmjTblVideo> findAllVideo(){
		return videoMapper.findAllVideo();
	}

	public List<TblRole> findAllVideoRole(int page,int size){
		return videoMapper.findAllVideoRole(page,size);
	}

	public List<Integer> findVidByRid(int rid){
		return videoMapper.findVidByRid(rid);
	}

	public boolean distributeMenu(List<Integer> list, Integer rid){
		return videoMapper.distributeMenu(list,rid);
	}

	public List<CmjTblVideo> findVideoByRoleId(int page, int size){
		return videoMapper.findVideoByRoleId(page,size);
	}

	public Integer countVideoByRoleId(){
		return videoMapper.countVideoByRoleId();
	}
}
