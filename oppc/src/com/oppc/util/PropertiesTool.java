package com.oppc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oppc.dbc.BaseDao;

/**
 * @author xingjia
 * 这个类是用于读取资源配置文件中的一些内容的方法
 */
public class PropertiesTool {
	
	
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	/**
	 * 资源属性
	 */
	private static Properties props;
	
	private static void initProperties(){
		try {
			props = loadAllProperties("config.properties");
		} catch (IOException e) {
			throw new RuntimeException("Load Properties error", e);
		}
	}
	public static Properties getProperties(){
		
			initProperties();
		
		return props;
	}
	
	public static String getProperty(String key){
		System.out.println(key);
		return getProperties().getProperty(key);
	}
	
	/**
	 * 加载.properties文件的方法
	 * @param resoName
	 * @return
	 */
	public static Properties loadAllProperties(String resoName) throws IOException{
		
		Enumeration<URL> urls  = PropertiesTool.class.getClassLoader().getResources("config.properties");
		Properties properties=new Properties();
		while(urls.hasMoreElements()){
			URL url = urls.nextElement();
			InputStream is = null;
			try {
				URLConnection con = url.openConnection();
				con.setUseCaches(false);
				is = con.getInputStream();
				properties.load(is);
			} finally {
				if (is != null) {
					is.close();
				}
			}
			
		}
		return properties;
		
		
	}
}
