package com.great.childschool.mapper;





import com.great.childschool.entity.TbLog;
import com.great.childschool.entity.TjzTbClassRoom;
import com.great.childschool.entity.TjzTbCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface TjzBackMapper
{

//	public TbAdmin findByTbAdmin(TbAdmin admin);
//
	public List<TjzTbClassRoom> findClassRooms(Map<String, Object> map);

	public int findClassRoomNum(Map<String, Object> map);
//
//	public int deleteTbUserById(int userId);
//
//	public int addTbUser(TbUser user);
//
//	public int updateUser(TbUser user);
//
//	public int updateAdmin(TbAdmin admin);//事务管理测试
//
//	public List<TbMenu> findMenu(int personID);
//
	public List<TbLog> findLog(Map<String, Object> map);

	public int findLogNum(Map<String, Object> map);

	public int addLog(TbLog log);

//	public int upload(TbDocument document);
//
//	public List<TbMenu> findFirstMenu(int personID);
//
//	public List<TbMenu> findChildMenu(Map<String, Object> map);
//
//	//更新树
//	public int updateTree(Map<String, Object> map);
//
//	/**树置为未选*/
//	public int clearChecked(int updateRoleId);
//
//
//	public List<TbRole> findRole();
//
//	public int findLoginRole(int adminId);
//
//	public List<TbDocument> findByTbDocument(Map<String, Object> map);
//
//	public int findByTbDocumentNum(Map<String, Object> map);


}
