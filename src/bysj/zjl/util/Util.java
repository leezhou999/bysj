package bysj.zjl.util;

import java.util.Random;
/**
 * 工具包
 * @author Administrator
 *
 */
public class Util {
	/**
	 * 生成8位注册码
	 * @return
	 */
	public String genRandomNum(){  
	      int  maxNum = 36;  
	      int i;  
	      int count = 0;  
	      char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
	        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
	        'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
	      StringBuffer pwd = new StringBuffer("");  
	      Random r = new Random();  
	      while(count < 8){  
	       i = Math.abs(r.nextInt(maxNum));     
	       if (i >= 0 && i < str.length) {  
	        pwd.append(str[i]);  
	        count ++;  
	       }  
	      }  
	      return pwd.toString();  
	    }
	/*public String sqlDate2UtilDate() {
		// util.date转换成sql.date

		java.util.Date utilDate = new java.util.Date(); //获取当前时间

		System.out.println(utilDate);

		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		System.out.println(sqlDate);



		// sql.date转换成util.date

		java.sql.Date sqlDate1 = new java.sql.Date(new java.util.Date().getTime());

		System.out.println(sqlDate1);

		java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());

		System.out.println(utilDate1);
		
	}*/
}
