/**  
 * @Title: Test.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.constant;

import java.util.Calendar;
import java.util.Date;

import com.zhaosen.util.DateUtil;
import com.zhaosen.util.MD5;


/**
 * ClassName: Test 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
public class Test {

	public static void main(String[] args) {
		
		try {
			Date date =  DateUtil.convertStringToDate("2010-03-02", DateUtil.DATE_FORMAT_yyyyMMdd);
//			System.out.println(date);
			System.out.println(getAge(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
