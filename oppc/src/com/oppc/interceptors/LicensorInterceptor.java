package com.oppc.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oppc.util.PropertiesTool;

/**
 * @author xingjia
 * 这是用户登录所要进行检查的拦截器
 *
 */
public class LicensorInterceptor implements HandlerInterceptor{
	
	
	/**不需要进行权限拦截的地址*/
	private List<String> excludeUrls;
	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("这是登录拦截器的after方法");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("这是登录拦截器的post方法");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("1==============测试系统是否登录");
		System.out.println("例外的路径："+excludeUrls);
		/**
		 * 判断此路径是否包含不需要检查的特殊路径
		 */
		StringBuffer path = request.getRequestURL();
		System.out.println(path);
		if(null!=excludeUrls){
			for(String url:excludeUrls){
				if(path.indexOf(url)>-1){
					return true;
				}
			}
		}
		
		
		String userName = (String) request.getSession().getAttribute("userName");
		if(null != userName){
			return true;
		}else{
			String valueStr = PropertiesTool.getProperty("LoginPage");
			
			response.sendRedirect(valueStr);
			return false;
		}
		
		
	}

	/**
	 * 自动生成的get方法
	 * @return
	 */
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}
	/**
	 * 自动生成的set方法
	 * @param excludeUrls
	 */
	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	
	
}
