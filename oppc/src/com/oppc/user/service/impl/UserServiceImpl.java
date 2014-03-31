package com.oppc.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oppc.user.dao.impl.UserDaoImpl;
import com.oppc.user.entity.UserEntity;
import com.oppc.user.service.IUserService;
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	/*
	 * (non-Javadoc)
	 * @see com.oppc.user.service.IUserService#findUser(java.lang.String, java.lang.String)
	 */
	@Override
	public UserEntity findUser(String vcUname, String vcPwd) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see com.oppc.user.service.IUserService#findByUname(java.lang.String)
	 */
	@Override
	public UserEntity findByUname(String vcUname) {
		// TODO Auto-generated method stub
		return userDaoImpl.findUserByName(vcUname);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.oppc.user.service.IUserService#addUser(com.oppc.user.entity.UserEntity)
	 */
	@Override
	public boolean addUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		if(null != userEntity){

			userDaoImpl.addUser(userEntity);
			
			return true;
		}
		
		return false;
	}

}
