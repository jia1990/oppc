package com.oppc.user.dao;


import com.oppc.user.entity.UserEntity;

/**
 * @author xingja
 * 用户表的数据操作接口
 */


public interface IUserDao{
	/**
	 * 根据用户名和密码来查看这个用户是否存在
	 * 用户登录时使用
	 * @param vcUname
	 * @param vcPwd
	 * @return
	 */
	public UserEntity findUser(String vcUname ,String vcPwd);
	
	/**
	 * 根据用户名查询该用户是否存在
	 * @param vcUname
	 * @return
	 */
	public UserEntity findByUname(String vcUname);
	
	/**
	 * 这是添加用户信息的方法
	 * @param userEntity
	 * @return
	 */
	public boolean addUser(UserEntity userEntity);
}
