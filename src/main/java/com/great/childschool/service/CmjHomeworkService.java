package com.great.childschool.service;

import com.great.childschool.entity.CmjHomework;
import com.great.childschool.mapper.HomeworkMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@className CmjHomeworkService
 *@description 作业Service
 *@author MJChen
 *@date 2019/12/25 11:33
 *@version 1.0
 **/
@Service
public class CmjHomeworkService
{
	@Resource
	private HomeworkMapper homeworkMapper;

	public boolean postHomework(String filename,String fileUrl,String curDate){
		return homeworkMapper.postHomework(filename,fileUrl,curDate);
	}

	public List<CmjHomework> findClassHomework(String curDate){
		return homeworkMapper.findClassHomework(curDate);
	}

	public CmjHomework findHomeworkById(int hid){
		return homeworkMapper.findHomeworkById(hid);
	}

	public boolean submitHomework(int bid,int hid,String curDate,String fileUrl){
		return homeworkMapper.submitHomework(bid,hid,curDate,fileUrl);
	}

	public List<HashMap<String,String>>classHomework(int curPage,int size,String curDate){
		return homeworkMapper.classHomework(curPage,size,curDate);

	}

	public Integer countClassHomework(String curDate){
		return homeworkMapper.countClassHomework(curDate);
	}

	public String findBabyHomeworkById(int bWid){
		return homeworkMapper.findBabyHomeworkById(bWid);
	}

	public boolean markHomework(int bWid,String score){
		return homeworkMapper.markHomework(bWid,score);
	}

	public List<CmjHomework> findPastHomework(int bid,String curDate,int page,int size){
		return homeworkMapper.findPastHomework(bid,curDate,page,size);
	}

	public Integer countPastHomework(int bid,String curDate){
		return homeworkMapper.countPastHomework(bid,curDate);
	}
}
