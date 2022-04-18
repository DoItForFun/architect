package com.study.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @author lizhe
 */
public class Md5Utils {

	/**
	 * 
	 * @Title: Md5Utils.java
	 * @Package com.imooc.utils
	 * @Description: 对字符串进行md5加密
	 */
	public static String getMD5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		return newstr;
	}
}
