package com.great.childschool.mapper;
import com.great.childschool.entity.CmjTblBaby;
import com.great.childschool.entity.TblParent;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @Author MJChen
 * @Description //班级信息接口
 * @Date 16:53 2019/12/17
 **/
@Mapper
public interface ClassInfoMapper
{
	/**
	 * @Author MJChen
	 * @Description //TODO
	 * @Date 14:59 2019/12/18
	 **/
	public List<CmjTblBaby> queryClassInfo(int page,int size,String beginDate,String endDate);
	/**
	 * @Author MJChen
	 * @Description //查询班级总记录数
	 * @Date 15:02 2019/12/18
	 **/
	public Integer countClassInfo(String beginDate,String endDate);
	/**
	 * @Author MJChen
	 * @Description //查询宝宝信息
	 * @Date 15:02 2019/12/18
	 **/
	public CmjTblBaby findBabyInfo(int bid);
	/**
	 * @Author MJChen
	 * @Description //查询家长信息
	 * @Date 15:02 2019/12/18
	 **/
	public TblParent findParentInfo(int bid);
}
