package com.oppc.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;



public final class JSONTool {
	//private static final Logger logger = LoggerFactory.getLogger(JSONTool.class);
	
	
	/***
	 * 将List对象序列化为JSON文本
	 */
	public static <T> String toJSONString(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);

		return jsonArray.toString();
	}
	
	/**
	 * 将list转换为json格式的数据
	 */
	public static <T> String serialize(T object) {
        return JSON.toJSONString(object);
    }
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
