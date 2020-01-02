package com.great.childschool.controller;

import com.great.childschool.entity.CmjTblVideo;
import com.great.childschool.entity.DataBean;
import com.great.childschool.entity.TblRole;
import com.great.childschool.service.CmjVideoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *视频控制层
 *@author MJChen
 *@date 2019/12/30 10:33
 *@version 1.0
 **/
@Controller
@RequestMapping("/VideoController")
public class CmjVideoController
{
	@Resource
	private CmjVideoService videoService;
	/**
	 * @Author MJChen
	 * @Description //跳转到某一界面
	 * @Date 14:51 2019/12/18
	 **/
	@RequestMapping("/query/{url}")
	public String turnToJsp(@PathVariable(value = "url") String path){
		return path;
	}
	@RequestMapping("/findAllVideo")
	@ResponseBody
	public DataBean findAllVideo(){
		DataBean<CmjTblVideo> dataBean = new DataBean<CmjTblVideo>();
		dataBean.setData(videoService.findAllVideo());
		dataBean.setCount(5);
		return dataBean;
	}
	@RequestMapping("/findAllVideoRole")
	@ResponseBody
	public DataBean findAllVideoRole(String page,String limit){
		int size = Integer.valueOf(limit);
		int curPage = (Integer.valueOf(page)-1)*size;
		DataBean<TblRole> dataBean = new DataBean<>();
		dataBean.setData(videoService.findAllVideoRole(curPage,size));
		dataBean.setCount(6);
		return dataBean;
	}

	@RequestMapping("/updateMenu")
	@ResponseBody
	public String updateMenu(String str,int rid){
		List<Integer> list = videoService.findVidByRid(rid);
		Map<Integer,Integer> map = new HashMap<>();
		JSONArray array = JSONArray.fromObject(str);
		for (int i = 0; i < array.size(); i++)
		{
			int id = array.getJSONObject(i).getInt("id");
			map.put(id,id);
			JSONArray children = array.getJSONObject(i).getJSONArray("children");
			for (int i1 = 0; i1 < children.size(); i1++)
			{
				int id1 = children.getJSONObject(i1).getInt("id");
				map.put(id1,id1);
			}
		}
		List<Integer> list1 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++)
		{
			if (map.containsKey(list.get(i))){
				map.remove(list.get(i));
			}
		}
		for (Map.Entry<Integer,Integer> entry:map.entrySet()){
			list1.add(entry.getValue());
		}
		if (list1.size() > 0){
			boolean b = videoService.distributeMenu(list1,rid);
			if (b){
				return "1";
			}
		}
		return "0";
	}

	@RequestMapping("/findVideoByVRid")
	@ResponseBody
	public Object findVideoByVRid(int id){
		List<CmjTblVideo> videos = videoService.findAllVideo();
		Map<Integer,CmjTblVideo > map = new HashMap<>();
		List<HashMap<String, Object>> result = new ArrayList<>();
		return fun(videos,result,id);
	}

	private Object fun(List<CmjTblVideo> videos, List<HashMap<String, Object>> result,int id) {
		for(CmjTblVideo video : videos){
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", video.getVid());
			map.put("title", video.getvName());
			map.put("spread", true);
			List<HashMap<String, Object>> result1 = new ArrayList<>();
			List<CmjTblVideo> children = video.getChildren();
			List<Integer> idList = videoService.findVidByRid(id);
			//这里可以根据自己需求判断节点默认选中
			for (Integer vid : idList)
			{
				if(vid.equals(video.getVid())){
					map.put("checked", true);
				}
			}

			map.put("children", fun(children, result1,id));
			result.add(map);
		}
		return result;
	}
}
