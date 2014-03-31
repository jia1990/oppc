package com.oppc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xingjia
 * 这是时间工具类(处理时间的问题，包括时间的格式转化)
 */
public final class TimeTool {
	
	public static Date StringToDate(String strTime){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		
		Date date = null;
		
		try {
			
			date = sdf.parse(strTime);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
		
	}
}
