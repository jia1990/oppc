package com.oppc.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oppc.dbc.BaseDao;
import com.oppc.user.entity.UserEntity;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDao<UserEntity,String>{
	
	
	
	/**
	 * 这是添加用户信息的方法
	 * @param userEntity
	 * @return
	 */
	public boolean addUser(UserEntity userEntity){
		if(null!=userEntity){
			this.save(userEntity);
			return true;
		}
		return false;
		
	}
	
	/**
	 * 这是根据用户查询用户的方法
	 * @param vcUname
	 * @return
	 */
	public UserEntity findUserByName(String vcUname){
		
		String shql = "from UserEntity as o where o.vcUname=?";
		UserEntity userEntity = null;
		
		if(null != vcUname){
			
			userEntity = (UserEntity) this.getSignleByHSql(shql,vcUname);
			
			if(null != userEntity){
				
				return userEntity;
				
			}
		}
		return null;
	}
	
	
	/**
	 * 这是查询菜单的方法，主要是根据纯sql语句进行查询
	 * @return
	 */
	public List findMenu(){
		
//		String hsql = "select c.int_disId pid,c.vc_disText pname,b.int_disId zid,b.vc_disText zname,"
//				+ "a.int_disId cid,a.vc_disText cname from os_menu a,os_menu b,os_menu c "
//				+ "where a.int_parId=b.int_disId and b.int_parId=c.int_disId";
//		
//		List listMenu = this.getAllBySql(hsql);
//		
//		if(listMenu.size() != 0){
//			
//			return listMenu;
//		}
//		
		String sql = "select c.int_disId rooId,c.vc_disText rootStr,b.int_disId fatherId,b.vc_disText fatherStr,"
				+ "a.int_disId childId,a.vc_disText childStr,a.vc_disUrl childUrl from os_menu a,os_menu b,os_menu c "
				+ "where a.int_parId=b.int_disId and b.int_parId=c.int_disId";
		
		List listMenu = this.getAllBySql(sql);
		
		if(listMenu.size() != 0){
			
			return listMenu;
		}
		
		return null;
		
	}
	
}
