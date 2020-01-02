package com.great.childschool.mapper;

import com.great.childschool.entity.CmjBabySign;
import com.great.childschool.entity.CmjTblBaby;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @Author MJChen
 * @Description //班级考勤接口
 * @Date 16:53 2019/12/17
 **/
@Mapper
public interface ClassSignMapper
{
	/**
	 * @Author MJChen
	 * @Description //查询班级考勤
	 * @Date 9:57 2019/12/24
	 * @Param [babyName]
	 * @return com.great.childschool.entity.CmjTblBaby
	 **/
	public List<CmjTblBaby> classSign(int page, int size, String bName);
	/**
	 * @Author MJChen
	 * @Description //查询考勤人数
	 * @Date 10:11 2019/12/24
	 * @Param [babyName]
	 * @return java.lang.Integer
	 **/
	public Integer countSign(String bName);
	/**
	 * @Author MJChen
	 * @Description //查询宝宝考勤信息
	 * @Date 14:00 2019/12/24
	 * @Param [bid, beginDate, endDate]
	 * @return java.util.List<com.great.childschool.entity.CmjBabySign>
	 **/
	public List<CmjBabySign> babySigin(int bid, String beginDate, String endDate);
}
