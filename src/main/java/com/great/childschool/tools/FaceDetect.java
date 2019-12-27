package com.great.childschool.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *@className FaceDetect
 *@description 人脸检测
 *@author MJChen
 *@date 2019/12/19 14:43
 *@version 1.0
 **/
public class FaceDetect
{
	public static String faceDetect() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			byte[] bytes = FileUtil.readFileByBytes("D:\\pic\\cmj.jpeg");
			String encode = Base64Util.encode(bytes);
			Map<String, Object> map = new HashMap<>();
			map.put("image", encode);
			map.put("image_type", "BASE64");

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
		FaceDetect.faceDetect();
	}
}
