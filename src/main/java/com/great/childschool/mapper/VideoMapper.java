package com.great.childschool.mapper;

import com.great.childschool.entity.CmjTblVideo;
import com.great.childschool.entity.TblRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 *视频mapper接口
 * @author : MJChen
 * @date : 10:17 2019/12/31
 */
@Mapper
public interface VideoMapper
{
	/**
	 * //查询所有视频信息
	 * @author : MJChen
	 * @date : 10:18 2019/12/30
	 * @param
	 * @return : java.util.List<com.great.childschool.entity.CmjTblVideo>
	 */
	public List<CmjTblVideo> findAllVideo();
	/**
	 * //查询所有的视频角色
	 * @author : MJChen
	 * @date : 10:34 2019/12/31
	 * @param page
	 * @param size
	 * @return : java.util.List<com.great.childschool.entity.TblRole>
	 */
	public List<TblRole> findAllVideoRole(int page, int size);
	/**
	 * //查询当前家长的班级角色所拥有的的视频
	 * @author : MJChen
	 * @date : 15:17 2020/1/2
	 * @param page
	 * @param size
	 * @return : java.util.List<com.great.childschool.entity.TblRole>
	 */
	public List<CmjTblVideo> findVideoByRoleId(int page, int size);
	/**
	 * //查询当前截止的班级角色所拥有的视频数量
	 * @author : MJChen
	 * @date : 15:18 2020/1/2
	 * @param
	 * @return : java.lang.Integer
	 */
	public Integer countVideoByRoleId();

	/**
	 * //根据角色id查询他所拥有的视频权限
	 * @author : MJChen
	 * @date : 11:16 2019/12/31
	 * @param rid
	 * @return : java.util.List<java.lang.Integer>
	 */
	public List<Integer> findVidByRid(int rid);
	/**
	 * // 给角色分配视频菜单
	 * @author : MJChen
	 * @date : 11:58 2019/12/31
	 * @param list
	 * @param rid
	 * @return : boolean
	 */
	public boolean distributeMenu(List<Integer> list, Integer rid);
}
