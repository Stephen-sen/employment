/**  
 * @Title: MessageCode.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.constant;

/**
 * ClassName: MessageCode 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class Const {
	
	/**
	 * 操作成功消息代码
	 */
	public static final String MSG_SUCCESS="01"; 
	
	/**
	 * 操作失败消息代码
	 */
	public static final String MSG_ERROR="02";
	
	/**
	 * 操作成功并打印
	 */
	public static final String MSG_SUCCESS_PRINT="03";
	
	public static String USER_PASSWORD_FAIL = "04"; // 密码错误
	public static String USER_USERNAME_NOT_FOUND = "05";// 未找匹配的用户名
	public static String USER_VALIDATION_CODE = "06"; // 验证码错误
	
	public static final int SUCCESS = 100;
  	public static final int FAILURE = 101;
  	public static final int LOGOUT=102; //登出

    public static final int ERR_PARAMS = 103;   //缺少参数
    public static final int NO_DATA = 104;      //没有匹配结果

    public static final int ERR_ACCOUNT = 201;   //账号或密码错误
    public static final int ACCOUNT_STOP = 202;  //账号停用
    public static final int ERR_OLDPWD = 203;    //原密码错误
    public static final int ERR_ACCOUNT_EQUIP = 204;    //账号设备匹配错误
    public static final int ERR_UNACTIVE_EQUIP = 205;    //账号设备匹配错误
}
