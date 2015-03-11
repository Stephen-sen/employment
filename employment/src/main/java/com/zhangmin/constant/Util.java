/**  
 * @Title: Util.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.constant;

import java.util.Date;

import com.zhaosen.util.DateUtil;

/**
 * ClassName: Util 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
public class Util {

	public static boolean isEmpty(String str){
		
		if("" == str || null == str){
			return true;
		}
		return false;
	}
	
	public static void main(String arg[])
	{
		System.out.println(DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss));
	}
}
