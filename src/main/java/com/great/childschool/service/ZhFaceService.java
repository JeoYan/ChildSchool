package com.great.childschool.service;



import com.great.childschool.entity.ZhTblWorker;
import com.great.childschool.mapper.ZhFaceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ZhFaceService
{
	@Resource
	private ZhFaceMapper zhFaceMapper;

	//查询所有工作人员

	public List<ZhTblWorker> findAllWork(){

		return  zhFaceMapper.findAllWork();
	}

//	//测试
//	public int save(ZhTblWorker tbladmin){
//		return zhFaceMapper.save(tbladmin);
//	}


}
