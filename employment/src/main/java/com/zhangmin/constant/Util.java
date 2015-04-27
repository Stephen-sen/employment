/**  
 * @Title: Util.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.constant;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.zhaosen.util.DateUtil;
import com.zhaosen.util.MD5;

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
	
	public static  String getAge(Date birthDay) throws Exception { 
        Calendar cal = Calendar.getInstance(); 

        if (cal.before(birthDay)) { 
            throw new IllegalArgumentException( 
                "The birthDay is before Now.It's unbelievable!"); 
        } 

        int yearNow = cal.get(Calendar.YEAR); 
        int monthNow = cal.get(Calendar.MONTH)+1; 
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
        
        cal.setTime(birthDay); 
        int yearBirth = cal.get(Calendar.YEAR); 
        int monthBirth = cal.get(Calendar.MONTH); 
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 

        int age = yearNow - yearBirth; 

        if (monthNow <= monthBirth) { 
            if (monthNow == monthBirth) { 
                //monthNow==monthBirth 
                if (dayOfMonthNow < dayOfMonthBirth) { 
                    age--; 
                } 
            } else { 
                //monthNow>monthBirth 
                age--; 
            } 
        } 

        return age +""; 
    }
	
	/**
	* 获取访问者IP
	* 
	* 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	* 
	* 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	* 如果还不存在则调用Request .getRemoteAddr()。
	* 
	* @param request
	* @return
	*/
	public static String getIpAddr(HttpServletRequest request) throws Exception{
	String ip = request.getHeader("X-Real-IP");
	if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	return ip;
	}
	ip = request.getHeader("X-Forwarded-For");
	if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	// 多次反向代理后会有多个IP值，第一个为真实IP。
	int index = ip.indexOf(',');
	if (index != -1) {
	return ip.substring(0, index);
	} else {
	return ip;
	}
	} else {
	return request.getRemoteAddr();
	}
	}
	
	
	public static void main(String arg[])
	{
		MD5 md5 = new MD5();
		System.out.println(md5.getMD5ofStr("123"));
	}
}
