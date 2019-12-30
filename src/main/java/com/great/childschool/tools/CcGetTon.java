package com.great.childschool.tools;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CcGetTon
{
	
	// �ٶ��ƽӿ��ĵ���https://ai.baidu.com/docs#/Face-Match-V3/top

	public static String getToken() {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();

		// ��ȡtoken ��ַ
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		// ������ȡ�İٶ���Ӧ�õ�AK�����滻���Լ��ġ�
		String clientId = "ouWTTa8SbhuK9AitXxmMtI9e";
		// ������ȡ�İٶ���Ӧ�õ�SK�����滻���Լ���
		String clientSecret = "877k9hSYSSR1y804TnnSAVKvOLp7ZHya";
		String getAccessTokenUrl = authHost
		// 1.grant_typeΪ�̶�����
				+ "grant_type=client_credentials"
				+ "&client_id=" + clientId
				+ "&client_secret=" + clientSecret;
		try {
			// ����url�������Ӷ���
			URL url = new URL(getAccessTokenUrl);
			// ������
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// �������󷽷�
			connection.setRequestMethod("GET");
			// ����ʵ������
			connection.connect();
			// ��ȡURL����Ӧ
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(sb.toString());
		String token = jsonObject.getString("access_token");
		return token;
	}

	public static void main(String[] args) {
		String tonken = getToken();
		System.out.println(tonken);
	}
}
