package com.great.childschool.service;

import com.great.childschool.aoplog.Log;
import com.great.childschool.entity.TblMenu;
import com.great.childschool.entity.TblParent;
import com.great.childschool.entity.TblWorker;
import com.great.childschool.mapper.YjjParentLoginMapper;
import com.great.childschool.mapper.YjjWorkerLoginMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 幼儿园工作人员登入业务层
 *     by 严俊杰
 */

@Service
public class YjjWorkerLoginService
{

	@Resource
	private YjjWorkerLoginMapper workerLoginMapper;

	/**
	 * 幼儿园工作人员登入，查询账号和密码是否存在
	 *     by 严俊杰
	 */
	@Transactional
	@Log(operationType="1",operationName="工作人员登入账号密码查询对应的用户")
	public TblWorker workerLoginCheck(String wid, String passWord){

		return workerLoginMapper.workerLoginCheck(wid,passWord);
	}

	/**
	 * 幼儿园工作人员登入，根据菜单获取对应的菜单
	 *     by 严俊杰
	 */
	@Transactional
	@Log(operationType="1",operationName="幼儿园工作人员登入，根据菜单获取对应的菜单")
	 public List<TblMenu> workerMenuFind(String wid){
		return workerLoginMapper.workerMenuFind(wid);
	}





}
