package com.oppc.user.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.oppc.user.entity.UserEntity;
import com.oppc.user.service.impl.UserServiceImpl;

/**
 * @author xingjia
 * 用户信息操作的action类
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	/**
	 * 添加用户信息的操作
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("AddUser")
	public String addUser2(HttpServletRequest request, HttpServletResponse response){
		
		String username = request.getParameter("usernamesignup");
		
		String password = request.getParameter("passwordsignup");
		
		Date birthday = new Date();
		
		UserEntity userEntity = new UserEntity();
		userEntity.setVcUid("nkl223");
		userEntity.setVcUname(username);
		userEntity.setVcPwd(password);
		userEntity.setDtBirday(birthday);
		
		boolean flag = userServiceImpl.addUser(userEntity);

		
		if(flag){
			return "/main";
		}else{
			return "user/AddUser11";
		}
	}
	
	/**
	 * 这是用户管理的方法
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("Manage1")
	public String manageUser(HttpServletRequest request,HttpServletResponse response){
		
		return "user/Sjoo";
	}
	
	/**
	 * 这是用户管理的方法2
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("Manage2")
	public String manageUser2(HttpServletRequest request,HttpServletResponse response){
		
		return "user/Sjoo2";
	}
}
