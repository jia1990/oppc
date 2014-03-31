package com.oppc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public final class JSONTool {
	//private static final Logger logger = LoggerFactory.getLogger(JSONTool.class);
	
	/**
	 * 将json数据通过printwrite返回页面
	 * @param response
	 * @param jsonStr
	 */
	public static void jsonWrite( HttpServletResponse response, String jsonStr ){
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(jsonStr.toString());
			pw.flush();
			pw.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
		
	}
}
