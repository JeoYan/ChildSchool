package com.great.childschool.service;

import com.great.childschool.entity.TblBaby;
import com.great.childschool.entity.TblChecklist;
import com.great.childschool.entity.TblClassroom;
import com.great.childschool.mapper.ZhMedicalManageDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * 体检管理Service层
 * by 张宏
 */
@Service
public class ZhMedicalManageService
{

	@Resource
	private ZhMedicalManageDao zhMedicalManageDao;



	/**
	 * 保健端体检管理
	 *
	 */

	//查询全部班级
	public List<TblClassroom> allClass(){
		return zhMedicalManageDao.allClass();
	};

	//查询班级宝宝
	public List<TblBaby> findbaby(long cId){

		return zhMedicalManageDao.findbaby(cId);
	};

	//查询是否已增加完体检
	public TblChecklist findMedical(TblChecklist tblChecklist){
		return zhMedicalManageDao.findMedical(tblChecklist);
	}

	//增加体检信息
	public int addMedical(TblChecklist tblChecklist){

		return zhMedicalManageDao.addMedical(tblChecklist);
	}

	//保健端体检管理表格
	public List<TblChecklist> findMedicalManage (TblChecklist tblChecklist){

		return  zhMedicalManageDao.findMedicalManage(tblChecklist);
	}

	//保健端体检管理表格的总页数
	public List<TblChecklist>  totalPageMedicalManage(TblChecklist tblChecklist){

		return  zhMedicalManageDao.totalPageMedicalManage(tblChecklist);
	}

	//修改体检信息
	public int updateMedical(TblChecklist tblChecklist){

		return zhMedicalManageDao.updateMedical(tblChecklist);
	}

	//删除体检信息
	public int deleteMedical(TblChecklist tblChecklist){
		return zhMedicalManageDao.deleteMedical(tblChecklist);
	};



	/**
	 * 家长端体检情况
	 *
	 */

	//家长端体检情况表格
	public List<TblChecklist> findMedicalCase (TblChecklist tblChecklist){

		return zhMedicalManageDao.findMedicalCase(tblChecklist);
	}

	//家长的体检情况总条数
	public List<TblChecklist>  totalPageMedicalCase (TblChecklist tblChecklist){


		return  zhMedicalManageDao.totalPageMedicalCase(tblChecklist);

	}

}
