package com.great.childschool.tools;

import java.util.HashMap;
import java.util.Map;

/**
 *@className UserGet
 *@description 用户信息查询
 *@author MJChen
 *@date 2019/12/19 14:22
 *@version 1.0
 **/
public class UserGet
{
	public static String userGet() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", "cmj");
			map.put("group_id", "chen");

			String param = GsonUtils.toJson(map);

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "24.bcd48cc215be2cdeb19ea461fdde13cb.2592000.1579319202.282335-18060691";

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		UserGet.userGet();
	}
}
