/**  
 * @Title: Test.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.constant;

import com.zhaosen.util.MD5;


/**
 * ClassName: Test 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
public class Test {

	public static void main(String[] args) {
		MD5 md = new MD5();
		System.out.println(md.getMD5ofStr("123"));
	}
}
