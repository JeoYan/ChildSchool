package com.great.childschool.mapper;


import com.great.childschool.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 微信业务dao层
 * by 张宏
 */
@Mapper
public interface ZhWeChatMapper
{
	//登录
	@Select("Select * from tbl_parent where pphone=#{uPhone} and ppsw=#{passWord}")
	public TblParent weChatLogin(@Param("uPhone")String uPhone, @Param("passWord")String passWord);


	//查询课程表
	@Select("select * from tbl_course c,tbl_subject s,  (select  b.cid from tbl_baby b, (select * from  tbl_parent_baby where pid=#{pid}) a where a.bid=b.bid) d  where c.cdate=#{date}  and  c.subid=s.subid and  c.cid=d.cid")
	public List<ZhTblSubject> findCourse(@Param("pid")String pid, @Param("date")String date);

	//查询作业评分
	public List<ZhTblHomework> findScore(ZhTblHomework zhTblHomework);

	//体检情况表格
	public List<TblChecklist> findMedicalCase (TblChecklist tblChecklist);


	//保健端膳食管理表格
	public List<TblFood> findFoodManage(TblFood tblFood);


	//查询考勤
	@Select("select A.bsperiod,A.bstime,A.bsdate,B.sname from tbl_baby_sign A,tbl_status B,(select bid FROM tbl_parent_baby where pid=#{pid}) D  where  A.bid = D.bid and  A.sid=B.sid and A.bsdate=#{date}")
	public List<ZhBabySign> findAttendance(@Param("pid")String pid, @Param("date")String date);




	//个人信息
	@Select("select p.pname, p.pdate, b.bname,c.cname,b.bdate FROM  tbl_parent p,  tbl_baby b,tbl_classroom c,  (select * FROM tbl_parent_baby where pid=#{pid}) a  WHERE  a.pid=p.pid and a.bid=b.bid and c.cid=b.cid")
	public ZhInformation findInformation(@Param("pid")String pid);



	//通讯录
	@Select("select w.wname,w.wphone,r.rname FROM tbl_worker w,tbl_role r, tbl_worker_role wr where w.wid=wr.wid and r.rid=wr.rid")
	public List<TblWorker> findAddress();


	//我的订单
	public List<ZhTblorder> findOrder(ZhTblorder zhTblorder);
}
