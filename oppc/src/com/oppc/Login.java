package com.oppc;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.ItalicAction;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.oppc.user.entity.MenuEntity;
import com.oppc.user.entity.MenuPEntity;
import com.oppc.user.entity.UserEntity;
import com.oppc.user.service.impl.UserServiceImpl;
import com.oppc.util.JSONTool;

@Controller
@RequestMapping("/")
public class Login {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@RequestMapping("ReLogin")
	public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response){
	
		System.out.println("这是登录必须经过的---跳转到登录页面");
	
		return new ModelAndView("/demo");
	}
	
	
	/**
	 * 这是动态构建树的方法，具体输出为json，目前各个元素固定，以后改进
	 * @param request
	 * @param response
	 */
	@RequestMapping("TreeData")
	public void treeData(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("测试动态树");
		
//		String jsonStr = "[{'id':'1','text':'云南供电局','children':[{'id':'403351','text':'测试','children':[{'id':'403352','text':'测试小区','children':[{'id':'403360','text':'53001232','leaf':true},{'id':'403359','text':'53001231','leaf':true},{'id':'403353','text':'测试台区','leaf':true},{'id':'403355','text':'53001236','leaf':true},{'id':'403356','text':'53001237','leaf':true},{'id':'403357','text':'53001238','leaf':true},{'id':'403358','text':'53001239','leaf':true},{'id':'403361','text':'53001240','leaf':true}]}]},{'id':'402930','text':'成都军区昆明解甲园','children':[{'id':'402931','text':'解甲园','children':[{'id':'401752','text':'解甲园','leaf':true}]}]},{'id':'401741','text':'个旧市供电局','children':[{'id':'401742','text':'个旧市','children':[{'id':'402929','text':'53010001','leaf':true},{'id':'403333','text':'53010002','leaf':true},{'id':'401743','text':'丽水金湾一号变压器','leaf':true},{'id':'401751','text':'丽水金湾二号变压器','leaf':true}]}]}]}]";
		String jsonStr = "[{'id':46,'urlid':'','text':'菜单栏','children':[{'id':461,'urlid':'','text':'用户管理','children':[{'id':4611,'urlid':'user/Manage1.sp','text':'用户1','leaf':true},{'id':4612,'urlid':'user/Manage2.sp','text':'用户2','leaf':true}]},{'id':462,'urlid':'','text':'业务管理','children':[{'id':4621,'urlid':'business/Manage1.sp','text':'业务1','leaf':true},{'id':4622,'urlid':'business/Manage2.sp','text':'业务2','leaf':true}]}]}]";
		/**
		 * 将获取到的数据重新装载，便于输出json
		 */
		List menuList = userServiceImpl.findMenu();
		MenuPEntity menupEntity = new MenuPEntity();
		Iterator menuIterator = menuList.iterator();
		
		while(menuIterator.hasNext()){
			MenuPEntity str = (MenuPEntity) menuIterator.next();
			System.out.println(str.getRooId()+"==="+str.getFatherId()+"==="+str.getChildId()+"====="+str.getChildUrl());
		}
		//String jsonStr = "";
		String jsonText = JSON.toJSONString(menuList, true);  
		System.out.println(jsonText);
		
		
		JSONTool.jsonWrite(response, jsonStr);
	}
	
	
	/**
	 * 登录页面登录后的验证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("RealLogin")
	public ModelAndView userRealLogin(HttpServletRequest request, HttpServletResponse response){
		/*
		 *从登录页面传来的用户名和密码 
		 */
		String userName = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		System.out.println(userName+"=="+password);
		
		
		//  查询此用户是否存在
		UserEntity userEntity = userServiceImpl.findByUname(userName);
		
		if(null != userEntity){
			
			System.out.println("密码是否相同"+userEntity.getVcPwd().equals(password));
			
			if(userEntity.getVcPwd().equals(password)){
				
				request.getSession().setAttribute("userName", userEntity.getVcUname());
				
			}
			
		}
		
		return new ModelAndView("redirect:./DefaultSite.sp");
	}
	
	
	/**
	 * 跳转到主页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("DefaultSite")
	public ModelAndView defaultSite(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/main");
	}
	
	
	/**
	 * 这是一个测试跳转成功的页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("Welcome")
	public ModelAndView welcomeSite(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/custom-drop-logic");
	}
	
	/**
	 * 获取当前状态的session
	 * @param request
	 * @param response
	 */
	@RequestMapping("GetSess")
	public void getSess(HttpServletRequest request, HttpServletResponse response){
		
		
		String jsonStr = (String) request.getSession().getAttribute("userName");
		JSONTool.jsonWrite(response, jsonStr);
	}
}
