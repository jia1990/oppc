package com.oppc.user.dao.impl;

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

	
}
