package com.great.childschool.tools;

import java.util.HashMap;
import java.util.Map;

/**
 *@className PersonVerify
 *@description 身份验证
 *@author MJChen
 *@date 2019/12/19 15:24
 *@version 1.0
 **/
public class PersonVerify
{
	public static String personVerify() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify";
		try {
			byte[] bytes = FileUtil.readFileByBytes("D:\\pic\\cmj.jpeg");
			String encode = Base64Util.encode(bytes);
			Map<String, Object> map = new HashMap<>();
			map.put("image", encode);
			map.put("liveness_control", "HIGH");
			map.put("name", "张三");
			map.put("id_card_number", "110...");
			map.put("image_type", "BASE64");
			map.put("quality_control", "LOW");

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
		PersonVerify.personVerify();
	}
}
