package com.great.childschool.tools;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YjjTools
{








	//MD5加密
	public static String toMD5(String plainText)
	{
		StringBuffer buf = new StringBuffer("");
		try
		{
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(plainText.getBytes());
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;

			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0)
				{
					i += 256;
				}
				if (i < 16)
				{
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String toAdd0(int i)
	{

		return String.format("%08d", i);
	}

	//获取当前时间 yyyy-MM-dd格式
	public static String getDate()
	{

		//获取当前时间
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}


	public static String getDatea()
	{
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dft.format(System.currentTimeMillis());
	}


}
