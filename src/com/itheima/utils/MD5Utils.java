package com.itheima.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//????
public class MD5Utils {
	/**
	 * ???md5????????????
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			//转成字节数组
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("???md5???????");
		}
		//转成字符串
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16????????
		// ???????????????32???????????0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}
