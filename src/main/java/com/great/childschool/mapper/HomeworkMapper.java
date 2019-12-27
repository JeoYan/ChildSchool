package com.great.childschool.mapper;
import com.great.childschool.entity.CmjHomework;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MJChen
 * @description //作业接口
 * @date 11:30 2019/12/25
 **/
@Mapper
public interface HomeworkMapper
{
	/**
	 * 教师发布作业
	 * @author MJChen
	 * @date 11:32 2019/12/25
	 * @param filename [filename, fileUrl, curDate]
	 * @return boolean
	 */
	public boolean postHomework(String filename,String fileUrl,String curDate);
	/**
	 * //家长提交作业
	 * @author : MJChen
	 * @date : 10:45 2019/12/26
	 * @param bid
	 * @param hid
	 * @param curDate
	 * @param fileUrl
	 * @return : boolean
	 */
	public boolean submitHomework(int bid,int hid,String curDate,String fileUrl);
	/**
	 * //教师查看今日提交作业
	 * @author : MJChen
	 * @date : 16:04 2019/12/26
	 * @param page
	 * @param size
	 * @param curDate
	 * @return : java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>>classHomework(int page,int size,String curDate);
	/**
	 * //统计今日作业提交情况
	 * @author : MJChen
	 * @date : 16:04 2019/12/26
	 * @param curDate
	 * @return : java.lang.Integer
	 */
	public Integer countClassHomework(String curDate);

	/**
	 * @Author MJChen
	 * @Description //家长查询今日作业
	 * @Date 15:55 2019/12/25
	 * @Param [curDate]
	 * @return java.util.List<com.great.childschool.entity.CmjHomework>
	 **/
	public List<CmjHomework> findClassHomework(String curDate);
	/**
	 * @Author MJChen
	 * @Description //根据作业编号找寻当前作业
	 * @Date 16:43 2019/12/25
	 * @Param [hid]
	 * @return com.great.childschool.entity.CmjHomework
	 **/
	public CmjHomework findHomeworkById(int hid);
	/**
	 * //根据宝宝作业id查询作业路径
	 * @author : MJChen
	 * @date : 9:19 2019/12/27
	 * @param bWid
	 * @return : java.lang.String
	 */
	public String findBabyHomeworkById(int bWid);
	/**
	 * //教师评价作业
	 * @author : MJChen
	 * @date : 10:10 2019/12/27
	 * @param bWid
	 * @param score
	 * @return : boolean
	 */
	public boolean markHomework(int bWid,String score);
	/**
	 * //家长查询往期作业
	 * @author : MJChen
	 * @date : 10:53 2019/12/27
	 * @param bid
	 * @param curDate
	 * @param page
	 * @param size
	 * @return : java.util.List<com.great.childschool.entity.CmjHomework>
	 */
	public List<CmjHomework> findPastHomework(int bid,String curDate,int page,int size);
	/**
	 * //查询往期作业的数量
	 * @author : MJChen
	 * @date : 11:05 2019/12/27
	 * @param bid
	 * @param curDate
	 * @return : java.lang.Integer
	 */
	public Integer countPastHomework(int bid,String curDate);
}
