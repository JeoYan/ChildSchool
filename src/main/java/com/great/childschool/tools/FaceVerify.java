package com.great.childschool.tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *@className FaceVerify
 *@description TODO
 *@author MJChen
 *@date 2019/12/19 15:33
 *@version 1.0
 **/
public class FaceVerify
{
	public static String faceVerify() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceverify";
		try {
			byte[] bytes = FileUtil.readFileByBytes("D:\\pic\\chongyu.jpeg");
			String encode = Base64Util.encode(bytes);
			byte[] bytes2 = FileUtil.readFileByBytes("D:\\pic\\cmj.jpeg");
			String encode2 = Base64Util.encode(bytes2);
			JSONObject obj1 = new JSONObject();
			obj1.put("image",encode);
			obj1.put("image_type","BASE64");
			obj1.put("face_field","age,beauty,expression");
			JSONObject obj2 = new JSONObject();
			obj2.put("image",encode2);
			obj2.put("image_type","BASE64");
			obj2.put("face_field","age,beauty");
			JSONArray array = new JSONArray();
			array.add(obj1);
			array.add(obj2);
			System.out.println(array.size());
			System.out.println(array.get(0));
			System.out.println(array.get(1));
			String param = GsonUtils.toJson(array);

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
		FaceVerify.faceVerify();
	}
}
